package pl.nt4.blockchain.demo;

import org.apache.commons.codec.digest.DigestUtils;

public class HashFunctionSha256Hex implements HashFunction {
    @Override
    public String hash(String data) {
        return DigestUtils.sha256Hex(data);
    }
}
