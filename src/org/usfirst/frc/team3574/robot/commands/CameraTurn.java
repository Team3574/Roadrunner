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

	public CameraTurn() {
		requires(Robot.drivetrain);
		camera = NetworkTable.getTable("camera");
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		//    	setpointAngle = ahrs.getYaw();
		Robot.drivetrain.driveArcade(0, 0.2);
		//    	ahrs.reset();
		time.reset();
		time.start();
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double roll = Robot.drivetrain.getRoll();
		double pitch = Robot.drivetrain.getPitch();
		double yaw = Robot.drivetrain.getYaw();
		//    	System.out.println(camera.getString("depth", "nothing"));
		//    	System.out.println("ANGLE" + ahrs.getAngle());
//		System.out.println(ahrs.getYaw());
		System.out.println("ROLL" + roll);
		System.out.println("PITCH" + pitch);
		System.out.println("YAWYAW" + yaw);
		
		if(time.get() > 1) {
			setpointAngle = roll;
			if(setpointAngle == roll && (setpointAngle - 50) < roll && (setpointAngle + 50) > roll) {
				System.out.println("hi bob");
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
