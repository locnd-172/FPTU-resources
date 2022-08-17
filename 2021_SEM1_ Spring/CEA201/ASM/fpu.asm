
include \masm32\include\masm32rt.inc \

;---- section uninitialized data ---------------------------------------
.data?

res dq ?


;---- section read/write data ------------------------------------------
.data

single_value1   dq 12.34
single_value2   dq -6
single_value3   dq 9

.code

_start:

    finit                            ;reset fpu stacks to default
    fld    qword ptr [single_value2] ;single_value1 to fpu stack(st1)
    fld    qword ptr [single_value3] ;single_value2 to fpu stack(st0)
    fdiv                             ;st0 := st1 / st0
    fstp   tbyte ptr [res]        
    printf("%.5f", res)

_exit:
    ret
    
end _start