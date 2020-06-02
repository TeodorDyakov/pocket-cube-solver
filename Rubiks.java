import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Rubiks {

	static final int R = 0;
	static final int Rprim = 1;
	static final int U = 2;
	static final int Uprim = 3;
	static final int F = 4;
	static final int Fprim = 5;

	static final String moves[] = new String[] { "R", "R'", "U", "U'", "F", "F'" };

	static final int Yellow = 0;
	static final int Red = 1;
	static final int Green = 2;
	static final int Blue = 3;
	static final int White = 4;
	static final int Orange = 5;

	static int cube[] = new int[25];
	static int cubeNew[] = new int[25];

	static int[] convertScrambleToCubeArray(String scramble) {
		int[] cube = new int[25];
		for (int i = 1; i < cube.length; i++) {
			switch (scramble.charAt(i - 1)) {
			case 'y':
				cube[i] = 0;
				break;
			case 'r':
				cube[i] = 1;
				break;
			case 'g':
				cube[i] = 2;
				break;
			case 'b':
				cube[i] = 3;
				break;
			case 'w':
				cube[i] = 4;
				break;
			case 'o':
				cube[i] = 5;
				break;

			}
		}
		return cube;
	}

	static boolean checkSolved(int[] cube) {
		int color = 0;
		for (int i = 1; i < cube.length; i++) {

			if (i % 4 == 1) {
				color = cube[i];
			}
			if (cube[i] != color) {
				return false;
			}
		}
		return true;
	}

	static void rotate(int side) {
		for (int i = 1; i < cube.length; i++) {
			cube[i] = cubeNew[i];
		}
		switch (side) {
		case U:
			rotateU();
			break;
		case Uprim:
			rotateUprim();
			break;
		case R:
			rotateR();
			break;
		case Rprim:
			rotateRprim();
			break;
		case F:
			rotateF();
			break;
		case Fprim:
			rotateFprim();
			break;
		}

	}

	static void rotateU() {
		cubeNew[5] = cube[21];
		cubeNew[6] = cube[22];
		cubeNew[1] = cube[3];
		cubeNew[2] = cube[1];
		cubeNew[3] = cube[4];
		cubeNew[21] = cube[13];
		cubeNew[22] = cube[14];
		cubeNew[13] = cube[17];
		cubeNew[14] = cube[18];
		cubeNew[17] = cube[5];
		cubeNew[18] = cube[6];
		cubeNew[4] = cube[2];
	}

	static void rotateUprim() {
		cubeNew[1] = cube[2];
		cubeNew[2] = cube[4];
		cubeNew[4] = cube[3];
		cubeNew[3] = cube[1];
		cubeNew[5] = cube[17];
		cubeNew[6] = cube[18];
		cubeNew[21] = cube[5];
		cubeNew[22] = cube[6];
		cubeNew[13] = cube[21];
		cubeNew[14] = cube[22];
		cubeNew[17] = cube[13];
		cubeNew[18] = cube[14];
	}

	static void rotateR() {
		cubeNew[2] = cube[6];
		cubeNew[4] = cube[8];
		cubeNew[21] = cube[23];
		cubeNew[22] = cube[21];
		cubeNew[23] = cube[24];
		cubeNew[24] = cube[22];
		cubeNew[6] = cube[10];
		cubeNew[8] = cube[12];
		cubeNew[10] = cube[15];
		cubeNew[12] = cube[13];
		cubeNew[13] = cube[4];
		cubeNew[15] = cube[2];
	}

	static void rotateRprim() {
		cubeNew[2] = cube[15];
		cubeNew[4] = cube[13];
		cubeNew[6] = cube[2];
		cubeNew[8] = cube[4];
		cubeNew[10] = cube[6];
		cubeNew[12] = cube[8];
		cubeNew[15] = cube[10];
		cubeNew[13] = cube[12];
		cubeNew[24] = cube[23];
		cubeNew[23] = cube[21];
		cubeNew[21] = cube[22];
		cubeNew[22] = cube[24];
	}

	static void rotateF() {
		cubeNew[3] = cube[20];
		cubeNew[4] = cube[18];
		cubeNew[21] = cube[3];
		cubeNew[23] = cube[4];
		cubeNew[18] = cube[9];
		cubeNew[20] = cube[10];
		cubeNew[10] = cube[21];
		cubeNew[9] = cube[23];
		cubeNew[6] = cube[5];
		cubeNew[5] = cube[7];
		cubeNew[7] = cube[8];
		cubeNew[8] = cube[6];
	}

	static void rotateFprim() {
		cubeNew[20] = cube[3];
		cubeNew[18] = cube[4];
		cubeNew[3] = cube[21];
		cubeNew[4] = cube[23];
		cubeNew[9] = cube[18];
		cubeNew[10] = cube[20];
		cubeNew[21] = cube[10];
		cubeNew[23] = cube[9];
		cubeNew[5] = cube[6];
		cubeNew[7] = cube[5];
		cubeNew[8] = cube[7];
		cubeNew[6] = cube[8];
	}

	static String algoToString(int[] algo) {
		String s = "";
		for (int i : algo) {
			s += moves[i] + " ";
		}
		return s;
	}

	static List<String> solves = new ArrayList<>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter cube scramble:\n ");
		System.out.println("y-yellow");
		System.out.println("r-red");
		System.out.println("g-green");
		System.out.println("b-blue");
		System.out.println("w-white");
		System.out.println("o-orange");

		String scramble = in.next();
		int[] cubeInitialState = convertScrambleToCubeArray(scramble);

		// yyyyrrrrwwwwoooobbbbgggg
		// yryrrwrwwowoyoyobbbbgggg
		// gbyyrgggwwbwryborbyrowoo

		int[] algo = new int[10];

		long tic = System.currentTimeMillis();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 6; k++) {
					for (int ii = 0; ii < 6; ii++) {
						for (int jj = 0; jj < 6; jj++) {
							for (int kk = 0; kk < 6; kk++) {
								for (int iii = 0; iii < 6; iii++) {
									for (int jjj = 0; jjj < 6; jjj++) {
										for (int kkk = 0; kkk < 6; kkk++) {
											for (int iiii = 0; iiii < 6; iiii++) {
												for (int idx = 1; idx < cubeInitialState.length; idx++) {
													cubeNew[idx] = cubeInitialState[idx];
												}
												algo[0] = i;
												algo[1] = j;
												algo[2] = k;
												algo[3] = ii;
												algo[4] = jj;
												algo[5] = kk;
												algo[6] = iii;
												algo[7] = jjj;
												algo[8] = kkk;
												algo[9] = iiii;
												for (int p = 0; p < algo.length; p++) {
													rotate(algo[p]);
													if (checkSolved(cubeNew)) {
														solves.add(algoToString(Arrays.copyOf(algo, p + 1)));
														break;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println(System.currentTimeMillis() - tic);
		int minLen = 20;
		String min = "";
		// System.out.println(solves);
		for (int i = 0; i < solves.size(); i++) {
			if (solves.get(i).length() < minLen) {
				minLen = solves.get(i).length();
				min = solves.get(i);
			}
		}
		System.out.println(min);
	}
}
