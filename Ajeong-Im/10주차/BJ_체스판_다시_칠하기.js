const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const [N, M] = input[0].split(" ").map(Number);
const board = input.slice(1);

let minPaint = Infinity;

for (let i = 0; i <= N - 8; i++) {
  for (let j = 0; j <= M - 8; j++) {
    let paint1 = 0; // 왼쪽 위 'W' 시작
    let paint2 = 0; // 왼쪽 위 'B' 시작

    for (let x = i; x < i + 8; x++) {
      for (let y = j; y < j + 8; y++) {
        const current = board[x][y];
        const shouldBeW = (x + y) % 2 === 0 ? "W" : "B";
        const shouldBeB = (x + y) % 2 === 0 ? "B" : "W";

        if (current !== shouldBeW) paint1++;
        if (current !== shouldBeB) paint2++;
      }
    }

    minPaint = Math.min(minPaint, paint1, paint2);
  }
}

console.log(minPaint);
