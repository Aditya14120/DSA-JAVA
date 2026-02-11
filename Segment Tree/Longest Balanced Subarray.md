# 3721. Longest Balanced Subarray II

## 🔗 Problem

LeetCode: Longest Balanced Subarray II

A subarray is called **balanced** if:

```
Number of distinct even elements
=
Number of distinct odd elements
```

Constraints:

```
1 ≤ nums.length ≤ 10^5
```

---

# 🧠 Core Idea

We convert the problem into a **prefix sum equality problem**.

Define:

```
+1 → first occurrence of even number
-1 → first occurrence of odd number
```

Let:

```
prefix[i] = (# distinct even up to i)
            - (# distinct odd up to i)
```

A subarray `[l, r]` is balanced if:

```
prefix[r] - prefix[l-1] = 0
```

So we need:

```
prefix[r] = prefix[l-1]
```

Thus:

> Find the longest subarray where prefix sum remains equal.

Because distinct contribution changes dynamically when left pointer moves,
we need:

* Range updates
* Range queries
* Lazy propagation

---

# ⚙️ Approach Overview

### Step 1 — Track occurrences

Maintain a queue of indices for each number.

Only first active occurrence contributes to prefix sum.

### Step 2 — Build prefix sum

+1 for even
-1 for odd

### Step 3 — Build Segment Tree

Supports:

* Range add
* Query for last index where prefix value = 0

### Step 4 — Slide left pointer

When removing nums[i]:

* Remove its contribution
* Update range until next occurrence

---

# ⏱️ Complexity

| Type  | Complexity |
| ----- | ---------- |
| Time  | O(n log n) |
| Space | O(n)       |

---

# 💻 Full Java Implementation

```java
import java.util.*;

class LazyTag {
    int toAdd;

    LazyTag() {
        this.toAdd = 0;
    }

    LazyTag add(LazyTag other) {
        this.toAdd += other.toAdd;
        return this;
    }

    boolean hasTag() {
        return this.toAdd != 0;
    }

    void clear() {
        this.toAdd = 0;
    }
}

class SegmentTreeNode {
    int minValue;
    int maxValue;
    LazyTag lazyTag;

    SegmentTreeNode() {
        this.minValue = 0;
        this.maxValue = 0;
        this.lazyTag = new LazyTag();
    }
}

class SegmentTree {

    private int n;
    private SegmentTreeNode[] tree;

    SegmentTree(int[] data) {
        this.n = data.length;
        this.tree = new SegmentTreeNode[this.n * 4 + 1];

        for (int i = 0; i < tree.length; i++)
            tree[i] = new SegmentTreeNode();

        build(data, 1, this.n, 1);
    }

    void add(int l, int r, int val) {
        LazyTag tag = new LazyTag();
        tag.toAdd = val;
        update(l, r, tag, 1, this.n, 1);
    }

    int findLast(int start, int val) {
        if (start > this.n) return -1;
        return find(start, this.n, val, 1, this.n, 1);
    }

    private void applyTag(int i, LazyTag tag) {
        tree[i].minValue += tag.toAdd;
        tree[i].maxValue += tag.toAdd;
        tree[i].lazyTag.add(tag);
    }

    private void pushdown(int i) {
        if (tree[i].lazyTag.hasTag()) {
            LazyTag tag = new LazyTag();
            tag.toAdd = tree[i].lazyTag.toAdd;

            applyTag(i << 1, tag);
            applyTag((i << 1) | 1, tag);

            tree[i].lazyTag.clear();
        }
    }

    private void pushup(int i) {
        tree[i].minValue = Math.min(
            tree[i << 1].minValue,
            tree[(i << 1) | 1].minValue
        );

        tree[i].maxValue = Math.max(
            tree[i << 1].maxValue,
            tree[(i << 1) | 1].maxValue
        );
    }

    private void build(int[] data, int l, int r, int i) {
        if (l == r) {
            tree[i].minValue = tree[i].maxValue = data[l - 1];
            return;
        }

        int mid = l + ((r - l) >> 1);
        build(data, l, mid, i << 1);
        build(data, mid + 1, r, (i << 1) | 1);
        pushup(i);
    }

    private void update(int targetL, int targetR,
                        LazyTag tag, int l, int r, int i) {

        if (targetL <= l && r <= targetR) {
            applyTag(i, tag);
            return;
        }

        pushdown(i);

        int mid = l + ((r - l) >> 1);

        if (targetL <= mid)
            update(targetL, targetR, tag, l, mid, i << 1);

        if (targetR > mid)
            update(targetL, targetR, tag,
                   mid + 1, r, (i << 1) | 1);

        pushup(i);
    }

    private int find(int targetL, int targetR,
                     int val, int l, int r, int i) {

        if (tree[i].minValue > val || tree[i].maxValue < val)
            return -1;

        if (l == r)
            return l;

        pushdown(i);

        int mid = l + ((r - l) >> 1);

        if (targetR >= mid + 1) {
            int res = find(targetL, targetR, val,
                           mid + 1, r, (i << 1) | 1);
            if (res != -1) return res;
        }

        if (mid >= targetL)
            return find(targetL, targetR, val,
                        l, mid, i << 1);

        return -1;
    }
}

class Solution {

    public int longestBalanced(int[] nums) {

        Map<Integer, Queue<Integer>> occurrences = new HashMap<>();

        int n = nums.length;
        int[] prefix = new int[n];

        prefix[0] = sgn(nums[0]);
        occurrences
            .computeIfAbsent(nums[0], k -> new LinkedList<>())
            .add(1);

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1];

            Queue<Integer> occ =
                occurrences.computeIfAbsent(nums[i],
                                            k -> new LinkedList<>());

            if (occ.isEmpty())
                prefix[i] += sgn(nums[i]);

            occ.add(i + 1);
        }

        SegmentTree seg = new SegmentTree(prefix);

        int len = 0;

        for (int i = 0; i < n; i++) {

            int pos = seg.findLast(i + len, 0);
            if (pos != -1)
                len = Math.max(len, pos - i);

            occurrences.get(nums[i]).poll();

            int nextPos = n + 1;

            if (!occurrences.get(nums[i]).isEmpty())
                nextPos = occurrences.get(nums[i]).peek();

            seg.add(i + 1, nextPos - 1, -sgn(nums[i]));
        }

        return len;
    }

    private int sgn(int x) {
        return (x % 2 == 0) ? 1 : -1;
    }
}
```

---

# 🧩 Patterns Used

* Prefix transformation
* Distinct tracking
* Range update segment tree
* Lazy propagation
* Advanced subarray equality trick
