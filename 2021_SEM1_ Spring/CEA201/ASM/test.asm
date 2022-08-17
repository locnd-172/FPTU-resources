INCLUDE \masm32\include\masm32rt.inc
INCLUDE \masm32\macros\macros.asm

.DATA
    result  REAL8 0.0
    lpstring DWORD 0
    array   REAL4 16 DUP (0.0)
            SDWORD -1                           ; End of array -> NaN

.CODE
main PROC

    xor ebx, ebx

    @@:
    mov esi, input("Enter number here ",62," ") ; Input string ... STRING!
    cmp BYTE PTR [esi], 0;                      ; Nothing inputted?
    je @F                                       ; Yes -> jump forward to the next @@

    push ebx                                    ; StrToFloat changes EBX! So it is to save
    INVOKE StrToFloat, esi, ADDR result         ; Convert string to double
    pop ebx                                     ; Restore the saved EBX

    fld REAL8 PTR result                        ; Load a double ...
    fstp REAL4 PTR array[ebx]                   ; ... and save it as single
    mov eax, -1                                 ; NaN = end of array
    mov DWORD PTR array[ebx+4], eax             ; Store the NaN as the next element

    add ebx, 4                                  ; Pointer to the next REAL4 in array
    jmp @B                                      ; Jump back to the previous @@

    @@:
    xor ebx, ebx

    @@:
    mov eax, DWORD PTR array[ebx]               ; "DWORD PTR" = "REAL4 PTR"
    cmp eax, -1                                 ; NaN = end of array?
    je @F                                       ; Yes -> Jump to the next @@

    fld DWORD PTR array[ebx]                    ; Load a single into FPU ...
    fstp QWORD PTR result                       ; ... and store it as double
    printf("%f\n",result)                       ; MASM32 macro that acts like the C function

    add ebx, 4                                  ; REAL4 has 4 bytes
    jmp @B                                      ; Jump to the previous @@

    @@:

    exit 0

main ENDP

END main