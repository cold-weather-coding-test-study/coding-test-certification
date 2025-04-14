# https://school.programmers.co.kr/learn/courses/30/lessons/42579?language=python3
def solution(genres, plays):    
    answer = []
    music = {} 
    total_gen = {} 
    
    # music = {'c':[(0, 50),(1, 10)]} 자료구조
    # total_gen 재생 수 합 => 장르 정렬
    for i, genre in enumerate(genres):
        play = plays[i]
        music.setdefault(genre, []).append((i, play))
        total_gen[genre] = total_gen.get(genre, 0) + play
        
    sorted_genres = sorted(total_gen.items(), key=lambda x:x[1], reverse=True)
    
    for genre, _ in sorted_genres:
        # 장르별 재생 수 내림차순, 고유 번호 오름차순으로 정렬
        top_songs = sorted(music[genre], key = lambda x: (-x[1], x[0]))
        answer.extend([idx for idx, _ in top_songs[:2]])
        
    return answer
