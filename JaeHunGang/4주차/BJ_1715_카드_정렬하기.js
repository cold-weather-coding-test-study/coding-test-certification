// https://www.acmicpc.net/problem/1715
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const N = parseInt(input[0], 10);
const numCardBundles = input.slice(1).map((v) => parseInt(v, 10));

class MinHeap {
  constructor() {
    this.heap = [];
  }

  numCardPush(val) {
    this.heap.push(val);
    this._numCardBubbleUp();
  }

  _numCardBubbleUp() {
    let i = this.heap.length - 1;
    while (i > 0) {
      let parent = Math.floor((i - 1) / 2);
      if (this.heap[i] >= this.heap[parent]) break;
      [this.heap[i], this.heap[parent]] = [this.heap[parent], this.heap[i]];
      i = parent;
    }
  }

  numCardPop() {
    if (this.heap.length === 1) return this.heap.pop();
    const min = this.heap[0];
    this.heap[0] = this.heap.pop();
    this._numCardSinkDown(0);
    return min;
  }

  _numCardSinkDown(i) {
    const length = this.heap.length;
    while (true) {
      let left = 2 * i + 1;
      let right = 2 * i + 2;
      let smallest = i;

      if (left < length && this.heap[left] < this.heap[smallest])
        smallest = left;
      if (right < length && this.heap[right] < this.heap[smallest])
        smallest = right;
      if (smallest === i) break;
      [this.heap[i], this.heap[smallest]] = [this.heap[smallest], this.heap[i]];
      i = smallest;
    }
  }

  numCardSize() {
    return this.heap.length;
  }
}

// 🟡 문제 해결 로직
const heap = new MinHeap();

for (let num of numCardBundles) {
  heap.numCardPush(num); // ✅ 바뀐 함수명 사용
}

let totalCost = 0;

while (heap.numCardSize() > 1) {
  // ✅ 바뀐 함수명 사용
  const a = heap.numCardPop(); // ✅ 바뀐 함수명 사용
  const b = heap.numCardPop(); // ✅ 바뀐 함수명 사용
  const cost = a + b;
  totalCost += cost;
  heap.numCardPush(cost); // ✅ 바뀐 함수명 사용
}

console.log(totalCost);
