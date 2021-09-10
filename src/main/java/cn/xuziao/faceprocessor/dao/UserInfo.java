package cn.xuziao.faceprocessor.dao;

import org.springframework.stereotype.Repository;

/**
 * @author xuziao
 * @date 2021/9/10 11:00
 */

@Repository
public class UserInfo {
    private String userName;
    private String password;
    private byte[] faceInfo;
    private String email;

    public UserInfo() {
    }

    public UserInfo(String userName, String password, byte[] faceInfo, String email) {
        this.userName = userName;
        this.password = password;
        this.faceInfo = faceInfo;
        this.email = email;
    }

    public UserInfo(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getFaceInfo() {
        return faceInfo;
    }

    public void setFaceInfo(byte[] faceInfo) {
        this.faceInfo = faceInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

