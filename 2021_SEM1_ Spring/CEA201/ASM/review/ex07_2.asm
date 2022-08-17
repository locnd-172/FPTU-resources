; Sort A, B, C

include \masm32\include\masm32rt.inc \

comp PROTO : PTR DWORD, : PTR DWORD

.data?
    va dd ?
    vb dd ?
    vc dd ?
      
.code

start:
    call main
    exit

main proc 
    LOCAL add_va : DWORD
    LOCAL add_vb : DWORD
    LOCAL add_vc : DWORD

    mov va, sval(input("Enter a: "))
    mov vb, sval(input("Enter b: "))
    mov vc, sval(input("Enter c: "))

    lea eax, va
    mov add_va, eax

    lea ebx, vb
    mov add_vb, ebx
    
    lea ecx, vc
    mov add_vc, ecx
    
    ;--------------------------
    
    push eax
    push ebx
    invoke comp, add_va, add_vb         ; compare v1, v2
    pop ebx
    pop eax
    
    ;---------------------------
       
    push eax
    push ebx
    invoke comp, add_va, add_vc         ; compare v1, v3
    pop ebx
    pop eax
    
    ;---------------------------
    
    push eax
    push ebx
    invoke comp, add_vb, add_vc         ; compare v2, v3
    pop ebx
    pop eax

    ;---------------------------

    print str$(va)
    print chr$(" ")
    print str$(vb)
    print chr$(" ")
    print str$(vc)
    
    ret 
    
main endp

comp proc vala : PTR DWORD, valb : PTR DWORD
    LOCAL cva : DWORD
    LOCAL cvb : DWORD

    mov edx, vala
    mov eax, [edx]

    mov edx, valb
    mov ebx, [edx]

    cmp eax, ebx
    je EAB
    jg GAB
    jl LAB

    EAB:
        ret
    GAB:
        mov edx, vala
        mov [edx], ebx      ;add_va = ebx
        mov edx, valb
        mov [edx], eax      ;add_vb = eax

        ret
    LAB: 
        ret

comp endp

end start          