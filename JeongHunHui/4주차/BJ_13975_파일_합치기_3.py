# 17:28
# 합칠 때 필요한 비용 = 두 파일 크기의 합
import heapq as hq

tc = int(input())

num_list = []
for _ in range(tc):
  n = int(input())
  nums = list(map(int, input().split()))
  num_list.append(nums)

for nums in num_list:
  hq.heapify(nums)
  answer = 0
  while len(nums) > 1:
    num1 = hq.heappop(nums)
    num2 = hq.heappop(nums)
    s = num1+num2
    answer += s
    hq.heappush(nums, s)
  print(answer)