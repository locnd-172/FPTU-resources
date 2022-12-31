quote = 'Let it be, let it be, let it be'

# first occurance of 'let it'(case sensitive)
result = quote.find('let it')
print("Substring 'let it':", result)

# find returns -1 if substring not found
result = quote.find('small')
print("Substring 'small ':", result)

# How to use find()
if (quote.find('be,') != -1):
    print("Contains substring 'be,'")
else:
    print("Doesn't contain substring")

# rfind
# Example 1: rfind() With No start and end Argument
quote = 'Let it be, let it be, let it be'

result = quote.rfind('let it')
print("Substring 'let it':", result)

result = quote.rfind('small')
print("Substring 'small ':", result)

result = quote.rfind('be,')
if  (result != -1):
    print("Highest index where 'be,' occurs:", result)
else:
    print("Doesn't contain substring")