/* 
   This program demonstrates the implementation of a Binary Search Tree (BST) in C++.
   
   The program creates a BST and performs various operations on it, including:
   - Insertion: Adds new nodes to the tree.
   - Deletion: Removes nodes from the tree.
   - Searching: Finds specific nodes in the tree.
   - Traversal: Visits all nodes in the tree in specific orders (in-order, pre-order, post-order, level-order(BFS)) and DFS.
   - Height/Depth: Calculates the maximum number of edges from the root to a leaf.
   - Size: Counts the total number of nodes in the tree.
   - Minimum/Maximum: Finds the minimum or maximum value in the tree.

   The main function demonstrates the usage of these operations.

   Note: This is a basic implementation assuming unique values in the tree. Additional functionality 
   and error handling can be added as per specific requirements.
*/


import java.util.LinkedList;
import java.util.Queue;

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BST {
    private Node root;

    public BST() {
        root = null;
    }

    public void insert(int data) {
        root = insertRecursive(root, data);
    }

    private Node insertRecursive(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data) {
            root.left = insertRecursive(root.left, data);
        } else if (data > root.data) {
            root.right = insertRecursive(root.right, data);
        }

        return root;
    }

    public void delete(int data) {
        root = deleteRecursive(root, data);
    }

    private Node deleteRecursive(Node root, int data) {
        if (root == null) {
            return root;
        }

        if (data < root.data) {
            root.left = deleteRecursive(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRecursive(root.right, data);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = minValue(root.right);
            root.right = deleteRecursive(root.right, root.data);
        }

        return root;
    }

    private int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    public boolean search(int data) {
        return searchRecursive(root, data);
    }

    private boolean searchRecursive(Node root, int data) {
        if (root == null) {
            return false;
        }

        if (data == root.data) {
            return true;
        } else if (data < root.data) {
            return searchRecursive(root.left, data);
        } else {
            return searchRecursive(root.right, data);
        }
    }

    public void inOrderTraversal() {
        inOrderTraversalRecursive(root);
        System.out.println();
    }

    private void inOrderTraversalRecursive(Node root) {
        if (root != null) {
            inOrderTraversalRecursive(root.left);
            System.out.print(root.data + " ");
            inOrderTraversalRecursive(root.right);
        }
    }

    public void preOrderTraversal() {
        preOrderTraversalRecursive(root);
        System.out.println();
    }

    private void preOrderTraversalRecursive(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrderTraversalRecursive(root.left);
            preOrderTraversalRecursive(root.right);
        }
    }

    public void postOrderTraversal() {
        postOrderTraversalRecursive(root);
        System.out.println();
    }

    private void postOrderTraversalRecursive(Node root) {
        if (root != null) {
            postOrderTraversalRecursive(root.left);
            postOrderTraversalRecursive(root.right);
            System.out.print(root.data + " ");
        }
    }

    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.data + " ");

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        System.out.println();
    }

    public int getHeight() {
        return getHeightRecursive(root);
    }

    private int getHeightRecursive(Node root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeightRecursive(root.left);
        int rightHeight = getHeightRecursive(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int getSize() {
        return getSizeRecursive(root);
    }

    private int getSizeRecursive(Node root) {
        if (root == null) {
            return 0;
        }

        int leftSize = getSizeRecursive(root.left);
        int rightSize = getSizeRecursive(root.right);

        return leftSize + rightSize + 1;
    }

    public int getMinimum() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }

        Node current = root;
        while (current.left != null) {
            current = current.left;
        }

        return current.data;
    }

    public int getMaximum() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }

        Node current = root;
        while (current.right != null) {
            current = current.right;
        }

        return current.data;
    }
// }

// public class Main {
    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.println("In-order traversal:");
        bst.inOrderTraversal();

        System.out.println("Pre-order traversal:");
        bst.preOrderTraversal();

        System.out.println("Post-order traversal:");
        bst.postOrderTraversal();

        System.out.println("Level-order traversal:");
        bst.levelOrderTraversal();

        System.out.println("Height of the tree: " + bst.getHeight());

        System.out.println("Size of the tree: " + bst.getSize());

        System.out.println("Minimum value in the tree: " + bst.getMinimum());

        System.out.println("Maximum value in the tree: " + bst.getMaximum());

        int searchData = 60;
        if (bst.search(searchData)) {
            System.out.println(searchData + " is found in the tree.");
        } else {
            System.out.println(searchData + " is not found in the tree.");
        }

        bst.delete(30);

        System.out.println("In-order traversal after deleting 30:");
        bst.inOrderTraversal();
    }
}
