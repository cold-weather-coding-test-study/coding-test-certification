const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const N = Number(input[0]);
const A = input[1].split(" ").map(Number);

const indexedA = A.map((val, idx) => [val, idx]);

// 값을 기준으로 정렬, 값이 같다면 인덱스 순
indexedA.sort((a, b) => {
  if (a[0] !== b[0]) return a[0] - b[0];
  return a[1] - b[1];
});

const P = Array(N);

for (let i = 0; i < N; i++) {
  const [_, originalIdx] = indexedA[i];
  P[originalIdx] = i;
}

console.log(P.join(" "));
