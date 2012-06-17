package com.github.suffixarray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import com.github.suffixarray.SuffixArray.Suffix;

/**
 * SuffixArray
 * 
 * @author Last changed by:$Author$
 * @version $Rev$ $Date:: $
 * @since 2012/05/08
 */
public class SuffixArray implements Iterable<Suffix> {

	/**
	 * suffix配列
	 */
	private List<Suffix> suffixes = new ArrayList<Suffix>();

	/**
	 * コンストラクタ
	 * @param text 文字列
	 */
	public SuffixArray(String text) {
		for (int i = 0; i < text.length(); i++) {
			suffixes.add(new Suffix(text.substring(i), i + 1));
		}
		Collections.sort(suffixes);
	}

	/**
	 * リストとしてSuffixを取得します。
	 * @return Suffixリスト
	 */
	public List<Suffix> asList() {
		return suffixes;
	}

	/**
	 * 検索します。
	 * 
	 * @param keyword キーワード
	 * @return Suffix
	 */
	public List<Suffix> search(String keyword) {
		int index = binarySearch(keyword);
		List<Suffix> result = new ArrayList<Suffix>();
		return result;
	}

	/**
	 * Suffix配列に対して2分木探索をします。
	 * 
	 * @param keyword キーワード
	 * @return 対象Suffix
	 */
	private int binarySearch(String keyword) {
		int low = 0;
		int high = suffixes.size() - 1;

		int mid = -1;
		while (low <= high) {
			mid = (low + high) / 2;
			Suffix midVal = suffixes.get(mid);
			if (midVal.value.startsWith(keyword)) {
				return mid;
			}

			int cmp = midVal.value.compareTo(keyword);
			if (cmp < 0) {
				low = mid + 1;
			} else if (cmp > 0) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return -(low + 1);
	}

	/** 
	 * {@inheritDoc}
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Suffix> iterator() {
		return suffixes.iterator();
	}

	/** 
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("SuffixArray [suffixes=%s]", suffixes);
	}

	/**
	 * Suffix
	 * 
	 * @author Last changed by:$Author$
	 * @version $Rev$ $Date::                     $
	 * @since 2012/06/05
	 */
	public static class Suffix implements Comparable<Suffix> {

		/**
		 * 値
		 */
		private String value;

		/**
		 * インデックス
		 */
		private int index;

		/**
		 * コンストラクタ
		 * @param value 値
		 * @param index インデックス
		 */
		public Suffix(String value, int index) {
			super();
			this.value = value;
			this.index = index;
		}

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
			return value;
		}

		/** 
		 * {@inheritDoc}
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(Suffix o) {
			return value.compareTo(o.value);
		}
	}
}
