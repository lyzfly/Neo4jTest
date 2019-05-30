package com.lyz.Algorithm;

import org.neo4j.driver.v1.*;

import static com.lyz.MyLabel.Person;

public class Community extends Importance{
    public static void SCC(Driver driver){
        String query = "CALL algo.scc('Person','Follow', {write:true,partitionProperty:'partition'})\n" +
                "YIELD loadMillis, computeMillis, writeMillis, setCount, maxSetSize, minSetSize;";

        Importance.callalgo(query, driver);
        try(Session session = driver.session()){
            try(Transaction tx = session.beginTransaction()){
                tx.run("CALL algo.scc('Person','Follow', {write:true,partitionProperty:'partition'})\n" +
                        "YIELD loadMillis, computeMillis, writeMillis, setCount, maxSetSize, minSetSize;");
                StatementResult result1 = tx.run("MATCH (u:Person)\n" +
                        "RETURN u.partition as partition,count(*) as size_of_partition\n" +
                        "ORDER by size_of_partition\n" +"DESC"+
                        "LIMIT 1");

                Value partition = result1.next().get("partition");
                Value sizeofpartion = result1.next().get("size_of_partition");
                System.out.println(partition.get("name"));
                System.out.println(sizeofpartion);
                tx.success();
            }
        }
    }
}
