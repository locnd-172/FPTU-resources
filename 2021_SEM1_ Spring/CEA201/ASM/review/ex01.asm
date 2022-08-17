; SQUARE ROOT OF AN INTEGER

include \masm32\include\masm32rt.inc \

.data?
    N dd ?

.data
    it dd 1
    tmp dd 1

.code

start:  
    call main
    exit

main proc
    mov N, sval(input("Enter N: "))

    
    CONTD:
        mov eax, it
        cmp eax, N
        je STOP

        xor eax, eax
        mov eax, it
        imul eax, eax
        
        mov tmp, eax
        print chr$(13, 10, "prod = ")
        print str$(tmp)

        mov eax, tmp 
        cmp eax, N
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
        print str$(it)
        ret

main endp 

end start

    