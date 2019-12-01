# Machine Simulator
CSCI 6461 Session 10 Fall 2019 Group 8

## Screenshot
Material Design Lighter Theme

![avatar](https://github.com/baoziii/CSCI-6461/blob/master/Material_Design_Lighter_1.png?raw=true)
![avatar](https://github.com/baoziii/CSCI-6461/blob/master/Material_Design_Lighter_2.png?raw=true)

Material Design Ocean Theme

![avatar](https://github.com/baoziii/CSCI-6461/blob/master/Material_Design_Ocean_1.png?raw=true)
![avatar](https://github.com/baoziii/CSCI-6461/blob/master/Material_Design_Ocean_1.png?raw=true)

## Design
[Design note](https://github.com/baoziii/CSCI-6461/blob/master/Design-note.md) 

Basic Design Internal Diagram
![avatar](https://github.com/baoziii/CSCI-6461/blob/master/Internal-Code-Design-basic.jpg?raw=true)

## Progress
- [x] Phase I (Complete)
- [x] Phase II (Complete)
- [x] Phase III (Complete)
- [x] Phase IV (Complete)

## Functions
- Characteristics
    - [x] `R0`...`R3` (16 bits) General Purpose Registers (GPRs)
    - [x] `PC` (12 bits)
    - [x] `CC` (4 bits)
    - [x] `IR` (16 bits)
    - [x] `MAR` (16 bits)
    - [x] `MBR` (16 bits)
    - [x] `MFR` (4 bits): No need
    - [x] `X1`...`X3` (16 bits) 
    - [x] `memory.Memory` (2048 words, expandable to 4096 words)
    - [x] `Cache` 16 Line X (8 Word + Tag + Valid)
- instruction.Instructions (Opcode)
    - [x] `LDR` (01): Load memory.Memory to register.Register
    - [x] `STR` (02): Store register.Register to memory.Memory
    - [x] `LDA` (03): Load memory.Address to register.Register
    - [x] `AMR` (04): Add memory to register 
    - [x] `SMR` (05): Subtract memory from register
    - [x] `AIR` (06): Add immediate to register
    - [x] `SIR` (07): Subtract immediate from register
    - [x] `JZ` (10): Jump if Zero
    - [x] `JNE` (11): Jump if not equal
    - [x] `JCC` (12): Jump if Condition Code
    - [x] `JMA` (13): Unconditional Jump to memory.Address
    - [x] `JSR` (14): Jump and save Return memory.Address
    - [x] `RFS` (15): Return from subroutine
    - [x] `SOB` (16): Subtract one and branch
    - [x] `JGE` (17): Jump address greater or equal
    - [x] `MLT` (20): Multiply register by register
    - [x] `DVD` (21): Divide register by register
    - [x] `TRR` (22): Test equality of register and register
    - [x] `AND` (23): Logical and of register and register
    - [x] `ORR` (24): Logical or of register and register
    - [x] `NOT` (25): Logical not of register and register
    - [x] `SRC` (31): Shift register by count
    - [x] `RRC` (32): Rotate register by count
    - [x] `FADD` (33): Add memory to floating point register
    - [x] `FSUB` (34): Subtract memory from floating point register
    - [x] `VADD` (35): Vector add
    - [x] `VSUB` (36): Vector subtract
    - [x] `CNVRT` (37): Convert to fixed or floating point
    - [x] `LDX` (41): Load Index register register from memory
    - [x] `STX` (42): Store Index register register to memory
    - [x] `LDFR` (50): Load floating point register from memory
    - [x] `STFR` (51): Store floating point register to memory
    - [x] `IN` (61): Input character to register from device
    - [x] `OUT` (62): Output character to device from register
    - [x] `CHK` (63): Check device status to register

## Memory Reserved Locations:

|Memory Address|Usage|
|:----:|:----|
|0|Reserved for the Trap instruction for Part III|
|1|Reserved for a machine fault|
|2|Store PC for Trap|
|3|Not Used|
|4|Store PC for Machine Fault|
|5|Not Used|
|6|I/O Devices Status|
|7|I/O Devices Data Buffer|
|8|Arguments List Address|
|9|Store PC for Subroutine|
