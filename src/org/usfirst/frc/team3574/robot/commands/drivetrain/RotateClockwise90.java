package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateClockwise90 extends Command {
	double targetYaw;
	double yaw;
	
	public RotateClockwise90() {
		requires(Robot.drivetrain);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		targetYaw = (Robot.drivetrain.getYaw() -  90);
		Robot.drivetrain.driveArcade(0.0, 0.2);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		yaw = Robot.drivetrain.getYaw();
//		System.out.println(targetYaw);
//		System.out.println(yaw);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(yaw >= (targetYaw - 1) && yaw <= (targetYaw + 1)) {
			Robot.drivetrain.driveArcade(0.0, 0.0);
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
