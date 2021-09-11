package cn.xuziao.faceprocessor.service;

import cn.xuziao.faceprocessor.dao.ReturnInfo;
import cn.xuziao.faceprocessor.dao.UserInfo;
import cn.xuziao.faceprocessor.dao.imple.UserInfoDAOImpl;
import cn.xuziao.faceprocessor.email.SendIdentifyingCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author xuziao
 * @date 2021/9/11 11:15
 */

@Service
public class UserInfoService {
    private UserInfoDAOImpl userInfoDAOImpl;

    @Autowired
    public UserInfoService(UserInfoDAOImpl userInfoDAOImpl) {
        this.userInfoDAOImpl = userInfoDAOImpl;
    }

    public String resignService(UserInfo userInfo) {
        ReturnInfo returnInfo = userInfoDAOImpl.addUser(userInfo);
        return String.valueOf(returnInfo.getCode());
    }

    public String loginService(String username, String password) {
        ReturnInfo returnInfo = userInfoDAOImpl.hasResigned(username, password);
        return String.valueOf(returnInfo.getCode());
    }
}
