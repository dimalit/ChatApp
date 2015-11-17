
public class NickCommand extends Command{
    private static final String nickname = "ChatApp 2015 user ";
    private static final Boolean isBusy = true;

    public NickCommand() {
    }

    public String getNickname() {
        return nickname;
    }

    public Boolean getIsBusy() {
        return isBusy;
    }
}
