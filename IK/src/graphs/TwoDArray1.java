package graphs;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TwoDArray1 {
    /*
     * Complete the function below.
     */
    static class XY{
        int x;
        int y;
        char val;
        int key;
        XY parent;
        XY(){};
        XY(int a,int b,char c){
            x = a;
            y=b;
            val = c;
        }
        XY(int a,int b,char c,XY p){
            x = a;
            y=b;
            val = c;
            parent=p;
        }
    }
    static boolean visisted[][][];
    static char[][] grid;
    static int[][] find_shortest_path(String[] inputGrid) {
        int m =inputGrid.length;
        int n = inputGrid[0].length();
        grid = new char[m][n];
        visisted= new boolean[m][n][1<<10];
        buildGrid(inputGrid);
        Queue<XY> queue = new LinkedList<XY>();
        queue.add(findStart());
        while(!queue.isEmpty()){
            XY current = queue.poll();
            visisted[current.x][current.y][current.key]=true;
            ArrayList<XY> adjList = findAdj(current);
            for(XY adj:adjList){
                if(!isValid(adj,current)){
                    continue;
                }
                if(adj.val == '+'){
                    List<int[]> result = new ArrayList<>();
                    result.add(new int[]{adj.x,adj.y});
                    XY parent=adj.parent;
                    while(null != parent){
                        result.add (new int[]{parent.x,parent.y});
                        parent = parent.parent;
                    }
                    Collections.reverse(result);
                    return result.toArray(new int[result.size()][]);
                }
                if(!visisted[adj.x][adj.y][adj.key]){
                    adj.parent = current;
                    queue.add(adj);
                    visisted[adj.x][adj.y][adj.key] = true;
                }
            }
        }
        return (new int[0][0]);
    }
    static boolean isValid(XY adj,XY parent){
        if(adj.val == '#'){
            return false;
        }
        if(adj.val >= 'A' && adj.val <='J'){
            if((parent.key >> (adj.val -'A') & 1)==0){
                return false ;
            }
        }
        adj.key = parent.key;
        if(adj.val >='a' && adj.val <='j'){
            int nextKey = 1<< adj.val - 'a';
            adj.key |= nextKey;
        }
        return true;
    }
    static ArrayList<XY> findAdj(XY cloc){
        int x= cloc.x;
        int y= cloc.y;
        ArrayList<XY> adjList = new ArrayList();
        if(x-1>=0&& (grid[x-1][y] !='#')) { addXY(x-1,y,adjList,cloc); }
        if(x+1<grid.length && (grid[x+1][y] !='#')) addXY(x+1,y,adjList,cloc);;
        if(y-1>=0&& (grid[x][y-1] !='#')) addXY(x,y-1,adjList,cloc);
        if(y+1<grid[x].length && (grid[x][y+1] !='#')) addXY(x,y+1,adjList,cloc);
        return adjList;
    }
    static void addXY(int x,int y,ArrayList<XY> adjList,XY cloc) {
        XY newXY = new XY(x,y,grid[x][y],cloc);
        adjList.add(newXY);
        return ;
    }
    static void buildGrid(String[] inputGrid){
        for(int i=0;i<inputGrid.length;i++){
            for(int j=0;j<inputGrid[i].length();j++){
                grid[i][j]=inputGrid[i].charAt(j);
            }
        }
    }
    static XY findStart(){
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j] == '@'){
                    return new XY(i,j,'@');
                }
            }
        }
        return null;
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] res;

        String[] grid = new String[3];
        grid[0]="...B";
        grid[1]=".b#.";
        grid[2]="@#+.";
        res = find_shortest_path(grid);
        int res_rowLength = res.length;
        for(int res_i = 0; res_i < res_rowLength; res_i++) {
            for(int res_j = 0; res_j < res[res_i].length; res_j++) {
                bw.write(String.valueOf(res[res_i][res_j]) + " ");
            }
            bw.newLine();
        }

        bw.close();
    }
}
