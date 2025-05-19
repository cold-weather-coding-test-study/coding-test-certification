# https://www.acmicpc.net/problem/16926
import sys
from collections import deque
sys.stdin = open("input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

n,m,r = map(int, input().split())
before = []
for _ in range(n):
    before.append(list(map(int, input().split())))

after = [[0]*m for _ in range(n)]

loops = min(n,m) //2

deq = deque()
for i in range(loops):
    deq.clear()
    deq.extend(before[i][i:m-i])
    deq.extend([row[m-1-i] for row in before[i+1:n-1-i]])
    deq.extend(before[n-1-i][i:m-i][::-1])
    deq.extend([row[i] for row in before[i+1:n-1-i][::-1]])

    deq.rotate(-r)

    for j in range(i, m-i): #위
        after[i][j] = deq.popleft()
    for j in range(i+1, n-1-i): #오른쪽
        after[j][m-1-i] = deq.popleft()
    for j in range(m-1-i, i-1, -1):
        after[n-1-i][j] = deq.popleft()
    for j in range(n-i-2, i, -1):
        after[j][i] = deq.popleft()
        
for row in after:
    print(*row)