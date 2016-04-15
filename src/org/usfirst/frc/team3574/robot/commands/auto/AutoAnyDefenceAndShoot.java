package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoAnyDefenceAndShoot extends CommandGroup {

	public  AutoAnyDefenceAndShoot() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		//      addSequential(new Command2());
		// these will run in order.
		if(Robot.defenceInPosition == 0) { //rock wall, moat, rough terain
			addSequential(new AutoDriveOverDefencesByTimeFAST());    		
		}
		if(Robot.defenceInPosition == 4) { //low bar
			addSequential(new AutoDriveOverDefencesByTimeSLOW());    		
		}
		if(Robot.defenceInPosition == 5) {
			addSequential(new AutoPortculis());
		}
		
		
		
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
