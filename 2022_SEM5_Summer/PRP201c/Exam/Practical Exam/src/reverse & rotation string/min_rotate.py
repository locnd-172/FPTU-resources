def findRotations(str):
     
    # tmp is the concatenated string.
    tmp = str + str
    n = len(str)
 
    for i in range(1, n + 1):
        # substring from i index of original string size.
        substring = tmp[i: i + n]
        # if substring matches with original string then we will come out of the loop.
        if (str == substring):
            return i
    return n

 
def minRotation():
    # input
    string = 'geeks'
    check = ''
    
    for r in range(1, len(string) + 1):
    # checking the input after each rotation
        check = string[r:] + string[:r]
        
        # following if statement checks if input is equals to check , if yes it will print r and break out of the loop
        if check == string:
            print(r)
            break
        
        
# Driver code
if __name__ == '__main__':
 
    str = "abc"
    print(findRotations(str))
    minRotation()
