package org.demo;

import lombok.Getter;
import lombok.ToString;
import java.util.Arrays;
import java.util.stream.IntStream;

@Getter
@ToString
public class Block {

    private String data;
    private Block previousBlock; // pointer
    private int previousHash;
    private int hash;

    public Block(String data, Block previousBlock) {
        this.data = data;
        this.previousBlock = previousBlock;
        if (previousBlock != null) {
            this.previousHash = previousBlock.getHash();
        }
        // Mix the content of this block with previous hash to create the hash of this new block
        // and that's what makes it block chain
        this.hash = Arrays.hashCode(IntStream.of(data.hashCode(), this.previousHash).toArray());
    }

    public String println() {
        return String.format("Block data: %s, hash: %s, previous: %s.%n",
                this.data, this.hash, this.previousHash);
    }
}