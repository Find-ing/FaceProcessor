package cn.xuziao.faceprocessor.dao.imple;

import cn.xuziao.faceprocessor.dao.ReturnInfo;
import cn.xuziao.faceprocessor.dao.UserInfo;
import cn.xuziao.faceprocessor.dao.UserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @author xuziao
 * @date 2021/9/10 12:51
 */

@Repository
public class UserInfoDAOImpl implements UserInfoDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserInfoDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public ReturnInfo addUser(UserInfo userInfo) {

        String sql = "insert into baseInfo(userName, password, email) VALUES (?, ?, ?)";
        Object[] objects = new Object[]{
                userInfo.getUserName(),
                userInfo.getPassword(),
                userInfo.getEmail()
        };
        try {
            jdbcTemplate.update(sql, objects);
        } catch (Exception e){
            e.printStackTrace();
            return ReturnInfo.OTHERS;
        }

        return ReturnInfo.OK;
    }

    @Override
    public ReturnInfo alertUser(UserInfo userInfo) {

        String sql = "update baseInfo set password=?, email=? where userName=?";

        Object[] objects = new Object[]{
                userInfo.getPassword(),
                userInfo.getEmail(),
                userInfo.getUserName()
        };
        try {
            jdbcTemplate.update(sql, objects);
        } catch (Exception e){
            e.printStackTrace();
            return ReturnInfo.OTHERS;
        }
        return ReturnInfo.OK;
    }

    @Override
    public ReturnInfo delUser(String userName) {
        String sql = "delete from baseInfo where userName = ?";
        try {
            jdbcTemplate.update(sql, userName);
        } catch (Exception e){
            e.printStackTrace();
            return ReturnInfo.OTHERS;
        }

        return ReturnInfo.OK;
    }

    @Override
    public ReturnInfo addFaceInfo(String userName, byte[] faceInfo) {
        String sql = "update baseInfo set faceInfo=? where userName=?";

        try {
            jdbcTemplate.update(sql, faceInfo, userName);
        } catch (Exception e){
            e.printStackTrace();
            return ReturnInfo.OTHERS;
        }

        return ReturnInfo.OK;
    }

    @Override
    public ReturnInfo hasResigned(String userName, String password) {

        String passwordInDatabase;
        String sql = "select password from baseinfo where userName=?";

        try {
            passwordInDatabase = jdbcTemplate.queryForObject(sql, String.class, userName);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return ReturnInfo.USER_NOT_FOUND;
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnInfo.OTHERS;
        }

        if (password.equals(passwordInDatabase)) {
            return ReturnInfo.OK;
        } else {
            return ReturnInfo.PASSWORD_NOT_MATCH;
        }
    }

    @Override
    public Object faceLogin(byte[] faceInfo) {

        String sql = "select faceInfo from baseinfo";

        List<byte[]> faceInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(byte[].class));

        for (byte[] bytes : faceInfoList) {

        }

        return null;
    }

}
