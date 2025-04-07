import * as fs from "fs";

// 리눅스 환경이면 '/dev/stdin', 아니면 './input.txt' 파일에서 입력을 읽어옵니다.
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath, "utf8").trim().split("\n");

const N = parseInt(input[0]); // 건물 개수
const buildings = [0].concat(input[1].split(" ").map(Number));

// 각 건물에서 볼 수 있는 건물의 개수 저장
let answer = Array(N + 1).fill(0);

for (let i = 1; i <= N; i++) {
  let rightMax = -Infinity; // 오른쪽 방향에서 현재까지의 최대 기울기
  let leftMin = Infinity; // 왼쪽 방향에서 현재까지의 최소 기울기
  const x1 = i;
  const y1 = buildings[i];

  // 오른쪽 탐색
  for (let j = i + 1; j <= N; j++) {
    const x2 = j;
    const y2 = buildings[j];
    const incline = (y2 - y1) / (x2 - x1);
    if (incline > rightMax) {
      answer[i]++;
      rightMax = incline;
    }
  }

  // 왼쪽 탐색
  for (let j = i - 1; j >= 1; j--) {
    const x2 = j;
    const y2 = buildings[j];
    const incline = (y2 - y1) / (x2 - x1);
    if (incline < leftMin) {
      answer[i]++;
      leftMin = incline;
    }
  }
}

console.log(Math.max(...answer));
