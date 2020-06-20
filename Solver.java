import java.util.*;

public class Solver {

	static final String moves[] = new String[] { "R", "R'", "U", "U'", "F", "F'" };

	static byte[] convertScrambleToStickerArray(String scramble) {
		byte[] cube = new byte[25];
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

	static boolean checkSolved(byte[] cube) {
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

	static List<String> traceSteps(CubeState cs, Map<CubeState, CubeState> graph) {
		List<String> steps = new ArrayList<>();
		while (cs != null) {
			steps.add(moves[cs.lastMove]);
			cs = graph.get(cs);
		}
		steps.remove(steps.size() - 1);
		Collections.reverse(steps);
		return steps;
	}

	static List<String> solveBFS(CubeState cubeState) {
		Map<CubeState, CubeState> prev = new HashMap<>();
		Queue<CubeState> q = new ArrayDeque<>();
		q.add(cubeState);
		prev.put(cubeState, null);

		while (!q.isEmpty()) {
			CubeState curr = q.poll();
			if (checkSolved(curr.stickers)) {
				return traceSteps(curr, prev);
			}
			for (CubeState neigh : curr.neighbours()) {
				if (!prev.containsKey(neigh)) {
					prev.put(neigh, curr);
					q.add(neigh);
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter 24 character string of sticker colors according to cube_diagram.png\n");
		System.out.println("y-yellow");
		System.out.println("r-red");
		System.out.println("g-green");
		System.out.println("b-blue");
		System.out.println("w-white");
		System.out.println("o-orange");

		String scramble = in.next();
		byte[] cube = convertScrambleToStickerArray(scramble);

		long tic = System.currentTimeMillis();
		CubeState init = new CubeState(cube, 0);
		List<String> solution = solveBFS(init);

		System.out.println("Shortest solution:\n" + solution);
		System.out.printf("Time taken to solve: %fs\n", (System.currentTimeMillis() - tic) / 1000f);

	}
}