package org.usfirst.frc.team3574.util;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class TalonAnalogLiveWindowSendable implements LiveWindowSendable {

	CANTalon m_talon;

	private ITable m_table;
	
	public TalonAnalogLiveWindowSendable(CANTalon talon) {
		m_talon = talon;
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
		return "Analog Input";
	}

	@Override
	public void updateTable() {
		if (m_table != null) { 
			m_table.putNumber("Value", m_talon.getAnalogInRaw());
		}
	}

	@Override
	public void startLiveWindowMode() {

	}

	@Override
	public void stopLiveWindowMode() {

	}

}
