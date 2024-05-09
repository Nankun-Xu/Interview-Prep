// Most Letter
/*
Given an input list of strings, for each letter appearing anywhere 
in the list, find the other letter(s) that appear in the most 
number of words with that letter.

Example: 
['abc', 'bcd', 'cde'] =>
  {
	a: [b, c],	# b appears in 1 word with a, c appears in 1 word with a
	b: [c], 	# c appears in 2 words with b, a and d each appear in only 1 word with b
	c: [b, d], # b appears in 2 words with c, d appears in 2 words with c. 
But a and e each appear in only 1 word with c.
	d: [c], # c appears in 2 words with d. But b and e each appear in only 1 word with d
	e: [c, d], # c appears in 1 word with e, d appears in 1 word with e
		
  }
*/

public class MostLetter{
    public static void main(String[] args) {
        String[] strs  = {"abc", "bcd", "cde"};
        System.out.println(getLettersAppears(strs));
    }
    
    private static Map<Character, Set<Character>> getLettersAppears(String[] strs) {
        Map<Character, Set<Integer>> map = new HashMap<>();
        for(int i=0;i<strs.length;i++) {
            String s = strs[i];
            for(char c : s.toCharArray()) {
                map.putIfAbsent(c, new HashSet<>());
                map.get(c).add(i);
            }
        }
    
        Map<Character, Set<Character>> res = new HashMap<>();
        for(char key : map.keySet()) {
            Set<Integer> nums = map.get(key);
            Map<Character, Integer> cntMap = new HashMap<>();
            int max = 0;
            for(Integer pos : nums) {
                for(char k : map.keySet()) {
                    if(key == k)
                        continue;
                    Set<Integer> tmp = map.get(k);
                    if(tmp.contains(pos)) {
                        cntMap.put(k, cntMap.getOrDefault(k, 0) + 1);
                        max = Math.max(max, cntMap.get(k));
                    }
                }	
            }
            Set<Character> set = new HashSet<>();
            for(Map.Entry<Character, Integer> entry : cntMap.entrySet()) {
                if(entry.getValue() == max)
                    set.add(entry.getKey());
            }
            res.put(key, set);
        }
        
        return res; 
    }
}

