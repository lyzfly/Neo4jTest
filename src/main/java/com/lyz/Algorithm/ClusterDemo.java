package com.lyz.Algorithm;

import org.neo4j.driver.v1.*;

import static com.lyz.MyLabel.Person;

public class ClusterDemo {
    public static void find(){
        Driver driver = GraphDatabase.driver("bolt://localhost:7687",AuthTokens.basic("neo4j", "0228"));
        try(Session session = driver.session()){
            try(Transaction tx = session.beginTransaction()){
                StatementResult result = tx.run("CALL algo.scc('Person','Follow', {write:true,partitionProperty:'partition'})\n" +
                        "YIELD loadMillis, computeMillis, writeMillis, setCount, maxSetSize, minSetSize;");
                StatementResult result1 = tx.run("MATCH (u:Person)\n" +
                        "RETURN u.partition as partition,count(*) as size_of_partition\n" +
                        "ORDER by size_of_partition\n" +
                        "LIMIT 1");
                Value m = result1.next().get("size_of_partition");
                System.out.println(m);
                tx.success();
            }
        }
    }
}
