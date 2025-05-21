#include <stdio.h>

class Example
{
public:
    void main()
    {
        int a = 5;
        float b = 2.5;
        int result = a + b;
        printf("Result: %d\n", result);
    }
};

int main()
{
    Example example;
    example.main();
    return 0;
}


