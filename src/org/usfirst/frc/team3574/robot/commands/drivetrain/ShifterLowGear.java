package org.usfirst.frc.team3574.robot.commands.drivetrain;

import java.time.format.ResolverStyle;

import org.usfirst.frc.team3574.robot.L;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShifterLowGear extends Command {

	public ShifterLowGear() {

		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		L.ogCmdInit(this);
		Robot.drivetrain.shifterLowGear();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {


	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("Finished Forward");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
