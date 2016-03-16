package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.OI;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriverJoyDrive extends Command {
	double deadZone;
	double throttle;
	//	double scaledX, scaledY, scaledZ;
	OI oI = Robot.oi;


	public DriverJoyDrive() {
		requires(Robot.drivetrain);
		System.out.println("DriverJoy started");
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		//		throttle = 0.0;
		//		deadZone = 0.0;

		Robot.drivetrain.preDriveMode(oI.badStickXAxis(), oI.badStickYAxis(), oI.badStickZAxis());

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
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
