import matplotlib.pyplot as plt
import numpy as np

ypoints = np.array([3, 8, 1, 10])

plt.plot(ypoints, marker = 'o')
plt.show()

plt.plot(ypoints, marker = 's') # mark with a star
plt.show()

# 'o'	Circle	
# '*'	Star	
# '.'	Point	
# ','	Pixel	
# 'x'	X	
# 'X'	X (filled)	
# '+'	Plus	
# 'P'	Plus (filled)	
# 's'	Square	
# 'D'	Diamond	
# 'd'	Diamond (thin)	
# 'p'	Pentagon	
# 'H'	Hexagon	
# 'h'	Hexagon	
# 'v'	Triangle Down	
# '^'	Triangle Up	
# '<'	Triangle Left	
# '>'	Triangle Right	
# '1'	Tri Down	
# '2'	Tri Up	
# '3'	Tri Left	
# '4'	Tri Right	
# '|'	Vline	
# '_'	Hline

# shortcut string notation: fmt -> marker|line|color
plt.plot(ypoints, 'o-.r')
plt.show()
# Line reference 
# '-'   Solid line	
# ':'	Dotted line	
# '--'	Dashed line	
# '-.'	Dashed/dotted line

# Color reference 
# 'r'	Red	
# 'g'	Green	
# 'b'	Blue	
# 'c'	Cyan	
# 'm'	Magenta	
# 'y'	Yellow	
# 'k'	Black	
# 'w'	White

# Marker size
# markersize or the shorter version, ms
plt.plot(ypoints, marker = 'o', ms = 8)
plt.show()

# markeredgecolor or the shorter mec
# markerfacecolor or the shorter mfc
plt.plot(ypoints, marker = 'o', ms = 20, mec = 'g', mfc = 'r')
plt.show()

plt.plot(ypoints, marker = 'o', ms = 20, mec = '#4CAF50', mfc = '#4CAF50')
plt.show()
