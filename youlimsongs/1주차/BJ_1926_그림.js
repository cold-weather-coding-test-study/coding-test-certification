const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt';
const input = require('fs')
  .readFileSync(filePath)
  .toString()
  .trim()
  .split('\n');

const [n, m] = input.shift().split(' ').map(Number);

const map = [];
for (const i of input) {
  const row = i.split(' ').map(Number);
  map.push(row);
}

const dx = [1, -1, 0, 0];
const dy = [0, 0, 1, -1];

let maxSize = 0;

function dfs(h, w) {
  let size = 0;
  map[h][w] = 0; // 방문 처리
  const que = [[h, w]];

  while (que.length) {
    const [y, x] = que.shift();
    size++;

    for (let i = 0; i < 4; i++) {
      const nx = x + dx[i];
      const ny = y + dy[i];

      if (nx < 0 || nx >= m || ny < 0 || ny >= n || map[ny][nx] === 0) continue;

      map[ny][nx] = 0;
      que.push([ny, nx]);
    }
  }

  maxSize = Math.max(size, maxSize);
}

let pic = 0;

for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    if (map[i][j]) {
      dfs(i, j);
      pic++;
    }
  }
}

console.log(`${pic}\n${maxSize}`);
