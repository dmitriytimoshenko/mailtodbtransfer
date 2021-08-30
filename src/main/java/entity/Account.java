package entity;

public class Account {
    private long id_acc;
    private String login;
    private String password;
    private boolean isactive;

    public void setId_acc(long id_acc) {
        this.id_acc = id_acc;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public long getId_acc() {
        return id_acc;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isIsactive() {
        return isactive;
    }
}
