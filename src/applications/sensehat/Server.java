package applications.sensehat;

import connectivity.RemoteTerminalUnit;

public class Server extends RemoteTerminalUnit {

	public Server() {
		super();
	}

	public static void main(String[] args) {
		Server s = new Server();
		s.launch();
	}
}
