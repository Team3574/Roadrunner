package org.usfirst.frc.team3574.robot.commands;

import org.usfirst.frc.team3574.robot.commands.intake.Calibrate;
import org.usfirst.frc.team3574.robot.commands.intake.PositionMotorSimple;
import org.usfirst.frc.team3574.robot.commands.intake.SetIntakeArmPositionDown;
import org.usfirst.frc.team3574.robot.commands.shooter.HoodReady;
import org.usfirst.frc.team3574.robot.commands.shooter.HoodStow;
import org.usfirst.frc.team3574.robot.commands.shooter.HoodStowAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PrepLowBar extends CommandGroup {
    
    public  PrepLowBar() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addParallel(new HoodStowAuto());
    	addSequential(new Calibrate());
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
