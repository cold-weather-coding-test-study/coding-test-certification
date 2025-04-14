import heapq

T = int(input())
for _ in range(T):
    K = int(input())
    files = list(map(int, input().split()))
    heapq.heapify(files)
    answer = 0

    while len(files) > 1:
        temp = 0
        a = heapq.heappop(files)
        b = heapq.heappop(files)
        tmp = a+b
        answer += tmp
        heapq.heappush(files, tmp)

    print(answer)