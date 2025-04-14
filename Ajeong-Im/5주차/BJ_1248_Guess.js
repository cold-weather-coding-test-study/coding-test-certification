const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const n = Number(input[0]);
const s = input[1];

const sign = Array.from({ length: n }, () => Array(n).fill(""));
let idx = 0;

// sign[i][j]에 부호 저장
for (let i = 0; i < n; i++) {
  for (let j = i; j < n; j++) {
    sign[i][j] = s[idx++];
  }
}

const result = Array(n).fill(0);

// i번째까지의 수열이 유효한지 확인
function isValid(i) {
  let sum = 0;
  for (let j = i; j >= 0; j--) {
    sum += result[j];
    const expected = sign[j][i];
    if (
      (expected === "+" && sum <= 0) ||
      (expected === "-" && sum >= 0) ||
      (expected === "0" && sum !== 0)
    ) {
      return false;
    }
  }
  return true;
}

// 백트래킹
function dfs(index) {
  if (index === n) {
    console.log(result.join(" "));
    process.exit(); // 하나만 찾으면 바로 종료
  }

  for (let num = -10; num <= 10; num++) {
    result[index] = num;
    if (isValid(index)) {
      dfs(index + 1);
    }
  }
}

dfs(0);
