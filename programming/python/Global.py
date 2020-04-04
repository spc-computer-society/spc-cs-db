"""
The global keyword is a special one in python. It can modify a variable outside of a function.
However, it is not recommended to use too often. A class and its class/instance variables should be used.
"""

signal = 0  # This is a variable declared outside of any functions.


def playmusic(album):
    global signal  # The global keyword allows functions to modify and read variables outside of the function.
    if signal == 0:
        print(f"Music of album {album} will now be played.")
        signal = 1  # It allows a function to modify it.


playmusic("Beatles")
print(signal)  # This shows that the variable signal has been modified after calling the function.
