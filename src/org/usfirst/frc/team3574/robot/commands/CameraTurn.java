package org.usfirst.frc.team3574.robot.commands;

import org.usfirst.frc.team3574.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class CameraTurn extends Command {
	NetworkTable camera;
	int step = 0;
	Timer time =  new Timer();
	double setpointAngle;
	double setpointDepth;
	boolean isDone = false,
			isRunTwice = false;
	double error;


	static final double tickPerPixels = 120/100;

	public CameraTurn() {
		requires(Robot.drivetrain);
		camera = NetworkTable.getTable("camera");

		//		camera = NetworkTable.getTable("camera");
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		step = 0;
		//    	setpointAngle = ahrs.getYaw();
		//    	ahrs.reset();
		time.reset();
		time.start();
		isDone = false;
		isRunTwice = false;

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (step) {
		case 0:
			if(time.get() > 1) {
				Robot.drivetrain.resetYaw();
				Robot.drivetrain.resetEncoders();
				step++;

			}
		case 1:
			if (camera.getNumber("angle", 0.0) != 0.0) {
				setpointAngle = (camera.getNumber("angle", 0.0) - Robot.drivetrain.CENTER_OF_GOAL)/2;
				step++;				
			}
			if (time.get() > 3) {
				isDone = true;
			}
		case 2:
			// center on goal
			error = setpointAngle - Robot.drivetrain.getYaw();
			Robot.drivetrain.driveArcade(0.0, error / 10);
			if(Math.abs(error) < 1) {
				Robot.drivetrain.driveArcade(0.0, 0.0);
				step++;
			}
		case 3:
			//check distance
			if (camera.getNumber("depth", 0.0) != 0.0) {
				setpointDepth = (camera.getNumber("depth", 0.0) - Robot.drivetrain.HEIGHT_OF_GOAL) * tickPerPixels;
				step++;
			}
		case 4:
			// drive distance // we're done!
			error = setpointDepth - Robot.drivetrain.lowestEncReading();
			double direction;
			if (error < 0) {
				direction = -1;
			} else {
				direction = 1;
			}
			Robot.drivetrain.driveArcade(.35 * direction, 0.0);
			if(Math.abs(error) < 100) {
				Robot.drivetrain.driveArcade(0.0, 0.0);
				if (!isRunTwice){
					step=0;
					isRunTwice = true;
				} else {
					step++;
					isDone = true;

				}
			}


		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isDone;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
