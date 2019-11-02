package main.java.common;

import main.java.register.ConditionCodeType;
import main.java.register.GeneralPurposeRegister;
import main.java.register.IndexRegister;

import java.util.List;

/**
 * @author jalal
 * @since 12/9/19
 *
 * The class to display current values of CISC computer
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

    private String mfr;
    private String ir;

    private String cc;
    private String cct;

    public Display(CiscComputer ciscComputer, boolean binary) {
        setGeneralPurposeRegisters(ciscComputer.getGeneralPurposeRegisters(), binary);
        setIndexRegisters(ciscComputer.getIndexRegisters(), binary);
        setMar(ciscComputer.getMemoryAddressRegister().getValue(binary));
        setMbr(ciscComputer.getMemoryBufferRegister().getValue(binary));
        setMfr(ciscComputer.getMachineFaultRegister().getValue(binary));
        setIr(ciscComputer.getInstructionRegister().getValue(binary));
        setPc(ciscComputer.getProgramCounter().getValue(binary));
        setCc(ciscComputer.getConditionCode().getValue(binary));

        ConditionCodeType conditionCodeType = ciscComputer.getConditionCode().getConditionCodeType();
        if (conditionCodeType != null) {
            setCct(conditionCodeType.toString());
        } else {
            setCct("");
        }
    }

    private void setIndexRegisters(List<IndexRegister> indexRegisters, boolean binary) {
        setIx1(indexRegisters.get(0).getValue(binary));
        setIx2(indexRegisters.get(1).getValue(binary));
        setIx3(indexRegisters.get(2).getValue(binary));
    }

    private void setGeneralPurposeRegisters(List<GeneralPurposeRegister> generalPurposeRegisters, boolean binary) {
        setR0(generalPurposeRegisters.get(0).getValue(binary));;
        setR1(generalPurposeRegisters.get(1).getValue(binary));;
        setR2(generalPurposeRegisters.get(2).getValue(binary));;
        setR3(generalPurposeRegisters.get(3).getValue(binary));;
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

    public String getIr() {
        return ir;
    }

    public void setIr(String ir) {
        this.ir = ir;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getMfr() {
        return mfr;
    }

    public void setMfr(String mfr) {
        this.mfr = mfr;
    }

    public String getCct() {
        return cct;
    }

    public void setCct(String cct) {
        this.cct = cct;
    }

    @Override
    public String toString() {
        return ", PC=" + (pc == null ? "" : pc)
                + ", R0=" + (r0 == null ? "" : r0)
                + ", R1=" + (r1 == null ? "" : r1)
                + ", R2=" + (r2 == null ? "" : r2)
                + ", R3=" + (r3 == null ? "" : r3)
                + ", IX1=" + (ix1 == null ? "" : ix1)
                + ", IX2=" + (ix2 == null ? "" : ix2)
                + ", IX3=" + (ix3 == null ? "" : ix3)
                + ", IR=" + (ir == null ? "" : ir)
                + ", MAR=" + (mar == null ? "" : mar)
                + ", MBR=" + (mbr == null ? "" : mbr)
                + ", MFR=" + (mfr == null ? "" : mfr)
                + ", CC=" + (cc == null ? "" : cc)
                + ", CCT=" + (cct == null ? "" : cct);
    }
}
