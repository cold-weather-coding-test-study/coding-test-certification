const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const [N, M] = input[0].split(" ").map(Number);

let minPackage = Infinity;
let minSingle = Infinity;

for (let i = 1; i <= M; i++) {
  const [pack, single] = input[i].split(" ").map(Number);
  minPackage = Math.min(minPackage, pack);
  minSingle = Math.min(minSingle, single);
}

//1: 패키지만
const case1 = Math.ceil(N / 6) * minPackage;

//2: 낱개만
const case2 = N * minSingle;

//3: 패키지 + 낱개
const case3 = Math.floor(N / 6) * minPackage + (N % 6) * minSingle;

// 세 가지 경우 중 최소값
console.log(Math.min(case1, case2, case3));
