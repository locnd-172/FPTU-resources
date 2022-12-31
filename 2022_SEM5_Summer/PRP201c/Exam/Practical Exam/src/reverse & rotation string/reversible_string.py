# This function basically checks if string is palindrome or not
def isReversible(st):
	i = 0; j = len(st) - 1;

	# iterate from left and right
	while (i < j):
		if (st[i] != st[j]):
			return False;
		i += 1;
		j -= 1;
	return True;


# Driver Code
st = "aba";
if (isReversible(st)):
	print("YES");
else:
	print("NO");