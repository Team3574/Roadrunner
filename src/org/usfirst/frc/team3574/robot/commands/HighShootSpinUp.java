package org.usfirst.frc.team3574.robot.commands;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HighShootSpinUp extends Command {
	Timer time = new Timer();
	boolean ssu = Robot.shooter.shooterSpunUp;
	
	
	public HighShootSpinUp() {
		requires(Robot.shooter);

		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		time.reset();
		time.start();
		
		if(!ssu) {
		Robot.shooter.shooter(-0.9);
		ssu = true;
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
//		if(time.get() > 10) {
//			Robot.shooter.shooter(0);
			return true;
//		} else {
//			return false;
//		}
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
