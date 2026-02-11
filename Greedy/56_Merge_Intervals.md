# 56. Merge Intervals

## 🔗 Problem

LeetCode: Merge Intervals

Given an array of intervals where
`intervals[i] = [start_i, end_i]`,

merge all overlapping intervals and return a new array of non-overlapping intervals.

---

## 🧠 Intuition

If intervals are sorted by **start time**,
then overlapping intervals will appear next to each other.

So the idea is:

1. Sort intervals by starting time.
2. Keep merging as long as they overlap.
3. If they don’t overlap, push the previous interval to result.

Key condition for overlap:

```
currentEnd >= nextStart
```

---

## ⚙️ Approach (Sort + Greedy Merge)

### Step 1: Sort intervals

Sort by `start`.

### Step 2: Iterate

Maintain a `current` interval:

* If overlapping → extend `current[1]`
* If not overlapping → add `current` to result and move forward

### Step 3: Add last interval

---

## ⏱️ Complexity

| Type  | Complexity |
| ----- | ---------- |
| Time  | O(n log n) |
| Space | O(n)       |

Sorting dominates time complexity.

---

## 💻 Java Solution

```java
import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {

        // Step 1: Sort intervals based on start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();

        // Step 2: Start with first interval
        int[] current = intervals[0];

        for (int i = 1; i < intervals.length; i++) {

            // If overlapping
            if (current[1] >= intervals[i][0]) {
                current[1] = Math.max(current[1], intervals[i][1]);
            }
            else {
                result.add(current);
                current = intervals[i];
            }
        }

        // Add last interval
        result.add(current);

        return result.toArray(new int[result.size()][]);
    }
}
```

---

## 🧩 Pattern Learned

* Interval sorting
* Greedy merging
* Overlap detection
* Foundation for interval scheduling problems
