package cn.xuziao.faceprocessor;

import cn.xuziao.faceprocessor.dao.ReturnInfo;
import cn.xuziao.faceprocessor.dao.UserInfo;
import cn.xuziao.faceprocessor.dao.imple.UserInfoDAOImpl;
import cn.xuziao.faceprocessor.tools.PasswordEncryption;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
class FaceProcessorApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserInfoDAOImpl userInfoDAO;

    @Autowired
    PasswordEncryption passwordEncryption;


    @Test
    void contextLoads() {
        ReturnInfo returnInfo = null;
        log.info("事务驱动：{}",jdbcTemplate.getClass());
        log.info("数据源信息：{}",dataSource.getClass());
//        userInfoDAO.addUser(new UserInfo("test1Usernamm", "81dc9bdb52d04dc20036dbd8313ed055", "88@169.com"));
        //returnInfo = userInfoDAO.alertUser(new UserInfo("test1Usernamm", "PaSsword", "777@qq.com"));
//        log.info("执行状态：{}", returnInfo.getStatus());
        log.info("MD5加密1234后的密文:{}", passwordEncryption.md5Encryption("1234"));
        log.info("SHA-1加密1234后的密文:{}", passwordEncryption.sha1Encryption("1234"));
        //returnInfo = userInfoDAO.delUser("test1Usernamm");
//        log.info("删除用户的执行状态：{}", returnInfo.getStatus());
//        returnInfo = userInfoDAO.hasResigned("test2Usernamm", passwordEncryption.md5Encryption("12434"));
        returnInfo = userInfoDAO.addUser(new UserInfo("lsr", "123", "123"));
        log.info("用户登录信息测试：{}", returnInfo.getStatus());
    }

}
