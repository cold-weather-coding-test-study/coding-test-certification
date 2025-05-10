import sys
sys.stdin = open("input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

#자료구조... 튜플? 딕셔너리는 순회가 어려우니..
n = int(input())
nums = list(map(int, input().split()))
operators = list(map(int, input().split())) # +,-,*,/

max_val = -int(1e9)
min_val = int(1e9)

def backtracking(calc, idx):
    global max_val, min_val
    if idx == n:
        max_val = max(max_val, calc)
        min_val = min(min_val, calc)
        return
    
    prev_idx = -1
    for i in range(4):
        if prev_idx != i and operators[i] > 0:
            operators[i] -= 1

            if i == 0:
                backtracking(calc + nums[idx], idx + 1)
            elif i == 1:
                backtracking(calc - nums[idx], idx + 1)
            elif i == 2:
                backtracking(calc * nums[idx], idx + 1)
            else:
                if calc < 0:
                    backtracking(-(-calc // nums[idx]), idx + 1)
                else:
                    backtracking(calc // nums[idx], idx + 1)

            operators[i] += 1
            prev_idx = i
            
backtracking(nums[0], 1)

print(max_val)
print(min_val)

# 백트래킹 - 같은 층에서 중복 불가능, 모든 경우의 수 계산
# 연산자 넣고 계산값 저장
# 최대 최솟값 구함