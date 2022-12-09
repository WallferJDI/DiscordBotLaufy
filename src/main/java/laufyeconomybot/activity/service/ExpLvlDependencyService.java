package laufyeconomybot.activity.service;

import com.wallferjdi.laufyeconomybot.activity.utils.RankTableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class ExpLvlDependencyService {

    @Autowired
    private RankTableMap rankTableMap;
    private Map<Integer,Integer> xpPerLevelTable;

    @PostConstruct
    private void init(){

        xpPerLevelTable  =rankTableMap.getXpPerLevelTable();
    }

    public Integer resolveExpToLevelDependency(Integer expCount){


        for (int i =1;i<xpPerLevelTable.size();i++){
            int j = i+1;
            if(expCount>=xpPerLevelTable.get(i)&&expCount<=xpPerLevelTable.get(j)){
                return i;
            }
        }
        return 0;
    }

    public void generateLvls(){
        int v;
        for(int i = 0;i<100;i++){
            System.out.println((int) (((i + 1) * 100) + Math.pow((((i + 1) * 4)), 2)));
        }



    }

    public Integer resolveLevelToExpDependency(Integer lvlCount){
        return xpPerLevelTable.get(lvlCount);
    }
}
