import math


def get_bit(n, k):
    return (n & (1 << k)) >> k


# set the k_th bit of n to 1
def set_bit(n, k):
    return n | (1 << k) 


# set the k_th bit of n to 0
def clear_bit(n, k):
    return n & ~(1 << k)

    
def toggle_bit(n, k):
    return n ^ (1 << k)


n = 49
k = 4

print(round(math.log(n)))
print(n.bit_count())
print(n.bit_length())
i = 0
while 2 ** i < n:
    print(get_bit(n, i), end='')
    i += 1

# dec to bin
print()
print('{:b}'.format(n))
print('{:08b}'.format(n))
print(bin(n))
print(bin(n).split('b')[1])
# print(type(bin(n).split('b')[1]))

# dec to oct
print()
print('{:o}'.format(n))
print('{:08o}'.format(n))
print(oct(n))
print(oct(n).split('o')[1])

# dec to hex
print()
print('{:x}'.format(n))
print('{:08x}'.format(n))
print(hex(n))
print(hex(n).split('x')[1])


# hex to dec
print()
print('{:x}'.format(130))
x = 0x82
print(int(x))

# oct to dec
print()
print('{:o}'.format(61))
x = 0o75
print(int(x))