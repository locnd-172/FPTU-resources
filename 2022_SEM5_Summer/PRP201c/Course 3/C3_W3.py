import re

data = open("data.txt")
a = list()
for line in data:
    x = re.findall('[0-9]+', line)
    a = a + x;

sum = 0
for x in a:
    sum = sum + int(x)

print(sum)