; MAX OF a, b, c

include \masm32\include\masm32rt.inc \

.data?
    va dd ?
    vb dd ?
    vc dd ?
    
.code

start:
    call main
    exit

main proc
    LOCAL res : DWORD

    mov va, sval(input("Enter a: "))
    mov vb, sval(input("Enter b: "))
    mov vc, sval(input("Enter c: "))

    mov eax, va
    cmp eax, vb
    jg AGB
    jl ALB

    AGB: 
        mov ecx, vc
        cmp ecx, va

        jg CGA
        jl CLA

        CGA:
            print str$(vc)
            ret
        CLA:
            print str$(va)
            ret
        
    ALB:
        mov ecx, vc
        cmp ecx, vb

        jg CGB
        jl CLB

        CGB:
            print str$(vc)
            ret
        CLB:
            print str$(vb)
            ret
            
    ret 
main endp

end start          