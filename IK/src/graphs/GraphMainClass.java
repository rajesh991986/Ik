package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class GraphMainClass {
    public static void main(String[] args){
        Graphs graph = new Graphs(4);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(1,3);
        //System.out.println("hasEulerianCycle: "+ graph.hasEulerianCycle() + " :hasEulerianPath: " + graph.hasEulerianPath());
       // graph.explore(1);
        System.out.println(find_order(new String[]{"vh","vv","ccvc","ccchvv","ccccvv"}));
    }
    static HashMap<Character, ArrayList<Character>> adjList = new HashMap();
    static LinkedHashMap<Character,Integer> degree = new LinkedHashMap<>();
    static String find_order(String[] words) {
        int i=0;
        if(words.length == 1){
           return  findEdgesFromString(words);
        }
        while(i+1< words.length){
            findAdjList(words,i,i+1);
            i++;
        }
        if(adjList.isEmpty()){
            return findEdgesFromString(words);
        }
        StringBuffer rb = new StringBuffer();
        Character startChar =findLowDegree();
        // findWithDegree(startChar,rb);
        dfsTSort(startChar,rb); rb=rb.reverse();
      return rb.toString();
    }
    static int timer =0;
    static HashMap<Character,Integer> departtime = new HashMap<>();
    static HashSet<Character> visited = new HashSet();
    static void dfsTSort(Character startChar,StringBuffer rb){
        timer++;
        visited.add(startChar);
        ArrayList<Character> adj = adjList.get(startChar);
        if(null != adj){
            for(Character s:adj){
                if(!visited.contains(s)){
                    dfsTSort(s,rb);
                }
                else{
                    if(null == departtime.get(startChar)){
                        break;
                    }
                }
            }
        }
        departtime.put(startChar,timer);
        rb.append(startChar);
        return;
    }
    static String findEdgesFromString(String[] words){
        int i=0;
        StringBuffer sb = new StringBuffer();
        sb.append(words[0].charAt(i));i++;
        Character lc = words[0].charAt(0);
        while(i<words[0].length()){
            if(lc!= words[0].charAt(i)){
                sb.append(words[0].charAt(i));
                lc = words[0].charAt(i);
            }
            i++;
        }
        return sb.toString();
    }
    static String findWithDegree(Character startChar,StringBuffer rb){

        while(startChar!='\0' && !degree.isEmpty()){
            ArrayList<Character> list = adjList.get(startChar);
            int minDeg = Integer.MAX_VALUE;
            Character minDegChar ='\0';
            if(null != list) {
                for (Character cur : list) {
                    if(null != degree.get(cur)){
                        if (degree.get(cur) < minDeg) {
                            minDeg = degree.get(cur);
                            minDegChar = cur;
                        }
                        int deg = degree.get(cur);
                        degree.put(cur, deg--);
                    }
                }
            }
            rb.append(startChar);
            degree.remove(startChar);
            //startChar =minDegChar;
            startChar = findLowDegree();
        }
        return rb.toString();
    }
    static Character findLowDegree(){
        int minDeg = Integer.MAX_VALUE;
        Character minChar = '\0';
        Character firstMinChar = '\0';
        for(Character key:degree.keySet()){
            if(degree.get(key) < minDeg){
                minDeg = degree.get(key);
                minChar = key;
            }
        }
        if(minDeg<0 || degree.isEmpty()){
            minChar = '\0';
        }
        return minChar;
    }
    static void findAdjList(String[] words,int index1,int index2){
        String w1 = words[index1];
        String w2 = words[index2];
        int i=0;
        while(i<w1.length() && i< w2.length()){
            if(w1.charAt(i) != w2.charAt(i)){
                Character c1 = w1.charAt(i);
                Character c2 = w2.charAt(i);
                ArrayList<Character> list = adjList.get(c1);
                if(list == null) list = new ArrayList();
                if(list.contains(c2)){
                    i++;
                    break;
                }
                list.add(c2);
                adjList.put(c1,list);
                if(null != degree.get(c2))
                {
                    int deg = degree.get(c2);deg++;
                    degree.put(c2,deg);}
                else degree.put(c2,1);
                if(null == degree.get(c1)) degree.put(c1,0);
                break;
            }
            i++;
        }
    }
}
