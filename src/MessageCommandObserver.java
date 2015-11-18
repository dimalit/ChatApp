public class MessageCommandObserver extends CommandObserver {
    private CommandType type=CommandType.MESSAGE;
    private MessageCommand messageCommand;

    //ссылка на форму с сообщениями или на её логическую часть

    public MessageCommandObserver(CommandListenerThread clt /*,непосредственно ссылка на ту форму*/){
        //задача формы
        this.clt=clt;
        clt.addCommandObserver(this);
    }

    public void update(Command command) {
        messageCommand = (MessageCommand) command;
        //отправка на форму или лог. часть сообщения через ссылку
    }

    public CommandType getType() {
        return type;
    }
}
