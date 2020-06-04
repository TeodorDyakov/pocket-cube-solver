import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CubeState {
	static final int R = 0;
	static final int Rprim = 1;
	static final int U = 2;
	static final int Uprim = 3;
	static final int F = 4;
	static final int Fprim = 5;

	int lastMove;
	int[] stickers;

	CubeState(int[] arr, int move) {
		this.stickers = arr;
		this.lastMove = move;
	}

	List<CubeState> neighbours() {
		List<CubeState> neigh = new ArrayList<>();
		for (int move = 0; move < 6; move++) {
			int[] stateArr = rotate(move, stickers);
			neigh.add(new CubeState(stateArr, move));
		}
		return neigh;
	}

	static int[] rotate(int side, int[] cube) {
		int cubeNew[] = Arrays.copyOf(cube, cube.length);
		switch (side) {
		case U:
			rotateU(cubeNew, cube);
			break;
		case Uprim:
			rotateUprim(cubeNew, cube);
			break;
		case R:
			rotateR(cubeNew, cube);
			break;
		case Rprim:
			rotateRprim(cubeNew, cube);
			break;
		case F:
			rotateF(cubeNew, cube);
			break;
		case Fprim:
			rotateFprim(cubeNew, cube);
			break;
		}
		return cubeNew;
	}

	static void rotateU(int[] cubeNew, int[] cube) {
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

	static void rotateUprim(int[] cubeNew, int[] cube) {
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

	static void rotateR(int[] cubeNew, int[] cube) {
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

	static void rotateRprim(int[] cubeNew, int[] cube) {
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

	static void rotateF(int[] cubeNew, int[] cube) {
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

	static void rotateFprim(int[] cubeNew, int[] cube) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CubeState cubeState = (CubeState) o;
		return Arrays.equals(stickers, cubeState.stickers);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(stickers);
	}

}
