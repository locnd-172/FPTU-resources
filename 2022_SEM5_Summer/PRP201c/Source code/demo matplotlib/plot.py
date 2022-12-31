import matplotlib.pyplot as plt
import numpy as np

xpoints = np.array([1, 8])
ypoints = np.array([3, 10])

plt.plot(xpoints, ypoints)
plt.show()

# Plotting Without Line
plt.plot(xpoints, ypoints, 'o')
plt.show()

# Multiple points
xpoints = np.array([1, 2, 6, 8])
ypoints = np.array([3, 8, 1, 10])

plt.plot(xpoints, ypoints)
plt.show()

# Default X-Points
# If we do not specify the points in the x-axis, they will get the default values 0, 1, 2, 3,
ypoints = np.array([3, 8, 1, 10, 5, 7])
plt.plot(ypoints)
plt.show()