package com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

import com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure.ICollectionWrapper;
import com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure.IEntity;
import com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure.IStackSet;
import com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure.Id;

/**
 * TODO: fill this
 *
 * @author Pascal Schumann
 */
public class CollectionWrapperWithStackSet<T extends IEntity>
implements ICollectionWrapper<T> {

	protected final IStackSet<T> StackSet = new StackSet<T>();

	protected CollectionWrapperWithStackSet() {
	}

	@Override
	public List<T> GetAll() {
		return StackSet.GetAll();
	}

	@Override
	public void Add(final T item) {
		StackSet.Push(item);
	}

	@Override
	public void AddAll(final Iterable<T> items) {
		StackSet.PushAll(items);
	}

	@Override
	public T GetAny() {
		return StackSet.GetAny();
	}

	@Override
	public IStackSet<T> ToStackSet() {
		return StackSet;
	}

	@Override
	public void Clear() {
		StackSet.Clear();
	}

	@Override
	public void Remove(final T t) {
		StackSet.Remove(t);
	}

	@Override
	public T GetById(final Id id) {
		return StackSet.GetById(id);
	}

	@Override
	public String toString() {
		return StackSet.toString();
	}

	@Override
	public boolean Contains(final T t) {
		return StackSet.Contains(t);
	}

	@Override
	public Iterator<T> iterator() {
		return StackSet.iterator();
	}

	@Override
	public void forEach(final Consumer<? super T> action) {
		StackSet.forEach(action);
	}

	@Override
	public Spliterator<T> spliterator() {
		return StackSet.spliterator();
	}

	@Override
	public boolean any() {
		return StackSet.Any();
	}

	@Override
	public boolean isEmpty() {
		return StackSet.Any() == false;
	}
}
