package pl.nt4.blockchain.demo;

import java.io.IOException;

public class BlockchainDemo {

    public final static String FIRST_BLOCK_EVER_SPECIAL_HASH = HashProvider.getHashFunction().hash("SPECIALHASH");

    public static void main(String[] args) throws IOException {

        // initialization block
        Block firstBlockEver = new Block(FIRST_BLOCK_EVER_SPECIAL_HASH, "FirstBlockEver", 0);
        String firstBlockHash = firstBlockEver.getHash();
        System.out.println(firstBlockEver.toString());

        // create blockchain using first ever init block
        Blockchain blockchain = new Blockchain(firstBlockEver.getHash());

        Miner miner = new Miner();

        // mine block
        Block block2 = miner.mine(firstBlockEver, "someinfo");
        System.out.println(block2.getHash());

        block2.validate(firstBlockHash);

        blockchain.add(block2);
        Block block3 = miner.mine(block2, "some very important info");
        blockchain.add(block3);
        blockchain.validate();

        Block block4 = miner.mine(block3, "some very important info");
        blockchain.add(block4);

        blockchain.validate();
        blockchain.print();

        // now we add invalid mined block
        Block invalidMined = new Block("0027c3a413e3bf9e4187554c56c0ae8c34c6679ad50d4be6dd82ab33d15a1729", "hehe ;]", 0);
        blockchain.add(invalidMined);


        blockchain.save("blockchain.snapshot");




    }




}
