import xml.etree.ElementTree as ET
import urllib.request

url = "http://py4e-data.dr-chuck.net/comments_1347511.xml"
html = urllib.request.urlopen(url)
data = html.read()
tags = ET.fromstring(data)
last = tags.findall('comments/comment')
x = 0
for item in last:
    element = int((item.find('count').text))
    x = element + x
print(x)