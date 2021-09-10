package cn.xuziao.faceprocessor.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author xuziao
 * @date 2021/9/9 12:57
 */
@Configuration
public class DruidDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource () {
        return new DruidDataSource();
    }
}
