class Solution:
    def canThreePartsEqualSum(self, A: List[int]) -> bool:
        sm = sum(A)
        if sm % 3 != 0:
            return False
        else:
            partial_sm = sm // 3
            
        current_sum = 0
        n_parts = 0
        for item in A:
            current_sum += item
            if current_sum == partial_sm:
                current_sum = 0
                n_parts += 1
        
        return n_parts >= 3
        