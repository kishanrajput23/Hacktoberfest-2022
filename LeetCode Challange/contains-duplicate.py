class Solution(object):
    def containsDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        found_it = set()

        for num in nums:
            if num in found_it: return True
            found_it.add(num)
        return False