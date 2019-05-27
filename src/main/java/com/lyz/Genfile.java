package com.lyz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Genfile {

    public static File RandomFile() throws IOException{
        File file = new File("data.txt");

        try{
            if(!file.exists()) {
                file.createNewFile();
            }
            HashMap<Character,List<Integer>> map = new HashMap<>();
            FileOutputStream outputStream = new FileOutputStream(file);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<20;i++) {

                char base = 'A';
                Random rand = new Random();
                int add_parm = rand.nextInt(4);
                int add_parm1 = rand.nextInt(4);
                char key = (char) (base + add_parm);
                int value = 1 + add_parm1;

                if (!map.containsKey(key)) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(value);
                    map.put(key, list);
                    String line = key + " " + value;
                    sb.append(line+"\r\n");
                }else if(!map.get(key).contains(value)){

                    List list = map.get(key);
                    list.add(value);
                    map.put(key, list);
                    String line = key + " " + value;
                    sb.append(line+"\r\n");
                }
            }
            outputStream.write(sb.toString().getBytes("utf-8"));
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return file;
    }
}
