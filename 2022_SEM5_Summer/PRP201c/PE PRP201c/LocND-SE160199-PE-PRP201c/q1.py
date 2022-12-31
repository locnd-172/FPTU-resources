while True:
    int_part = input("Enter the integral part: ")
    try:
        a = int(int_part)
        frac_part = input("Enter the fraction: ")
        try:
            b = int(frac_part)
            if b < 0:
                print("Fraction must be positive!\n")
                continue
        except:
            print("Fraction must be a positive integer!\n")
            continue
        
        r = str(a) + "." + str(b)
        real_num = float(r)
        print("Real number: ", real_num)
        break;
    except:
        print("Input must be an integer!\n")