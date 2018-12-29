package bean;

import java.util.Date;

public class User {
    int userSystemId;
    String userTel;
    String userMail;
    String userCustomId;
    String registerDate;
    String userPassword;
    String userNickname;
    String customInfo;
    boolean isEnable;
    String userAvatar;
    Date userBirthdat;
    String userSex;

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Date getUserBirthdat() {
        return userBirthdat;
    }

    public void setUserBirthdat(Date userBirthdat) {
        this.userBirthdat = userBirthdat;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public int getUserSystemId() {
        return userSystemId;
    }

    public void setUserSystemId(int userSystemId) {
        this.userSystemId = userSystemId;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserCustomId() {
        return userCustomId;
    }

    public void setUserCustomId(String userCustomId) {
        this.userCustomId = userCustomId;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getCustomInfo() {
        return customInfo;
    }

    public void setCustomInfo(String customInfo) {
        this.customInfo = customInfo;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
