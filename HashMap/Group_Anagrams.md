# 49. Group Anagrams

## 🔗 Problem

LeetCode: Group Anagrams

Given an array of strings `strs`, group the anagrams together.

Two strings are anagrams if they contain the same characters with same frequency.

---

## 🧠 Intuition

Anagrams contain identical characters but in different order:

```
eat, tea, ate → same frequency of letters
```

So instead of sorting every string (costly), we can build a **character frequency signature**.

For each word:

* Count occurrences of 26 letters
* Create a unique key from frequency array

Example:

```
eat → [1,0,0,0,1,0,...,1]
tea → [1,0,0,0,1,0,...,1]

Same key → same group
```

Thus all anagrams map to the same bucket.

---

## ⚙️ Approach (HashMap + Frequency Encoding)

1. Create HashMap<String, List<String>>
2. For each word:

   * Build frequency array size 26
   * Convert it to string key
3. Store words under same key

We use delimiter `#` to avoid ambiguity:

```
[1,11] != [11,1]
```

---

## ⏱️ Complexity

| Type  | Complexity |
| ----- | ---------- |
| Time  | O(n × k)   |
| Space | O(n × k)   |

Where:

* n = number of strings
* k = average word length

Better than sorting approach `O(n × k log k)`

---

## 💻 Java Solution

```java
import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String word : strs) {

            int[] freq = new int[26];

            for (char c : word.toCharArray())
                freq[c - 'a']++;

            StringBuilder key = new StringBuilder();
            for (int f : freq) {
                key.append(f).append('#');
            }

            map.putIfAbsent(key.toString(), new ArrayList<>());
            map.get(key.toString()).add(word);
        }

        return new ArrayList<>(map.values());
    }
}
```

---

## 🧩 Pattern Learned

* Hashing using frequency signature
* Replace sorting with counting
* Reduce complexity using encoding
