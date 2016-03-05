package org.usfirst.frc.team3574.robot.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Shooter2 extends Subsystem {
	CANTalon shooterWheel = RobotMap.motorShooter1;
	CANTalon shooterFolower = RobotMap.motorShooter2;
	CANTalon hoodRotater = RobotMap.motorHoodRotator;
	
	
	public Shooter2() {
	shooterWheel.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
	shooterWheel.enableBrakeMode(false);
	shooterFolower.changeControlMode(CANTalon.TalonControlMode.Follower);
	shooterWheel.reverseOutput(true);
	shooterFolower.set(5);
	shooterWheel.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	hoodRotater.changeControlMode(CANTalon.TalonControlMode.Position);

	LiveWindow.addActuator("Shooter", "motorShooter1", shooterWheel);
	LiveWindow.addActuator("Shooter", "motorShooter2", shooterFolower);
	LiveWindow.addActuator("Shooter", "motorHoodRotator", hoodRotater);
	}
	
	public void initDefaultCommand() {
		
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	
	public void shooter(double shooterSpeed) {
		shooterWheel.set(shooterSpeed);
	}
	
	
	public void hood(double hoodRotate) {
		hoodRotater.set(hoodRotate);
	}
	

}
