package org.usfirst.frc.team3574.robot.commands;

import org.usfirst.frc.team3574.robot.OI;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakePositionDown extends Command {

    public IntakePositionDown() {
//    	requires(Robot.intake);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.intakePositionSpeed(11000);
    	
    	System.out.println("Moving Up");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.intakePositionSpeed(-Robot.oi.badStickSlider());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.intakePositionSpeed(0.0);
    	System.out.println("Stopiling MoverDowner");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.intakePositionSpeed(0.0);
    }
}
