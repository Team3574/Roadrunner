package org.usfirst.frc.team3574.robot.commands.shooter;

import org.usfirst.frc.team3574.robot.L;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HighShootKick extends Command {
	Timer time = new Timer();
	boolean ssu = Robot.shooter.shooterSpunUp;

	public HighShootKick() {
		//    	requires(Robot.intake);
		//    	requires(Robot.shooter);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		time.reset();
		time.start();

		//    	if(ssu) {


		//    	Robot.intake.rollerAndWheelsIn();
		L.og("init HSK");
		Robot.shooter.shooter(-1);
		//    	}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(time.get() > 2) {
			L.og("exec post 2 secs HSK");
			Robot.intake.feedShooter();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(time.get() > 3) {
			L.og("exec post 3 secs HSK");
			Robot.intake.stopIntake();
			Robot.shooter.shooter(0);
			//       	 ssu = false;
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
