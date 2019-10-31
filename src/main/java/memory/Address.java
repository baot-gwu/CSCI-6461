package main.java.memory;

/**
 * @author jalal
 * @since 29/9/19
 * <p>
 * memory.Memory memory.Address
 */
public class Address {

    private int effectiveAddress;
    private int tag;
    private int offset;

    public Address(int effectiveAddress) {
        this.effectiveAddress = effectiveAddress;
        this.tag = effectiveAddress / CacheLine.MAX_WORD;
        this.offset = effectiveAddress % CacheLine.MAX_WORD;
    }

    public int getEffectiveAddress() {
        return effectiveAddress;
    }

    public int getTag() {
        return tag;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return "Effective Address=" + effectiveAddress + ", tag=" + tag  + ", offset=" + offset;
    }
}
