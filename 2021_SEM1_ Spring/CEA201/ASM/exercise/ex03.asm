; calc sum and average of 3 numbers a, b and c

include \masm32\include\masm32rt.inc \


.code                       
start:                          
    call main                   
    exit

main proc

    LOCAL va : DWORD
    LOCAL vb : DWORD
    LOCAL vc : DWORD
    LOCAL sum : DWORD
    LOCAL ave : DWORD
    LOCAL rem : DWORD
    
    mov va, sval(input("Enter number 1 : "))
    mov vb, sval(input("Enter number 2 : "))
    mov vc, sval(input("Enter number 3 : "))
   
    mov ebx, va
    add ebx, vb
    add ebx, vc
    mov sum, ebx

    xor edx, edx
    mov eax, sum
    cdq
    mov ecx, 3
    idiv ecx 

    mov ave, eax
    mov rem, edx

    print chr$("sum: ")
    print str$(sum)  
               
    print chr$(13, 10, "average: ")
    print str$(ave)
    print chr$(", r = ")
    print str$(rem)   

    ret
    
main endp

end start                      