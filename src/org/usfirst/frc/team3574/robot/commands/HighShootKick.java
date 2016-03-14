package org.usfirst.frc.team3574.robot.commands;

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
    	requires(Robot.intake);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	time.reset();
    	time.start();
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	if(shooterSpunUp) {
    	Robot.intake.feedShooter(); 
//    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
     if(time.get() > .5) {
    	 Robot.intake.stopIntake();
    	 Robot.shooter.shooter(0);
    	 ssu = false;
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
