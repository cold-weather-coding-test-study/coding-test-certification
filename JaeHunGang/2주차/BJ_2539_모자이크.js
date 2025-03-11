const input = require("fs").readFileSync("/dev/stdin").toString().trim().split("\n");

const [rows, cols] = input[0].split(" ");
const nConfetties = input[1];
const nWrongSpaces = input[2];
