#include <iostream>
#include <string>

void printDetails(const std::string& name, int age) {
    std::cout << "Name: " << name << ", Age: " << age << std::endl;
}

int main() {
    std::string name = "John";
    int age;

    printDetails(name, age);

    age = "Twenty-five";

    int* ptr;
    *ptr = 100;
    std::cout << *ptr << std::endl;

    return 0;
}
