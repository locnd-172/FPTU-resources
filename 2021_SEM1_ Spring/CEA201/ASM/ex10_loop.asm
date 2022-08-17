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
    LOCAL cnt : DWORD

    mov cnt, 3
    print chr$("Program will calc a + b in ")
    print str$(cnt)
    print chr$(" times: ", 13, 10)

    CONTD:
        cmp cnt, 0
        je STOP

        print chr$("Time ")
        mov eax, 4
        sub eax, cnt
        print str$(eax)
        print chr$(": ", 13, 10)

        mov va, sval(input("Enter a: "))
        mov vb, sval(input("Enter b: "))

        invoke calc_sum, va, vb
        mov res, eax
 

        print chr$("sum: ")
        print str$(res)
        print chr$(13, 10, 13, 10)

        dec cnt
        jmp CONTD
        
     STOP:
            ret
 
main endp

calc_sum proc va : DWORD, vb : DWORD
    mov eax, va
    add eax, vb

    ret
calc_sum endp

end start                      