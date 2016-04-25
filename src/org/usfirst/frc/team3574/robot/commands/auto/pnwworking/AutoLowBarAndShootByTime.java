package org.usfirst.frc.team3574.robot.commands.auto.pnwworking;

import org.usfirst.frc.team3574.robot.commands.drivetrain.ConstantSpeedLowBarShoot;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ConstantSpeedGoSLOW;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceForward;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateClockwiseByX;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShifterHighGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.TurnByTicks;
import org.usfirst.frc.team3574.robot.commands.drivetrain.driveOtherWay;
import org.usfirst.frc.team3574.robot.commands.intake.Calibrate;
import org.usfirst.frc.team3574.robot.commands.intake.PositionMotorSimple;
import org.usfirst.frc.team3574.robot.commands.intake.SetIntakeArmPositionUp;
import org.usfirst.frc.team3574.robot.commands.shooter.HighShootKick;
import org.usfirst.frc.team3574.robot.commands.shooter.HighShootSpinUp;
import org.usfirst.frc.team3574.robot.commands.shooter.HoodReadyAuto;
//import org.usfirst.frc.team3574.robot.commands.shooter.ShooterSpinUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLowBarAndShootByTime extends CommandGroup {
    
    public  AutoLowBarAndShootByTime() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new ResetYaw());
    	
    	/**
    	 * Go under Low bar auto
    	 */
    	addSequential(new ShifterHighGear());
    	addParallel(new Calibrate());
    	addSequential(new ConstantSpeedLowBarShoot(), 3.2);
    	addSequential(new SetIntakeArmPositionUp(), 1);
//    	addSequential(new PositionMotorSimple(0), 2);
   //TODO:  WaitForOneSeconAndThenDropTheArms
    	/**
    	 * end of low bar auto
    	 */
    	addParallel(new HoodReadyAuto());
//    	addSequential(new driveOtherWay());
//    	addSequential(new ConstantSpeedGoSLOW(), 0.5);
//    	addSequential(new driveOtherWay());
    	
    	addSequential(new RotateToADegreeClockwiseOnly(55));
    	addParallel(new HighShootSpinUp());
    	addSequential(new NoDrive(), 1);
    	addSequential(new ConstantSpeedGoSLOW(), 1.5);
    	
    	addSequential(new NoDrive(), 1);
    	addSequential(new HighShootKick());
    	
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
