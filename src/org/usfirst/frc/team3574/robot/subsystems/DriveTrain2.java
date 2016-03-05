package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.commands.DriveWithJoy;
import org.usfirst.frc.team3574.robot.commands.NoDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class DriveTrain2 extends Subsystem {
	CANTalon leftMotor = RobotMap.motorDriveLeft1;
	CANTalon leftFollower = RobotMap.motorDriveLeft2;
	CANTalon rightMotor = RobotMap.motorDriveRight1;
	CANTalon rightFollower = RobotMap.motorDriveRight2;


	public DriveTrain2() {
		leftMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		leftFollower.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftFollower.set(1);

		rightMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightFollower.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightFollower.set(3);

//		rightMotor.reverseOutput(true);

		leftMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rightMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

		LiveWindow.addActuator("driveTrain","driveMotorLeft1", leftMotor);
		LiveWindow.addActuator("driveTrain","driveMotorLeft2", leftFollower);
		LiveWindow.addActuator("driveTrain","driveMotorRight1", rightMotor);
		LiveWindow.addActuator("driveTrain","driveMotorRight2", rightFollower);
		System.out.println("DriveTrain2");
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoy());
		
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}



	public void driveTank(double leftSpeed, double rightSpeed) {
		leftMotor.set(leftSpeed);
		rightMotor.set(-rightSpeed);
	}

}
