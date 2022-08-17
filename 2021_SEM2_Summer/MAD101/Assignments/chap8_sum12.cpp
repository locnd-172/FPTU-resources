// sum of 1, 2
#include <bits/stdc++.h>
using namespace std;

string serires;

void print_sum(string str) {
    for (int i = 0; i<str.length(); ++i) {
        cout << str[i];
        if (i != str.length() - 1) cout << "+";
    }
    cout << '\n';
}

void backtrack(int n, string st) {
    if (n == 0) print_sum(st);
    for (int i = 1; i <= 2; ++i) {
        if (n-i >= 0) {
            string tmp = st;
            tmp = tmp + std::to_string(i);
            backtrack(n-i, tmp);
        }

    }
}

int main() {
    int N; cin>>N;
    string st;
    backtrack(N, st);
}
