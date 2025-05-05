# https://www.acmicpc.net/problem/4963
import sys
sys.stdin = open("input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

while(1):
    n,m = map(int, input().split())
    if(n==0 and m==0):
        break
    
    graph = []
    for i in range(m):
        graph.append(list(map(int, input().split())))

    dx = [0,0,1,-1,1,-1,1,-1]
    dy = [1,-1,0,0,1,-1,-1,1]
    cnt = 0

    def bfs(y, x):
        graph[y][x] = 2
        queue = [[y,x]]

        while(queue):
            [y, x] = queue.pop(0)
            for i in range(8):
                ny = y+dy[i]
                nx = x+dx[i]

                if nx<0 or nx >= n or ny < 0 or ny >= m or graph[ny][nx] != 1:
                    continue
                graph[ny][nx] = 2
                queue.append([ny,nx])


    for y in range(m):
        for x in range(n):
            if graph[y][x]==1:
                bfs(y,x)
                cnt+=1
    print(cnt)
            

                    
                    


