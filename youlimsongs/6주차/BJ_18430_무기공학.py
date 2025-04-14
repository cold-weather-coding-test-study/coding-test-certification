# https://www.acmicpc.net/problem/18430

n,m = map(int, input().split())
block = []
for _ in range(n):
    block.append(list(map(int, input().split())))


#모든 좌표를 탐색했을때 최댓값 갱신
#좌표를 넘기는 건, idx로 넘겨서 y,x값 계산
power = 0
dx=[1,-1,1,-1]
dy = [-1,1,1,-1]
visited = [[False]*m for _ in range(n)]

def backt(idx, cur_sum):
    global power

    if idx == n*m:
        power = max(cur_sum, power)
        return
    
    y = idx //m
    x = idx % m
    
    if visited[y][x]:
        backt(idx+1, cur_sum)
        return
    
    for i in range(4):
        nx = x+dx[i]
        ny = y+dy[i]

        if(nx < 0 or nx >=m or ny <0 or ny>=n): continue

        if(visited[y][x] or visited[y][nx] or visited[ny][x]): continue

        visited[y][x]=visited[y][nx]=visited[ny][x]=True

        temp= block[y][x]*2 + block[ny][x] + block[y][nx]

        backt(idx+1, cur_sum+temp)

        visited[y][x]=visited[y][nx]=visited[ny][x] = False

    backt(idx + 1, cur_sum)

backt(0,0)
print(power)