package com.github.srwaggon.digibits.application;

import com.github.srwaggon.digibits.Monster;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
public class MainController {

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/monster")
  public String randomMonster() {
    return new Monster(randomName(), UUID.randomUUID()).toString();
  }

  private String randomName() {
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();

    String name = random.ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }
}
