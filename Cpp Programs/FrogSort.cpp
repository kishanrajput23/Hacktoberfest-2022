#include <bits/stdc++.h>

using namespace std;

int main() {

	int t; cin >> t;

	while (t--) {

		int n; cin >> n;

		int weights[n];

		for (int i = 0; i < n; i++)
			cin >> weights[i];

		int jumps[n];

		for (int i = 0; i < n; i++)
			cin >> jumps[i];

		map<int, pair<int, int>>data;
		for (int i = 0; i < n; i++) {

			data[weights[i]] = {jumps[i], i};
		}


		int answer = 0, current ;


		for (auto iter = data.begin(); iter != data.end(); iter++) {

			if (iter == data.begin()) {

				current = (iter->second).second;
			}
			else {

				int idx = (iter->second).second;

				while (current >= idx) {

					answer++;
					idx += ((iter->second).first);
				}
				current = idx;
			}
		}
		cout << answer << endl;

	}

	return 0;
}











/*
ans = 0;


int answer(int a[], i, n, num)
{
	if (i == n - 1)
	{
		if (a[i] + ans == num)
			ans = ans + a[i];
		else
			ans =  ans - a[i];
	}
	else
	{
		if (a[i] + ans == num)
			ans =  a[i] + answer(a, i + 1, n, num);
		else
			ans =  -a[i] + answer(a, i + 1, n, num);
	}
	return ans
}

*/




