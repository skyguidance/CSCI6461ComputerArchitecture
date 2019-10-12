package Computer;

/**
 * Instruction enumerator.
 * This is used for easy programming.
 */
public enum Instruction {
    HALT(0),
    LDR(1),
    STR(2),
    LDA(3),
    AMR(4),
    SMR(5),
    AIR(6),
    SIR(7),
    JZ(10),
    JNE(11),
    JCC(12),
    JMA(13),
    JSR(14),
    RFS(15),
    SOB(16),
    JGE(17),
    MLT(20),
    DVD(21),
    TRR(22),
    AND(23),
    ORR(23),
    NOT(25),
    SRC(31),
    RRC(32),
    FADD(33),
    FSUB(34),
    VADD(35),
    VSUB(36),
    CNVRT(37),
    TRAP(36),
    LDX(41),
    STX(42),
    LDFR(50),
    STFR(51),
    IN(61),
    OUT(62),
    CHK(63)
    ;
    private  final String opcode;
    private Instruction(int value){
        opcode=Integer.toBinaryString(value);
    }
    public String getOpcode(){
        return opcode;
    }
}