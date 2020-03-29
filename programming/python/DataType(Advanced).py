lst = ["apple", "banana", 15]  # A list can store unordered values, duplicated values. Syntax: []
tpl = ("apple", "banana", 15)  # A tuple is like a list but immutable, meaning that it cannot be appended. Syntax: ()
st = {"apple", "banana", 15}  # A set is also like a list, but items cannot be duplicated and are unordered. Syntax: {}
# These are datatypes that are able to store multiple items. They are separated by ','.

print(lst, type(lst))
print(lst[0])

print(tpl, type(tpl))
print(tpl[1])

print(st, type(st))
# A set cannot be accessed through indexes since they are unordered. For loops are usually used in these cases.

dictionary = {"apple": 5, "banana": 3, 15: 2.84, "item": lst}
# A dictionary is a very useful datatype, which contains a key and a value separated by ':'.
# Each pair is separated by ','.
# The value of a dictionary can even be a list. This allows multiple values.

print(dictionary, type(dictionary))
print(dictionary["apple"])
print(dictionary[15])
print(dictionary["item"][1])
# This prints the value of key: item, which is a list.
# The [1] prints the item with index(1) of the list
