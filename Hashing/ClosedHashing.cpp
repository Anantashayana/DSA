#include <iostream>
#include <vector>
#include <string>

class HashTable {
private:
    std::vector<std::string> table;
    unsigned int tableSize;

public:
    HashTable(unsigned int size) : tableSize(size) {
        table.resize(tableSize, "");
    }

    unsigned int hashFunction(const std::string& input) {
        unsigned int hash = 0;
        for (char c : input) {
            hash = hash * 31 + c;
        }
        return hash % tableSize;
    }

    void insert(const std::string& input) {
        unsigned int index = hashFunction(input);
        while (!table[index].empty()) {
            ++index;
            if (index >= tableSize) {
                index = 0;
            }
        }
        table[index] = input;
    }

    bool search(const std::string& input) {
        unsigned int index = hashFunction(input);
        while (!table[index].empty()) {
            if (table[index] == input) {
                return true;
            }
            ++index;
            if (index >= tableSize) {
                index = 0;
            }
        }
        return false;
    }

    void display() {
        for (unsigned int i = 0; i < tableSize; ++i) {
            if (!table[i].empty()) {
                std::cout << "Index " << i << ": " << table[i] << std::endl;
            }
        }
    }
};

int main() {
    unsigned int tableSize;
    std::cout << "Enter the size of the hash table: ";
    std::cin >> tableSize;

    HashTable hashTable(tableSize);

    std::string input;
    std::cout << "Enter strings to insert (press Ctrl+Z or Ctrl+D to stop):" << std::endl;
    while (std::cin >> input) {
        hashTable.insert(input);
    }

    std::cout << std::endl << "Hash table contents:" << std::endl;
    hashTable.display();

    std::cout << std::endl << "Enter a string to search: ";
    std::cin >> input;
    bool found = hashTable.search(input);
    if (found) {
        std::cout << "String found in the hash table." << std::endl;
    } else {
        std::cout << "String not found in the hash table." << std::endl;
    }

    return 0;
}
