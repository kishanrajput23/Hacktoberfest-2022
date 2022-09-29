# Problems where its Difficult to figure out if Binary Search can be applied.

-There are patterns of problems where its little difficult to figure out if binary search can be applied.
-There would be a given array of length (n) and we need to find minimum which satifies contraint on array.
-Runtime of these are normally nLog(m).

![link](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/)
![link](https://leetcode.com/problems/sum-of-mutated-array-closest-to-target/)
![link](https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/)
![link](https://leetcode.com/problems/koko-eating-bananas/)
![link](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/)

class Solution {
	bool isValid(vector<int>& bloomDay, int m, int k, int mid) {

		int count = 0, size = 0;
		for (int i = 0; i < bloomDay.size(); i++) {
			size = (bloomDay[i] <= mid) ? size+1 : 0;
			if (size == k) size = 0, count++;
			if (count == m)
				return true;
		}

		return false;
	}
public:
	int minDays(vector<int>& bloomDay, int m, int k) {
		if(bloomDay.size() == 0 || m == 0 || k == 0) return 0;
		if (m * k > bloomDay.size()) return -1;

		int l = INT_MAX, r = INT_MIN;
		for (int i = 0; i < bloomDay.size(); i++) {
			l = min(l, bloomDay[i]);
			r = max(r, bloomDay[i]);
		}

		while (l <= r) {
			int mid = l + (r-l)/2;

			if (isValid(bloomDay, m, k, mid))
				r = mid-1;
			else
				l = mid+1;
		}

		return l;
	}
};







### Tricky Binary Search






-There are multiple conditions we need to figure out if we need to select left or if we need to select right.

![link](https://leetcode.com/problems/find-peak-element/)
![link](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)
![link](https://leetcode.com/problems/search-in-rotated-sorted-array/)
![link](https://leetcode.com/problems/missing-element-in-sorted-array/)

class Solution {

	int findPeakElementUtil(vector<int>& nums, int l, int r) {

		if (l > r) return -1;

		int m = l + (r-l)/2;

		if (((m > 0) && (nums[m] > nums[m-1])) &&
			((m < nums.size()-1) && (nums[m] > nums[m+1])))
			return m;

		if (m == 0 && nums.size() > 1 && nums[m] > nums[m+1])
			return m;

		if ((m == nums.size()-1) && (nums[m] > nums[m-1]))
			return m;

		int left = findPeakElementUtil(nums, l, m-1);
		int right = findPeakElementUtil(nums, m+1, r);

		if (left != -1)
			return left;
		else
			return right;
	}
public:
	int findPeakElement(vector<int>& nums) {

		int n = nums.size();

		if (n == 1) return 0;

		return findPeakElementUtil(nums, 0, n-1);
	}
};





### Simple Binary Search






![link](https://leetcode.com/problems/find-smallest-letter-greater-than-target/)
![link](https://leetcode.com/problems/missing-element-in-sorted-array/)
![link](https://leetcode.com/problems/peak-index-in-a-mountain-array/)
![link](https://leetcode.com/problems/h-index-ii/)
![link](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)
![link](https://leetcode.com/problems/first-bad-version/)

class Solution {
public:
	char nextGreatestLetter(vector<char>& letters, char target) {

		int n = letters.size();
		int l = 0, r = n-1;

		if (target >= letters[n-1] || target < letters[0])
			return letters[0];

		int m = 0;
		while (l <= r) {
			m = l + (r-l)/2;

			if (m > 0 && (target >= letters[m-1] && target < letters[m]))
					return letters[m];
			else if (target >= letters[m])
					l = m+1;
			else	
					r = m-1;
		}

		return letters[m];
	}
};







### Using C++ STL upper bound for binary search






![link](https://leetcode.com/problems/time-based-key-value-store/)
![link](https://leetcode.com/problems/online-election/)
![link](https://leetcode.com/problems/random-pick-with-weight/)
![link](https://leetcode.com/problems/find-right-interval/)

For Java script, built in api is not available. Sharing @claytonjwong approach. Thanks to him.
![link](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/discuss/692931/Javascript-and-C%2B%2B-solutions)
![link](https://gist.github.com/claytonjwong/53bd1c1489b12eccad176addb8afd8e0)

class TimeMap {
	unordered_map<string, map<int, string>>hm;
public:
	/** Initialize your data structure here. */
	TimeMap() {

	}

	void set(string key, string value, int timestamp) {
		hm[key].insert({timestamp, value});
	}

	string get(string key, int timestamp) {
		auto it = hm[key].upper_bound(timestamp);
		return (it == hm[key].begin()) ? "" : prev(it)->second;
	}
};







### Binary search based on condition on 2 arrays
![link](https://leetcode.com/problems/median-of-two-sorted-arrays/)

class Solution {
public:
	double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {

		int n = nums1.size();
		int m = nums2.size();

		if (n > m)
			return findMedianSortedArrays(nums2, nums1);

		int k = (n+m-1)/2;

		int l = 0;
		int r = min(k, n);

		while (l < r) {
			int mid1 = l + (r-l)/2;
			int mid2 = k-mid1;

			if (nums1[mid1] > nums2[mid2])
				r = mid1;
			else
				l = mid1+1;
		}

		/* if (n+m) is odd, the median is the larger one between nums1[l-1] and nums2[k-l] */
		int a = max(l >= 1 ? nums1[l-1] : INT_MIN, k >= l ? nums2[k-l] : INT_MIN);
		if ((n+m) % 2 != 0)
			return a;

		/* in case (n+m) is even, take the average of mid 2 elements */
		int b =  min(l < n ? nums1[l] : INT_MAX, k-l+1 < m ? nums2[k-l+1] : INT_MAX);

		return (a+b)/2.0;
	}
};