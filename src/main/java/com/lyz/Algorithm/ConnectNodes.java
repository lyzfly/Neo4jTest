package com.lyz.Algorithm;

import com.lyz.MyRelationshipTypes;
import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.PathExpanders;

/**
 * 判断两个节点是否连通
 */
public class ConnectNodes {

    public static boolean connectTwoNode(Node start, Node end){
        boolean isHavePath = false;
        PathFinder<Path> finder = GraphAlgoFactory.allPaths(PathExpanders.forTypeAndDirection(MyRelationshipTypes.In, Direction.OUTGOING), 100);
        Path path = finder.findSinglePath(start, end);
        if(path!=null){
            isHavePath = true;
        }
        return isHavePath;
    }
}
