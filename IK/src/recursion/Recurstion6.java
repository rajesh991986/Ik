package recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recurstion6 {
    public static void main(String[] args){
        ArrayList<Integer> inputArr = new ArrayList<>();
        inputArr.add(1);
        inputArr.add(3);
        inputArr.add(2);
        inputArr.add(4);
        ArrayList returnList = new ArrayList<>();

        //nChooseK(inputArr,0,2,new ArrayList<>(),returnList);
       // System.out.println(nChooseK(3,2));
       // nChooseK(inputArr,0,2,new ArrayList<>(),returnList);
       // System.out.println(catalinaNumb(4));
        quicksort(inputArr,0,inputArr.size()-1);
        print(inputArr);
    }
    public static void quicksort(ArrayList<Integer> inputArray,int start,int end ){
        if(start > end )
            return;
        int pivortIndex = start;
        int[] val = threewayPartition(inputArray,start,end,pivortIndex);
        quicksort(inputArray,start,val[0]-1);
        quicksort(inputArray,val[1],end);
    }
    public static int[] threewayPartition(ArrayList<Integer> inputArray,int start,int end,int pivortIndex){
        int l = start,r=start,u = end;
        while (r<=u){
            if(inputArray.get(pivortIndex)== inputArray.get(r)){
                r++;
            }
            else if (inputArray.get(pivortIndex)< inputArray.get(r)){
                swap(inputArray,r,u);
                u--;
            }
            else{
                l++;r++;
            }
        }
        return new int[]{l,r};
    }
    public static int catalinaNumb(int n){
        if(n==0 || n==1){
            if(n==0) return 1;
            if(n==1)return  1;
        }
        int count =0;
        for (int i = 2; i <= n ; i++) {
           count=0;
            for (int j = 0; j <i ; j++) {
                count = count + catalinaNumb(j) * catalinaNumb(i-j-1);
            }
        }
        return count;
    }
    public static int nChooseK(int n , int k){
        if(n<=1 || k == n || k==0) return 1;
        return nChooseK(n-1,k-1) + nChooseK(n,k-1);
    }

    public static void nChooseK(ArrayList<Integer> inputArr,int n, int k , ArrayList<Integer> slate,ArrayList returnList){
        if( k == 0 ){
            returnList.add(slate.clone());
            return;
        }
        for (int i = n; i < inputArr.size(); i++) {
            slate.add(inputArr.get(i));
            swap(inputArr,i,n);
            nChooseK(inputArr,i+1,k-1,slate,returnList);
            swap(inputArr,i,n);
            slate.remove(slate.size()-1);
            while (i+1<inputArr.size() && inputArr.get(i) == inputArr.get(i+1)){
                i=i+1;
            }
        }
    }

    public static void subSet(ArrayList<Integer> inputArr, int n , ArrayList<Integer> slate,ArrayList returnList){
        if(n==inputArr.size()){
            returnList.add(slate.clone());
            return;
        }

        slate.add(inputArr.get(n));
        subSet(inputArr,n+1,slate,returnList);
        slate.remove(slate.size()-1);
        while (n+1<inputArr.size() && inputArr.get(n) == inputArr.get(n+1)){
            n = n+1;
        }

        subSet(inputArr,n+1,slate,returnList);

    }
    public static void perm(ArrayList<Integer> inputArr, int n , ArrayList<Integer> slate,ArrayList returnList){
        if(inputArr.size() == slate.size()){
            returnList.add(slate.clone());
            return;
        }
        for (int i = n; i < inputArr.size() ; i++) {
            slate.add(inputArr.get(i));
            swap(inputArr,i,n);
            perm(inputArr,n+1,slate,returnList);
            swap(inputArr,n,i);
            slate.remove(slate.size()-1);
            while (i+1<inputArr.size() && inputArr.get(i) == inputArr.get(i+1)){
                i = i+1;
            }
        }
    }

    public static void swap (ArrayList<Integer> inputArr, int index1, int index2){
        int temp = inputArr.get(index1);
        inputArr.set(index1, inputArr.get(index2));
        inputArr.set(index2, temp);
    }
    public static void swap (int[] arr,int index1,int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
    public static void print(ArrayList list){
        System.out.println("Size : "+list.size());
        for (Object current:list
        ) {
            System.out.println(current);
        }
    }
    public static void print(List list) {
        System.out.println("Size : "+list.size());
        for (Object current : list
        ) {
            System.out.println(current);
        }
    }
}
