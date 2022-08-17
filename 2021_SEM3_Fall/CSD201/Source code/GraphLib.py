#B1: Soạn file inputGraph.txt với nội dung như trên và tải lên Google drive của bạn


"""
#Nội dung file inputGraph.txt

0 1 4
0 7 8
1 2 8
1 7 11
2 3 7
2 5 4
2 8 2
3 4 9
3 5 14
4 5 10
5 6 2
6 7 1
6 8 6
7 8 7
"""

#Bước 2 chạy 2 dòng lệnh sau
# from google.colab import drive
# drive.mount('/content/gdrive')

#Nếu cần cài thư viện nào bạn gõ
#!pip install tên thư viện

import networkx as nx
import matplotlib.pyplot as plt
import networkx.algorithms.tree.mst as mst
G = nx.Graph()
E = []
for line in open('F:\CSD\inputGraph.txt'):
  if line is not None:
    data = line.split()
    E.append((int(data[0]), int(data[1]), int(data[2])))

G.add_weighted_edges_from(E)
nx.draw(G, with_labels=True, node_color='r', edge_color='b')
print(nx.dijkstra_path(G, 0, 4))
print("Deg:")
for x in G.nodes:
  print(x, " - ", nx.degree(G, x))
cost = 0
A = mst.kruskal_mst_edges(G, True, "weight")
for x in A:
  print(x)
  cost += x[2]['weight']
print("Kruskal cost: ", cost)
cost = 0
B = mst.prim_mst_edges(G, False)
for x in B:
  print(x)
  cost += x[2]['weight']
print("Prim cost: ", cost)
plt.show()
print("done")