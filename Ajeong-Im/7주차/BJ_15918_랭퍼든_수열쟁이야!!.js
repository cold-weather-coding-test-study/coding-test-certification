const filePath =
  process.platform === "linux" ? "/dev/stdin" : "./Javascript/input.txt";
const input = require("fs").readFileSync(filePath).toString().trim();
const [n, x, y] = input.split(" ").map(Number);
const arr = Array(2 * n + 1).fill(0);
let answer = 0;
arr[x] = y - x - 1;
arr[y] = y - x - 1;
combination(1);
console.log(answer);

function combination(num) {
  if (num === y - x - 1) {
    combination(num + 1);
    return;
  }
  if (num > n) {
    answer++;
    return;
  }
  for (let i = 1; i < 2 * n - num; i++) {
    if (arr[i] === 0 && arr[i + num + 1] === 0) {
      arr[i] = num;
      arr[i + num + 1] = num;
      combination(num + 1);
      arr[i] = 0;
      arr[i + num + 1] = 0;
    }
  }
}
