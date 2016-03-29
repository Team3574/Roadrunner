package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.commands.drivetrain.ConstantSpeedGoFAST;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ConstantSpeedGoSLOW;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShifterLowGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShifterHighGear;
import org.usfirst.frc.team3574.robot.commands.intake.PositionMotorSimple;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveOverDefencesByTimeSLOW extends CommandGroup {
    
    public  AutoDriveOverDefencesByTimeSLOW() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new ShifterHighGear());
    	//TODO: must figure out if this is low or high gear
//    	addParallel(new );
    	addParallel(new PositionMotorSimple(1200));
    	addSequential(new ConstantSpeedGoSLOW(), 4.0);
   //TODO:  WaitForOneSeconAndThenDropTheArms
    	addSequential(new NoDrive());
    	addSequential(new ResetYaw());
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
