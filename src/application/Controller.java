package application;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class Controller implements Initializable {
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@FXML
	public Button temperatureB, humidityB, pressureB, pullB, logB, sendB, updateB, connectB, disconnectB, resetB;

	@FXML
	public GridPane grids;

	@FXML
	public CheckBox rotationCB, positionCB;

	@FXML
	public ProgressBar temperaturePB, humidityPB, pressurePB;

	@FXML
	public ColorPicker colorP;

	@FXML
	public Label grid00, grid01, grid02, grid03, grid04, grid05, grid06, grid07, grid10, grid11, grid12, grid13, grid14,
			grid15, grid16, grid17, grid20, grid21, grid22, grid23, grid24, grid25, grid26, grid27, grid30, grid31,
			grid32, grid33, grid34, grid35, grid36, grid37, grid40, grid41, grid42, grid43, grid44, grid45, grid46,
			grid47, grid50, grid51, grid52, grid53, grid54, grid55, grid56, grid57, grid60, grid61, grid62, grid63,
			grid64, grid65, grid66, grid67, grid70, grid71, grid72, grid73, grid74, grid75, grid76, grid77, rotateXL,
			rotateYL, rotateZL, positionXL, positionYL, positionZL, rotateXB, rotateYB, rotateZB, positionXB,
			positionYB, positionZB;

	@FXML
	public void color00() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid00.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color01() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid01.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color02() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid02.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color03() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid03.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color04() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid04.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color05() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid05.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color06() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid06.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color07() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid07.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color10() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid10.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color11() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid11.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color12() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid12.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color13() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid13.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color14() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid14.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color15() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid15.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color16() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid16.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color17() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid17.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color20() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid20.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color21() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid21.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color22() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid22.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color23() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid23.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color24() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid24.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color25() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid25.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color26() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid26.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color27() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid27.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color30() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid30.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color31() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid31.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color32() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid32.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color33() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid33.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color34() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid34.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color35() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid35.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color36() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid36.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color37() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid37.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color40() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid40.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color41() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid41.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color42() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid42.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color43() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid43.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color44() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid44.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color45() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid45.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color46() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid46.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color47() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid47.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color50() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid50.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color51() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid51.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color52() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid52.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color53() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid53.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color54() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid54.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color55() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid55.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color56() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid56.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color57() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid57.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color60() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid60.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color61() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid61.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color62() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid62.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color63() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid63.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color64() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid64.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color65() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid65.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color66() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid66.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color67() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid67.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color70() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid70.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color71() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid71.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color72() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid72.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color73() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid73.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color74() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid74.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color75() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid75.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color76() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid76.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void color77() {
		StringBuffer text = new StringBuffer(colorP.getValue().toString());
		text.replace(7, 9, "");
		text.replace(0, 2, "");
		grid77.setStyle("-fx-background-color: #" + text + ";\n" + "-fx-border-color: black");
	}

	@FXML
	public void pressTemp() {
		String style = this.temperatureB.getStyle();
		if (style.contains("-fx-border-color: green")) {
			this.temperatureB.setStyle(style.replaceAll("-fx-border-color: green", "").replaceAll(";;", ";"));
		} else {
			this.temperatureB.setStyle(style.concat(";-fx-border-color: green"));
		}
		// TODO switch desirability
	}

	@FXML
	public void pressHumi() {
		String style = this.humidityB.getStyle();
		if (style.contains("-fx-border-color: red")) {
			this.humidityB.setStyle(style.replaceAll("-fx-border-color: red", "").replaceAll(";;", ";"));
		} else {
			this.humidityB.setStyle(style.concat(";-fx-border-color: red"));
		}
		// TODO set as desirable
	}

	@FXML
	public void pressPres() {
		String style = this.pressureB.getStyle();
		if (style.contains("-fx-border-color: yellow")) {
			this.pressureB.setStyle(style.replaceAll("-fx-border-color: yellow", "").replaceAll(";;", ";"));
		} else {
			this.pressureB.setStyle(style.concat(";-fx-border-color: yellow"));
		}
		// TODO set as desirable
	}

	@FXML
	public void pressReset() {
		Node[] arr = new Node[] { temperatureB, humidityB, pressureB };

		for (Node button : arr) {
			button.setStyle("");
		}
		this.positionCB.setSelected(false);
		this.rotationCB.setSelected(false);

		for (Node label : grids.getChildren()) {
			label.setStyle("-fx-background-color: white; -fx-border-color: black");
		}
	}

	@FXML
	public void pressConnect() {
		try {
			System.setIn(new ByteArrayInputStream("connect".getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		this.connectB.setDisable(true);
		this.disconnectB.setDisable(false);
		this.sendB.setDisable(false);
	}

	@FXML
	public void pressDisconnect() {
		this.disconnectB.setDisable(true);
		this.connectB.setDisable(false);
		this.sendB.setDisable(true);
		this.pullB.setDisable(true);
		this.logB.setDisable(true);
	}

	@FXML
	public void pressSend() {
		this.logB.setDisable(false);
		this.updateB.setDisable(false);
	}

	@FXML
	public void pressUpdate() {
		this.updateB.setDisable(true);
	}

	@FXML
	public void pressLog() {
		this.logB.setDisable(true);
		this.pullB.setDisable(false);
	}

	@FXML
	public void pressPull() {
		this.pullB.setDisable(true);
		this.logB.setDisable(false);
		this.updateB.setDisable(false);
	}
}