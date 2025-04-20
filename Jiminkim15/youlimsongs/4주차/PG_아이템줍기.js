function solution(rectangle, characterX, characterY, itemX, itemY) {
  characterX *= 2;
  characterY *= 2;
  itemX *= 2;
  itemY *= 2;
  let doubleRec = rectangle.map((rec) => rec.map((r) => r * 2));

  let dx = [1, -1, 0, 0];
  let dy = [0, 0, 1, -1];

  let map = Array.from({ length: 103 }, () => Array(103).fill(0));

  doubleRec.forEach(([x1, y1, x2, y2]) => {
    for (let i = x1; i <= x2; i++) {
      for (let j = y1; j <= y2; j++) {
        if (i === x1 || i === x2 || j === y1 || j === y2) {
          if (map[i][j] === 0) map[i][j] = 1;
        } else map[i][j] = 2; //직사각형 내부2, 테두리1, 외부0
      }
    }
  });

  let queue = [[characterX, characterY, 0]]; //x,y,거리
  map[characterX][characterY] = 0;

  function bfs() {
    while (queue.length) {
      const [x, y, dist] = queue.shift();

      if (x === itemX && y === itemY) return dist / 2;

      for (let i = 0; i < 4; i++) {
        const nx = x + dx[i];
        const ny = y + dy[i];

        if (map[nx][ny] === 1) {
          map[nx][ny] = 0;
          queue.push([nx, ny, dist + 1]);
        }
      }
    }
  }

  let answer = bfs();

  return answer;
}

//https://school.programmers.co.kr/learn/courses/30/lessons/87694
