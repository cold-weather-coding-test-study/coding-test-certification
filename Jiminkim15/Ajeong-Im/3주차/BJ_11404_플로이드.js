const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const n = +input[0];
const m = +input[1];
const INF = Infinity;

const dist = Array.from({ length: n + 1 }, () => Array(n + 1).fill(INF));

// 자기 자신 -> 자기 자신은 0으로 초기화
for (let i = 1; i <= n; i++) {
  dist[i][i] = 0;
}

// 버스 정보 입력
for (let i = 2; i < 2 + m; i++) {
  const [a, b, c] = input[i].split(" ").map(Number);
  dist[a][b] = Math.min(dist[a][b], c); // 중복 간선 고려
}

// 플로이드–워셜 알고리즘
for (let k = 1; k <= n; k++) {
  for (let i = 1; i <= n; i++) {
    for (let j = 1; j <= n; j++) {
      if (dist[i][k] + dist[k][j] < dist[i][j]) {
        dist[i][j] = dist[i][k] + dist[k][j];
      }
    }
  }
}

// 출력
let result = "";
for (let i = 1; i <= n; i++) {
  for (let j = 1; j <= n; j++) {
    result += (dist[i][j] === INF ? 0 : dist[i][j]) + " ";
  }
  result += "\n";
}
console.log(result.trim());
