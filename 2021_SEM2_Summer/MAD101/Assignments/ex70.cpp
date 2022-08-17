#include <bits/stdc++.h>
using namespace std;

double tinh(double a, int n) {
    if (n == 1) return a;
    else return a + tinh(a, n-1);
}
int main() {
    int n; double a;
    cin >> a >> n;
    cout << tinh(a, n);
}
