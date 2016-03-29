package org.usfirst.frc.team3574.robot.commands.intake;

import org.usfirst.frc.team3574.robot.L;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */


public class Calibrate extends Command {
	boolean isDone;

	public Calibrate() {
		requires(Robot.lifterArm);
		//	    	SmartDashboard.putData(this);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		isDone = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		System.out.println(Robot.intake.isLowLimitSwitchClicked());;
		if (!Robot.intake.isLowLimitSwitchClicked()){
			Robot.intake.lowerInteakeArms();
		} else {
			Robot.intake.calibratePositionToCurrentPos(1607 -85);
			Robot.intake.positionMotorSimple(1607 - 85);
//			Robot.intake.calibratePositionToCurrentPos(1510);
//			Robot.intake.positionMotorSimple(1510);
			isDone = true;
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {	
		return isDone;
	}

	// Called once after isFinished returns true
	protected void end() {
		// TODO
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}