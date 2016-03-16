package org.usfirst.frc.team3574.robot.commands.shooter;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowShoot extends Command {
	Timer time = new Timer();

	public LowShoot() {
		requires(Robot.intake);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("Starting lowShoot");
		Robot.intake.lowShoot();
		time.reset();
		time.start();
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(time.get() > .2) {
			Robot.intake.stopIntake();
			return true;
		} else {
			return false;
		}
}

// Called once after isFinished returns true
protected void end() {
	System.out.println("lowShooting Proporly");
}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
protected void interrupted() {
}
}
