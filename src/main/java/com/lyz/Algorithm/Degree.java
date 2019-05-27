package com.lyz.Algorithm;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;

/**
 寻找最大的度的节点
 @author lyz
*/
public class Degree {

    public static Node findMaxDegreeNode(ResourceIterator<Node> it){
        Node maxDegreeNode = null;
        int maxDegree = Integer.MIN_VALUE;
        while(it.hasNext()){
            Node nextNode = it.next();
            int count = nextNode.getDegree(Direction.OUTGOING);
            if(count>maxDegree){
                maxDegree = count;
                maxDegreeNode = nextNode;
            }
        }
        return maxDegreeNode;
    }
}
