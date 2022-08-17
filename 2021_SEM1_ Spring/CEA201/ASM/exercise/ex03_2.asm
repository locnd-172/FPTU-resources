

  include \masm32\include\masm32rt.inc \


.code                       
start:                          
    call main                   
    exit

main proc

    LOCAL va : DWORD
    LOCAL vb : DWORD
    LOCAL vc : DWORD
       
    mov va, sval(input("Enter number 1 : "))
    mov vb, sval(input("Enter number 2 : "))
    mov vc, sval(input("Enter number 3 : "))

    print chr$("sum: ")
    mov ebx, va
    add ebx, vb
    add ebx, vc

    print str$(ebx)             
 
    print chr$(13, 10, "average: ")

    xor edx, edx
    mov eax, ebx
    cdq
    mov ecx, 3
    idiv ecx 
    print str$(eax)

    print chr$(13, 10, "r = ")
    mov ebx, va
    add ebx, vb
    add ebx, vc

    xor edx, edx
    mov eax, ebx
    cdq
    mov ecx, 3
    idiv ecx 
    print str$(edx)   

    ret
main endp

end start                      