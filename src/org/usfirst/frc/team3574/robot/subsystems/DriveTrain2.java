package org.usfirst.frc.team3574.robot.subsystems;

import org.omg.PortableServer.ThreadPolicyOperations;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ArcadeDriveWithJoy;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveWithJoy;
import org.usfirst.frc.team3574.robot.commands.drivetrain.NoDrive;
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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain2 extends Subsystem {
	CANTalon leftMotor = RobotMap.motorDriveLeft1;
	CANTalon leftFollower = RobotMap.motorDriveLeft2;
	CANTalon rightMotor = RobotMap.motorDriveRight1;
	CANTalon rightFollower = RobotMap.motorDriveRight2;
	DoubleSolenoid shifter = RobotMap.shifter;
	TalonEncoderLiveWindowSendable leftDriveEnc = new TalonEncoderLiveWindowSendable(leftMotor);
	TalonEncoderLiveWindowSendable rightDriveEnc = new TalonEncoderLiveWindowSendable(rightMotor);
	AHRS ahrs = new AHRS(SPI.Port.kMXP); 

	public int driveOpositeDirection = 1;
	public static final double CENTER_OF_GOAL = 342.0;
	public static final double HEIGHT_OF_GOAL = 198.0;
	public static final double TICKS_PER_FOOT = 2440;
	public DriveTrain2() {
		/*
		 * Left Side
		 */
		leftMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
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
		rightMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rightMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		/**PID RIGHT
		 * rightMotor.changeControlMode(CANTalon.TalonControlMode.Position);
		 * rightMotor.configMaxOutputVoltage(8.0);
		 * rightMotor.setPID(1, 0, 0);		
		 */
		rightFollower.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightFollower.set(3);
		
//		rightMotor.reverseOutput(true);
		

		LiveWindow.addActuator("DRIVE","LEFT 1 +back", leftMotor);
		LiveWindow.addActuator("DRIVE","LEFT 2 +back", leftFollower);
		LiveWindow.addActuator("DRIVE","RIGHT 1 +front", rightMotor);
		LiveWindow.addActuator("DRIVE","RIGHT 2 +front", rightFollower);
		
		LiveWindow.addActuator("DRIVE", "SHIFT forward=high gear", shifter);
		
		System.out.println("DriveTrain2");
		
		LiveWindow.addSensor("DRIVE", "ENC LEFT", leftDriveEnc);
		LiveWindow.addSensor("DRIVE", "ENC RIGHT", rightDriveEnc);
		
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

	
	public double getYaw() {
		if (ahrs != null) {
			return ahrs.getYaw()/* + offset) % 360*/;  // ahrs without mods goes -180 to 180
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
	public double getRoll() {
		if (ahrs != null) {
			return ahrs.getRoll()/* + offset) % 360*/;  // ahrs without mods goes -180 to 180
		} else {
			return 0;
		}
	}
	
	public void resetYaw() {
		ahrs.reset();
	}
	
	public double leftEncReading() {
		return -leftMotor.getEncPosition();
	}
	public double rightEncReading() {
		return rightMotor.getEncPosition();
	}
	public double lowestEncReading() {
		if(rightEncReading() > leftEncReading()) {
			return leftEncReading();
		} else if(leftEncReading() > rightEncReading()) {
			return rightEncReading();
		} else {
			return 0.0;
		}
	}

	public void driveTank(double leftSpeed, double rightSpeed) {
		leftMotor.set(leftSpeed * driveOpositeDirection);
		rightMotor.set(rightSpeed * -driveOpositeDirection);
//		System.out.println(leftMotor.getEncPosition());
	}
	
	public void driveArcade(double throttle, double turnValue) {
		leftMotor.set((throttle + turnValue) * driveOpositeDirection);
		rightMotor.set((throttle - turnValue) * -driveOpositeDirection);
//		System.out.println(ahrs.getAngle());
	}
	
	public void preDriveMode(double throttleFront, double throttleSide, double turnValue) {
		double setpoint = Math.tan(throttleSide / throttleFront);
		System.out.println(setpoint);
//		JulesMode(throttleFront, throttleSide, turnValue, this.getYaw(), setpoint);
	}
	
	public void JulesMode(double throttleFront, double throttleSide, double turnValue, double gyroAngle, double setpoint) {
		double directionToSpin = 1;
		if(!(gyroAngle > (setpoint - 20) && gyroAngle < (setpoint + 20))) {
			if(gyroAngle > (setpoint - 20)) {
				directionToSpin = -directionToSpin;
			}
			leftMotor.set((throttleFront + throttleSide) * directionToSpin);
			rightMotor.set((throttleFront - throttleSide) * -directionToSpin);
		}
		if(gyroAngle > (setpoint - 20) && gyroAngle < (setpoint + 20)) {
			leftMotor.set(throttleFront + throttleSide);
			rightMotor.set(-(throttleFront - throttleSide));
		}
	}
	
	public void resetEncoders() {
		leftMotor.setEncPosition(-1);
		rightMotor.setEncPosition(1);
	}
	
	public void shifterHightGear() {
		shifter.set(DoubleSolenoid.Value.kForward);
	}
	public void shifterLowGear() {
		shifter.set(DoubleSolenoid.Value.kReverse);
	}
	public void shifterOff() {
		shifter.set(DoubleSolenoid.Value.kOff);
	}
	
	public void brakeMode(boolean bool) {
		rightMotor.enableBrakeMode(bool);
		leftMotor.enableBrakeMode(bool);
	}

	public void log() {
		SmartDashboard.putNumber("left Enc", leftEncReading());
		SmartDashboard.putNumber("right Enc", rightEncReading());
		
		
		SmartDashboard.putNumber("AHRS Yaw", getYaw());
//		SmartDashboard.putNumber("AHRS Roll", getRoll());
//		SmartDashboard.putNumber("AHRS Pitch", getPitch());
	

		SmartDashboard.putBoolean("Left Motor Brake Mode", leftMotor.getBrakeEnableDuringNeutral());
		SmartDashboard.putBoolean("Left Follower Brake Mode", leftMotor.getBrakeEnableDuringNeutral());
		SmartDashboard.putBoolean("Right Motor Brake Mode", leftMotor.getBrakeEnableDuringNeutral());
		SmartDashboard.putBoolean("Right Follower Mode", leftMotor.getBrakeEnableDuringNeutral());

	
	}

}
