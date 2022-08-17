    ;Write a MASM program that will solve the equation ax+b=0 => x = -b / a

include \masm32\include\masm32rt.inc \

swap1 PROTO :DWORD, :DWORD

.code    
                   
start:                       
    call main                   
    exit

main proc
    LOCAL va : DWORD
    LOCAL add_va : DWORD
    LOCAL vb : DWORD
    LOCAL add_vb : DWORD

    mov va, sval(input("Enter a: "))
    mov vb, sval(input("Enter b: "))

    lea eax, va
    mov add_va, eax

    lea ebx, vb
    mov add_vb, ebx

    print chr$("va, address: ")
    print str$(add_va)
    print chr$(", value: ")
    print str$(va)

    print chr$(13,10)
    print chr$("vb, address: ")
    print str$(add_vb)
    print chr$(", value: ")
    print str$(vb)

    print chr$(13, 10, 13, 10)

    push eax
    push ebx
    invoke swap1, va, vb
    pop ebx 
    pop eax

    ;print result
    print chr$("after swapping: ")
    print str$(va)
    print chr$(", :")
    print str$(vb)
   
    ret

main endp

swap1 proc va : DWORD, vb : DWORD

    print chr$("argument 1, address: ")
    lea eax, va
    print str$(eax)
    print chr$(", value: ")
    print str$(va)

    print chr$(13, 10)

    print chr$("argument 2, address: ")
    lea eax, vb
    print str$(eax)
    print chr$(", value: ")
    print str$(vb)

    print chr$(13, 10)


    ;swap value
    mov eax, va
    mov ebx, vb
    mov va, ebx
    mov vb, eax
    
    ret
swap1 endp

end start                      