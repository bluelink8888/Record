package com.github.bluelink.restapi;

import com.github.bluelink.restapi.config.TestMysqlDatasource;
import com.github.bluelink.restapi.repository.SystemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Import(TestMysqlDatasource.class)
/**
 * Spring boot default test and datasource test
 */
class RestapiApplicationTests {

  @Autowired
  private SystemRepository systemRepository;

  @Test
  void contextLoads() {
  }

  @Test
  void testRepositoryIsExist() {
    assertThat(systemRepository).isNotNull();
  }

  @Test
  void testDatasoruceIsConnected() {
    assertThat(systemRepository.getStringFromDatabase()).isEqualTo(Optional.of("test"));
  }
}