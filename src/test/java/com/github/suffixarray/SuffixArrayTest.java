package com.github.suffixarray;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Test;
import com.github.suffixarray.SuffixArray.Suffix;

/**
 * {@link com.github.suffixarray.SuffixArray}のテストケース
 * 
 * @author Last changed by:$Author$
 * @version $Rev$ $Date::                     $
 * @since 2012/06/08
 */
public class SuffixArrayTest {

	@Test
	public void iterator() {
		SuffixArray suffixarray = new SuffixArray("cba");
		Iterator<Suffix> iterator = suffixarray.iterator();
		assertEquals("a", iterator.next().toString());
		assertEquals("ba", iterator.next().toString());
		assertEquals("cba", iterator.next().toString());
	}

}
