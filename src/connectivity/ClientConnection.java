package connectivity;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientConnection extends Socket {
	
	public ClientConnection(InetAddress IP, int port) throws IOException {
		super(IP, port);
	}
}
