const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

// K(이미 가진 랜선 갯수) = 4, N(필요한 랜선 갯수) = 11
const [K, N] = input[0].split(" ").map((value) => parseInt(value, 10));

const lanCables = input
  .slice(1)
  .map((value) => parseInt(value, 10));

let start = 1; // 최소 갯수
let end = Math.max(...lanCables) // 최대 갯수
let result = 0;

while(start <= end) {
  let mid = Math.floor((start + end) / 2);
  let count = lanCables.reduce((acc, cur) => acc + Math.floor(cur/mid), 0);

  if (count >= N) {
    result = mid;
    start = mid + 1;
  } else {
    end = mid - 1;
  }
}

console.log(result);
