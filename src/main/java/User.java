
public class User {
    private String email;
    private String username;
    private String password;

    public User (String email, String username, String password) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User (String username, String password) {
        this.password = password;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
