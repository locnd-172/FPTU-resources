# Example 1: Encode to Default Utf-8 Encoding
# unicode string
string = 'pythön!'

# print string
print('The string is:', string)

# default encoding to utf-8
string_utf = string.encode()

# print result
print('The encoded version is:', string_utf)


# Example 2: Encoding with error parameter
# unicode string
string = 'pythön!'

# print string
print('The string is:', string)

# ignore error
print('The encoded version (with ignore) is:', string.encode("ascii", "ignore"))

# replace error
print('The encoded version (with replace) is:', string.encode("ascii", "replace"))