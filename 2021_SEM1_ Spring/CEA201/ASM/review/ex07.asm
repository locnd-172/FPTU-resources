; Sort A, B, C

include \masm32\include\masm32rt.inc \

comp PROTO : PTR DWORD, : PTR DWORD
swap PROTO : PTR DWORD, : PTR DWORD

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

    mov eax, va
    cmp eax, vb
    je AEB
    jg AGB
    jl ALB

    AEB:
        jmp over

    AGB:
        lea eax, va
        mov add_va, eax

        lea ebx, vb
        mov add_vb, ebx 
        
        push eax
        push ebx
        invoke swap, add_va, add_vb
        pop ebx
        pop eax
        jmp over

    ALB: 
        jmp over

    over:
        mov eax, va
        cmp eax, vc
        je AEC
        jg AGC
        jl ALC

        AEC:
            jmp over2

        AGC: 
            lea eax, va
            mov add_va, eax

            lea ecx, vc
            mov add_vc, ecx
        
            push eax
            push ebx
            invoke swap, add_va, add_vc
            pop ebx
            pop eax
            jmp over2

        ALC:
            jmp over2

        over2:
            mov ebx, vb
            cmp ebx, vc
            je BEC
            jg BGC
            jl BLC

            BEC:
                jmp over3

            BGC:
                lea ebx, vb
                mov add_vb, ebx

                lea ecx, vc
                mov add_vc, ecx

                push eax
                push ebx
                invoke swap, add_vb, add_vc
                pop ebx
                pop eax
                jmp over3

            BLC:
                jmp over3

            over3:
                print str$(va)
                print chr$(" ")
                print str$(vb)
                print chr$(" ")
                print str$(vc)
 
                
    ret 
main endp

swap proc adda : PTR DWORD, addb : PTR DWORD

    ;swap value
    mov edx, adda
    mov eax, [edx]      ;eax = va
    mov edx, addb
    mov ebx, [edx]      ;ebx = vb
    
    mov edx, adda
    mov [edx], ebx      ;add_va = ebx
    mov edx, addb
    mov [edx], eax      ;add_vb = eax

    ret
swap endp


end start          