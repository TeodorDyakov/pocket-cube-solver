import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Rubiks {

	static final int R = 0;
	static final int Rprim = 1;
	static final int U = 2;
	static final int Uprim = 3;
	static final int F = 4;
	static final int Fprim = 5;

	static final int[] reverseMoves = new int[] { Rprim, R, Uprim, U, Fprim, F };
	static final String moves[] = new String[] { "R", "R'", "U", "U'", "F", "F'" };

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

	static boolean isTopOriented(int[] cube) {
		return cube[1] == cube[2] && cube[3] == cube[2] && cube[4] == cube[3];
	}

	static boolean isLayerOneSolved(int[] cube) {
		return cube[7] == cube[8] && cube[9] == cube[10] && cube[10] == cube[11] && cube[11] == cube[12]
				&& cube[15] == cube[16] && cube[19] == cube[20] && cube[23] == cube[24];
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

	static void execAlg(int[] alg) {
		for (int move : alg) {
			rotate(move);
		}
	}

	static Map<String, Integer> dp = new HashMap<>();

	// ogbyyrggwwwwybbbyrrrgooo
	static void solve(String algo, int numberOfMoves, int prevMove) {
		if (numberOfMoves >= 12) {
			return;
		}

		if (checkSolved(cubeNew)) {
			solves.add(algo);
			return;
		}
		String cubeState = Arrays.toString(cubeNew);
		Integer movesToState = dp.get(cubeState);

		if (movesToState == null || movesToState > numberOfMoves) {
			dp.put(cubeState, numberOfMoves);
		} else if (movesToState <= numberOfMoves) {
			return;
		}

		for (int move = 0; move < 6; move++) {
			if (reverseMoves[move] == prevMove) {
				continue;
			}
			rotate(move);
			solve(algo + moves[move], numberOfMoves + 1, move);
			rotate(reverseMoves[move]);
		}
	}

	static List<String> solves = new ArrayList<>();

	static String shortestSolve() {
		int minLen = 20;
		String min = "";
		// System.out.println(solves);
		for (int i = 0; i < solves.size(); i++) {
			if (solves.get(i).length() < minLen) {
				minLen = solves.get(i).length();
				min = solves.get(i);
			}
		}
		return min;
	}

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
		cubeNew = Arrays.copyOf(cubeInitialState, cubeNew.length);

		long tic = System.currentTimeMillis();
		solve("", 0, 7);
		System.out.printf("Time taken to find shorest solution, %fs\n%s",
				(System.currentTimeMillis() - tic) / 1000f, shortestSolve());

	}
}
