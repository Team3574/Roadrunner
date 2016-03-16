package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.OI;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.subsystems.DriveTrain2;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArcadeDriveWithJoy extends Command {
	double deadZone;
	double throttle;
//	double scaledX, scaledY, scaledZ;
	OI oI = Robot.oi;
	
    public ArcadeDriveWithJoy() {
    	requires(Robot.drivetrain);
        System.out.println("arcade with joy started");
    	// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//		throttle = 0.0;
//		deadZone = 0.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double turn = oI.arcadeTurn();
    	double multiplier = 1.0;
    	if(turn < 0) {
    		multiplier = -1.0;
    	}
    	turn = Math.pow(Math.abs(turn), 2.2) * multiplier;
    	
		Robot.drivetrain.driveArcade(oI.arcadeThrottle(), turn);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
//		Robot.drivetrain.driveArcade(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
//		Robot.drivetrain.driveArcade(0.0, 0.0);
    }
}
