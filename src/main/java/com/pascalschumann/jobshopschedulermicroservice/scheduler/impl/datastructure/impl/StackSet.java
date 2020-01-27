package com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.impl;

import java.util.*;
import java.util.function.Consumer;

import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.IEntity;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.IStackSet;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.Id;

/**
 * TODO: fill this
 *
 * @author Pascal Schumann
 */
public class StackSet<T extends IEntity> implements IStackSet<T> {

    private final Map<Id, T> _dictionary = new HashMap<Id, T>();

    // index to find Element in list via the element itself
    private final Map<T, Id> _index = new HashMap<T, Id>();

    public StackSet() {}

    public StackSet(final Collection<T> list) {
        PushAll(list);
    }

    @Override
    public void Push(final T element) {
        if (element == null) {
            return;
        }

        // a set contains the element only once, else skip adding
        if (Contains(element) == false) {
            final Id id = element.getId();
            _dictionary.put(id, element);
            _index.put(element, id);
        }
    }

    @Override
    public void Remove(final T element) {
        if (element == null) {
            return;
        }

        _dictionary.remove(_index.get(element));
        _index.remove(element);
    }

    @Override
    public boolean Any() {
        return !_dictionary.isEmpty();
    }

    @Override
    public T PopAny() {
        T element = null;
        for (final Map.Entry<Id, T> entry : _dictionary.entrySet()) {
            element = entry.getValue();
            break;
        }

        Remove(element);
        return element;
    }

    @Override
    public T GetAny() {
        for (final Map.Entry<Id, T> entry : _dictionary.entrySet()) {
            return entry.getValue();
        }

        return null; // if empty
    }

    @Override
    public void PushAll(final Iterable<T> elements) {
        for (final T element : elements) {
            Push(element);
        }
    }

    @Override
    public int size() {
        return _dictionary.size();
    }

    @Override
    public List<T> GetAll() {
        // create a copy of list
        final List<T> all = new LinkedList<>();
        all.addAll(_dictionary.values());
        return all;
    }

    @Override
    public void Clear() {
        _dictionary.clear();
        _index.clear();
    }

    @Override
    public String toString() {
        String result = "";

        for (final T item : _dictionary.values()) {
            result += item.toString() + System.lineSeparator();
        }

        return result;
    }

    @Override
    public T GetById(final Id id) {
        if (_dictionary.containsKey(id)) {
            return _dictionary.get(id);
        } else {
            return null;
        }
    }

    @Override
    public void RemoveById(final Id id) {
        if (_dictionary.containsKey(id) == false) {
            throw new RuntimeException("Id " + id + " doesn't exists.");
        }

        final T t = _dictionary.get(id);
        Remove(t);
    }

    @Override
    public boolean Contains(final T t) {
        return _index.containsKey(t);
    }

    @Override
    public boolean Contains(final Id id) {
        return _dictionary.containsKey(id);
    }

    @Override
    public Iterator<T> iterator() {
        return _dictionary.values().iterator();
    }

    @Override
    public void forEach(final Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (final T value : _dictionary.values()) {
            action.accept(value);
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return _dictionary.values().spliterator();
    }
}
