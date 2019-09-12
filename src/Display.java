import java.util.List;

/**
 * @author jalal
 * @since 12/9/19
 */
public class Display {

    private String r0;
    private String r1;
    private String r2;
    private String r3;

    private String ix1;
    private String ix2;
    private String ix3;

    private String pc;
    private String mar;
    private String mbr;

    public Display(CiscComputer ciscComputer) {
        setGeneralPurposeRegisters(ciscComputer.getGeneralPurposeRegisters());
        setIndexRegisters(ciscComputer.getIndexRegisters());
    }

    private void setIndexRegisters(List<IndexRegister> indexRegisters) {
        setIx1(indexRegisters.get(0).getValue());
        setIx2(indexRegisters.get(1).getValue());
        setIx3(indexRegisters.get(2).getValue());
    }

    private void setGeneralPurposeRegisters(List<GeneralPurposeRegister> generalPurposeRegisters) {
        setR0(generalPurposeRegisters.get(0).getValue());;
        setR1(generalPurposeRegisters.get(1).getValue());;
        setR2(generalPurposeRegisters.get(2).getValue());;
        setR3(generalPurposeRegisters.get(3).getValue());;
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

    public String getMar() {
        return mar;
    }

    public void setMar(String mar) {
        this.mar = mar;
    }

    public String getMbr() {
        return mbr;
    }

    public void setMbr(String mbr) {
        this.mbr = mbr;
    }

    @Override
    public String toString() {
        return "Display{" +
                "r0=" + r0 + '\n' +
                ", r1=" + r1 + '\n' +
                ", r2=" + r2 + '\n' +
                ", r3=" + r3 + '\n' +
                ", ix1=" + ix1 + '\n' +
                ", ix2=" + ix2 + '\n' +
                ", ix3=" + ix3 + '\n' +
                ", pc=" + pc + '\n' +
                ", mar=" + mar + '\n' +
                ", mbr=" + mbr + '\n' +
                '}';
    }
}
