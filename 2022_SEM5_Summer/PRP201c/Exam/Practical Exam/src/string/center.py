sentence = "Python is awesome"

# returns the centered padded string of length 24 
new_string = sentence.center(24, '*')

print(new_string)

# Output: ***Python is awesome****


text = "Python is awesome"

# returns padded string by adding whitespace up to length 24
new_text = text.center(24)

print("Centered String: ", new_text)