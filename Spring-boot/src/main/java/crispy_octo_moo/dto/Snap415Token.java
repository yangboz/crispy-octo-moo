package crispy_octo_moo.dto;


public class Snap415Token {
    private String id;//LinkedIn is member_id;
    private String token;//LinkedIn is bearer token;
    private String provider;//faceboo,linkedin...

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "provider:" + getProvider() + ",id:" + getId() + ",token:" + getToken();
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}