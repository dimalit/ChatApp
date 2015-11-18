public interface CommandObserver{

    void update(Command command);
    CommandType getType();
}
