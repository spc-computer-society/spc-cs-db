table = {"Alfa", "Bravo"} --only type of data structure in lua, basically array with advance functions
table["wow"] = "Chrales" --allows other data type as index
print("Table: ")
for i = 1, #table do --# will only get the number of integer index elements, and index start at 1
    print(table[i])
end
print(table["wow"])