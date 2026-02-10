# 3719. Longest Balanced Subarray

## 🔗 Problem

Longest Balanced Subarray

A subarray is called **balanced** if:

```
Number of distinct even elements
=
Number of distinct odd elements
```

Return the length of the longest such subarray.

---

## 🧠 Intuition

We need to compare **distinct counts**, not frequencies.

So sliding window with shrinking doesn’t work easily because:

* Removing elements may or may not reduce distinct count
* Duplicate values complicate window maintenance

Given `n ≤ 1500`, we can safely use a **brute-force expansion strategy**.

Idea:

* Fix a starting index `i`
* Expand the subarray to the right
* Track distinct even and odd numbers using a HashMap

Whenever:

```
distinctEven == distinctOdd
```

we update the answer.

---

## ⚙️ Approach (Brute Force + HashMap)

1. Fix left boundary `i`
2. Create a frequency map for subarray `i → j`
3. Track:

   * `evenDistinct`
   * `oddDistinct`
4. Expand `j` from `i` to `n-1`
5. Update answer whenever counts match

---

## ⏱️ Complexity

| Type  | Complexity |
| ----- | ---------- |
| Time  | O(n²)      |
| Space | O(n)       |

Acceptable because `n ≤ 1500`.

---

## 💻 Java Solution

```java
import java.util.*;

class Solution {
    public int longestBalancedSubarray(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {

            Map<Integer, Integer> freq = new HashMap<>();
            int evenDistinct = 0;
            int oddDistinct = 0;

            for (int j = i; j < n; j++) {

                int val = nums[j];

                if (!freq.containsKey(val)) {
                    if (val % 2 == 0)
                        evenDistinct++;
                    else
                        oddDistinct++;
                }

                freq.put(val, freq.getOrDefault(val, 0) + 1);

                if (evenDistinct == oddDistinct) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
}
```

---

## 🧩 Pattern Learned

* Brute force with pruning
* Tracking **distinct** elements
* HashMap frequency technique
* When sliding window is **not** optimal
