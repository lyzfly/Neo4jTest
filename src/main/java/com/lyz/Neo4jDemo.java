package com.lyz;

import com.lyz.GraphOperation.GenGraph;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

/**
 @author lyz
 @Description
 @data:$Date
*/
public class Neo4jDemo {


    public static void main(String[] args) throws SQLException, IOException {

        /*File file = GenGraph.RandomFile(Driver driver);   //调用随机函数生成数据文件data.txt
        GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(new File("Test"));

        try(Transaction tx = db.beginTx()){
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line= "";
            while((line=br.readLine())!=null) {
                String[] arr = line.split(" ");
                Node node_name = db.findNode(MyLabel.Person, "name", arr[0]);
                if(node_name==null){
                    Node personNode = db.createNode(MyLabel.Person);
                    personNode.setProperty("name", arr[0]);

                }
                node_name = db.findNode(MyLabel.Person, "name", arr[0]);
                Node node_address = db.findNode(MyLabel.Address, "address", arr[1]);
                if(node_address==null){
                    Node addressNode = db.createNode(MyLabel.Address);
                    addressNode.setProperty("address",arr[1]);
                }
                node_address = db.findNode(MyLabel.Address, "address", arr[1]);
                node_name.createRelationshipTo(node_address, MyRelationshipTypes.In);
            }
            *//*ResourceIterator<Node> nodes = db.findNodes(MyLabel.Person);
            Node maxDegreeNode = Degree.findMaxDegreeNode(nodes);
            System.out.println("出度最大节点是"+ maxDegreeNode.getProperty("name")+","+"度为"+maxDegreeNode.getDegree(Direction.OUTGOING));
            boolean isConnective = ConnectNodes.connectTwoNode(db.findNode(MyLabel.Person, "name", "A"), db.findNode(MyLabel.Address, "address", "4"));
            System.out.println(isConnective);
            tx.success();*//*
        }
        db.shutdown();

        *//*Connection con  = DriverManager.getConnection("jdbc:neo4j://localhost：7474");
        String query = "CALL algo.degree(\"User\", \"FOLLOWS\", {direction: \"incoming\", writeProperty: \"followers\"})";
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("MATCH (n:user ) RETURN n" );
            Map<String,Object> row = result.
        }catch (Exception e){
            e.printStackTrace();
         }*//*


        *//*try(Transaction ignored = db.beginTx();
            Result result = db.execute("CALL algo.degree(\"User\", \"FOLLOWS\", {direction: \"incoming\", " +
                    "writeProperty: \"followers\"})")){
            while(result.hasNext()){
                Node node = (Node) result.next();
                node.getDegree(Direction.INCOMING);
            }
        }*/
    }

}
