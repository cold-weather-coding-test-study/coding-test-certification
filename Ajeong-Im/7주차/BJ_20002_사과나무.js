const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const N = Number(input[0]);
const arr = input.slice(1).map((line) => line.split(" ").map(Number));

// 누적합 테이블 초기화
const prefix = Array.from({ length: N + 1 }, () => Array(N + 1).fill(0));

// 누적합 계산
for (let i = 1; i <= N; i++) {
  for (let j = 1; j <= N; j++) {
    prefix[i][j] =
      arr[i - 1][j - 1] +
      prefix[i - 1][j] +
      prefix[i][j - 1] -
      prefix[i - 1][j - 1];
  }
}

let maxSum = -Infinity;

// 모든 K × K 정사각형 시도
for (let k = 1; k <= N; k++) {
  for (let i = k; i <= N; i++) {
    for (let j = k; j <= N; j++) {
      const sum =
        prefix[i][j] -
        prefix[i - k][j] -
        prefix[i][j - k] +
        prefix[i - k][j - k];
      maxSum = Math.max(maxSum, sum);
    }
  }
}

console.log(maxSum);
