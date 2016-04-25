package org.usfirst.frc.team3574.robot.commands;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ScalerWindString extends Command {
	Timer time = new Timer();
	boolean isFinished = false;

    public ScalerWindString() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time.reset();
    	time.start();
    	Robot.scaler.intakeString();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (time.get() > 4.0) {
    		Robot.scaler.stopScaling();
    		isFinished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
