package ScalableSystems;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistantHashing {
    public static SortedMap<Long,String> circle = new TreeMap<>();
    public static String[] serverIps = new String[] {"104.128.9.1","104.128.9.2","104.128.9.3"};
    static {
        for (String ip:serverIps
             ) {
            circle.put(hash(ip),ip);
        }
    }
    public static void addToCircle(String key){
        circle.put(hash(key),key);
    }

    public static String getServerKey(String key){
        SortedMap<Long,String> subMap = circle.tailMap(hash(key));
        if(subMap.isEmpty()){
            return  circle.get(circle.firstKey());
        }
        else{
            return  circle.get(subMap.firstKey());
        }
    }

    public static long hash(String key){
        MessageDigest instance = null;
        try{
            instance = MessageDigest.getInstance("MD5");
        }
        catch (Exception e){
           e.printStackTrace();
           return 0;

        }
        instance.reset();
        instance.update(key.getBytes());
        byte[] digest = instance.digest();
        long h =0;
        for (int i = 0; i < 4; i++) {
            h<<=8;
            h|=((int)digest[i]) & 0xFF;
        }
        return h;
    }
    public static void main(String[] args){
            String[] keys = new String[]{ "This","is","Rajesh","104.128.9.2"};
        for (String key:keys
             ) {
            System.out.println("key :" + key + " : Server: "+ getServerKey(key));
        }
    }
}
