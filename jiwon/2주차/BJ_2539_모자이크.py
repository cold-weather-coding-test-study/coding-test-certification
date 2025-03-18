import sys

# 입력부
n, m = map(int, sys.stdin.readline().split())
k = int(sys.stdin.readline())
q = int(sys.stdin.readline())
arr = []
left = 0
right = 9876543210
for _ in range(q):
    a, b = map(int, sys.stdin.readline().split())

    # 최소 높이 갱신
    left = max(left, a)
    arr.append((a, b))

# x축 기준 정렬
arr.sort(key=lambda x: (x[1]))

while left < right:
    # 이 때 mid 값이 색종이의 길이가 된다
    mid = (left + right) // 2

    # idx : 현재 새로운 색종이의 시작하는 칸의 인덱스
    idx = 0

    # cnt : 현재까지 몇개의 색종이를 붙였는가에 대한 변수
    cnt = 0
    while idx < q:
        cnt += 1
        start_x = arr[idx][1]

        # 문제 조건에 의해 색종이는 반드시 밑변에 붙어야 하므로 start_y는 1
        start_y = 1
        i = idx

        # 현재 색종이로 덮을 수 있는 칸을 탐색
        while i < q and start_x <= arr[i][1] < start_x + mid and start_y <= arr[i][0] < start_y + mid:
            i += 1

        # 현재 색종이로 덮을 수 없는 최초의 칸이 다시 시작점이 된다
        idx = i

    # 만일 현재 mid 길이로 k개 이하로 덮을 수 있다면, 길이를 줄인다
    if cnt <= k:
        right = mid
    # 그렇지 않으면 길이를 늘린다
    else:
        left = mid + 1

# 정답 출력
print(right)
