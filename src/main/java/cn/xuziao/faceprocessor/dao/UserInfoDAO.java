package cn.xuziao.faceprocessor.dao;

/**
 * @author xuziao
 * @date 2021/9/10 11:10
 */

public interface UserInfoDAO {

   /**
    * 增加用户
    * @param userInfo 用户信息
    * @return 返回执行结果
    */
   ReturnInfo addUser(UserInfo userInfo);

   /**
    * 修改用户信息
    * @param userInfo 修改后的用户信息
    * @return 返回执行结果
    */
   ReturnInfo alertUser(UserInfo userInfo);

   /**
    * 删除用户
    * @param userName 要删除用户的用户名
    * @return 返回执行结果
    */
   ReturnInfo delUser(String userName);

    /**
     * 增加面部数据
     * @param userName 要增加面部数据的用户
     * @param faceInfo 面部数据
     * @return 返回执行结果
     */
   ReturnInfo addFaceInfo(String userName, byte[] faceInfo);

    /**
     * 账号密码登录
     * @param userName 要登陆的用户名
     * @param password 该用户名对应的密码
     * @return 返回执行结果
     */
   ReturnInfo hasResigned(String userName, String password);

    /**
     * 面部登录
     * @param faceInfo 采集到的面部信息
     * @return 如果返回的是ReturnInfo.FACE_INFO_NOT_FOUND证明该面部不存在
     */
   Object faceLogin(byte[] faceInfo);


}
