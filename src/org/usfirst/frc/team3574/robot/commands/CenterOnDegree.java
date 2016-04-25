package org.usfirst.frc.team3574.robot.commands;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.commands.drivetrain.Rotate1degreeClockwise;
import org.usfirst.frc.team3574.robot.commands.drivetrain.Rotate1degreeCounterClockwise;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CenterOnDegree extends Command {
	double targetDegree;
	double yaw;
	int runcount = 0;
	public CenterOnDegree(double degree) {
		requires(Robot.drivetrain);
		this.targetDegree = degree;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		runcount = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		yaw = (Robot.drivetrain.getYaw() + 360) % 360;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(runcount % 20 == 0) {
			if(yaw > targetDegree - 1 && yaw < targetDegree + 1) {
				Robot.drivetrain.driveTank(0.0, 0.0);
				return true;
			}
			if(yaw < targetDegree) {
				new Rotate1degreeClockwise().start();
			}
			if(yaw > targetDegree) {
				new Rotate1degreeCounterClockwise().start();	
			}
		}
		runcount++;
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
