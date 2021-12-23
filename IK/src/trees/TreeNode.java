package trees;

import java.lang.reflect.Array;
import java.util.*;

public class TreeNode{
    int key;
    TreeNode leftNode;
    TreeNode rightNode;
    int level = -1;
    TreeNode(){
    }
    TreeNode(int key){
        this.key = key;
        this.leftNode=null;
        this.rightNode=null;
    }
    public void preOrderIteration(TreeNode root){
        if(root == null) return;
        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> preOrderList = new ArrayList<>();
        while(null!=current || !stack.isEmpty()){
            while(null!=current){
                preOrderList.add(current.key);
                stack.add(current);
                current= current.leftNode;
            }
            current = stack.pop();
            current=current.rightNode;
        }
        System.out.println("Preorder Iteration : ");
        for(int i:preOrderList) System.out.println(i);
    }
    public void inOrderIteration(TreeNode root){
        if(root == null) return;
        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> preOrderList = new ArrayList<>();
        while(null!=current || !stack.isEmpty()){
            while(null!=current){
                stack.add(current);
                current= current.leftNode;
            }
            current = stack.pop();
            preOrderList.add(current.key);
            current=current.rightNode;
        }
        System.out.println("Preorder Iteration : ");
        for(int i:preOrderList) System.out.println(i);
    }
    public void bfsWithLevel(TreeNode root){
        if(root == null){
            return;
        }
        ArrayList<ArrayList<Integer>> returnList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        root.level = 0;
        while (!queue.isEmpty()){
            TreeNode currentNode = queue.poll();
            if(returnList.size() <= currentNode.level){
                returnList.add(currentNode.level,new ArrayList<Integer>());
            }
            returnList.get(currentNode.level).add(currentNode.key);
            if(currentNode.leftNode != null ){
                currentNode.leftNode.level = currentNode.level +1;
                queue.add(currentNode.leftNode);
            }
            if(currentNode.rightNode != null){
                currentNode.rightNode.level = currentNode.level +1;
                queue.add(currentNode.rightNode);
            }
        }
        for(ArrayList<Integer> current : returnList){
            System.out.println();
            for(Integer i:current){
                System.out.printf(","+i);
            }
        }
    }
    public void preOrder(TreeNode root){
        if(root==null){
            return;
        }
        System.out.println(root.key);
        preOrder(root.leftNode);
        preOrder(root.rightNode);
    }
    public void postOrder(TreeNode root){
        if(root==null){
            return;
        }
        postOrder(root.leftNode);
        postOrder(root.rightNode);
        System.out.println(root.key);
    }
    public void inOrder(TreeNode root){
        if(root==null){
            return;
        }
        inOrder(root.leftNode);
        System.out.println(root.key);
        inOrder(root.rightNode);
    }
    public void bfs(TreeNode root){
        if(root == null){
            return;
        }
        Queue<TreeNode> list = new LinkedList<>();
        list.add(root);
        while(!list.isEmpty()){
            TreeNode current = list.poll();
            if(null!=current) {
                list.add(current.leftNode);
                list.add((current.rightNode));
                System.out.println(current.key);
            }
        }

    }
    public TreeNode delete(TreeNode root,int target,TreeNode pre){
        if(root == null){
            return null;
        }
        //search target
        TreeNode current = root;
        TreeNode ancestor = null;
        while (current != null){
            if(target == current.key){
                break;
            }
            else if(target > current.key){
                pre=current;
                current = current.rightNode;
            }
            else{
                pre=current;
                ancestor = current;
                current = current.leftNode;
            }
        }
        if(current == null){
            return null;
        }
        //delete
        TreeNode nodeToReplace = null;
        if(current.leftNode == null && current.rightNode == null){
            if(pre.leftNode == current){
                pre.leftNode = null;
            }
            else{
                pre.rightNode =null;
            }
        }
        else if (current.leftNode == null){
            if(pre.leftNode == current){
                pre.leftNode = current.rightNode;
            }
            else{
                pre.rightNode =current.rightNode;
            }
        }
        else if (current.rightNode == null){
            if(pre.leftNode == current){
                pre.leftNode = current.leftNode;
            }
            else{
                pre.rightNode =current.leftNode;
            }
        }
        else if (current.leftNode != null && current.rightNode != null){
            //find successor
            TreeNode succ= current.rightNode;
            while(null !=succ.leftNode) succ = succ.leftNode;
            current.key = succ.key;
            delete(succ,succ.key,current.rightNode);
        }
        return root;
    }
    public TreeNode successor(TreeNode root,int target){
        TreeNode succ = successorHelper(root,target);
        if(null != succ) {
            System.out.println("Successor of " + target + " is " + succ.key);
        }
        else{
            System.out.println("Successor not found for "+target);
        }
        return succ;
    }
    public TreeNode successorHelper(TreeNode root,int target){
        if(root == null){
            return null;
        }
        TreeNode current = root;
        TreeNode ancestor = null;
        while (current != null){
            if(target == current.key){
                break;
            }
            else if(target > current.key){
                current = current.rightNode;
            }
            else{
                ancestor = current;
                current = current.leftNode;
            }
        }
        if(current == null){
            return null;
        }
        if(current.rightNode != null){
            current = current.rightNode;
            while (current.leftNode != null){
                current = current.leftNode;
            }
            return current;
        }
        else{
            return ancestor;
        }
    }

