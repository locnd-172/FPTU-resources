; calc factorial N!

include \masm32\include\masm32rt.inc \

factorial PROTO : DWORD

.code    
                   
start:                       
    call main                   
    exit

main proc
    LOCAL N : DWORD
    LOCAL res : DWORD
    
    mov N, sval(input("Enter N: "))

    print chr$("N! = ")

    push eax
    invoke factorial, N
    mov res, eax
    pop eax
    
    print str$(res)
    
    ret

main endp

factorial proc n1 : DWORD
    LOCAL it : DWORD
    LOCAL last : DWORD

    mov it, 1
    mov last, 1

    CONTD:
        mov ecx, it
        cmp ecx, n1
        je STOP
        
        xor eax, eax
        mov eax, it
        add eax, 1       ;eax = i = i + 1

        imul eax, last   ;last = last * eax = last * (i+1)
        mov last, eax
       
        inc it
        jmp CONTD
        
     STOP:
        mov eax, last
        ret    
        
    ret
factorial endp

end start                      