package com.lyz.Algorithm;

import org.neo4j.driver.v1.*;
import org.neo4j.graphdb.Node;
import static org.neo4j.driver.v1.Values.parameters;

/**
 * 判断两个节点是否连通
 */
public class Connectivity extends CallAlgorithm{

    public static boolean shortestPath(Driver driver,String startnode, String  endnode) {
        
        boolean flag = false;
        try (Session session = driver.session()) {
            try (Transaction tx = session.beginTransaction()) {

                StatementResult result = tx.run("MATCH (start:Person{name:{x}}), (end:Person{name:{y}})\n" +
                        "CALL algo.shortestPath.stream(start, end,'cost')\n" +
                        "YIELD nodeId, cost\n" +
                        "RETURN algo.asNode(nodeId).name AS name, cost",parameters("x",startnode,"y",endnode));
                while(result.hasNext()) {
                    Record record = result.next();
                    System.out.println(record.get("name")+" "+record.get("cost"));
                    flag = true;
                }
                tx.success();
            }
        }
        System.out.println(flag);
        return flag;

    }

}
