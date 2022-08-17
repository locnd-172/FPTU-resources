package pe.DTO;

/**
 *
 * @author Admin
 */
public class User {

    private String userID;
    private String fullname;
    private String password;
    private int roleID;
    private int status;

    public User() {
    }

    public User(String userID, String fullname, String password, int roleID, int status) {
        this.userID = userID;
        this.fullname = fullname;
        this.password = password;
        this.roleID = roleID;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", fullname=" + fullname + ", password=" + password + ", roleID=" + roleID + ", status=" + status + '}';
    }

}
