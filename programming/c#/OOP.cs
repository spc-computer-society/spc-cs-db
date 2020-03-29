namespace Program
{
    class Program
    {
        Car Camry = new Car("Camry", 1000, "white"); //Optional parameters are automatically filled if it is left out
        Car CyberTruck = new Car("CyberTruck", 1100, aBrand: "Tesla"); //Named parameters allows parameters to be specifically assigned regardless of the order
    }

    class Car
    {
        public string Name { get; private set; } //public getter and private setter

        public string Color { get; set; }//public getter and setter

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

        public Car(string aName, double aWeight, string aColor = "black", string aBrand = "Toyota") //Constructor
        {
            Name = aName;
            Weight = aWeight;
            Color = aColor;
            Brand = aBrand;
        }
    }
}