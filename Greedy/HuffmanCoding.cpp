/*
Note that this example assumes the input message consists only of lowercase letters. You can modify the 
program to handle other characters or symbols by adjusting the data structures and input processing logic 
accordingly
*/

/*
Calculate the frequency of each symbol in the input message.
Build the Huffman tree based on the symbol frequencies:
a. Create leaf nodes for each symbol with their frequencies.
b. Create a priority queue (min-heap) and insert the leaf nodes into it.
c. While the priority queue has more than one node:
i. Extract the two nodes with the lowest frequencies from the priority queue.
ii. Create a new internal node with a frequency equal to the sum of the two extracted nodes' frequencies.
iii. Set the two extracted nodes as the left and right children of the new internal node.
iv. Insert the new internal node back into the priority queue.
d. The last remaining node in the priority queue is the root of the Huffman tree.
Generate Huffman codes for each symbol in the Huffman tree:
a. Start at the root of the tree.
b. Traverse the tree recursively:
i. If the current node is a leaf node, assign the current code to the symbol.
ii. For the left child, append "0" to the current code.
iii. For the right child, append "1" to the current code.
Encode the input message using the generated Huffman codes:
a. Traverse the input message character by character.
b. For each character, retrieve its corresponding Huffman code and append it to the encoded message.
Decode the encoded message using the Huffman tree:
a. Start at the root of the Huffman tree.
b. Traverse the encoded message bit by bit:
i. If the current bit is "0," move to the left child of the current node.
ii. If the current bit is "1," move to the right child of the current node.
iii. If the current node is a leaf node, append its symbol to the decoded message and return to the root.
Print the original message, encoded message, and decoded message.
*/

#include <iostream>
#include <queue>
#include <unordered_map>
#include <bitset>

using namespace std;

// Structure to represent a node in the Huffman tree
struct Node {
    char symbol;
    int frequency;
    Node* left;
    Node* right;

    Node(char symbol, int frequency) : symbol(symbol), frequency(frequency), left(nullptr), right(nullptr) {}
};

// Comparison function for priority queue
struct CompareNodes {
    bool operator()(Node* a, Node* b) {
        return a->frequency > b->frequency;
    }
};

// Function to build the Huffman tree
Node* buildHuffmanTree(const unordered_map<char, int>& frequencyMap) {
    priority_queue<Node*, vector<Node*>, CompareNodes> pq;

    // Create leaf nodes and push them into the priority queue
    for (const auto& pair : frequencyMap) {
        Node* newNode = new Node(pair.first, pair.second);
        pq.push(newNode);
    }

    // Build the Huffman tree by merging nodes
    while (pq.size() > 1) {
        Node* leftChild = pq.top();
        pq.pop();
        Node* rightChild = pq.top();
        pq.pop();

        Node* internalNode = new Node('\0', leftChild->frequency + rightChild->frequency);
        internalNode->left = leftChild;
        internalNode->right = rightChild;
        pq.push(internalNode);
    }

    // Return the root of the Huffman tree
    return pq.top();
}

// Function to generate Huffman codes for each symbol
void generateHuffmanCodes(Node* root, const string& currentCode, unordered_map<char, string>& huffmanCodes) {
    if (root == nullptr)
        return;

    // If the node is a leaf, assign the code
    if (root->left == nullptr && root->right == nullptr)
        huffmanCodes[root->symbol] = currentCode;

    // Recursively traverse the left and right subtrees
    generateHuffmanCodes(root->left, currentCode + "0", huffmanCodes);
    generateHuffmanCodes(root->right, currentCode + "1", huffmanCodes);
}

// Function to encode the input message using Huffman coding
string encodeMessage(const string& message, const unordered_map<char, string>& huffmanCodes) {
    string encodedMessage;
    for (char c : message)
        encodedMessage += huffmanCodes.at(c);
    return encodedMessage;
}

// Function to decode the encoded message using Huffman coding
string decodeMessage(const string& encodedMessage, Node* root) {
    string decodedMessage;
    Node* currentNode = root;

    for (char bit : encodedMessage) {
        if (bit == '0')
            currentNode = currentNode->left;
        else if (bit == '1')
            currentNode = currentNode->right;

        if (currentNode->left == nullptr && currentNode->right == nullptr) {
            decodedMessage += currentNode->symbol;
            currentNode = root;
        }
    }

    return decodedMessage;
}

int main() {
    string message = "hello world";

    // Step 1: Calculate the frequency of each symbol in the message
    unordered_map<char, int> frequencyMap;
    for (char c : message)
        frequencyMap[c]++;

    // Step 2: Build the Huffman tree
    Node* root = buildHuffmanTree(frequencyMap);

    // Step 3: Generate Huffman codes for each symbol
    unordered_map<char, string> huffmanCodes;
    generateHuffmanCodes(root, "", huffmanCodes);

    // Step 4: Encode the message using Huffman coding
    string encodedMessage = encodeMessage(message, huffmanCodes);

    // Step 5: Decode the encoded message using Huffman coding
    string decodedMessage = decodeMessage(encodedMessage, root);

    // Print the results
    cout << "Original message: " << message << endl;
    cout << "Encoded message: " << encodedMessage << endl;
    cout << "Decoded message: " << decodedMessage << endl;

    // Clean up memory
    delete root;

    return 0;
}
