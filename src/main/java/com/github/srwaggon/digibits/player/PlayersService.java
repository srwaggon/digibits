package com.github.srwaggon.digibits.player;

import com.google.common.collect.Lists;

import com.github.srwaggon.digibits.monster.Monster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

@Service
public class PlayersService {

  @Autowired
  private PlayerRepository playerRepository;

  @EventListener
  public void createPlayerForUserOnInitialLogin(AuthenticationSuccessEvent authorizedEvent) {
    DefaultOidcUser principal = (DefaultOidcUser) authorizedEvent.getAuthentication().getPrincipal();
    String sub = principal.getAttribute("sub");
    String name = principal.getAttribute("name");
    playerRepository.findById(sub)
        .orElseGet(() -> playerRepository.save(new Player(sub, name, Lists.newArrayList())));
  }

  public Player getCurrentPlayer() {
    DefaultOidcUser principal = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String sub = principal.getAttribute("sub");
    return playerRepository.findById(sub)
        .orElseThrow(this::newPlayerDoesNotExistException);
  }

  public void addMonster(Player player, Monster monster) {
    playerRepository.findById(player.getId())
        .ifPresentOrElse(foundPlayer -> {
          foundPlayer.getMonsters().add(monster.getId());
          playerRepository.save(foundPlayer);
        }, () -> {
          throw newPlayerDoesNotExistException();
        });
  }

  private RuntimeException newPlayerDoesNotExistException() {
    return new RuntimeException("No current player exists.");
  }

  public Player getById(String id) {
    return playerRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Player not found with id" + id));
  }
}
