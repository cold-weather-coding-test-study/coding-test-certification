// https://www.acmicpc.net/problem/11725
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const nodes = parseInt(input[0], 10);
const connectionNodes = input.splice(1).map((line) => {
  const [node1, node2] = line.split(" ").map((v) => parseInt(v, 10));
  return [node1, node2];
});
const parentNode = Array.from({ length: nodes + 1 }, () => []);
const result = Array(nodes + 1).fill(0);

for (const [node1, node2] of connectionNodes) {
  parentNode[node1].push(node2);
  parentNode[node2].push(node1);
}

function DFS(current, parent) {
  result[current] = parent;

  for (const next of parentNode[current]) {
    if (result[next] === 0) {
      DFS(next, current);
    }
  }
}

DFS(1, 0);
console.log(result.splice(2).join("\n"));
