using System;
using System.IO;

namespace Program
{
    class Program
    {
        static void Main(string[] args)
        {
            string currentDir = Directory.GetCurrentDirectory();
            string parentDir = Path.Combine(currentDir, "..");
            string path = parentDir + "\\_common\\PrinText.txt";
            
            try
            {
                using (StreamReader sr = new StreamReader(path))
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
