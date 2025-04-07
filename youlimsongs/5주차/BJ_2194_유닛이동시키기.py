from collections import deque

n,m,a,b,k = map(int, input().split())
graph = [[0]*(m+1) for _ in range(n+1)]

for _ in range(k):
    y, x = map(int, input().split())
    graph[y][x] = 1

sy, sx = map(int, input().split())
fy, fx = map(int, input().split())

dx=[0,0,1,-1]
dy=[1,-1,0,0]

def is_clear(y, x):
    #왼쪽 오른쪽 변 검사
    for i in range(a): 
        if graph[y+i][x] == 1: return False
        if graph[y+i][x+b-1] == 1: return False
    
    #윗변 아랫변 확인
    for j in range(b):
        if graph[y][x+j] == 1: return False
        if graph[y+a-1][x+j] == 1: return False

    return True

def bfs():
    queue=deque()
    queue.append((sy, sx, 0))
    graph[sy][sx] = 3 #방문처리

    while(queue):
        y,x,dep = queue.popleft() #pop(0)보다 빠른 큐 처리

        if(y==fy and x==fx):
            return dep
        
        for i in range(4):
            mx = x+dx[i]
            my = y+dy[i]

            if mx <= 0 or mx+b-1 > m or my <= 0 or my+a-1 > n:
                continue

            if graph[my][mx] != 3 and is_clear(my, mx):
                graph[my][mx] = 3 #방문처리
                queue.append((my,mx,dep+1))
    
    return -1

print(bfs())