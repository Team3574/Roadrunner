package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.commands.BrakeModeOff;
import org.usfirst.frc.team3574.robot.commands.BrakeModeOn;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistance;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeCounterClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShifterHighGear;
import org.usfirst.frc.team3574.robot.commands.intake.SetIntakeArmPositionUp;
import org.usfirst.frc.team3574.robot.commands.shooter.HighShootKick;
import org.usfirst.frc.team3574.robot.commands.shooter.HighShootSpinUp;
import org.usfirst.frc.team3574.robot.commands.shooter.HoodReadyAuto;
import org.usfirst.frc.team3574.robot.subsystems.DriveTrain2;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PositionToShoot4 extends CommandGroup {
    
    public  PositionToShoot4() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new BrakeModeOn());
    	addSequential(new ShifterHighGear());
    	addSequential(new DriveForDistance(8.0 * DriveTrain2.TICKS_PER_FOOT, -0.4, 0.0));
    	addSequential(new SetIntakeArmPositionUp(), 1);
    	
    	addParallel(new HoodReadyAuto());
    	
    	addSequential(new RotateToADegreeCounterClockwiseOnly(-30)); //low bar/position 1 is 59
    	addParallel(new HighShootSpinUp());
    	addSequential(new NoDrive(), 1);
    	
    	addSequential(new NoDrive(), 1);
    	addSequential(new HighShootKick());
    	addSequential(new BrakeModeOff());
        
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
