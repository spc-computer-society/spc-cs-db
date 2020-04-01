"""Someone can definitely explain better than me. I suck at explaining."""


class Test:
    class_var = "This is a class variable."

    def __init__(self, in_var):  # Constructor, or "initializer".
        self.in_var = in_var

    # These are instance methods lol. It takes in self as its first parameter.
    def func(self):
        return self.invar

    # Class methods are used when you need to change things in the class itself. For example, it can modify class
    # variables. Therefore, cls will be automatically passed in as the first parameter.
    @classmethod
    def cls_func(cls, new):
        cls.class_var = new  # This can change a class variable directly.

    # Static methods are used when it does not have anything to do with either the instance or the class.
    # It is just logically related. Therefore, it does not take in either cls or self as its first parameter.
    @staticmethod
    def static_func(msg):
        return f"Your msg is {msg}"
