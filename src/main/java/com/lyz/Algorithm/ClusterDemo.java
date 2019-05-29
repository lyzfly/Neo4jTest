package com.lyz.Algorithm;

import org.neo4j.driver.v1.*;

public class ClusterDemo {

    public static void main(String[] args) {

        Driver driver = GraphDatabase.driver("bolt://localhost:7687",AuthTokens.basic("neo4j", "0228"));
        try(Session session = driver.session()){
            try(Transaction tx = session.beginTransaction()){
                StatementResult result = tx.run("CALL algo.degree.stream(\"User\", \"FOLLOWS\", {direction: \"incoming\"})\n" +
                        "YIELD nodeId, score\n" +
                        "RETURN algo.asNode(nodeId).id AS name, score AS followers\n" +
                        "ORDER BY followers DESC");

                while(result.hasNext()){
                    Record record = result.next();
                    record.get("followers");

                }

                tx.success();
            }
        }
    }
}
