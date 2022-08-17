

  include \masm32\include\masm32rt.inc \
   
.data
    va dd 0
    vb dd 0
    vc dd 0
    ;========

.code                       
start:                          
    call main                   
    exit

main proc
       
    mov va, sval(input("Enter number 1 : "))
    mov vb, sval(input("Enter number 2 : "))
    mov vc, sval(input("Enter number 3 : "))

    mov eax, va
    mov ebx, vb  
    add ebx, eax
            
    mov eax, vc
    add ebx, eax

    print str$(ebx)             
    print chr$(13,10)    

    mov edx, 0
    mov eax, ebx
    mov ecx, 3
    div ecx 
    print str$(eax)

    ret
main endp

end start                      