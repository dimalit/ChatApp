import java.util.*;

import javax.swing.*;

public class HistoryView extends JTextArea implements Observer{

private HistoryModel model;
private int messageCount;
HistoryView()
{
	
}
HistoryView(HistoryModel model)
{
	this.model=model;
}
HistoryModel getModel()
{
	return model;
}
void setModel(HistoryModel model)
{
	this.model=model;
	//messageCount=model.getSize();
}

	public void update(Observable arg0, Object arg1) {
		if( model.getSize()==0)
		{
			this.setText("");
			refresh();
		}
	}
	//TODO
	void refresh()
	{
	
	}

}
