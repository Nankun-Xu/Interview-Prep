import java.util.*;

public class GetRandom {
    Random random;
    List<Integer> keyList;//use a list of keys to make get keyvalue O(1)
    Map<Integer,Integer> map;
    Map<Integer, Integer> idxMap;//<key, idx>

    public GetRandom(){
        random = new Random();
        keyList = new ArrayList<>();//use a list of keys to make get keyvalue O(1)
        map = new HashMap<Integer,Integer>();//
        idxMap = new HashMap<>();
    }

    public void put(int key, int val){
        map.put(key,val);
        keyList.add(key);
        idxMap.put(key, keyList.size() - 1);
    }

    public int get(int key){
        return map.get(key);
    }

    public void remove(int key){
        //remove the key from keylist -> switch the last element with key and remove the last ele
        int keyIdx = idxMap.get(key);
        int lastKey = keyList.get(keyList.size() - 1);
        keyList.set(keyIdx,lastKey);
        keyList.remove(keyList.size() - 1);
        //remove the key from idxmap -> O(1), update lastkey,idx
        idxMap.remove(key);
        idxMap.put(lastKey, keyIdx);
        //remove the key from map -> O1
        map.remove(key);
//        for(int i = 0; i < keyList.size(); i++){
//            if(keyList.get(i) == key){
//                keyList.remove(i);
//            }
//        }
    }

    public int getRandomValue(){
        int randomIdx = random.nextInt(keyList.size());
        int randomKey = keyList.get(randomIdx);
        return map.get(randomKey);
    }

    public static void main(String[] args){
        GetRandom getRandom = new GetRandom();
        getRandom.put(2,4);
        getRandom.put(3,4);
        getRandom.put(0,9);
        getRandom.put(1,8);
        getRandom.remove(2);
        System.out.println(getRandom.get(1));
        System.out.println(getRandom.getRandomValue());
    }
}