public abstract class CommandObserver{

    protected CommandListenerThread clt;

    public void update(Command command) {

    }

    CommandType getType() {
        return null;
    }
}
