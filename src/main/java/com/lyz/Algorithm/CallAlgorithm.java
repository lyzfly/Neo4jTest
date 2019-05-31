package com.lyz.Algorithm;

import org.neo4j.driver.v1.*;

public class CallAlgorithm {

    public static StatementResult callalgo(String query, Driver driver){
        StatementResult result;
        try(Session session = driver.session()){
            try(Transaction tx = session.beginTransaction()){
                result = tx.run(query);
                tx.success();
            }
        }
        return result;

    }
}
