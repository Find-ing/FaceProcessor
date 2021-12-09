package cn.xuziao.faceprocessor.face;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.toolkit.ImageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;


/**
 * @author xuziao
 * @date 2021/8/21 18:01
 */

@Service
public class ImageHandler {
//    String path;
    InputStream inputStream = null;

    private final FaceEngine faceEngine;
    private final EngineConfiguration engineConfiguration;
    private ImageInfo imageInfo;
    private List<FaceInfo> faceInfoList;
    final Logger log = LoggerFactory.getLogger(ImageHandler.class);



    private ImageHandler() {
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
        engineConfiguration.setDetectFaceMaxNum(3);
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceScaleVal(16);
        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(true);
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
        functionConfiguration.setSupportFaceRecognition(true);
        functionConfiguration.setSupportGender(true);
        functionConfiguration.setSupportLiveness(true);
        functionConfiguration.setSupportIRLiveness(false);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);
        this.engineConfiguration = engineConfiguration;
        this.faceEngine = new FaceEngine();
    }

//    public void setPath(String path) {
//        this.path = path;
//    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void init() {
        if (inputStream == null) {
            log.error("未设置路径");
            return;
        }
        String appId = "AYk7yFUHxrMQMKc2uL31amVSD7q7WaSUACzSxt5x2psP";
        String sdkKey = "JDxk1vyKWWcouDUzr7Md2FjXs54At14dXHtWueh91en1";
        int errorCode = faceEngine.activeOnline(appId, sdkKey);
        if (errorCode != ErrorInfo.MOK.getValue() &&
                errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue())
        {
            log.info("引擎激活失败："+errorCode);
        } else {
            log.info("引擎激活成功");
        }

        int errorCode2 = faceEngine.init(engineConfiguration);
        isAccepted(errorCode2, "引擎初始化成功", "引擎初始化失败");
        getPorecss(inputStream);
    }


    /**
     * 调用process接口
     */
    private void getPorecss(InputStream inputStream) {

        FunctionConfiguration configuration = new FunctionConfiguration();
        configuration.setSupportAge(true);
        configuration.setSupportGender(true);
        configuration.setSupportLiveness(true);
        configuration.setSupportFace3dAngle(true);
//        configuration.setSupportFaceRecognition(true);

//        imageInfo = getRGBData(new File(path));
        imageInfo = getRGBData(inputStream);

        faceInfoList = new ArrayList<>();

        int errorCode3 = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(),
                imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
        isAccepted(errorCode3, "数据传入成功", "数据传入失败");

        int errorCode4 = faceEngine.process(imageInfo.getImageData(), imageInfo.getWidth(),
                imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList, configuration);
        isAccepted(errorCode4, "process接口调用成功", "process接口调用失败");


    }

    public List<AgeInfo> getAges() {
        List<AgeInfo> ageInfoList = new ArrayList<>();
        int errorCode5 = faceEngine.getAge(ageInfoList);
        isAccepted(errorCode5, "年龄获取成功", "年龄获取失败");
        return ageInfoList;
    }

    public List<GenderInfo> getGenders() {
        List<GenderInfo> genderInfoList = new ArrayList<>();
        int errorCode6 = faceEngine.getGender(genderInfoList);
        isAccepted(errorCode6, "性别获取成功", "性别获取失败");
        return genderInfoList;
    }


    public List<FaceFeature> getFaceFeature() {

        List<FaceFeature> facesFeatureList = new ArrayList<>();
        int errorCode7 = 0;
        for (FaceInfo faceInfo : faceInfoList) {
            FaceFeature faceFeature = new FaceFeature();
             errorCode7 = faceEngine.extractFaceFeature(imageInfo.getImageData(),
                    imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(),
                    faceInfo, faceFeature);
            facesFeatureList.add(faceFeature);
        }
        isAccepted(errorCode7, "面部特征值获取成功", "面部特征值获取失败");
        return facesFeatureList;
    }

    public FaceSimilar getFaceSimilar(FaceFeature faceFeature1, FaceFeature faceFeature2) {
        FaceSimilar faceSimilar = new FaceSimilar();
        int errorCode7 = faceEngine.compareFaceFeature(faceFeature1, faceFeature2, faceSimilar);
        isAccepted(errorCode7, "面部数据比对成功", "面部数据比对失败");
        return faceSimilar;
    }

    public double getFaceSimilarRate(byte[] faceInfo1, byte[] faceInfo2) {
        FaceFeature faceFeature1 = new FaceFeature(faceInfo1);
        FaceFeature faceFeature2 = new FaceFeature(faceInfo2);
        return getFaceSimilar(faceFeature1, faceFeature2).getScore();
    }



    private void isAccepted(int errorCode, String success, String failure) {
        if (errorCode == ErrorInfo.MOK.getValue()){
            log.info(success);
        } else {
            log.info(failure+"，错误码为："+errorCode);
        }
    }

}
