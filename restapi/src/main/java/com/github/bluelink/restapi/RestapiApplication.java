package com.github.bluelink.restapi;

import com.github.bluelink.restapi.config.MysqlDatasource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},
    scanBasePackages = {"com.github.bluelink.restapi.controller",
        "com.github.bluelink.restapi.service"
    })
@Import({MysqlDatasource.class})
public class RestapiApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestapiApplication.class, args);
  }

}
