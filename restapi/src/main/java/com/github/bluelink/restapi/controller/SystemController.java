package com.github.bluelink.restapi.controller;

import com.github.bluelink.restapi.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SystemController {

  @Autowired
  private SystemService systemService;

  @GetMapping("/dbCheck")
  public @ResponseBody Map<String, String> getStringFromDatabase(){
    Map<String, String> result = new HashMap<>();
    result.put("date", LocalDateTime.now().toString());
    result.put("check", systemService.getStringFromDatabase().orElse("null"));
    return result;
  }
}
