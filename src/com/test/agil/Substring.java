package com.test.agil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Substring {

	public static void main(String[] args) {
		Substring obj = new Substring();
		String input = "Mississippi";
		Set<String> result = new HashSet<String>();
		System.out.println("=======1st");
		result = obj.getsubstring(input);
		for (String s : result) {
			System.out.println(s);
		}
		System.out.println("=======2nd");
		result = obj.secondMethod(input);
		for (String s : result) {
			System.out.println(s);
		}
	}

	/* method 1 */
	public Set<String> getsubstring(String input) {
		Set<String> result = new HashSet<String>();
		for (int i = 0; i < input.length(); i++) {
			for (int j = input.length() - 1; j > i; j--) {
				if (isvalid(input.substring(i, j))) {
					result.add(input.substring(i, j));
				}
			}
		}
		return result;

	}

	public boolean isvalid(String sub) {
		int[] arr = new int[256];
		boolean flag = false;
		for (int i = 0; i < sub.length(); i++) {
			if (arr[sub.charAt(i)] < 1) {
				arr[sub.charAt(i)] = arr[sub.charAt(i)] + 1;
			}else if (arr[sub.charAt(i)] == 1) {
					if (flag) {
						return false;
					} else {
						flag = true;
						arr[sub.charAt(i)] = arr[sub.charAt(i)] + 1;
					}
			} else {
				return false;
			}

		}
		return flag;

	}


	/* method 2 */

	public Set<String> secondMethod(String str) {
		Set<String> returnArr = new HashSet<String>();
		if (str.isEmpty() || str.length() <= 1)
			return returnArr;
		// finding longest and getting start, occ1, occ2, end
		int start = 0;
		int end = 0;
		int occ1 = -1;
		int occ2 = -1;
		int[] arr = new int[256];
		Arrays.fill(arr, -1);

		for (int i = 0; i < str.length(); i++) {

			if (arr[str.charAt(i)] == -1) {
				arr[str.charAt(i)] = i;
			} else if (arr[str.charAt(i)]  > 0) {
				if(occ2 ==-1){
					occ1=arr[str.charAt(i)];
					occ2=i;
				}else{
					end = i-1;
					genrateSubStr(start, end, occ1, occ2, str, returnArr);
					if (end + 1 < str.length()) {
						int occPos = arr[str.charAt(end + 1)];
						if (occPos < occ1)
							start = occPos + 1;
						else
							start = occ1 + 1;
					} else {
						start = occ1 + 1;
					}
					end = start;
					i = start - 1;
					occ1 = -1;
					occ2 = -1;
					Arrays.fill(arr, -1);
				}
			}
		}
		return returnArr;

}

	public void genrateSubStr(int start, int end, int occ1, int occ2, String str,
	    Set<String> returnArr) {
		for (int i = start; i <= occ1; i++) {
			for (int j = end; j >= occ2; j--) {
				returnArr.add(str.substring(i, j + 1));
			}
		}

	}

}


