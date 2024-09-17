class Solution(object):
    def containsNearbyDuplicate(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: bool
        """
        win = set()
        l = 0

        for r in range(len(nums)):
            if r - l > k:
                win.remove(nums[l])
                l += 1
            if nums[r] in win:
                return True
            win.add(nums[r])