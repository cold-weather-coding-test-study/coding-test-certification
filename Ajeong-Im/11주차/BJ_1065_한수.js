const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim();

const N = parseInt(input);
let count = 0;

function isHansoo(num) {
  if (num < 100) return true;

  const digits = String(num).split("").map(Number);
  const diff = digits[0] - digits[1];

  for (let i = 1; i < digits.length - 1; i++) {
    if (digits[i] - digits[i + 1] !== diff) {
      return false;
    }
  }
  return true;
}

for (let i = 1; i <= N; i++) {
  if (isHansoo(i)) count++;
}

console.log(count);
