package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateClockwiseByX extends Command {
	double targetYaw;
	double yaw;
	int reverse;
	int amountToTurn;
	boolean isDone = false;


	public RotateClockwiseByX(int amountToTurnPositiveIsClockwise) {
		requires(Robot.drivetrain);
		this.amountToTurn = amountToTurnPositiveIsClockwise;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		reverse = 1;
		isDone = false;
		Robot.drivetrain.resetYaw();
		targetYaw = (Robot.drivetrain.getYaw() + amountToTurn);

		if(amountToTurn < 0) {
			reverse *= -1;
		}
		Robot.drivetrain.driveArcade(0.0, -0.5 * reverse);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		yaw = Robot.drivetrain.getYaw();
		//		System.out.println(targetYaw);
		//		System.out.println(yaw);
		if(reverse == 1) {

			if(yaw >= targetYaw) {
				Robot.drivetrain.driveArcade(0.0, 0.0);
				isDone = true;
			} 
		} else {

			if(yaw <= targetYaw) {
				Robot.drivetrain.driveArcade(0.0, 0.0);
				isDone = true;
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
