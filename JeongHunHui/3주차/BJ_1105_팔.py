# 17:29
l, r = input().split()
l = l.rjust(len(r), 'x')
answer = 0
for i in range(len(r)):
  if l[i] != r[i]:
    break
  if l[i] == '8':
    answer += 1
print(answer)