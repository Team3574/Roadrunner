package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;

import com.ni.vision.NIVision.CalibrationThumbnailType;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Scaler2 extends Subsystem {
	CANTalon lifter = RobotMap.motorScalerLifter;
	DoubleSolenoid pin = RobotMap.holdPin;
	
	public Scaler2() {
		lifter.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		lifter.enableBrakeMode(true);
		
		LiveWindow.addActuator("SCALER", "+ is intake", lifter);
		
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public void pinSet() {
		pin.set(Value.kForward);
	}
	
	public void pinRelease() { //natural robot state
		pin.set(Value.kReverse);
	}
	
	public void intakeString() {
		lifter.set(1.0);
	}
	
	public void releaseString() {
		lifter.set(-0.3);
	}
	
	public void stopScaling() {
		lifter.set(0.0);
	}
}
