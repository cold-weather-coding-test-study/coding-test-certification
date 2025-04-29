#  https://www.acmicpc.net/problem/6068
n = int(input())
works = []
for _ in range(n):
    burst, end_time = map(int, input().split())
    works.append((burst, end_time))

sort_works= sorted(works,key = lambda k: -k[1])

cur_time = sort_works[0][1]
for work in sort_works:
    if(work[1]<cur_time):
        cur_time = work[1]
    cur_time -= work[0]

    if(cur_time < 0):
        print("-1")
        exit()

print(cur_time)
