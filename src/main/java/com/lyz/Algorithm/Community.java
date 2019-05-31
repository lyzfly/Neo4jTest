package com.lyz.Algorithm;

import org.neo4j.driver.v1.*;

import static com.lyz.MyLabel.Person;

public class Community extends Importance{
    public static void SCC(Driver driver){
        String query = "CALL algo.scc('Person','Follow', {write:true,partitionProperty:'partition'})\n" +
                "YIELD loadMillis, computeMillis, writeMillis, setCount, maxSetSize, minSetSize;";

        StatementResult result = Importance.callalgo(query, driver);
        while(result.hasNext()){
            Record record = result.next();
            System.out.println(record);
        }
    }
}
