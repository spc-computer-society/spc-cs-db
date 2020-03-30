file = io.open('HelloWorld.lua', 'r') --LUA has failed us in path functions, there is no way to get a path and get the parent path

print(file:read())

io.close(file)