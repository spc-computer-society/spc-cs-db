def info(name: str, age: int, gender: str):  # Keyword: def defines a function. :str/:int, etc. are converters to only allow certain datatypes.
    return (f"Your name is {name}.\n"  # Keyword: return Returns the value to the caller. if no expressions, it returns None.
            f"Your are {age} years old.\n" # Putting "f"(f-string) before quotation marks allows {var}. 
            f"You are {gender}.")  # The "\n" skips a line.


Charles = info("Charles", 98, "Male")  # This gets the returned value.
print("The information of Charles is:\n"
      f"{Charles}")
