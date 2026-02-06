# Minimum Removals to Balance an Array

## 🔗 Problem

LeetCode: Minimum Removals to Balance an Array

---

## 🧠 Intuition

The array is considered **balanced** if for any chosen smallest element `x` and largest element `y` in the remaining array:

```
y <= x * k
```

Instead of removing elements randomly, we can:

1. Sort the array
2. Try to keep a valid continuous window `[left ... right]`
3. Remove everything outside that window

So the task becomes:

> Find the **largest valid subarray** where
> `nums[right] <= nums[left] * k`

Then:

```
removals = total_elements - size_of_valid_subarray
```

We maximize the window → minimize removals.

---

## ⚙️ Approach (Two Pointer / Sliding Window)

1. Sort the array
2. Use two pointers `left` and `right`
3. Expand `right` while condition holds
4. Window size = `right - left`
5. Track maximum valid window

Finally:

```
answer = n - max_window_size
```

---

## ⏱️ Complexity

| Type        | Complexity |
| ----------- | ---------- |
| Sorting     | O(n log n) |
| Two Pointer | O(n)       |
| Total       | O(n log n) |
| Space       | O(1)       |

---

## 💻 Java Solution

```java
class Solution {

    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int ans = n;
        int right = 0;

        for (int left = 0; left < n; left++) {
            while (right < n && nums[right] <= (long) nums[left] * k) {
                right++;
            }
            ans = Math.min(ans, n - (right - left));
        }

        return ans;
    }
}
```

---

## 🧩 Pattern Learned

* Sort + Sliding Window
* Maximize valid subarray instead of minimizing removals
* Convert condition problems → window problems
