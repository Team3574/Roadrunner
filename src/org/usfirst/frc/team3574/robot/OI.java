package org.usfirst.frc.team3574.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3574.robot.commands.Collect;
import org.usfirst.frc.team3574.robot.commands.ConstantSpeedGo;
import org.usfirst.frc.team3574.robot.commands.ExampleCommand;
import org.usfirst.frc.team3574.robot.commands.HighShootKick;
import org.usfirst.frc.team3574.robot.commands.HighShootSpinUp;
import org.usfirst.frc.team3574.robot.commands.Leave;
import org.usfirst.frc.team3574.robot.commands.LowShoot;
import org.usfirst.frc.team3574.robot.commands.IntakePositionDown;
import org.usfirst.frc.team3574.robot.commands.IntakePositionUp;
import org.usfirst.frc.team3574.robot.commands.RotateLifter;
import org.usfirst.frc.team3574.robot.commands.ScalerExpand;
import org.usfirst.frc.team3574.robot.commands.ScalerRetract;
import org.usfirst.frc.team3574.robot.commands.ShifterForward;
import org.usfirst.frc.team3574.robot.commands.ShifterOff;
import org.usfirst.frc.team3574.robot.commands.ShifterReverse;
import org.usfirst.frc.team3574.robot.commands.ShooterSpinUp;
import org.usfirst.frc.team3574.robot.commands.ShooterKickBoulder;
import org.usfirst.frc.team3574.robot.subsystems.Shooter2;
import org.usfirst.frc.team3574.util.POVButton;
import org.usfirst.frc.team3574.util.TrigerButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick stick = new Joystick(0);
	Joystick badStick = new Joystick(1);

	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);



	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released  and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	public OI() {
		/*
		 * Shooting
		 */
//		Button POV0 = new POVButton(stick, 0);
//		POV0.whenPressed(new HighShoot());
//		JoystickButton shootingLow = new JoystickButton(stick, 4);
//		shootingLow.whenPressed(new LowShoot());
//
		JoystickButton spinUpFirst = new JoystickButton(stick, 1);
		spinUpFirst.whenPressed(new HighShootSpinUp());
		JoystickButton actuallyShootAfter = new JoystickButton(stick, 2);
		actuallyShootAfter.whenPressed(new HighShootKick());
//
//		
//		/*
//		 * shifting
//		 */
//		JoystickButton leftForwardShifter = new JoystickButton(stick, 9);
//		leftForwardShifter.whenPressed(new ShifterForward());
//		JoystickButton rightReverseShifter = new JoystickButton(stick, 10);
//		rightReverseShifter.whenPressed(new ShifterReverse());
//
//		/**********************
//		 * intake
//		 *********************/
		TrigerButton collecterIn = new TrigerButton(stick, 3);
		collecterIn.whileHeld(new Collect());
		TrigerButton collecterOut = new TrigerButton(stick, 2);
		collecterOut.whileHeld(new Leave());

//		JoystickButton positionDown = new JoystickButton(stick, 5);
//		positionDown.whileHeld(new IntakePositionDown());
//		JoystickButton positionUp = new JoystickButton(stick, 6);
//		positionUp.whileHeld(new IntakePositionUp());


		/*************
		 * scaling
		 ************/
//		JoystickButton rotateScaler = new JoystickButton(stick, 8);
//		rotateScaler.whenPressed(new RotateLifter());

		

		/**************************************************************************
		 * BADSTICK CODE (not a bad stick, just a bad stick)
		 *************************************************************************/
		/*
		 * Shooting
		 */
		Button badPOV0 = new POVButton(badStick, 0);
		badPOV0.whenPressed(new HighShootSpinUp());
		JoystickButton badShootingLow = new JoystickButton(badStick, 6);
		badShootingLow.whenPressed(new LowShoot());
		
		
		JoystickButton badSpinUpFirst = new JoystickButton(badStick, 10);
		badSpinUpFirst.whileHeld(new ShooterSpinUp());
		JoystickButton badActuallyShootAfter = new JoystickButton(badStick, 9);
		badActuallyShootAfter.whileHeld(new ShooterKickBoulder());
		/*
		 * shifting
		 */
		JoystickButton badLeftForwardShifter = new JoystickButton(badStick, 11);
		badLeftForwardShifter.whenPressed(new ShifterForward());
		JoystickButton badRightReverseShifter = new JoystickButton(badStick, 12);
		badRightReverseShifter.whenPressed(new ShifterReverse());


		/**********************
		 * intake
		 *********************/
		JoystickButton badCollecterIn = new JoystickButton(badStick, 1);
		badCollecterIn.whileHeld(new Collect());
		JoystickButton badCollecterOut = new JoystickButton(badStick, 5);
		badCollecterOut.whileHeld(new Leave());

		JoystickButton badPositionDown = new JoystickButton(badStick, 3);
		badPositionDown.whileHeld(new IntakePositionDown());
		JoystickButton badPositionUp = new JoystickButton(badStick, 4);
		badPositionUp.whileHeld(new IntakePositionUp());


		/*************
		 * scaling
		 ************/
//		JoystickButton badRotateScaler = new JoystickButton(stick, 8);
//		badRotateScaler.whenPressed(new RotateLifter());



		/**
		 * test
		 **/
		JoystickButton speedConstant = new JoystickButton(badStick, 2);
		speedConstant.whileHeld(new ConstantSpeedGo());
		
	}


	/**
	 * 
	 *STICK 
	 *
	 */

	public double joystickLeft() {
		return stick.getRawAxis(1);
	}

	public double joystickRight() {
		return stick.getRawAxis(5);
	}

	public int getPOV() {
//		return stick.getPOV(0);
		//TODO: implement this correctly
		return 0;
	}



	/**
	 * 
	 * BADSTICK
	 * 
	 **/
/*
 * arcade mode
 */
	public double arcadeThrottle() {
		return badStick.getRawAxis(1);
	}

	public double arcadeTurn() {
		return -badStick.getRawAxis(2);
	}
	
	
	
	/**********************************************
	 * driver mode
	 *********************************************/
	public double badStickXAxis() {
		return badStick.getRawAxis(0);
	}
	public double badStickYAxis() {
		return badStick.getRawAxis(1);
	}
	public double badStickZAxis() {
		return -badStick.getRawAxis(2);
	}
	
	public double badStickSlider(){
		return -(badStick.getRawAxis(3)-1)/2;
	}

	public int getBadPOV() {
		return badStick.getPOV();
	}
}

