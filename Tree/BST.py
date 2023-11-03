class Node:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None

class BST:
    def __init__(self):
        self.root = None

    def insert(self, data):
        self.root = self._insert_recursive(self.root, data)

    def _insert_recursive(self, root, data):
        if root is None:
            return Node(data)

        if data < root.data:
            root.left = self._insert_recursive(root.left, data)
        elif data > root.data:
            root.right = self._insert_recursive(root.right, data)

        return root

    def delete(self, data):
        self.root = self._delete_recursive(self.root, data)

    def _delete_recursive(self, root, data):
        if root is None:
            return root

        if data < root.data:
            root.left = self._delete_recursive(root.left, data)
        elif data > root.data:
            root.right = self._delete_recursive(root.right, data)
        else:
            if root.left is None:
                return root.right
            elif root.right is None:
                return root.left

            min_value = self._min_value(root.right)
            root.data = min_value
            root.right = self._delete_recursive(root.right, min_value)

        return root

    def _min_value(self, root):
        min_val = root.data
        while root.left is not None:
            min_val = root.left.data
            root = root.left
        return min_val

    def search(self, data):
        return self._search_recursive(self.root, data)

    def _search_recursive(self, root, data):
        if root is None:
            return False

        if data == root.data:
            return True
        elif data < root.data:
            return self._search_recursive(root.left, data)
        else:
            return self._search_recursive(root.right, data)

    def in_order_traversal(self):
        self._in_order_traversal_recursive(self.root)
        print()

    def _in_order_traversal_recursive(self, root):
        if root is not None:
            self._in_order_traversal_recursive(root.left)
            print(root.data, end=' ')
            self._in_order_traversal_recursive(root.right)

    def pre_order_traversal(self):
        self._pre_order_traversal_recursive(self.root)
        print()

    def _pre_order_traversal_recursive(self, root):
        if root is not None:
            print(root.data, end=' ')
            self._pre_order_traversal_recursive(root.left)
            self._pre_order_traversal_recursive(root.right)

    def post_order_traversal(self):
        self._post_order_traversal_recursive(self.root)
        print()

    def _post_order_traversal_recursive(self, root):
        if root is not None:
            self._post_order_traversal_recursive(root.left)
            self._post_order_traversal_recursive(root.right)
            print(root.data, end=' ')

    def level_order_traversal(self):
        if self.root is None:
            return

        queue = []
        queue.append(self.root)

        while queue:
            current = queue.pop(0)
            print(current.data, end=' ')

            if current.left is not None:
                queue.append(current.left)
            if current.right is not None:
                queue.append(current.right)

        print()

    def get_height(self):
        return self._get_height_recursive(self.root)

    def _get_height_recursive(self, root):
        if root is None:
            return 0

        left_height = self._get_height_recursive(root.left)
        right_height = self._get_height_recursive(root.right)

        return max(left_height, right_height) + 1

    def get_size(self):
        return self._get_size_recursive(self.root)

    def _get_size_recursive(self, root):
        if root is None:
            return 0

        left_size = self._get_size_recursive(root.left)
        right_size = self._get_size_recursive(root.right)

        return left_size + right_size + 1

    def get_minimum(self):
        if self.root is None:
            raise Exception("Tree is empty")

        current = self.root
        while current.left is not None:
            current = current.left

        return current.data

    def get_maximum(self):
        if self.root is None:
            raise Exception("Tree is empty")

        current = self.root
        while current.right is not None:
            current = current.right

        return current.data


bst = BST()
bst.insert(50)
bst.insert(30)
bst.insert(70)
bst.insert(20)
bst.insert(40)
bst.insert(60)
bst.insert(80)

print("In-order traversal:")
bst.in_order_traversal()

print("Pre-order traversal:")
bst.pre_order_traversal()

print("Post-order traversal:")
bst.post_order_traversal()

print("Level-order traversal:")
bst.level_order_traversal()

print("Height of the tree:", bst.get_height())

print("Size of the tree:", bst.get_size())

print("Minimum value in the tree:", bst.get_minimum())

print("Maximum value in the tree:", bst.get_maximum())

search_data = 60
if bst.search(search_data):
    print(search_data, "is found in the tree.")
else:
    print(search_data, "is not found in the tree.")

bst.delete(30)

print("In-order traversal after deleting 30:")
bst.in_order_traversal()
