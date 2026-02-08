# 110. Balanced Binary Tree

## 🔗 Problem

LeetCode: Balanced Binary Tree

Given a binary tree, determine if it is height-balanced.

A tree is balanced if for every node:

```
|height(left) - height(right)| <= 1
```

---

## 🧠 Intuition

Naive idea:

* For every node → calculate height of left & right subtree
* Check difference

But calculating height repeatedly makes it **O(n²)**.

Instead, compute height **while checking balance** in one DFS traversal.

Key trick:

> If any subtree becomes unbalanced → return `-1` immediately and stop further checks.

So height function doubles as a validator.

---

## ⚙️ Approach (DFS Bottom-Up)

We calculate height recursively:

```
height(node):
    get left height
    get right height

    if abs diff > 1 → return -1 (unbalanced)

    else return max(left, right) + 1
```

Main function checks:

```
height(root) != -1
```

---

## ⏱️ Complexity

| Type  | Complexity           |
| ----- | -------------------- |
| Time  | O(n)                 |
| Space | O(h) recursion stack |

---

## 💻 Java Solution

```java
class Solution {

    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    private int height(TreeNode node) {

        if (node == null)
            return 0;

        int left = height(node.left);
        if (left == -1) return -1;

        int right = height(node.right);
        if (right == -1) return -1;

        if (Math.abs(left - right) > 1)
            return -1;

        return Math.max(left, right) + 1;
    }
}
```

---

## 🧩 Pattern Learned

* Bottom-up DFS
* Combine validation + computation
* Early termination optimization
