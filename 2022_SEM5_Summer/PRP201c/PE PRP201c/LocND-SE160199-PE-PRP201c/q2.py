file_name = input("Enter file: ")

if not file_name: 
    file_name = "Text.txt"

# print(file_name)

file_handler = open(file_name)

word_freq = dict()
for line in file_handler:
    words = line.strip().split()
    for word in words:
        word = word.strip()
        word_freq[word] = word_freq.get(word, 0) + 1
      
for word, freq in word_freq.items():
    print("{}: {}".format(word, freq))
