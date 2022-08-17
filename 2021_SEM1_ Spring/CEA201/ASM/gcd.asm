; FIND GCD, LCM

include \masm32\include\masm32rt.inc \

findGCD PROTO : PTR DWORD, : PTR DWORD
findLCM PROTO : PTR DWORD, : PTR DWORD, : PTR DWORD

.data?
    va dd ?
    vb dd ?
      
.code

start:
    call main
    exit

main proc 
    LOCAL gcd_ab : DWORD
    LOCAL lcm_ab : DWORD
    
    mov va, sval(input("Enter a: "))
    mov vb, sval(input("Enter b: "))
  
    ;--------------------------
    
    push eax
    push ebx
    invoke findGCD, va, vb        
    mov gcd_ab, ebx
    pop ebx
    pop eax
    

    mov edx, gcd_ab
    mov ecx , 0
    cmp edx, ecx
    je NS
    jne EX

    NS:
        print chr$("GCD does not exist!")
        ret
    EX:
        print chr$(13, 10, 13, 10, "GCD: ")
        print str$(gcd_ab)
        jmp over

    over:

    xor eax, eax
    xor ebx, ebx
    
    push eax
    push ebx
    invoke findLCM, va, vb, gcd_ab
    mov lcm_ab, ebx
    pop ebx
    pop eax

    print chr$(13, 10, "LCM: ")
    print str$(lcm_ab)

    ret 
    
main endp

findGCD proc vala : PTR DWORD, valb : PTR DWORD

    mov eax, vala
    mov edx, 0
    cmp eax, edx
    je AEZ
    jne ANZ

    AEZ:
        mov ebx, valb
        mov edx, 0
        cmp ebx, edx
        je BEZ
        jne BNZ
        BEZ:
            mov ebx, 0
            ret
            
        BNZ:
            mov ebx, valb
            ret

    ANZ:
        mov ebx, valb
        mov edx, 0
        cmp ebx, edx
        je BEZ2
        jne BNZ2

        BEZ2:
            mov ebx, vala
            ret
        BNZ2:
            CONTD:
                print chr$(13, 10, "a = ")
                print str$(vala)
                print chr$(", b = ")
                print str$(valb)
                
                mov eax, vala
                mov edx, valb
                cmp eax, edx
                je AEB
                jg AGB
                jl ALB
                AEB:
                    ;print chr$("a = b", 13, 10)
                    mov ebx, vala
                    ret

                ALB:
                    mov ebx, valb
                    sub ebx, vala           ; b=b-a
                    mov valb, ebx
                    jmp over

                AGB:
                    mov eax, vala
                    sub eax, valb           ; a=a-b
                    mov vala, eax
                    jmp over

                over:
                    jmp CONTD

            STOP:
                mov ebx, valb
                ret  
    ret      
                
findGCD endp


findLCM proc av : PTR DWORD, bv : PTR DWORD, vgcd : PTR DWORD
    LOCAL prod : DWORD
    
    mov edx, 0
    cmp av, edx
    je AEZ
    jne ANZ

    AEZ:
        mov ebx, 0
        ret

    ANZ:
        mov ecx, 0
        cmp bv, ecx
        je BEZ
        jne BNZ

        BEZ:
            mov ebx, 0
            ret
        BNZ:
            mov edx, av
            imul edx, bv
            mov prod, edx               ; p = a * b

            xor edx, edx
            xor eax, eax
            
            mov eax, prod
            cdq
            mov ecx, vgcd
            idiv ecx
            mov ebx, eax
            ret

findLCM endp

end start          