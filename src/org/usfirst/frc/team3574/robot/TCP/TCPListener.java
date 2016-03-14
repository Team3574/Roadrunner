package org.usfirst.frc.team3574.robot.TCP;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPListener extends Thread {
	private int listenPort;

	// A TCP Socket Connection for RoboRio
	private ServerSocket listener = null;

	// TCP Socket Stream connection
	private Socket client = null;

	public TCPListener(int port) {
		this.listenPort = port;
	}

	public void run() {
		System.out.println("new thread");
		
		try {
			listener = new ServerSocket(listenPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (true) {

		// wait for a client to connect, this blocks the current thread until a connect
		// is made
		System.out.println("Listening on port: " + listener.getLocalPort());
		try {
			client = listener.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//For the input stream, try the following:
		try {
			DataInputStream is = new DataInputStream(client.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			int data;
			while((data = reader.read()) != -1) {
				System.out.println(data);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		}

	}
}
