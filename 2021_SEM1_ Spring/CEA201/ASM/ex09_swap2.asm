    ;Write a MASM program that will solve the equation ax+b=0 => x = -b / a

include \masm32\include\masm32rt.inc \

swap2 PROTO : PTR DWORD, : PTR DWORD

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
    invoke swap2, add_va, add_vb
    pop ebx 
    pop eax

    ;print result
    print chr$("after swapping: ")
    print str$(va)
    print chr$(", ")
    print str$(vb)
   
    ret

main endp

swap2 proc add_va : PTR DWORD, add_vb : PTR DWORD

    print chr$("argument 1, address: ")
    lea eax, add_va
    print str$(eax)
    print chr$(", value: ")
    print str$(add_va)

    print chr$(13, 10)

    print chr$("argument 2, address: ")
    lea eax, add_vb
    print str$(eax)
    print chr$(", value: ")
    print str$(add_vb)

    print chr$(13, 10)

    ;swap value
    mov edx, add_va
    mov eax, [edx]      ;eax = va
    mov edx, add_vb
    mov ebx, [edx]      ;ebx = vb
    
    mov edx, add_va
    mov [edx], ebx      ;add_va = ebx
    mov edx, add_vb
    mov [edx], eax      ;add_vb = eax

    ret
swap2 endp

end start                      