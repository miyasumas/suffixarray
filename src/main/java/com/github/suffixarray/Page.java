package com.github.suffixarray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	 * 内容
	 */
	private String content;

	/**
	 * リンク
	 */
	private Set<URL> links;

	/**
	 * コンストラクタ
	 * @param url URL
	 * @throws IOException コンテンツを取得できなかった場合
	 * @throws URISyntaxException URIが正しくない場合
	 */
	public Page(URL url) throws IOException, URISyntaxException {
		this.url = url;
		this.content = readContent(url);
		this.links = parseLinks(url, content);
	}

	/**
	 * urlを取得します。
	 * @return url
	 */
	public URL getUrl() {
		return url;
	}

	/**
	 * contentを取得します。
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * linksを取得します。
	 * @return links
	 */
	public Set<URL> getLinks() {
		return links;
	}

	/**
	 * コンテンツを読み込みます。
	 * 
	 * @param url URL
	 * @return コンテンツ
	 * @throws IOException コンテンツを取得できなかった場合
	 */
	private String readContent(URL url) throws IOException {
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

	/**
	 * コピーします。
	 * 
	 * @param reader リーダー
	 * @param writer ライター
	 * @throws IOException コピーに失敗した場合
	 */
	private void copy(BufferedReader reader, BufferedWriter writer) throws IOException {
		String line = null;
		while ((line = reader.readLine()) != null) {
			writer.write(line);
			writer.newLine();
		}
		writer.flush();
	}

	/**
	 * リンクを解析します。
	 * 
	 * @param url URL
	 * @param content コンテンツ
	 * @return リンク
	 * @throws MalformedURLException URLが正しく生成できなかった場合
	 * @throws URISyntaxException URIが正しくない場合
	 */
	private Set<URL> parseLinks(URL url, String content) throws MalformedURLException, URISyntaxException {
		Set<URL> links = new HashSet<URL>();
		Pattern pattern = Pattern.compile("<a [^\\w]*href=\"([^\"]+)\"[^>]*>");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			String href = matcher.group(1).trim();
			if (href.startsWith("mailto:")) {
				continue;
			}
			URI link = url.toURI().resolve(href).normalize();
			links.add(link.toURL());
		}
		return links;
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
