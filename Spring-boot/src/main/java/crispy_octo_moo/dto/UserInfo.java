package crispy_octo_moo.dto;

public class UserInfo {
    private String userId;//LinkedIn is member_id;
    private String token;//LinkedIn is bearer token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "userId:" + getUserId() + ",token:" + getToken();
    }
}
