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
	byte[] stickers;

	CubeState(byte[] arr, int move) {
		this.stickers = arr;
		this.lastMove = move;
	}

	List<CubeState> neighbours() {
		List<CubeState> neigh = new ArrayList<>();
		for (int move = 0; move < 6; move++) {
			byte[] stickersRot = rotate(move, stickers);
			neigh.add(new CubeState(stickersRot, move));
		}
		return neigh;
	}

	static byte[] rotate(int move, byte[] stickers) {
		byte[] stickersRot = Arrays.copyOf(stickers, stickers.length);
		switch (move) {
		case U:
			rotateU(stickersRot, stickers);
			break;
		case Uprim:
			rotateUprim(stickersRot, stickers);
			break;
		case R:
			rotateR(stickersRot, stickers);
			break;
		case Rprim:
			rotateRprim(stickersRot, stickers);
			break;
		case F:
			rotateF(stickersRot, stickers);
			break;
		case Fprim:
			rotateFprim(stickersRot, stickers);
			break;
		}
		return stickersRot;
	}

	static void rotateU(byte[] stickersRot, byte[] stickers) {
		stickersRot[5] = stickers[21];
		stickersRot[6] = stickers[22];
		stickersRot[1] = stickers[3];
		stickersRot[2] = stickers[1];
		stickersRot[3] = stickers[4];
		stickersRot[21] = stickers[13];
		stickersRot[22] = stickers[14];
		stickersRot[13] = stickers[17];
		stickersRot[14] = stickers[18];
		stickersRot[17] = stickers[5];
		stickersRot[18] = stickers[6];
		stickersRot[4] = stickers[2];
	}

	static void rotateUprim(byte[] stickersRot, byte[] stickers) {
		stickersRot[1] = stickers[2];
		stickersRot[2] = stickers[4];
		stickersRot[4] = stickers[3];
		stickersRot[3] = stickers[1];
		stickersRot[5] = stickers[17];
		stickersRot[6] = stickers[18];
		stickersRot[21] = stickers[5];
		stickersRot[22] = stickers[6];
		stickersRot[13] = stickers[21];
		stickersRot[14] = stickers[22];
		stickersRot[17] = stickers[13];
		stickersRot[18] = stickers[14];
	}

	static void rotateR(byte[] stickersRot, byte[] stickers) {
		stickersRot[2] = stickers[6];
		stickersRot[4] = stickers[8];
		stickersRot[21] = stickers[23];
		stickersRot[22] = stickers[21];
		stickersRot[23] = stickers[24];
		stickersRot[24] = stickers[22];
		stickersRot[6] = stickers[10];
		stickersRot[8] = stickers[12];
		stickersRot[10] = stickers[15];
		stickersRot[12] = stickers[13];
		stickersRot[13] = stickers[4];
		stickersRot[15] = stickers[2];
	}

	static void rotateRprim(byte[] stickersRot, byte[] stickers) {
		stickersRot[2] = stickers[15];
		stickersRot[4] = stickers[13];
		stickersRot[6] = stickers[2];
		stickersRot[8] = stickers[4];
		stickersRot[10] = stickers[6];
		stickersRot[12] = stickers[8];
		stickersRot[15] = stickers[10];
		stickersRot[13] = stickers[12];
		stickersRot[24] = stickers[23];
		stickersRot[23] = stickers[21];
		stickersRot[21] = stickers[22];
		stickersRot[22] = stickers[24];
	}

	static void rotateF(byte[] stickersRot, byte[] stickers) {
		stickersRot[3] = stickers[20];
		stickersRot[4] = stickers[18];
		stickersRot[21] = stickers[3];
		stickersRot[23] = stickers[4];
		stickersRot[18] = stickers[9];
		stickersRot[20] = stickers[10];
		stickersRot[10] = stickers[21];
		stickersRot[9] = stickers[23];
		stickersRot[6] = stickers[5];
		stickersRot[5] = stickers[7];
		stickersRot[7] = stickers[8];
		stickersRot[8] = stickers[6];
	}

	static void rotateFprim(byte[] stickersRot, byte[] stickers) {
		stickersRot[20] = stickers[3];
		stickersRot[18] = stickers[4];
		stickersRot[3] = stickers[21];
		stickersRot[4] = stickers[23];
		stickersRot[9] = stickers[18];
		stickersRot[10] = stickers[20];
		stickersRot[21] = stickers[10];
		stickersRot[23] = stickers[9];
		stickersRot[5] = stickers[6];
		stickersRot[7] = stickers[5];
		stickersRot[8] = stickers[7];
		stickersRot[6] = stickers[8];
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CubeState stickersState = (CubeState) o;
		return Arrays.equals(stickers, stickersState.stickers);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(stickers);
	}

}
