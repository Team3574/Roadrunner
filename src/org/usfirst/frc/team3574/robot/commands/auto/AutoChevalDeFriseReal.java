package org.usfirst.frc.team3574.robot.commands.auto;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ConstantSpeedGoSLOW;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistance;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShifterHighGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.WheelsHalfSpeed;
import org.usfirst.frc.team3574.robot.commands.drivetrain.driveOtherWay;
import org.usfirst.frc.team3574.robot.commands.intake.Calibrate;
import org.usfirst.frc.team3574.robot.commands.intake.PositionMotorSimple;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoChevalDeFriseReal extends CommandGroup {
    
    public  AutoChevalDeFriseReal() {
        boolean ready = false;
    	// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new ShifterHighGear());
    	//TODO: must figure out if this is low or high gear
//    	addParallel(new );
    	addSequential(new driveOtherWay());
    	addParallel(new Calibrate());
    	addSequential(new DriveForDistance(1 * Robot.drivetrain.TICKS_PER_FOOT, 0.3, 0.0));
    	
    	if(!ready && Robot.intake.isLowLimitSwitchClicked()) {
    		ready = true;
    	} else if(!ready) {
    		addSequential(new DriveForDistance(0.1 * Robot.drivetrain.TICKS_PER_FOOT, -0.1, 0.0));
    	}
    	if(ready) {
    	addParallel(new PositionMotorSimple(0));
    	addSequential(new NoDrive(), 1.0);
    	addSequential(new ConstantSpeedGoSLOW(), 2.0);
    	addSequential(new driveOtherWay());
    	
    	addSequential(new ResetYaw());
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
