import time
import numpy as np
import matplotlib.pyplot as plt

def execution_timer(input_size):
    counter = 1
    for outer_loop in range(input_size):
        for inner_loop in range(input_size):
            counter += 1  
            
input_sizes = np.arange(5, 1000, 20)
execution_times = []

for size in input_sizes:
    start_clock = time.time()
    execution_timer(size)
    end_clock = time.time()
    execution_times.append(end_clock - start_clock)
    
execution_times = np.array(execution_times)

poly_coeffs = np.polyfit(input_sizes, execution_times, 2)  
poly_function = np.poly1d(poly_coeffs)  

residual_values = np.abs(execution_times - poly_function(input_sizes))
max_residual_index = np.argmax(residual_values)  
estimated_n0 = input_sizes[max_residual_index]  

plt.figure(figsize=(8, 5))
plt.scatter(input_sizes, execution_times, label="Measured Time", color="red", s=20)  
plt.plot(input_sizes, poly_function(input_sizes), label=f"Polynomial Fit: {poly_coeffs[0]:.3e}n² + {poly_coeffs[1]:.3e}n + {poly_coeffs[2]:.3e}", color="blue")         
plt.axvline(x=estimated_n0, color='green', linestyle='--', label=f"n_0 ≈ {estimated_n0}")
plt.text(estimated_n0, execution_times[max_residual_index], f' Location of N_0\n({estimated_n0}, {execution_times[max_residual_index]:.3f})', fontsize=10)
plt.xlabel("Input Size (n)")
plt.ylabel("Execution Time (s)")
plt.title("Execution Time of Function with Identified n_0")
plt.legend()
plt.grid()
plt.show()
