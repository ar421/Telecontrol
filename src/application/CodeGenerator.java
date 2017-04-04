package application;

import javafx.fxml.FXML;

public class CodeGenerator {
	public static void main(String[] args) {
		//colorgrid();
	}
	
	private static void colorgrid() {
		String x = "";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				x += "@FXML\n"
			+ "public void color"
						+ i
						+ j
						+ "(){\n\t"
						+ "StringBuffer text = new StringBuffer(colorP.getValue().toString());"
						+ "\n\t"
						+ "text.replace( 7 ,9 ,\"\");"
						+ "\n\t"
						+ "text.replace(0, 2, \"\");"
						+ "\n\t"
						+ "grid"
						+ i
						+ j
						+ ".setStyle(\"-fx-background-color: #\" + text + \";\\n\" + \"-fx-border-color: black\");" + "\n}\n";
			}
		}
		System.out.print(x);
	}
}
