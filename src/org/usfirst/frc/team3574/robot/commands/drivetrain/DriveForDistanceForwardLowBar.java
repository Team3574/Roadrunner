package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForDistanceForwardLowBar extends Command {
	Timer time = new Timer();
	private double targetTicks;
	private double speed;
	private double rotate;
	
	public DriveForDistanceForwardLowBar(double ticks, double speed, double rotate) {
		requires(Robot.drivetrain);
		//2500 ticks to 1 foot
		this.targetTicks = ticks;
		this.speed = speed;
		this.rotate = rotate;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		time.reset();
		time.start();
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.driveArcade(speed, rotate);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.drivetrain.leftEncReading() < 200 && Robot.drivetrain.rightEncReading() < 200) {
			Robot.drivetrain.driveArcade(speed * 0.5, rotate * 0.5);
		} else if(Robot.drivetrain.leftEncReading() > (targetTicks - 500) || Robot.drivetrain.rightEncReading() > (targetTicks - 500)) {
			Robot.drivetrain.driveArcade(speed * 0.5, rotate * 0.5);	
		} else {
			Robot.drivetrain.driveArcade(speed, rotate);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(time.get() > 0.2) {
			if(Robot.drivetrain.leftEncReading() > targetTicks || Robot.drivetrain.rightEncReading() > targetTicks) {
				Robot.drivetrain.driveArcade(0, 0);
				return true;
			} else {
				return false;
			}
			
		}
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
