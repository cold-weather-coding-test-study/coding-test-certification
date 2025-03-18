# 00:26

# 색종이 크기의 범위: 가장 높이 있는 칸 ~ 최대 높이
# y축은 별로 안중요할듯 걍 가장 높은 칸만 구하면 됨

max_height, max_width = map(int, input().split())
paper_count = int(input())
miss_count = int(input())
min_height = 0
widths = []
for _ in range(miss_count):
  h, w = map(int, input().split())
  min_height = max(min_height, h)
  widths.append(w)
widths.sort()

def can_full(target_width):
  pre_w = widths[0]
  count = 1
  for w in widths[1:]:
    diff = w - pre_w
    if diff >= target_width:
      pre_w = w
      count += 1
    if count > paper_count:
      return False
  return True

while min_height != max_height:
  target_width = min_height + (max_height - min_height)//2
  if can_full(target_width):
    max_height = target_width
  else:
    min_height = target_width + 1

print(min_height)