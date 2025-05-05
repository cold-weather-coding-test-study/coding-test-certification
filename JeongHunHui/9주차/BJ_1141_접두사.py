# 17:38
n = int(input())
words = [input() for _ in range(n)]
words.sort()

def is_prefix(word1, word2):
  long_word, short_word = '', ''
  if len(word1) > len(word2):
    long_word, short_word = word1, word2
  else:
    long_word, short_word = word2, word1
  return short_word == long_word[:len(short_word)]

answer = 1
for i in range(n-1):
  if not is_prefix(words[i], words[i+1]):
    answer += 1
print(answer)