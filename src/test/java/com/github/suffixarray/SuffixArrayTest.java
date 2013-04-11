package com.github.suffixarray;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

import miyasum.suffixarray.SuffixArray;

import org.junit.Test;

/**
 * {@link miyasum.suffixarray.SuffixArray}のテストケース
 * 
 * @author Last changed by:$Author$
 * @version $Rev$ $Date::                     $
 * @since 2012/06/08
 */
public class SuffixArrayTest {

	@Test
	public void list() {
		SuffixArray suffixarray = new SuffixArray("cba");
		List<String> list = suffixarray.asList();
		assertEquals(3, list.size());
		assertEquals("a", list.get(0));
		assertEquals("ba", list.get(1));
		assertEquals("cba", list.get(2));
	}

	@Test
	public void search() {
		SuffixArray suffixarray = new SuffixArray("abccbaabc");
		System.out.println(suffixarray);
		int[] result = suffixarray.search("bc");
		System.out.println(Arrays.toString(result));
		assertEquals(2, result.length);
		assertEquals(8, result[0]);
		assertEquals(2, result[1]);
	}
}
