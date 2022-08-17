
include \masm32\include\masm32rt.inc \

;---- section uninitialized data ---------------------------------------
.data?

single_sum      dd ?
single_sub      dd ?
single_mul      dd ?
single_div      dd ?
single2double   dq ?
double_sum      dq ?


;---- section read/write data ------------------------------------------
.data

single_value1   dd 12.34
single_value2   dd 102.35
single_value3   dd -52.02

double_value1   dq 12.34
double_value2   dq 102.35
double_value3   dq -52.02

extended_value1 dt 12.34
extended_value2 dt 102.35
extended_value3 dt -52.02

.code

_start:

example_1:
    finit                            ;reset fpu registers to default
    fld    dword ptr [single_value1] ;push value 12.34 to fpu stack
    fld    dword ptr [single_value2] ;push value 102.35 to fpu stack
    fld    dword ptr [single_value3] ;push value -52.02 to fpu stack

example_2;
    finit                            ;reset fpu registers to default
    fld    qword ptr [double_value1] ;push value 12.34 to fpu stack
    fld    qword ptr [double_value2] ;push value 102.35 to fpu stack
    fld    qword ptr [double_value3] ;push value -52.02 to fpu stack

example_3:
    finit                              ;reset fpu registers to default
    fld    tbyte ptr [extended_value1] ;push value 12.34 to fpu stack
    fld    tbyte ptr [extended_value2] ;push value 102.35 to fpu stack
    fld    tbyte ptr [extended_value3] ;push value -52.02 to fpu stack

example_4:
;+---------------------------------------------------------------------+
;|   Before fadd: st0 = 102.34999847412109375                          |
;|                st1 = 12.340000152587890625                          |
;|                                                                     |
;|   After fadd:  st0 = 114.689998626708984375                         |
;|                st1 = 0                                              |
;+---------------------------------------------------------------------+

    finit                            ;reset fpu stacks to default
    fld    dword ptr [single_value1] ;push single_value1 to fpu stack
    fld    dword ptr [single_value2] ;push single_value2 to fpu stack
    fadd                             ;st0 := st1 + st0
    fstp   dword ptr [single_sum]    ;store the sum result into mem


example_5:
;+---------------------------------------------------------------------+
;| Add Operation (single + single + single)                            |                         
;+---------------------------------------------------------------------+
    finit                            ;reset fpu stacks to default
    fld    dword ptr [single_value1] ;push single_value1 to fpu stack
    fld    dword ptr [single_value2] ;push single_value2 to fpu stack
    fadd                             ;st0 := st1 + st0
    fld    dword ptr [single_value3] ;push single_value3 to fpu stack
    fadd                             ;st0 := st1 + st0
    fstp   dword ptr [single_sum]    ;store the sum result into mem


example_6:
;+---------------------------------------------------------------------+
;| Add Operation (single + double + extended)                          |
;+---------------------------------------------------------------------+

    finit                              ;reset fpu stacks to default
    fld    dword ptr [single_value1]   ;push single_value1 to fpu stack
    fld    qword ptr [double_value2]   ;push double_value2 to fpu stack
    fadd                               ;st0 := st1 + st0
    fld    tbyte ptr [extended_value3] ;psh extended_value3 to fpu stack
    fadd                               ;st0 := st1 + st0
    fstp   qword ptr [double_sum]      ;store the sum result into mem


example_7:
;+---------------------------------------------------------------------+
;| Subtract Operation (single - single)                                |
;+---------------------------------------------------------------------+
    finit                            ;reset fpu stacks to default
    fld    dword ptr [single_value1] ;single_value1 to fpu stack(st1)
    fld    dword ptr [single_value2] ;single_value2 to fpu stack(st0)
    fsub                             ;st0 := st1 - st0
    fstp   dword ptr [single_sub]    ;store the subtract result into mem


example_8:
;+---------------------------------------------------------------------+
;| Multiply Operation (single * single)                                |
;+---------------------------------------------------------------------+
    finit                            ;reset fpu stacks to default
    fld    dword ptr [single_value1] ;single_value1 to fpu stack(st1)
    fld    dword ptr [single_value2] ;single_value2 to fpu stack(st0)
    fmul                             ;st0 := st1 * st0
    fstp   dword ptr [single_mul]    ;store the mult. result into mem


example_9:
;+---------------------------------------------------------------------+
;| Divide Operation (single / single)                                  |
;+---------------------------------------------------------------------+
    finit                            ;reset fpu stacks to default
    fld    dword ptr [single_value1] ;single_value1 to fpu stack(st1)
    fld    dword ptr [single_value2] ;single_value2 to fpu stack(st0)
    fdiv                             ;st0 := st1 / st0
    fstp   dword ptr [single_div]    ;store the div result into mem


example_10:
;+---------------------------------------------------------------------+
;| Convert single to double                                            |
;+---------------------------------------------------------------------+
    finit                            ;reset fpu stacks to default
    fld    dword ptr [single_value1] ;single_value1 to fpu stack(st0)
    fstp   qword ptr [single2double] ;store the double value into mem


