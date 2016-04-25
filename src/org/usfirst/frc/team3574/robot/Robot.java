
package org.usfirst.frc.team3574.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team3574.robot.TCP.SerialStream;
import org.usfirst.frc.team3574.robot.commands.BrakeModeOff;
import org.usfirst.frc.team3574.robot.commands.BrakeModeOn;
import org.usfirst.frc.team3574.robot.commands.CenterOnDegree;
import org.usfirst.frc.team3574.robot.commands.RampRateOn;
import org.usfirst.frc.team3574.robot.commands.RampNoRamprate;
import org.usfirst.frc.team3574.robot.commands.Stop;
import org.usfirst.frc.team3574.robot.commands.auto.AutoAnyDefenceAndShoot;
import org.usfirst.frc.team3574.robot.commands.auto.ChevalDeFrise;
import org.usfirst.frc.team3574.robot.commands.auto.PositionToShoot2;
import org.usfirst.frc.team3574.robot.commands.auto.PositionToShoot3;
import org.usfirst.frc.team3574.robot.commands.auto.PositionToShoot3Curve;
import org.usfirst.frc.team3574.robot.commands.auto.PositionToShoot4;
import org.usfirst.frc.team3574.robot.commands.auto.PositionToShoot5;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceBackward;
import org.usfirst.frc.team3574.robot.commands.drivetrain.DriveForDistanceForward;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetEncoders;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ResetYaw;
import org.usfirst.frc.team3574.robot.commands.drivetrain.Rotate1degreeCounterClockwise;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.RotateToADegreeCounterClockwiseOnly;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShifterHighGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.ShifterLowGear;
import org.usfirst.frc.team3574.robot.commands.drivetrain.TurnTo180;
import org.usfirst.frc.team3574.robot.commands.intake.ResetPositionEnc;
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

	SerialStream serialCamera;

	Command autonomousCommand;
	//	SendableChooser chooser;
	SendableChooser position;
	SendableChooser defenceType;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		//		chooser = new SendableChooser();
		//		chooser.addDefault("Drive over Defences FAST", new AutoDriveOverDefencesByTimeFAST());
		//		chooser.addObject("Drive over Defences SLOW", new AutoDriveOverDefencesByTimeSLOW());
		//		chooser.addObject("Low Bar and Shoot", new AutoLowBarAndShootByTicks());
		//		chooser.addObject("Do Nothing", new AutoDoNothing());
		//		chooser.addObject("Portculis", new AutoPortculis());
		//		chooser.addObject("TEST Touch Outerworks", new AutoTouchDefences());

		position = new SendableChooser();
		position.addDefault("Don't Shoot", 0);
		position.addObject("1", 1);
		position.addObject("2", 2);
		position.addObject("3", 3);
		position.addObject("4", 4);
		position.addObject("5", 5);

		defenceType = new SendableChooser();
		defenceType.addDefault("Rock Wall", 0);
		defenceType.addObject("Rough Terain", 0);
		defenceType.addObject("Moat", 0);
		//		defenceType.addObject("Ramparts", 3);
		defenceType.addObject("Low Bar", 4);
		defenceType.addObject("Portculis", 5);
		defenceType.addObject("Cheval De Frise", 6);
		defenceType.addObject("Touch Outerworks", 7);
		defenceType.addObject("Do Nothing", 8);
//		defenceType.addObject("LowBar and Shoot", 10);


		//		SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putData("Position", position);
		SmartDashboard.putData("Type", defenceType);


		//		SmartDashboard.putData("rotate clockwise 90", new RotateClockwiseByX(90));
		//		SmartDashboard.putData("rotate counter clockwise 90", new RotateClockwiseByX(-90));
		//		SmartDashboard.putData("Low goal shoot test", new LowShootForward());
		//		SmartDashboard.putData("Move Hood Time", new ReadyHoodThenAimByTime());
//		SmartDashboard.putData("Rotate to 45", new RotateToADegreeClockwiseOnly(45));
//		L.ogSD("Drive Ticks High", new DriveForDistanceForward(10 * DriveTrain2.TICKS_PER_FOOT, -0.3, 0.0));
//		L.ogSD("Drive Ticks Low Gear", new DriveForDistanceForward(10 * DriveTrain2.TICKS_PER_FOOT, -0.75, 0.0));

//		L.ogSD("Drive Ticks Backwards", new DriveForDistanceBackward(-10 * DriveTrain2.TICKS_PER_FOOT, 0.75, 0.0));
		//		L.ogSD("hood full auto", new HoodReadyAuto());
		//		L.ogSD("break off", new BrakeModeOff());
		//		L.ogSD("brake on", new BrakeModeOn());
		L.ogSD("stop", new Stop());
		L.ogSD("rotate to -45", new RotateToADegreeCounterClockwiseOnly(-45));
		L.ogSD("reset yaw", new ResetYaw()); //reset yaw just does ahrs.reset so...
//		L.ogSD("low gear", new ShifterLowGear());
//		L.ogSD("high gear", new ShifterHighGear());
		L.ogSD("brake on", new BrakeModeOn());
		L.ogSD("brake off", new BrakeModeOff());
		L.ogSD("reset encoders", new ResetEncoders());
		
		L.ogSD("position shoot 2", new PositionToShoot2());
		L.ogSD("position shoot 3", new PositionToShoot3());
		L.ogSD("position shoot 4", new PositionToShoot4());
		L.ogSD("position shoot 5", new PositionToShoot5());
		L.ogSD("position swerve 3", new PositionToShoot3Curve());

		L.ogSD("turn 180", new TurnTo180());
		L.ogSD("position reset enc", new ResetPositionEnc());
		L.ogSD("Reset Drive Encoders", new ResetEncoders());
		
		L.ogSD("commands runing", Scheduler.getInstance());
		L.ogSD("rotate 1 degree", new Rotate1degreeCounterClockwise());
		L.ogSD("center on 0", new CenterOnDegree(180));
		
		
//		L.ogSD("lowbar auto shoot", new AutoLowBarAndShootByTicks());
		
		L.ogSD("ramp", new RampRateOn());
		L.ogSD("no ramp", new RampNoRamprate());
		//		SmartDashboard.putData(new IntakeSetPosition(100));
		//		SmartDashboard.putData(new HoodSetPosition(100));

		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(intake);
		SmartDashboard.putData(shooter);
		SmartDashboard.putData(scaler);
		SmartDashboard.putData(lifterArm);

		System.out.println("***************************Welcome to Roadnuner*************************");

		serialCamera = new SerialStream();
		serialCamera.start();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit(){
		this.log();
	}

	public void disabledPeriodic() {
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
		/***  production code  ****/
		//		autonomousCommand = (Command) chooser.getSelected();

		positionOnField = (int) position.getSelected();
		defenceInPosition = (int) defenceType.getSelected();
		autonomousCommand = new AutoAnyDefenceAndShoot();

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
		new RampNoRamprate().start();
		new BrakeModeOff().start();
		
		drivetrain.driveOpositeDirection = 1;
		drivetrain.shifterHightGear();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		this.log();
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
