package com.pascalschumann.jobshopscheduler.scheduler.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pascalschumann.jobshopscheduler.scheduler.IJobShopScheduler;
import com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure.IStackSet;
import com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure.Id;
import com.pascalschumann.jobshopscheduler.scheduler.impl.datastructure.impl.StackSet;
import com.pascalschumann.jobshopscheduler.scheduler.impl.graph.IDirectedGraph;
import com.pascalschumann.jobshopscheduler.scheduler.impl.graph.INode;
import com.pascalschumann.jobshopscheduler.scheduler.impl.graph.INodes;
import com.pascalschumann.jobshopscheduler.scheduler.impl.graph.impl.Node;
import com.pascalschumann.jobshopscheduler.scheduler.impl.modelWrappers.Machine;
import com.pascalschumann.jobshopscheduler.scheduler.impl.modelWrappers.Operation;

/**
 * TODO: fill this
 *
 * @author Pascal Schumann
 */
@Component
public class JobShopScheduler
implements IJobShopScheduler {

	/*
	 * private void CorrectIdleStartTimesOfMachines(IEnumerable<Operation> operations, Dictionary<Id, List<Resource>>
	 * resourcesByResourceSkillId) { for (var operation in operations) { for (var resource in
	 * resourcesByResourceSkillId[operation.GetResourceSkillId()]) { if
	 * (resource.GetValue().Id.Equals(operation.GetValue().ResourceId) && resource.GetIdleStartTime().GetValue() <
	 * operation.GetEndTime()) { resource.SetIdleStartTime(new DueTime(operation.GetEndTime())); } } } }
	 */

	@Override
	public void ScheduleWithGifflerThompsonAsZaepfel(final IPriorityRule priorityRule,
		final IDirectedGraph operationGraph, final List<Machine> machines) {
		final Map<Id, List<Machine>> machinesByMachineGroup = new HashMap<>();
		for (final Machine machine : machines) {
			if (machinesByMachineGroup.containsKey(machine.getMachineGroupId())) {

			} else {
				machinesByMachineGroup.put(machine.getMachineGroupId(), new LinkedList<>());
				machinesByMachineGroup.get(machine.getMachineGroupId()).add(machine);
			}
		}

		/*
		 * // set correct idleStartTimes in resources from operations of last cycle(s) // TODO: implement this
		 * CorrectIdleStartTimesOfMachines(dbTransactionData.OperationGetAll(), machinesByMachineGroup);
		 */

		/*
		 * S: Menge der aktuell einplanbaren Arbeitsvorgänge a: Menge der technologisch an erster Stelle eines
		 * Fertigungsauftrags stehenden Arbeitsvorgänge N(o): Menge der technologisch direkt nachfolgenden
		 * Arbeitsoperationen von Arbeitsoperation o M(o): Maschine auf der die Arbeitsoperation o durchgeführt wird K:
		 * Konfliktmenge (die auf einer bestimmten Maschine gleichzeitig einplanbaren Arbeitsvorgänge) p(o):
		 * Bearbeitungszeit von Arbeitsoperation o (=Duration) t(o): Startzeit der Operation o (=Start) d(o):
		 * Fertigstellungszeitpunkt von Arbeitsoperation o (=End) d_min: Minimum der Fertigstellungszeitpunkte o_min:
		 * Operation mit minimalem Fertigstellungszeitpunkt o1: beliebige Operation aus K (o_dach bei Zäpfel)
		 */
		IStackSet<Operation> S = new StackSet<Operation>();

		// Bestimme initiale Menge: S = a
		S = CreateS(operationGraph);

		// t(o) = 0 für alle o aus S, but use start if given
		for (final Operation o : S) {
			if (o.getStart() == null) {
				o.setStart(0L);
			}

		}

		// while S not empty do
		while (S != null && S.Any()) {
			long d_min = Long.MAX_VALUE;
			Operation o_min = null;
			for (final Operation o : S) {
				// Berechne d(o) = t(o) + p(o) für alle o aus S
				final long newEnd = o.getStart() + o.getDuration();
				o.setEnd(newEnd);
				// Bestimme d_min = min{ d(o) | o aus S }
				if (o.getEnd() < d_min) {
					d_min = o.getEnd();
					o_min = o;
				}
			}

			// Bilde Konfliktmenge K = { o | o aus S UND M(o) == M(o_min) UND t(o) < d_min }
			final IStackSet<Operation> K = new StackSet<Operation>();
			for (final Operation o : S) {
				if (o.getMachineGroupId().equals(o_min.getMachineGroupId()) && o.getStart() < d_min) {
					K.Push(o);
				}
			}

			// while K not empty do
			if (K.Any()) {
				// Entnehme Operation mit höchster Prio (o1) aus K und plane auf nächster freier Resource ein

				final List<Operation> allO1 = new LinkedList<>();

				machinesByMachineGroup.get(o_min.getMachineGroupId()).sort(Comparator.comparing(Machine::getIdleStart));
				for (final Machine machine : machinesByMachineGroup.get(o_min.getMachineGroupId())) {
					if (K.Any() == false) {
						break;
					}

					Operation o1 = null;
					o1 = priorityRule.GetHighestPriorityOperation(machine.getIdleStart(), K.GetAll());
					if (o1 == null) {
						throw new RuntimeException("This is not possible if K.Any() is true.");
					}

					allO1.add(o1);

					K.Remove(o1);

					o1.setPlannedMachine(machine.getId());
					// correct op's start time if resource's idleTime is later
					if (machine.getIdleStart() > o1.getStart()) {
						final long newStart = machine.getIdleStart();
						o1.setStart(newStart);
						final long newEnd = o1.getStart() + o1.getDuration();
						o1.setEnd(newEnd);
					}

					// correct op's start time if op's material is later available
					final Long dueTimeOfOperationMaterial = o1.getMaterialAvailability();
					if (dueTimeOfOperationMaterial != null && dueTimeOfOperationMaterial > o1.getStart()) {
						final long newStart = dueTimeOfOperationMaterial;
						o1.setStart(newStart);
						final long newEnd = o1.getStart() + o1.getDuration();
						o1.setEnd(newEnd);
					}

					machine.setIdleStart(o1.getEnd());
				}

				// t(o) = d(letzte o1 aus allO1) für alle o aus K (ohne alle o1)
				for (final Operation o : K) {
					o.setStart(allO1.get(allO1.size() - 1).getEnd());
				}

				/*
				 * if N(o1) not empty then S = S vereinigt N(o1) ohne alle o1
				 */
				for (final Operation o1 : allO1) {
					final INode o1AsNode = new Node(o1);

					final INodes allPredecessorsRecursive = operationGraph.GetPredecessorNodesRecursive(o1AsNode);
					if (allPredecessorsRecursive != null) {
						final IStackSet<Operation> N = new StackSet<Operation>();
						for (final INode node : allPredecessorsRecursive) {
							N.Push((Operation) node.GetEntity());
						}

						// t(o) = d(o1) für alle o aus N(o1)
						for (final Operation n : N) {
							n.setStart(o1.getEnd());
						}
					}

					// prepare for next round
					operationGraph.RemoveNode(o1AsNode, true);
				}

				S = CreateS(operationGraph);
			}
		}
	}

	/**
	 * @return: all leafs of all operationGraphs
	 */
	private IStackSet<Operation> CreateS(final IDirectedGraph operationGraph) {
		final INodes leafs = operationGraph.GetLeafNodes();
		final IStackSet<Operation> S = new StackSet<Operation>();
		if (leafs != null) {
			for (final INode leaf : leafs) {
				S.Push((Operation) leaf.GetEntity());
			}
		}

		return S;
	}
}
