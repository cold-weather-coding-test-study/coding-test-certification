const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const nWords = Number(input[0]); // 영단어 갯수
const result = []; // 결과 저장 배열

// Next Permutation, 순열
const np = (str) => {
  let wordLength = str.length; // 현재 영단어 길이

  let i = wordLength - 1; // 뒤에서부터 시작
  while (i > 0 && str[i - 1] >= str[i]) i--; // abc, b >= c, 실행 X / acb, c >= b, i--;

  if (i == 0) return false; // 순열이 없을때

  let j = wordLength - 1; // 뒤에서부터 시작
  while (str[i - 1] >= str[j]) j--; // i-1 보다 작은 알파벳

  [str[i - 1], str[j]] = [str[j], str[i - 1]]; // 순서 바꾸기, abc -> acb

  let k = wordLength - 1; // 뒤에서부터 시작
  while (i < k) {
    [str[i], str[k]] = [str[k], str[i]]; // acb -> bac
    i++; // 증가
    k--; // 감소 함수 종료
  }

  return true;
};

for (let i = 1; i <= nWords; i++) {
  let str = [...input[i]].sort(); // 알파벳순 정렬
  do {
    result.push(str.join(""));
  } while (np(str));
}

console.log(result.join("\n"));
