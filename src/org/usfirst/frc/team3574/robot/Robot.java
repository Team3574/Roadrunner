
package org.usfirst.frc.team3574.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team3574.robot.TCP.TCPClientDataStreem;
import org.usfirst.frc.team3574.robot.TCP.TCPClientControl;
import org.usfirst.frc.team3574.robot.TCP.TCPListener;
import org.usfirst.frc.team3574.robot.commands.CameraTurn;
import org.usfirst.frc.team3574.robot.commands.ExampleCommand;
import org.usfirst.frc.team3574.robot.commands.auto.AutoDoNothing;
import org.usfirst.frc.team3574.robot.commands.auto.AutoDriveOverDefencesByTimeFAST;
import org.usfirst.frc.team3574.robot.commands.auto.AutoDriveOverDefencesByTimeSLOW;
import org.usfirst.frc.team3574.robot.commands.auto.AutoDriveOverDefencesDistance;
import org.usfirst.frc.team3574.robot.commands.auto.AutoDriveOverFlatDefencesByTime;
import org.usfirst.frc.team3574.robot.commands.auto.AutoLowBarAndShoot;
import org.usfirst.frc.team3574.robot.commands.auto.AutoPortculis;
import org.usfirst.frc.team3574.robot.commands.auto.AutoTestPid;
import org.usfirst.frc.team3574.robot.commands.intake.IntakeSetPosition;
import org.usfirst.frc.team3574.robot.commands.shooter.HoodSetPosition;
import org.usfirst.frc.team3574.robot.commands.shooter.LowShoot;
import org.usfirst.frc.team3574.robot.subsystems.DriveTrain2;
import org.usfirst.frc.team3574.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team3574.robot.subsystems.Intake2;
import org.usfirst.frc.team3574.robot.subsystems.LifterArm;
import org.usfirst.frc.team3574.robot.subsystems.Scaler2;
import org.usfirst.frc.team3574.robot.subsystems.Shooter2;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final DriveTrain2 drivetrain = new DriveTrain2();
	public static final Intake2 intake = new Intake2();
	public static final Scaler2 scaler = new Scaler2();
	public static final LifterArm lifterArm = new LifterArm();
	public static final Shooter2 shooter = new Shooter2();
	public static OI oi;

	TCPClientDataStreem client;
	TCPClientControl commendCliant;

	Command autonomousCommand;
	SendableChooser chooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		chooser = new SendableChooser();
		        chooser.addDefault("camera turn", new CameraTurn(10));
//		chooser.addObject("intake to position", new AutoTestPid());
		chooser.addDefault("Drive over Defences FAST", new AutoDriveOverDefencesByTimeFAST());
		chooser.addObject("Drive over Flat Defences", new AutoDriveOverFlatDefencesByTime());
		chooser.addObject("Drive over Defences SLOW", new AutoDriveOverDefencesByTimeSLOW());
		chooser.addObject("Low Bar and Shoot", new AutoLowBarAndShoot());
		chooser.addObject("Do Nothing", new AutoDoNothing());
		chooser.addObject("Portculis", new AutoPortculis());
		//        chooser.addObject("Drive Distance", new AutoDriveOverDefencesDistance());

		SmartDashboard.putData("Auto mode", chooser);

//		SmartDashboard.putData(new IntakeSetPosition(100));
//		SmartDashboard.putData(new HoodSetPosition(100));

		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(intake);
		SmartDashboard.putData(shooter);
		SmartDashboard.putData(scaler);
		SmartDashboard.putData(lifterArm);

		        client = new TCPClientDataStreem("tegra-steve.local", 8222); //port used to be 8222 but 22 seems to work better?
		        client.start();
//		        commendCliant = new TCPClientControl("169.254.9.214", 0xb08);
//		        commendCliant.start();
		System.out.println("***************************Welcome to Roadnuner*************************");



	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit(){
		drivetrain.driveOpositeDirection = 1;
		drivetrain.shifterHightGear();
		this.log();
	}

	public void disabledPeriodic() {
		drivetrain.driveOpositeDirection = 1;
		drivetrain.shifterHightGear();
		Scheduler.getInstance().run();
		this.log();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		autonomousCommand = (Command) chooser.getSelected();

		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */

		// schedule the autonomous command (example)
		if (autonomousCommand != null) autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		this.log();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to 
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) autonomousCommand.cancel();
		Scheduler.getInstance().add(new ExampleCommand());
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		SmartDashboard.putNumber("motorDriveLeft1", RobotMap.motorDriveLeft1.getOutputCurrent());
		SmartDashboard.putNumber("motorDriveLeft2", RobotMap.motorDriveLeft2.getOutputCurrent());
		SmartDashboard.putNumber("motorDriveRight1", RobotMap.motorDriveRight1.getOutputCurrent());
		SmartDashboard.putNumber("motorDriveRight2", RobotMap.motorDriveRight2.getOutputCurrent());
		SmartDashboard.putNumber("motorIntakePosition", RobotMap.motorIntakePosition.getOutputCurrent());
		SmartDashboard.putNumber("motorIntakeRollers", RobotMap.motorIntakeRollers.getOutputCurrent());
		SmartDashboard.putNumber("motorIntakeWheels", RobotMap.motorIntakeWheels.getOutputCurrent());
		SmartDashboard.putNumber("motorHoodRotator", RobotMap.motorHoodRotator.getOutputCurrent());
		SmartDashboard.putNumber("motorScalerLifter", RobotMap.motorScalerLifter.getOutputCurrent());
		SmartDashboard.putNumber("motorScalerRotater", RobotMap.motorScalerRotater.getOutputCurrent());
		SmartDashboard.putNumber("motorShooter1", RobotMap.motorShooter1.getOutputCurrent());
		SmartDashboard.putNumber("motorShooter2", RobotMap.motorShooter2.getOutputCurrent());

		SmartDashboard.putNumber("SLiderValue", Robot.oi.badStickSlider());

		this.log();

		Scheduler.getInstance().run();
	}

	private void log() {
		intake.log();
		shooter.log();
		drivetrain.log();

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
