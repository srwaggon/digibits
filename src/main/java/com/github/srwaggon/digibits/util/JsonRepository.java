package com.github.srwaggon.digibits.util;

import com.google.common.collect.Lists;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class JsonRepository<T extends Identified<ID>, ID> implements Repository<T, ID> {

  private final ObjectMapper objectMapper;
  private final Class<T> clazz;

  public JsonRepository(ObjectMapper objectMapper, Class<T> clazz) {
    this.objectMapper = objectMapper;
    this.clazz = clazz;
  }

  @Override
  public List<T> findAll() {
    File database = new File("database");
    if (!database.exists()) {
      return Lists.newArrayList();
    }

    File directory = new File(database, clazz.getSimpleName());
    if (!directory.exists()) {
      return Lists.newArrayList();
    }

    File[] files = directory.listFiles();
    if (files == null) {
      return Lists.newArrayList();
    }

    List<T> entities = Lists.newArrayList();
    Arrays.stream(files)
        .forEach(jsonFile -> {
          try {
            entities.add(objectMapper.readValue(jsonFile, clazz));
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
    return entities;
  }

  @Override
  public T save(T entity) {
    File database = new File("database");
    if (!database.exists()) {
      if (!database.mkdir()) {
        throw new RuntimeException("Failed to create database for entity " + entity.toString());
      }
    }
    File directory = new File(database, clazz.getSimpleName());
    if (!directory.exists()) {
      if (!directory.mkdir()) {
        throw new RuntimeException("Failed to create directory for entity " + entity.toString());
      }
    }
    File jsonFile = new File(directory, entity.getId().toString() + ".json");
    if (!jsonFile.exists()) {
      try {
        if (!jsonFile.createNewFile()) {
          throw new RuntimeException("Failed to create json file for entity " + entity.toString());
        }
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }

    try {
      objectMapper.writeValue(jsonFile, entity);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return entity;
  }

  @Override
  public Optional<T> findById(ID id) {
    File database = new File("database");
    if (!database.exists()) {
      return Optional.empty();
    }

    File directory = new File(database, clazz.getSimpleName());
    if (!directory.exists()) {
      return Optional.empty();
    }

    File jsonFile = new File(directory, id.toString() + ".json");
    if (!jsonFile.exists()) {
      return Optional.empty();
    }
    try {
      return Optional.ofNullable(objectMapper.readValue(jsonFile, clazz));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public void delete(T entity) {
    File database = new File("database");
    if (!database.exists()) {
      return;
    }

    File directory = new File(database, clazz.getSimpleName());
    if (!directory.exists()) {
      return;
    }

    File jsonFile = new File(directory, entity.getId().toString() + ".json");
    if (!jsonFile.exists()) {
      return;
    }
    if (!jsonFile.delete()) {
      new IOException("Failed to delete file " + jsonFile + " for entity " + entity + ".").printStackTrace();
    }
  }

  @Override
  public void deleteAll() {
    File database = new File("database");
    if (!database.exists()) {
      return;
    }

    File directory = new File(database, clazz.getSimpleName());
    if (!directory.exists()) {
      return;
    }

    File[] files = directory.listFiles();
    if (files == null) {
      return;
    }

    Arrays.stream(files).forEach(jsonFile -> {
      if (!jsonFile.delete()) {
        new IOException("Failed to delete file " + jsonFile + ".").printStackTrace();
      }
    });
  }
}
