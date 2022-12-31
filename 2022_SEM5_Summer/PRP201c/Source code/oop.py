
# 3. Using objects
# stuff = list()
# stuff.append('python')
# stuff.append('chuck')
# stuff.sort()
# print (stuff[0])
# print (stuff.__getitem__(0))
# print (list.__getitem__(stuff,0))
# print(dir(stuff))

# 4. Starting with program
# class PartyAnimal:
#     x = 0
#     def party(self) :
#         self.x = self.x + 1
#         print("So far",self.x)
        
# an = PartyAnimal()
# an.party()
# an.party()
# an.party()
# PartyAnimal.party(an)
# print()
# print ("Type", type(an))
# print ("Dir ", dir(an))
# print ("Type", type(an.x))
# print ("Type", type(an.party))

# Object lifecycle
# class PartyAnimal:
#     x = 0
#     def __init__(self):
#         print('I am constructed')
#     def party(self) :
#         self.x = self.x + 1
#         print('So far',self.x)
#     def __del__(self):
#         print('I am destructed', self.x)

# an = PartyAnimal()
# an.party()
# an.party()
# an = 42
# print('an contains',an)

# Multiple instances
class PartyAnimal:
    x = 0
    name = ''
    def __init__(self, nam):
        self.name = nam
        print(self.name,'constructed')

    def party(self) :
        self.x = self.x + 1
        print(self.name,'party count',self.x)

# s = PartyAnimal('Sally')
# j = PartyAnimal('Jim')

# s.party()
# j.party()
# s.party()

# Inheritance 
