
import networkx as nx
import matplotlib.pyplot as plt

G = nx.Graph()
E = [('A', 'B', 2), ('A', 'C', 1), ('B', 'D', 5), ('B', 'E', 3), ('C', 'E', 2)]
G.add_weighted_edges_from(E)

print(G.degree())
print([val for (node, val) in G.degree()])
print([val for (node, val) in sorted(G.degree(), key=lambda pair: pair[0])])

pos = nx.spring_layout(G)
nx.draw_networkx_labels(G, pos)
nx.draw_networkx_edges(G, pos)
nx.draw_networkx_edges(G, pos)
plt.show()
