# Parsing HTML using regular expressions

# Search for link values within URL input
import urllib.request
# import urllib.parse, urllib.error
import re
import ssl

# Ignore SSL certificate errors
ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

url = input('Enter - ')
html = urllib.request.urlopen(url, context=ctx).read()
links = re.findall(b'href="(http[s]?://.*?)"', html)

for link in links:
    print(link.decode())

# input
# https://docs.python.org

#######################################################
# Parsing HTML using BeautifulSoup
# import urllib.request, urllib.parse, urllib.error
from bs4 import BeautifulSoup
# import ssl

# Ignore SSL certificate errors
ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

url = input('Enter - ')
html = urllib.request.urlopen(url, context=ctx).read()
soup = BeautifulSoup(html, 'html.parser')

# Retrieve all of the anchor tags
tags = soup('a')
for tag in tags:
    print(tag.get('href', None))
    
#######################################################    
# BeautifulSoup to pull out various parts of each tag:
from urllib.request import urlopen
# from bs4 import BeautifulSoup
# import ssl

# Ignore SSL certificate errors
ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

url = input('Enter - ')
html = urlopen(url, context=ctx).read()
soup = BeautifulSoup(html, "html.parser")

# Retrieve all of the anchor tags
tags = soup('a')
for tag in tags:
    # Look at the parts of a tag
    print('TAG:', tag)
    print('URL:', tag.get('href', None))
    if (len(tag.contents) > 0): print('Contents:', tag.contents[0])
    print('Attrs:', tag.attrs)
    print()