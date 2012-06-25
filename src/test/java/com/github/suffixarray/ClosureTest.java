package com.github.suffixarray;

import static org.junit.Assert.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import org.junit.Test;

/**
 * {@link com.github.suffixarray.Closure}のテストケース
 * 
 * @author Last changed by:$Author$
 * @version $Rev$ $Date::                     $
 * @since 2012/06/26
 */
public class ClosureTest {

	@Test
	public void getPages() throws Exception {
		URL url = new URL("http://www.osss.cs.tsukuba.ac.jp/kato/codeconv/CodeConvTOC.doc.html");
		Closure closure = new Closure(url);
		for (Page page : closure.getPages()) {
			System.out.println(page);
		}
	}

}
