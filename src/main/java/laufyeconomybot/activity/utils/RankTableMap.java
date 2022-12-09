package laufyeconomybot.activity.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class RankTableMap {

    private int level = 1;

    private Map<Integer,Integer> xpPerLevelTable;

    @Value("${utils.rank.path}")
    private String rankTablePath;

   @PostConstruct
    public Map<Integer, Integer> loadXpPerLevel() throws IOException {

        Map<Integer, Integer> xpPerLevel = new LinkedHashMap<>();
       InputStream inputStream = getClass().getResourceAsStream("/xpPerLevel.txt");
       BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try (Stream<String> lines =  reader.lines()) {
            lines.forEach(line -> xpPerLevel.put(level++, Integer.valueOf(line)));
        }

        xpPerLevelTable=xpPerLevel;
        return xpPerLevel;
    }

    public Map<Integer, Integer> getXpPerLevelTable() {
        return xpPerLevelTable;
    }


}
