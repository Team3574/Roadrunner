package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.commands.drivetrain.ConstantSpeedGoFAST;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistance;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShifterLowGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShifterHighGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveOverDefencesDistance extends CommandGroup {
    
    public  AutoDriveOverDefencesDistance() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new ShifterHighGear());
    	addSequential(new ResetYaw());
    	//TODO: must figure out if this is low or high gear
    	addSequential(new DriveForDistance(100, 0, 0));
    	addSequential(new NoDrive());

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
