fout = open('output.txt', 'w')
line1 = "This here's the wattle,\n"
fout.write(line1)

line2 = 'the emblem of our land.\n'
fout.write(line2)

fout.close()

# DEBUG
s = '1 2\t 3\n 4'
print(s)
# 'repr': takes any object as an argument and returns a string representation of the object.
print(repr(s))