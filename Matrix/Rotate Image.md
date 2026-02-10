# 48. Rotate Image

## 🔗 Problem

LeetCode: Rotate Image

Given an `n x n` 2D matrix, rotate the image **90 degrees clockwise** **in-place**.

---

## 🧠 Intuition

A 90° clockwise rotation can be broken into **two simple operations**:

1. **Transpose the matrix**

   * Convert rows into columns
2. **Reverse each row**

   * Completes the clockwise rotation

This avoids using extra space and keeps the operation in-place.

---

## ⚙️ Approach (Transpose + Reverse)

### Step 1: Transpose

Swap:

```
matrix[i][j] ↔ matrix[j][i]
```

for all `j > i`

### Step 2: Reverse each row

Use two pointers (`left`, `right`) to reverse each row.

---

## ⏱️ Complexity

| Type  | Complexity |
| ----- | ---------- |
| Time  | O(n²)      |
| Space | O(1)       |

---

## 💻 Java Solution

```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose matrix
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
}
```

---

## 🧩 Pattern Learned

* Matrix manipulation
* In-place transformation
* Decomposition of complex operation into simple steps
