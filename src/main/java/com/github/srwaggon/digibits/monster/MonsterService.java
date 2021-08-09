package com.github.srwaggon.digibits.monster;

import com.github.srwaggon.digibits.player.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MonsterService {

  @Autowired
  private MonsterRepository monsterRepository;

  public Monster newMonster() {
    return new Monster(UUID.randomUUID(), randomName());
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

  public Monster getMonster(UUID monsterId) {
    return monsterRepository.findById(monsterId)
        .orElseThrow(() -> new RuntimeException("Monster not found with id " + monsterId));
  }

  public List<Monster> getMonsters(Player player) {
    return player.getMonsters().stream()
        .map(this::getMonster)
        .collect(Collectors.toList());
  }

  public Monster save(Monster monster) {
    return monsterRepository.save(monster);
  }

  public void feed(Monster monster, Player player) {
    monster.feed();
    save(monster);
  }
}
