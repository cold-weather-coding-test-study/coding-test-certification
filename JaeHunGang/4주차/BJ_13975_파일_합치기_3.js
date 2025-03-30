//https://www.acmicpc.net/problem/13975
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const T = parseInt(input[0], 10);
let line = 1;

for (let i = 0; i < T; i++) {
  const K = parseInt(input[0], 10);
  const files = input[line++].split(" ").map((v) => parseInt(v, 10));

  const minHeap = [];
  for (let file of files) {
    minHeap.push(file);
  }
  minHeap.sort((a, b) => a - b);

  let totalCost = 0;

  while (minHeap.length > 1) {
    const a = minHeap.shift();
    const b = minHeap.shift();
    sum = a + b;
    totalCost += sum;

    minHeap.push(sum);
    minHeap.sort((a, b) => a - b);
  }
  console.log(totalCost);
}
