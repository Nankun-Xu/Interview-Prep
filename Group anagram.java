//LC49 Group anagram
//first will compare two String, then originial 49, last optimize 
//kind like abbccc -> a1b2c3

//Compare Two String
//Time: O(nlogn), n is length of the longer string
//Space: O(n)
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length())return false;
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
        
    }
}

//Given an array of strings strs, group the anagrams together.
//In this case we can use hashmap to catagrize anagrams by key-list pair
//key is the combination of letters and value is the list of all valid anagrams
//To get the key we are going to sort each string and add the original str into list
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
 
        for(String str : strs){
            char[]strKey = str.toCharArray();
            Arrays.sort(strKey);
            String key = new String(strKey);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
 
        return new ArrayList<List<String>>(map.values());
    }
}
//Time：O(nklog⁡k) n is the num of strings in strs，k is the max length of str
//we 1st need to sort each string so the complexity is klogk, O(1) to check with hashmap, the whole complexity is O(nklogk)
//Space：O(nk)，we need to use hashmap to store all the strs

//Follow up: Optimize time complexity
//we can use an int[] to store the number of each different chars in this string
//Then append the char and the count together as the key of the map
//In this case we only need to traverse the int array one time and the string one time during each loop
//So Time complexity decrease from klogk to 26 + n, 26 could be ignored so the whole TC is O(nk)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagrams = new HashMap<>();
        for(String str : strs){
            int[] chars = new int[26];
            for(int i = 0; i < str.length(); i++){
                chars[str.charAt(i) - 'a']++;
            }
 
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 26; i++){
                if(chars[i] != 0){
                    sb.append((char)('a' + i));
                    sb.append(chars[i]);
                }
            }
            List<String> list = anagrams.getOrDefault(sb.toString(), new ArrayList<String>());
            list.add(str);
            anagrams.put(sb.toString(),list);
        }
 
        return new ArrayList<List<String>>(anagrams.values());
    }
 }
 

 
