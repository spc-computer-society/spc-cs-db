using System;
using System.IO;

namespace Program
{
    class Program
    {
        static void Main(string[] args)
        {
            try
            {
                using (StreamReader sr = new StreamReader("../_common/PrintText.txt"))
                {
                    string txt = sr.ReadToEnd();
                    Console.WriteLine(txt);
                }
            }
            catch (IOException e)
            {
                Console.WriteLine("The file could not be read:");
                Console.WriteLine(e.Message);
            }

            Console.ReadLine();
        }
    }
}
