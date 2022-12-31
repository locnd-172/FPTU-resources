# while 
n = 5
while n > 0:
    print(n)
    n = n - 1 # iteration variable
print('Blastoff!')

# infinite loop
# n = 10
# while True:
#     print(n, end=' ')
#     n = n - 1
# print('Done!')

while True:
    line = input('> ')
    if line == 'done':
        break
    print(line)
print('Done!')

while True:
    line = input('> ')
    if line[0] == '#':
        continue
    if line == 'done':
        break
    print(line)
print('Done!')

################
# for
friends = ['Joseph', 'Glenn', 'Sally']
for friend in friends:
    print('Happy New Year:', friend)
print('Done!')

# Counting and summing loops
count = 0
for itervar in [3, 41, 12, 9, 74, 15]:
    count = count + 1
print('Count: ', count)

# Maximum and minimum loops
largest = None
print('Before:', largest)
for itervar in [3, 41, 12, 9, 74, 15]:
    if largest is None or itervar > largest :
        largest = itervar
    print('Loop:', itervar, largest)
print('Largest:', largest)

def min_value(values):
    smallest = None
    for value in values:
        if smallest is None or value < smallest:
            smallest = value
    return smallest