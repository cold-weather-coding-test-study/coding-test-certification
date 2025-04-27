# 15:14
from collections import Counter, deque
# 테스트 케이스 수
c = int(input())
tc = []
for _ in range(c):
  # 문서의 개수, 궁금한 문서 번호
  _, m = map(int, input().split())
  priorities = list(map(int, input().split()))
  tc.append((m, priorities))

def solution(idx, priorities):
  counter = Counter(priorities)
  priority_rank = sorted(list(counter))
  dq = deque(enumerate(priorities))
  print_count = 0
  while dq:
    current_idx, priority = dq.popleft()
    # 현재 우선도가 가장 높으면 인쇄
    if priority_rank[-1] == priority:
      counter[priority] -= 1
      if counter[priority] == 0:
        priority_rank.pop()
      print_count += 1
      if idx == current_idx:
        return print_count
    # 아니면 가장 뒤로 보내기
    else:
      dq.append((current_idx, priority))
  return -1

for m, priorities in tc:
  print(solution(m, priorities))