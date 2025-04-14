### 📢 스터디 진행 방식

매주 **일요일** 까지 **공통 문제 3문제**와 **개인 문제 2문제** 풀고 **PR 제출**

- 공통 문제 3문제는 [Notion 문제집](https://www.notion.so/1a9aa2ba233d80a78a47c5830261f6cc?pvs=21) or [Github Issue](https://github.com/cold-weather-coding-test-study/coding-test-certification/issues) 참조
  - [문제집](https://algorithm.tony9402.com/)에서 **백준**, **실버3~골드1**, **주요 유형** 중 무작위로 3문제 선정
  - 아래 설명할 스크립트를 사용할 예정
- 개인 문제는 플랫폼, 난이도 상관없이 자유롭게 선정

### 무작위 문제 선출 스크립트 사용 방법

1. 권한 부여

   `chmod +x random_problem_picker.sh`

2. 스크립트 실행

   Usage: `./random_problem_picker.sh -l MIN_LEVEL -L MAX_LEVEL -t TAG_LIST -c COUNT -d`

   Options

   - `-l MIN_LEVEL` : 최소 난이도 (default: 8)
   - `-L MAX_LEVEL` : 최대 난이도 (default: 15)
   - `-t TAG_LIST` : 태그 목록 (default: "2,1,3,4,5,6,7,8,9,15,17,28")
   - `-c COUNT` : 선택할 문제 개수 (default: 3)
   - `-d` : 태그 겹침 검사 비활성화 (default: 활성화)

3. 결과 확인

   `BJ_{oj_problem_number}_{oj_problem_title} {문제_링크}`
   이러한 형식으로 문제를 출력

### **PR 제출 방식**

[예시 PR](https://github.com/cold-weather-coding-test-study/coding-test-certification/pull/2)

1. 각자 깃허브 아이디로 브랜치 생성

   ex: `git branch JeongHunHui`

2. `깃허브 아이디`/`n`주차/`플랫폼`\_`문제번호`\_`문제이름` 이러한 경로로 문제 풀이 추가

   ![image](https://github.com/user-attachments/assets/a99da735-1f42-4dbc-85d1-7ed3d68e6d27)

   - ex) `JeongHunHui/1주차/BJ_1027_고층_건물.py`
   - 커밋 메시지 & 커밋 방식은 자유
   - 백준 = `BJ` / 프로그래머스 = `PG` / LeetCode = `LC`

3. PR 생성, 아래와 같이 PR 문서 작성
   - PR 제목 - {n}주차 / {이름}
     ![image](https://github.com/user-attachments/assets/ed2d3790-45bf-4082-9684-9c709ff073c1)
   - PR 본문
     ![image](https://github.com/user-attachments/assets/bce6e353-e1fe-4d29-9c70-c9d0701cdb81)
     #1에서 1은 그 주의 이슈 번호
     이슈 번호는 해당 주차의 이슈에 들어가면 아래와 같이 확인 가능(아래의 경우 15가 이슈 번호)
     ![image](https://github.com/user-attachments/assets/f5c4b427-2841-45f6-9fb3-7343e46cdd1f)
