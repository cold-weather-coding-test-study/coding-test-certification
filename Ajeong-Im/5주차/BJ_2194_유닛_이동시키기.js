const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const [N, M, A, B, K] = input[0].split(" ").map(Number);
const map = Array.from({ length: N }, () => Array(M).fill(0));

// 장애물 입력
for (let i = 1; i <= K; i++) {
  const [r, c] = input[i].split(" ").map((x) => parseInt(x) - 1);
  map[r][c] = 1;
}

// 시작, 도착 위치
const [sr, sc] = input[K + 1].split(" ").map((x) => parseInt(x) - 1);
const [er, ec] = input[K + 2].split(" ").map((x) => parseInt(x) - 1);

// 유닛이 (r, c) 위치에 올 수 있는지 체크 (왼쪽 위 기준)
function canMove(r, c) {
  if (r < 0 || c < 0 || r + A > N || c + B > M) return false;

  for (let i = r; i < r + A; i++) {
    for (let j = c; j < c + B; j++) {
      if (map[i][j] === 1) return false;
    }
  }
  return true;
}

// BFS
const visited = Array.from({ length: N }, () => Array(M).fill(false));
const queue = [[sr, sc, 0]];
visited[sr][sc] = true;

const dr = [-1, 1, 0, 0]; // 상하좌우
const dc = [0, 0, -1, 1];

let answer = -1;

while (queue.length) {
  const [r, c, cnt] = queue.shift();

  if (r === er && c === ec) {
    answer = cnt;
    break;
  }

  for (let d = 0; d < 4; d++) {
    const nr = r + dr[d];
    const nc = c + dc[d];

    if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
    if (visited[nr][nc]) continue;
    if (!canMove(nr, nc)) continue;

    visited[nr][nc] = true;
    queue.push([nr, nc, cnt + 1]);
  }
}

console.log(answer);
