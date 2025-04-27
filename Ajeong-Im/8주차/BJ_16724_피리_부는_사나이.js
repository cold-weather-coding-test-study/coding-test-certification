const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const [N, M] = input[0].split(" ").map(Number);
const map = input.slice(1).map((line) => line.split(""));

// 상태: 0 = 미방문, 1 = 탐색중, 2 = 탐색완료
const visited = Array.from({ length: N }, () => Array(M).fill(0));

const direction = { U: [-1, 0], D: [1, 0], L: [0, -1], R: [0, 1] };

let answer = 0;

function dfs(x, y) {
  visited[x][y] = 1; // 탐색 중

  const [dx, dy] = direction[map[x][y]];
  const nx = x + dx;
  const ny = y + dy;

  if (visited[nx][ny] === 0) {
    dfs(nx, ny);
  } else if (visited[nx][ny] === 1) {
    answer++;
  }

  visited[x][y] = 2; // 탐색 완료
}

for (let i = 0; i < N; i++) {
  for (let j = 0; j < M; j++) {
    if (visited[i][j] === 0) {
      dfs(i, j);
    }
  }
}

console.log(answer);
