package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.L;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.commands.CenterOnDegree;
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
import org.usfirst.frc.team3574.robot.subsystems.DriveTrain2;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChevalDeFrise extends Command {
	Timer time = new Timer();
	Command bigCommand;
	Command calibrate;
	Command positionUp;
    int step = 0;
    boolean isFinished = false;
	public ChevalDeFrise() {
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
    	new ShifterLowGear().start();
//    	new RampForAuto().start();
    	new ResetYaw().start();
    	new ResetPositionEnc().start();
		Robot.drivetrain.setRamprate(25);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	L.og(step);
    	L.ogSD("step", step);
    	switch(step){
    		case 0: 
    			if(time.get() > .1) {
    				step++;
    			}
    			break;
    		case 1: 
    			bigCommand = new DriveForDistanceBackward(-2.4 * DriveTrain2.TICKS_PER_FOOT, 0.8, 0.0);
    			bigCommand.start();
    			step++;
    			break;
    		case 2:
//    			L.og(bigCommand.isRunning());
    			if(!bigCommand.isRunning()) {
    				step++;
    			} 
    			break;
    		case 3:
    			calibrate = new Calibrate();
    			calibrate.start();
    			time.reset();
    			time.start();
    			step++;
    			break;
    		case 4:
    			if(Robot.intake.positionEncoderValue() > 1300) {
    				step = 5;
    			} //1290
    			if(time.get() > 2) {
        			bigCommand = new DriveForDistanceForward(0.1 * DriveTrain2.TICKS_PER_FOOT, -0.3, 0.0);
        			bigCommand.start();
        			step = 70;
    			}
    			break;
    		case 5:
    			bigCommand = new DriveForDistanceBackward(-600, 1.0, 0.0);
    			bigCommand.start();
    			time.reset();
    			step++;
    			break;
    		case 6:
    			if(!bigCommand.isRunning()) {
    				step++;
    			} 
    			break;
    		case 7:
    			positionUp = new PositionMotorSimple(0);
    			positionUp.start();
    			step++;
    			break;
    		case 8:
    			bigCommand = new DriveForDistanceBackward(-7.0 * DriveTrain2.TICKS_PER_FOOT, 0.5, 0.0);
    			bigCommand.start();
    			step++;
    			break;
    		case 9:
    			if(!bigCommand.isRunning()) {
    				step++;
    			} 
    			break;

    		case 10:
    			Robot.drivetrain.setRamprate(0);
    			bigCommand = new TurnTo180();
    			bigCommand.start();
    			step++;
    			break;
    		case 11:
    			if(!bigCommand.isRunning()) {
    				step++;
    			} 
    			break;    			
//    		case 12:
//    			bigCommand = new CenterOnDegree(180);
//    			bigCommand.start();
//    			step++;
//    			break;
//    		case 13:
//    			if(!bigCommand.isRunning()) {
//    				step++;
//    				isFinished = true;
//    			}
    		case 70:  //didn't go all the way down
    			if(Robot.intake.positionEncoderValue() > 1300) {
    				step = 5;
    			}
    			if(time.get() > 4) {
        			bigCommand = new DriveForDistanceForward(0.1 * DriveTrain2.TICKS_PER_FOOT, -0.3, 0.0);
        			bigCommand.start();
    				step++;
    			}
    			break;
    		case 71:  //didn't go all the way down
    			if(Robot.intake.positionEncoderValue() > 1300) {
    				step = 5;
    			}
    			if(time.get() > 6) {
    				// give up!
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
