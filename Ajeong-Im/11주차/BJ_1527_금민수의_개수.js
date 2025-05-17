const fs = require("fs");
const [A, B] = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split(" ")
  .map(Number);

function generateGeumminsu(current, limit, result) {
  if (current !== "") {
    const num = parseInt(current);
    if (num > limit) return;
    result.push(num);
  }

  if (current.length > 10) return;

  generateGeumminsu(current + "4", limit, result);
  generateGeumminsu(current + "7", limit, result);
}

function countGeumminsu(A, B) {
  const result = [];
  generateGeumminsu("", B, result);

  let count = 0;
  for (const num of result) {
    if (num >= A && num <= B) count++;
  }
  return count;
}

console.log(countGeumminsu(A, B));
