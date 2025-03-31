// https://www.acmicpc.net/problem/5427
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

const t = +input.shift();

for (let i = 0; i < t; i++) {
  const map = [];
  let fire = [];
  let x, y;

  const [c, r] = input.shift().split(' ').map(Number);
  for (let i = 0; i < r; i++) {
    const tmp = input.shift().split('');
    map.push(tmp);

    for (let j = 0; j < c; j++) {
      if (tmp[j] === '*') fire.push([i, j]);
      if (tmp[j] === '@') {
        x = j;
        y = i;
      }
    }
  }

  let dx = [0, 0, 1, -1];
  let dy = [1, -1, 0, 0];
  let queue = [[y, x, 0]];
  let answer = 0,
    cnt = 1;

  function bfs() {
    while (queue.length) {
      //불 번짐처리
      let fireCount = fire.length;
      let tmp = [];
      for (let i = 0; i < fireCount; i++) {
        const [fy, fx] = fire.pop();
        for (let d = 0; d < 4; d++) {
          const nfy = fy + dy[d];
          const nfx = fx + dx[d];
          if (nfx < 0 || nfx >= c || nfy < 0 || nfy >= r) continue;
          if (map[nfy][nfx] === '.' || map[nfy][nfx] === '@') {
            map[nfy][nfx] = '*';
            tmp.push([nfy, nfx]);
          }
        }
      }
      fire = tmp;

      const qLen = queue.length;
      let newQueue = [];
      for (let k = 0; k < qLen; k++) {
        //상근 이동
        const [y, x, cnt] = queue.pop();
        map[y][x] = '+';

        if (y === 0 || y === r - 1 || x === 0 || x === c - 1) {
          return cnt + 1;
        }

        for (let i = 0; i < 4; i++) {
          let nx = dx[i] + x;
          let ny = dy[i] + y;

          if (nx < 0 || nx >= c || ny < 0 || ny >= r) continue;
          if (map[ny][nx] === '.') {
            map[ny][nx] = '+'; //방문처리..?
            newQueue.push([ny, nx, cnt + 1]);
          }
        }
      }
      queue = newQueue;
    }
    return 0;
  }

  answer = bfs();
  console.log(answer === 0 ? 'IMPOSSIBLE' : answer);
}
