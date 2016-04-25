package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.commands.BrakeModeOff;
import org.usfirst.frc.team3574.robot.commands.BrakeModeOn;
import org.usfirst.frc.team3574.robot.commands.RampNoRamprate;
import org.usfirst.frc.team3574.robot.commands.RampRateOn;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceForward;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeCounterClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShifterHighGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShifterLowGear;
import org.usfirst.frc.team3574.robot.commands.intake.Calibrate;
import org.usfirst.frc.team3574.robot.commands.intake.SetIntakeArmPositionUp;
import org.usfirst.frc.team3574.robot.commands.shooter.HighShootKick;
import org.usfirst.frc.team3574.robot.commands.shooter.HighShootSpinUp;
import org.usfirst.frc.team3574.robot.commands.shooter.HoodReadyAuto;
import org.usfirst.frc.team3574.robot.subsystems.DriveTrain2;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PositionToShoot3Curve extends CommandGroup {
    
    public  PositionToShoot3Curve() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	/**
    	 * WE SHOULD HAVE ALREADY CROSSED THE OUTERWORKS
    	 */
    	
       	addSequential(new RampRateOn());
    	addSequential(new BrakeModeOn());
    	addSequential(new ShifterLowGear());
    	
    	addParallel(new SetIntakeArmPositionUp(), 1);
    	addParallel(new HoodReadyAuto());

    	addSequential(new DriveForDistanceForward(6.5 * DriveTrain2.TICKS_PER_FOOT, -0.7, -0.125));
    	
    	addSequential(new NoDrive(), 0.2);
    	
    	addSequential(new RotateToADegreeCounterClockwiseOnly(0)); //low bar/position 1 is 59
    	addParallel(new HighShootSpinUp());
    	addSequential(new NoDrive(), 0.5);
    	
    	addSequential(new HighShootKick());

    	addSequential(new BrakeModeOff());
    	addSequential(new RampNoRamprate());

    	    	
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
