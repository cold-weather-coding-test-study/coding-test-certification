import sys
sys.setrecursionlimit(10**6) # 파이썬 재귀깊이가 최대 1000이므로 늘려줘야함
n = int(sys.stdin.readline())
tree = [[] for _ in range(n+1)]
cnt = 0
for i in range(n):
    root, left, right = map(int, sys.stdin.readline().split()) # 부모노드에서 자식노드 연결해주기
    if left == -1: # 왼쪽자식이 없다는거니깐 0 으로 넣어주기
        left = 0
    if right == -1:  # 오른쪽자식이 없다는거니깐 0 으로 넣어주기
        right = 0
    tree[root].append(left)
    tree[root].append(right)

def max_right(t): # 마지막 종료지점을 찾기위해 중위 순회
    global r
    if tree[t][0]:
        max_right(tree[t][0])
    r = t # 계속 바뀌면서 결국 마지막 도착지점을 r로 받음
    if tree[t][1]:
        max_right(tree[t][1])

def inorder(t):
    global cnt # 몇 번 가는지 카운트
    global ans # 정답 담을 변수
    if r == t: # 만약 마지막 노드에 도착했다면 그동안 셌던 cnt를 ans로 받는다. (이때는 마지막 노드가 자식이 없을경우)
        ans = cnt
    if tree[t][0]:
        cnt += 1 # 이제 자식으로 갈 때 cnt + 1
        inorder(tree[t][0])
        cnt += 1 # 다시 나한테 왔을때 cnt + 1
        if r == t: # 마지막 노드가 왼쪽 자식이 있을경우
            ans = cnt # 여기서 ans를 받아준다.
    if tree[t][1]:
        cnt += 1 # 이제 자식으로 갈 때 cnt + 1
        inorder(tree[t][1])
        cnt += 1 # 다시 나한테 왔을때 cnt + 1

max_right(1) # 마지막 도착 노드를 구하고
inorder(1) # 루트노드인 1에서 출발
print(ans)
