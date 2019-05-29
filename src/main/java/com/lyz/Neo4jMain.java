package com.lyz;

import com.lyz.Algorithm.ClusterDemo;
import com.lyz.Algorithm.Degree;
import org.neo4j.driver.v1.GraphDatabase;

public class Neo4jMain {

    public static void main(String[] args) {

        Degree.findMaxDegreeNode();
        ClusterDemo.find();

    }
}
