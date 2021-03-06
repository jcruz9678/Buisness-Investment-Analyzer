package Entity;

public class ProfileObject
{
    private Integer id;
    private Input input;
    private Integer userId;

    public ProfileObject(Integer id, Input input, Integer userId) {
        this.id = id;
        this.input = input;
        this.userId = userId;
    }

    public ProfileObject() {
        this.id = null;
        this.input = null;
        this.userId = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
