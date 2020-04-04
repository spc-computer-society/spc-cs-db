"""
A Function is like a verb, it does things when it is called, and it returns values(if applicable).
In python, declaring a function is easy af. A def keyword is used.
You can also pass in parameters(Arguments) in a function.
"""


def ping():  # This declares a function without parameters. No Arguments are required to pass in when calling.
    print("I love Ping Pong!")


def student(name, cls, cls_no, gender):  # These are positional arguments. Caller inputs the value of them according to the positions.
    return ("Student Info:\n"  # The return keyword returns a value to the caller.
            f"Name: {name}\n"
            f"Class: {cls}\n"
            f"Class No.: {cls_no}\n"
            f"Gender: {gender}")


def foo():
    return  # If the returned value is left blank, the value would be None by default.


ping()  # Remember to add parenthesis even if there are no parameters!
print(student("Charles", "1D", 21, "Female"))  # This prints the returned value.
print(foo())  # This will return None as mentioned. If you come from other languages, None is equivalent to Null.
