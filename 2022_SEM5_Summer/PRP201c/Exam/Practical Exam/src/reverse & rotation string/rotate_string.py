# An efficient Python3 program to print
# all rotations of a string.


# Print all the rotated string.
def printRotatedString(string):
	
	n = len(string)

	# Concatenate str with itself
	temp = string + string

	# Print all substrings of size n.
	# Note that size of temp is 2n
	for i in range(n):
		
		for j in range(n):
			print(temp[i + j], end="")
			
		print()


# Driver Code
if __name__ == "__main__":

	string = "geeks"
	printRotatedString(string)

# This code is contributed by Ryuga
