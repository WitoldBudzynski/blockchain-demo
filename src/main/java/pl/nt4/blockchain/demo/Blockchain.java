package pl.nt4.blockchain.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class Blockchain implements Serializable {


    private List<Block> blockchain = new LinkedList<>();
    private String firstBlockHash;

    public Blockchain(String firstBlockHash) {
        this.firstBlockHash = firstBlockHash;
    }


    public Block findLast() {
        if (blockchain.size() > 0) {
            return blockchain.get(blockchain.size() - 1);
        }
        else {
            return null;
        }
    }

    public void add(Block block) {
       if ((blockchain.size() == 0) || block.validate(findLast().getHash())) {
           blockchain.add(block);
       }
    }

    public boolean validate() {

        log.info("Full blockchain validation");
        String prevBlockHash = firstBlockHash;
        for(Block block : blockchain) {
            if (!block.validate(prevBlockHash)) {
                log.error("Invalid block found!");
                return false;
            }
            prevBlockHash = block.getHash();
        }
        log.info("Full blockchain is valid");
        return true;
    }

    public void print() {
        log.info("Full blockchain:");
        String prevBlockHash = firstBlockHash;
        for(Block block : blockchain) {
            log.info(block.toString());
        }
    }

    public void save(String fileName) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileUtils.writeStringToFile(new File(fileName), gson.toJson(blockchain));
        log.info("saved to file {}",  fileName);
    }

    public void load(String fileName) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = FileUtils.readFileToString(new File(fileName));
        Type type = new TypeToken<List<Block>>(){}.getType();
        blockchain = gson.fromJson(json, type);
    }


}
