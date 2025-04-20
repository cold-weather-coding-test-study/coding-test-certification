# 19:19
n = int(input())
words = [sorted([c for c in input()]) for _ in range(n)]
answers = []
word_set = set()

def backtracking(word, is_visited, current_word):
  if len(current_word) == len(word):
    answers.append(current_word)
    return
  for i, c in enumerate(word):
    new_word = current_word+c
    if not is_visited[i] and new_word not in word_set:
      is_visited[i] = True
      word_set.add(new_word)
      backtracking(word, is_visited, new_word)
      is_visited[i] = False

for word in words:
  is_visited = [False] * len(word)
  backtracking(word, is_visited, '')
  word_set.clear()

for answer in answers:
  print(answer)