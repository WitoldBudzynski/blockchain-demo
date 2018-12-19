package pl.nt4.blockchain.demo;

import org.apache.commons.codec.digest.DigestUtils;

public class HashFunctionMd5Hex implements HashFunction {


    @Override
    public String hash(String data) {
        return DigestUtils.md5Hex(data);
    }
}
