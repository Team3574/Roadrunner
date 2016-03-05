package org.usfirst.frc.team3574.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class POVButton extends Button{
	
	 GenericHID m_joystick;
	 int m_POV;

	public POVButton(GenericHID joystick, int POV) {
        m_joystick = joystick;
        m_POV = POV;
	}
	public boolean get() {
        
        if (m_joystick.getPOV(m_POV) != -1) {
			return true;
		} else {
			return false;
		}
	}
}