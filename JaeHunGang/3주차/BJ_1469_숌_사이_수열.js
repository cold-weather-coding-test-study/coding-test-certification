// 입력 처리
const input = require('fs').readFileSync('/dev/stdin', 'utf8').trim().split('\n');
const N = parseInt(input[0], 10);
const sequenceX = input[1].split(' ').map(Number).sort((a, b) => a - b);

// 초기화
const sequenceS = Array(2 * N).fill(-1);
const visited = Array(N).fill(false);

// 백트래킹 함수
function backtrack(depth) {
  // 수열을 완성한 경우 출력하고 프로그램 종료
  if (depth === 2 * N) {
    console.log(sequenceS.join(' '));
    process.exit(0);
  }

  // 이미 숫자가 배치된 위치는 건너뜀
  if (sequenceS[depth] !== -1) {
    backtrack(depth + 1);
    return;
  }

  // 숫자 배치 시도
  for (let i = 0; i < N; i++) {
    const num = sequenceX[i];
    const nextPos = depth + num + 1;

    if (!visited[i] && nextPos < 2 * N && sequenceS[depth] === -1 && sequenceS[nextPos] === -1) {
      // 숫자 배치
      sequenceS[depth] = num;
      sequenceS[nextPos] = num;
      visited[i] = true;

      backtrack(depth + 1);

      // 배치 취소 (백트래킹)
      sequenceS[depth] = -1;
      sequenceS[nextPos] = -1;
      visited[i] = false;
    }
  }
}

// 백트래킹 시작
backtrack(0);

// 수열을 찾지 못한 경우 -1 출력
console.log(-1);