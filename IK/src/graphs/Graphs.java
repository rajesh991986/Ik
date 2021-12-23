package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graphs {
    int V;
    ArrayList<ArrayList<Integer>> adjList;
    Graphs(){

    }
    Graphs(int size){
        this.V =size;
        this.adjList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            adjList.add(new ArrayList<>());
        }
    }
    void addEdge(int start,int end){
        addEdge(start,end,true);
    }
    void addEdge(int start,int end ,boolean bidirec){
        adjList.get(start).add(end);
        if(bidirec){
            adjList.get(end).add(start);
        }
    }
    int totalOddEdges(){
        int odd = 0;
        for (ArrayList list:this.adjList
        ) {
            if(list.size() % 2 == 1){
                odd++;
            }
        }
        return odd;
    }
    boolean hasEulerianCycle(){
        int odd = totalOddEdges();
        if(odd == 0){
            return  true;
        }
        return  false;
    }
    boolean hasEulerianPath(){
        int odd = totalOddEdges();
        if(odd ==0 || odd == 2){
            return true;
        }
        return  false;
    }
    void explore(int s){
        boolean[] captrued = new boolean[V];
        boolean[] visited = new boolean[V];
        ArrayList<Integer> parent = new ArrayList<>();
        for (int i = 0; i <V ; i++) {
            parent.add(-1);
        }
        captrued[s] = true;
        visited[s] = true;
        //for each fridge edege :
        Queue<Integer> fringeEdges = new LinkedList<>();
        fringeEdges.addAll(adjList.get(s));
        for (Integer c:adjList.get(s))
        {
            parent.add(c,s);
        }
        while(!fringeEdges.isEmpty()){
            int current = fringeEdges.poll();
            if(captrued[current]){
                continue;
            }
            else{
                if(!visited[current])
                fringeEdges.addAll(adjList.get(current));
                for (Integer c:adjList.get(current)) {
                    if(parent.get(c) == -1)
                    parent.add(c,current);
                }
                captrued[current] = true;
            }
        }
        System.out.println("Parents List : ");
        for (int i = 0; i < parent.size(); i++) {
            System.out.println(i+"->"+parent.get(i));
        }
        System.out.println("Captured");
        for (int i = 0; i < captrued.length; i++) {
            System.out.println(i+"="+captrued[i]);
        }
    }
}
