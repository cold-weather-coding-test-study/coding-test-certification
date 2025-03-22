const fs = require("fs");
const input = parseInt(fs.readFileSync("/dev/stdin").toString());

const dp = Array(input + 1).fill(Infinity);
dp[0] = 0;

for (let i = 1; i <= input; i++) {
  for (let j = 1; j * j <= i; j++) {
    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
  }
}

console.log(dp[input]);
