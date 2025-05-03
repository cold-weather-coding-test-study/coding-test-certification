# https://www.acmicpc.net/problem/8972
from collections import Counter

#종수의 위치 x,y를 벗어나지 않는 선에서 이동
import sys
sys.stdin = open("input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

R, C = map(int, input().split())
board = []
crazy_a = []
player = (-1, -1)

for y in range(R):
    row = list(input().strip())
    for x in range(C):
        if row[x] == 'R':
            crazy_a.append((y, x))
        elif row[x] == 'I':
            player = (y, x)

commands = list(map(int, list(input().strip())))

def check_dir(num):
    # (y, x)
    dir = { 
        1: (1, -1),
        2: (1, 0),
        3: (1, 1),
        4: (0, -1),
        5: (0, 0),
        6: (0, 1),
        7: (-1, -1),
        8: (-1, 0),
        9: (-1, 1)
    }

    return dir[num]

cnt = 0
for cmd in commands:
    dy, dx = check_dir(cmd)
    player = (player[0] + dy, player[1] + dx)
    cnt += 1 #이동횟수

    if player in crazy_a:
        print("kraj", cnt)
        exit()
    
    new_crazy_a = []
    for y, x in crazy_a:
        ny, nx = y, x
        if y < player[0]: ny += 1
        elif y > player[0]: ny -= 1
        if x < player[1]: nx += 1
        elif x > player[1]: nx -= 1

        new_crazy_a.append((ny, nx))  

    count = Counter(new_crazy_a)

    # 이동한 좌표가 종수랑 겹치면 게임 끝
    if count[player] >= 1:
        print(f'kraj {cnt}')
        exit()

    #겹친 아두이노 제거
    crazy_a = [pos for pos in new_crazy_a if count[pos] == 1]  
        

#종수 아두이노 위치에 맞게 graph출력 
for y in range(R):
    row = []
    for x in range(C):
        if (y, x) == player:
            row.append('I')
        elif (y, x) in crazy_a:
            row.append('R')
        else:
            row.append('.')
    print(''.join(row))

