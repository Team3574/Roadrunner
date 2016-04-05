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
	DoubleSolenoid rotatator1 = RobotMap.scalerRotator1;
	DoubleSolenoid rotatator2 = RobotMap.scalerRotator2;
	
	public Scaler2() {
		lifter.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		
		LiveWindow.addActuator("SCALER", "LIFT +expand?", lifter);
		
		lifter.enableBrakeMode(true);
		
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public void armsUp() {
		rotatator1.set(Value.kForward);
		rotatator2.set(Value.kForward);
	}
	
	public void armsDown() { //natural robot state
		rotatator1.set(Value.kReverse);
		rotatator2.set(Value.kReverse);
	}
	
	public void extend(/*double lifterSetPos*/) {
		lifter.set(-0.5);
	}
	
	public void retract() {
		lifter.set(0.6);
	}
	
	public void stopScaling() {
		lifter.set(0);
	}
}
