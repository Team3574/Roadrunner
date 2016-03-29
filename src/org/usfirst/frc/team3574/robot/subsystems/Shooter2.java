package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.L;
import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.util.DigitalInputLiveWindowSendable;
import org.usfirst.frc.team3574.util.TalonAnalogLiveWindowSendable;
import org.usfirst.frc.team3574.util.TalonEncoderLiveWindowSendable;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter2 extends Subsystem {
	CANTalon shooterWheel = RobotMap.motorShooter1;
//	CANTalon shooterFollower = RobotMap.motorShooter2;
	CANTalon hoodRotater = RobotMap.motorHoodRotator;
	TalonEncoderLiveWindowSendable shooterEnc = new TalonEncoderLiveWindowSendable(shooterWheel);
	TalonAnalogLiveWindowSendable hoodPot = new TalonAnalogLiveWindowSendable(hoodRotater);
	public boolean shooterSpunUp = false;


	public Shooter2() {
		shooterWheel.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		shooterWheel.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		shooterWheel.enableBrakeMode(false);
		shooterWheel.reverseOutput(true);

//		shooterFollower.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
//		shooterFollower.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
//		shooterFollower.enableBrakeMode(false);
//		shooterFollower.reverseOutput(true);
		
/** changed because follower is not working */
//		shooterFollower.changeControlMode(CANTalon.TalonControlMode.Follower);
//		shooterFollower.set(5);


		hoodRotater.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		//	hoodRotater.setPID(1, .01, 0);

		hoodRotater.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		//	hoodRotater.changeControlMode(CANTalon.TalonControlMode.Position);
		//	hoodRotater.set(0);
		//	hoodRotater.enable();
		
		hoodRotater.ConfigFwdLimitSwitchNormallyOpen(false);
		hoodRotater.ConfigRevLimitSwitchNormallyOpen(false);

		LiveWindow.addActuator("Shooter", "SHOOTER 1 +shoot", shooterWheel);
//		LiveWindow.addActuator("Shooter", "SHOOTER 2 +shoot", shooterFollower);
		LiveWindow.addActuator("Shooter", "HOOD ROTATER +ready_to_shoot", hoodRotater);
		
		LiveWindow.addSensor("Shooter", "ENC SHOOTER", shooterEnc);
		LiveWindow.addSensor("Shooter", "POT SHOOTER", hoodPot);

	}

	public void initDefaultCommand() {
		SmartDashboard.putNumber("Shoot Pos P", 1.0);
		SmartDashboard.putNumber("Shoot Pos I", 0.01);
		SmartDashboard.putNumber("Shoot Pos D", 0.0);
		SmartDashboard.putNumber("Shoot pos location", 0.0);	
	}

	public void setHoodPosition(double position) {
		hoodRotater.setPID(
				SmartDashboard.getNumber("Shoot Pos P", 1.0),
				SmartDashboard.getNumber("Shoot Pos I", 0.01),
				SmartDashboard.getNumber("Shoot Pos D", 0.0)
				);
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		hoodRotater.set(position);

	}
	
	public void hoodMotorSimple(double setpoint) {
		// up
		if(hoodRotater.getAnalogInPosition() > (setpoint + 20)) {
			L.og("go up");
			hoodRotater.set( -0.4 /*-0.001 * position.getEncPosition()*/);
		//down
		} else if(hoodRotater.getAnalogInPosition() < (setpoint - 20)) {
			L.og("go down");
			hoodRotater.set(0.4);
		} else {
			hoodRotater.set(0);
		}
	}


	public void shooter(double shooterSpeed) {
		//TODO: REVERSE THE SHOOTER SPEEDS!!! :)
		shooterWheel.set(-shooterSpeed);
//		shooterFollower.set(-shooterSpeed);
		//		System.out.println(shooterWheel.getEncPosition());
	}


	public void hood(double hoodRotatePos) {
		hoodRotater.set(hoodRotatePos);
	}

	public boolean hoodStowedlmtswitch() {
		return false;
		//TODO: needs to change
	}

	public boolean hoodShootlmtswitch() {
		return false;
		//TODO: also needs to change
	}


	public void log() {
		SmartDashboard.putNumber("hoodPot", hoodRotater.getAnalogInRaw());

	}

}
