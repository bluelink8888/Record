package com.github.bluelink.restapi;

import com.github.bluelink.restapi.config.TestMysqlDatasource;
import com.github.bluelink.restapi.controller.SystemController;
import com.github.bluelink.restapi.repository.SystemRepository;
import com.github.bluelink.restapi.service.SystemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

/**
 * Spring boot basic test and datasource test
 */
@SpringBootTest
@Import(TestMysqlDatasource.class)
class RestapiApplicationTests {

  @Autowired
  private SystemRepository systemRepository;

  @Autowired
  private SystemService systemService;

  @Autowired
  private SystemController systemController;

  @Test
  void contextLoads() {
  }

  /**
   * Here start basic test case in this application
   */

  // This test make sure mybatis initial instance success
  @Test
  void testRepositoryIsExist() {
    assertThat(systemRepository).isNotNull();
  }

  // This test make sure repository will return expect result, Optional String test is expect value.
  @Test
  void testDatasoruceIsConnected() {
    assertThat(systemRepository.getStringFromDatabase()).isEqualTo(Optional.of("test"));
  }

  // This test make sure service layer will return same result like repository.
  @Test
  void testSystemServiceGetStringFromDatabaseIsEqualToTest() {
    assertThat(systemService.getStringFromDatabase()).isEqualTo(Optional.of("test"));
  }

  // This test make sure controller return value contain expect value.
  @Test
  void testSystemControllerGetStringFromDatabase() {
    assertThat(systemController.getStringFromDatabase()).containsValues("test");
  }
}