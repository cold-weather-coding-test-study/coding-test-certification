const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
let idx = 0;
const C = Number(input[idx++]);

for (let c = 0; c < C; c++) {
  const [N, M] = input[idx++].split(" ").map(Number);
  const board = [];
  for (let i = 0; i < N; i++) {
    board.push(input[idx++]);
  }

  const valid = [];
  for (let i = 0; i < N; i++) {
    let mask = 0;
    for (let j = 0; j < M; j++) {
      if (board[i][j] === ".") mask |= 1 << j;
    }
    valid.push(mask);
  }

  const dp = Array.from({ length: N }, () => Array(1 << M).fill(-1));

  function countBits(x) {
    let cnt = 0;
    while (x > 0) {
      cnt += x & 1;
      x >>= 1;
    }
    return cnt;
  }

  function solve(row, prev) {
    if (row === N) return 0;
    if (dp[row][prev] !== -1) return dp[row][prev];

    let res = 0;
    for (let cur = 0; cur < 1 << M; cur++) {
      if ((cur & valid[row]) !== cur) continue;
      if (cur & (cur << 1)) continue; //
      if (cur & (prev << 1)) continue; //
      if (cur & (prev >> 1)) continue; //

      res = Math.max(res, countBits(cur) + solve(row + 1, cur));
    }
    return (dp[row][prev] = res);
  }

  console.log(solve(0, 0));
}
