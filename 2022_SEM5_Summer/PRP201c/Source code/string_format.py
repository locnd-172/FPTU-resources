txt = "For only {price:.2f} dollars!"
print(txt.format(price = 49)) # For only 49.00 dollars!


txt1 = "My name is {fname}, I'm {age}".format(fname = "John", age = 36)
txt2 = "My name is {0}, I'm {1}".format("John",36)
txt3 = "My name is {}, I'm {}".format("John",36)

print(txt1)
print(txt2)
print(txt3)

# get ASCII Value
var = 'a'
print(ord(var), print(ord('a')))

# get Unicode from ASCII value
c = 97
print(chr(c), print(chr(104)))



# ======================
# Formatting Types

# :<    Left aligns the result (within the available space)
txt = "We have {:<8} chickens."
print(txt.format(49))

# :>    Right aligns the result (within the available space)
txt = "We have {:>8} chickens."
print(txt.format(49))

# :^    Center aligns the result (within the available space)
txt = "We have {:^8} chickens."
print(txt.format(49))

# :=    Places the sign to the left most position
txt = "The temperature is {:=8} degrees celsius."
print(txt.format(-5))

# :+    Use a plus sign to indicate if the result is positive or negative
txt = "The temperature is between {:+} and {:+} degrees celsius."
print(txt.format(-3, 7))

# :-    Use a minus sign for negative values only
txt = "The temperature is between {:-} and {:-} degrees celsius."
print(txt.format(-3, 7))

# :     Use a space to insert an extra space before positive numbers (and a minus sign before negative numbers)
txt = "The temperature is between {: } and {: } degrees celsius."
print(txt.format(-3, 7))

# :,    Use a comma as a thousand separator
txt = "The universe is {:,} years old."
print(txt.format(13800000000))

# :_    Use a underscore as a thousand separator
txt = "The universe is {:_} years old."
print(txt.format(13800000000))

# :b    Binary format
txt = "The binary version of {0} is {0:b}"
print(txt.format(5))

# :c    Converts the value into the corresponding unicode character

# :d    Decimal format
txt = "We have {:d} chickens."
print(txt.format(0b101))

# :e    Scientific format, with a lower case e
txt = "We have {:e} chickens."
print(txt.format(5))

# :E    Scientific format, with an upper case E
txt = "We have {:E} chickens."
print(txt.format(5))

# :f    Fix point number format
txt = "The price is {:.2f} dollars."
print(txt.format(45))
#without the ".2" inside the placeholder, this number will be displayed like this:
txt = "The price is {:f} dollars."
print(txt.format(45))

# :F    Fix point number format, in uppercase format (show inf and nan as INF and NAN)
x = float('inf')
txt = "The price is {:F} dollars."
print(txt.format(x))
#same example, but with a lower case f:
txt = "The price is {:f} dollars."
print(txt.format(x))

# :g    General format

# :G    General format (using a upper case E for scientific notations)

# :o    Octal format
txt = "The octal version of {0} is {0:o}"
print(txt.format(10))

# :x    Hex format, lower case
txt = "The Hexadecimal version of {0} is {0:x}"
print(txt.format(255))

# :X    Hex format, upper case
txt = "The Hexadecimal version of {0} is {0:X}"
print(txt.format(255))

# :n    Number format

# :%    Percentage format
txt = "You scored {:%}"
print(txt.format(0.25))
#Or, without any decimals:
txt = "You scored {:.0%}"
print(txt.format(0.25))