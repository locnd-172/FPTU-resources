; calc sum, diff, product, division of 2 numbers  v1 and v2
; tich < 0 -> -d,xxx (x<0 can sua thanh x>0)
; tich > 0 -> d.xxx

    
include \masm32\include\masm32rt.inc \
   
.data
    va dd 0
    vb dd 0
    cnt dd 0
    vr dd 0
    vp dd 0
    vq dd 0
    xx dd 0

.code    
                   
start:                          
    call main                   
    exit

main proc
       
    mov va, sval(input("Enter number 1 : "))
    mov vb, sval(input("Enter number 2 : "))
  
    print chr$("v1 + v2 = ")

    mov eax, va
    add eax, vb
    print str$(eax)

    print chr$(13, 10, "v1 - v2 = ")
    
    mov eax, va
    sub eax, vb
    print str$(eax)

    print chr$(13, 10, "v1 * v2 = ")
    mov eax, va
    imul eax, vb
    print str$(eax)

    mov ebx, vb
    print chr$(13, 10, "v1 / v2 = ")
    
    cmp vb, 0
    je equal
    jne nequal

    equal:
        print chr$("non-defined operation")
        ret
        
    nequal:

    xor edx, edx
    mov eax, va
    cdq
    mov ecx, vb
    idiv ecx
    mov vq, eax     ; q = eax (thuong)
    mov vr, edx     ; r = edx (so du)

    ;print str$(edx)
    CMP edx, 0
    je rz
    jne nrz
    
    rz:
        print str$(vq)
        ret
    
    nrz:
    print str$(vq)
    print chr$(",")

    mov edx, vq
    mov vp, edx
    xor edx, edx

    mov cnt, 3   
  
    CONTD:
        cmp cnt, 0
        je STOP
        
        ; p = 10r
        mov ecx, vr
        imul ecx, 10
        mov vp, ecx

        ; in ra 10r div b
        xor edx, edx
        mov eax, vp
        cdq
        mov ecx, vb
        idiv ecx
 
        mov xx, eax
        mov vr, edx

        print str$(xx)
  
        dec cnt
        jmp CONTD
        
     STOP:
        ret    

    ret
main endp

end start                      