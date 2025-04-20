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

const n = +input.shift();
let map = Array.from({ length: n + 1 }, () => []);

for (let i of input) {
  let [a, b] = i.split(' ').map(Number);

  map[a].push(b);
  map[b].push(a);
}

let queue = [1];
let p = Array(n + 1).fill(0);
let visited = Array(n + 1).fill(0);
visited[1] = 1;
//map 그려서 bfs돌면서 부모배열-자식인덱스에 본인 저장
function bfs() {
  while (queue.length) {
    const parent = queue.shift();
    for (let n of map[parent]) {
      if (visited[n] === 0) {
        visited[n] = 1;
        p[n] = parent;
        queue.push(n);
      }
    }
  }
}

bfs();
console.log(p.slice(2, n + 1).join('\n'));
