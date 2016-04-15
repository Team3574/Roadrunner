
package org.usfirst.frc.team3574.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team3574.robot.TCP.TCPClientDataStreem;
import org.usfirst.frc.team3574.robot.TCP.TCPClientControl;
import org.usfirst.frc.team3574.robot.TCP.TCPListener;
import org.usfirst.frc.team3574.robot.commands.BrakeModeOff;
import org.usfirst.frc.team3574.robot.commands.BrakeModeOn;
import org.usfirst.frc.team3574.robot.commands.CameraTurn;
import org.usfirst.frc.team3574.robot.commands.ExampleCommand;
import org.usfirst.frc.team3574.robot.commands.auto.AutoAnyDefenceAndShoot;
import org.usfirst.frc.team3574.robot.commands.auto.AutoDoNothing;
import org.usfirst.frc.team3574.robot.commands.auto.AutoDriveOverDefencesByTimeFAST;
import org.usfirst.frc.team3574.robot.commands.auto.AutoDriveOverDefencesByTimeSLOW;
import org.usfirst.frc.team3574.robot.commands.auto.AutoDriveOverDefencesDistance;
import org.usfirst.frc.team3574.robot.commands.auto.AutoDriveOverFlatDefencesByTime;
import org.usfirst.frc.team3574.robot.commands.auto.AutoLowBarAndShootByTicks;
import org.usfirst.frc.team3574.robot.commands.auto.AutoLowBarAndShootByTime;
import org.usfirst.frc.team3574.robot.commands.auto.AutoPortculis;
import org.usfirst.frc.team3574.robot.commands.auto.AutoTestPid;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistance;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateClockwiseByX;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.intake.IntakeSetPosition;
import org.usfirst.frc.team3574.robot.commands.shooter.HoodReadyAuto;
//import org.usfirst.frc.team3574.robot.commands.shooter.HoodSetPosition;
import org.usfirst.frc.team3574.robot.commands.shooter.LowShoot;
import org.usfirst.frc.team3574.robot.commands.shooter.LowShootForward;
import org.usfirst.frc.team3574.robot.commands.shooter.ReadyHoodThenAimByTime;
import org.usfirst.frc.team3574.robot.subsystems.DriveTrain2;
import org.usfirst.frc.team3574.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team3574.robot.subsystems.Intake2;
import org.usfirst.frc.team3574.robot.subsystems.CollecterArmerPositioner;
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
	public static final CollecterArmerPositioner lifterArm = new CollecterArmerPositioner();
	public static final Shooter2 shooter = new Shooter2();
	public static OI oi;
	public static int positionOnField = 0;
	public static int defenceInPosition = 0;

	TCPClientDataStreem client;
	TCPClientControl commendCliant;

	Command autonomousCommand;
	SendableChooser chooser;
	SendableChooser position;
	SendableChooser defenceType;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		chooser = new SendableChooser();
        chooser.addObject("camera turn", new CameraTurn());
//		chooser.addObject("intake to position", new AutoTestPid());
		chooser.addDefault("Drive over Defences FAST", new AutoDriveOverDefencesByTimeFAST());
		chooser.addObject("Drive over Flat Defences", new AutoDriveOverFlatDefencesByTime());
		chooser.addObject("Drive over Defences SLOW", new AutoDriveOverDefencesByTimeSLOW());
		chooser.addObject("Low Bar and Shoot", new AutoLowBarAndShootByTicks());
		chooser.addObject("Do Nothing", new AutoDoNothing());
		chooser.addObject("Portculis", new AutoPortculis());
		chooser.addObject("Any Defence and Shoot", new AutoAnyDefenceAndShoot());
		
		//        chooser.addObject("Drive Distance", new AutoDriveOverDefencesDistance());
		
		position = new SendableChooser();
		position.addDefault("Don't Shoot", 0);
		position.addObject("1", 1);
		position.addObject("2", 2);
		position.addObject("3", 3);
		position.addObject("4", 4);
		position.addObject("5", 5);
		
		defenceType = new SendableChooser();
		defenceType.addDefault("Rock Wall", 0);
		defenceType.addObject("Rough Terain", 1);
		defenceType.addObject("Moat", 2);
//		defenceType.addObject("Ramparts", 3);
		defenceType.addObject("Low Bar", 4);
		defenceType.addObject("Portculis", 5);
		defenceType.addObject("Cheval De Frise", 6);
		defenceType.addObject("Touch Outerworks", 7);
		defenceType.addObject("Do Nothing", 8);
		
		
		SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putData("Position", position);
		SmartDashboard.putData("Type", defenceType);
		
		
		SmartDashboard.putData("rotate clockwise 90", new RotateClockwiseByX(90));
		SmartDashboard.putData("rotate counter clockwise 90", new RotateClockwiseByX(-90));
		SmartDashboard.putData("Low goal shoot test", new LowShootForward());
		SmartDashboard.putData("Move Hood Time", new ReadyHoodThenAimByTime());
		SmartDashboard.putData("Rotate to 45", new RotateToADegreeClockwiseOnly(45));
		L.ogSD("Drive 500 Ticks", new DriveForDistance(500, 0, 0));
		L.ogSD("hood full auto", new HoodReadyAuto());
		L.ogSD("break off", new BrakeModeOff());
		L.ogSD("brake on", new BrakeModeOn());
		
		
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
		int posNumber;
		int defNumber;
		if((int) position.getSelected() == 1) {
			posNumber = 0;
		} else {
			posNumber = (int) position.getSelected();
		}
		if((int) defenceType.getSelected() == 1 || (int) defenceType.getSelected() == 2) {
			defNumber = 0;
		} else {
			defNumber = (int) defenceType.getSelected();
		}
		System.out.println(posNumber);
		System.out.println(defNumber);
		autonomousCommand = (Command) chooser.getSelected();

		positionOnField = posNumber;
		defenceInPosition = defNumber;
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
		SmartDashboard.putNumber("motorShooter1", RobotMap.motorShooter1.getOutputCurrent());
		SmartDashboard.putNumber("motorShooter2", RobotMap.motorShooter2.getOutputCurrent());

		
		SmartDashboard.putNumber("SLiderValue", Robot.oi.badStickSlider0To1());

		this.log();

		Scheduler.getInstance().run();
	}

	private void log() {
		intake.log();
		shooter.log();
		drivetrain.log();
		oi.log();

		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
