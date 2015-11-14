import java.util.*;

public class HistoryModel extends Observable {
	private List<Message> messages;

	HistoryModel() {
		messages = new ArrayList<Message>();

	}

	void addMessage(String nick, Date date, String text) {
		HistoryModel.Message message = new HistoryModel.Message(nick, date, text);
		messages.add(message);
	}

	void addMessage(HistoryModel.Message model) {
		messages.add(model);
	}

	void clear() {
		messages.clear();
	}

	HistoryModel.Message getMessage(int pos) {
		return messages.get(pos);
	}

	int getSize() {
		return messages.size();
	}

	class Message {
		private Date date;
		private String nick, text;

		Message(String nick, Date date, String text) {
			this.nick = nick;
			this.text = text;
			this.date = date;
		}

		Date getDate() {
			return date;
		}

		String getNick() {
			return nick;
		}

		String getText() {
			return text;
		}
	}
}
