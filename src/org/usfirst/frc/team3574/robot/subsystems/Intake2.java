package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.util.DigitalInputLiveWindowSendable;
import org.usfirst.frc.team3574.util.TalonEncoderLiveWindowSendable;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake2 extends Subsystem {
	CANTalon rollers = RobotMap.motorIntakeRollers;
	CANTalon position = RobotMap.motorIntakePosition;
	CANTalon position2 = RobotMap.motorIntakePosition2;
	CANTalon wheels = RobotMap.motorIntakeWheels;
	DigitalInput boulderInIntake = new DigitalInput(0);
	TalonEncoderLiveWindowSendable positionEnc = new TalonEncoderLiveWindowSendable(position);
	DigitalInputLiveWindowSendable PositionLimitSwitchFRWRD = new DigitalInputLiveWindowSendable(position, true);
	DigitalInputLiveWindowSendable PositionLimitSwitchBCK = new DigitalInputLiveWindowSendable(position, false);



	public Intake2() {

		position.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		//		position.setPID(1, .01, 0);
		position.configMaxOutputVoltage(7.2);

		position.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		//		position.changeControlMode(CANTalon.TalonControlMode.Position);
		//		position.set(11000);
		//		position.enable();
		
//		position2.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		position2.changeControlMode(CANTalon.TalonControlMode.Follower);
		position2.set(11);
		//		position2.changeControlMode(CANTalon.TalonControlMode.Position);
		//		position2.set(11000);
		//		position2.enable();	
		
		position.ConfigFwdLimitSwitchNormallyOpen(false);
		position.ConfigRevLimitSwitchNormallyOpen(false);

		
		LiveWindow.addActuator("Intake", "ROLLERS +in", rollers);
		LiveWindow.addActuator("Intake", "SUCKER WHEELS +out", wheels);
		LiveWindow.addActuator("Intake", "POSSITION +down", position);
		LiveWindow.addActuator("Intake", "22222POSSITION22222 +down", position2);
		
		LiveWindow.addSensor("Intake", "ENC POSITION", positionEnc);
		LiveWindow.addSensor("Intake", "INTAKE FULL", boulderInIntake);

		LiveWindow.addSensor("Intake", "UP ARM LIMIT", PositionLimitSwitchFRWRD);
		LiveWindow.addSensor("Intake", "DOWN ARM LIMIT", PositionLimitSwitchBCK);


		//		System.out.println(position.getEncPosition());

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	public void rollerAndWheelsIn() {
		rollers.set(0.7);
		wheels.set(-0.5);
	}

	public void rollerAndWheelsOut() {
		rollers.set(-1);
//		rollers.set(-0.7);
//		wheels.set(0.5);
		wheels.set(0.75);
	}

	public void setIntakePosition(double setPositionEncoder) {

		position.set(-setPositionEncoder);
//		position2.set(-setPositionEncoder);
	}
	
	public void lowerInteakeArms() {
		position.set(0.2);
//		position2.set(0.2);
	}
	public void raiseInteakeArms() {
		position.set(-0.2);
//		position2.set(-0.2);
	}

	public void positionMotorSimple(double setpoint) {
		/** 
		 *  POSITION2 IS IN FOLLOWER MODE
		 */
		if(position.getEncPosition() >= (setpoint + 200)) {// up
			//			L.og("go up");
			position.set( -0.2 /*-0.001 * position.getEncPosition()*/);
//			position2.set ( -0.5 /*-0.001 * position.getEncPosition()*/);
		} else if(position.getEncPosition() > (setpoint + 20) && position.getEncPosition() < (setpoint + 200)) {
			position.set(0.3 * (position.getEncPosition() - setpoint) / 1000);
//			position2.set(-0.1);
			
		} else if(position.getEncPosition() < (setpoint - 20)) {//down
			//			L.og("go down");
//			position.set(-(0.3 * (position.getEncPosition() - setpoint) / 1000));
			position.set(0.0);
		} else {
			position.set(0);
//			position2.set(0);
		}
	}
	
	public void setPositionArmsManually(double posIsDown) {
		position.set(posIsDown);
//		position2.set(posIsDown);
	}

	public void feedShooter() {
		wheels.set(-1);
	}

	public void lowShoot() {
		wheels.set(1);
	}

	public void stopIntake() {
		wheels.set(0);
		rollers.set(0);
		//				System.out.println("stopping intake");
	}

	public boolean intakeFull() {
		return boulderInIntake.get();
	}
	
	public void calibratePositionToCurrentPos(int newEncPosition) {
		position.setEncPosition(newEncPosition);
	}
	
	public int positionEncoderValue() {
		return position.getEncPosition();
	}
	
	public boolean isLowLimitSwitchClicked() {
		return !position.isFwdLimitSwitchClosed();
	}


	public void log() {
		SmartDashboard.putNumber("position encoder", position.getEncPosition());
	}


}
