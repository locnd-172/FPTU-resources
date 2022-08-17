; CHECK PRIME NUMBER
; thieu N = 2 -> YES

include \masm32\include\masm32rt.inc \

FRoot PROTO : DWORD

.data?
    rem dd ?
    

.data
    divisor dd 2
    cnt dd 1

.code

start:
    call main
    exit

main proc
    LOCAL N : DWORD
    LOCAL sqrt : DWORD
    
    mov N, sval(input("Number to be checked: "))

    push eax
    invoke FRoot, N
    mov sqrt, eax
    pop eax
    
    CONTD:
        mov eax, divisor
        sub eax, 1
        cmp eax, sqrt
        je STOP
      
        xor eax, eax
        xor edx, edx
        
        mov eax, N
        cdq
        mov ecx, divisor
        idiv ecx
        
        cmp edx, 0
        je ADDC
        jne OKN

        ADDC:
            inc cnt
            inc divisor
            jmp CONTD
        OKN:
            inc divisor
            jmp CONTD

    STOP:        
        mov ebx, cnt
        cmp ebx, 1
        je PRIME
        jne NOTPRIME

        PRIME: 
            print chr$("YES")
            ret
        NOTPRIME:
            print chr$("NO")
            ret    
    ret 
main endp

FRoot proc n1 : DWORD

    LOCAL it : DWORD

    mov it, 1
    
    CONTD:
        mov eax, it
        cmp eax, n1
        je STOP

        xor eax, eax
        mov eax, it
        imul eax, eax
   
        cmp eax, n1
        je STOP
        jg DSTOP
        jl GO

        GO:
        
        inc it
        jmp CONTD

        DSTOP:
        dec it
        jmp STOP

    STOP:
        mov eax, it
        ret
        
FRoot endp

end start          