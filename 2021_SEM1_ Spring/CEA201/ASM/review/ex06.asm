; PRINT PRIME NUMBER IN SEGMENT [1;N]
; neu nhap 1 -> bao "no prime number"

include \masm32\include\masm32rt.inc \

check PROTO : DWORD

.data?
    N dd ?
    
.data 
    prod dd 1
        
.code

start:
    call main
    exit

main proc
    LOCAL it : DWORD
    LOCAL okp : DWORD
    
    mov N, sval(input("Enter N: "))

    mov it, 2
    
    CONTD:
        mov eax, it
        sub eax, 1
        cmp eax, N
        je STOP
        
        push eax
        invoke check, it
        mov okp, eax
        pop eax
        
        mov ebx, okp
        cmp ebx, 1
        je ISPRIME
        jne NOTPRIME

        ISPRIME:
            print chr$(" ")
            print str$(it)
            jmp over

        NOTPRIME:
            jmp over

        over:
        inc it
        jmp CONTD
        
    STOP:
        ret
        
    ret 
main endp

check proc val1 : DWORD
    LOCAL divi : DWORD
    LOCAL cnt : DWORD

    mov divi, 2
    mov cnt, 0
    
    CONTD:
        mov eax, divi
        sub eax, 1
        cmp eax, val1
        je STOP
      
        xor eax, eax
        xor edx, edx
        
        mov eax, val1
        cdq
        mov ecx, divi
        idiv ecx
        
        cmp edx, 0
        je ADDC
        jne OKN

        ADDC:
            inc cnt
            inc divi
            jmp CONTD
        OKN:
            inc divi
            jmp CONTD

    STOP:        
        mov ebx, cnt
        cmp ebx, 1
        je PRIME
        jne NOTPRIME

        PRIME: 
            mov eax, 1
            ret
        NOTPRIME:
            mov eax, 0
            ret
check endp

end start          