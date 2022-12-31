import re

inp = input("Enter password: ")
passwords = inp.strip().split(",")
ls = list()
for password in passwords:
    lower, upper, digit, spec, leng = 0, 0, 0, 0, 0
    if re.search("[a-z]+", password): lower = 1
    if re.search("[A-Z]+", password): upper = 1
    if re.search("[0-9]+", password): digit = 1
    if re.search("[$#@]+", password): spec = 1
    if len(password) >= 6 and len(password) <= 12: leng = 1
    if lower == 0 or upper == 0 or digit == 0 or spec == 0 or leng == 0:
        continue

    ls.append(password)
    
print(",".join(ls))

# Pidolphin$9,Pidolphi1,Pidolphin$,pidolphin1#,Pidolphin$1,piDophin@0

