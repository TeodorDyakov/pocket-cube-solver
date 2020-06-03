import java.util.*;

public class Rubiks {

    static final int R = 0;
    static final int Rprim = 1;
    static final int U = 2;
    static final int Uprim = 3;
    static final int F = 4;
    static final int Fprim = 5;

    static final String moves[] = new String[]{"R", "R'", "U", "U'", "F", "F'"};

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

    static class CubeState {
        int move;
        int[] arr;

        CubeState(int[] arr, int move) {
            this.arr = arr;
            this.move = move;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CubeState cubeState = (CubeState) o;
            return Arrays.equals(arr, cubeState.arr);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(arr);
        }
    }

    static void traceSteps(CubeState cs, Map<CubeState, CubeState> graph) {
        List<String> steps = new ArrayList<>();
        while (cs != null) {
            steps.add(moves[cs.move]);
            cs = graph.get(cs);
        }
        steps.remove(steps.size() - 1);
        Collections.reverse(steps);
        System.out.println(steps);
    }

    static void bfs(CubeState cubeState) {
        Map<CubeState, CubeState> prev = new HashMap();
        Queue<CubeState> q = new ArrayDeque<>();
        q.add(cubeState);
        prev.put(cubeState, null);

        while (!q.isEmpty()) {
            CubeState curr = q.poll();
            if (checkSolved(curr.arr)) {
                traceSteps(curr, prev);
                break;
            }
            for (CubeState neigh : neighbours(curr)) {
                if (!prev.containsKey(neigh)) {
                    prev.put(neigh, curr);
                    q.add(neigh);
                }
            }
        }
    }

    static List<CubeState> neighbours(CubeState cubeState) {
        List<CubeState> neigh = new ArrayList<>();
        for (int move = 0; move < 6; move++) {
            int[] stateArr = rotate(move, cubeState.arr);
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
        int[] cube = convertScrambleToCubeArray(scramble);

        long tic = System.currentTimeMillis();
        CubeState init = new CubeState(cube, 0);
        bfs(init);
        System.out.printf("Time taken to find shorest solution, %fs\n",
                (System.currentTimeMillis() - tic) / 1000f);

    }
}
