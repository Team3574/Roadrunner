package org.usfirst.frc.team3574.robot.TCP;

import org.usfirst.frc.team3574.robot.L;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class SerialStream extends Thread {
	private SerialPort port;
	NetworkTable camera;

	public SerialStream() {
		camera = NetworkTable.getTable("camera");
		System.out.println("Serial Instantiate");
		try {
			port = new SerialPort(9600, Port.kOnboard);
			port.enableTermination();
		}
		catch (Exception ex) {
			L.og(ex);
			L.og("*************************** \n\n\n  Did you check the robot console out setting?  go to http://10.35.74.2/ and uncheck Enable Console Out \n\n\n**********************************");
		}
	}

	public void run() {
		System.out.println("new thread");
		//		System.out.println("Run Code TCP");
		String data;
		String rawValuesOnly;
		String xaxis;
		String yaxis;
		if (port == null) {
			return;
		}
		while (true) {
			//			System.out.println("While Loop TCP");

			//			System.out.println("connecting to port: " + this.serverPort);
			try {
				//				System.out.println("Try Loop TCP");
				//System.out.println("Reset Serial");
				//port.reset();
				if ((data = port.readString()) != null && !data.isEmpty() &&
						data.charAt(0) == '{' && data.charAt(data.length()-2) == '}') {
					System.out.println(data);

					xaxis = data.substring(7, 12);

					if(xaxis.equals("match")) { //output substings
						//substring stuff
						rawValuesOnly = data.substring(21);
						xaxis = rawValuesOnly.substring(0, rawValuesOnly.indexOf(","));

						yaxis = rawValuesOnly.substring(rawValuesOnly.indexOf(",") + 1, rawValuesOnly.indexOf("\""));

						camera.putNumber("angle", Double.parseDouble(xaxis));
						camera.putNumber("depth", Double.parseDouble(yaxis));
					}
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			try {
				//				System.out.println("Sleep TCP");
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				//				TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
