package com.github.srwaggon.digibits.server;

import com.github.srwaggon.digibits.monster.Monster;
import com.github.srwaggon.digibits.monster.MonsterService;
import com.github.srwaggon.digibits.player.Player;
import com.github.srwaggon.digibits.player.PlayersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

@Controller
public class MainController {

  @Autowired
  private PlayersService playersService;

  @Autowired
  private MonsterService monsterService;

  @GetMapping("/")
  public String index(Model model) {
    Player currentPlayer = playersService.getCurrentPlayer();
    model.addAttribute("player", currentPlayer);

    List<UUID> monsterIds = currentPlayer.getMonsters();
    if (monsterIds.isEmpty()) {
      Monster monster = monsterService.newMonster();
      monsterService.save(monster);
      playersService.addMonster(currentPlayer, monster);
    }

    List<Monster> monsters = monsterService.getMonsters(currentPlayer);
    model.addAttribute("monsters", monsters);

    model.addAttribute("monsterService", monsterService);
    return "index";
  }

}
