package pl.nt4.blockchain.demo;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.text.SimpleDateFormat;
import java.util.Date;

import static pl.nt4.blockchain.demo.HashProvider.DIFFICULITY_PREFIX;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Block {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("YYYYMMDDHHmmssZ");
    private static final char SEPARATOR = '#';

    private String prevHash;
    private String hash;
    private String content;
    private Date timestamp;

    // number to increment in mining process
    private int nope;

    public Block(String prevHash, String content, int nope) {
        this.prevHash = prevHash;
        this.content = content;
        this.timestamp = new Date();
        this.nope = nope;
        this.hash = this.calcHash();
    }

    public String calcAndSetHash() {
        hash = calcHash();
        return hash;
    }

    public String calcHash() {
        return HashProvider.getHashFunction().hash(StringUtils.join(
                prevHash,
                content ,
                SIMPLE_DATE_FORMAT.format(timestamp),
                Integer.toString(nope),
                SEPARATOR));
    }

    public void increment() {
        nope++;
    }


    public boolean validate(String prevBlockHash) {


        if (!prevHash.equals(prevBlockHash)) {
            log.warn("incorrect prevHash required: {} found {} ", prevBlockHash, prevHash);
            return false;
        }

        if (!calcHash().equals(hash)) {
            log.warn("invalid hash " + hash);
            return false;
        }

        if (!hash.startsWith(DIFFICULITY_PREFIX)) {
            log.warn("Invalid difficulity " + hash + " required " + DIFFICULITY_PREFIX);
            return false;
        }

        log.info("block {} is correct", hash);
        return true;
    }

}
