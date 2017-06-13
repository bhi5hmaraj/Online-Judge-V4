#include <iostream>
#include <vector>
#include <stack>
#include <bitset>
#include <fstream>
#include <string>
#include <cstdio>
#include <list>
#include <math.h>
#include <algorithm>
#include <unordered_set>
#include <bits/stdc++.h>
#include <limits>
 
using std::string;
using namespace std;
 
typedef unsigned int ui;
typedef unsigned long long llu;
typedef unsigned long lu;
typedef long long ll;
 
typedef vector <ll> vi;
typedef pair<ui,ui> ii;
typedef vector <ii> vii;
 
#define N 100010
#define MOD 1000000007
 
//--------------------------------------------
 
bool asec(ll x,ll y){
	return x<y;
}
 
vi b[N];
ll a[N];
 
 
ll binary_min(ll i,ll x){
	long long left=0,right=b[a[i]].size()-1,mid;
	while(left<=right){
		mid=(left+right)/2;
		if(b[a[i]][mid]==x)
			return mid;
		if(b[a[i]][mid]>x)
			right=mid-1;
		else
			left=mid+1;
	}
	return left;
}
 
ll binary_max(ll i,ll x){
	long long left=0,right=b[a[i]].size()-1,mid;
	while(left<=right){
		mid=(left+right)/2;
		if(b[a[i]][mid]==x)
			return mid;
		if(b[a[i]][mid]>x)
			right=mid-1;
		else
			left=mid+1;
	}
	return right;
}
 
 
int main(){
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
 
	ll t,ans;
	ll n,q,i,j;
 
	ll l,r,x,y,s,ss;
	ll al,ar,ax,ay;
	ll ii,jj,oo,pp;
 
	bitset<N> bit;
 
	cin>>t;
	while(t--){
		cin>>n>>q;
 
        for(i=0;i<N;i++)
        	b[i].clear();
 
 
		for(i=0;i<n;i++){
			cin>>a[i];
			b[a[i]].push_back(i);
		}
 
 
 
		while(q--){
			cin>>l>>r>>x>>y;
 
			if(x<l) {swap(x,l);swap(r,y);}
 
			if(l==x) {cout<<"YES"<<"\n";continue;}
 
			else{
			al=--l,ar=--r,ax=--x,ay=--y;
 
			if(x<r+1) {
				swap(x,r);
				r--;x++;
			}
 
			ans=0;
			bit.set();
 
			//Binary_min ----> n All no smaller
			//Binary_max ----> -1 All no are greater
 
			ss=r+1;
 
			for(i=l;i<=r && ans<2;i++){
				if(bit[a[i]]){
					ii=binary_min(i,l);
					jj=binary_max(i,r);
					oo=binary_min(i,x);
					pp=binary_max(i,y);
					bit[a[i]]=false;
					//cout<<a[i]<<" "<<ii<<" "<<jj<<" "<<oo<<" "<<pp<<endl;
					if(ii==b[a[i]].size()) ii--;
					if(oo>pp){
						ans+=jj-ii+1;
						s=i;
					}
 
					else{
						if(jj-ii>pp-oo){
							s=i;
							ans+=jj-ii-pp+oo;
						}
						else if(pp-oo>jj-ii)
							ss=i;
					}
					//cout<<"ans="<<ans<<endl;
				}
			}
 
 
			if(ans==1){
				if(ss==r+1)
					for(i=x;i<=y;i++){
						if(bit[a[i]]){
							ss=i;break;
						}}
 
						ii=0;
 
						for(i=al;i<=ar;i++){
							if(a[i]>min(a[s],a[ss]) && a[i]<max(a[s],a[ss]))
								ii++;
						}
 
						for(i=ax;i<=ay;i++){
							if(a[i]>min(a[s],a[ss]) && a[i]<max(a[s],a[ss]))
								ii++;
						}
 
						
						if(ii!=0) ans++;
					}
 
					if(ans<2) cout<<"YES"<<"\n";
					else cout<<"NO"<<"\n";
				}
				
				//cout<<"At query" + q<<"\n";
	     }
	}
   return 0;
} 
