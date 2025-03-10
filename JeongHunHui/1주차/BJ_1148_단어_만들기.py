# 16:36
# 한 글자당 최대 1번, 단어는 최소 4글자 이상, 정 중앙 글자는 반드시 사용
# 최소 중앙, 최소 정답 수, 최대 중앙, 최대 정답 수

from collections import defaultdict

words = []
while True:
  word = input()
  if word == '-':
    break
  word_dict = defaultdict(int)
  for c in word:
    word_dict[c] += 1
  words.append(word_dict)
  
puzzles = []
while True:
  puzzle = input()
  if puzzle == '#':
    break
  word_dict = defaultdict(int)
  for c in puzzle:
    word_dict[c] += 1
  puzzles.append(word_dict)
for puzzle in puzzles:
  word_dict = defaultdict(int)
  for key in puzzle.keys():
    word_dict[key] = 0
  for word in words:
    keys = word.keys()
    if all([word[key] <= puzzle[key] for key in keys]):
      for key in set(keys):
        word_dict[key] += 1
  min_answer_keys = []
  min_answer_count = 200000
  max_answer_keys = []
  max_answer_count = 0
  for key in word_dict.keys():
    answer_count = word_dict[key]
    if min_answer_count > answer_count:
      min_answer_keys = [key]
      min_answer_count = answer_count
    elif min_answer_count == answer_count:
      min_answer_keys.append(key)
    
    if max_answer_count < answer_count:
      max_answer_keys = [key]
      max_answer_count = answer_count
    elif max_answer_count == answer_count:
      max_answer_keys.append(key)
  
  print(''.join(sorted(min_answer_keys)), min_answer_count, ''.join(sorted(max_answer_keys)), max_answer_count)