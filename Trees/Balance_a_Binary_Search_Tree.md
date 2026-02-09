# 1382. Balance a Binary Search Tree

## 🔗 Problem

LeetCode: Balance a Binary Search Tree

Given a Binary Search Tree, return a **balanced BST** containing the same node values.

A BST is balanced if:

```
|height(left) - height(right)| ≤ 1
```

for every node.

---

## 🧠 Intuition

A key property of a **BST**:

> Inorder traversal of a BST gives a **sorted sequence**.

So the problem reduces to:

1. Extract all node values in sorted order
2. Rebuild a **balanced BST** from that sorted list

This is similar to constructing a BST from a sorted array.

---

## ⚙️ Approach (Inorder + Divide & Conquer)

### Step 1: Inorder Traversal

* Traverse the BST in inorder
* Store values in a list
* Resulting list is sorted

### Step 2: Build Balanced BST

* Pick middle element as root
* Recursively build left subtree from left half
* Recursively build right subtree from right half

This guarantees minimum height.

---

## ⏱️ Complexity

| Type  | Complexity |
| ----- | ---------- |
| Time  | O(n)       |
| Space | O(n)       |

---

## 💻 Java Solution

```java
class Solution {

    public TreeNode balanceBST(TreeNode root) {

        // Step 1: get sorted nodes
        List<Integer> inorder = new ArrayList<>();
        getInorder(root, inorder);

        // Step 2: build balanced BST
        return buildBalanced(inorder, 0, inorder.size() - 1);
    }

    private void getInorder(TreeNode root, List<Integer> list) {
        if (root == null) return;

        getInorder(root.left, list);
        list.add(root.val);
        getInorder(root.right, list);
    }

    private TreeNode buildBalanced(List<Integer> arr, int start, int end) {

        if (start > end) return null;

        int mid = start + (end - start) / 2;

        TreeNode root = new TreeNode(arr.get(mid));

        root.left = buildBalanced(arr, start, mid - 1);
        root.right = buildBalanced(arr, mid + 1, end);

        return root;
    }
}
```

---

## 🧩 Pattern Learned

* BST inorder traversal
* Tree to array conversion
* Divide & conquer tree construction
* Balancing via median selection
