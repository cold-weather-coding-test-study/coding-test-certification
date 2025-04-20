
import sys
input = sys.stdin.readline

nums = {}
nums[0] = [1, 1, 1, 0, 1, 1, 1]
nums[1] = [0, 0, 1, 0, 0, 1, 0]
nums[2] = [1, 0, 1, 1, 1, 0, 1]
nums[3] = [1, 0, 1, 1, 0, 1, 1]
nums[4] = [0, 1, 1, 1, 0, 1, 0]
nums[5] = [1, 1, 0, 1, 0, 1, 1]
nums[6] = [1, 1, 0, 1, 1, 1, 1]
nums[7] = [1, 0, 1, 0, 0, 1, 0]
nums[8] = [1, 1, 1, 1, 1, 1, 1]
nums[9] = [1, 1, 1, 1, 0, 1, 1]

n, k, p, x = list(map(int, input().rstrip().split()))
x = str(x).zfill(k)
res = 0

def find_change(p, n):
    p, n = int(p), int(n)
    return sum(x != y for x, y in zip(nums[p], nums[n]))

for tn in range(1, n + 1):
    num = str(tn).zfill(k)
    temp = 0
    for i in range(k):
        temp += find_change(x[i], num[i])
    if (num != x) and (temp <= p):
        res += 1

print(res)
