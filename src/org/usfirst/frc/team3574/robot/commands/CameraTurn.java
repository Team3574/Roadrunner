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
	double setpointAngle;
	Timer time =  new Timer();
	

	public CameraTurn(double setAngle) {
		requires(Robot.drivetrain);
		setpointAngle = setAngle;
//		camera = NetworkTable.getTable("camera");
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		//    	setpointAngle = ahrs.getYaw();
//		Robot.drivetrain.driveArcade(0, 0.2);
		//    	ahrs.reset();
		time.reset();
		time.start();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		camera = NetworkTable.getTable("camera");
		setpointAngle = camera.getNumber("angle", 0.0);
		double yaw = Robot.drivetrain.getYaw();
		//    	System.out.println(camera.getString("depth", "nothing"));
		//		System.out.println(ahrs.getYaw());
		System.out.println(yaw);
		System.out.println("X:  " + camera.getNumber("angle", 0.0));
//		System.out.println("Y: " + camera.getNumber("depth", 0.0));
	
		if(time.get() > 1) {
//			setpointAngle = yaw;
			if((setpointAngle - 1) < yaw && (setpointAngle + 1) > yaw) {
				System.out.println("stop wheels");
				Robot.drivetrain.driveArcade(0, 0);
			}



		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
