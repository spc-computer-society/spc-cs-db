using System;
using System.Collections.Generic;

namespace Program
{
    class Program
    {
        static void Main(string[] args)
        {
            IDictionary<int, string> dict = new Dictionary<int, string>() //Dictionary require a key and a value pair for easy search up
            {
                { 1, "Alfa" },
                { 2, "Bravo" }
            };
            dict.Add(3, "Charles");
            Console.WriteLine("Dictionary: ");
            foreach (KeyValuePair<int, string> item in dict)
            {
                Console.WriteLine(item.Key + ", " + item.Value);
            }

            IList<string> list = new List<string>() //List allows easy access of items by index
            {
                "Delta",
                "Echo"
            };
            list.Add("Foxtrot");
            Console.WriteLine("\nList: ");
            foreach (string item in list)
            {
                Console.WriteLine(item);
            }

            Queue<string> queue = new Queue<string>(); //Queue is first in first out (FIFO)
            queue.Enqueue("Golf");
            queue.Enqueue("Hotel");
            queue.Enqueue("India");
            queue.Dequeue();
            Console.WriteLine("\nQueue: ");
            foreach (string item in queue)
            {
                Console.WriteLine(item);
            }

            Stack<string> stack = new Stack<string>(); //Stack is last in first out (LIFO)
            stack.Push("Juliett");
            stack.Push("Kilo");
            stack.Push("Lima");
            stack.Pop();
            Console.WriteLine("\nStack: ");
            foreach (string item in stack)
            {
                Console.WriteLine(item);
            }

            Console.ReadLine();
        }
    }
}
