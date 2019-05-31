package com.lyz;

import com.lyz.Algorithm.Community;
import com.lyz.Algorithm.Connectivity;
import com.lyz.Algorithm.Importance;
import com.lyz.GraphOperation.DelGraph;
import com.lyz.GraphOperation.GenGraph;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

import java.io.IOException;

public class Neo4jMain {

    public static void main(String[] args) throws IOException {
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "0228"));
        DelGraph.delete(driver);
        GenGraph.gengraph(driver);
        Importance.Degree(driver);
        Importance.PageRank(driver);
        //Community.SCC(driver);
        //Connectivity.shortestPath(driver, "A", "C");
        //Connectivity.shortestPath(driver, "A", "B");
        driver.close();
    }
}
