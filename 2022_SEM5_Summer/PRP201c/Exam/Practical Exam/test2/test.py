# print(r'foo\\bar\nbaz')
# print('foo\\bar\nbaz')
# print(ascii('d'))

# print(divmod(14, 5))
import re
s = 'From stephen.marquard@uct.ac.za Sat Jan 5 09:14:16 2008'
print(type(re.search('\S+?@\S+', s)))
x, y = re.search('\S+?@\S+', s).start(), re.search('\S+?@\S+', s).end()
print(s[x:y + 1])
