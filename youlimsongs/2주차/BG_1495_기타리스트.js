const path = require('path');
const filePath =
  process.platform === 'linux'
    ? '/dev/stdin'
    : path.join(__dirname, 'input.txt');
const input = require('fs')
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n');

const [n, s, m] = input[0].split(' ').map(Number);
const v = input[1].split(' ').map(Number);
let dp = Array.from({ length: n + 1 }, () => Array(m + 1).fill(0));

dp[0][s] = 1;

for (let i = 1; i <= n; i++) {
  for (let j = 0; j <= m; j++) {
    if (dp[i - 1][j] === 1) {
      if (j + v[i - 1] <= m) dp[i][j + v[i - 1]] = 1;

      if (j - v[i - 1] >= 0) dp[i][j - v[i - 1]] = 1;
    }
  }
}

let ans = -1;
for (let i = m; i >= 0; i--) {
  if (dp[n][i] === 1) {
    ans = i;
    break;
  }
}

console.log(ans);
