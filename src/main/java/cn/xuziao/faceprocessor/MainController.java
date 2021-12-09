package cn.xuziao.faceprocessor;

import cn.xuziao.faceprocessor.dao.ReturnInfo;
import cn.xuziao.faceprocessor.dao.UserInfo;
import cn.xuziao.faceprocessor.face.ImageHandler;
import cn.xuziao.faceprocessor.service.FaceService;
import cn.xuziao.faceprocessor.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author xuziao
 * @date 2021/9/9 21:58
 */

@Controller
@RestController
@Slf4j
public class MainController {

    private final UserInfoService userInfoService;
    private final FaceService faceService;

    @Autowired
    public MainController(UserInfoService userInfoService, ImageHandler imageHandler, FaceService faceService) {
        this.userInfoService = userInfoService;
        this.faceService = faceService;
    }

    @RequestMapping("/test")
    public String testAction () {
        return "Test Success!";
    }

    @RequestMapping(value = "/resign/{username}/{password}/{email}")
    public String resign(UserInfo userInfo) {
        return userInfoService.resignService(userInfo);
    }

    @RequestMapping(value = "/login/{username}/{password}")
    public String login(@PathVariable String password, @PathVariable String username) {
        return userInfoService.loginService(username, password);
    }

    @RequestMapping(value = "sendCode/{email}/{code}")
    public String sendCode(@PathVariable String code, @PathVariable String email){
        return userInfoService.sendCodeService(code, email);
    }

    @PostMapping(value = "FaceAdd")
    public String upload(@RequestPart("photo") MultipartFile photo, @RequestPart("name") String name) {
        log.info("图片大小{}", photo.getSize());
        ReturnInfo returnInfo = null;
        try {
            returnInfo = faceService.addFaceInfo(name, photo.getInputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        assert returnInfo != null;
        return String.valueOf(returnInfo.getCode());
    }

    @PostMapping(value = "FaceLogin")
    public String upload(@RequestPart("photo") MultipartFile photo) {
        log.info("图片大小{}", photo.getSize());
        Object obj = null;
        try {
            obj = faceService.FaceLoginService(photo.getInputStream());
            return (String) obj;
        } catch (IOException e){
            return String.valueOf(ReturnInfo.OTHERS.getCode());
        } catch (ClassCastException classCastException) {
            assert obj != null;
            return String.valueOf(((ReturnInfo) obj).getCode());
        }
    }

    @GetMapping(value = "queryErrorCode/{errorCode}")
    public String getInfo(@PathVariable int errorCode){
        for (ReturnInfo info: ReturnInfo.values()){
            if (info.getCode() == errorCode){
                return info.getStatus();
            }
        }
        return "传入代码错误\nERROR CODE!\n";
    }

}
