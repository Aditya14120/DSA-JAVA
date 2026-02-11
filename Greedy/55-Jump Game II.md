# 55. Jump Game

## 🔗 Problem

LeetCode: Jump Game

You are given an integer array `nums` where each element represents your maximum jump length from that position.

Return `true` if you can reach the last index, otherwise return `false`.

---

## 🧠 Intuition

At each position, you don’t need to simulate jumps.

You just need to track:

> What is the **farthest index** reachable so far?

If at any point:

```
current index > maxReach
```

it means you are stuck.

If `maxReach` reaches or crosses the last index → you win.

This is a pure greedy reachability problem.

---

## ⚙️ Approach (Greedy)

1. Initialize:

```
maxReach = 0
```

2. Traverse array:

   * If `i > maxReach` → cannot proceed → return false
   * Update:

```
maxReach = max(maxReach, i + nums[i])
```

* If `maxReach >= last index` → return true

---

## ⏱️ Complexity

| Type  | Complexity |
| ----- | ---------- |
| Time  | O(n)       |
| Space | O(1)       |

---

## 💻 Java Solution

```java
class Solution {
    public boolean canJump(int[] nums) {

        int maxReach = 0;

        for (int i = 0; i < nums.length; i++) {

            if (i > maxReach)
                return false;

            maxReach = Math.max(maxReach, i + nums[i]);

            if (maxReach >= nums.length - 1)
                return true;
        }

        return true;
    }
}
```

---

## 🧩 Pattern Learned

* Greedy reachability
* Maintain global maximum
* Early stopping optimization
* Foundation for Jump Game II
