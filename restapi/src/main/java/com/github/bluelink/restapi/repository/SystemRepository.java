package com.github.bluelink.restapi.repository;


import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemRepository {

  @Select("select 'test'")
  Optional<String> getStringFromDatabase();
}
