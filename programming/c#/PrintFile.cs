using System;
using System.IO;

namespace Program
{
    class Program
    {
        static void Main(string[] args)
        {
            string currentDir = Directory.GetCurrentDirectory();
            string currentDirInfo = new DirectoryInfo(currentDir)
            string grandParentDir = currentDirInfo.Parent.Parent.FullName;
            string path = grandParentDir + @"\_common\PrinText.txt";
            
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
