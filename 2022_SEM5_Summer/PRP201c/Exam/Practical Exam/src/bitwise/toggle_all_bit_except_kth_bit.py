
# Python3 program to toggle all bits
# except kth bit

 
# Returns a number with all bit toggled
# in n except k-th bit
def toggleAllExceptK(n, k):
     
    # 1) Toggle k-th bit by doing n ^ (1 << k)
    # 2) Toggle all bits of the modified number
    temp = bin(n ^ (1 << k))[2:]
 
    ans = ""
 
    for i in temp:
        if i == '1':
            ans += '0'
        else:
            ans += '1'
 
    return int(ans, 2)

 
# Driver code
if __name__ == '__main__':
     
    n = 4294967295
    k = 0
     
    print(toggleAllExceptK(n, k))
