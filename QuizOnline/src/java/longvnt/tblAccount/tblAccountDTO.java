/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvnt.tblAccount;

/**
 *
 * @author PC
 */
public class tblAccountDTO {
    private String email;
    private String password;
    private String name;
    private boolean isadmin;
    private boolean status;

    public tblAccountDTO() {
    }

    public tblAccountDTO(String email, String password, String name, boolean isadmin, boolean status) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.isadmin = isadmin;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsadmin() {
        return isadmin;
    }

    public void setIsadmin(boolean isadmin) {
        this.isadmin = isadmin;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
}
