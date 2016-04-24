package org.usfirst.frc.team3574.robot.commands.auto.pnwworking;

import org.usfirst.frc.team3574.robot.commands.drivetrain.ConstantSpeedGoSLOW;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShifterHighGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.TurnTo180;
import org.usfirst.frc.team3574.robot.commands.drivetrain.WheelsHalfSpeed;
import org.usfirst.frc.team3574.robot.commands.drivetrain.driveOtherWay;
import org.usfirst.frc.team3574.robot.commands.intake.Calibrate;
import org.usfirst.frc.team3574.robot.commands.intake.PositionMotorSimple;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPortculisByTime extends CommandGroup {
    
    public  AutoPortculisByTime() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new ShifterHighGear());
    	//TODO: must figure out if this is low or high gear
//    	addParallel(new );
    	addSequential(new driveOtherWay());
    	addSequential(new Calibrate(), 2);
    	addSequential(new WheelsHalfSpeed(), 3.0);
    	addSequential(new driveOtherWay());
    	addSequential(new RotateToADegreeClockwiseOnly(178));
    	
    	addSequential(new ResetYaw());
    	addSequential(new TurnTo180());
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
