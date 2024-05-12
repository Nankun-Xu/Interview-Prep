import java.util.*;

public class GetRandom {
    Random random;
    List<Integer> keyList;//use a list of keys to make get keyvalue O(1)
    Map<Integer,Integer> map;//

    public GetRandom(){
        random = new Random();
        keyList = new ArrayList<>();//use a list of keys to make get keyvalue O(1)
        map = new HashMap<Integer,Integer>();//
    }

    public void put(int key, int val){
        map.put(key,val);
        keyList.add(key);
    }

    public int get(int key){
        return map.get(key);
    }

    public void remove(int key){
        map.remove(key);
        for(int i = 0; i < keyList.size(); i++){
            if(keyList.get(i) == key){
                keyList.remove(i);
            }
        }
        /*  int idx = map.get(val);//(val,idx(key))
            int last = list.get(list.size() - 1);//get last element of list
            list.set(idx, last);//put last element to the position of the element you want to remove
            map.put(last, idx);//update map(orginal (last, list.size() - 1))
            map.remove(val);//remove by val in map
            list.remove(list.size() - 1);//remove in list*/

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