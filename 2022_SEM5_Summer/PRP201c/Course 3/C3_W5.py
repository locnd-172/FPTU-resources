from urllib import request
from bs4 import BeautifulSoup

html = request.urlopen('http://py4e-data.dr-chuck.net/comments_1347509.html').read()
bs = BeautifulSoup(html)
tags = bs('span')
sum = 0
for tag in tags:
    sum = sum + int(tag.contents[0])

print(sum)