#include <bits/stdc++.h>
using namespace std;

int n;
void print(vector<int> vect){
    cout << n <<" = ";
    for(int i=0;i<vect.size(); ++i){
       if(i>0)
           cout <<"+" <<vect[i];
       else cout << vect[i];
    }
    cout << endl;
}

void gen(int n, vector<int> vect){
   if(!n)
      print(vect);
   else{
      for(int i=1;i<=2;++i){
          if(n-i>=0){
              std::vector<int> vect2(vect);
              vect2.push_back(i);
              gen(n-i,vect2);
          }
      }
   }
}

int main(){
   scanf("%d",&n);
   vector<int> vect;
   gen(n,vect);
}
