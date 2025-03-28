const input = require("fs").readFileSync("/dev/stdin").toString().split("\n");

const bNum = parseInt(input[0], 10); // 빌딩 갯수 변수
const bHei = input[1].split(" ").map((num) => parseInt(num, 10)); // 빌딩 높이 배열

function getVisibleCount(index) {
  let count = 0;

  // 왼쪽 빌딩들 확인
  let minSlope = Infinity;
  for (let i = index - 1; i >= 0; i--) {
    const slope = (bHei[index] - bHei[i]) / (index - i);
    if (slope < minSlope) {
      minSlope = slope;
      count++;
    }
  }

  // 오른쪽 빌딩들 확인
  let maxSlope = -Infinity;
  for (let i = index + 1; i < bNum; i++) {
    const slope = (bHei[i] - bHei[index]) / (i - index);
    if (slope > maxSlope) {
      maxSlope = slope;
      count++;
    }
  }

  return count;
}

let maxVisible = 0;
for (let i = 0; i < bNum; i++) {
  const visibleCount = getVisibleCount(i);
  if (visibleCount > maxVisible) {
    maxVisible = visibleCount;
  }
}

console.log(maxVisible);
