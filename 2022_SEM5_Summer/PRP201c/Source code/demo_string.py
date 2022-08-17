fruit = 'banana'
letter = fruit[1]
print(letter)

# Traversal through a string with a loop
index = 0
while index < len(fruit):
    letter = fruit[index]
    print(letter)
    index = index + 1

for char in fruit:
    print(char)
    
# String slices
s = 'Monty Python'
print(s[0:5]) 
print(s[6:12])

fruit = 'banana'
print(fruit[:3])  # 'ban'
print(fruit[3:])  # 'ana'
print(fruit[3:3])  # ''
print(fruit[:])  # 'banana'

# Strings are immutable
# >>> greeting = 'Hello, world!'
# >>> greeting[0] = 'J'
# TypeError: 'str' object does not support item assignment

greeting = 'Hello, world!'
new_greeting = 'J' + greeting[1:]
print(new_greeting)  # Jello, world!

# 'in' operator
print('a' in 'banana')

# String comparison
word = 'Pineapple'
if word < 'banana':
    print('Your word,' + word + ', comes before banana.')
elif word > 'banana':
    print('Your word,' + word + ', comes after banana.')
else:
    print('All right, bananas.')
    
# Python does not handle uppercase and lowercase letters the same way that people
# do. All the uppercase letters come before all the lowercase letters, so:
# >>> Your word, Pineapple, comes before banana.

# String methods
# Strings are an example of Python objects. An object contains both data (the actual string itself) and methods,
# Python has a function called dir which lists the methods available for an object.
stuff = 'Hello world'
print(type(stuff))
print(dir(stuff))
print(help(str.capitalize))

new_word = stuff.upper()
print(stuff.split())
print(new_word)

word = 'banana'
index = word.find('a')
print(index)
print(word.find('na'))
print(word.find('na', 3))  # second argument the index where it should start

line = ' Here we go '
print(line.strip())  # 'Here we go'

# count 
string = "Python is awesome, isn't it?"
substring = "is"
count = string.count(substring)
print("The count is:", count)

# rindex()
quote = 'Let it be, let it be, let it be'
result = quote.rindex('let it')
print("Substring 'let it':", result)

# PARSE STRING
data = 'From stephen.marquard@uct.ac.za Sat Jan 5 09:14:16 2008'
atpos = data.find('@')
print(atpos)  # 21
sppos = data.find(' ', atpos)
print(sppos)  # 31
host = data[atpos + 1:sppos]
print(host)  # uct.ac.za

# FORMAT OPERATOR
# %d decimal 
# %g floating-point numbers
# %s string
camels = 42
print('%d' % camels)
print('I have spotted %d camels.' % camels)
print('In %d years I have spotted %g %s.' % (3, 0.1, 'camels'))