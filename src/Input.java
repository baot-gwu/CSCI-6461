/**
 * @author jalal
 * @since 12/9/19
 */
public class Input {

    private String text;
    private InstructionType instructionType;

    private String r0;
    private String r1;
    private String r2;
    private String r3;

    private String ix1;
    private String ix2;
    private String ix3;

    private String pc;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public InstructionType getInstructionType() {
        return instructionType;
    }

    public void setInstructionType(InstructionType instructionType) {
        this.instructionType = instructionType;
    }

    public String getR0() {
        return r0;
    }

    public void setR0(String r0) {
        this.r0 = r0;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }

    public String getR3() {
        return r3;
    }

    public void setR3(String r3) {
        this.r3 = r3;
    }

    public String getIx1() {
        return ix1;
    }

    public void setIx1(String ix1) {
        this.ix1 = ix1;
    }

    public String getIx2() {
        return ix2;
    }

    public void setIx2(String ix2) {
        this.ix2 = ix2;
    }

    public String getIx3() {
        return ix3;
    }

    public void setIx3(String ix3) {
        this.ix3 = ix3;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }
}
