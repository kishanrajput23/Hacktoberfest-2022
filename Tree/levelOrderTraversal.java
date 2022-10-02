package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class levelOrderTraversal {
    private Node root;
    private static class Node{
        private int data;
        private Node left;
        private Node right;
        public Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;

        }
    }

    //    Method to Creating a Tree
/*
                1
              /   \
             2     3
           /  \   /  \
          4    5  6   7
 */

    public void createTree(){
        Node first = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);
        Node sixth = new Node(6);
        Node seventh = new Node(7);

        root = first;
        first.left = second;
        first.right = third;

        second.left = fourth;
        second.right = fifth;

        third.left = sixth;
        third.right = seventh;
    }

//    Method for Level Order Traversal.
    public void levelOrder(Node root){
        if(root == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            Node temp = queue.poll();
            System.out.print(temp.data + " ");
            if(temp.left != null){
                queue.offer(temp.left);
            }
            if(temp.right != null){
                queue.offer(temp.right);
            }
        }
    }


    public static void main(String[] args) {
        levelOrderTraversal tree = new levelOrderTraversal();
        tree.createTree();
        System.out.println("Level Order Traversal Of a Given Binary Tree :");
        tree.levelOrder(tree.root);
    }
}
