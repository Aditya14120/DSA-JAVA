# 33. Search in Rotated Sorted Array

## 🔗 Problem

LeetCode: Search in Rotated Sorted Array

You are given a rotated sorted array of **distinct integers** and a target value.
Return the index of the target if it exists, otherwise return `-1`.

---

## 🧠 Intuition

A rotated sorted array still has **one sorted half** at every step.

Example:

```
[4,5,6,7,0,1,2]
```

At any `mid`:

* Either **left half is sorted**
* Or **right half is sorted**

If we identify the sorted half, we can decide:

* whether the target lies in that half
* or move to the other half

So this becomes a **modified binary search**.

---

## ⚙️ Approach (Binary Search on Rotated Array)

1. Initialize `low = 0`, `high = n-1`
2. While `low <= high`:

   * Find `mid`
   * If `nums[mid] == target` → return `mid`
3. Check which half is sorted:

   * **Left sorted** if `nums[low] <= nums[mid]`
   * Else **right sorted**
4. Decide search direction based on target range

---

## ⏱️ Complexity

| Type  | Complexity |
| ----- | ---------- |
| Time  | O(log n)   |
| Space | O(1)       |

---

## 💻 Java Solution

```java
class Solution {
    public int search(int[] nums, int target) {

        int low = 0, high = nums.length - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] == target)
                return mid;

            // left half sorted
            if (nums[low] <= nums[mid]) {

                if (target >= nums[low] && target < nums[mid])
                    high = mid - 1;
                else
                    low = mid + 1;

            }
            // right half sorted
            else {

                if (target > nums[mid] && target <= nums[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }

        return -1;
    }
}
```

---

## 🧩 Pattern Learned

* Binary Search on rotated arrays
* Identifying sorted half
* Decision making using ranges
