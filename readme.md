# Machine Simulator
CSCI 6461

## Progress
- [x] Phase I (Complete)
- [ ] Phase II (Not Start)
- [ ] Phase III (Not Start)
- [ ] Phase IV (Not Start)

## Functions
- Characteristics
    - [x] `R0`...`R3` (16 bits) General Purpose Registers (GPRs)
    - [x] `PC` (12 bits)
    - [ ] `CC` (4 bits)
    - [x] `IR` (16 bits)
    - [x] `MAR` (16 bits)
    - [x] `MBR` (16 bits)
    - [ ] `MFR` (4 bits)
    - [x] `X1`...`X3` (16 bits) 
    - [x] `Memory` (2048 words, expandable to 4096 words)
- instruction.Instructions (Opcode)
    - [x] `LDR` (01): Load Memory to Register
    - [x] `STR` (02): Store Register to Memory
    - [x] `LDA` (03): Load Address to Register
    - [ ] `AMR` (04):
    - [ ] `SMR` (05):
    - [ ] `AIR` (06):
    - [ ] `SIR` (07):
    - [ ] `JZ` (10): Jump if Zero
    - [ ] `JNE` (11): Jump if not equal
    - [ ] `JCC` (12): Jump if Condition Code
    - [ ] `JMA` (13): Unconditional Jump to Address
    - [ ] `JSR` (14): Jump and save Return Address
    - [ ] `RFS` (15): 
    - [ ] `SOB` (16):
    - [ ] `JGE` (17):
    - [ ] `MLT` (20):
    - [ ] `DVD` (21):
    - [ ] `TRR` (22):
    - [ ] `AND` (23):
    - [ ] `ORR` (24):
    - [ ] `NOT` (25):
    - [ ] `SRC` (31):
    - [ ] `RRC` (32):
    - [ ] `FADD` (33):
    - [ ] `FSUB` (34):
    - [ ] `VADD` (35):
    - [ ] `VSUB` (36):
    - [ ] `CNVRT` (37):
    - [x] `LDX` (41): Load Index Register from Memory
    - [x] `STX` (42): Store Index Register to Memory
    - [ ] `LDFR` (50):
    - [ ] `STFR` (51):
    - [ ] `IN` (61):
    - [ ] `OUT` (62):
    - [ ] `CHK` (63):