package com.github.suffixarray;

import static org.junit.Assert.*;
import java.net.URL;

import miyasum.suffixarray.Page;

import org.junit.Test;

/**
 * {@link miyasum.suffixarray.Page}のテストケース
 * 
 * @author Last changed by:$Author$
 * @version $Rev$ $Date::                     $
 * @since 2012/06/25
 */
public class PageTest {

	@Test
	public void Page_読み込みと解析が正常に行える() throws Exception {
		URL url = new URL("http://www.osss.cs.tsukuba.ac.jp/kato/codeconv/CodeConvTOC.doc.html");
		Page page = new Page(url);
		assertEquals(new URL("http://www.osss.cs.tsukuba.ac.jp/kato/codeconv/CodeConvTOC.doc.html"), page.getUrl());
		for (URL link : page.getLinks()) {
			System.out.println(link);
		}
	}
}
