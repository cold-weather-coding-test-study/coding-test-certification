# 18:15
words = []
while True:
  word = input()
  if word == '*':
    break
  words.append([c for c in word])

def is_surprising(word):
  l = len(word)
  for i in range(1, l):
    temp_set = set()
    for j in range(l-i):
      temp_set.add(word[j]+word[j+i])
    if len(temp_set) != l-i:
      return False
  return True

for word in words:
  print(f"{''.join(word)} is {'' if is_surprising(word) else 'NOT '}surprising.")