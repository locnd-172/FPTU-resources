; CALC a^n

include \masm32\include\masm32rt.inc \

.data?
    va dd ?
    N dd ?
    
.data 
    prod dd 1
        
.code

start:
    call main
    exit

main proc
    LOCAL it : DWORD
    
    mov va, sval(input("Enter a: "))
    mov N, sval(input("Enter N: "))

    mov it, 1
    CONTD:
        mov eax, it
        sub eax, 1
        cmp eax, N
        je STOP

        mov eax, prod
        imul eax, va
        mov prod, eax

        inc it
        jmp CONTD
        
    STOP:
        print str$(va)
        print chr$("^")
        print str$(N)
        print chr$(" = ")
        print str$(prod)
        ret
        
    ret 
main endp

end start          