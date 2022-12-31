# Method 1: By value 
a = "one"
b = "two"
c = "three"

d = {a: 1, b: 2, c:3}

print(d)

d = sorted(d, key=d.get, reverse=True)
print(d)

# =================
# Method 1.2: By value
x = {1: 2, 3: 4, 4: 3, 2: 1, 0: 0}
sorted_x = sorted(x.items(), key=lambda kv: kv[0])

print(sorted_x)

t = (1, 5, 2, 8, 4, 6, 3)
print(t[2:-2])


# =================
# Method 2: By key
x = {1: 2, 3: 4, 4: 3, 2: 1, 0: 0}
sorted_x = sorted(x) # list of keys in sorted order
print(sorted_x)

# Method 2.2: By key

# function calling
def dictionairy():
 
    # Declaring the hash function
    key_value = {
        2: 56,
        1: 2,
        5: 12,
        4: 24,
        6: 18,
        3: 323
    }
 
    # Initialize value
    # key_value[2] = 56
    # key_value[1] = 2
    # key_value[5] = 12
    # key_value[4] = 24
    # key_value[6] = 18
    # key_value[3] = 323
     
    print("key_value",key_value)
 
    print("key_value ", end="")
     
    # sorted(key_value) returns an iterator over the
    # Dictionary’s value sorted in keys.
    print(sorted(key_value))
    print(type(sorted(key_value))) 
    for i in sorted(key_value):
        print(i, key_value[i])
        print((i, key_value[i]), end=" ")
        print("\n")
 
 
def main():
        # function calling
    dictionairy()
 
 
# main function calling
if __name__ == "__main__":
    main()