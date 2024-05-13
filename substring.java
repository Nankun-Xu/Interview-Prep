import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public String[] shortestSubsequences(String[] arr) {
        HashMap<String, Integer> subsequenceFrequency = new HashMap<>();
        String[] result = new String[arr.length];

        // Count frequencies of all subsequences
        for (String str : arr) {
            HashSet<String> seenSubsequences = new HashSet<>();
            generateSubsequences(str, seenSubsequences);
            for (String sub : seenSubsequences) {
                subsequenceFrequency.put(sub, subsequenceFrequency.getOrDefault(sub, 0) + 1);
            }
        }

        // Find the shortest unique subsequences
        for (int idx = 0; idx < arr.length; ++idx) {
            String str = arr[idx];
            String shortestUnique = ""; // Track the shortest unique subsequence

            HashSet<String> seenSubsequences = new HashSet<>();
            generateSubsequences(str, seenSubsequences);

            for (String sub : seenSubsequences) {
                if (subsequenceFrequency.get(sub) == 1) {
                    if (shortestUnique.isEmpty() || sub.length() < shortestUnique.length() || (sub.length() == shortestUnique.length() && sub.compareTo(shortestUnique) < 0)) {
                        shortestUnique = sub; // Update if it's shorter or lexicographically smaller
                    }
                }
            }

            result[idx] = shortestUnique; // Assign the shortest unique subsequence found
        }

        return result;
    }

    private void generateSubsequences(String str, HashSet<String> subsequences) {
        int n = str.length();
        for (int i = 0; i < (1 << n); ++i) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; ++j) {
                if ((i & (1 << j)) > 0) {
                    sb.append(str.charAt(j));
                }
            }
            if (sb.length() > 0) {
                subsequences.add(sb.toString());
            }
        }
    }
}

