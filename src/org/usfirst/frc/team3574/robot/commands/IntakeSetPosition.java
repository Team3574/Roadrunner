package org.usfirst.frc.team3574.robot.commands;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntakeSetPosition extends Command {
	private double postion;
   
	public IntakeSetPosition(double setPosition) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.postion = setPosition;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.intake.intakeSetPosition(SmartDashboard.getNumber("pos location", 0.0));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
    }
}
