package com.github.bluelink.restapi.service;

import com.github.bluelink.restapi.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SystemService {

  @Autowired
  private SystemRepository systemRepository;

  public Optional<String> getStringFromDatabase(){
    return systemRepository.getStringFromDatabase();
  }
}
