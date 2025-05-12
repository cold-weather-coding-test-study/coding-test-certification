const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const [N, M] = input[0].split(" ").map(Number);
const targets = input[1].split(" ").map(Number);

let deque = Array.from({ length: N }, (_, i) => i + 1);
let count = 0;

for (const target of targets) {
  const idx = deque.indexOf(target);
  const left = idx;
  const right = deque.length - idx;

  if (left <= right) {
    // 왼쪽 회전
    count += left;
    while (deque[0] !== target) {
      deque.push(deque.shift());
    }
  } else {
    // 오른쪽 회전
    count += right;
    while (deque[0] !== target) {
      deque.unshift(deque.pop());
    }
  }
  // 맨 앞 제거
  deque.shift();
}

console.log(count);
