package com.lyz;

import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.traversal.TraversalMetadata;

public class IMPathFinder implements PathFinder {
    @Override
    public Iterable findAllPaths(Node node, Node node1) {
        return null;
    }

    @Override
    public Path findSinglePath(Node node, Node node1) {
        return null;
    }

    @Override
    public TraversalMetadata metadata() {
        return null;
    }


}
