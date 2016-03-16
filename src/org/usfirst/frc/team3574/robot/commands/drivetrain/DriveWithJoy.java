package org.usfirst.frc.team3574.robot.commands.drivetrain;

import org.usfirst.frc.team3574.robot.OI;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.subsystems.DriveTrain2;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithJoy extends Command {
	double deadZone;
	double throttle;
	double scaledX, scaledY, scaledZ;
	OI oI = Robot.oi;
	final private double MAKE_GO_STRAIGHT = 0.9;
	
	
    public DriveWithJoy() {
    	requires(Robot.drivetrain);
        System.out.println("drive with joy started");
    	// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		throttle = 1.0;
		deadZone = 0.05;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

		if(Math.abs(oI.joystickLeft()) > MAKE_GO_STRAIGHT || (Math.abs(oI.joystickRight()) > MAKE_GO_STRAIGHT)){
			deadZone = .3;
		} else {
			deadZone = .05;
		}
		
		scaledX = Math.abs(-oI.joystickLeft()) < deadZone ? 0.0 : -oI.joystickLeft() * throttle;
		scaledY = Math.abs(-oI.joystickRight()) < deadZone ? 0.0 : -oI.joystickRight() * throttle; 
//		scaledX = joystickScale(scaledX);
//		scaledY = joystickScale(scaledY);				
		Robot.drivetrain.driveTank(oI.joystickLeft(), oI.joystickRight());

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
