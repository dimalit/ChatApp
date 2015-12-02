import java.sql.Connection;
import java.sql.*;
import java.util.*;

public class ServerConnection {
    private String serverAddress;
    private Connection connection;
    private Statement statement;

    public ServerConnection() {

    }

    public ServerConnection(String sAdress) {
        this(sAdress, null);
    }

    public ServerConnection(String sAdress, String nickname) {
        if (nickname != null)
            setLocalNick(nickname);
        if (sAdress != null) {
            setServerAddress(sAdress);
            ConnectToServer();
        }
    }

    public void ConnectToServer() {
        if (isConnected())
            return;
        assert (serverAddress != null && !serverAddress.trim().isEmpty());
        try {
            connection = DriverManager.getConnection(serverAddress, "user", "password");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DisconnectFromServer() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }

    public void goOnline() {
        goOnline(28411);
    }

    public void goOnline(int port) {
        assert Protocol.localNick != null;
        assert isConnected();
        String s = "INSERT INTO user (nick, ip, online, port) values ('" + Protocol.localNick
                + "', SUBSTRING_INDEX(USER(),'@',-1), 1,'" + port + "');";
        try {
            statement.executeUpdate(s);
        } catch (SQLException sqlexception) {
            boolean nick_impact = true;
            try {
                if (getIp(Protocol.localNick) != null) {
                    s = "UPDATE user set ip=SUBSTRING_INDEX(USER(),'@',-1), online=1, port=" + port + " WHERE nick='"
                            + Protocol.localNick + "';";
                    nick_impact = true;
                }
                else {
                    s = "UPDATE user set nick='" + Protocol.localNick + "', online=1 WHERE ip=SUBSTRING_INDEX(USER(),'@',-1) AND port="
                            + port + ";";
                    nick_impact = false;
                }
                statement.executeUpdate(s);
            } catch (SQLException sqlexception1) {
                try {
                    if (nick_impact)
                        statement.execute("DELETE FROM user WHERE ip=SUBSTRING_INDEX(USER(),'@',-1) AND port=" + port + ";");
                    else statement.execute("DELETE FROM user WHERE nick='" + Protocol.localNick + "';");
                    statement.execute(s);
                } catch (SQLException sqlexception2) {
                    sqlexception2.printStackTrace();
                }
                sqlexception1.printStackTrace();
            }
        }
    }

    public void goOffline() {
        assert Protocol.localNick != null;
        assert isConnected();
        try {
            statement.execute("INSERT INTO user (nick, ip, online) values ('" + Protocol.localNick
                    + "', SUBSTRING_INDEX(USER(),'@',-1), 0) ON DUPLICATE KEY UPDATE ip=VALUES(ip), online=VALUES(online);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setServerAddress(String serverAddress) {
        if (serverAddress.equals(this.serverAddress))
            return;
        boolean connected = isConnected();
        if (connected) {
            goOffline();
            DisconnectFromServer();
        }
        this.serverAddress = serverAddress;
        if (connected)
            ConnectToServer();
    }

    public void setLocalNick(String newNick) {
        assert newNick != null;
        newNick = ChangeNick(newNick);
        if (newNick.equals(Protocol.localNick))
            return;
        boolean online = false;
        if (isConnected()) {
            online = isNickOnline(Protocol.localNick);
            if (online)
                goOffline();
        }
        Protocol.localNick = newNick;
        if (online)
            goOnline();
    }

    public String getIp(String nickname) {
        assert isConnected();
        assert nickname != null;
        nickname = ChangeNick(nickname);
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery("SELECT ip FROM user WHERE nick='" + nickname + "'");
            if (!resultSet.next())
                return null;
            return resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getPort(String nick) {
        assert isConnected();
        assert nick != null;
        nick = ChangeNick(nick);
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery("SELECT port FROM user WHERE nick='" + nick + "'");
            if (!resultSet.next())
                return 0;
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String[] getAllNicks() {
        assert isConnected();
        ResultSet resultSet;
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT nick FROM user;");
            while (resultSet.next()) {
                String nick = resultSet.getString(1);
                arrayList.add(nick);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList.toArray(new String[0]);
    }

    public boolean isConnected() {
        return statement != null;
    }

    public boolean isNickOnline(String nickname) {
        if (nickname == null)
            return false;
        assert isConnected();
        nickname = ChangeNick(nickname);
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery("SELECT online FROM user WHERE nick='" + nickname + "'");
            if (!resultSet.next())
                return false;
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String ChangeNick(String s) {
        return s.replaceAll("['\";]", "").replaceAll("\\s", "");
    }

    public static void main(String[] args) {
        String locNick = "localNick";
        String remNick = "remoteNick";
        ServerConnection serverConnection = new ServerConnection();
        serverConnection.setServerAddress("jdbc:mysql://files.litvinov.in.ua/chatapp_server?characterEncoding=utf-8&useUnicode=true");
        serverConnection.ConnectToServer();
        assert serverConnection.isConnected();
        serverConnection.setLocalNick(locNick);
        System.out.println("Before: " + serverConnection.isNickOnline(locNick));
        serverConnection.goOnline();
        System.out.println("After: " + serverConnection.isNickOnline(locNick));
        serverConnection.goOffline();
        System.out.println("After offline: " + serverConnection.isNickOnline(locNick));
        System.out.println("Another nick: " + serverConnection.isNickOnline(remNick));
        System.out.println("First ip: " + serverConnection.getIp(locNick));
        System.out.println("Second ip: " + serverConnection.getIp(remNick));
        System.out.println("First port: " + serverConnection.getPort(locNick));
        System.out.println("Second port: " + serverConnection.getPort(remNick));
        }
    }
