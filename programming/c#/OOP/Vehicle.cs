namespace Program
{
    class Vehicle
    {
        string Type { get; set; }

        public Vehicle(string aType = "Car")
        {
            Type = aType;
        }

        public void DisplayType()
        {
            Conosle.WriteLine(Type);
        }
    }
}
