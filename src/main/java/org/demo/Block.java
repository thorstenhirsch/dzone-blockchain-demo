package org.demo;

import lombok.Getter;
import lombok.ToString;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.IntStream;

@Getter
@ToString
public class Block implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public String toString() {
        return String.format("Block data: %s, hash: %s, previous: %s.",
                this.data, this.hash, this.previousHash);
    }

    public void writeToFile(String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
