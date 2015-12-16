import java.util.*;

import javax.swing.*;

public class HistoryView extends JTextArea implements Observer {

	private HistoryModel model;
	private int messageCount;

	HistoryView() {
		HistoryModel model = new HistoryModel();
		this.messageCount = 0;
	}

	HistoryView(HistoryModel model) {
		this.model = model;
	}

	HistoryModel getModel() {
		return model;
	}

	void setModel(HistoryModel model) {
		this.model = model;
		// messageCount=model.getSize();
	}

	public void update(Observable m, Object arg1) {
		if (model.getSize() == 0) {
			this.setText("");
		} else {
			refresh();
			repaint();
		}
	}

	void refresh() {
		if (messageCount != model.getSize()) {
			for (int i = (messageCount); i < model.getSize(); i++) {
				append(model.getMessage(i).toString());
			}
			messageCount = model.getSize();
		}
	}

}
