sentence1 = "THIS SHOULD ALL BE LOWERCASE."
print(sentence1.swapcase())

sentence2 = "this should all be uppercase."
print(sentence2.swapcase())

# converts lowercase to uppercase and vice versa
sentence3 = "ThIs ShOuLd Be MiXeD cAsEd."
print(sentence3.swapcase())

# ====================
text = "groß "

# converts text to uppercase
print(text.swapcase()) 

# performs swapcase() on text.swapcase() 
print(text.swapcase().swapcase()) 

print(text.swapcase().swapcase() == text) 
