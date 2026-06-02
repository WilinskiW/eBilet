package com.portfolio.ebilet.reservations.domain;

import com.fasterxml.uuid.Generators;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public abstract class InMemoryBaseRepository <T extends BaseEntity> implements JpaRepository<T, UUID> {
    protected final Map<UUID, T> storage = new HashMap<>();

    protected UUID generateUUIDv7() {
        return Generators.timeBasedEpochGenerator().generate();
    }

    @Override
    public <S extends T> S save(S entity) {
        if (entity.getId() == null) {
            entity.setId(generateUUIDv7());
        }
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<T> findById(UUID uuid) {
        return Optional.ofNullable(storage.get(uuid));
    }

    @Override
    public List<T> findAll() {
        return storage.values().stream().toList();
    }

    @Override
    public void delete(T entity) {
        if (entity.getId() != null) {
            storage.remove(entity.getId());
        }
    }

    @Override
    public void deleteById(UUID uuid) {
        storage.remove(uuid);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    public long count() {
        return storage.size();
    }

    @Override
    public void deleteAll() {
        storage.clear();
    }

    // --- Poniżej metody interfejsu JpaRepository, które najczęściej pozostają puste w in-memory ---

    @Override
    public void flush() {}

    @Override
    public <S extends T> S saveAndFlush(S entity) { return save(entity); }

    @Override
    public <S extends T> List<S> saveAllAndFlush(Iterable<S> entities) { return saveAll(entities); }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        entities.forEach(this::save);
        return (List<S>) findAll();
    }

    @Override
    public void deleteAllInBatch(Iterable<T> entities) { entities.forEach(this::delete); }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) { uuids.forEach(this::deleteById); }

    @Override
    public void deleteAllInBatch() { deleteAll(); }

    @Override
    public T getOne(UUID uuid) { return findById(uuid).orElse(null); }

    @Override
    public T getById(UUID uuid) { return findById(uuid).orElse(null); }

    @Override
    public T getReferenceById(UUID uuid) { return findById(uuid).orElse(null); }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) { return Optional.empty(); }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) { return List.of(); }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) { return List.of(); }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) { return null; }

    @Override
    public <S extends T> long count(Example<S> example) { return 0; }

    @Override
    public <S extends T> boolean exists(Example<S> example) { return false; }

    @Override
    public <S extends T, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }

    @Override
    public List<T> findAllById(Iterable<UUID> uuids) { return List.of(); }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) { uuids.forEach(this::deleteById); }

    @Override
    public void deleteAll(Iterable<? extends T> entities) { entities.forEach(this::delete); }

    @Override
    public List<T> findAll(Sort sort) { return findAll(); }

    @Override
    public Page<T> findAll(Pageable pageable) { return null; }
}
