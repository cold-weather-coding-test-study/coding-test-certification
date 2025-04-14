def back(pos, prev):
    if pos == 2 * N:
        return True

    for i in range(N):
        if used[i] < 2 and ((prev[i] != -1 and pos - prev[i] == X[i] + 1) or (prev[i] == -1)):
            S[pos] = X[i]
            used[i] += 1
            prev_tmp = prev[i]
            prev[i] = pos

            if back(pos + 1, prev):
                return True

            # Backtrack
            used[i] -= 1
            S[pos] = 0
            prev[i] = prev_tmp
    return False


N = int(input())
X = sorted(list(map(int, input().split())))  # 만약 여러 개일 경우 사전 순으로 가장 빠른 것을 출력
S = [0] * 2 * N
used = [0] * N
prev = [-1] * N

if back(0, prev):
    print(' '.join(map(str, S)))
else:
    print(-1)
