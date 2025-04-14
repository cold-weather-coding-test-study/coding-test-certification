const path = require('path');
const filePath =
  process.platform === 'linux'
    ? '/dev/stdin'
    : path.join(__dirname, 'input.txt');
const input = require('fs')
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n');

const k = +input.shift();
const [m, n] = input.shift().split(' ').map(Number);
const graph = [];
for (let i = 0; i < n; i++) {
  graph.push(input.shift().split(' ').map(Number));
}

const dist = [
  [1, 0],
  [0, 1],
  [-1, 0],
  [0, -1],
];

const horse = [
  [-2, -1],
  [-2, 1],
  [-1, -2],
  [-1, 2],
  [2, -1],
  [2, 1],
  [1, -2],
  [1, 2],
];

function bfs() {
  const visited = new Array(n);
  for (let i = 0; i < n; i++) {
    visited[i] = new Array(m);
    for (let j = 0; j < m; j++) {
      visited[i][j] = new Array(k + 1).fill(0);
    }
  }

  const queue = [];
  queue.push([0, 0, 0]);
  visited[0][0][0] = 1;

  while (queue.length > 0) {
    const [x, y, z] = queue.shift();

    if (x === n - 1 && y === m - 1) {
      return visited[x][y][z] - 1;
    }

    for (const [i, j] of dist) {
      const dx = x + i;
      const dy = y + j;
      if (
        dx >= 0 &&
        dx < n &&
        dy >= 0 &&
        dy < m &&
        visited[dx][dy][z] === 0 &&
        graph[dx][dy] === 0
      ) {
        visited[dx][dy][z] = visited[x][y][z] + 1;
        queue.push([dx, dy, z]);
      }
    }

    if (z < k) {
      for (const [hi, hj] of horse) {
        const hx = x + hi;
        const hy = y + hj;
        if (
          hx >= 0 &&
          hx < n &&
          hy >= 0 &&
          hy < m &&
          graph[hx][hy] === 0 &&
          visited[hx][hy][z + 1] === 0
        ) {
          visited[hx][hy][z + 1] = visited[x][y][z] + 1;
          queue.push([hx, hy, z + 1]);
        }
      }
    }
  }

  return -1;
}

console.log(bfs());
