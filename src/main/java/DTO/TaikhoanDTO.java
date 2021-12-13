
package DTO;

/**
 *
 * @author xuannguyen
 */
public class TaikhoanDTO {
    private String maTK, password, role;

    public TaikhoanDTO(){
        this.maTK = "";
        this.password = "";
        this.role = "";
    }
    public TaikhoanDTO(String maTK, String password, String role) {
        this.maTK = maTK;
        this.password = password;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getMaTK() {
        return maTK;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
