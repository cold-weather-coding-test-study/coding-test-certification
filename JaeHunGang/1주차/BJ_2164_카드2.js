// 카드 2
const input = require("fs").readFileSync("/dev/stdin").toString().trim();

let numArr = [];

for (let i = 1; i <= input; i++) {
  numArr.push(i);
}

// while (numArr.length > 1) {
//   // 첫번째 요소 제거 [2, 3, 4, 5, 6]
//   numArr.shift();

//   // 그 다음 요소 저장
//   let firstNum = numArr[0];

//   // 기존 배열의 마지막에 추가 [2, 3, 4, 5, 6, 2]
//   numArr.push(firstNum);

//   // 첫번재 요소 제거 [3, 4, 5, 6, 2]
//   numArr.shift();
// }

// // while문 간단하게
// while (numArr.length > 1) {
//   numArr.shift(); // 첫 번째 요소 제거
//   numArr.push(numArr.shift()); // 그다음 첫 번째 요소를 끝에 추가
// }

// 하지만 이 두가지 방법 모두 시간초과 -> Queue 방법 사용!

let front = 0;

// 참조 가능한 배열 조정
// 6-0 / 7-2 / 8-4 / 9-6 / 10-8 / 11-10(while문 종료)
while (numArr.length - front > 1) {
  front++; // front 포인터 증가 ( 인덱스 = 0 인 요소를 사용하지 않을거니까 )

  // 해당 포인터에 해당하는 값 배열 마지막에 추가
  numArr.push(numArr[front]);

  front++; // front 포인터 증가
}

console.log(numArr[front]);
