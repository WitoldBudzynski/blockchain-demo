package pl.nt4.blockchain.demo;

import java.io.IOException;

public class BlockchainMiner implements Runnable {

    private final String firstBlockHash;

    public BlockchainMiner(String firstBlockHash) {
        this.firstBlockHash  = firstBlockHash;
    }

    @Override
    public void run() {
        Blockchain privBlockChain = new Blockchain(firstBlockHash);
        try {
            privBlockChain.load("blockchain.snapshot");
        } catch (IOException e) {
            e.printStackTrace();
        }

        privBlockChain.validate();
        privBlockChain.print();
    }
}
