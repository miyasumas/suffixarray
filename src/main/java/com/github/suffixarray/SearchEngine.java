package com.github.suffixarray;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * サーチエンジン
 * 
 * @author Last changed by:$Author$
 * @version $Rev$ $Date::                     $
 * @since 2012/06/18
 */
public class SearchEngine {

	/**
	 * ページ
	 */
	private Map<Page, Integer> pages = new HashMap<Page, Integer>();

	/**
	 * SuffixArray
	 */
	private SuffixArray suffixArray;

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
	public SearchEngine(List<Page> pages) throws IOException {
		StringBuilder builder = new StringBuilder();
		char delim = '\0';
		for (Page page : pages) {
			builder.append(delim);
			String content = page.getContentAsString();
			builder.append(content);
			this.pages.put(page, content.length());
			delim = '\0';
		}
		suffixArray = new SuffixArray(builder.toString());
	}
}
