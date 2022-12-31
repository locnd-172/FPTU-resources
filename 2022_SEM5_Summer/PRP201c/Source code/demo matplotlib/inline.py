# $$
from matplotlib import pyplot as plt
import numpy as np

# $$
x = np.linspace(1, 100, 2)
y = 0.1 * x + 2

plt.title('matplotlib inline in vscode')
plt.plot(x, y, color='green', linestyle=':')
plt.show()