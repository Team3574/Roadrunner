package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.commands.ArcadeDriveWithJoy;
import org.usfirst.frc.team3574.robot.commands.DriveWithJoy;
import org.usfirst.frc.team3574.robot.commands.NoDrive;
import org.usfirst.frc.team3574.util.TalonEncoderLiveWindowSendable;

import com.kauailabs.navx.frc.AHRS;
import com.ni.vision.NIVision.CalibrationThumbnailType;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class DriveTrain2 extends Subsystem {
	CANTalon leftMotor = RobotMap.motorDriveLeft1;
	CANTalon leftFollower = RobotMap.motorDriveLeft2;
	CANTalon rightMotor = RobotMap.motorDriveRight1;
	CANTalon rightFollower = RobotMap.motorDriveRight2;
	DoubleSolenoid shifter = RobotMap.shifter;
	TalonEncoderLiveWindowSendable leftDriveEnc = new TalonEncoderLiveWindowSendable(leftMotor);
	TalonEncoderLiveWindowSendable rightDriveEnc = new TalonEncoderLiveWindowSendable(rightMotor);
	AHRS ahrs = new AHRS(SPI.Port.kMXP); 
//    private double offset = 90;


	public DriveTrain2() {
		/*
		 * Left Side
		 */
		leftMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		/**PID LEFT
		 * leftMotor.changeControlMode(CANTalon.TalonControlMode.Position);
		 * leftMotor.configMaxOutputVoltage(8.0);
		 * leftMotor.setPID(1, 0, 0);
		 */
		leftFollower.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftFollower.set(1);

		
		/**
		 * Right Side
		 */
		rightMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		/**PID RIGHT
		 * rightMotor.changeControlMode(CANTalon.TalonControlMode.Position);
		 * rightMotor.configMaxOutputVoltage(8.0);
		 * rightMotor.setPID(1, 0, 0);		
		 */
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
		
		LiveWindow.addSensor("driveTrain", "Left Enc", leftDriveEnc);
		LiveWindow.addSensor("driveTrain", "Right Enc", rightDriveEnc);
		
//		LiveWindow.addSensor("encoder", "posEnc", component);tor.getEncPosition());
		ahrs = new AHRS(SPI.Port.kMXP);
		
		if (ahrs != null) {
			LiveWindow.addSensor("AHRS", "Gyro", ahrs);
		}
	}

	public void initDefaultCommand() {
//		setDefaultCommand(new DriveWithJoy());
		setDefaultCommand(new ArcadeDriveWithJoy());
		
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	public double getRoll() {
		if (ahrs != null) {
			return ahrs.getRoll()/* + offset) % 360*/;  // ahrs without mods goes -180 to 180
		} else {
			return 0;
		}
	}
	
	public double getPitch() {
		if (ahrs != null) {
			return ahrs.getPitch()/* + offset) % 360*/;  // ahrs without mods goes -180 to 180
		} else {
			return 0;
		}
	}
	
	public double getYaw() {
		if (ahrs != null) {
			return ahrs.getYaw()/* + offset) % 360*/;  // ahrs without mods goes -180 to 180
		} else {
			return 0;
		}
	}

	public void driveTank(double leftSpeed, double rightSpeed) {
		leftMotor.set(leftSpeed);
		rightMotor.set(-rightSpeed);
//		System.out.println(leftMotor.getEncPosition());
	}
	
	public void driveArcade(double throttle, double turnValue) {
		leftMotor.set(throttle + turnValue);
		rightMotor.set(-(throttle - turnValue));
//		System.out.println(ahrs.getAngle());
	}
	
	public void DriveMode(double throttleFront,double throttleSide,double turnValue) {
		if(throttleFront > turnValue || throttleSide > turnValue){
			leftMotor.set(throttleFront + throttleSide);
			rightMotor.set(-(throttleFront - throttleSide));
		} else {
			leftMotor.set(turnValue);
			rightMotor.set(-turnValue);
		}
	}
	
	public void shifterForward() {
		shifter.set(DoubleSolenoid.Value.kForward);
	}
	public void shifterReverse() {
		shifter.set(DoubleSolenoid.Value.kReverse);
	}
	public void shifterOff() {
		shifter.set(DoubleSolenoid.Value.kOff);
	}


}
