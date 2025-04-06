# https://school.programmers.co.kr/learn/courses/30/lessons/42584

from collections import deque
def solution(prices):
    answer = [0 for _ in range(len(prices))]
    stock = [];
    
    for i, p in enumerate(prices):
        #stock 비어있지 않고, 마지막 수 > 현재 수
        #마지막 수 <= 현재 수 까지, pop -> 
        while(stock and stock[-1][1] > p):
            idx, price = stock.pop()
            answer[idx] = i-idx
                
        stock.append((i, p))
                
    for i,p in stock:
        answer[i] = len(prices)-i-1
    
    return answer