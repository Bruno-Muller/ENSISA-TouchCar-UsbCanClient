package client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import can.CanMessage;

public class Client implements Runnable {

	public static final int DEFAULT_PORT = 50885;
	private Socket socket;
	private boolean terminating;

	public Client(int port) throws UnknownHostException, IOException {
		this.socket = new Socket("192.168.125.12", port);
		this.terminating = false;
	}

	public void run() {
		System.out.println("Starting... ");
		try {
			byte bytes[] = new byte[6];
			while (this.socket.getInputStream().read(bytes,0,6) != -1) {
				System.out.println("Receive " + new CanMessage(bytes).toString());
			}

		} catch (IOException e) {
			if (!this.terminating)
				e.printStackTrace();
		}

		System.out.println("Client thread has been terminated");
	}

	public void close() throws IOException {
		this.socket.close();
		System.out.println("Client has been closed");
	}

	public boolean isClose() {
		this.terminating = true;
		return this.socket.isClosed();
	}

	public void sendCanMessage(CanMessage canMessage) {
		try {
			OutputStream outputStream = this.socket.getOutputStream();
			outputStream.write(canMessage.getBytes());
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Send" + canMessage.toString());
	}

}
