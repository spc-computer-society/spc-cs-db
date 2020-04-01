using System;
using System.Collections.Generic;

namespace Program
{
    class Program
    {
        static void Main(string[] args)
        {
            IList<string> list = new List<string>()
            {
                "Alfa",
                "Bravo",
                "Charlie"
            };

            //Loop through all item in a IEnumerable type class or array
            foreach (string item in list)
            {
                Console.WriteLine(item);
            }

            //Infinite loop
            for (; ; )
            {
                Console.WriteLine("Never gonna give you up!");
            }
        }
    }
}