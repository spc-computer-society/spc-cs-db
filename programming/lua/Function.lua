function info(name, age, gender)
    return string.format("Your name is %s.\nYou are %d years old.\nYou are %s", name, age, gender)
end

Charles = info("Charles", 98, "Male")
print("The information of Charles is:\n "..Charles)