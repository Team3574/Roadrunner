package org.usfirst.frc.team3574.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Log extends Command {

	String name;
	double length;

	public Log(String name, double length) {
		this.name = name;
		this.length = length;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println(name + " init");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println(name + " exec");
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(timeSinceInitialized() > length) {
			System.out.println(name + " isFinished");
			return true;
		} else {
			return false;
		}

	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println(name + " end");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		System.out.println(name + " interrupted");
	}
}
