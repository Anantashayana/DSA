A binary tree is BST if it satisfies any one of the following condition:
1. It is empty
2. It has no subtrees
3. For every node x in the tree all the keys (if any) in the left sub tree must be less than key(x) and all the keys (if
any) in the right sub tree must be greater than key(x).


## Deleting
Case 1: Node has no children (leaf node):
Set the parent's reference to the node to be deleted to null.
Free the memory occupied by the node (optional in some programming languages).

Case 2: Node has only one child:
Identify the non-null child of the node to be deleted.
Update the parent's reference to the node to be deleted with the child node.
Free the memory occupied by the node (optional in some programming languages).

Case 3: Node has two children:
- Find the successor or predecessor of the node to be deleted.
    Successor: The node with the smallest value in the right subtree (i.e., the leftmost node in the right subtree).
    Predecessor: The node with the largest value in the left subtree (i.e., the rightmost node in the left subtree).
- the value of the node to be deleted with the value of the successor or predecessor.
- Recursively delete the successor or predecessor node from its position in the subtree.

After the deletion, ensure that the BST properties are maintained:
If the deleted node had two children, the replacement value preserves the ordering property of the BST.
If the deleted node had one child or was a leaf node, the BST properties are still preserved.
It's important to handle all the different cases correctly to maintain the structure and ordering of the BST during the deletion process.






