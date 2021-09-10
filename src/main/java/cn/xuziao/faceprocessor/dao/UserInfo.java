package cn.xuziao.faceprocessor.dao;

import org.springframework.stereotype.Repository;

import java.util.Arrays;

/**
 * @author xuziao
 * @date 2021/9/10 11:00
 */

@Repository
public class UserInfo {
    private String username;
    private String password;
    private byte[] faceInfo;
    private String email;

    public UserInfo() {
    }

    public UserInfo(String username, String password, byte[] faceInfo, String email) {
        this.username = username;
        this.password = password;
        this.faceInfo = faceInfo;
        this.email = email;
    }

    public UserInfo(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", faceInfo=" + Arrays.toString(faceInfo) +
                ", email='" + email + '\'' +
                '}';
    }
}

