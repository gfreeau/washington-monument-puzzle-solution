Washington monument puzzle solution
-----------------------------------

All cube faces must add up to 555. There is over 262000+ possible permutations for each cube face.

http://www.creativecrafthouse.com/index.php?main_page=product_info&products_id=502

The approach is to find all possible permutations for only a single cube face and then eliminate permutations that do not add up to 555. 
This brings the possible permutations to less than 10000.

From there we check the other faces to see if they add up to 555.

The main challenge is that the same number can appear on the same cube. So a valid permutation can be created from different cube face placement even though they all add up to 555. You can eliminate permutations by checking the other faces to see if they add up to 555.

The easiest solution is place these numbers on the cubes facing you:

```
60
55
55
55
55
55
55
55
55
55
```

Then make sure the bottom 3 cubes are showing 55 on the cube facing right.

There are 9 Possible solutions:

```
[95, 30, 25, 60]
[25, 20, 70, 55]
[20, 50, 55, 10]
[45, 55, 65, 15]
[90, 80, 90, 55]
[90, 85, 55, 60]
[15, 55, 45, 95]
[65, 55, 55, 75]
[55, 70, 35, 55]
[55, 55, 60, 75]


[95, 30, 25, 60]
[25, 20, 70, 55]
[10, 20, 50, 55]
[65, 15, 45, 55]
[90, 80, 90, 55]
[60, 90, 85, 55]
[45, 95, 15, 55]
[55, 75, 65, 55]
[55, 70, 35, 55]
[55, 60, 75, 55]


[95, 30, 25, 60]
[25, 20, 70, 55]
[20, 50, 55, 10]
[65, 15, 45, 55]
[80, 90, 55, 90]
[60, 90, 85, 55]
[45, 95, 15, 55]
[55, 55, 75, 65]
[55, 55, 70, 35]
[55, 55, 60, 75]


[95, 30, 25, 60]
[70, 55, 25, 20]
[10, 20, 50, 55]
[45, 55, 65, 15]
[55, 90, 80, 90]
[55, 60, 90, 85]
[45, 95, 15, 55]
[55, 55, 75, 65]
[70, 35, 55, 55]
[55, 60, 75, 55]


[95, 30, 25, 60]
[25, 20, 70, 55]
[10, 20, 50, 55]
[45, 55, 65, 15]
[90, 80, 90, 55]
[90, 85, 55, 60]
[45, 95, 15, 55]
[65, 55, 55, 75]
[35, 55, 55, 70]
[55, 60, 75, 55]


[95, 30, 25, 60]
[25, 20, 70, 55]
[10, 20, 50, 55]
[55, 65, 15, 45]
[55, 90, 80, 90]
[90, 85, 55, 60]
[55, 45, 95, 15]
[55, 55, 75, 65]
[55, 70, 35, 55]
[60, 75, 55, 55]


[95, 30, 25, 60]
[20, 70, 55, 25]
[55, 10, 20, 50]
[15, 45, 55, 65]
[90, 80, 90, 55]
[60, 90, 85, 55]
[15, 55, 45, 95]
[75, 65, 55, 55]
[55, 55, 70, 35]
[75, 55, 55, 60]


[95, 30, 25, 60]
[25, 20, 70, 55]
[10, 20, 50, 55]
[45, 55, 65, 15]
[55, 90, 80, 90]
[60, 90, 85, 55]
[45, 95, 15, 55]
[75, 65, 55, 55]
[70, 35, 55, 55]
[75, 55, 55, 60]


[95, 30, 25, 60]
[20, 70, 55, 25]
[55, 10, 20, 50]
[55, 65, 15, 45]
[55, 90, 80, 90]
[55, 60, 90, 85]
[55, 45, 95, 15]
[55, 75, 65, 55]
[35, 55, 55, 70]
[75, 55, 55, 60]


Found 9 solutions
```
