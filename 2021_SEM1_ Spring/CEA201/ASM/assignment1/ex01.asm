; MAX OF A and B

include \masm32\include\masm32rt.inc \

.data?
    va dd ?
    vb dd ?
        
.code

start:
    call main
    exit

main proc
    mov va, sval(input("Enter a: "))
    mov vb, sval(input("Enter b: "))

    print chr$("Max of ")
    print str$(va)
    print chr$(" and ")
    print str$(vb)
    print chr$(" is: ")
    
    mov eax, va
    cmp eax, vb
    je EQUAL
    jg LG
    jl LO

    LG:
        print str$(va)
        ret
    LO:
        print str$(vb)
        ret
    EQUAL:
        print str$(va)
        ret

    ret 
main endp



end start          