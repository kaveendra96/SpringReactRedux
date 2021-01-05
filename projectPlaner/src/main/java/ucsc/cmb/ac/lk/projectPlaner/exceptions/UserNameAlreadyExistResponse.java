package ucsc.cmb.ac.lk.projectPlaner.exceptions;

public class UserNameAlreadyExistResponse {
    private String username;

    public UserNameAlreadyExistResponse(String message) {
        this.username=message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
