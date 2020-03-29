using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Hello
{
    class Vehicle
    {
        string Type { get; set; }

        public Vehicle(string aType = "Car")
        {
            Type = aType;
        }
    }
}
