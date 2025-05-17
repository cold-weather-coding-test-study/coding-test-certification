const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const N = parseInt(input[0]);
const str = input.slice(1);

function isNum(c) {
  return "0" <= c && c <= "9";
}

str.sort((s1, s2) => {
  const len1 = s1.length;
  const len2 = s2.length;
  let i = 0,
    j = 0;

  while (i < len1 && j < len2) {
    const c1 = s1[i];
    const c2 = s2[j];
    const numeric1 = isNum(c1);
    const numeric2 = isNum(c2);

    if (numeric1 && numeric2) {
      let cnt1 = 0,
        cnt2 = 0;
      while (i < len1 && s1[i] === "0") {
        cnt1++;
        i++;
      }
      while (j < len2 && s2[j] === "0") {
        cnt2++;
        j++;
      }

      let sb1 = "";
      let sb2 = "";
      while (i < len1 && isNum(s1[i])) {
        sb1 += s1[i++];
      }
      while (j < len2 && isNum(s2[j])) {
        sb2 += s2[j++];
      }
      i--;
      j--;

      if (sb1.length > sb2.length) return 1;
      if (sb1.length < sb2.length) return -1;
      for (let a = 0; a < sb1.length; a++) {
        if (sb1[a] !== sb2[a]) {
          return sb1[a].charCodeAt(0) - sb2[a].charCodeAt(0);
        }
      }
      if (cnt1 !== cnt2) return cnt1 - cnt2;
    } else if (!numeric1 && !numeric2) {
      const ch1 = s1[i];
      const ch2 = s2[j];
      if (ch1 === ch2) {
        i++;
        j++;
        continue;
      }

      const isUpper1 = ch1 < "a";
      const isUpper2 = ch2 < "a";

      const n1 = isUpper1
        ? ch1.charCodeAt(0) - "A".charCodeAt(0)
        : ch1.charCodeAt(0) - "a".charCodeAt(0);
      const n2 = isUpper2
        ? ch2.charCodeAt(0) - "A".charCodeAt(0)
        : ch2.charCodeAt(0) - "a".charCodeAt(0);

      if (n1 !== n2) return n1 - n2;
      return isUpper1 ? -1 : 1;
    } else {
      return numeric1 ? -1 : 1;
    }

    i++;
    j++;
  }

  if (i < len1) return 1;
  if (j < len2) return -1;
  return 0;
});

console.log(str.join("\n"));
