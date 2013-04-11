package com.github.suffixarray;

import static org.junit.Assert.*;
import java.io.IOException;
import java.net.URL;

import miyasum.suffixarray.Page;
import miyasum.suffixarray.SearchEngine;

import org.junit.Test;

/**
 * {@link miyasum.suffixarray.SearchEngine}のテストケース
 * 
 * @author Last changed by:$Author$
 * @version $Rev$ $Date::                     $
 * @since 2012/06/27
 */
public class SearchEngineTest {

	@Test
	public void search() throws Exception {
		URL url = new URL("http://www.osss.cs.tsukuba.ac.jp/kato/codeconv/CodeConvTOC.doc.html");
		Page page = new Page(url);
		SearchEngine engine = new SearchEngine(page);

		System.out.println(engine.search("File"));
	}
}
