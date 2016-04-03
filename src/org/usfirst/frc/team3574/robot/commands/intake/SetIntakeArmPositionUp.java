package org.usfirst.frc.team3574.robot.commands.intake;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetIntakeArmPositionUp extends Command {
	int 
		positionEnc; // :)
	/** int setpoint; */
	
    public SetIntakeArmPositionUp(/** int setpoint */) {
    	requires(Robot.lifterArm);
//    	this.setpoint = setpoint;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	positionEnc = Robot.intake.positionEncoderValue();
    	
    	
    	
    	 if(positionEnc > (20) && positionEnc < (200)) {
 			Robot.intake.setPositionArmsManually(-0.1 /** * positionEnc / 1000*/);
    	 } else if(positionEnc >= (200)) {// up
			Robot.intake.setPositionArmsManually( -0.55 /*-0.001 * position.getEncPosition()*/);
		} else {
			Robot.intake.setPositionArmsManually(0.0);
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
    }
}
