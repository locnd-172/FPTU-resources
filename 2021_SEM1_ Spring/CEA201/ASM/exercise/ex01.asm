; syntax of arithmethic instructions

include \masm32\include\masm32rt.inc

.code

start:
    call main
    exit

main proc
    ; ADD reg, reg / mem, reg / reg, mem / reg, immed / mem, immed / accum, immed
    print chr$("ADD: 1 + 2 = ")
    mov eax, 1
    add eax, 2
    print str$(eax)

    print chr$(13, 10)
    ; SUB reg, reg / mem, reg / reg, mem / reg, immed / mem, immed / accum, immed
    print chr$("SUB: 3 - 2 = ")
    mov eax, 3
    sub eax, 2
    print  str$(eax)

    print chr$(13, 10)
    ; MUL reg / mem (unsigned multiply: eax * reg / mem) 
    print chr$("MUL: 3 * 5 = ")
    mov eax, 3
    mov ebx, 5
    mul ebx
    print str$(eax)

    print chr$(13, 10)
    ; IMUL reg / mem / reg, mem / reg, immed / reg, reg, immed / reg, mem , immed (singed multiply)
    print chr$("IMUL: 10 * (-5) = ")
    mov eax, 10
    mov ebx, -5
    imul ebx, eax
    print str$(ebx)

    print chr$(13, 10)
    ; DIV reg / mem (unsigned divide)
    print chr$("DIV: 26 / 5 = ")
    xor edx, edx
    xor eax, eax
    
    mov eax, 26
    mov ebx, 5
    div ebx
    print str$(eax)  ; eax : store quotient
    
    print chr$(" , r = ")
    xor edx, edx
    xor eax, eax
    
    mov eax, 26
    mov ebx, 5
    div ebx
    print str$(edx) ; edx : store remainder

    print chr$(13, 10)
    ; IDIV reg / mem (signed divide)
    print chr$("IDIV: -43 / 5 = ")
    xor edx, edx
    xor eax, eax
    
    mov eax, -43
    cdq
    mov ecx, 5
    idiv ecx
    print str$(eax)

    print chr$(" , r = ")
    xor edx, edx
    xor eax, eax
    
    mov eax, -43
    cdq
    mov ecx, 5
    idiv ecx
    print str$(edx)

    ret
    

main endp

end start