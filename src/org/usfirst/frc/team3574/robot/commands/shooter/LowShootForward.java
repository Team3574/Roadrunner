package org.usfirst.frc.team3574.robot.commands.shooter;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowShootForward extends Command {
	Timer time = new Timer();
	boolean isDone = false;
	
    public LowShootForward() {
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
    	
    	Robot.shooter.shooter(-0.55);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(time.get() > 3) {
    		Robot.shooter.shooter(0.0);
        	Robot.intake.stopIntake();
        	isDone = true;
    	} else if(time.get() > 2) {
    		Robot.intake.feedShooter();
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
