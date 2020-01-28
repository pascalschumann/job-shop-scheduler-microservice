package com.pascalschumann.jobshopschedulermicroservice.integration.utils;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.RandomStringUtils;

import com.pascalschumann.jobshopschedulermicroservice.api.model.JobToPlan;
import com.pascalschumann.jobshopschedulermicroservice.api.model.Machine;
import com.pascalschumann.jobshopschedulermicroservice.api.model.ScheduleRequest;
import com.pascalschumann.jobshopschedulermicroservice.api.repository.User;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.IEntity;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.datastructure.Id;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.IDirectedGraph;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.INode;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.INodes;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.impl.DirectedGraph;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.impl.Edge;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.impl.Node;
import com.pascalschumann.jobshopschedulermicroservice.scheduler.impl.graph.impl.Nodes;

/**
 * Provides static methods to generate entities.
 */
public class EntityFactory {

    private static final int maxLength = 5;
    private static final AtomicLong idCounter = new AtomicLong(1);
    private static final Random random = new Random();

    public static User createUser() {

        final String name = RandomStringUtils.randomAlphabetic(maxLength);
        final String password = RandomStringUtils.randomAlphabetic(maxLength);
        return new User(name, password);
    }

    public static ScheduleRequest createScheduleRequest() {

        final Long id = idCounter.getAndIncrement();
        final Machine[] machines = createMachines();
        final JobToPlan[] jobs = createJobsToPlan(machines);

        return new ScheduleRequest(id, jobs, machines);
    }

    public static JobToPlan[] createJobsToPlan(final Machine[] machines) {

        final JobToPlan[] jobs = new JobToPlan[machines.length];
        for (int i = 0; i < machines.length; i++) {
            final Machine machine = machines[i];
            final Long id = idCounter.getAndIncrement();
            final Long duration = (long) random.nextInt(maxLength) + 1;
            final Long machineGroupId = machine.getMachineGroupId();

            jobs[i] = new JobToPlan(new Id(id), duration, machineGroupId);
        }
        fillPredecessorsSuccessors(jobs);

        return jobs;
    }

    private static void fillPredecessorsSuccessors(final JobToPlan[] jobs) {
        final INode[] nodes = createNodes(jobs);
        final IDirectedGraph directedGraph = createBinaryDirectedGraph(nodes);
        for (final INode node : nodes) {
            final JobToPlan job = (JobToPlan) node.GetEntity();
            final INodes predecessors = directedGraph.GetPredecessorNodes(node);
            // can be only one since we use a binary tree here
            if (predecessors != null && predecessors.isEmpty() == false) {
                job.setPredecessorId(predecessors.GetAny().getId().getValue());
            }


            final INodes successors = directedGraph.GetSuccessorNodes(node);
            if (successors != null) {
                final Long[] successorIds = new Long[successors.size()];
                int i = 0;
                for (final INode successor : successors) {
                    successorIds[i] = successor.getId().getValue();
                    i++;
                }
                job.setSuccessorIds(successorIds);
            }

        }
    }

    public static Machine[] createMachines() {
        final Machine[] machines = new Machine[maxLength];
        for (int i = 0; i < maxLength; i++) {
            machines[i] = new Machine(idCounter.getAndIncrement(), idCounter.getAndIncrement());
        }
        return machines;
    }

    public static INode[] createNodes(final IEntity[] entities) {
        final INode[] nodes = new Node[entities.length];
        for (int i = 0; i < entities.length; i++) {
            nodes[i] = new Node(entities[i]);
        }

        return nodes;
    }

    private static IDirectedGraph createBinaryDirectedGraph(final INode[] nodes) {
        final IDirectedGraph directedGraph = new DirectedGraph();
        INode root;
        final INodes leafs = new Nodes();
        for (int i = 0; i < nodes.length; i++) {
            // left: 2*i + 1 , right: 2*i + 2
            final int maxIndex = nodes.length;
            final int left = 2 * i + 1;
            final int right = 2 * i + 2;
            if (left < maxIndex) {
                directedGraph.AddEdge(new Edge(nodes[i], nodes[left]));
            }

            if (right < maxIndex) {
                directedGraph.AddEdge(new Edge(nodes[i], nodes[right]));
            }
        }

        return directedGraph;
    }
}
