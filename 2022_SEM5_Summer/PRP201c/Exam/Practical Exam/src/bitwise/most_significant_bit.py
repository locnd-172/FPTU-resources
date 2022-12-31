
# Python program to find
# MSB number for given n.
import math
 
def setBitNumber(n):
     
    # To find the position of the most significant
    # set bit
    k = int(math.log(n, 2))
     
    # To return the value of the number with set bit at k-th position
    return 1 << k
 
# Driver code
n = 273       
print(setBitNumber(n))