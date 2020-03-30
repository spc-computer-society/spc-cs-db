using System;

namespace Program
{
    class Car : Vehicle //Car class is a derived/child/sub class of Vehicle class (Inheritance)
    {
        public string Name { get; private set; } //public getter and private setter

        public string HonkSound { get; set; }//public getter and setter

        public string Brand { get; set; }

        private double _weight; //backup field
        public double Weight //property
        {
            get
            {
                return _weight;
            }
            private set
            {
                if (value > 0)
                {
                    _weight = value;
                }
            }
        }

        //Encapsulation
        //Apart from the following 2 access modifier, there are also protected, internal
        private bool CheckWeight(double aWeight) //private function for inside the class
        {
            return aWeight > 0;
        }

        public void Honk() //public for all scopes
        {
            Console.WriteLine("BEEP BEEP");
        }

        public override void DisplayType() //Override parent class function, use "new" modifier to not affect the parent function
        {
            Console.WriteLine("Car");
        }

        public Car(string aName, double aWeight, string aHonkSound = "BEEP", string aBrand = "Toyota", string aType = "Car") : base(aType) //Constructor
        {
            Name = aName;
            Weight = aWeight;
            HonkSound = aHonkSound;
            this.Brand = aBrand; //this. assign value to the class itself(Polymorphism), can be left out
        }
    }
}
