# https://www.acmicpc.net/problem/1874
from collections import deque
import sys

n = int(input())
dq = deque()
for _ in range(n):
    dq.append(int(input()))

answer = []

num = 1 #다음 삽입할 숫자
stack = [] #숫자 삽입된 배열

#시간복잡도 2n
while dq:
    n = dq.popleft()

    while num <= n :        
        stack.append(num)
        answer.append("+")
        num +=1

    if stack and stack[-1] == n:
        stack.pop()
        answer.append("-")
    
    else:
        print("NO")
        sys.exit(0)

print('\n'.join(answer))