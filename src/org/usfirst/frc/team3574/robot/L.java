package org.usfirst.frc.team3574.robot;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class L {
	public static void og(String s) {
		System.out.println(s);
	}
	
	public static void og(double d) {
		System.out.println(d);
	}

	public static void og(Object o) {
		System.out.println(o);
	}
	
	public static void ogCmdInit(Command c) {
		System.out.println("- INIT ------- " + c.getName());
	}
	
	public static void ogCmdInit(Command c, String s) {
		System.out.println("- INIT ------- " + c.getName() + ", s");
	}
	
	public static void ogCmdEnd(Command c) {
		System.out.println("-- END ------- " + c.getName());
	}

	public static void ogCmdInterrupted(Command c) {
		System.out.println("-- INTERRUPT - " + c.getName());
	}
	
	public static void ogSD(String key, double value) {
		SmartDashboard.putNumber(key, value);
	}

	public static void ogSD(String key, String value) {
		SmartDashboard.putString(key, value);
	}

	public static void ogSD(String key, Sendable data) {
		SmartDashboard.putData(key, data);
	}
}

