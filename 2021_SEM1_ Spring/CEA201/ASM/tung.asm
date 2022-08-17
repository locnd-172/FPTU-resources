include \masm32\include\masm32rt.inc

.code
start: 
    call main
    exit

main proc
    LOCAL var1:DWORD
    LOCAL var2:DWORD

    
    mov var1, sval(input("Enter a: "))
    mov var2, sval(input("Enter b: "))
    
    neg var2
    print chr$("x = ")

    
    mov eax, var2
    xor edx, edx
    cdq
    mov ebx, var1
    idiv ebx
    print str$(eax)


ret
main endp

end start