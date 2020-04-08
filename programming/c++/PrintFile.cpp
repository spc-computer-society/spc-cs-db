#include <iostream>
#include <fstream>

int main()
{
    std::ifstream ifs("../_common/PrintText.txt");
    std::string content( (std::istreambuf_iterator<char>(ifs) ),
                       (std::istreambuf_iterator<char>()    ) );
    std::cout << content << std::endl;

    return 0;
}