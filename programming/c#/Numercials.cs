using System;

namespace Program
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine(420 + 69);
            Console.WriteLine(420 - 69);
            Console.WriteLine(420 * 69);
            //Integer Division
            Console.WriteLine(420 / 69);
            //Decimal Division (2 ways)
            Console.WriteLine((double)420 / 69);
            Console.WriteLine(Decimal.Divide(420, 69)); //returns Decimal class (more precise)
            Console.WriteLine(420 % 69);

            Console.ReadLine();
        }
    }
}