# seed the pseudorandom number generator
import random

# # seed random number generator
# seed(1)
# # generate some random numbers
# print(random(), random(), random())

# # reset the seed
# seed(1)
# # generate some random numbers
# print(random(), random(), random())

for i in range(10):
    x = random.random()
    print(x)
    
print(random.randint(5, 10))

t = [10, 11, 12, 13]
v = [11, 12, 23, 34, 45, 56, 67, 78, 89, 110]
print(random.choice(t), random.choice(v))
