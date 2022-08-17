// josephus problem

#include <bits/stdc++.h>
using namespace std;

#define MAX_N 100000

int id[MAX_N], alive[MAX_N];

int dp(int n, int k) {
    if (n == 1) return 1;
    return (dp(n-1, k) + k - 1) % n + 1;
}

void naive(int n, int k) {
    int id = 1, cnt = 0;
    int cur_n = n;
    int more = 0;
    while (true) {
        while (alive[id] == 0) ++id;
        if (id > n) id = 1;
        if (alive[id] == 1) {
            ++cnt;
            if (cnt == k)
                alive[id] = 0, --cur_n, cnt = 0;
        }
        ++id;
        //if (cur_n == 2) more = id;
        if (cur_n == 2) break;
    }
    for (int i = 1; i <= n; ++i)
        if (alive[i] == 1) {
            cout << i << " ";
        //return i;
        }
}

int main() {
    int n, k;
    cout << "Input N, K sequentially below: \n";
    cin >> n >> k;
    for (int i = 0; i <= n; ++i) id[i] = i, alive[i] = 1;
    cout << "\n";
    cout << "Naive approach: "; naive(n, k); cout << "\n";
    cout << "DP formula: " << dp(n, k);
}
