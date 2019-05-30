package com.lyz;

import org.neo4j.driver.v1.*;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;

/**
 寻找最大的度的节点
 @author lyz
*/
public class Degree {

    public static StatementResult findMax(Driver driver){
        StatementResult result = null;
        try(Session session = driver.session()){
            try(Transaction tx = session.beginTransaction()){
                result = tx.run("CALL algo.degree.stream(\"Person\", \"In\", {direction: \"incoming\"})\n" +
                        "YIELD nodeId, score\n" +
                        "RETURN algo.asNode(nodeId).name AS name, score AS followers\n" +
                        "ORDER BY followers DESC");

                while(result.hasNext()){
                    Record record = result.next();

                    System.out.println(record.get("name")+" "+record.get("followers"));
                }
                tx.success();

            }
        }

        return result;
    }
}
