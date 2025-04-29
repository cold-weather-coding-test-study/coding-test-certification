# https://www.acmicpc.net/problem/15918
from sys import stdin
input = stdin.readline

n, x, y = map(int, input().split())
first = y - x - 1

if first == 0:
    print(0)
    exit()

size = 2 * n
arr = [0] * size
arr[x - 1] = arr[y - 1] = first

num_list = set(range(1, n + 1))
num_list.remove(first)

cnt = 0

def backtracking():
    global cnt
    if 0 not in arr:
        cnt += 1
        return

    i = arr.index(0)
    for j in range(i + 1, size):
        dist = j - i - 1
        if arr[j] == 0 and dist in num_list:
            arr[i] = arr[j] = dist
            num_list.remove(dist)

            backtracking()

            arr[i] = arr[j] = 0
            num_list.add(dist)

backtracking()
print(cnt)
