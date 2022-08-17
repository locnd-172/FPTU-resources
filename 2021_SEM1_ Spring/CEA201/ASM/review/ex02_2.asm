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
    LOCAL N1 : DWORD
    
    mov N, sval(input("Number to be checked: "))

    mov eax, N
    sub eax, 1
    mov N1, eax
    
    CONTD:
        mov eax, divisor
        sub eax, 1
        cmp eax, N1         ; div == N-1
        je STOP
      
        xor eax, eax
        xor edx, edx
        
        mov eax, N
        cdq
        mov ecx, divisor
        idiv ecx
        
        cmp edx, 0
        je NOTPR
        jne GO
        
        NOTPR:
            print chr$("NO")
            ret
        GO:
            inc divisor
            jmp CONTD
            
    STOP:        
        print chr$("YES")
        ret   
         
    ret 
main endp

end start          