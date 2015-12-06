import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JTextArea;

public class Friends extends MainForm{
	private static JTextArea textRLogin;
	private static JTextArea textRAddr;
	private File file = new File("Friends.txt");
	private String[][] frendmass = new String[1000][2];
	private Integer size = 0;
	
	public Friends(JTextArea textRLogin,JTextArea textRAddr) throws FileNotFoundException {
		super();
	}
	public void addFriends() {
		if (textRLogin.getText() != "") {
			frendmass[size][0] = textRLogin.getText();
			frendmass[size][1] = textRAddr.getText();
			size++;
		}
	}

	public void readFriends() throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		Scanner reader = new Scanner(file);
		while (reader.hasNext()) {
			frendmass[size][0] = reader.next();
			frendmass[size][1] = reader.next();
			size++;

		}
	}

	public void writeFriends() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(file);
		for (int i = 0; i < size; i++) {
			writer.print(frendmass[i][0] + " ");
			writer.print(frendmass[i][1] + " ");
			writer.flush();
		}
		writer.close();
	}

}
