import sys

n = int(input())
s = input()
graph = [[None]*n for _ in range(n)]
p=0

#+1,0,-1로 그래프 초기화
#임의의 숫자 넣어가면서 조건 맞는지 검사
    #백트래킹
    
for i in range(n):
    for j in range(i, n):
        if s[p] == '+':
            graph[i][j] = 1
        elif s[p] == '-':
            graph[i][j] = -1
        else:
            graph[i][j] = 0
        p+=1

answer = [0]*n #후보

def check(idx, num):
    answer[idx] = num
    temp = 0
    for i in range(idx, -1, -1):
        temp += answer[i]
        if graph[i][idx] > 0 and temp <=0:
            return False
        elif graph[i][idx] < 0 and temp >=0:
            return False
        elif graph[i][idx] == 0 and temp != 0:
            return False
    return True

def dfs(dep): 
    if dep==n:
        print(*answer)
        sys.exit()
    
    else:
        if graph[dep][dep] == 0:
            if check(dep, 0):
                dfs(dep+1)
        else:
            for x in range(1,11):
                num = x*graph[dep][dep]
                if check(dep, num):
                    dfs(dep+1)

dfs(0)