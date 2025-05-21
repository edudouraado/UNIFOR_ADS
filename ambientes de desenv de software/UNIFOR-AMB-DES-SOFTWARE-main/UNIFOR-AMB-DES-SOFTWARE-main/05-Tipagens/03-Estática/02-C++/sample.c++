#include <iostream>
#include <string>

class MyClass
{
public:
    static void Main()
    {
        int number = 10;
        std::string message = "Hello, world!";

        std::cout << "Number: " << number << std::endl;
        std::cout << "Message: " << message << std::endl;
    }
};

int main()
{
    MyClass::Main();
    return 0;
}

