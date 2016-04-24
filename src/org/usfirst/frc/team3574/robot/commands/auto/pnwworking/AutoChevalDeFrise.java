package org.usfirst.frc.team3574.robot.commands.auto.pnwworking;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ConstantSpeedGoSLOW;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceBackward;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceForward;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShifterHighGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShifterLowGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.WheelsHalfSpeed;
import org.usfirst.frc.team3574.robot.commands.drivetrain.driveOtherWay;
import org.usfirst.frc.team3574.robot.commands.intake.Calibrate;
import org.usfirst.frc.team3574.robot.commands.intake.PositionMotorSimple;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoChevalDeFrise extends CommandGroup {
    
    public  AutoChevalDeFrise() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new ShifterLowGear());
    	//TODO: must figure out if this is low or high gear
//    	addParallel(new );
    	addSequential(new DriveForDistanceBackward(-3.0 * Robot.drivetrain.TICKS_PER_FOOT, 0.75, 0.0));
//    	addSequential(new Calibrate(), 2);
//    	addSequential(new ChevalDeFrise());
    	addSequential(new DriveForDistanceBackward(-3.5 * Robot.drivetrain.TICKS_PER_FOOT, 0.8, 0.0));
    	addParallel(new PositionMotorSimple(0));
    	addSequential(new NoDrive(), 1.0);
    	addSequential(new ConstantSpeedGoSLOW(), 2.0);
    	
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
