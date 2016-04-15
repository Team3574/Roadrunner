package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NotUsedMovePositionToShoot extends Command {
	Timer time = new Timer();
	boolean isDone = false;
	
	public NotUsedMovePositionToShoot() {
		requires(Robot.drivetrain);
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
		if(Robot.positionOnField == 2) {		//second position
			if(time.get() < 3) {
				Robot.drivetrain.driveArcade(-0.3, 0.0);
			} else if(time.get() < 5) {
				Robot.drivetrain.driveArcade(0.0, 0.3);
			} else {
				Robot.drivetrain.driveArcade(0.0, 0.0);
				isDone = true;
			}

		} else if(Robot.positionOnField == 3) {	//third position
			if(time.get() < 2) {
				Robot.drivetrain.driveArcade(0.0, 0.3);
			} else if(time.get() < 3) {
				Robot.drivetrain.driveArcade(-0.3, 0.0);
			} else if(time.get() < 5) {
				Robot.drivetrain.driveArcade(0.0, -0.3);
			} else {
				Robot.drivetrain.driveArcade(0.0, 0.0);
				isDone = true;
			}
			
		} else if(Robot.positionOnField == 4) { //fourth position
			if(time.get() < 2) {
				Robot.drivetrain.driveArcade(0.0, -0.3);
			} else if(time.get() < 3) {
				Robot.drivetrain.driveArcade(-0.3, 0.0);
			} else if(time.get() < 5) {
				Robot.drivetrain.driveArcade(0.0, 0.3);
			} else {
				Robot.drivetrain.driveArcade(0.0, 0.0);
				isDone = true;
			}
			
		} else if(Robot.positionOnField == 5) {//Fifth position
			if(time.get() < 3) {
				Robot.drivetrain.driveArcade(0.3, 0.0);
			} else if(time.get() < 5) {
				Robot.drivetrain.driveArcade(0.0, -0.3);
			} else {
				Robot.drivetrain.driveArcade(0.0, 0.0);
				isDone = true;
			}
			
		} else {
			isDone = false;
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
