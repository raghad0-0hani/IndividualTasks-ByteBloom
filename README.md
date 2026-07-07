# TrackerGauntlet

## Overview
This repository contains my Kotlin solutions for the **TrackerGauntlet** challenge. 
The project focuses on practicing fundamental programming concepts, algorithmic thinking, and Clean Code principles->
by solving five independent problems without relying on unnecessary built-in functions.

---

## Problems Implemented

### 1. Priority Dispatcher
**Objective:**
Simulate a package priority system for numbers from **1 to 50**.

**Solution Steps:**
- Iterate through package indices using a `for` loop.
- Check if the number is divisible by both **3 and 5**.
- Print **PriorityExpress** if both conditions are true.
- Print **Express** if divisible by **3** only.
- Print **Freight** if divisible by **5** only.
- Otherwise, print the package index.

**Concepts Used**
- Loops
- Conditional statements
- Modulus operator (`%`)

---

### 2. Waypoints Reverser
**Objective:**
Reverse the route:

```
HubA → HubB → HubC
```

into

```
HubC → HubB → HubA
```

without using built-in reverse functions.

**Solution Steps**
- Store the route as a single string.
- Split the string into individual waypoints.
- Iterate from the last waypoint to the first using `downTo`.
- Print each waypoint in reverse order.
- Print the separator only between waypoints.

**Concepts Used**
- String manipulation
- `split()`
- Reverse traversal
- Array/List indexing

---

### 3. Maximum Weight Filter
**Objective**
Find the highest valid package weight while ignoring invalid values (`-1.0`).

**Solution Steps**
- Store all package weights in a `DoubleArray`.
- Initialize the highest weight with `Double.NEGATIVE_INFINITY`.
- Traverse the array.
- Ignore invalid values (`-1.0`).
- Update the highest value whenever a larger valid weight is found.
- Print the final result.

**Concepts Used**
- Arrays
- Looping
- Conditional filtering
- Maximum value search

---

### 4. Palindrome Transit Code
**Objective**
Determine whether a transit code is a palindrome regardless of letter case.

**Solution Steps**
- Convert the input to uppercase.
- Use two pointers:
  - One starts from the beginning.
  - One starts from the end.
- Compare both characters.
- Stop immediately if they don't match.
- If all characters match, the code is a palindrome.

**Concepts Used**
- Two-pointer technique
- String indexing
- While loop
- Case-insensitive comparison

---

### 5. Binary Search Lookup
**Objective**
Find the index of a tracking ID using a manual Binary Search implementation.

**Solution Steps**
- Define the left and right search boundaries.
- Calculate the middle index.
- Compare the middle value with the target.
- Search only the appropriate half.
- Repeat until the value is found or the search space becomes empty.
- Print every comparison iteration.
- Return the index if found, otherwise return `-1`.

**Time Complexity**

```
O(log n)
```

Binary Search repeatedly divides the search space in half, making it significantly faster than Linear Search for sorted arrays.

**Concepts Used**
- Binary Search
- While loop
- Search boundaries
- Big O notation
- Algorithm tracing

---

## Skills Practiced

- Kotlin syntax
- Loops and conditions
- Arrays
- String manipulation
- Clean Code naming
- Algorithmic thinking
- Binary Search
- Time Complexity (Big O)

---

## Project Structure

```
TrackerGauntlet.kt
README.md
```

---

## Author

**Raghad Hani Abu Hasanein**
