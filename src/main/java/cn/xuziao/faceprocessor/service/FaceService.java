package cn.xuziao.faceprocessor.service;

import cn.xuziao.faceprocessor.dao.ReturnInfo;
import cn.xuziao.faceprocessor.dao.imple.UserInfoDAOImpl;
import cn.xuziao.faceprocessor.face.ImageHandler;
import com.arcsoft.face.FaceFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * @author xuziao
 * @date 2021/12/9 19:24
 */

@Service
public class FaceService {

    private final ImageHandler imageHandler;
    private final UserInfoDAOImpl userInfoDAOImpl;

    @Autowired
    public FaceService(ImageHandler imageHandler, UserInfoDAOImpl userInfoDAOImpl) {
        this.userInfoDAOImpl = userInfoDAOImpl;
        this.imageHandler = imageHandler;
    }
    private List<FaceFeature> getFaceFeature(InputStream inputStream) {
        imageHandler.setInputStream(inputStream);
        imageHandler.init();
        return imageHandler.getFaceFeature();
    }

    public ReturnInfo addFaceInfo(String username, InputStream inputStream) {
        List<FaceFeature> faceFeatures = getFaceFeature(inputStream);
        int countFace = faceFeatures.size();
        if (countFace > 1) {
            return ReturnInfo.TOO_MANY_FACES;
        } else if (countFace == 0){
            return ReturnInfo.USER_NOT_FOUND;
        } else {
            return userInfoDAOImpl.addFaceInfo(username, faceFeatures.get(0).getFeatureData());
        }
    }

    public Object FaceLoginService(InputStream inputStream){
        List<FaceFeature> faceFeatureList = getFaceFeature(inputStream);
        String username;
        for (FaceFeature faceFeature: faceFeatureList){
            Object obj = userInfoDAOImpl.faceLogin(faceFeature.getFeatureData());
            try {
                ReturnInfo returnInfo = (ReturnInfo)obj;
            } catch (Exception e){
                return obj;
            }

        }
        return ReturnInfo.FACE_INFO_NOT_FOUND;
    }

}
