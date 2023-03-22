public class UserLoginParams {
    private String uid;
    private String accessToken;

    public UserLoginParams(String uid, String accessToken) {
        this.accessToken = accessToken;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
