const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const [N, M] = input[0].split(" ").map(Number);
const A = input[1].split(" ").map(Number);

let lo = 1;
let hi = Math.max(...A) * M;
let answer = hi;

while (lo <= hi) {
  const mid = Math.floor((lo + hi) / 2);

  // mid 시간 동안 만들 수 있는 풍선 총 개수
  const total = A.reduce((sum, time) => sum + Math.floor(mid / time), 0);

  if (total >= M) {
    answer = mid;
    hi = mid - 1; // 더 빠른 시간 가능한지 확인
  } else {
    lo = mid + 1; // 더 많은 시간이 필요함
  }
}

console.log(answer);
