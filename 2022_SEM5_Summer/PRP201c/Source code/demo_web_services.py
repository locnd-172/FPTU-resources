# Parsing XML
import xml.etree.ElementTree as ET

data = '''
<person>
    <name>Chuck</name>
    <phone type="intl">
    +1 734 303 4456
    </phone>
    <email hide="yes" />
</person>'''

tree = ET.fromstring(data)
print('Name:', tree.find('name').text)
print('Attr:', tree.find('email').get('hide'))
print('Phone:', tree.find('phone').text.strip())
print('Phone type:', tree.find('phone').get('type'))

# Looping through nodes
# import xml.etree.ElementTree as ET
inp = '''
<stuff>
    <users>
        <user x="2">
            <id>001</id>
            <name>Chuck</name>
        </user>
        <user x="7">
            <id>009</id>
            <name>Brent</name>
        </user>
    </users>
</stuff>'''
stuff = ET.fromstring(inp)
lst = stuff.findall('users/user')
print('User count:', len(lst))
print()
for item in lst:
    print('Name', item.find('name').text)
    print('Id', item.find('id').text)
    print('Attribute', item.get('x'))
    print()
    
# It is important to include all parent level elements in the findall statement except
# for the top level element (e.g., users / user). Otherwise, Python will not find any
# desired nodes.
lst2 = stuff.findall('user')
print('User count:', len(lst2))  # 0



# JavaScript Object Notation - JSON
# Parsing JSON
import json

data = '''
[
    { 
        "id" : "001",
        "x" : "2",
        "name" : "Chuck"
    },
    {
        "id" : "009",
        "x" : "7",
        "name" : "Brent"
    }
]'''

info = json.loads(data)
print('User count:', len(info))

for item in info:
    print('Name', item['name'])
    print('Id', item['id'])
    print('Attribute', item['x'])
    print()
