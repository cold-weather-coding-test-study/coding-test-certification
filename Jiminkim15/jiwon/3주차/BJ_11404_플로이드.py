import sys
input = sys.stdin.readline

n = int(input())
m = int(input())
INF = 1e9
maps = [[INF for i in range(n+1)] for i in range(n+1)]
for i in range(m):
    a,b,c = map(int,input().split())
    maps[a][b] = min(maps[a][b],c)

for k in range(1,n+1):
    for i in range(1,n+1):
        for j in range(1,n+1):
            maps[i][j] = min(maps[i][k] + maps[k][j], maps[i][j])

for i in range(n+1):
    maps[i][i] = 0
    for j in range(n+1):
        if maps[i][j] == INF:
            maps[i][j] = 0
for i in range(1,n+1):
    for j in range(1,n+1):
        print(maps[i][j],end =" ")
    print()
