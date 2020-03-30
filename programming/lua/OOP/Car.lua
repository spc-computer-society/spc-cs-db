--"Car" Class Initialize
Car = { Name = "hi", Brand = "hi" }

--Constructor
function Car:new(obj, aName, aBrand)
    obj = obj or {}
    setmetatable(obj, self)
    self.__index = self

    self.Name = aName --self. specify the current class object
    self.Brand = aBrand or "Toyota" --Optional parameter, default value
    return obj
end

--Class functions
function Car:PrintName()
    print("Name: ", self.Name)
end

camry = Car:new(nil, "Camry") --Create object
camry:PrintName() --Call function

cyberTruck = Car:new(nil ,"CyberTruck", "Tesla")
cyberTruck:PrintName()