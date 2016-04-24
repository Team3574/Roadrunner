package org.usfirst.frc.team3574.robot.commands.auto.pnwworking;

import org.usfirst.frc.team3574.robot.L;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.commands.RampRateOn;
import org.usfirst.frc.team3574.robot.commands.RampNoRamprate;
import org.usfirst.frc.team3574.robot.commands.auto.AutoDoNothing;
import org.usfirst.frc.team3574.robot.commands.auto.AutoDriveOverDefencesByTimeFAST;
import org.usfirst.frc.team3574.robot.commands.auto.AutoDriveOverDefencesByTimeSLOW;
import org.usfirst.frc.team3574.robot.commands.auto.AutoLowBarAndShootByTicks;
import org.usfirst.frc.team3574.robot.commands.auto.AutoPortculisByTicks;
import org.usfirst.frc.team3574.robot.commands.auto.AutoTouchDefences;
import org.usfirst.frc.team3574.robot.commands.auto.ChevalDeFrise;
import org.usfirst.frc.team3574.robot.commands.auto.PositionToShoot2;
import org.usfirst.frc.team3574.robot.commands.auto.PositionToShoot3;
import org.usfirst.frc.team3574.robot.commands.auto.PositionToShoot4;
import org.usfirst.frc.team3574.robot.commands.auto.PositionToShoot5;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoAnyDefenceAndShootNotWorking extends CommandGroup {

	public  AutoAnyDefenceAndShootNotWorking() {
		L.og(Robot.defenceInPosition);
		// Add Commands here:
		// e.g. addSequential(new Command1());
		//      addSequential(new Command2());
		// these will run in order.
//		addSequential(new RampForAuto());
		if(Robot.defenceInPosition == 0) { //rock wall, moat, rough terain
			addSequential(new AutoDriveOverDefencesByTimeFAST());    		
		}
		if(Robot.defenceInPosition == 4 && Robot.positionOnField == 1) { //low bar
			addSequential(new AutoLowBarAndShootByTicks());    		
		} else if(Robot.defenceInPosition == 4) {
			addSequential(new AutoDriveOverDefencesByTimeSLOW());    			
		}
		if(Robot.defenceInPosition == 5) {
			addSequential(new AutoPortculisByTicks());
		}
		if(Robot.defenceInPosition == 6) {
			addSequential(new ChevalDeFrise());
		}
		if(Robot.defenceInPosition == 7) {
			addSequential(new AutoTouchDefences());
		}
		if(Robot.defenceInPosition == 8) {
			addSequential(new AutoDoNothing());
		}
//		if(Robot.defenceInPosition == 10) {
//			addSequential(new AutoLowBarAndShootByTicks());
//		}


		if(Robot.defenceInPosition != 7 && Robot.defenceInPosition != 8) {
			L.og(Robot.positionOnField);
			if(Robot.positionOnField == 2) {
				addSequential(new PositionToShoot2());	
			}
			if(Robot.positionOnField == 3) {
				addSequential(new PositionToShoot3());    		
			}
			if(Robot.positionOnField == 4) {
				addSequential(new PositionToShoot4());    		
			}
			if(Robot.positionOnField == 5) {
				addSequential(new PositionToShoot5());    		
			}
		}
		
//		addSequential(new RampForTeleop());

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		//      addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}
