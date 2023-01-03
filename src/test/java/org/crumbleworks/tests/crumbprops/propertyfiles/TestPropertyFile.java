package org.crumbleworks.tests.crumbprops.propertyfiles;

import org.crumbleworks.forge.crumbprops.annotations.Property;
import org.crumbleworks.forge.crumbprops.annotations.PropertyFile;

@PropertyFile(fileName = "ManualTest.conf")
public class TestPropertyFile {

	@Property(key = "firstNumber")
	public Integer firstNumber;

	@Property(key = "emptyString")
	public String emptyString;

	@Property(key = "White spaced key")
	public String whiteSpacedKey;

	@Property(key = "myFancyFloat")
	public Float myFancyFloat;

	/* GENERATED */
	
	public Integer getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(Integer firstNumber) {
		this.firstNumber = firstNumber;
	}

	public String getEmptyString() {
		return emptyString;
	}

	public void setEmptyString(String emptyString) {
		this.emptyString = emptyString;
	}

	public String getWhiteSpacedKey() {
		return whiteSpacedKey;
	}

	public void setWhiteSpacedKey(String whiteSpacedKey) {
		this.whiteSpacedKey = whiteSpacedKey;
	}

	public Float getMyFancyFloat() {
		return myFancyFloat;
	}

	public void setMyFancyFloat(Float myFancyFloat) {
		this.myFancyFloat = myFancyFloat;
	}
}