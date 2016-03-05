package org.usfirst.frc.team3574.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class TrigerButton extends Button{
	
	 GenericHID m_joystick;
	 int m_axis;

	public TrigerButton(GenericHID joystick, int axis) {
        m_joystick = joystick;
        m_axis = axis;
	}
	public boolean get() {
        
        if (m_joystick.getRawAxis(m_axis) >= 0.75) {
			return true;
		} else {
			return false;
		}
	}
}