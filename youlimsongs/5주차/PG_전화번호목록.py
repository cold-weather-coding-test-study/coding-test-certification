# https://school.programmers.co.kr/learn/courses/30/lessons/42577?language=python3

def solution(phone_book):
    #한 개의 번호를 기준으로, 해시 맵에서 전체와 비교
    hash = {}
    
    for phone in phone_book:
        hash[phone] = 1
    
    for phone in phone_book:
        temp = ''
        for p in phone:
            temp += p
            if temp in hash and temp != phone:
                return False
            
    return True