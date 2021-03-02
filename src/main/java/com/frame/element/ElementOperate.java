package com.frame.element;

import com.frame.driver.DriverBase;
import com.frame.utils.RandomUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import java.util.List;

/**
 *
 * @author 作者 lizi
 * 元素操作
 * @version 创建时间：2020年2月15日 下午7:01:32
 *
 */
public class ElementOperate {
	private static Logger log = Logger.getLogger(ElementOperate.class);
	DriverBase driver;
	Actions action;// web端手势操作
	public ElementOperate(DriverBase driver) {
		this.driver=driver;
		this.action = new Actions(driver.getDriver());//driver.getDriver()的类型是Webdriver

	}
	//<locator type="linkText" timeout="3" value="登录"  desc="登录链接">loginlink</locator>
	public void sendKeys(ElementBeans elementBeans, String str) {
		driver.findElement(elementBeans).sendKeys(str);
		log.info("向【" + elementBeans.getElementName() + "】输入【" + str + "】成功");
	}

	public void sendKeysUtilCorrect(ElementBeans elementBeans, String str) {
		driver.findElement(elementBeans).sendKeys(str);
		String attribute = this.getAttribute(elementBeans, "value");
		while (!attribute.equals(str)) {
			log.info("向【" + elementBeans.getElementName() + "】输入【" + attribute + "】成功");
			this.clear(elementBeans);
			driver.findElement(elementBeans).sendKeys(str);
			attribute = this.getAttribute(elementBeans, "value");
		}
		log.info("向【" + elementBeans.getElementName() + "】输入【" + str + "】成功");
	}

	public void sendKeysClear(ElementBeans elementBeans, String str) {
		this.clear(elementBeans);
		driver.findElement(elementBeans).sendKeys(str);
		log.info("向【" + elementBeans.getElementName() + "】输入【" + str + "】成功");
	}

	/**
	 * 该方法用于输入和原来输入框内容不一样的值
	 *
	 * @param elementBeans
	 * @param length
	 * @return
	 */
	public String sendKeysDiff(ElementBeans elementBeans, int length) {
		String currentName = this.getAttribute(elementBeans, "value");
		String newName = RandomUtil.getRndStrZhByLen(length);
		while (currentName.equals(newName)) {
			newName = RandomUtil.getRndStrZhByLen(length);
		}
		this.clear(elementBeans);
		this.sendKeys(elementBeans, newName);
		return newName;
	}

	public void click(ElementBeans elementBeans) {
		driver.findElement(elementBeans).click();
		log.info("点击【" + elementBeans.getElementName() + "】成功");
	}

	public void clickToBeClickable(ElementBeans elementBeans) {
		WebElement element = new WebDriverWait(driver.getDriver(), elementBeans.getTimout())
				.until(ExpectedConditions.elementToBeClickable(elementBeans.getBy()));
		element.click();
		log.info("在" + elementBeans.getTimout() + "秒内点击【" + elementBeans.getElementName() + "】成功");
	}

	public String getAttribute(ElementBeans elementBeans, String name) {
		String attributeValue = driver.findElement(elementBeans).getAttribute(name);
		log.info("获取【" + elementBeans.getElementName() + "】的属性【" + name + "】结果是：" + attributeValue);
		return attributeValue;
	}

	public void clear(ElementBeans elementBeans) {
		driver.findElement(elementBeans).clear();
		log.info("清除【" + elementBeans.getElementName() + "】成功");
	}

