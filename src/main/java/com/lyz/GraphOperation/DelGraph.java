package com.lyz.GraphOperation;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Transaction;

public class DelGraph {

    public static void delete(Driver driver){
        try(Session session = driver.session()){
            try(Transaction tx = session.beginTransaction()){
                tx.run("MATCH (a:Person) DETACH DELETE a");
                tx.success();
            }
        }
    }
}
