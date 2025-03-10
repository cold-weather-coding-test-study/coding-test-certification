import * as fs from "fs";

// 리눅스 환경이면 '/dev/stdin', 그 외 환경이면 './input.txt'에서 입력을 읽어옵니다.
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath, "utf8").trim().split("\n");

// 단어 개수 저장
const T = parseInt(input[0].trim());

const output: string[] = [];

for (let i = 1; i <= T; i++) {
  const word = input[i].trim();

  // 1. 단어의 문자를 사전순으로 정렬하기
  const arr = word.split("").sort();

  // 정렬된 결과 출력
  output.push(arr.join(""));

  // 2. 정렬된 문자로 애너그램 생성
  while (nextPermutation(arr)) {
    output.push(arr.join(""));
  }
}

console.log(output.join("\n"));

function nextPermutation(arr: string[]): boolean {
  let i = arr.length - 2;
  while (i >= 0 && arr[i] >= arr[i + 1]) {
    i--;
  }
  if (i < 0) return false;

  let j = arr.length - 1;
  while (arr[j] <= arr[i]) {
    j--;
  }

  [arr[i], arr[j]] = [arr[j], arr[i]];

  let left = i + 1;
  let right = arr.length - 1;
  while (left < right) {
    [arr[left], arr[right]] = [arr[right], arr[left]];
    left++;
    right--;
  }
  return true;
}
