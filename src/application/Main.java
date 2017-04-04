package application;

import connectivity.ControllingCentre;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage launcher) {
		try {
			Model.c = new ControllingCentre(new String[]{"127.0.0.1"});
			Thread th = Model.createClientThread();
			th.setDaemon(true);
			th.start();
			Parent root = FXMLLoader.load(getClass().getResource("Layout.fxml"));
			launcher.setScene(new Scene(root));
			launcher.setTitle("SenseHAT SCADA");
			launcher.initStyle(StageStyle.DECORATED);
			launcher.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
