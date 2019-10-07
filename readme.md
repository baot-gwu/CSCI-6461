# Machine Simulator
CSCI 6461

## Progress
- [x] Phase I (Complete)
- [ ] Phase II (In Progress)
- [ ] Phase III (Not Start)
- [ ] Phase IV (Not Start)

## Functions
- Characteristics
    - [x] `R0`...`R3` (16 bits) General Purpose Registers (GPRs)
    - [x] `PC` (12 bits)
    - [x] `CC` (4 bits)
    - [x] `IR` (16 bits)
    - [x] `MAR` (16 bits)
    - [x] `MBR` (16 bits)
    - [ ] `MFR` (4 bits)
    - [x] `X1`...`X3` (16 bits) 
    - [x] `memory.Memory` (2048 words, expandable to 4096 words)
    - [x] `Cache` 16 Line X (8 Word + Tag + Valid)
- instruction.Instructions (Opcode)
    - [x] `LDR` (01): Load memory.Memory to register.Register
    - [x] `STR` (02): Store register.Register to memory.Memory
    - [x] `LDA` (03): Load memory.Address to register.Register
    - [x] `AMR` (04):
    - [x] `SMR` (05):
    - [x] `AIR` (06):
    - [x] `SIR` (07):
    - [x] `JZ` (10): Jump if Zero
    - [x] `JNE` (11): Jump if not equal
    - [x] `JCC` (12): Jump if Condition Code
    - [x] `JMA` (13): Unconditional Jump to memory.Address
    - [ ] `JSR` (14): Jump and save Return memory.Address
    - [x] `RFS` (15): 
    - [x] `SOB` (16):
    - [x] `JGE` (17):
    - [x] `MLT` (20):
    - [x] `DVD` (21):
    - [x] `TRR` (22):
    - [x] `AND` (23):
    - [x] `ORR` (24):
    - [x] `NOT` (25):
    - [x] `SRC` (31):
    - [x] `RRC` (32):
    - [ ] `FADD` (33):
    - [ ] `FSUB` (34):
    - [ ] `VADD` (35):
    - [ ] `VSUB` (36):
    - [ ] `CNVRT` (37):
    - [x] `LDX` (41): Load Index register.Register from memory.Memory
    - [x] `STX` (42): Store Index register.Register to memory.Memory
    - [ ] `LDFR` (50):
    - [ ] `STFR` (51):
    - [ ] `IN` (61):
    - [ ] `OUT` (62):
    - [ ] `CHK` (63):
