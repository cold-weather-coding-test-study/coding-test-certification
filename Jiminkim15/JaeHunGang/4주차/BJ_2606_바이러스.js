// DFS & BFS 유형
// Depth-First Search & Breath-First Search
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const numComs = parseInt(input[0], 10);
const connectionCounts = parseInt(input[1], 10);
const comConnections = input.splice(2).map((line) => {
  const [com1, com2] = line.split(" ").map((v) => parseInt(v, 10));
  return [com1, com2];
});

const graph = Array.from({ length: numComs + 1 }, () => []);

for (const [com1, com2] of comConnections) {
  graph[com1].push(com2); // 그래프의 com1번째 배열에 com2번 컴퓨터 추가
  graph[com2].push(com1); // 그래프의 com2번째 배열에 com1번 컴퓨터 추가
}

const visited = Array(numComs + 1).fill(false);
let count = 0;


function spread(comNumber) {
  visited[comNumber] = true;
  for (const nextCom of graph[comNumber]) {
    if (!visited[nextCom]) {
      count++;
      spread(nextCom);
    }
  }
}

spread(1);
console.log(count);
