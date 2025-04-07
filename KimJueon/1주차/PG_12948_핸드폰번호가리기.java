class Solution {
  public String solution(String phone_number) {
    int length = phone_number.length();
    String sub = phone_number.substring(length - 4, length);

    return String.format("%" + length + "s", sub).replace(' ', '*');
  }
}