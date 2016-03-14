package org.usfirst.frc.team3574.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//	+ back
	public static CANTalon motorDriveLeft1 = new CANTalon(1);
	//	+ back
	public static CANTalon motorDriveLeft2 = new CANTalon(2);
	//	+ front
	public static CANTalon motorDriveRight1 = new CANTalon(3);
	//	+ front
	public static CANTalon motorDriveRight2 = new CANTalon(4);
	
	public static DoubleSolenoid shifter = new DoubleSolenoid(0, 1);
	
	
	//	+ back
	public static CANTalon motorShooter1 = new CANTalon(5);
	//	+ back
	public static CANTalon motorShooter2 = new CANTalon(6);
	//	+  rotates towords back
	public static CANTalon motorHoodRotator  = new CANTalon(8);
	
	
	//	+ in
	public static CANTalon motorIntakeWheels = new CANTalon(7);
	//	+ down
	public static CANTalon motorIntakePosition = new CANTalon(11);	
	//	+ in
	public static CANTalon motorIntakeRollers = new CANTalon(12);
	
	
	//	?
	public static CANTalon motorScalerRotater = new CANTalon(9);
	//	?
	public static CANTalon motorScalerLifter = new CANTalon(10);
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
