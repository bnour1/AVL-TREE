package avltree;
import java.util.*;

public class PrintTree {

    public static void printBinaryTree(TreeNode root) {
        if (root == null){
            System.out.println("A arvore está vaiza");
            return;
        }
        LinkedList<TreeNode> treeLevel = new LinkedList<TreeNode>();
        treeLevel.add(root);
        LinkedList<TreeNode> temp = new LinkedList<TreeNode>();
        int counter = 0;
        int height = root.getHeight()-1;
        //System.out.println(height);
        double numberOfElements = (Math.pow(2 , (height + 1)) - 1);
        //System.out.println(numberOfElements);
        while (counter <= height) {
            TreeNode removed = treeLevel.removeFirst();
            if (temp.isEmpty()) {
                printSpace(numberOfElements / Math.pow(2 , counter + 1), removed);
            } else {
                printSpace(numberOfElements / Math.pow(2 , counter), removed);
            }
            if (removed == null) {
                temp.add(null);
                temp.add(null);
            } else {
                temp.add(removed.getEsq());
                temp.add(removed.getDir());
            }
 
            if (treeLevel.isEmpty()) {
                System.out.println("");
                System.out.println("");
                treeLevel = temp;
                temp = new LinkedList<>();
                counter++;
            }
 
        }
    }
 
public static void printSpace(double n, TreeNode removed){
    for(;n>0;n--) {
            System.out.print("\t");
    }
    if(removed == null){
        System.out.print(" ");
    }
    else {
        System.out.print(removed.getInfo());
    }
}
 
public static int heightOfTree(TreeNode root){
    if(root==null){
        return 0;
    }
     return 1+ Math.max(heightOfTree(root.getEsq()),heightOfTree(root.getDir()));
}
 
}
    
