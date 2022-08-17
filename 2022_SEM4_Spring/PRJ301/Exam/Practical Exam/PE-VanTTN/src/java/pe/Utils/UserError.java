/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.Utils;

/**
 *
 * @author Admin
 */
public class UserError {

    private String userIDError;
    private String fullnameError;
    private String passwordError;
    private String roleIDError;
    private String statusError;
    private String confirmError;

    public UserError() {
        this.userIDError = "";
        this.fullnameError = "";
        this.passwordError = "";
        this.roleIDError = "";
        this.statusError = "";
        this.confirmError = "";
    }

    public UserError(String userIDError, String fullnameError, String passwordError, String roleIDError, String statusError, String confirmError) {
        this.userIDError = userIDError;
        this.fullnameError = fullnameError;
        this.passwordError = passwordError;
        this.roleIDError = roleIDError;
        this.statusError = statusError;
        this.confirmError = confirmError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getFullnameError() {
        return fullnameError;
    }

    public void setFullnameError(String fullnameError) {
        this.fullnameError = fullnameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    @Override
    public String toString() {
        return "UserError{" + "userIDError=" + userIDError + ", fullnameError=" + fullnameError + ", passwordError=" + passwordError + ", roleIDError=" + roleIDError + ", statusError=" + statusError + ", confirmError=" + confirmError + '}';
    }

}
