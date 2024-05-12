import java.util.*;

public class GetUniqueRandomValue {
    Random random;
    List<Integer> valueList;//use a list of keys to make get keyvalue O(1)
    Map<Integer,Integer> map;
    Map<Integer,Integer> countMap;//count of values

    public GetRandom(){
        random = new Random();
        valueList = new ArrayList<>();
        map = new HashMap<Integer,Integer>();
        countMap = new HashMap<Integer,Integer>();
    }

    public void put(int key, int val){
        map.put(key,val);
        if(countMap.get(key) == 0) valueList.add(val);
        countMap.put(val, countMap.getOrDefault(val, 0) + 1);
    }

    public int get(int key){
        return map.get(key);
    }

    public void remove(int key){
        int val = map.get(key);
        int count = countMap.get(val);
        map.remove(key);
        count--;
        if(count == 0){
            //delete val from countmap
            countMap.remove(val);
            //delete val from vallist
            for(int i = 0; i < valueList.size; i++){
                if(valueList(i) == val){
                    vallist.remove(i);
                }
            }
        }
        else countMap.put(val, count);
        /*  int idx = map.get(val);//(val,idx(key))
            int last = list.get(list.size() - 1);//get last element of list
            list.set(idx, last);//put last element to the position of the element you want to remove
            map.put(last, idx);//update map(orginal (last, list.size() - 1))
            map.remove(val);//remove by val in map
            list.remove(list.size() - 1);//remove in list*/

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
