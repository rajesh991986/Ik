package sort.programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class findMedianSortedArrays {
    public static void main(String[] args){
       /* int[] num1  = new int[]{1,2,3,0,0,0};
        int[] num2  = new int[]{2,5,6};
        merge(num1,3,num2,3);
        for (int i = 0; i <num1.length ; i++) {
            System.out.println(num1[i]);
        }*/
        /*String[] strs = new String[]{"eat","tea","tan","ate","nat","bat"};
        List<List<String>> sortedList= groupAnagrams(strs);
        System.out.println(sortedList.toString());*/
        int[] nums = new int[]{4,5,6,7,0,1,2};
        System.out.println(search(nums,1));
    }
    public static int search(int[] nums, int target) {
        return subsearch(nums,target,0,nums.length-1);
    }

    public static int subsearch(int[] nums, int target,int start,int end ){
        if(nums[start] == target ){
            return start;
        }
        if(nums[end] == target ){
            return end;
        }
        int index =0;
        int mid = (start + end ) /2;
        if( target > nums[start] && target <= nums[mid] ){
           index = subsearch(nums,target,start,mid);
        }
        else{
           index = subsearch(nums,target,mid+1,end);
        }
        return  index;
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> sortedList = new ArrayList<>();
        HashMap<String,ArrayList> map = new HashMap<>();

        for (int i = 0; i < strs.length ; i++) {
            String string =strs[i];
            char[] chars =  string.toCharArray();
            Arrays.sort(chars);
            String sortedArray = new String(chars);
            if(!map.containsKey(sortedArray)){
                ArrayList list = new ArrayList();
                list.add(string);
                map.put(sortedArray,list);
            }
            else{
                ArrayList list = map.get(sortedArray);
                list.add(string);
                map.put(sortedArray,list);
            }
        }

        for (String keys:map.keySet()
        ) {
            sortedList.add(map.get(keys));
        }
        return sortedList;
    }
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i =m-1,j=n-1,k=(m+n)-1;

        for (int l=k; l >=0 ; l--) {
            if(j<0 || nums1[i]>nums2[j]){
                nums1[l] = nums1[i];
                i--;
            }
            else {
                nums1[l] = nums2[j];
                j--;
            }
        }
    }


}
