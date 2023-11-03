#include <iostream>
#include <vector>
#include <list>
#include <string>

class HashTable {
private:
    std::vector<std::list<std::string>> table;
    unsigned int tableSize;

public:
    HashTable(unsigned int size) : tableSize(size) {
        table.resize(tableSize);
    }

    unsigned int hashFunction(const std::string& input) {
        unsigned int hash = 0;
        for (char c : input) {
            hash = ((hash << 5) + hash + c) + c;
        }
        return hash % tableSize;
    }

    void insert(const std::string& input) {
        unsigned int index = hashFunction(input);
        table[index].push_back(input);
    }

    void display() {
        for (unsigned int i = 0; i < tableSize; ++i) {
            std::cout << "Index " << i << ": ";
            for (const std::string& value : table[i]) {
                std::cout << value << " ";
            }
            std::cout << std::endl;
        }
    }
};

int main() {
    unsigned int tableSize;
    std::cout << "Enter the size of the hash table: ";
    std::cin >> tableSize;

    HashTable hashTable(tableSize);

    std::string input;
    std::cout << "Enter strings (press Ctrl+Z or Ctrl+D to stop):" << std::endl;
    while (std::cin >> input) {
        hashTable.insert(input);
    }


    std::cout << std::endl << "Hash table contents:" << std::endl;
    hashTable.display();

    return 0;
}
