; 

include \masm32\include\masm32rt.inc \

.data?
    b1 dd ?
    b2 dd ?
    vr dd ?
    sum dd ?
    prod dd ?
    con dd ?
    res dq ?
        
.code

start:
    call main
    exit

main proc
    mov b1, sval(input("Canh day 1: "))
    mov b2, sval(input("Canh day 2: "))
    mov vr, sval(input("Duong cao: "))

    print chr$(13, 10, "1. Tong chieu dai 2 day: ")
    mov eax, b1
    add eax, b2
    mov sum, eax
    
    print str$(sum)

    print chr$(13, 10, 13, 10, "2. Dien tich hinh thang: ")

    mov eax, sum
    imul eax, vr
    mov prod, eax
    mov con, 2
    
    finit
    fld DWORD PTR [prod]
    fld DWORD PTR [con]
    fdiv
    fstp QWORD PTR [res]
    printf("%.3f", res)   

    print chr$(13, 10)
    ret 
main endp

end start          