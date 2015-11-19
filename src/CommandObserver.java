
public abstract class CommandObserver {
    protected CommandListenerThread commandListenerThread;

    public void update(Connection connection) {

    }

    CommandTypes getType() {
        return null;
    }
}
