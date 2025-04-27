const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const N = Number(input[0]);
const board = input.slice(1).map((line) => line.split(" ").map(Number));

const dp = Array.from({ length: N }, () => Array(N).fill(BigInt(0)));
dp[0][0] = BigInt(1);

for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    const jump = board[i][j];
    if (dp[i][j] === BigInt(0) || (i === N - 1 && j === N - 1)) continue;

    if (i + jump < N) {
      dp[i + jump][j] += dp[i][j];
    }
    if (j + jump < N) {
      dp[i][j + jump] += dp[i][j];
    }
  }
}

console.log(dp[N - 1][N - 1].toString());
