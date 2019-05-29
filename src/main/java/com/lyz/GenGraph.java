package com.lyz;

import java.io.*;
import java.util.Random;
import org.neo4j.driver.v1.*;

import static org.neo4j.driver.v1.Values.parameters;

public class GenGraph {

    public static File RandomFile() throws  IOException{
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "0228"));
        try (Session session = driver.session()) {
            try(Transaction tx = session.beginTransaction()){
                tx.run("MATCH(a:Person) DETACH DELETE a");
                tx.success();
            }
        }
        driver.close();
        File file = new File("data.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream outputStream = new FileOutputStream(file);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 10; i++) {

                char base = 'A';
                Random rand = new Random();
                int add_parm1 = rand.nextInt(4);
                int add_parm2 = rand.nextInt(4);

                char personA = (char) (base + add_parm1);
                char personB = (char) (base + add_parm2);
                String line = personA + " " + personB;
                sb.append(line+"\r\n");

            }
            outputStream.write(sb.toString().getBytes("utf-8"));
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void gengraph() throws IOException {
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "0228"));
        try (Session session = driver.session()) {
            try (Transaction tx = session.beginTransaction()) {
                File file = RandomFile();
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                while ((line = br.readLine()) != null) {
                    String[] arr = line.split(" ");
                    StatementResult result = tx.run("MATCH (n:Person) WHERE n.name=$person RETURN n",parameters("person",arr[0]));
                    if (!result.hasNext()) {
                        tx.run("create(a:Person{name:{name}})", parameters("name", arr[0]));
                    }
                    StatementResult result1 = tx.run("MATCH (n:Person) WHERE n.name={x} RETURN n", parameters("x", arr[1]));
                    if (!result1.hasNext()) {
                        tx.run("create(a:Person{name:{name}})", parameters("name", arr[1]));
                    }

                    if(!arr[0].equals(arr[1])) {
                        StatementResult result2 = tx.run("MATCH(a:Person) --> (b:Person) WHERE a.name={x} AND b.name={y} RETURN a",
                                parameters("x", arr[0], "y", arr[1]));
                        if (!result2.hasNext()) {
                            tx.run("MATCH(a:Person),(b:Person) WHERE a.name={x} AND b.name={y} create(a)-[r:Follow]->(b) RETURN r",
                                    parameters("x", arr[0], "y", arr[1]));
                        }
                    }
                }
                tx.success();
            }
        }
        driver.close();
    }
    public static void main(String[] args) throws IOException {

        gengraph();
    }
}
