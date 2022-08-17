; print Nth fibonacci number

include \masm32\include\masm32rt.inc \

.data
    index dd 3
    
.code    
                   
start:                       
    call main                   
    exit

main proc
    LOCAL cnt : DWORD
    LOCAL va : DWORD
    LOCAL vb : DWORD
    LOCAL N : DWORD
    
    mov cnt, sval(input("Enter index of fibonacci number (n) = "))
    mov eax, cnt
    mov N, eax
    
; F[1] = 1
    CMP cnt, 1
    je STOP1
    jne CONT1
    STOP1:
        print chr$("F[1] = 1")   
        jmp over
    CONT1:

; F[2] = 1
    cmp cnt, 2
    je STOP2
    jne CONT2
    STOP2:
        print chr$("F[2] = 1")   
        jmp over
       
    CONT2:
; N = N - 2
    mov ebx, cnt
    sub ebx, 2
    mov cnt, ebx

; init: f1 = 1, f2 = 2

    xor eax, eax
    xor ebx, ebx
    mov va, 1
    mov vb, 1

    print chr$("F[1] = 1", 13, 10, "F[2] = 1")
    
    CONTD:
        cmp cnt, 0
        je STOP
      
        mov eax, va   
        add eax, vb     ; f[i] = f[i-1] + f[i-2]

        mov ecx, vb
        mov va, ecx     ; f[i-2] = f[i-1]
        mov vb, eax     ; f[i-1] = f[i]

        print chr$(13, 10, "F[")
        print str$(index)
        print chr$("] = ")
        print str$(vb)

        inc index
        dec cnt
        jmp CONTD
        
     STOP:
        ;print chr$("F[" )
        ;print str$(N)
        ;print chr$("] = ")
        ;print str$(vb)
        ret    

    over: 
        ret

main endp

end start                      