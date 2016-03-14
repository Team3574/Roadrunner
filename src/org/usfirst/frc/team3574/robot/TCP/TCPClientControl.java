package org.usfirst.frc.team3574.robot.TCP;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPClientControl extends Thread {
	private int serverPort;
	private String serverIp;

	// TCP Socket Stream connection
	private Socket client = null;

	public TCPClientControl(String ip, int port) {
		this.serverPort = port;
		this.serverIp = ip;
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
//				client = new Socket(serverIp, serverPort);
//				DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
//				String auth = "{\"serivce\": \"visiond\", \"command\": \"auth\"}";
//				String data = "{\"command\": \"do stuffs\"}";
//				System.out.println("connected");
//				outToServer.writeBytes(auth);
//				while(true) {
//					outToServer.writeBytes(data + "\n");
//					Thread.sleep(1000);
//				}
				
				client = new Socket(serverIp, serverPort);
				
				System.out.println("connectedControl");
				DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
				DataInputStream is = new DataInputStream(client.getInputStream());
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				
				String auth = "{\"serivce\": \"visiond\", \"command\": \"auth\"}";
				String command = "{\"service\": \"goaldetect\", \"command\": \"getGoalLocation\"}";
				System.out.println("connected");
				outToServer.writeBytes(auth);
				
				String response = reader.readLine();
				System.out.println(response);
								
				

			} catch (Exception e) {
//				e.printStackTrace();
			}

//			System.out.println("end catch");
		}

	}
}
