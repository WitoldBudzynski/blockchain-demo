package pl.nt4.blockchain.demo;

import org.apache.commons.lang3.StringUtils;

public class HashProvider {

    public static final HashFunction getHashFunction() {
        return new HashFunctionSha256Hex();
    }

    public static  final int DIFFICULITY = 2;

    public static final String DIFFICULITY_PREFIX = StringUtils.repeat('0', DIFFICULITY);

}
