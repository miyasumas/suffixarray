package com.github.suffixarray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;

/**
 * ページ
 * 
 * @author Last changed by:$Author$
 * @version $Rev$ $Date::                     $
 * @since 2012/06/05
 */
public class Page {

	/**
	 * URL
	 */
	private URL url;

	/**
	 * コンストラクタ
	 * @param url URL
	 */
	public Page(URL url) {
		this.url = url;
	}

	public String getContentAsString() throws IOException {
		StringWriter text = new StringWriter();
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			writer = new BufferedWriter(text);
			copy(reader, writer);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// ignore
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}
		return text.toString();
	}

	private void copy(BufferedReader reader, BufferedWriter writer) throws IOException {
		String line = null;
		while ((line = reader.readLine()) != null) {
			writer.write(line);
			writer.newLine();
		}
		writer.flush();
	}

	/** 
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	/** 
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	/** 
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Page [url=%s]", url);
	}
}
