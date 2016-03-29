package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.commands.drivetrain.ConstantSpeedGoFAST;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShifterHighGear;
import org.usfirst.frc.team3574.robot.commands.intake.PositionMotorSimple;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveOverFlatDefencesByTime extends CommandGroup {
    
    public  AutoDriveOverFlatDefencesByTime() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new ShifterHighGear());
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
    	addParallel(new PositionMotorSimple(0));
    	addSequential(new ConstantSpeedGoFAST(), 1.25);
    	addSequential(new NoDrive());
    	addSequential(new ResetYaw());
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
