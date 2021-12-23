package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recursion5 {
    public static void main(String[] args){
        ArrayList<Integer> inputArr = new ArrayList<>();
        inputArr.add(1);
        //inputArr.add(1);
        inputArr.add(2);
        inputArr.add(3);
        inputArr.add(4);
        ArrayList returnList = new ArrayList<>();
         //perm(inputArr,0,new ArrayList<>(),returnList);
        //combinations(inputArr,2,0,new ArrayList<>(),returnList);
        //swap(inputArr,0,new ArrayList<>(),returnList);
        //subset(inputArr,0,new ArrayList<>(),returnList);
       //List<String> list = (List<String>)Arrays.asList(generate_palindromic_decompositions("abracadabra"));
        //print(list);
        //System.out.println(returnList.size());
        //printc(4,0);
        //prints(4,0);
        //print(returnList);
        generateBrackets(3,0,0,new StringBuffer(),returnList);
        print(returnList);
    }
    public static void generateBrackets(int n,int lcount,int rcount,StringBuffer slate,ArrayList<String> rlist){
        if(slate.length() == n*2){
            rlist.add(slate.toString());
            return;
        }
        if(lcount <n){
            slate.append('(');
            generateBrackets(n,lcount+1,rcount,slate,rlist);
            slate.deleteCharAt(slate.length()-1);
        }
        if(rcount<lcount){
            slate.append(')');
            generateBrackets(n,lcount,rcount+1,slate,rlist);
            slate.deleteCharAt(slate.length()-1);
        }
    }
    public static void prints(int actn,int n){
        if(n == actn){
            return;
        }
            System.out.print(gtab(n)+n+" ");
            printc(actn,n+1);
    }
    public static void printc(int actn,int n){
        if(n == actn){
            return;
        }
        for (int i = n; i < actn; i++) {
            System.out.print(gtab(n)+n+" "+i);
            printc(actn,i+1);
        }
    }
    public static void printp(int actn,int n){
        if(n == actn){
            return;
        }
        for (int i = n; i < actn; i++) {
            System.out.print(gtab(n)+n+" "+i);
            printp(actn,n+1);
        }
    }
    public static void subset(ArrayList<Integer> inputArr,int n, ArrayList<Integer> slate,ArrayList returnList){
        if(n==inputArr.size()){
            returnList.add(new ArrayList<>(slate));
            return;
        }
            System.out.print(gtab(n)+n+" ");
           slate.add(inputArr.get(n));
           subset(inputArr,n+1,slate,returnList);
           slate.remove(slate.size()-1);
        while (n+1<inputArr.size() && inputArr.get(n+1) == inputArr.get(n)){
            n++;
        }
           subset(inputArr,n+1,slate,returnList);
    }
    public static void combinations(ArrayList<Integer> inputArr,int k,int n, ArrayList<Integer> slate,ArrayList returnList){
        if(k==0){
            returnList.add(new ArrayList<>(slate));
            return;
        }
        for(int i=n;i<inputArr.size();i++){
            System.out.print(gtab(n)+n+" "+i);
            slate.add(inputArr.get(i));
            combinations(inputArr,k-1,i+1,slate,returnList);
            slate.remove(slate.size()-1);
            while (i+1<inputArr.size() && inputArr.get(i+1) == inputArr.get(i)){
                i++;
            }
        }
    }

    public static void perm(ArrayList<Integer> inputArr,int n, ArrayList<Integer> slate,ArrayList returnList){
        if(slate.size() == inputArr.size()){
            returnList.add(new ArrayList<>(slate));
            return;
        }
        for(int i=n;i<inputArr.size();i++){
            System.out.print(gtab(n)+n+" "+i);;
            slate.add(inputArr.get(i));
            swap(inputArr,i,n);
            perm(inputArr,n+1,slate,returnList);
            slate.remove(slate.size()-1);
            swap(inputArr,i,n);
            while (i+1<inputArr.size() && inputArr.get(i+1) == inputArr.get(i)){
                i++;
            }
        }
    }

    static String[] generate_palindromic_decompositions(String s) {
        List<String> result = new ArrayList<>();
        String[][] palindromeMap = new String[s.length()][s.length()];
        generate_palindromic_decompositions_recursive(s.toCharArray(), result, new char[s.length() * 2], 0, 0, palindromeMap);
        for(int i=0;i<palindromeMap.length;i++){
            System.out.println();
            for (int j = 0; j <palindromeMap.length ; j++) {
                String s1 = (palindromeMap[i][j] != null?palindromeMap[i][j]:"0" );
                System.out.print(""+s1);
            }
        }
        return result.toArray(new String[result.size()]);
    }

    static void generate_palindromic_decompositions_recursive(char[] s, List<String> result, char[] slate, int index, int slateIx, String[][] palindromeMap) {
        if (index >= s.length) {
            result.add(new String(slate, 0, slateIx - 1));
            return;
        }
        for (int i = index; i < s.length; i++) {
            slate[slateIx++] = s[i];
            //System.out.print(gtab(index)+index+" "+i);
            if (isPalindrome(s, index, i, palindromeMap) == "Y") {
                slate[slateIx] = '|';
                generate_palindromic_decompositions_recursive(s, result, slate, i + 1, slateIx + 1, palindromeMap);
            }
        }
    }
    static String gtab(int index){
        String s ="\n";
        for(int i=0;i<index;i++){
            s +="\t";
        }
        return s;
    }

    static String isPalindrome(char[] s, int left, int right, String[][] palindromeMap) {
        if (palindromeMap[left][right] != null) {
            return palindromeMap[left][right];
        }
        String result = "Y";
        while (left < right) {
            if (s[left] != s[right]) {
                result = "N";
                break;
            }
            left++;
            right--;
        }
        palindromeMap[left][right] = result;
        return result;
    }

    public static void swap (ArrayList<Integer> inputArr,int index1,int index2){
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
        for (Object current:list
        ) {
            System.out.println(current);
        }
    }
    public static void print(List list){
        for (Object current:list
        ) {
            System.out.println(current);
        }
    }
}
