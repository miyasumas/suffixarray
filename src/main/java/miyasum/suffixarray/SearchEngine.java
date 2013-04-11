package miyasum.suffixarray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * サーチエンジン
 * 
 * @author Last changed by:$Author$
 * @version $Rev$ $Date::                     $
 * @since 2012/06/18
 */
public class SearchEngine {

	/**
	 * ページインデックス
	 */
	private SortedMap<Integer, Page> pageIndexes = new TreeMap<Integer, Page>();

	/**
	 * SuffixArray
	 */
	private SuffixArray suffixArray;

	/**
	 * コンストラクタ
	 * @param closure 閉包
	 * @throws IOException ページの読み込みに失敗した場合
	 */
	public SearchEngine(Closure closure) throws IOException {
		this(closure.getPages());
	}

	/**
	 * コンストラクタ
	 * @param pages ページ
	 * @throws IOException ページの読み込みに失敗した場合
	 */
	public SearchEngine(Page... pages) throws IOException {
		this(Arrays.asList(pages));
	}

	/**
	 * コンストラクタ
	 * @param pages ページ
	 * @throws IOException ページの読み込みに失敗した場合
	 */
	public SearchEngine(Iterable<Page> pages) throws IOException {
		StringBuilder builder = new StringBuilder();
		String delim = "";
		for (Page page : pages) {
			this.pageIndexes.put(builder.length(), page);
			String content = skipTags(page.getContent());
			System.out.println(content.replace("\0", ""));
			builder.append(delim).append(content);
			delim = "\0";
		}
		suffixArray = new SuffixArray(builder.toString());
	}

	private String skipTags(String content) {
		// タグをスキップ
		Pattern p = Pattern.compile("(<[\\w!/]+ .*>)", Pattern.MULTILINE);
		Matcher m = p.matcher(content);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			StringBuilder nullString = new StringBuilder();
			for (int i = 0; i < m.group(1).length(); i++) {
				System.out.println(m.group(0));
				nullString.append("\0");
			}
			m.appendReplacement(sb, nullString.toString());
		}
		m.appendTail(sb);
		return sb.toString();
	}

	SuffixArray getSuffixArray() {
		return suffixArray;
	}

	public Map<Page, List<Integer>> search(String keyword) {
		Map<Page, List<Integer>> map = new HashMap<Page, List<Integer>>();

		int[] result = suffixArray.search(keyword);
		for (int index : result) {
			int pageIndex = pageIndexes.headMap(index).lastKey();
			Page page = pageIndexes.get(pageIndex);
			List<Integer> list = map.get(page);
			if (list == null) {
				list = new ArrayList<Integer>();
				map.put(page, list);
			}
			list.add(index - pageIndex);
		}

		return map;
	}
}
