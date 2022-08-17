// chess board

#include <bits/stdc++.h>
using namespace std;

const int maxN = 1001;
int N, col[maxN];

bool check(int x2,int y2){
    for(int i = 1; i < x2 ;i++)
        if(col[i] == y2 || abs(i-x2) == abs(col[i] - y2) ) return false;
    return true;
}

void Output(){
    for(int i = 1; i <= N; ++i) cout << col[i] << " ";
    cout << "\n";
}

void DFS(int i){
    for(int j = 1; j <= N; ++j) {
        if (check(i, j)) {
            col[i] = j;
            if (i==N) Output();
            DFS(i+1);
        }
    }
}

int main(){
    cin >> N;
    DFS(1);
}
