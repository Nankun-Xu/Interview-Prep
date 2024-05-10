/*
input: 一个字符串数组, 比如 ["peloton", "pelican", "cheapoair", "cheapair"]
output: 对应每个字符串, 找出最短的unique substring, 如果有多个unique substring长度一样, 随便选一个输出.
ex:
peloton, 一个字符的substring有: p, e, l, o, t, n, 其中p, e, l, o, n在其他三个字符串中出现过, 不是unique, 所以不能选. 只有t合适
pelican, 一个字符的substring有: p, e, l, i, c, a, n, 这些都在其他三个字符串中出现过, 都不合适.
两个字符串的substring有: pe, el, li, ic, ca, an, pe出现在peloton中, el出现在peloton中. 但‌是, li, ic, ca, an都没有在其他字符串中出现过, 所以随便选一个.
最后答案是:(答案不唯一, 这是其他一个答案)
['t', 'li', 'po', 'pa']
*/

//Brute Force
public class ShortestSubstring {
        public static [] shortestSubstrings(String[] arr) {
            int n = arr.length;
            String[] ans = new String[n];

            //check each string element in arr
            for(int i = 0; i < n; i++){
                int strLen = arr[i].length();
                String res = "";
                //check from shortest substring, which size is 1
                for(int size = 1; size <= strLen && res.isEmpty();size++){
                    for(int j = size; j <= strLen; j++){
                        String subStr = arr[i].substring(j - size, j);
                        if(res.isEmpty() && isUnique(arr, i, subStr)) {
                            res = subStr;
                        }
                    }
                }
                ans[i] = res;
            }

            return ans;
        }

        //Helper function to check whether sub string is unique or not
        private static boolean isUnique(String[] arr, int i, String sub) {
            for (int j = 0; j < arr.length; j++) {
                if (j != i && arr[j].contains(sub)) {
                    return false;
                }
            }
            return true;
        }
}


/*
Follow up:hash 优化
unique 最小 Sub Sequence 
*/