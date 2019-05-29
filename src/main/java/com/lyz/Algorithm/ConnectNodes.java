package com.lyz.Algorithm;

import org.neo4j.driver.v1.*;
import org.neo4j.graphdb.Node;
import static org.neo4j.driver.v1.Values.parameters;

/**
 * 判断两个节点是否连通
 */
public class ConnectNodes {

    public static boolean connectTwoNode(String startnode, String  endnode) {
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "0228"));
        boolean flag = false;
        try (Session session = driver.session()) {
            try (Transaction tx = session.beginTransaction()) {
                StatementResult result = tx.run("MATCH(a:Person) --> (b:Person) WHERE a.name={x} AND b.name={y} RETURN a",
                        parameters("x", startnode, "y", endnode));
                if (result.hasNext()) {
                    flag = true;
                }
                tx.success();
            }
        }
        System.out.println(flag);
        driver.close();
        return flag;
    }
}
