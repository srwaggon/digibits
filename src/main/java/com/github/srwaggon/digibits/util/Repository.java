package com.github.srwaggon.digibits.util;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends Identified<ID>, ID> {

  List<T> findAll();

  T save(T entity);

  Optional<T> findById(ID id);

  void delete(T entity);

  void deleteAll();
}
