import java.util.*;

public class GetUniqueRandomValue {
    Random random;
    List<Integer> valueList;//use a list of keys to make get keyvalue O(1)
    Map<Integer,Integer> map;
    Map<Integer,List<Integer>> countIdxMap;//count and idx fof values

    public GetRandom(){
        random = new Random();
        valueList = new ArrayList<>();
        map = new HashMap<Integer,Integer>();
        countIdxMap = new HashMap<Integer,Integer>();
    }

    public void put(int key, int val){
        map.put(key,val);
        if(countIdxMap.get(key) == 0) valueList.add(val);
        countIdxMap.put(val, countIdxMap.getOrDefault(val, 0) + 1);
    }

    public int get(int key){
        return map.get(key);
    }

    public void remove(int key){

    }

    public int getRandomValue(){
        int randomIdx = random.nextInt(valueList.size());
        return valueList.get(randomKey);
    }

    public static void main(String[] args){
        GetRandom getRandom = new GetRandom();
        getRandom.put(2,4);
        getRandom.put(3,4);
        getRandom.put(0,8);
        getRandom.put(1,8);
        getRandom.remove(2);
        System.out.println(getRandom.get(1));
        System.out.println(getRandom.getRandomValue());
    }
}
