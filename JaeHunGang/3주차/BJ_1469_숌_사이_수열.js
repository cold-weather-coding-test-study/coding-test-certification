const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const N = parseInt(input[0], 10);
const sequenceX = input[1].split(" ").map((v) => parseInt(v, 10));
const sequenceS = [];

function backtrack(depth){
  
}
