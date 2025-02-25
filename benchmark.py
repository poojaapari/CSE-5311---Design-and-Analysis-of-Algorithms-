import time
import random
import numpy as np
import matplotlib.pyplot as plt

def partition(arr, low, high):
    pivot = arr[high]
    i = low - 1
    for j in range(low, high):
        if arr[j] <= pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]
    arr[i + 1], arr[high] = arr[high], arr[i + 1]
    return i + 1

def quicksort_iterative(arr):
    stack = [(0, len(arr) - 1)]

    while stack:
        low, high = stack.pop()
        if low < high:
            pivot_index = partition(arr, low, high)
            stack.append((low, pivot_index - 1))
            stack.append((pivot_index + 1, high))

def generate_best_case(n):
    return list(range(n)) 

def generate_worst_case(n):
    return list(range(n, 0, -1))  

def generate_average_case(n):
    """Generate an input that represents the average case for Quicksort."""
    return random.sample(range(n * 2), n)  

def benchmark_quicksort_iterative():
    sizes = [100, 200, 400, 800, 1600, 3200, 6400]

    best_times = []
    worst_times = []
    average_times = []

    for size in sizes:
        # Best case
        best_case_input = generate_best_case(size)
        start_time = time.time()
        quicksort_iterative(best_case_input)
        best_times.append(time.time() - start_time)

        # Worst case
        worst_case_input = generate_worst_case(size)
        start_time = time.time()
        quicksort_iterative(worst_case_input)
        worst_times.append(time.time() - start_time)

        # Average case
        average_case_input = generate_average_case(size)
        start_time = time.time()
        quicksort_iterative(average_case_input)
        average_times.append(time.time() - start_time)

    # Plot results
    plt.figure(figsize=(10, 6))
    plt.plot(sizes, best_times, label="Best Case (O(n log n))", marker="o")
    plt.plot(sizes, worst_times, label="Worst Case (O(n^2))", marker="o")
    plt.plot(sizes, average_times, label="Average Case (O(n log n))", marker="o")

    plt.xlabel("Input Size (n)")
    plt.ylabel("Time (seconds)")
    plt.title("Iterative Quicksort Performance for Different Cases")
    plt.legend()
    plt.grid(True)
    plt.show()

if __name__ == "__main__":
    benchmark_quicksort_iterative()
