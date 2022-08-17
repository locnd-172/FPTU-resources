import math 

# Built-in func
print(type(32))
print(max('Hello world'))
print(min('Hello world'))
print(len('Hello world'))

# Math func
signal_power = 8
noise_power = 10
ratio = signal_power / noise_power
decibels = 10 * math.log10(ratio)
print(decibels)

radians = 0.7
height = math.sin(radians)
print(height)

# convert deg to rad
degrees = 45
radians = degrees / 360.0 * 2 * math.pi
print(math.sin(radians))

print(math.sqrt(5))