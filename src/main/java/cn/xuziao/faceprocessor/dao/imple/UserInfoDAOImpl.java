package cn.xuziao.faceprocessor.dao.imple;

import cn.xuziao.faceprocessor.dao.ReturnInfo;
import cn.xuziao.faceprocessor.dao.UserInfo;
import cn.xuziao.faceprocessor.dao.UserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

        String sql = "insert into baseInfo(username, password, email) VALUES (?, ?, ?)";
        Object[] objects = new Object[]{
                userInfo.getUsername(),
                userInfo.getPassword(),
                userInfo.getEmail()
        };
        try {
            jdbcTemplate.update(sql, objects);
        } catch (DuplicateKeyException duplicateKeyException) {
            return ReturnInfo.USER_IS_EXISTED;
        }
        catch (Exception e){
            e.printStackTrace();
            return ReturnInfo.OTHERS;
        }

        return ReturnInfo.OK;
    }

    @Override
    public ReturnInfo alertUser(UserInfo userInfo) {

        String sql = "update baseInfo set password=?, email=? where username=?";

        Object[] objects = new Object[]{
                userInfo.getPassword(),
                userInfo.getEmail(),
                userInfo.getUsername()
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
    public ReturnInfo delUser(String username) {
        String sql = "delete from baseInfo where username = ?";
        try {
            jdbcTemplate.update(sql, username);
        } catch (Exception e){
            e.printStackTrace();
            return ReturnInfo.OTHERS;
        }

        return ReturnInfo.OK;
    }

    @Override
    public ReturnInfo addFaceInfo(String username, byte[] faceInfo) {
        String sql = "update baseInfo set faceInfo=? where username=?";

        try {
            jdbcTemplate.update(sql, faceInfo, username);
        } catch (Exception e){
            e.printStackTrace();
            return ReturnInfo.OTHERS;
        }

        return ReturnInfo.OK;
    }

    @Override
    public ReturnInfo hasResigned(String username, String password) {

        String passwordInDatabase;
        String sql = "select password from baseinfo where username=?";

        try {
            passwordInDatabase = jdbcTemplate.queryForObject(sql, String.class, username);
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
