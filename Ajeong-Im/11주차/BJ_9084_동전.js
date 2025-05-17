const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let T = parseInt(input[0], 10);
let idx = 1;

for (let t = 0; t < T; t++) {
  const N = parseInt(input[idx]);
  const coins = input[idx + 1].split(" ").map(Number);
  const M = parseInt(input[idx + 2]);
  idx += 3;

  const dp = new Array(M + 1).fill(0);
  dp[0] = 1;

  for (const coin of coins) {
    for (let i = coin; i <= M; i++) {
      dp[i] += dp[i - coin];
    }
  }

  console.log(dp[M]);
}
