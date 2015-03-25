package api.algorithm;

/**
 * DNA and Protein Alignment Algorithm for two of the same type
 * 
 * @author jdandrade
 *
 */

public class SequenceAlignmentAlgorithm {
	/**
	 * MATCH - When two characters match. MISMATCH - When two characters do not
	 * match. GAP - When one of the characters is a gap.
	 */
	private static final int MATCH = 4;
	private static final int MISMATCH = -1;
	private static final int GAP = -2;

	private static final int DIAGONAL = 0;
	private static final int LEFT = 1;
	private static final int UP = 2;

	private static int[][] SCOREMATRIX;
	private static int[][] MOVEMENTMATRIX;
	private static int MATRIXLENGTH;

	private SequenceAlignmentAlgorithm() {

	}

	/**
	 * Given two Strings, returns an array with those two Strings Aligned with
	 * each other (SequenceAlignmentAlgorithm).
	 * 
	 * @param s1
	 *            Sequence 1
	 * @param s2
	 *            Sequence 2
	 * @return Array with two Sequences
	 */
	public static String[] ALIGN(String s1, String s2) {

		MATRIXLENGTH = (s1.length() > s2.length()) ? s1.length() : s2.length();

		StringBuilder sone = new StringBuilder(s1);
		StringBuilder stwo = new StringBuilder(s2);

		while (sone.length() != stwo.length()) {
			int lengthdif = sone.length() - stwo.length();

			if (lengthdif < 0) {
				sone.append(" ");
			} else {
				stwo.append(" ");
			}
		}

		s1 = sone.toString();
		s2 = stwo.toString();

		SCOREMATRIX = new int[MATRIXLENGTH + 1][MATRIXLENGTH + 1];
		MOVEMENTMATRIX = new int[MATRIXLENGTH + 1][MATRIXLENGTH + 1];

		initBothMatrix();
		buildBothMatrix(s1, s2);

		sone.delete(0, sone.length());
		stwo.delete(0, stwo.length());

		int i = MOVEMENTMATRIX.length - 1;
		int j = MOVEMENTMATRIX[0].length - 1;

		while (true) {
			if (MOVEMENTMATRIX[i][j] == DIAGONAL) {
				sone.append(s1.charAt(i - 1));
				stwo.append(s2.charAt(j - 1));
				i += -1;
				j += -1;
			} else if (MOVEMENTMATRIX[i][j] == LEFT) {
				sone.append("-");
				stwo.append(s2.charAt(j - 1));
				i += 0;
				j += -1;
			} else if (MOVEMENTMATRIX[i][j] == UP) {
				sone.append(s1.charAt(i - 1));
				stwo.append("-");
				i += -1;
				j += 0;
			} else if (MOVEMENTMATRIX[i][j] == 3) {
				break;
			}
		}

		sone.reverse();
		stwo.reverse();

		sone = trimChar(sone, '-');
		stwo = trimChar(stwo, '-');

		s1 = sone.toString();
		s2 = stwo.toString();

		String[] rslt = { s1, s2 };

		return rslt;
	}

	private static void buildBothMatrix(String s1, String s2) {
		for (int i = 0; i != SCOREMATRIX.length; i++) {
			for (int j = 0; j != SCOREMATRIX[i].length; j++) {
				if (SCOREMATRIX[i][j] == 10000) {
					boolean match = false;
					if (s1.charAt(i - 1) == s2.charAt(j - 1))
						match = true;
					int p1 = SCOREMATRIX[i - 1][j] + GAP;
					int p2 = SCOREMATRIX[i][j - 1] + GAP;
					int p3 = -1;
					if (match) {
						p3 = SCOREMATRIX[i - 1][j - 1] + MATCH;
					} else {
						p3 = SCOREMATRIX[i - 1][j - 1] + MISMATCH;
					}

					if (p1 >= p2 && p1 >= p3) {
						SCOREMATRIX[i][j] = p1;
						MOVEMENTMATRIX[i][j] = UP;
					} else if (p2 >= p3 && p2 >= p1) {
						SCOREMATRIX[i][j] = p2;
						MOVEMENTMATRIX[i][j] = LEFT;
					} else if (p3 >= p1 && p3 >= p2) {
						SCOREMATRIX[i][j] = p3;
						MOVEMENTMATRIX[i][j] = DIAGONAL;
					}
				}
			}
		}
	}

	private static void initBothMatrix() {
		for (int i = 0; i != SCOREMATRIX.length; i++) {
			for (int j = 0; j != SCOREMATRIX[i].length; j++) {
				if (i == 0) {
					SCOREMATRIX[i][j] = j * GAP;
					MOVEMENTMATRIX[i][j] = LEFT;
				} else {
					SCOREMATRIX[i][j] = 10000;
				}
				if (j == 0) {
					SCOREMATRIX[i][j] = i * GAP;
					MOVEMENTMATRIX[i][j] = UP;
				}
				if (j == 0 && i == 0) {
					MOVEMENTMATRIX[i][j] = 3;
				}

			}
		}
	}

	private static StringBuilder trimChar(StringBuilder string, char c) {

		for (int i = string.length(); i != 0; i--) {
			if (string.charAt(i - 1) != '-' && string.charAt(i - 1) != '\n') {
				break;
			}
			string.delete(i - 1, i);
		}

		int x = 0;

		x = 0;
		for (int i = 0; i != string.length() - 1; i++) {
			if (string.charAt(i) != '-') {
				break;
			}
			x++;

		}
		for (int i = x; i != 0; i--) {
			string.replace(i - 1, i, " ");
		}

		return string;
	}
}
