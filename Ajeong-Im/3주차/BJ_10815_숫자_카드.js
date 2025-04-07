const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const N = +input[0];
const sanggeunCards = new Set(input[1].split(" ").map(Number));
const M = +input[2];
const query = input[3].split(" ").map(Number);

const result = query.map((num) => (sanggeunCards.has(num) ? 1 : 0));
console.log(result.join(" "));
