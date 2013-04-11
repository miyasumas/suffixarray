package miyasum.suffixarray;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
	private int[] indexes;

	/**
	 * コンストラクタ
	 * @param text 文字列
	 */
	public SuffixArray(String text) {
		int textLength = text.length();

		suffixes = new String[textLength];
		for (int i = 0; i < textLength; i++) {
			suffixes[i] = text.substring(i);
		}
		Arrays.sort(suffixes);

		indexes = new int[textLength];
		for (int i = 0; i < textLength; i++) {
			indexes[i] = textLength - suffixes[i].length() + 1;
		}
	}

	/**
	 * リストとしてSuffixを取得します。
	 * @return Suffixリスト
	 */
	public List<String> asList() {
		return Arrays.asList(suffixes);
	}

	/**
	 * 検索します。
	 * 
	 * @param keyword キーワード
	 * @return インデックス
	 */
	public int[] search(String keyword) {
		int elemNo = Arrays.binarySearch(suffixes, keyword, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.startsWith(o2)) {
					return 0;
				}
				return o1.compareTo(o2);
			}
		});

		int from = elemNo;
		for (int i = elemNo; i <= 0; i--) {
			if (!suffixes[i].startsWith(keyword)) {
				break;
			}
			from = i;
		}
		int to = elemNo;
		for (int i = elemNo; i < suffixes.length; i++) {
			to = i;
			if (!suffixes[i].startsWith(keyword)) {
				break;
			}
		}

		return Arrays.copyOfRange(indexes, from, to);
	}

	/** 
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("SuffixArray [suffixes=%s, indexes=%s]", Arrays.toString(suffixes), Arrays.toString(indexes));
	}

//	/**
//	 * Suffix
//	 * 
//	 * @author Last changed by:$Author$
//	 * @version $Rev$ $Date::                     $
//	 * @since 2012/06/05
//	 */
//	public static class Suffix implements Comparable<Suffix> {
//
//		/**
//		 * 値
//		 */
//		private String value;
//
//		/**
//		 * インデックス
//		 */
//		private int index;
//
//		/**
//		 * コンストラクタ
//		 * @param value 値
//		 * @param index インデックス
//		 */
//		public Suffix(String value, int index) {
//			super();
//			this.value = value;
//			this.index = index;
//		}
//
//		/**
//		 * indexを取得します。
//		 * @return index
//		 */
//		public int getIndex() {
//			return index;
//		}
//
//		/** 
//		 * {@inheritDoc}
//		 * @see java.lang.Object#toString()
//		 */
//		@Override
//		public String toString() {
//			return value;
//		}
//
//		/** 
//		 * {@inheritDoc}
//		 * @see java.lang.Comparable#compareTo(java.lang.Object)
//		 */
//		@Override
//		public int compareTo(Suffix o) {
//			return value.compareTo(o.value);
//		}
//	}
}
