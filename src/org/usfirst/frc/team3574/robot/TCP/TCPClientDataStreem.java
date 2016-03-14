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
	}

	public void run() {
		System.out.println("new thread");
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
//			System.out.println("connecting to port: " + this.serverPort);
			try {
				client = new Socket(serverIp, serverPort);
				DataInputStream is = new DataInputStream(client.getInputStream());
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String data;
				System.out.println("connectedData");
				while((data = reader.readLine()) != null) {
					camera.putString("depth", data);
					System.out.println(data);
				}

//				System.out.println("end reSDER");
			} catch (IOException e) {
//				e.printStackTrace();
			}

//			System.out.println("end catch");
		}

	}
}
