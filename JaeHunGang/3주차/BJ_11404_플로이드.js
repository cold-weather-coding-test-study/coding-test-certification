const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const numCities = parseInt(input[0], 10); // 도시의 수
const numBuses = parseInt(input[1], 10); // 버스의 수

// busCost 양의 무한대로 초기화
const dist = Array.from({ length: numCities + 1 }, () =>
  Array(numCities + 1).fill(Infinity)
);

for (let i = 1; i <= numCities; i++) dist[i][i] = 0; // 시작 도시 = 도착 도시, 비용 = 0

for (let i = 2; i < numBuses + 2; i++) {
  // a : 시작 도시, b : 도착 도시, c : atob 비용
  const [fromCity, toCity, busCost] = input[i]
    .split(" ")
    .map((v) => parseInt(v, 10));
  // 2차원 배열에 비용 저장
  dist[fromCity][toCity] = Math.min(dist[fromCity][toCity], busCost);
}

for (let k = 1; k <= numCities; k++) {
  // 비용
  for (let i = 1; i <= numCities; i++) {
    // 시작 도시
    for (let j = 1; j <= numCities; j++) {
      // 도착 도시
      if (dist[i][j] > dist[i][k] + dist[k][j]) {
        // atob > atok + ktob
        dist[i][j] = dist[i][k] + dist[k][j];
      }
    }
  }
}

let result = "";
for (let i = 1; i <= numCities; i++) {
  for (let j = 1; j <= numCities; j++) {
    result += (dist[i][j] === Infinity ? 0 : dist[i][j]) + " ";
  }
  result += "\n";
}
console.log(result);
