const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const T = parseInt(input[0]);
const testCases = input.slice(1).map(Number);

const count = Array.from({ length: 41 }, () => [0, 0]);

// 초기값 설정
count[0] = [1, 0]; // fibonacci(0): 0 출력 1번, 1 출력 0번
count[1] = [0, 1]; // fibonacci(1): 0 출력 0번, 1 출력 1번

// DP 테이블 구성
for (let i = 2; i <= 40; i++) {
  count[i][0] = count[i - 1][0] + count[i - 2][0];
  count[i][1] = count[i - 1][1] + count[i - 2][1];
}

// 각 테스트 케이스 출력
let result = "";
for (let n of testCases) {
  result += `${count[n][0]} ${count[n][1]}\n`;
}
console.log(result.trim());
