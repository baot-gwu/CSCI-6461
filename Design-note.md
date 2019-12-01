# Packages
Source code is divided into following packages:
- common
- device
- instruction
- memory
- panel
- program
- register
- theme
- util

All the operations initialize by Main class. That class initialize class, which initialize CISCComputerClass.
All the register, memory and essential variables get ready for running.

- Common Package:
This package contains the main CiscComputer class which contains all the register, memory, devices, clock cycle count.
Class display is used for displaying states of all the elements of CiscComputer.
Initializer Class used for initializing all the elements of CiscComputer

- Device Package:
Device package contains CardReader, ConsoleKeyboard, ConsolePrinter. All of these extended from abstract class Device
and has unique DeviceType, which is enum.

- Instruction Package:
This package contains class for all the operations. ArithmeticLogicalInstructionProcessor,
FloatingPointInstructionProcessor, InputOutputInstructionProcessor, LoadStoreInstructionProcessor,
MiscellaneousInstructionProcessor, TransferInstructionProcessor implements interface Instruction
to be called and perform any instruction. All of this class has unique enum InstructionType.
This package also contains Address decoder to decode address for an instruction.

- Memory Package:
This package contains Address class, which is combine of tag and offset for caching. Cache contains, CacheLine
and each CacheLine contains 8 Words, that means contains of 8 location of main memory. Memory class is the main memory
which as 16 bit boolean value.

- Panel Package:
This package contains classes for UI. Controller, DebugPanel and OptionPanel.

- Register Package
This package contains all the registers which extends from abstract Register. All the register has unique RegisterType.

- Theme Package:
Contains class for for UI looks.

- Util:
Common functions used all over the system kept here.

Brief interaction class diagram shown on 
[Basic Design Internal Diagram](https://github.com/baoziii/CSCI-6461/blob/master/Internal-Code-Design-basic.jpg)









