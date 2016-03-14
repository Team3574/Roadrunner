package org.usfirst.frc.team3574.util;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class TalonEncoderLiveWindowSendable implements LiveWindowSendable {

	CANTalon m_talon;

	private ITable m_table;
	
	public TalonEncoderLiveWindowSendable(CANTalon talon) {
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
		return "Quadrature Encoder";
	}

	@Override
	public void updateTable() {
		if (m_table != null) {
			m_table.putNumber("Speed", m_talon.getEncVelocity());
			m_table.putNumber("Distance", m_talon.getEncPosition());
			m_table.putNumber("Distance per Tick", 0);  	

		}
	}

	@Override
	public void startLiveWindowMode() {

	}

	@Override
	public void stopLiveWindowMode() {

	}

}
