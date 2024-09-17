class Solution(object):
    def minBitFlips(self, start, goal):
        """
        :type start: int
        :type goal: int
        :rtype: int
        """
        result = 0
        while start or goal:
            if (start & 1) != (goal & 1):
                result += 1
            start = start // 2
            goal = goal // 2

        return result