package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForDistance extends Command {
	private int targetTicks;
	public DriveForDistance(int ticks) {
		requires(Robot.drivetrain);
		this.targetTicks = ticks;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.driveArcade(0.8, 0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(Robot.drivetrain.leftEncReading() > targetTicks || Robot.drivetrain.rightEncReading() > targetTicks) {
			Robot.drivetrain.driveArcade(0, 0);
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
