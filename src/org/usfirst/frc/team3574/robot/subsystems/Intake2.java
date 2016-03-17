package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.L;
import org.usfirst.frc.team3574.robot.RobotMap;
import org.usfirst.frc.team3574.util.DigitalInputLiveWindowSendable;
import org.usfirst.frc.team3574.util.TalonEncoderLiveWindowSendable;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake2 extends Subsystem {
	CANTalon rollers = RobotMap.motorIntakeRollers;
	CANTalon position = RobotMap.motorIntakePosition;
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

		position.ConfigFwdLimitSwitchNormallyOpen(false);
		position.ConfigRevLimitSwitchNormallyOpen(false);



		LiveWindow.addActuator("Intake", "ROLLERS +in", rollers);
		LiveWindow.addActuator("Intake", "SUCKER WHEELS +in", wheels);
		LiveWindow.addActuator("Intake", "POSSITION +down", position);
		
		LiveWindow.addSensor("Intake", "ENC POSITION", positionEnc);
		LiveWindow.addSensor("Intake", "INTAKE FULL", boulderInIntake);

		LiveWindow.addSensor("Intake", "UP ARM LIMIT", PositionLimitSwitchFRWRD);
		LiveWindow.addSensor("Intake", "DOWN ARM LIMIT", PositionLimitSwitchBCK);


		//		System.out.println(position.getEncPosition());

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		SmartDashboard.putNumber("Pos P", 1.0);
		SmartDashboard.putNumber("Pos I", 0.01);
		SmartDashboard.putNumber("Pos D", 0.0);
		SmartDashboard.putNumber("pos location", 0.0);	
	}

	public void intakeSetPosition(double setpoint) {
		position.setPID(
				SmartDashboard.getNumber("Pos P", 1.0),
				SmartDashboard.getNumber("Pos I", 0.01),
				SmartDashboard.getNumber("Pos D", 0.0)
				);

		position.set(setpoint);
		System.out.println("setpos for pos");
	}

	public void rollerAndWheelsIn() {
		rollers.set(.5);
		wheels.set(0.4);
	}

	public void rollerAndWheelsOut() {
		rollers.set(-.5);
		wheels.set(-0.3);
	}

	public void intakePositionSpeed(double setPositionSpeed) {

		position.set(-setPositionSpeed);
	}

	public void positionMotorSimple(double setpoint) {
		
		if(position.getEncPosition() >= (setpoint + 200)) {// up
			//			L.og("go up");
			position.set( -0.5 /*-0.001 * position.getEncPosition()*/);
		} else if(position.getEncPosition() > (setpoint + 50) && position.getEncPosition() < (setpoint + 200)) {
			position.set(-0.2);
			
		} else if(position.getEncPosition() < (setpoint - 20)) {//down
			//			L.og("go down");
			position.set(0.15);
		} else {
			position.set(0);
		}
	}

	public void feedShooter() {
		wheels.set(.9);
	}

	public void lowShoot() {
		wheels.set(-.9);
	}

	public void stopIntake() {
		wheels.set(0);
		rollers.set(0);
		//				System.out.println("stopping intake");
	}

	public boolean intakeFull() {
		return boulderInIntake.get();
	}


	public void log() {
		SmartDashboard.putNumber("position encoder", position.getEncPosition());
	}


}
