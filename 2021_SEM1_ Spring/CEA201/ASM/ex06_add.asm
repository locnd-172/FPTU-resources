    ;Write a MASM program that will solve the equation ax+b=0 => x = -b / a

include \masm32\include\masm32rt.inc \

calc_sum PROTO : DWORD, : DWORD

.code    
                   
start:                       
    call main                   
    exit

main proc
    LOCAL va : DWORD
    LOCAL vb : DWORD
    LOCAL res : DWORD
      
    print chr$("Calc a + b", 13, 10)
    
    mov va, sval(input("Enter a : "))
    mov vb, sval(input("Enter b : "))

    push eax
    invoke calc_sum, va, vb
    mov res, eax
    pop eax

    print chr$("a + b = ")
    print str$(res)
    
    ret

main endp

calc_sum proc va : DWORD, vb : DWORD
    mov eax, va
    add eax, vb
    ret
calc_sum endp

end start                      