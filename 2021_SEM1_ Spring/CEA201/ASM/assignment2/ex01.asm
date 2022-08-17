; AVERAGE OF SUM OF NUMBERS BETWEEN a AND b

include \masm32\include\masm32rt.inc \

swap PROTO : PTR DWORD, : PTR DWORD


.data?
    va dd ?
    vb dd ?
    it dd ?
    N dd ?

.data 
    sum dd 0
    even_sum dd 0
    odd_sum dd 0
        
.code

start:
    call main
    exit

main proc
    LOCAL v1 : DWORD
    LOCAL v2 : DWORD
    
    LOCAL add_va : DWORD
    LOCAL add_vb : DWORD

    
    mov va, sval(input("Enter a: "))
    mov vb, sval(input("Enter b: "))

    mov eax, va
    mov v1, eax

    mov ebx, vb
    mov v2, ebx
    
    ;print chr$(13, 10, "N = ")
    
    mov ecx, vb
    sub ecx, va
       
    cmp ecx, 0
    
    jg posi
    jl nega
    
    nega:
        neg ecx
        add ecx, 1
        mov N, ecx
        
        ;print str$(ecx)
        ;print chr$(13, 10)
        
        lea eax, va
        mov add_va, eax

        lea ebx, vb
        mov add_vb, ebx

        push eax
        push ebx
        invoke swap, add_va, add_vb
        pop ebx
        pop eax
        
        jmp over

    posi:
        add ecx, 1
        mov N, ecx
        jmp over


    over:
    mov eax, va
    mov it, eax
    
    CONTD:
        mov eax, it
        sub eax, 1
        cmp eax, vb   
        je STOP
        jg STOP
        
        mov eax, sum
        add eax, it
        mov sum, eax

        inc it
        jmp CONTD  
          
    STOP:
        print chr$("a. Sum a -> b: ")
        print str$(sum)
        print chr$(13, 10)
        jmp CALC2

CALC2:
    print chr$("b. Sum of even number 0 -> v1: ", 13, 10)
    xor eax, eax
    mov eax, 0
    mov it, eax
    
    CONTD2:
        mov eax, it
        sub eax, 1
        cmp eax, v1
        je STOP2
        jg STOP2

        print str$(it)
        print chr$(" + ")
        
        xor eax, eax
        mov eax, even_sum
        add eax, it
        mov even_sum, eax

        inc it
        inc it
        jmp CONTD2
        
    STOP2:
        print chr$(" = ")
        print str$(even_sum)
        print chr$(13, 10)
        jmp CALC3

CALC3:
    print chr$("c. Sum of odd number 0 -> v2: ", 13, 10)
    xor eax, eax
    mov eax, 1
    mov it, eax
    
    CONTD3:
        mov eax, it
        sub eax, 1
        cmp eax, v2
        je STOP3
        jg STOP3
        
        print str$(it)
        print chr$(" + ")
        
        xor eax, eax
        mov eax, odd_sum
        add eax, it
        mov odd_sum, eax

        inc it
        inc it
        jmp CONTD3
        
    STOP3:
        print chr$(" = ")
        print str$(odd_sum)  
        jmp ENDC

ENDC:    
    ret 
main endp

swap proc add_va : PTR DWORD, add_vb : PTR DWORD
    ;swap value
    mov edx, add_va
    mov eax, [edx]      ;eax = va
    mov edx, add_vb
    mov ebx, [edx]      ;ebx = vb
    
    mov edx, add_va
    mov [edx], ebx      ;add_va = ebx
    mov edx, add_vb
    mov [edx], eax      ;add_vb = eax

    ret
swap endp

end start          