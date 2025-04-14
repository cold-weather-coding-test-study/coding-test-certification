# 문제 풀이 ...
# 이거 파이썬 조합 사용해서 나온 결과에 set하면 되겠다
# 1차 풀이
# from itertools import permutations
#
# n = int(input())
#
# answer = []
# for i in range(n):
#     str = input()
#     a = list(permutations(str,len(str)))
#     a.sort()
#     b= answer+a
#
#     for i in range(len(b)):
#         print()
#         for j in range(len(str)):
#             print(b[i][j], end='')


# 2차 풀이
def permute_unique(freq, prefix, length):
    """freq: {'a':2, 'b':1, ...} 형태의 딕셔너리
       prefix: 현재까지 만든 부분 문자열
       length: 최종 문자열 길이"""
    if len(prefix) == length:
        print(prefix)
        return

    # freq를 키 순서대로(사전순) 순회
    for ch in sorted(freq.keys()):
        if freq[ch] > 0:
            # 해당 문자 사용
            freq[ch] -= 1
            permute_unique(freq, prefix + ch, length)
            # 백트래킹 복원
            freq[ch] += 1


n = int(input().strip())
for _ in range(n):
    word = input().strip()
    # 문자 빈도수 계산
    freq_map = {}
    for c in word:
        freq_map[c] = freq_map.get(c, 0) + 1

    # 백트래킹 호출 (prefix="", length=len(word))
    permute_unique(freq_map, "", len(word))
