#include <iostream>
#include <string>
#include <sstream>

std::string Info(std::string name, int age, std::string gender)
{
    std::ostringstream str;
    str << "Your name is " << name << ".\nYou are " << age << " years old.\nYou are " << gender << "."; 
    return str.str();
}

int main()
{
    std::cout << Info("Charles", 98, "Male") << std::endl;

    return 0;
}