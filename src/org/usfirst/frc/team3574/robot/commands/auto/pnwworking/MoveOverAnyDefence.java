package org.usfirst.frc.team3574.robot.commands.auto.pnwworking;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveOverAnyDefence extends Command {
	Timer time = new Timer();
	boolean isDone = false;
	boolean setupOnce = false;
	
	public MoveOverAnyDefence() {
		requires(Robot.drivetrain);
		requires(Robot.lifterArm);
		requires(Robot.intake);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		isDone = false;
		setupOnce = false;
		time.reset();
		time.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.defenceInPosition == 0) {
			// setup
			if(!setupOnce) {
				Robot.drivetrain.shifterHightGear();
				Robot.intake.positionMotorSimple(0);
				setupOnce = true;
			}
			
			//driving
			Robot.drivetrain.driveArcade(-0.95, 0.0);
			if(time.get() > 1.75) {
				Robot.drivetrain.driveArcade(0.0, 0.0);	
				System.out.println("Rock Wall Auto");
				isDone = true;
			}
		} else if(Robot.defenceInPosition == 3) {
			// setup
			if(!setupOnce) {
				Robot.drivetrain.shifterHightGear();
				Robot.intake.positionMotorSimple(0);
				setupOnce = true;
			}
			
			//driving
			Robot.drivetrain.driveArcade(-1, 0.0);
			if(time.get() > 1.75) {
				Robot.drivetrain.driveArcade(0.0, 0.0);	
				System.out.println("Ramparts Auto");
				isDone = true;
			}
		} else if(Robot.defenceInPosition == 4) {
			// setup
			if(!setupOnce) {
				Robot.drivetrain.shifterHightGear();
				setupOnce = true;
			}
			// calibrate
			if (!Robot.intake.isLowLimitSwitchClicked()){
				Robot.intake.lowerInteakeArms();
			} else {
//				//alpha
				Robot.intake.calibratePositionToCurrentPos(1607 -85);
				Robot.intake.positionMotorSimple(1607 - 85);
			}
			
			//driving
			Robot.drivetrain.driveArcade(-0.3, 0.0);
			if(time.get() > 4) {
				Robot.drivetrain.driveArcade(0.0, 0.0);	
				System.out.println("Low Bar Auto");
				isDone = true;
			}
		} else if(Robot.defenceInPosition == 5) {
			// setup
			if(!setupOnce) {
				Robot.drivetrain.shifterHightGear();
				Robot.drivetrain.driveOpositeDirection *= -1;
				setupOnce = true;
			}
			
			// calibrate
			if (!Robot.intake.isLowLimitSwitchClicked()){
				Robot.intake.lowerInteakeArms();
			} else {
				Robot.intake.calibratePositionToCurrentPos(1607 -85);
				Robot.intake.positionMotorSimple(1607 - 85);
			}
			
			//driving
			Robot.drivetrain.driveArcade(-0.5, 0.0);
			if(time.get() > 3) {
				Robot.drivetrain.driveArcade(0.0, 0.0);
				Robot.drivetrain.driveOpositeDirection *= -1;
				isDone = true;
			}
		} else if(Robot.defenceInPosition == 6) {
			// setup
			if(!setupOnce) {
			Robot.drivetrain.shifterHightGear();
			Robot.drivetrain.driveOpositeDirection *= -1;
			setupOnce = true;
			}
			
			// calibrate
			if(time.get() < 1) {
				if (!Robot.intake.isLowLimitSwitchClicked()){
					Robot.intake.lowerInteakeArms();
				} else {
					Robot.intake.calibratePositionToCurrentPos(1607 -85);
					Robot.intake.positionMotorSimple(1607 - 85);
				}
			}
			
			// driving
			Robot.drivetrain.driveArcade(-0.3, 0.0);
			if(time.get() > 4) {
				Robot.drivetrain.driveArcade(0.0, 0.0);
				isDone = true;
			} else if(time.get() > 2) {
				Robot.drivetrain.driveArcade(-0.3, 0.0);
				Robot.intake.positionMotorSimple(0);
				
			} else if(time.get() > 1) {
				Robot.drivetrain.driveArcade(0.0, 0.0);
			}
		} else if(Robot.defenceInPosition == 7) {
		
			new NoDrive();
			System.out.println("No Auto");
			isDone = false;
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
