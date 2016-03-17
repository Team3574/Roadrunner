package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;

import com.ni.vision.NIVision.CalibrationThumbnailType;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Scaler2 extends Subsystem {
	CANTalon lifter = RobotMap.motorScalerLifter;
	CANTalon rotater = RobotMap.motorScalerRotater;
	
	public Scaler2() {
//		lifter.changeControlMode(CANTalon.TalonControlMode.Position);
//		lifter.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
//		lifter.setPID(1, 1, 0);
		
		LiveWindow.addActuator("SCALER", "ROTATE +up?", rotater);
		LiveWindow.addActuator("SCALER", "LIFT +expand?", lifter);
		
		lifter.enableBrakeMode(true);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public void rotateArm(/*double lifterSetPos*/) {
		rotater.set(0.2);
	}
	
	public void expand(/*double lifterSetPos*/) {
		lifter.set(-0.5);
	}
	
	public void retract() {
		lifter.set(0.6);
	}
	
	public void stopScaling() {
		rotater.set(0);
		lifter.set(0);
	}
}
