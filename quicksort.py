import random

def quicksort(arr):
    if len(arr) <= 1:
        return arr
    pivot = arr[-1]
    left = [x for x in arr[:-1] if x <= pivot]
    right = [x for x in arr[:-1] if x > pivot]
    return quicksort(left) + [pivot] + quicksort(right)

def randomized_quicksort(arr):
    if len(arr) <= 1:
        return arr
    pivot_index = random.randint(0, len(arr) - 1)
    pivot = arr[pivot_index]
    left = [x for i, x in enumerate(arr) if x <= pivot and i != pivot_index]
    right = [x for i, x in enumerate(arr) if x > pivot]
    return randomized_quicksort(left) + [pivot] + randomized_quicksort(right)

user_input = input("Numbers separated by spaces: ")
arr = list(map(int, user_input.split()))

sorted_standard = quicksort(arr)
sorted_random = randomized_quicksort(arr)

print("Sorted using Standard Quicksort:", sorted_standard)
print("Sorted using Randomized Quicksort:", sorted_random)
