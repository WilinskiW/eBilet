package com.portfolio.ebilet.reservations.domain;

import com.fasterxml.uuid.Generators;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class InMemoryEventRepository implements EventRepository{
    private final Map<UUID, Event> events = new HashMap<>();

    private UUID generateUUIDv7(){
        return Generators.timeBasedEpochGenerator().generate();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Event> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Event> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Event> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Event getOne(UUID uuid) {
        return null;
    }

    @Override
    public Event getById(UUID uuid) {
        return null;
    }

    @Override
    public Event getReferenceById(UUID uuid) {
        return null;
    }

    @Override
    public <S extends Event> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Event> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Event> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Event> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Event> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Event> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Event, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Event> S save(S entity) {
        UUID uuid = generateUUIDv7();
        entity.setId(uuid);
        events.put(uuid, entity);
        return entity;
    }

    @Override
    public <S extends Event> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Event> findById(UUID uuid) {
        return events.values()
                .stream()
                .filter(event -> event.getId().equals(uuid))
                .findFirst();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public List<Event> findAll() {
        return events.values()
                .stream()
                .toList();
    }

    @Override
    public List<Event> findAllById(Iterable<UUID> uuids) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void delete(Event entity) {
        events.remove(entity.getId());
    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends Event> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Event> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Event> findAll(Pageable pageable) {
        return null;
    }
}
