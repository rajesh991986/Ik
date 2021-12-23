package recursion;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Recursion {
    public static void main(String[] args){
        int n = 4;
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        ArrayList returnList = new ArrayList<>();
       // subset("",arr,returnList);
        //subsetHelper(new ArrayList<>(),0,"abc",returnList);
        permuClass(new ArrayList<>(),0,new int[]{1,2,3},returnList);
        print(returnList);
    }

    public static void permuClass(ArrayList<Integer> slate,int n , int[] input , ArrayList returnList ){
        if(slate.size() == input.length){
            returnList.add(slate.clone());
            return;
        }
        for(int i =n;i<input.length;i++){
            swap(input,i,n);
            slate.add(input[n]);
            permuClass(slate,n+1,input,returnList);
            slate.remove(slate.size()-1);
            swap(input,n,i);
        }
    }

    public static void swap (int[] arr,int index1,int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
    public static void subsetHelper(ArrayList<Character> slate, int n ,String input ,ArrayList returnList ){
        if(n == input.length()){
            returnList.add(slate.toString());
            return;
        }
        subsetHelper(slate,n+1,input,returnList);
        slate.add(input.charAt(n));
        subsetHelper(slate,n+1,input,returnList);
        slate.remove(slate.size()-1);
    }

    public static void letterCasePermutation(String slate,String input,ArrayList retrunList){
        if(slate.length() == input.length()){
            retrunList.add(slate);
            return;
        }
        char current = input.charAt(slate.length());
        if(Character.isAlphabetic(current)){
            letterCasePermutation(slate+Character.toLowerCase(current),input,retrunList);
            letterCasePermutation(slate+Character.toUpperCase(current),input,retrunList);
        }
        else{
            letterCasePermutation(slate+current,input,retrunList);
        }
    }

    public static void combinationHelper(int n , StringBuffer slate,ArrayList<String> returnList ){
        if(n==0){
            returnList.add(slate.toString());
            return;
        }
        StringBuffer orginalSlate = new StringBuffer(slate);
        combinationHelper(n-1,orginalSlate.append("0"),returnList);
        orginalSlate.deleteCharAt(orginalSlate.length()-1);
        combinationHelper(n-1,orginalSlate.append("1"),returnList);
    }


    public static void subset(String slate,ArrayList<Integer> arr,ArrayList<String> retrunList){
        if(arr.size() == 0){
            retrunList.add(slate);
            return;
        }
        else{
            ArrayList<Integer> newList = (ArrayList<Integer>)arr.clone();
            newList.remove(0);
            subset(slate,newList,retrunList);
            subset(slate+arr.get(0),newList,retrunList);
        }
    }

    public static void print(ArrayList list){
        for (Object current:list
             ) {
            System.out.println(current);
        }
    }
    public static void permutationsDecimalNoRep(String slate, ArrayList<Integer> arr,ArrayList<String> retrunList){
        if(arr.size() == 0){
            retrunList.add(slate);
            return;
        }
        for (int i = 0; i < arr.size(); i++) {
            ArrayList<Integer> newArray = (ArrayList<Integer>) arr.clone();
            newArray.remove(i);
            permutationsDecimalNoRep(slate+arr.get(i),newArray,retrunList);
        }
    }
    public static void permStrings(String slate,String chars){
        if(chars.length() == 0){
            System.out.println(slate);
        }
        else{
            for(int i=0;i<chars.length();i++){
                permStrings(slate+chars.charAt(i),chars.substring(0,i) + chars.substring(i+1,chars.length()));
            }
        }
    }

    public static void decimalStrings(int n,String slate){
        if(n==0){
            System.out.println(slate);
        }
        else{
            for (int i = 0; i < 10; i++) {
                if(!slate.contains(""+i+""))
                decimalStrings(n-1,""+i+""+slate);
            }
        }
        return ;
    }
    public static void binaryStrings(int n,String slate){
        if(n==0){
            System.out.println(slate);
        }
        else{
            binaryStrings(n-1,"0"+slate);
            binaryStrings(n-1,"1"+slate);
        }
        return ;
    }
    public static void print(String[] arr){
        System.out.println("Print Array");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(","+arr[i]);
        }
    }
    public static String[] prepend(String prependValue,String[] slate){
        for (int i = 0; i <slate.length ; i++) {
            if(slate[i] == null){
                slate[i] = prependValue;
            }
            else {
                slate[i] = prependValue + slate[i];
            }
        }
        return slate;
    }

    // System.out.println(isPalindrom("abba"));
    public static boolean isPalindrom(String str){
        if(str.length() == 1 || str.length() == 0){
            return true;
        }
        if(str.charAt(0) == str.charAt(str.length()-1)){
            if(isPalindrom(str.substring(1,str.length()-1))){
                return true;
            }
        }
        return false;
    }
    /*
        int[] src = new int[]{0,1,2,3,4,5};
        int[] dest = new int[src.length];
        int[] temp = new int[src.length];
        towOfHanoi(src.length-1,src,dest,temp);
        print(dest);
    *
    * */
    public static void towOfHanoi(int n,int[] src,int[] dest,int[] temp){
        if(n == 1){
           // System.out.println("Print " + n + "Dest:" + dest);
            dest[n] = src[n];
        }
        else{
            towOfHanoi(n-1,src,temp,dest);
            dest[n]= src[n];
            //System.out.println("Print " + n + "Dest:" + dest);
            towOfHanoi(n-1,temp,dest,src);
        }
    }


//int[] arr = printFibonoci(10);
    //print(arr);

    public static int[] printFibonoci(int k){
        int[] arr = new int[k+1];
        if(k==0){
            arr[0] = 0;
            return arr;
        }
        arr[0] =0;
        arr[1] =1;
        for (int i = 2; i <= k ; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr;
    }

    public static void print(int[] arr){
        System.out.println("Print Array");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" "+arr[i]);
        }
    }
}
