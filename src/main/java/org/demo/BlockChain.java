package org.demo;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.stream.Stream;

public class BlockChain {

    /*
     * This is a very, very simple blockchain taken from:
     * https://dzone.com/articles/a-simplest-block-chain-in-java
     * (c) Amit Rathi
     *
     * I only replaced the ArrayList with a LinkedList, added the
     * serialization code and the maven project. (Thorsten Hirsch)
     *
     * Here's a more realistic example based on Merkle Trees:
     * https://dzone.com/articles/blockchain-implementation-with-java-code
     */

    public static void main(String[] args) {

        Block genesis = new Block("BlockChain", null);
        Block helloBlock = new Block("Hello", genesis);
        Block worldBlock = new Block("World", helloBlock);
        Block dZoneBlock = new Block("DZone", worldBlock);

        System.out.println("---------------------");
        System.out.println("- BlockChain -");
        System.out.println("---------------------");

        Stream.of(genesis, helloBlock, worldBlock, dZoneBlock).forEach(System.out::println);

        System.out.println("---------------------");
        System.out.println("Is valid?: " + validate(dZoneBlock));
        System.out.println("---------------------");

        dZoneBlock.writeToFile("dzoneblock.dat");
        System.out.printf("Block %s was succesfully written to file %s.%n", dZoneBlock.getData(), "dzoneblock.dat");
    }

    private static boolean validate(Block latestBlock) {

        boolean result = true;
        Block currentBlock = latestBlock;
        Block previousBlock;

        while(currentBlock.getPreviousBlock() != null) {
            previousBlock = currentBlock.getPreviousBlock();
            if (currentBlock.getPreviousHash() != previousBlock.getHash()) {
                result = false;
                break;
            }
            currentBlock = previousBlock;
        }

        return result;
    }
}