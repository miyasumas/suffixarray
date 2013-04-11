package miyasum.suffixarray;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * ページの閉包
 * 
 * @author Last changed by:$Author$
 * @version $Rev$ $Date::                     $
 * @since 2012/06/25
 */
public class Closure {

	/**
	 * ページ
	 */
	private Set<Page> pages;

	/**
	 * コンストラクタ
	 * @param base ベースURL
	 * @throws MalformedURLException URLが正しく生成できなかった場合
	 * @throws URISyntaxException URIが正しくない場合
	 */
	public Closure(URL base) throws IOException, URISyntaxException {
		this(base, true);
	}

	/**
	 * コンストラクタ
	 * @param base ベースURL
	 * @param onlySameHost 同一ホスト内のみの閉包とするか
	 * @throws MalformedURLException URLが正しく生成できなかった場合
	 * @throws URISyntaxException URIが正しくない場合
	 */
	public Closure(URL base, boolean onlySameHost) throws IOException, URISyntaxException {
		this.pages = resolve(base, onlySameHost);
	}

	/**
	 * 閉包を求めます。
	 * 
	 * @param base ベースURL
	 * @param onlySameHost 同一ホストのみとするか
	 * @return ページの閉包
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private Set<Page> resolve(URL base, boolean onlySameHost) throws IOException, URISyntaxException {
		Set<Page> pages = new HashSet<Page>();
		Page basePage = new Page(base);
		pages.add(basePage);
		for (URL link : basePage.getLinks()) {
			if (onlySameHost && !link.getHost().equals(base.getHost())) {
				continue;
			}

			pages.addAll(resolve(link, onlySameHost));
		}
		return pages;
	}

	/**
	 * pagesを取得します。
	 * @return pages
	 */
	public Set<Page> getPages() {
		return pages;
	}
}
