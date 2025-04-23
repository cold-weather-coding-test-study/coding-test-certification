const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const N = +input[0];
const M = +input[1];
const adj = Array.from({ length: N + 1 }, () => Array(N + 1).fill(0));

// 측정된 관계 입력
for (let i = 2; i < 2 + M; i++) {
  const [a, b] = input[i].split(" ").map(Number);
  adj[a][b] = 1; // a가 b보다 무겁다
}

// Floyd-Warshall 알고리즘
for (let k = 1; k <= N; k++) {
  for (let i = 1; i <= N; i++) {
    for (let j = 1; j <= N; j++) {
      if (adj[i][k] && adj[k][j]) {
        adj[i][j] = 1;
      }
    }
  }
}

// 결과 계산
for (let i = 1; i <= N; i++) {
  let count = 0;
  for (let j = 1; j <= N; j++) {
    if (i === j) continue;
    if (adj[i][j] === 0 && adj[j][i] === 0) {
      count++;
    }
  }
  console.log(count);
}
