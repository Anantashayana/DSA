#include <iostream>
#include <string>

unsigned int hashFunction(const std::string& input, unsigned int hashSize) {
    unsigned int hash = 0;
    for (char c : input) {
        hash = ((hash << 5) + hash + c) % hashSize;
    }
    return hash;
}

int main() {
    std::string input;
    std::cout << "Enter a string: ";
    std::getline(std::cin, input);

    unsigned int hashSize;
    std::cout << "Enter the size of the hash: ";
    std::cin >> hashSize;

    unsigned int hash = hashFunction(input, hashSize);
    std::cout << "Hash value: " << hash << std::endl;

    return 0;
}
