package client;

import java.io.IOException;
import java.util.Scanner;

import can.CanMessage;

public class Terminal {

	public static void main(String[] args) {

		int port = Client.DEFAULT_PORT;
		if (args.length > 0)
			port = Integer.valueOf(args[0]);
		try {
			Client client = new Client(port);
			new Thread(client).start();

			Scanner scanner = new Scanner(System.in);
			while (!client.isClose()) {
				String string = scanner.next();
				if (string.equalsIgnoreCase("exit")) {
					System.out.println("Closing all thread... ");
					client.close();
				}
				if (string.equalsIgnoreCase("menu")) {
					System.out.println("left_on");
					System.out.println("left_off");
					System.out.println("right_on");
					System.out.println("right_off");
					System.out.println("warning_on");
					System.out.println("warning_off");
					System.out.println("position_on");
					System.out.println("position_off");
					System.out.println("croisement_on");
					System.out.println("croisement_off");
					System.out.println("route_on");
					System.out.println("route_off");
					System.out.println("stop_on");
					System.out.println("stop_off");
					System.out.println("back_on");
					System.out.println("back_off");
				}
				if (string.equalsIgnoreCase("left_on")) {
					client.sendCanMessage(new CanMessage(CanMessage.RIGHT, CanMessage.CLIGNO_OFF, 0x0));
					client.sendCanMessage(new CanMessage(CanMessage.LEFT, CanMessage.CLIGNO_ON, 0x0));
				}
				if (string.equalsIgnoreCase("left_off"))
					client.sendCanMessage(new CanMessage(CanMessage.ALL, CanMessage.CLIGNO_OFF, 0x0));
				if (string.equalsIgnoreCase("right_on")) {
					client.sendCanMessage(new CanMessage(CanMessage.LEFT, CanMessage.CLIGNO_OFF, 0x0));
					client.sendCanMessage(new CanMessage(CanMessage.RIGHT, CanMessage.CLIGNO_ON, 0x0));
				}
				if (string.equalsIgnoreCase("right_off"))
					client.sendCanMessage(new CanMessage(CanMessage.ALL, CanMessage.CLIGNO_OFF, 0x0));
				if (string.equalsIgnoreCase("warning_on"))
					client.sendCanMessage(new CanMessage(CanMessage.ALL, CanMessage.CLIGNO_ON, 0x0));
				if (string.equalsIgnoreCase("warning_off"))
					client.sendCanMessage(new CanMessage(CanMessage.ALL, CanMessage.CLIGNO_OFF, 0x0));
				if (string.equalsIgnoreCase("position_on"))
					client.sendCanMessage(new CanMessage(CanMessage.ALL, CanMessage.POS_OFF, 0x0));
				if (string.equalsIgnoreCase("position_off"))
					client.sendCanMessage(new CanMessage(CanMessage.ALL, CanMessage.POS_OFF, 0x0));
				if (string.equalsIgnoreCase("croisement_on")) {
					client.sendCanMessage(new CanMessage(CanMessage.ALL, CanMessage.POS_ON, 0x0));
					client.sendCanMessage(new CanMessage(CanMessage.FRONT, CanMessage.CROIS_ON, 0x0));
				}
				if (string.equalsIgnoreCase("croisement_off")) {
					client.sendCanMessage(new CanMessage(CanMessage.ALL, CanMessage.POS_OFF, 0x0));
					client.sendCanMessage(new CanMessage(CanMessage.FRONT, CanMessage.CROIS_OFF, 0x0));
				}
				if (string.equalsIgnoreCase("route_on"))
					client.sendCanMessage(new CanMessage(CanMessage.FRONT, CanMessage.PHARE_ON, 0x0));
				if (string.equalsIgnoreCase("route_off"))
					client.sendCanMessage(new CanMessage(CanMessage.FRONT, CanMessage.PHARE_OFF, 0x0));
				if (string.equalsIgnoreCase("stop_on"))
					client.sendCanMessage(new CanMessage(CanMessage.REAR, CanMessage.STOP_ON, 0x0));
				if (string.equalsIgnoreCase("stop_off"))
					client.sendCanMessage(new CanMessage(CanMessage.REAR, CanMessage.STOP_OFF, 0x0));
				if (string.equalsIgnoreCase("back_on"))
					client.sendCanMessage(new CanMessage(CanMessage.REAR, CanMessage.RECULE_ON, 0x0));
				if (string.equalsIgnoreCase("back_off"))
					client.sendCanMessage(new CanMessage(CanMessage.REAR, CanMessage.RECULE_OFF, 0x0));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Terminal has been terminated");
	}

}
