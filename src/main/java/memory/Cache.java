package main.java.memory;

import main.java.util.Utils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author jalal
 * @since 29/9/19
 *
 * Cache of the machine, which contains 16 cache line.
 *
 * All memory related exchange done by this Class
 */
public class Cache {

    private static final int MAX_CACHE_LINES = 16;

    private static Queue<CacheLine> cacheLines = new LinkedList<>();

    private static void add(CacheLine cacheLine) {
        if (cacheLines.size() >= MAX_CACHE_LINES) {
            System.out.println("Cache is full. Removing the oldest element of the Cheche");

            cacheLines.remove();
        }

        cacheLines.add(cacheLine);
    }

    public static String getWordStringValue(Address address) {
        return getWord(address).getValue();
    }

    public static int getWordDecimalValue(Address address) {
        return Utils.unsignedBinaryToDecimal(getWord(address).getValue());
    }

    public static Word getWord(Address address) {
        CacheLine cacheLine = getCacheLine(address);

        if (cacheLine == null) {
            cacheLine = loadFromMemory(address.getTag());
        }

        return cacheLine.getWord(address.getOffset());
    }

    private static CacheLine getCacheLine(Address address) {
        for (CacheLine cacheLine : cacheLines) {
            if (cacheLine.isValid()) {
                if (cacheLine.isTagMatch(address.getTag())) {
                    return cacheLine;
                }
            }
        }

        return null;
    }

    private static CacheLine loadFromMemory(int tag) {
        CacheLine cacheLine = new CacheLine(tag);

        add(cacheLine);

        return cacheLine;
    }

    public static void writeToMemory(Address address, Word word) {
        CacheLine cacheLine = getCacheLine(address);

        if (cacheLine == null) {
            cacheLine = loadFromMemory(address.getTag());
        }

        cacheLine.setWord(address, word);
    }
}
