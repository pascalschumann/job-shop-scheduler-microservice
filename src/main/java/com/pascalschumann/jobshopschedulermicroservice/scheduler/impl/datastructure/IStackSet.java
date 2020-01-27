package com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure;

import java.util.List;

/**
 * own Set impl, since HashSet ist not useful if: - get any element e.g. first with O(1) <br>
 * - pop any element with O(1) <br>
 * - remove O(n), since list must be reindexed <br>
 * - push() O(1) <br>
 * - GetById(), Contains(): O(1) but remains the idea of a mathematical set: every item exists only once
 *
 * @author Pascal Schumann
 */
public interface IStackSet<T extends IEntity>
extends Iterable<T> {

	void Push(T element);

	void PushAll(Iterable<T> elements);

	void Remove(T element);

	boolean Any();

	T PopAny();

	T GetAny();

	/**
	 * Should return a new list which contains all elements
	 */
	List<T> GetAll();

	void Clear();

	T GetById(Id id);

	boolean Contains(T t);

	boolean Contains(Id id);

	void RemoveById(Id id);

	int size();
}
