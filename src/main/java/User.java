import lombok.*;
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class User {
    private String email;
    private String username;
    private String password;

    public User (String email, String username){
        this.email = email;
        this.username = username;
    }

}
