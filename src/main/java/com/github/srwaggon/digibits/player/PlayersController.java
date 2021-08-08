package com.github.srwaggon.digibits.player;


import com.github.srwaggon.digibits.util.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/players")
public class PlayersController {

  @Autowired
  private PlayersService playersService;

  @Autowired
  private Repository<Player, String> playerRepository;

  @GetMapping
  public List<Player> getAllPlayers() {
    return playerRepository.findAll();
  }

  @PostMapping
  public Player newPlayer(@RequestBody Player player) {
    player.setId(UUID.randomUUID().toString());
    return playerRepository.save(player);
  }

  @GetMapping("/{id}")
  public Player getPlayerById(String id) {
    return playersService.getById(id);
  }

  @PutMapping("/{id}")
  public Player replacePlayer(@RequestBody Player newPlayer, @PathVariable String id) {
    newPlayer.setId(id);
    return playerRepository.save(newPlayer);
  }

  @DeleteMapping("/{id}")
  public void deletePlayer(@PathVariable String id) {
    playerRepository.findById(id)
        .ifPresent(playerRepository::delete);
  }

  @GetMapping("/current")
  public Player getCurrentPlayer() {
    return playersService.getCurrentPlayer();
  }

}
