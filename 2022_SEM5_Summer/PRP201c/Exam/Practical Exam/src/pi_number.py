import math

n = 10
res = "{c:."+ str(n) + "f}"
fv = res.format(c=math.pi)
print(float(fv))
