package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.L;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Rotate1degreeCounterClockwise extends Command {
	
    public Rotate1degreeCounterClockwise() {
//    	requires(Robot.drivetrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.driveTank(0.4, -0.4);
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	L.og(this.timeSinceInitialized());    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(this.timeSinceInitialized() > 0.200) {
    		Robot.drivetrain.driveTank(0.0, 0.0);
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
