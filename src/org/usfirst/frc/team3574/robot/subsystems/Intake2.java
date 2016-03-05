package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Intake2 extends Subsystem {
	CANTalon rollers = RobotMap.motorIntakeRollers;
	CANTalon position = RobotMap.motorIntakePosition;
	CANTalon wheels = RobotMap.motorIntakeWheels;
	
	
	
	public Intake2() {
		position.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		position.changeControlMode(CANTalon.TalonControlMode.Position);
		
		LiveWindow.addActuator("Intake", "motorIntakeRollers", rollers);
		LiveWindow.addActuator("Intake", "motorIntakePossition", position);
		LiveWindow.addActuator("Intake", "motorIntakeWheels", wheels);
		
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	
	public void rollerIn() {
		rollers.set(.5);
		wheels.set(-0.3);
	}
	
	public void rollerOut() {
		rollers.set(-.5);
		wheels.set(-0.3);
	}
	
	public void intakePosition(double setPosition) {
		position.set(setPosition);
	}
	
	public void feedShooter() {
		wheels.set(1);
	}
	
	public void stopIntake() {
		wheels.set(0);
		rollers.set(0);
	}
	
	
	
}
