package com.github.srwaggon.digibits.monster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/monster")
public class MonsterController {

  @Autowired
  private MonsterService monsterService;

  @GetMapping("")
  public String randomMonster() {
    return monsterService.newMonster().toString();
  }

}
