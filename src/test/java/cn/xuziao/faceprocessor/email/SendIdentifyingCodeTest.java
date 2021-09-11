package cn.xuziao.faceprocessor.email;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class SendIdentifyingCodeTest {
@Test
    public void myTests() {

      log.info(String.valueOf(new SendIdentifyingCode("750133671@qq.com", "测试")));
    }

}