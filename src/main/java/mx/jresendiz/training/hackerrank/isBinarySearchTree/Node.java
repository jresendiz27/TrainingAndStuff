package mx.jresendiz.training.hackerrank.isBinarySearchTree;

public class Node {
    int data;
    Node left;
    Node right;

    boolean checkBST(Node root) {
        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTree(Node root, int low, int high) {
        if (root == null) {
            return true;
        }
        if (root.data <= low || root.data >= high) {
            return false;
        }
        return isBinarySearchTree(root.left, low, root.data) &&
                isBinarySearchTree(root.right, root.data, high);
    }
}