	public void getScreenshotAs(ElementBeans elementBeans, String filename) {
		File file = driver.findElement(elementBeans).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(filename));
			log.info("针对元素【" + elementBeans.getElementName() + "】截图成功，图片是：" + filename);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("针对元素【" + elementBeans.getElementName() + "】截图失败", e);
		}
	}

	public String getText(ElementBeans elementBeans) {
		// TODO Auto-generated method stub
		String text = driver.findElement(elementBeans).getText();
		log.info("获取【" + elementBeans.getElementName() + "】的文本是：" + text);
		return text;
	}

	public void moveToElement(ElementBeans elementBeans) {
		WebElement element = driver.findElement(elementBeans);
		action.moveToElement(element).perform();
		log.info("鼠标悬浮到元素【" + elementBeans.getElementName() + "】完成");
	}

	public void clickTap() {
		action.sendKeys(Keys.TAB).perform();
		log.info("Tap键点击完成");
	}

	public void clickEnter() {
		action.sendKeys(Keys.ENTER).perform();
		log.info("Enter键点击完成");
	}

	/**
	 * web端上传文件操作，调用该方法前请先加几秒等待，等待文件管理器打开，否则会失败，适用windows
	 *
	 * @param file
	 * @throws Exception
	 */
	public void uploadFile(String file) throws Exception {
		// String file="C:\\Users\\LXG\\Desktop\\115.png";
		// 基于文件路径构建StringSelection对象
		StringSelection stringSelection = new StringSelection(file);
		// 通过TookKit将文件路径黏贴到剪贴板
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	/**
	 * 该方法用于随机选择下拉框里和当前选中的值不一样的值
	 *
	 * @param locator
	 * @return
	 */
	public String selectRandomOption(ElementBeans elementBeans) {
		Select select = new Select(driver.findElement(elementBeans));
		String curName="";
		try {
			curName = select.getFirstSelectedOption().getText();
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println(curName);
		log.info("下拉框【" + elementBeans.getElementName() + "】当前值是：" + curName);
		List<WebElement> allOptions = select.getOptions();
		int index = RandomUtil.randomInt(1, allOptions.size() - 1);
		// System.out.println(index+"index");
		select.selectByIndex(index);
		String newName = select.getFirstSelectedOption().getText();
		while (curName.equals(newName)) {
			index = RandomUtil.randomInt(1, allOptions.size() - 1);
			select.selectByIndex(index);
			newName = select.getFirstSelectedOption().getText();
		}
		log.info("下拉框【" + elementBeans.getElementName() + "】选择的新值是：" + newName);
		return newName;
	}

	public String getFirstSelected(ElementBeans elementBeans) {
		Select select = new Select(driver.findElement(elementBeans));
		String curName = select.getFirstSelectedOption().getText();
		log.info("获取下拉框【" + elementBeans.getElementName() + "】当前值是：" + curName);
		return curName;
	}

	public void selectOption(ElementBeans elementBeans, Object o) {
		Select select = new Select(driver.findElement(elementBeans));
		if (o instanceof Integer) {
			select.selectByIndex(((Integer) o).intValue());
			log.info("选择下拉框【" + elementBeans.getElementName() + "】索引：" + o.toString());

		} else if (o instanceof String) {
			try {
				select.selectByValue(o.toString());
				log.info("通过value值选择下拉框【" + elementBeans.getElementName() + "】值为：" + o.toString());
			} catch (Exception e) {
				// TODO: handle exception
				select.selectByVisibleText(o.toString());
				log.info("通过文本选择下拉框【" + elementBeans.getElementName() + "】文本为：" + o.toString());
			}
		}
	}

	public String selectRadio(ElementBeans elementBeans) {
		String radioValue = "";
		List<WebElement> radios = driver.findElements(elementBeans);
		for (WebElement we : radios) {
			if (!we.isSelected()) {
				we.click();
				radioValue = we.getAttribute("value");
				break;
			}
		}
		return radioValue;
	}

	/**
	 * 判断元素是否存在
	 *
	 * @param elementBeans
	 * @return boolean
	 */
	public boolean isElementExist(ElementBeans elementBeans) {
		driver.implicitlyWaitZero();
		try {
			driver.findElement(elementBeans).isDisplayed();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("元素【" + elementBeans.getElementName() + "】不存在");
			return false;
		} finally {
			driver.implicitlyWaitDefault();
		}
	}

	/**
	 * 判断元素是否存在
	 *
	 * @param elementBeans
	 * @return boolean
	 */
	public boolean isElementExistTimeOut(ElementBeans elementBeans) {
		driver.implicitlyWaitZero();
		try {
			driver.findElementTimeOut(elementBeans).isDisplayed();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("元素【" + elementBeans.getElementName() + "】在"+elementBeans.getTimout()+"秒内未找到");
			return false;
		} finally {
			driver.implicitlyWaitDefault();
		}
	}




}
