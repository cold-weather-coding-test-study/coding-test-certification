# 15:37
def get_password(key_log):
  forward = []
  behind = []
  for c in key_log:
    if c == '<':
      if len(forward) > 0:
        behind.append(forward.pop())
    elif c == '>':
      if len(behind) > 0:
        forward.append(behind.pop())
    elif c == '-':
      if len(forward) > 0:
        forward.pop()
    else:
      forward.append(c)
  return ''.join(forward+list(reversed(behind)))

key_logs = [input() for _ in range(int(input()))]

for key_log in key_logs:
  print(get_password(key_log))