package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.L;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnTo180 extends Command {
	double yaw;
	int reverse;
	int targetYaw;
	
	
	
	public TurnTo180() {
		requires(Robot.drivetrain);
//		this.targetYaw = positiveIsClockwise;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		L.ogCmdInit(this);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
//		reverse = 1;
	
//		if(targetYaw < 0) {
//			reverse *= -1;
//		}
		Robot.drivetrain.driveArcade(0.0, -0.7);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		yaw = (Robot.drivetrain.getYaw() + 360) % 360;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		
		if(/*yaw >= 175 || yaw <= -175*/	yaw >= 170 && yaw <= 200) {
			Robot.drivetrain.driveArcade(0.0, 0.0);
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		L.ogCmdEnd(this);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
