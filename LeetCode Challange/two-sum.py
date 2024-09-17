class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        found = {}
        for i, num in enumerate(nums):
            if target - num in found:
                return [found[target - num],i]
            elif num not in found:
                found[num] = i
