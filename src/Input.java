/**
 * @author jalal
 * @since 12/9/19
 */
public class Input {

    private String text;
    private InstructionType instructionType;

    private String ix1;
    private String ix2;
    private String ix3;

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
}
