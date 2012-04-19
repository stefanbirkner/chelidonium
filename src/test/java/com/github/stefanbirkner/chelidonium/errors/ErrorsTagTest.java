package com.github.stefanbirkner.chelidonium.errors;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.stefanbirkner.serverrule.Jetty;

@RunWith(Parameterized.class)
public class ErrorsTagTest {
	@Parameters
	public static List<Object[]> data() {
		return asList(new Object[][] { { "singleError" }, { "twoErrors" },
				{ "block" }, { "closeable" } });
	}

	@ClassRule
	public static final Jetty SERVER = new Jetty();

	private final String name;

	public ErrorsTagTest(String name) {
		this.name = name;
	}

	@Test
	public void showsErrors() throws Exception {
		String url = "http://127.0.0.1:8080/test/errors/" + name + ".html";
		assertEquals(snippetWithName(name), pageSourceForUrl(url));
	}

	private String pageSourceForUrl(String urlAsString)
			throws MalformedURLException, IOException {
		URL url = new URL(urlAsString);
		return IOUtils.toString(url.openStream());
	}

	private String snippetWithName(String name) throws IOException {
		InputStream is = getClass().getResourceAsStream(name + ".snippet");
		return IOUtils.toString(is);
	}
}
