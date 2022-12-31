import re

fhand = open("test1/trace.txt", "r")

print("Troubleshoort wired LAN related issues: ")

names = list()

for line in fhand:
    line = line.strip()
    if line.startswith("Name:"): 
        name = line[5:].strip().split("-")
        for na in name:
            names.append(na)
    
print(names)

def countFreq(mylist):
    freqs = dict()
    for name in names:
        name = str(name)
        freqs[name] = freqs.get(name, 0) + 1
    
    for name in sorted(freqs):
        print("{name}: {freq}".format(name=name, freq=freqs[name]))
        
countFreq(names)