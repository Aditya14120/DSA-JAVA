# 4. Median of Two Sorted Arrays

## 🔗 Problem

LeetCode: Median of Two Sorted Arrays

Given two sorted arrays `nums1` and `nums2`, return the median of the two sorted arrays.

**Time complexity must be `O(log(min(n, m)))`.**

---

## 🧠 Intuition

Merging both arrays would give median easily — but that’s **O(n + m)**, not allowed.

Key observation:

> Median divides the combined array into two equal halves
> where **left half ≤ right half**

Instead of merging arrays, we:

* Binary search on the **smaller array**
* Try to partition both arrays such that:

  * left part has `(n + m + 1) / 2` elements
  * max(left) ≤ min(right)

Once correct partition is found → median is direct.

---

## ⚙️ Approach (Binary Search on Partition)

Let:

* `A` = smaller array
* `B` = larger array

We choose a cut in `A`, and compute corresponding cut in `B`.

Conditions for correct partition:

```
Aleft <= Bright
Bleft <= Aright
```

If conditions fail:

* Move left or right using binary search

Median calculation:

* If total length is odd → max(left)
* If even → average of max(left) and min(right)

---

## ⏱️ Complexity

| Type  | Complexity        |
| ----- | ----------------- |
| Time  | O(log(min(n, m))) |
| Space | O(1)              |

---

## 💻 Java Solution

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1.length > nums2.length)
            return findMedianSortedArrays(nums2, nums1);

        int n1 = nums1.length;
        int n2 = nums2.length;

        int low = 0, high = n1;

        while (low <= high) {

            int cut1 = (low + high) / 2;
            int cut2 = (n1 + n2 + 1) / 2 - cut1;

            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];

            int right1 = (cut1 == n1) ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = (cut2 == n2) ? Integer.MAX_VALUE : nums2[cut2];

            if (left1 <= right2 && left2 <= right1) {

                if ((n1 + n2) % 2 == 0)
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                else
                    return Math.max(left1, left2);
            }
            else if (left1 > right2) {
                high = cut1 - 1;
            }
            else {
                low = cut1 + 1;
            }
        }

        return 0.0;
    }
}
```

---

## 🧩 Pattern Learned

* Binary search on answer space
* Partition-based thinking
* Median without merging
* Handling edge cases using ±∞
