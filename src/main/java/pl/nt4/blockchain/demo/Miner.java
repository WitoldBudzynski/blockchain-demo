package pl.nt4.blockchain.demo;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static pl.nt4.blockchain.demo.HashProvider.DIFFICULITY;
import static pl.nt4.blockchain.demo.HashProvider.DIFFICULITY_PREFIX;

@Slf4j
public class Miner {

    public Block mine(Block prevBlock, String content) {
        log.info("Mining (difficulity={}, prev={}} ...", DIFFICULITY, prevBlock.getHash() );

        Block currentBlock = new Block();
        currentBlock.setContent(content);
        currentBlock.setTimestamp(new Date());
        currentBlock.setPrevHash(prevBlock.getHash());


        int iteration = 0;
        Instant startTime = Instant.now();

        while (!currentBlock.calcAndSetHash().startsWith(DIFFICULITY_PREFIX)) {
            currentBlock.increment();
            iteration++;
        }

        Instant endTime = Instant.now();
        Duration miningTime = Duration.between(startTime, endTime);

        log.info("mined " + currentBlock + " with iterations " + iteration + " mined in "  + miningTime);

        return currentBlock;
    }



}
