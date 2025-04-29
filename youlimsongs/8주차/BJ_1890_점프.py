n = int(input())
arr = []
for _ in range(n):
    arr.append(list(map(int, input().split())))

cnt = [[0]*n for _ in range(n)] #arr[i][j] i,j까지 올수있는 경우의 수
cnt[0][0] = 1

def dp():
    for i in range(n):
        for j in range(n):
            move = arr[i][j]
            if move==0 or cnt[i][j] == 0: continue

            if(j+move < n): cnt[i][j+move] += cnt[i][j]
            if(i+move < n): cnt[i+move][j] += cnt[i][j]
dp()
print(cnt[n-1][n-1])