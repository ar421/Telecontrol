package connectivity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection extends ServerSocket {
	private Socket client;

	public ServerConnection(int port) throws IOException {
		super(port);
	}

	public void launch() {
		try {
			setClient(accept());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

}
