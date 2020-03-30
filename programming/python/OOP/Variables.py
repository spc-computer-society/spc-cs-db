class Foo:  # This is how you declare a class in python.

    # This is how you declare a class variable.
    class_var = "This is a class variable."

    def __init__(self, name):  # Every class starts with a function __init__. It automatically runs everytime you call the class.
        self.name = name  # This is how you declare an instance variable.

    def testprint(self):
        return self.name, self.class_var  # You have to start with "self." when you call a class/instance var.


Charles = Foo("Charles")  # You can put your arguments when calling a class. Arguments are stated in def __init__.
print(Charles.testprint())
