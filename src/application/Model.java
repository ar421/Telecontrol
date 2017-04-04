package application;

import connectivity.ControllingCentre;
import connectivity.RemoteTerminalUnit;

public class Model {
	static ControllingCentre c;
	static RemoteTerminalUnit r;
	public static void main (String[] args) {
		Thread th = createServerThread();
		th.setDaemon(true);
		th.start();
	}
	static Thread createServerThread() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				r = new RemoteTerminalUnit();
				r.launch();
			}
		};
		return new Thread(runnable);
	}
	static Thread createClientThread() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				c.launch();
			}
		};
		return new Thread(runnable);
	}
}
