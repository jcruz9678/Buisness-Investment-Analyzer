package Entity;

public class UserObject {
    private String userName;
    private String password;
    private Integer id;

    public UserObject()
    {
        this.userName = null;
        this.password = null;
    }

    public UserObject(String userName, String password, Integer id) {
        this.userName = userName;
        this.password = password;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
