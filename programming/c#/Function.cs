using System;

namespace Program
{
    class Program
    {
        static string Info(string name, int age, string gender)
        {
            return $"Your name is {name}.\nYou are {age} years old.\nYou are {gender}.";
        }

        static void Main(string[] args)
        {
            string Charles = Info("Charles", 98, "Male");

            Console.WriteLine("The information of Charles is:\n" + Charles);

            Console.ReadLine();
        }
    }
}