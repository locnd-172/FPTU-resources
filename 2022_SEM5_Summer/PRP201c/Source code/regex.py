# Search for lines that contain 'From'
import re
hand = open('mbox-short.txt')
for line in hand:
    line = line.rstrip()
    if re.search('From:', line):
        print(line)
    
print('------')
hand = open('mbox-short.txt')
for line in hand:
    line = line.rstrip()
    pos = line.find('From:')
    if (pos != -1):
        print(line)
        
# Search for lines that start with 'From'
hand = open('mbox-short.txt')
for line in hand:
    line = line.rstrip()
    if re.search('^From:', line):
        print(line)

# Character matching in regular expressions
# Search for lines that start with 'F', followed by 2 characters, followed by 'm:'
hand = open('mbox-short.txt')
for line in hand:
    line = line.rstrip()
    if re.search('^F..m:', line):
        print(line)
        
# Search for lines that start with From and have an at sign
hand = open('mbox-short.txt')
for line in hand:
    line = line.rstrip()
    if re.search('^From:.+@', line):
        print(line)
        
# Extracting data using regular expressions
s = 'A message from csev@umich.edu to cwen@iupui.edu about meeting @2PM'
lst = re.findall('\S+@\S+', s)
print(lst)
# Search for lines that have an at sign between characters
hand = open('mbox-short.txt')
for line in hand:
    line = line.rstrip()
    x = re.findall('\S+@\S+', line)
    if len(x) > 0:
        print(x)
# \S is asking to match the set of “non-whitespace characters”. 
# Square brackets are used to indicate a set of multiple acceptable characters we are willing to consider matching.
# Search for lines that have an at sign between characters
# The characters must be a letter or number
hand = open('mbox-short.txt')
for line in hand:
    line = line.rstrip()
    x = re.findall('[a-zA-Z0-9]\S*@\S*[a-zA-Z]', line)
    if len(x) > 0:
        print(x)

# Combining searching and extracting
# find numbers on lines that start with the string “X-” such as:
# X-DSPAM-Confidence: 0.8475
# X-DSPAM-Probability: 0.0000

# Search for lines that start with 'X' followed by any non
# whitespace characters and ':'
# followed by a space and any number.
# The number can include a decimal.
hand = open('mbox-short.txt')
for line in hand:
    line = line.rstrip()
    if re.search('^X\S*: [0-9.]+', line):
        print(line)
  
# when you are using findall(), parentheses indicate that while you want the
# whole expression to match, you only are interested in extracting a portion of the
# substring that matches the regular expression.      
# e.g: Search for lines that start with 'X' followed by any
# non whitespace characters and ':' followed by a space
# and any number. The number can include a decimal.
# Then print the number if it is greater than zero.
hand = open('mbox-short.txt')
for line in hand:
    line = line.rstrip()
    x = re.findall('^X\S*: ([0-9.]+)', line)
    if len(x) > 0:
        print(x)
   
# there are a number of lines of the form:     
# Details: http://source.sakaiproject.org/viewsvn/?view=rev&rev=39772
# Search for lines that start with 'Details: rev='
# followed by numbers
# Then print the number if one is found
hand = open('mbox-short.txt')
for line in hand:
    line = line.rstrip()
    x = re.findall('^Details:.*rev=([0-9]+)', line)
    if len(x) > 0:
        print(x)
        
# lines of the form:
# From stephen.marquard@uct.ac.za Sat Jan 5 09:14:16 2008
# extract hour of day -> regex: ^From .* [0-9][0-9]:
# In order to pull out only the hour using findall(), we add parentheses around the two digits as follows:
# -> regex: ^From .* ([0-9][0-9]):
#  Search for lines that start with From and a character
# followed by a two digit number between 00 and 99 followed by ':'
# Then print the number if one is found
hand = open('mbox-short.txt')
for line in hand:
    line = line.rstrip()
    x = re.findall('^From .* ([0-9][0-9]):', line)
    if len(x) > 0: print(x)
    

# Escape character
x = 'We just received $10.00 for cookies.'
y = re.findall('\$[0-9.]+',x)
print(y)