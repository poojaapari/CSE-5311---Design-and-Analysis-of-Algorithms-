import matplotlib.pyplot as plt

input_sizes = [5, 10, 20, 100, 1000, 5000, 10000]
insertion_sort_times = [0, 0, 0, 0, 1, 2, 11]  
selection_sort_times = [0, 0, 0, 0, 1, 7, 41]  
bubble_sort_times = [0, 0, 0, 0, 1, 25, 120]

plt.figure(figsize=(10, 6))
plt.plot(input_sizes, insertion_sort_times, marker='o', label='Insertion Sort')
plt.plot(input_sizes, selection_sort_times, marker='s', label='Selection Sort')
plt.plot(input_sizes, bubble_sort_times, marker='^', label='Bubble Sort')
plt.title('Sorting Algorithm Benchmark', fontsize=16)
plt.xlabel('Input Size (n)', fontsize=14)
plt.ylabel('Runtime (ms)', fontsize=14)
plt.grid(True, linestyle='--', alpha=0.7)
plt.legend(fontsize=12)
plt.tight_layout()
plt.show()

