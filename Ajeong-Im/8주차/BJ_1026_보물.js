const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const N = Number(input[0]);
const A = input[1].split(" ").map(Number);
const B = input[2].split(" ").map(Number);

const B_with_index = B.map((value, index) => ({ value, index }));
B_with_index.sort((a, b) => b.value - a.value); // B를 내림차순 정렬

A.sort((a, b) => a - b);

const newA = Array(N);
for (let i = 0; i < N; i++) {
  newA[B_with_index[i].index] = A[i];
}

let S = 0;
for (let i = 0; i < N; i++) {
  S += newA[i] * B[i];
}

console.log(S);
