package org.usfirst.frc.team3574.robot.TCP;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class TCPClientDataStreem extends Thread {
	private int serverPort;
	private String serverIp;
	NetworkTable camera;
	// TCP Socket Stream connection
	private Socket client = null;
	
	public TCPClientDataStreem(String ip, int port) {
		this.serverPort = port;
		this.serverIp = ip;
		camera = NetworkTable.getTable("camera");
//		System.out.println("Constructor TCP");
	}

	public void run() {
		System.out.println("new thread");
//		System.out.println("Run Code TCP");
		while (true) {
//			System.out.println("While Loop TCP");
			
//			System.out.println("connecting to port: " + this.serverPort);
			try {
//				System.out.println("Try Loop TCP");
				client = new Socket(serverIp, serverPort);
				DataInputStream is = new DataInputStream(client.getInputStream());
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String data;
				String rawValuesOnly;
				String xaxis;
				String yaxis;
				System.out.println("connectedData");
				while((data = reader.readLine()) != null) {
//					System.out.println(data);
					
 					xaxis = data.substring(7, 12);
//					System.out.println("                                                           " + xaxis);
					
					if(xaxis.equals("match")) { //output substings
						//substring stuff
//						System.out.println("                                                       BOB");
						rawValuesOnly = data.substring(21);
						xaxis = rawValuesOnly.substring(0, rawValuesOnly.indexOf(","));

//						System.out.println("X: " + xaxis);
						
						
						yaxis = rawValuesOnly.substring(rawValuesOnly.indexOf(",") + 1, rawValuesOnly.indexOf("\""));
						
						
//						System.out.println("Y: " + yaxis);					
						
						camera.putNumber("angle", Double.parseDouble(xaxis));
						camera.putNumber("depth", Double.parseDouble(yaxis));

//						System.out.println("DEPTH :    " + camera.getNumber("depth", 0.0));
//						System.out.println("ANGLE :    " + camera.getNumber("angle", 0.0));
					}
					
					
					
//					camera.putString("x", xaxis);
//					camera.putString("y", yaxis);
//					System.out.println("X: " + xaxis);
//					System.out.println("Y: " + yaxis);
					

					
				}

//				System.out.println("end reSDER");
			} catch (IOException e) {
//				e.printStackTrace();
			}
			
			try {
//				System.out.println("Sleep TCP");
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
//				 TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			System.out.println("end catch");
		}

	}
}