example_11:
;+---------------------------------------------------------------------+
;| Compare float numbers (Find the largest value)                      |
;|                                                                     |
;| This example shows how to compare 2 floating-point numbers.         |
;|                                                                     |
;| Given single_value1 and single_value2,                              |
;| If single_value1 > single_value2 then                               |
;|     Print "single_value1 is bigger than single_value2"              |
;| Else If single_value1 < single_value2 Then                          |
;|     Print "single_value1 is less than single_value2"                |
;| Else                                                                |
;|     Print "single_value1 is equal to single_value2"                 |
;|                                                                     |
;|                                                                     |
;| The picture below shows                                             |
;| FPU Status Word 16-bit register (stores general condition of FPU):  |
;|                                                                     |
;|            +-----+                                                  |
;|     Bit 0  | IE  | --> Invalid operation exception                  |
;|            +-----+                                                  |
;|     Bit 1  | DE  | --> Denormalized exception                       |
;|            +-----+                                                  |
;|     Bit 2  | ZE  | --> Zero divide exception                        |
;|            +-----+                                                  |
;|     Bit 3  | OE  | --> Overflow exception                           |
;|            +-----+                                                  |
;|     Bit 4  | UE  | --> Underflow exception                          |
;|            +-----+                                                  |
;|     Bit 5  | PE  | --> Precision exception                          |
;|            +-----+                                                  |
;|     Bit 6  | SF  | --> Stack Fault exception                        |
;|            +-----+                                                  |
;|     Bit 7  | IR  | --> Interupt Request                             |
;|            +-----+                                                  |
;|     Bit 8  | C0  | --> CF (carry flag) [CONDITION CODE]             |
;|            +-----+                                                  |
;|     Bit 9  | C1  | --> =1 (stack overflow), =0 (stack underflow)    |
;|            +-----+                                                  |
;|     Bit 10 | C2  | --> PF (parity flag) [CONDITION CODE]            |
;|            +-----+                                                  |
;|     Bit 11 |     |                                                  |
;|     Bit 12 | TOP | --> Top of stack pointer                         |
;|     Bit 13 |     |                                                  |
;|            +-----+                                                  |
;|     Bit 14 | C3  | --> ZF (zero flag) [CONDITION CODE]              |
;|            +-----+                                                  |
;|     Bit 15 |  B  | --> B=1 (busy), B=0 (idle)                       |
;|            +-----+                                                  |
;|                                                                     |
;| This table shows FPU Condition Code Bits:                           |
;| +-------------+-------------------+-------------------------------+ |
;| |             |Condition Code bits|                               | |
;| | Instruction +----+----+----+----+           Condition           | |
;| |             | C3 | C2 | C1 | C0 |                               | |
;| +-------------+----+----+----+----+-------------------------------+ |
;| | fcom,       | 0  | 0  |    | 0  | st0 > source                  | |
;| | fcomp,      +----+----+----+----+-------------------------------+ |
;| | fcompp,     | 0  | 0  |    | 1  | st0 < source                  | |
;| | ficom,      +----+----+----+----+-------------------------------+ |
;| | ficomp      | 1  | 0  |    | 0  | st0 = source                  | |
;| |             +----+----+----+----+-------------------------------+ |
;| |             | 1  | 1  |    | 1  | st0 or source are undefined   | |
;| +-------------+----+----+----+----+-------------------------------+ |
;| reference: http://www.plantation-productions.com/Webster/           |
;|            www.artofasm.com/Linux/HTML/RealArithmetic.html          |
;|                                                                     |
;+---------------------------------------------------------------------+

    finit                            ;reset fpu stacks to default
    fld    dword ptr [single_value2] ;single_value2 to fpu stack(st1)
    fld    dword ptr [single_value1] ;single_value1 to fpu stack(st0)
    fcom                             ;compare st0 with st1
    fstsw  ax                        ;ax := fpu status register

    and    eax, 0100011100000000B ;take only condition code flags
    cmp    eax, 0000000000000000B ;is st0 > source ?
    je     example_11_greater
    cmp    eax, 0000000100000000B ;is st0 < source ?
    je     example_11_less
    cmp    eax, 0100000000000000B ;is st0 = source ?
    je     example_11_equal
    jmp    example_11_error      ;else, st0 or source are undefined

example_11_greater:
    print chr$("single_value1 is more than single_value2", 0aH, 00H)
    jmp    _exit

example_11_less:
    print chr$("single_value1 is less than single_value2", 0aH, 00H)
    jmp    _exit

example_11_equal:
    print chr$("single_value1 is equal to single_value2", 0aH, 00H)
    jmp    _exit

example_11_error:
    print chr$("st(0) and source are undefined!", 0aH, 00H)


_exit:
    invoke ExitProcess, 0

end _start