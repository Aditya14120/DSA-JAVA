# 47. Permutations II

## 🔗 Problem

LeetCode: Permutations II

Given a collection of numbers that might contain duplicates, return all possible **unique permutations**.

---

## 🧠 Intuition

Normal permutation generates **n! permutations**.

But duplicates cause repeated permutations:

Example:

```
[1,1,2]

Normal permutations:
1,1,2
1,2,1
1,1,2   ❌ duplicate
1,2,1   ❌ duplicate
2,1,1
2,1,1   ❌ duplicate
```

We must **avoid generating duplicates**, not remove them later.

Key observation:

> If two identical numbers exist, we should only use the first unused one at a recursion level.

So we:

1. Sort array → duplicates become adjacent
2. Skip duplicates during recursion

---

## ⚙️ Approach (Backtracking + Pruning)

Steps:

1. Sort array
2. Use `used[]` to track chosen elements
3. Build permutation recursively
4. Skip duplicate case:

```
if current == previous AND previous not used
→ skip
```

This ensures:

* Same value is not chosen multiple times at same recursion level
* Prevents duplicate permutations

---

## ⏱️ Complexity

| Type  | Complexity           |
| ----- | -------------------- |
| Time  | O(n! × n)            |
| Space | O(n) recursion stack |

---

## 💻 Java Solution

```java
import java.util.*;

class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        backtrack(nums, used, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, boolean[] used,
                           List<Integer> curr, List<List<Integer>> res) {

        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (used[i]) continue;

            // duplicate pruning ⭐
            if (i > 0 && nums[i] == nums[i-1] && !used[i-1])
                continue;

            used[i] = true;
            curr.add(nums[i]);

            backtrack(nums, used, curr, res);

            curr.remove(curr.size() - 1);
            used[i] = false;
        }
    }
}
```

---

## 🧩 Pattern Learned

* Backtracking
* Duplicate pruning
* Sorting to handle duplicates
* Tree level vs branch level choice control
