import sys
from functools import cmp_to_key

input = sys.stdin.readline

n = int(input())
res = []

# 입력 토큰화
for _ in range(n):
    s = input().rstrip()
    arr = []
    num = ''
    prev_num = False
    for ch in s:
        if ch.isdigit():
            num += ch
            prev_num = True
        else:
            if prev_num:
                arr.append(num)
                num = ''
            prev_num = False
            arr.append(ch)
    if prev_num:
        arr.append(num)
    res.append(arr)

def natural_sort(lst1, lst2):
    i = 0
    L1, L2 = len(lst1), len(lst2)
    
    while i < min(L1, L2):
        a, b = lst1[i], lst2[i]
        if a == b:
            i += 1
            continue

        # 숫자, 문자
        if a.isdigit() and b.isalpha():
            return -1
        if a.isalpha() and b.isdigit():
            return 1

        # 둘 다 문자
        if a.isalpha() and b.isalpha():
            la, lb = a.lower(), b.lower()
            if la != lb: #'B', 'a' 인 경우
                return -1 if la < lb else 1
            return -1 if a < b else 1

        # 둘 다 숫자
        ai, bi = int(a), int(b)
        if ai != bi:
            return -1 if ai < bi else 1

        if len(a) != len(b): #'1', '0001'
            return -1 if len(a) < len(b) else 1

        i += 1

    return -1 if L1 < L2 else 1

for tokens in sorted(res, key=cmp_to_key(natural_sort)):
    print(''.join(tokens))
