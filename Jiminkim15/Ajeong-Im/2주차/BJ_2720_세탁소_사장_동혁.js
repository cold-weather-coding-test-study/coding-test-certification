function solve(input) {
  const lines = input.trim().split("\n");
  let idx = 0;
  const T = parseInt(lines[idx++], 10);
  let output = "";
  for (let i = 0; i < T; i++) {
    let C = parseInt(lines[idx++], 10);
    const quarters = Math.floor(C / 25);
    C %= 25;
    const dimes = Math.floor(C / 10);
    C %= 10;
    const nickels = Math.floor(C / 5);
    C %= 5;
    const pennies = C;
    output += `${quarters} ${dimes} ${nickels} ${pennies}\n`;
  }
  return output.trim();
}

if (typeof process !== "undefined" && process.stdin) {
  let input = "";
  process.stdin.on("data", (chunk) => (input += chunk));
  process.stdin.on("end", () => console.log(solve(input)));
}
