// 최소 힙 구현
class MinHeap {
  constructor() {
    this.heap = [];
  }

  push(value) {
    this.heap.push(value);
    this.bubbleUp();
  }

  pop() {
    if (this.heap.length <= 1) return this.heap.pop();
    const min = this.heap[0];
    this.heap[0] = this.heap.pop();
    this.bubbleDown();
    return min;
  }

  bubbleUp() {
    let index = this.heap.length - 1;
    const inserted = this.heap[index];

    while (index > 0) {
      const parentIndex = Math.floor((index - 1) / 2);
      if (this.heap[parentIndex] <= inserted) break;
      this.heap[index] = this.heap[parentIndex];
      index = parentIndex;
    }

    this.heap[index] = inserted;
  }

  bubbleDown() {
    let index = 0;
    const length = this.heap.length;
    const root = this.heap[0];

    while (true) {
      let left = index * 2 + 1;
      let right = index * 2 + 2;
      let smallest = index;

      if (left < length && this.heap[left] < this.heap[smallest]) {
        smallest = left;
      }

      if (right < length && this.heap[right] < this.heap[smallest]) {
        smallest = right;
      }

      if (smallest === index) break;

      [this.heap[index], this.heap[smallest]] = [this.heap[smallest], this.heap[index]];
      index = smallest;
    }

    this.heap[index] = root;
  }

  size() {
    return this.heap.length;
  }
}

// 문제 풀이
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const T = parseInt(input[0], 10);
let line = 1;

for (let t = 0; t < T; t++) {
  const K = parseInt(input[line++], 10);
  const files = input[line++].split(" ").map(Number);

  const minHeap = new MinHeap();

  for (let file of files) {
    minHeap.push(file);
  }

  let totalCost = 0;

  while (minHeap.size() > 1) {
    const a = minHeap.pop();
    const b = minHeap.pop();
    const sum = a + b;
    totalCost += sum;
    minHeap.push(sum);
  }

  console.log(totalCost);
}