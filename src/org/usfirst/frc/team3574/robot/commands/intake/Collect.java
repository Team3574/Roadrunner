package org.usfirst.frc.team3574.robot.commands.intake;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Collect extends Command {

    public Collect() {
    	
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.rollerAndWheelsIn();
    	System.out.println("Starting Collect");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.intake.intakeFull()){
    		Robot.intake.stopIntake();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.stopIntake();
    	System.out.println("Collecting proporly");
    	
    }
}
