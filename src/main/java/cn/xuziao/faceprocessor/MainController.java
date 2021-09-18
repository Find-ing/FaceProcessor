package cn.xuziao.faceprocessor;

import cn.xuziao.faceprocessor.dao.UserInfo;
import cn.xuziao.faceprocessor.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuziao
 * @date 2021/9/9 21:58
 */

@Controller
@RestController
@Slf4j
public class MainController {

    private final UserInfoService userInfoService;

    @Autowired
    public MainController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
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


}
