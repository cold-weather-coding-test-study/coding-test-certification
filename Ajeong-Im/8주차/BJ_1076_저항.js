const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

// 색상표
const colorValue = {
  black: 0,
  brown: 1,
  red: 2,
  orange: 3,
  yellow: 4,
  green: 5,
  blue: 6,
  violet: 7,
  grey: 8,
  white: 9,
};

const colorMultiplier = {
  black: 1,
  brown: 10,
  red: 100,
  orange: 1000,
  yellow: 10000,
  green: 100000,
  blue: 1000000,
  violet: 10000000,
  grey: 100000000,
  white: 1000000000,
};

// 입력
const first = input[0];
const second = input[1];
const third = input[2];

// 계산
const firstValue = colorValue[first];
const secondValue = colorValue[second];
const multiplier = colorMultiplier[third];

// 최종 결과
const resistance = (firstValue * 10 + secondValue) * multiplier;

console.log(resistance);
