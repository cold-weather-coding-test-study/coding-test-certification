import sys
sys.stdin = open("input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

a, b = input().split(' ')
cnt = 0

def backt(num_len, nums):
    global cnt
    if len(nums) == num_len:
        if int(nums) < int(a) or int(nums) > int(b):
            return
        
        cnt += 1
        return
    
    for i in ["4", "7"]:
        backt(num_len, nums+i)
    
for l in range(len(a), len(b)+1):
    backt(l, '')

print(cnt)

