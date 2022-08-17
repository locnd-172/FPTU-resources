; calc sum, diff, product, division of 2 numbers  v1 and v2
    
include \masm32\include\masm32rt.inc \

.data?
    res dq ? 
    va dd ?
    vb dd ?
    
.data
    prod dd 0
    
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
    
    mov prod, eax
    print str$(prod)


    print chr$(13, 10, "v1 / v2 = ")
    
    cmp vb, 0
    je equal
    jne nequal

    equal:
        print chr$("non-defined expression")
        ret
        
    nequal:

    cmp prod, 0
    je p_z
    jl lower_z
    jg greater_z
    
p_z:
    print chr$("0")
    ret
    
lower_z:
    print chr$("-")
    
    cmp va, 0
    jl alz
    jg agz

    alz:
        mov eax, -1
        imul eax, va
        mov va, eax
        ;print chr$("va = ")
        ;print str$(va)
        ;print chr$(13, 10)
        jmp over
    agz:
        jmp cmp_b

    cmp_b:
    cmp vb, 0
    jl blz
    jg bgz

    blz:
        mov eax, -1
        imul eax, vb
        mov vb, eax
        ;print chr$("vb = ")
        ;print str$(vb)
        ;print chr$(13, 10)
        jmp over
    bgz:
        jmp over
        
greater_z:
    jmp over

over:

    comment * <<<<<
    print chr$(13, 10, "va = ")
    print str$(va)
    print chr$(13, 10, "vb = ")
    print str$(vb)
    print chr$(13, 10)
    <<<< *
    
    finit
    fld DWORD PTR [va]
    fld DWORD PTR [vb]
    fdiv
    fstp QWORD PTR [res]
    printf("%.5f", res)
    
    ret
main endp

end start                      