const fs = require("fs");
const [s, N, K, R1, R2, C1, C2] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split(" ")
  .map(Number);

// 가운데 시작 인덱스 계산
const margin = (N - K) / 2;

function isBlack(r, c, level) {
  if (level === 0) return false;

  const len = Math.pow(N, level - 1);
  const row = Math.floor(r / len) % N;
  const col = Math.floor(c / len) % N;

  if (row >= margin && row < margin + K && col >= margin && col < margin + K) {
    return true;
  }

  return isBlack(r, c, level - 1);
}

let output = "";

for (let r = R1; r <= R2; r++) {
  for (let c = C1; c <= C2; c++) {
    output += isBlack(r, c, s) ? "1" : "0";
  }
  output += "\n";
}

process.stdout.write(output);
