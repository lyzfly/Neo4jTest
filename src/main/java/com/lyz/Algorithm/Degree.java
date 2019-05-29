package com.lyz.Algorithm;

import org.neo4j.driver.v1.*;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;

/**
 寻找最大的度的节点
 @author lyz
*/
public class Degree {

    public static StatementResult findMaxDegreeNode(){
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "0228"));
        StatementResult result = null;
        try(Session session = driver.session()){
            try(Transaction tx = session.beginTransaction()){
                result = tx.run("CALL algo.degree.stream(\"Person\", \"In\", {direction: \"incoming\"})\n" +
                        "YIELD nodeId, score\n" +
                        "RETURN algo.asNode(nodeId).name AS name, score AS followers\n" +
                        "ORDER BY followers DESC");

                tx.success();

            }
        }
        driver.close();

        return result;
    }
}
