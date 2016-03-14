package org.usfirst.frc.team3574.util;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class DigitalInputLiveWindowSendable implements LiveWindowSendable {

	private CANTalon m_talon;
	private boolean isForwardSwitch;

	private ITable m_table;
	
	public DigitalInputLiveWindowSendable(CANTalon talon, boolean trueIfForwardSwitch) {
		m_talon = talon;
		isForwardSwitch = trueIfForwardSwitch;
	}

	@Override
	public void initTable(ITable subtable) {
		m_table = subtable;
		updateTable();
	}

	@Override
	public ITable getTable() {
		return m_table;
	}

	@Override
	public String getSmartDashboardType() {
		return "Digital Input";
	}

	@Override
	public void updateTable() {
		if (m_table != null) {
			m_table.putBoolean("Value", isForwardSwitch? m_talon.isFwdLimitSwitchClosed(): m_talon.isRevLimitSwitchClosed());
		}
	}

	@Override
	public void startLiveWindowMode() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopLiveWindowMode() {
		// TODO Auto-generated method stub

	}

}
