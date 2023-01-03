package org.crumbleworks.tests.crumbprops;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.crumbleworks.forge.crumbprops.PropertyManager;
import org.crumbleworks.tests.crumbprops.propertyfiles.TestPropertyFile;
import org.junit.Test;

public class PropertyFileTest {

	@Test
	public void integrationTest() {
		TestPropertyFile m = new TestPropertyFile();
		PropertyManager p = new PropertyManager();

		// WRITE
		m.setFirstNumber(17);
		m.setEmptyString("");
		m.setMyFancyFloat(6.789f);
		m.setWhiteSpacedKey("White spaced value");

		p.add(m);
		p.save(m);

		// SCAMBLE
		m.setFirstNumber(1);
		m.setEmptyString("asdasdasd");
		m.setMyFancyFloat(0.005f);
		m.setWhiteSpacedKey("lkjhgf");

		assertThat(m.getFirstNumber(), not(equalTo(17)));
		assertThat(m.getEmptyString(), not(equalTo("")));
		assertThat(m.getMyFancyFloat(), not(equalTo(6.789f)));
		assertThat(m.getWhiteSpacedKey(), not(equalTo("White spaced value")));

		// LOAD
		p.load(m);
		assertThat(m.getFirstNumber(), equalTo(17));
		assertThat(m.getEmptyString(), equalTo(""));
		assertThat(m.getMyFancyFloat(), equalTo(6.789f));
		assertThat(m.getWhiteSpacedKey(), equalTo("White spaced value"));
	}
}