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
        
.code

start:
    call main
    exit

main proc
    LOCAL quo : DWORD
    LOCAL rem : DWORD
    LOCAL add_va : DWORD
    LOCAL add_vb : DWORD
    
    
    mov va, sval(input("Enter a: "))
    mov vb, sval(input("Enter b: "))

    print chr$(13, 10, "N = ")
    
    mov ecx, vb
    sub ecx, va
       
    cmp ecx, 0
    
    jg posi
    jl nega
    
    nega:
        neg ecx
        add ecx, 1
        mov N, ecx
        
        print str$(ecx)
        print chr$(13, 10)
        
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
        
        mov eax, sum
        add eax, it
        mov sum, eax

        inc it
        jmp CONTD  
          
    STOP:
        print chr$("SUM = ")
        print str$(sum)
        
        xor edx, edx
        xor eax, eax
        mov eax, sum
        cdq
        mov ecx, N
        idiv ecx
        mov quo, eax
        mov rem, edx

        print chr$(13, 10, "q = ")
        print str$(quo)
        print chr$(", r= ")
        print str$(rem)
        

    
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