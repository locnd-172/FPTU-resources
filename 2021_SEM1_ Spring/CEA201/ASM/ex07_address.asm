    ;Write a MASM program that will solve the equation ax+b=0 => x = -b / a

include \masm32\include\masm32rt.inc \

.data
    anInt DD 123
    txt1 db "I love you", 0

.data?
    aReal DD ?
    txt2 db 128 dup(?)
    
.code    
                   
start:                       
    call main                   
    exit

main proc
    LOCAL va : DWORD
    
 ; Access address and value of anInt
    print chr$("Address of anInt: ")
    mov eax, OFFSET anInt 
    print str$(eax)    
    print chr$(", value: ")
    print str$(anInt)
    print chr$(13,10)
    
 ; Access address and value of txt1
    print chr$("Address of txt1: ")
    mov eax, OFFSET txt1 
    print str$(eax)    
    print chr$(", value: ")
    print OFFSET txt1
    print chr$(13,10)

 ; Access address and value of aReal
    print chr$("Address of aReal: ")
    mov eax, OFFSET aReal
    print str$(eax)
    print chr$(", value: ")
    mov aReal, 1011
    print str$(aReal)
    print chr$(13,10)

 ; Access address and value of aReal
    print chr$("Address of txt2: ")
    mov eax, OFFSET txt2
    print str$(eax)
    print chr$(", value: ")
    print OFFSET txt2
    print chr$(13,10)

 ; Access address and value of aReal
    mov va, 1111
    print chr$("Address of va: ")
    lea eax, va
    print str$(eax)
    print chr$(", value: ")
    print str$(va)
    print chr$(13,10)


    ret

main endp

end start                      