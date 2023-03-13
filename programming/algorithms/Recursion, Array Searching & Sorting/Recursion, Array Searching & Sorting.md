# Recursion, Array Searching & Sorting

Demonstrations and visualizations: [VisualAlgo](https://visualgo.net/en)

# Recursion

### Factorial number

Equation:

$$
n! = \begin{cases}
0 & n = 1,\\
n(n - 1)! & \text{otherwise}
\end{cases}
$$

Implementation:

```python
def factorial(n: int) -> int:
	# base case
	if n == 0:
		return 1

	# recursive case
	return n * factorial(n - 1)
```

Recursion tree of `factorial(7)`:

![Factorial Recursion Tree](Factorial%20Recursion%20Tree.png)

### Fibonacci number

Equation:

$$
fib(n) = \begin{cases}
0 & n = 0,\\
1 & n = 1,\\
fib(n-1) + fib(n-2) & \text{otherwise}
\end{cases}
$$

Implementation:

```python
def fib(n: int) -> int:
	# base cases
	if n == 0:
		return 0
	elif n == 1:
		return 1

	# recursive case
	return fib(n - 1) + fib(n - 2)
```

Expanding $fib(n)$ for $n > 1$:

$$
\begin{aligned}
fib(n) &= fib(n - 1) + fib(n-2)\\
&= (fib(n - 2) + fib(n - 3)) + (fib(n - 3) + fib(n - 4))\\
&=...
\end{aligned}
$$

Expanding $fib(7)$:

$$
\begin{aligned}
fib(7) &= fib(6) + fib(5)\\
&= (fib(5) + fib(4)) + (fib(4) + fib(3))\\
&=...\\
&= fib(1) + fib(1) + fib(1) + ...\\
&= 13
\end{aligned}
$$

Recursion tree of $fib(7)$:

![Fibonacci Recursion Tree](Fibonacci%20Recursion%20Tree.png)

### For range loop implementation

Python for range loop:

```python
for i in range(n):
	# do things with i
```

Python while loop:

```python
i = 0
while i < n:
	# do things with i
	i += 1
```

Equation:

$$
f(i, n) = \begin{cases}
f(i + 1, n) & i < n,\\
None & \text{otherwise}
\end{cases}
$$

Recursive implementation:

```python
# for i in range(n)
def for_range(i: int, n: int) -> None:
	# base case
	if i >= n:
		return

	# recursive case
	# do things with i
	return for_range(i + 1, n)

# for i in range(10)
for_range(0, 10)
```

Recursion tree of `for_range(0, 10)`:

![For Range Recursion Tree](For%20Range%20Recursion%20Tree.png)

# Optimization

### Tabulation

A bottom-up approach for optimizing recursion.

Values are calculated starting from the base cases.

Iterates until reaching the wanted value.

Fibonacci number implementation using tabulation:

```python
def fib(n):
	# base cases
	a = 0
	b = 1
	
	# iteration
	for _ in range(n):
	    result = a + b
	    a = b
	    b = result
	
	return a
```

### Memoization

A top-down approach for optimizing recursion.

When calculating the values for the first time, memoize (remember) them in a table.

Value from the table is used when computing the same value later.

Time for computing the value again is saved.

Fibonacci number implementation using memoization:

```python
fib_memo = { 0: 0, 1: 1 } # base cases

def fib(n: int):
	# find in fib_memo
	if n in fib_memo:
		return fib_memo[n]

	# otherwise calculate
	# print(f'calculating fib({n})')
	fib_memo[n] = fib(n - 1) + fib(n - 2)
	return fib_memo[n]

print(fib(7))
```

# Array searching & sorting

## Searching

### Linear search

Concept: iterate through array one by one.

$T(n) = O(n)$

### Binary search

Concept:

- Given a sorted array $A$ and an index $i$, all elements before $A_i$ are smaller than $A_i$, after $A_i$ are greater.
- Divide the array into 2 halves evenly with the middle element $A_m$
- If target element is not $A_m$ and is smaller than $A_m$ then it is in the first half, else it is in the second half.

$T(n) = O(\log n)$

## Sorting

### Bubble sort

Concept:

- Iterate through array comparing each adjacent elements, if first < second, then swap.
- After each complete iteration, last element is always the largest ⇒ problem size decrease.

$T(n) = O(n^2)$

### Insertion sort

Concept:

- Divide the array into 2 halves, initially the first half has 0 size.
- Move the first element in the second half to the first half, move towards front until it is in the correct position.

$T(n) = O(n^2)$

### Selection sort

Concept:

- Divide the array into 2 halves, initially the first half has 0 size.
- Find the smallest element in the second half, swap it with the first element of the second half.
- The first element of the second half will become the last element of the first half.

$T(n) = O(n^2)$

### Merge sort

Concept:

- Divide the array into 2 halves evenly.
- Recursively sort the 2 halves with merge sort.
- Combine the sorted 2 halves into 1 sorted array.

$T(n) = O(n \log n)$

### Quick sort

Concept:

- Partition: divide the array into 2 halves with a chosen pivot, all elements in first half are smaller than the pivot, all elements in the second half are greater than the pivot.
- Partition can be done multiple ways, e.g., LL pointers, LR pointers.
- Place the pivot in between the 2 halves if needed.
- Recursively partition the 2 halves with quick sort.

$T(n) = O(n \log n)$

### Counting sort

Concept:

- Can only work on array of numbers.
- Using the values as the index for a counting array.
- Iterate through the counting array and place the counted indices back into the original array.

$T(n) = O(n)$

### Radix sort

Concept:

- Can only work on array of numbers.
- Iterate through each digit of the number, either from smallest to greatest, or the other way around.
- Sort the numbers based on their digits.

$T(n, d) = O(nd)$

$d$ is the number of digits of the numbers.