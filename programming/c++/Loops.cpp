#include <iostream>
#include <vector>

int main()
{
    std::vector<std::string> vec
    {
        "Alfa",
        "Bravo",
        "Charlie"
    };

    //Loop through all item in a data structure (array can't)
    for (std::string name : vec)
    {
        std::cout << name << std::endl;
    }

    //Infinite Loop
    for (;;)
    {
        std::cout << "Never gonna let you down!" << std::endl;
    }
}