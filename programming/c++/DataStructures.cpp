#include <iostream>
#include <vector>
#include <map>
#include <stack>
#include <queue>

int main()
{
    std::vector<std::string> vec //A better array, or generally speaking a list
    {
        "Alfa",
        "Bravo"
    };
    vec.emplace_back("Charles");
    std::cout << "Vector: " << std::endl;
    for (std::string name : vec)
    {
        std::cout << name << std::endl;
    }

    std::map<int, std::string> map //Data stored with a key, basically a dictionary
    {
        {1, "Delta"},
        {2, "Echo"}
    };
    map.emplace(3, "Foxtrot");
    std::cout << "Map: " << std::endl;
    for (std::pair<int, std::string> name : map)
    {
        std::cout << name.second << std::endl;
    }

    std::stack<std::string> stack; //Last in First out LIFO
    stack.push("Golf");
    stack.push("Hotel");
    stack.push("India");
    stack.pop();
    std::cout << "Stack: " << std::endl;
    while(stack.size() > 0)
    {
        std::string name = stack.top();
        stack.pop();
        std::cout << name << std::endl;
    }

    std::queue<std::string> queue; //First in First out FIFO
    queue.push("Juliett");
    queue.push("Kilo");
    queue.push("Lima");
    queue.pop();
    std::cout << "Queue: " << std::endl;
    while(queue.size() > 0)
    {
        std::string name = queue.front();
        queue.pop();
        std::cout << name << std::endl;
    }

    return 0;
}