    public TreeNode predecessor(TreeNode root,int target){
        TreeNode pred = predecessorHelper(root,target);
        if(null != pred) {
            System.out.println("Predecessor of " + target + " is " + pred.key);
        }
        else{
            System.out.println("Predecessor not found for "+target);
        }
        return pred;
    }
    public TreeNode predecessorHelper(TreeNode root,int target){
        if(root == null){
            return root;
        }
        TreeNode current =root;
        TreeNode ancestor=null;
        while (current != null){
            if(current.key == target){
                break;
            }
            else if(current.key > target){
                current = current.leftNode;
            }
            else{
                ancestor = current;
                current = current.rightNode;
            }
        }
        if(current==null){
            return null;
        }
        if(current.leftNode == null){
            return ancestor;
        }
        else{
            current = current.leftNode;
            while (current.rightNode != null){
                current = current.rightNode;
            }
            return current;
        }
    }
    public TreeNode search(TreeNode root,int target){
        if(root == null){
            return root;
        }
        TreeNode current = root;
        while (current != null){
            if(target == current.key){
                System.out.println("Target "+target+" Found");
                return current;
            }
            else if(target > current.key){
                current = current.rightNode;
            }
            else{
                current = current.leftNode;
            }
        }
        System.out.println("Target "+target+" Not Found");
        return null;
    }
    public int max(TreeNode root){
        if(root == null){
            return -1;
        }
        TreeNode current = root;
        while (current.rightNode != null){
            current = current.rightNode;
        }
        return current.key;
    }
    public int min(TreeNode root){
        if(root == null){
            return -1;
        }
        TreeNode current = root;
        while (current.leftNode != null){
            current = current.leftNode;
        }
        return current.key;
    }
    public void print(TreeNode root){
       int treeHeight= treeHeight(root,0);
        System.out.println("Height : "+treeHeight + " : Max: " + max(root) + " :Min: "+min(root));
       int currentHeight=0;
       while (currentHeight<=treeHeight){
           System.out.println();
           printLevel(root,currentHeight);
           currentHeight++;
       }
    }
    public void printLevel(TreeNode node,int level){
        if(node == null){
            return;
        }
        if(level == 1){
            System.out.print("\t"+node.key);
        }
        printLevel(node.leftNode,level-1);
        printLevel(node.rightNode,level-1);
    }
    public int treeHeight(TreeNode root,int height){
        if(root == null){
            return height;
        }
        int leftHeight=  treeHeight(root.leftNode,height+1);
        int rightHeight = treeHeight(root.rightNode,height+1);
        if(leftHeight>rightHeight){
            return leftHeight;
        }
        return rightHeight;
    }
    public TreeNode insertNode(TreeNode root,int key){
        if(root == null){
            return new TreeNode(key);
        }
        TreeNode current = root;
        TreeNode prev = null;
        while (current !=null){
            if(current.key == key){
                return root;
            }
            else if(current.key > key){
                prev= current;
                current = current.leftNode;
            }
            else{
                prev = current;
                current = current.rightNode;
            }
        }
        if(prev!=null){
            if(prev.key > key){
                prev.leftNode = new TreeNode(key);
            }
            else{
                prev.rightNode = new TreeNode(key);
            }
        }
        return root;
    }

}

