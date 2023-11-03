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

#include <iostream>
#include <queue>
using namespace std;

struct TreeNode {
    int data;
    TreeNode* left;
    TreeNode* right;

    TreeNode(int val) {
        data = val;
        left = nullptr;
        right = nullptr;
    }
};

class BST {
private:
    TreeNode* root;

    TreeNode* insertRecursive(TreeNode* root, int val) {
        if (root == nullptr) {
            return new TreeNode(val);
        }
        if (val < root->data) {
            root->left = insertRecursive(root->left, val);
        } else {
            root->right = insertRecursive(root->right, val);
        }
        return root;
    }

    bool searchRecursive(TreeNode* root, int val) {
        if (root == nullptr) {
            return false;
        }
        if (val == root->data) {
            return true;
        } else if (val < root->data) {
            return searchRecursive(root->left, val);
        } else {
            return searchRecursive(root->right, val);
        }
    }

    TreeNode* findMinNode(TreeNode* root) {
        while (root->left != nullptr) {
            root = root->left;
        }
        return root;
    }

    TreeNode* deleteRecursive(TreeNode* root, int val) {
        if (root == nullptr) {
            return nullptr;
        }
        if (val < root->data) {
            root->left = deleteRecursive(root->left, val);
        } else if (val > root->data) {
            root->right = deleteRecursive(root->right, val);
        } else {
            // Node to be deleted found
            if (root->left == nullptr) {
                TreeNode* temp = root->right;
                delete root;
                return temp;
            } else if (root->right == nullptr) {
                TreeNode* temp = root->left;
                delete root;
                return temp;
            } else {
                // Node has two children
                TreeNode* minNode = findMinNode(root->right);
                root->data = minNode->data;
                root->right = deleteRecursive(root->right, minNode->data);
            }
        }
        return root;
    }

    void inOrderTraversalRecursive(TreeNode* root) {
        if (root != nullptr) {
            inOrderTraversalRecursive(root->left);
            cout << root->data << " ";
            inOrderTraversalRecursive(root->right);
        }
    }

    void preOrderTraversalRecursive(TreeNode* root) {
        if (root != nullptr) {
            cout << root->data << " ";
            preOrderTraversalRecursive(root->left);
            preOrderTraversalRecursive(root->right);
        }
    }

    void postOrderTraversalRecursive(TreeNode* root) {
        if (root != nullptr) {
            postOrderTraversalRecursive(root->left);
            postOrderTraversalRecursive(root->right);
            cout << root->data << " ";
        }
    }

    void levelOrderTraversal(TreeNode* root) {
        if (root == nullptr) {
            return;
        }

        queue<TreeNode*> q;// = queue<TreeNode*>;
        q.push(root);

        while (!q.empty()) {
            TreeNode* current = q.front();
            q.pop();
            cout << current->data << " ";

            if (current->left != nullptr) {
                q.push(current->left);
            }
            if (current->right != nullptr) {
                q.push(current->right);
            }
        }
    }

    void dfs(TreeNode* node) {
    if (node == nullptr) {
        return;
    }
  
    cout << node->data << " ";

    // Perform DFS on the left subtree
    dfs(node->left);

    // Perform DFS on the right subtree
    dfs(node->right);
}


    int getHeightRecursive(TreeNode* root) {
        if (root == nullptr) {
            return -1;
        }
        int leftHeight = getHeightRecursive(root->left);
        int rightHeight = getHeightRecursive(root->right);
        return max(leftHeight, rightHeight) + 1;
    }

    int getSizeRecursive(TreeNode* root) {
        if (root == nullptr) {
            return 0;
        }
        return getSizeRecursive(root->left) + getSizeRecursive(root->right) + 1;
    }

    TreeNode* findMinNodeRecursive(TreeNode* root) {
        if (root->left == nullptr) {
            return root;
        }
        return findMinNodeRecursive(root->left);
    }

public:
    BST() {
        root = nullptr;
    }

    void insert(int val) {
        root = insertRecursive(root, val);
    }

    bool search(int val) {
        return searchRecursive(root, val);
    }

    void remove(int val) {
        root = deleteRecursive(root, val);
    }

    void inOrderTraversal() {
        inOrderTraversalRecursive(root);
    }

    void preOrderTraversal() {
        preOrderTraversalRecursive(root);
    }

    void postOrderTraversal() {
        postOrderTraversalRecursive(root);
    }

    void levelOrderTraversal() {
        levelOrderTraversal(root);
    }

    void dfs() {
        dfs(root);
    }

    int getHeight() {
        return getHeightRecursive(root);
    }

    int getSize() {
        return getSizeRecursive(root);
    }

    int getMin() {
        if (root == nullptr) {
            cerr << "Error: Tree is empty." << endl;
            return -1; // Or any other appropriate value or error handling mechanism
        }
        TreeNode* minNode = findMinNodeRecursive(root);
        return minNode->data;
    }
};

int main() {
    BST bst;

    bst.insert(5);
    bst.insert(3);
    bst.insert(7);
    bst.insert(2);
    bst.insert(4);
    bst.insert(6);
    bst.insert(8);

    cout << "In-order traversal: ";
    bst.inOrderTraversal();
    cout << endl;

    cout << "Pre-order traversal: ";
    bst.preOrderTraversal();
    cout << endl;

    cout << "Post-order traversal: ";
    bst.postOrderTraversal();
    cout << endl;

    cout << "Level-order traversal: ";
    bst.levelOrderTraversal();
    cout << endl;

    cout << "DFS traversal: ";
    bst.dfs();
    cout << endl;


    cout << "Height of the tree: " << bst.getHeight() << endl;
    cout << "Size of the tree: " << bst.getSize() << endl;
    cout << "Minimum value in the tree: " << bst.getMin() << endl;

    cout << "Searching for value 4: " << (bst.search(4) ? "Found" : "Not found") << endl;

    bst.remove(3);
    cout << "In-order traversal after deleting node with value 3: ";
    bst.inOrderTraversal();
    cout << endl;

    return 0;
}
