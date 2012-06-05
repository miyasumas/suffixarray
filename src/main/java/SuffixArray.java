import java.util.Arrays;

/**
 * SuffixArray
 * 
 * @author Last changed by:$Author$
 * @version $Rev$ $Date:: $
 * @since 2012/05/08
 */
public class SuffixArray {

	/**
	 * suffix配列
	 */
	private String[] suffixes;

	/**
	 * インデックス
	 */
	private int[] indices;

	/**
	 * コンストラクタ
	 * @param text 文字列
	 */
	public SuffixArray(String text) {
		this.suffixes = createSuffixes(text);

		this.indices = new int[this.suffixes.length];
		for (int i = 0; i < indices.length; i++) {
			indices[i] = text.length() - suffixes[i].length() + 1;
		}
	}

	/**
	 * suffix配列を作成します。
	 * @param text 対象文字列
	 * @return suffix配列
	 */
	private String[] createSuffixes(String text) {
		String[] suffixes = new String[text.length()];
		for (int i = 0; i < text.length(); i++) {
			suffixes[i] = text.substring(i);
		}
		Arrays.sort(suffixes);
		return suffixes;
	}

	/**
	 * suffixesを取得します。
	 * @return suffixes
	 */
	public String[] getSuffixes() {
		return suffixes;
	}

	/**
	 * indexesを取得します。
	 * @return indices
	 */
	public int[] getIndices() {
		return indices;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("SuffixArray [suffixes=%s, indices=%s]",
				Arrays.toString(suffixes), Arrays.toString(indices));
	}

	/**
	 * エントリー
	 * 
	 * @author Last changed by:$Author$
	 * @version $Rev$ $Date::                     $
	 * @since 2012/06/05
	 */
	public static class Entry implements Comparable<Entry> {

		private String suffix;

		private int index;

		/**
		 * indexを取得します。
		 * @return index
		 */
		public int getIndex() {
			return index;
		}

		/** 
		 * {@inheritDoc}
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return suffix;
		}

		/** 
		 * {@inheritDoc}
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(Entry o) {
			return suffix.compareTo(o.suffix);
		}
	}

	public static void main(String[] args) {
		SuffixArray array = new SuffixArray("abcbccab");
		System.out.println(Arrays.toString(array.getIndices()));
	}
}
