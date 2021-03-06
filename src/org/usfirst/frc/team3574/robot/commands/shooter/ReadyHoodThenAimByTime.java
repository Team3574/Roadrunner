package org.usfirst.frc.team3574.robot.commands.shooter;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReadyHoodThenAimByTime extends Command {
	Timer time = new Timer();
	Boolean otherWay = false,
			isDone = false;
	
    public ReadyHoodThenAimByTime() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	otherWay = false;
    	isDone = false;
    	
    	time.reset();
    	time.start();
    	
    	Robot.shooter.hood(0.8);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(otherWay) {
			if(time.get() < 1.3) {
				Robot.shooter.hood(-0.2);
			} else {
				Robot.shooter.hood(0.0);
				isDone = true;
			}
		} else if(Robot.shooter.hoodShootLmtSwitchClicked() || time.get() > 2.0) {
			otherWay = true;
	    	time.reset();
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
