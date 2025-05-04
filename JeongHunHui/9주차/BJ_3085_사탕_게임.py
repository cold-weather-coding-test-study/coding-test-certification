# 16:34

# 모든 행과 열을 돌면서 사탕을 바꿨을 때 가장 길어지는 길이의 최대값을 구한다.

n = int(input())
candies = [[c for c in input()] for _ in range(n)]

def get_max_seq_candy_count(candy):
  answer = 0
  current_answer = 1
  pre_c = candy[0]
  for c in candy[1:]:
    if c == pre_c:
      current_answer += 1
    else:
      answer = max(answer, current_answer)
      current_answer = 1
    pre_c = c
  return max(answer, current_answer)


# 모든 행과 열의 연속 캔디 수
row_seq_count = [get_max_seq_candy_count(candies[i]) for i in range(n)]
col_seq_count = [get_max_seq_candy_count([candies[j][i] for j in range(n)]) for i in range(n)]

def get_max_seq_candy_count_with_pair(is_row, idx):
  i1, i2 = idx, idx+1
  candy_str_1 = candies[i1] if is_row else [candies[j][i1] for j in range(n)]
  candy_str_2 = candies[i2] if is_row else [candies[j][i2] for j in range(n)]
  answer = 0
  for i in range(len(candy_str_1)):
    # 두 사탕에서 각각 i번째 끼리 교환
    # 교환하려는 두 사탕이 같으면 스킵
    if candy_str_1[i] == candy_str_2[i]:
      continue
    temp_candy_str_1 = candy_str_1[:i] + candy_str_2[i:i+1] + candy_str_1[i+1:]
    temp_candy_str_2 = candy_str_2[:i] + candy_str_1[i:i+1] + candy_str_2[i+1:]
    temp = candies[i] if is_row else [candies[j][i] for j in range(n)]
    temp_candy_str_3 = temp[:i1] + [temp[i2]] + [temp[i1]] + temp[i2+1:]
    answer = max(answer, get_max_seq_candy_count(temp_candy_str_1), get_max_seq_candy_count(temp_candy_str_2), get_max_seq_candy_count(temp_candy_str_3))
    if is_row:
      answer = max([answer] + col_seq_count[:i] + col_seq_count[i+1:] + row_seq_count[:i1] + row_seq_count[i2+1:])
    else:
      answer = max([answer] + row_seq_count[:i] + row_seq_count[i+1:] + col_seq_count[:i1] + col_seq_count[i2+1:])
  return answer

answer = 0
# 모든 행
for i in range(n-1):
  get_max_seq_candy_count
  answer = max(answer, get_max_seq_candy_count_with_pair(True, i))
# 모든 열
for i in range(n-1):
  answer = max(answer, get_max_seq_candy_count_with_pair(False, i))

print(answer)