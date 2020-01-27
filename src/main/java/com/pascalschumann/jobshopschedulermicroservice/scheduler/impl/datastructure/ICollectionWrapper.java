package com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure;

import java.util.List;

/**
 * TODO: fill this
 *
 * @author Pascal Schumann
 */
public interface ICollectionWrapper<T extends IEntity>
extends Iterable<T> {

	boolean any();

	boolean isEmpty();

	List<T> GetAll();

	void Add(T item);

	void AddAll(Iterable<T> items);

	T GetAny();

	/**
	 * returns a copy of inner collection as stackSet
	 */
	IStackSet<T> ToStackSet();

	void Clear();

	void Remove(T t);

	T GetById(Id id);

	boolean Contains(T t);
}
