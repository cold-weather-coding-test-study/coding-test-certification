const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const [N, M] = input[0].split(" ").map(Number);
const truthInfo = input[1].split(" ").map(Number);
const T = truthInfo[0];
const truthPeople = truthInfo.slice(1);

const parent = Array.from({ length: N + 1 }, (_, i) => i);
const parties = [];

// 유니온 파인드: find
function find(x) {
  if (parent[x] !== x) {
    parent[x] = find(parent[x]);
  }
  return parent[x];
}

// 유니온 파인드: union
function union(x, y) {
  const rootX = find(x);
  const rootY = find(y);
  if (rootX !== rootY) {
    parent[rootY] = rootX;
  }
}

// 파티 연결
for (let i = 2; i < 2 + M; i++) {
  const party = input[i].split(" ").map(Number).slice(1);
  parties.push(party);
  for (let j = 1; j < party.length; j++) {
    union(party[0], party[j]);
  }
}

// 진실 루트 찾기
const truthRoots = new Set(truthPeople.map(find));

// 파티별로 과장 가능한지 판단
let count = 0;
for (const party of parties) {
  const isLiePossible = party.every((person) => !truthRoots.has(find(person)));
  if (isLiePossible) count++;
}

console.log(count);
