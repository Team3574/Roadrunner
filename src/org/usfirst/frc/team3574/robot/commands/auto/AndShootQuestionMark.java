package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AndShootQuestionMark extends Command {
	Timer time = new Timer();
	boolean isDone = false;
	boolean okayToFinish = false;

	public AndShootQuestionMark() {
		requires(Robot.shooter);
		requires(Robot.intake);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		isDone = false;

		time.reset();
		time.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.positionOnField == 0) {
			Robot.shooter.shooter(0.0);
			Robot.shooter.hood(-0.8);

		} else if(Robot.positionOnField >= 2) {
			if(time.get() > 5) {
				Robot.shooter.shooter(0.0);
				Robot.intake.stopIntake();
			} else if(time.get() > 4) {
				Robot.intake.feedShooter();
			} else if(time.get() > 2) {
				Robot.shooter.shooter(-1);
				System.out.println("-1 speed");
//				okayToFinish = true;
			} else {
				Robot.shooter.hood(0.8);
				Robot.shooter.shooter(-0.55);
				System.out.println("-0.55 speed");
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
