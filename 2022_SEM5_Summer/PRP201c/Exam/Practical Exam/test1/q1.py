
def main():
    while True:
        try:
            n = int(input("Enter a positive integer number: "))
            if n > 0: 
                res = "{n} is converted into binary: {bin_n:b}"
                print(res.format(n = n, bin_n = n))
                break
                
            print("The number must be positive.")
            
        except:
            print("Input must be an integer!")
            
main()