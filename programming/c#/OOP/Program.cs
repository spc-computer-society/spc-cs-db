using System;

namespace Hello
{
    class Program
    {
        static void Main(string[] args)
        {
            Car Camry = new Car("Camry", 1000, "DOOD DOOD"); //Optional parameters are automatically filled if it is left out
            Car CyberTruck = new Car("CyberTruck", 1100, aBrand: "Tesla"); //Named parameters allows parameters to be specifically assigned regardless of the order

            Console.WriteLine($"Cars: {Camry.Name}, {CyberTruck.Name}."); //Access Properties/Fields

            Camry.Honk(); //Call class function
        }
    }
}