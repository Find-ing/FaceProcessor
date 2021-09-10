package cn.xuziao.faceprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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
    @RequestMapping("/test")
    public String testAction () {
        log.info("日志打印");
        return "Test Success!";
    }

}
