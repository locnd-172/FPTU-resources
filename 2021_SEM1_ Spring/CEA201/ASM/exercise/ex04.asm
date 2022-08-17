; solve the equation ax+b=0 => x = -b / a

include \masm32\include\masm32rt.inc \

.data?
    res dq ?
    prod dd ?
    va dd ?
    vb dd ?
    
.code    
                   
start:                          
    call main                   
    exit

main proc

    print chr$("Solve ax + b = 0", 13, 10)
    
    mov va, sval(input("Enter a : "))
    mov vb, sval(input("Enter b : "))

    cmp va, 0
    je a_equal_z
    jne a_n_equal_z
    
    a_equal_z:
        cmp vb, 0
        je equal_b
        jne nequal_b
        
        equal_b:
            print chr$("Infinite solution")
            ret
           
        nequal_b:
            print chr$("No solution")
            ret
  

    a_n_equal_z:
        print chr$("x = ")
        mov ebx, vb
        imul ebx, -1
        mov vb, ebx

        mov eax, va
        imul eax, vb
        mov prod, eax
    
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
        jmp over
        
    bgz:
        jmp over
        
greater_z:
    jmp over

over:
  
    finit
    fld DWORD PTR [va]
    fld DWORD PTR [vb]
    fdiv
    fstp QWORD PTR [res]
    printf("%.5f", res)
    
    ret
    
main endp

end start                      