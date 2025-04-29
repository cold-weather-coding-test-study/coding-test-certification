const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

// 입력값 처리
const N = Number(input[0]);
const tasks = input.slice(1).map((line) => {
  const [T, S] = line.split(" ").map(Number);
  return { T, S };
});

tasks.sort((a, b) => a.S - b.S); // 종료시간 기준으로 정렬

let time = tasks[N - 1].S; // 마지막 작업의 종료시간

for (let i = N - 1; i >= 0; i--) {
  const { T, S } = tasks[i];
  time = Math.min(time, S);
  time -= T;
  if (time < 0) {
    console.log(-1);
    return;
  }
}

console.log(time);
