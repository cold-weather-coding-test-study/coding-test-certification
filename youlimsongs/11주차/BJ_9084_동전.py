import sys
sys.stdin = open("input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

#1개부터 n개까지의 동전을 조합해서 금액m을 만든다.
# 10000*5000*3400..? -> 터지는데.. 조건 걸어서 가지를 치면 되려나..?

# def backt(calc, idx): 
#     if calc == 0:
#         global cnt
#         cnt +=1
#         return
    
#     for i in range(idx, n):
#         max_cnt =calc//coins[i]
#         for m in range(1, max_cnt+1):
#             backt(calc - (coins[i] * m), i+1)

# backt(money, 0)
t = int(input())
for _ in range(t):
    n = int(input())
    coins = list(map(int, input().split(' ')))
    money = int(input())

    dp = [0]*(money+1)
    dp[0] = 1
    # print(dp)
    
    for c in coins:
        for i in range(c, money+1):
            dp[i] += dp[i-c]
    
    print(dp[money])