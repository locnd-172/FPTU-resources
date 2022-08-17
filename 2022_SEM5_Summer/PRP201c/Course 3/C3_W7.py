import urllib.request as ur
import json

json_url = input("Enter location: ")
print("Retrieving ", json_url)
data = ur.urlopen(json_url).read().decode('utf-8')
print('Retrieved', len(data), 'characters')
json_obj = json.loads(data)

sum = 0
cnt = 0

for comment in json_obj["comments"]:
    sum += int(comment["count"])
    cnt += 1

print('Count:', cnt)
print('Sum:', sum)