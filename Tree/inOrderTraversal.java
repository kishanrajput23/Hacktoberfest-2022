package Tree;

import java.util.Stack;

public class inOrderTraversal {
        private Node root;

        private static class Node{
            private int data;
            private Node left;
            private Node right;

            public Node(int data){
                this.data = data;
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


        public void createBinaryTree(){
            Node first = new Node(1);
            Node second = new Node(2);
            Node third = new Node(3);
            Node fourth = new Node(4);
            Node fifth = new Node(5);
            Node sixth = new Node(6);
            Node seventh = new Node(7);

            root = first;  //root --> first;

            // second <----- first -----> third.
            first.left = second;
            first.right = third;

            // fourth <------ second -------> fifth.
            second.left = fourth;
            second.right = fifth;

            //sixth <---------third ----------> seventh
            third.left = sixth;
            third.right = seventh;
        }
    /*
    InOrder Traversal of A Binary Tree :- left child -> root -> right child.
     */

        //Recursive method for Inorder Traversal of a Binary Tree
        public void inOrder(Node root){

            if(root == null)
                return;

            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);

        }

//        Method for inorder Traversing of the binary tree
        public void iterativeInOrder(Node root){
            if(root == null){
                return;
            }
            Stack<Node> stack = new Stack<>();
            Node temp = root;
            while(!stack.isEmpty() || temp != null){
                if(temp != null){
                    stack.push(temp);
                    temp = temp.left;
                }else{
                    temp = stack.pop();
                    System.out.print(temp.data + " ");
                    temp = temp.right;
                }
            }
        }

//Main method
        public static void main(String[] args) {
            inOrderTraversal tree = new inOrderTraversal();
            tree.createBinaryTree();
            System.out.println();
            System.out.print("InOrder Traversal of the Binary tree is : ");
//            tree.inOrder(tree.root);
            tree.iterativeInOrder(tree.root);
        }
}

