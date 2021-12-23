package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Result {
    class XY{
        int x =-1;
        int y =-1;
        XY(){

        }
        XY(int a,int b){
            x = a;
            y=b;
        }
    }
    public static int count_islands(List<List<Integer>> matrix) {
        // Write your code here
        int count =0;
        Result result = new Result();
        for(int i=0;i<matrix.size();i++){
            for(int j=0;j<matrix.get(i).size();j++){
                if(matrix.get(i).get(j) ==1){
                    count++;
                    result.BFS(i,j,matrix);
                }
            }
        }
        return count;
    }
    public  void BFS(int x,int y ,List<List<Integer>> matrix){
        Queue<XY> queue = new LinkedList<XY>();
        queue.add(new XY(x,y));
        while(!queue.isEmpty()){
            XY xy = queue.poll();
            matrix.get(xy.x).set(xy.y,0);
            ArrayList<XY> adjList = adjList(xy.x,xy.y,matrix);
            for(XY adjXY:adjList) {
                queue.add(adjXY);
            }
        }
    }
    public  ArrayList<XY> adjList(int x,int y,List<List<Integer>> matrix){
        ArrayList<XY> adjList = new ArrayList<XY>();
        if(x+1<matrix.size() && matrix.get(x+1).get(y) == 1) adjList.add(new XY(x+1,y));
        if(y+1<matrix.get(x).size() && matrix.get(x).get(y+1) == 1) adjList.add(new XY(x,y+1));
        if(y-1>=0 && matrix.get(x).get(y-1) == 1) adjList.add(new XY(x,y-1));
        if(x-1>=0 && matrix.get(x-1).get(y) == 1) adjList.add(new XY(x-1,y));
        if(x-1>=0 && y-1>=0 && matrix.get(x-1).get(y-1) == 1) adjList.add(new XY(x-1,y-1));
        if(x-1>=0 && y+1>=matrix.get(x).size() && matrix.get(x-1).get(y+1) == 1) adjList.add(new XY(x-1,y+1));
        if(x+1<matrix.size() && y+1<matrix.get(x).size() && matrix.get(x+1).get(y+1) == 1) adjList.add(new XY(x+1,y+1));
        if(x+1<matrix.size() && y-1>=0 && matrix.get(x+1).get(y-1) == 1) adjList.add(new XY(x+1,y-1));

        return adjList;
    }

}
