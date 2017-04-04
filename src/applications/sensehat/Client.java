package applications.sensehat;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Stack;

import connectivity.ControllingCentre;
import converters.Converter;
import iec_60870_5_104.apdu.APDU;
import iec_60870_5_104.apdu.ASDU;

public class Client {

	public Client(String arg) {
		
	}
	
	public static void main(String[] args) {
		Client c = new Client(args[0]);
		c.launch();
	}

	@Override
	protected void send(APDU sendable) {
		try {
			OutputStream out = connection.getOutputStream();
			byte[] output = Converter.encode(sendable);
			out.write(output);
			setVs(getVs() + 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
