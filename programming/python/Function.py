def info(name: str, age: int, gender: str):
    return (f"Your name is {name}.\n"
            f"Your are {age} years old.\n"
            f"You are {gender}.")


Charles = info("Charles", 98, "Male")
print("The information of Charles is:\n"
      f"{Charles}")