package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.L;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.commands.RampRateOn;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ConstantSpeedGoSLOW;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceBackward;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceForward;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShifterLowGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.TurnTo180;
import org.usfirst.frc.team3574.robot.commands.intake.Calibrate;
import org.usfirst.frc.team3574.robot.commands.intake.PositionMotorSimple;
import org.usfirst.frc.team3574.robot.commands.intake.ResetPositionEnc;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class AutoAnyDefenceAndShoot extends Command {
	Timer time = new Timer();
	Command theCommand;
    int step = 0;
    boolean isFinished = false;

    public AutoAnyDefenceAndShoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	L.ogCmdInit(this);
    	step = 0;
    	isFinished = false;
    	time.reset();
    	time.start();
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	L.og("Uberstep: " + step);
    	switch(step){
    		case 0: 
    			if(Robot.defenceInPosition == 0) { //rock wall, moat, rough terain
    				theCommand = (new AutoDriveOverDefencesByTimeFAST());    		
    			}
    			if(Robot.defenceInPosition == 4 && Robot.positionOnField == 1) { //low bar
    				theCommand = (new AutoLowBarAndShootByTicks());    		
    			} else if(Robot.defenceInPosition == 4) {
    				theCommand = (new AutoDriveOverDefencesByTimeSLOW());    			
    			}
    			if(Robot.defenceInPosition == 5) {
    				theCommand = (new AutoPortculisByTicks());
    			}
    			if(Robot.defenceInPosition == 6) {
    				theCommand = (new ChevalDeFrise());
    			}
    			if(Robot.defenceInPosition == 7) {
    				theCommand = (new AutoTouchDefences());
    			}
    			if(Robot.defenceInPosition == 8) {
    				theCommand = (new AutoDoNothing());
    			}
    			theCommand.start();
    			step++;
    			break;
    		case 1:
//    			L.og(theCommand.isRunning());
    			if(!theCommand.isRunning()) {
    				step++;
    			} 
    			break;
    		case 2:
    			if(Robot.defenceInPosition != 7 && Robot.defenceInPosition != 8) {
    				L.og(Robot.positionOnField);
    				if(Robot.positionOnField == 2) {
    					theCommand = (new PositionToShoot2());	
    				}
    				if(Robot.positionOnField == 3) {
    					theCommand = (new PositionToShoot3()); 
    				}
    				if(Robot.positionOnField == 4) {
    					theCommand = (new PositionToShoot4());    		
    				}
    				if(Robot.positionOnField == 5) {
    					theCommand = (new PositionToShoot5());    		
    				}
        			theCommand.start();
    			}
    			step++;
    			break;
    		case 3:
    			if(!theCommand.isRunning()) {
    				step++;
    				isFinished = true;
    			} 
    			break;
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
