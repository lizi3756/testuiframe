package com.frame.element;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

/**
* @author 作者 lizi
* 元素类，去掉getter/setter和全参的构造方法
* @version 创建时间：2020年12月15日 下午4:25:36

*/
@Data
@AllArgsConstructor
public class ElementBeans {
	private String value;
	private int timout;
	private ByType byType;
    private String elementName;

	public enum ByType {
		xpath, id, linkText, name, className, tagName, partialLinkText,cssSelector
	}
	public By getBy() {
		switch (byType) {
		case xpath :
            return By.xpath(value);
        case id:
            return By.id(value);
        case cssSelector:
            return By.cssSelector(value);
        case name:
            return By.name(value);
        case className:
            return By.className(value);
        case linkText:
            return By.linkText(value);
        case partialLinkText:
            return By.partialLinkText(value);
        case tagName:
            return By.tagName(value);
        default :
            return By.xpath(value);
		}
	}
}
