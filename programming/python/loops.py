import time

"""Loops"""

# For loop, for known amount of loop time.
for i in range(0, 10):  # 0 to 9
    print(i)

lst = ["apple", "banana", "pear", "grapes", "durian"]
for item in lst:
    print(item)

for value, item in enumerate(lst):  # enumerate() will return a tuple, it can be further decomposed into index and item.
    print(f"{value+1}: {item}")

[print(item) for item in lst]  # List comprehension. Works for any statements.

# While loop, for unknown/infinite loops.
count = 0
while True:  # Infinite loop
    count += 1
    time.sleep(1)
    print(count)
    if count >= 20:  # Breaks when if statement is met.
        break
