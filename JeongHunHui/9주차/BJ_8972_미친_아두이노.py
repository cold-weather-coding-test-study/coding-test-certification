# 15:14
from collections import Counter

r, c = map(int, input().split())
js_y, js_x = 0, 0
robot_positions = []
for y in range(r):
  s = input()
  for x, ch in enumerate(s):
    if ch == 'I':
      js_y, js_x = y, x
    elif ch == 'R':
      robot_positions.append((y, x))
move_commands = [int(ch) for ch in input()]

dirs = [None, (1,-1), (1,0), (1,1), (0,-1), (0,0), (0,1), (-1,-1), (-1,0), (-1,1)]

def is_js_meet_robot():
  return (js_y, js_x) in robot_positions

answer = None
for i, move_command in enumerate(move_commands):
  # 종수 이동 (move_command로)
  dy, dx = dirs[move_command]
  js_y += dy
  js_x += dx
  
  # 종수와 아두이노가 같은 칸에 있으면 패배
  if is_js_meet_robot():
    answer = f'kraj {i+1}'
    break

  # 아두이노가 없으면 스킵
  if len(robot_positions) == 0:
    continue

  # 아두이노 이동 (종수가 있는 방향으로)
  temp_robot_positions = []
  for robot_y, robot_x in robot_positions:
    if robot_y > js_y:
      robot_y -= 1
    elif robot_y < js_y:
      robot_y += 1
    if robot_x > js_x:
      robot_x -= 1
    elif robot_x < js_x:
      robot_x += 1
    temp_robot_positions.append((robot_y, robot_x))
  robot_positions = temp_robot_positions

  # 종수와 아두이노가 같은 칸에 있으면 패배
  if is_js_meet_robot():
    answer = f'kraj {i+1}'
    break
  
  # 2개 이상의 아두이노가 같은 칸에 있으면 모두 제거
  robot_position_counter = Counter(robot_positions)
  for pos, count in list(robot_position_counter.items()):
    if count > 1:
      del robot_position_counter[pos]
  robot_positions = list(robot_position_counter.elements())

if answer != None:
  print(answer)
else:
  for y in range(r):
    temp = ''
    for x in range(c):
      if (y, x) in robot_positions:
        temp += 'R'
      elif js_x == x and y == js_y:
        temp += 'I'
      else:
        temp += '.'
    print(temp)