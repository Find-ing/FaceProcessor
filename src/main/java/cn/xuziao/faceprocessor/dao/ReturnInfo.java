package cn.xuziao.faceprocessor.dao;

/**
 * @author 86188
 */

public enum ReturnInfo {
    /**
     * 返回信息
     */
    OK(0, "一切正常"),
    USER_NOT_FOUND(1, "并未找到该用户"),
    PASSWORD_NOT_MATCH(2, "密码错误"),
    FACE_INFO_NOT_FOUND(3, "找不到面部信息"),
    FACE_CAN_NOT_IDENTIFY(4 ,"检测不到面部信息"),
    USER_IS_EXISTED(5 ,"该用户名已经存在"),
    IMAGE_FACE_NOT_EXIT(6, "图片中不含有面部信息，请重新识别"),
    TOO_MANY_FACES(7, "出现太多面部信息，请保证摄像头区域内只有一张人脸"),
    OTHERS(8, "出现未知错误，请联系管理员解决");


    private final int code;
    private final String status;

    ReturnInfo(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}
