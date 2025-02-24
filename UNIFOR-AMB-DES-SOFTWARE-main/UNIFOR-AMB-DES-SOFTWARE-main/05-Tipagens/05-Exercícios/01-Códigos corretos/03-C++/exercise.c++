#include <iostream>
#include <string>

void printDetails(const std::string& name, int age) {
    std::cout << "Name: " << name << ", Age: " << age << std::endl;
}

int main() {
    std::string name = "John";
    int age;

    printDetails(name, age);  // Erro: 'age' não inicializado

    // Tentativa de atribuir um valor incorreto
    age = "Twenty-five";  // Erro: incompatibilidade de tipo

    // Uso incorreto de ponteiro e tipo
    int* ptr;
    *ptr = 100;  // Erro: ponteiro não inicializado
    std::cout << *ptr << std::endl;

    return 0;
}
