class Solution(object):
    def findTheLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        vowels = 'aeiuo'
        res = 0
        mask = 0
        mask_to_idx = {0: -1}

        for i, c in enumerate(s):
            if c in vowels:
                mask = mask ^ (1 + ord(c) - ord('a')) 
            if mask in mask_to_idx:
                length = i - mask_to_idx[mask]
                res = max(res, length)
            else:
                mask_to_idx[mask] = i
        return res
