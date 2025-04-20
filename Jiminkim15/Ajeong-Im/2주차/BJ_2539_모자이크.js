function solve(input) {
  const lines = input.trim().split("\n");
  let idx = 0;

  const [rows, cols] = lines[idx++].split(" ").map(Number);
  const paperCount = parseInt(lines[idx++], 10); // 사용할 색종이 개수
  const mistakeCount = parseInt(lines[idx++], 10); // 잘못 칠한 칸 개수

  const mistakes = [];
  for (let i = 0; i < mistakeCount; i++) {
    const [r, c] = lines[idx++].split(" ").map(Number);
    mistakes.push({ r, c });
  }

  mistakes.sort((a, b) => a.c - b.c);

  let maxRow = 0;
  let minCol = Infinity;
  let maxCol = -Infinity;
  for (const mistake of mistakes) {
    maxRow = Math.max(maxRow, mistake.r);
    minCol = Math.min(minCol, mistake.c);
    maxCol = Math.max(maxCol, mistake.c);
  }

  let low = maxRow;
  let high = Math.max(maxRow, maxCol - minCol + 1);
  let answer = high;

  while (low <= high) {
    const mid = Math.floor((low + high) / 2);
    if (canCover(mistakes, paperCount, mid)) {
      answer = mid;
      high = mid - 1;
    } else {
      low = mid + 1;
    }
  }

  return answer.toString();
}

function canCover(mistakes, K, L) {
  for (const mistake of mistakes) {
    if (mistake.r > L) {
      return false;
    }
  }

  let usedPaper = 0; // 사용한 색종이 수
  let i = 0;

  while (i < mistakes.length) {
    usedPaper++;
    const coverEnd = mistakes[i].c + L - 1;
    while (i < mistakes.length && mistakes[i].c <= coverEnd) {
      i++;
    }
    if (usedPaper > K) return false;
  }

  return true;
}

if (typeof process !== "undefined" && process.stdin) {
  let input = "";
  process.stdin.on("data", (chunk) => (input += chunk));
  process.stdin.on("end", () => {
    console.log(solve(input));
  });
}
