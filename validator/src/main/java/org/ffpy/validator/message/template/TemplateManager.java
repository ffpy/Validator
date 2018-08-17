package org.ffpy.validator.message.template;

import org.ffpy.validator.exception.ValidatorException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板管理器。
 */
public class TemplateManager {
	/** 默认模板文件名 */
	private static final String DEFAULT_FILENAME = "validator-template.xml";
	/** 模板ID与模板内容的映射 */
	private static final Map<String, String> templates = new HashMap<>();

	static {
		init(DEFAULT_FILENAME);
	}

	/**
	 * 初始化模板管理器。
	 *
	 * @param filename 模板文件名，从classpath下查找。
	 */
	public static void init(String filename) {
		templates.clear();
		try {
			// 创建SAX解析器
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			TemplateParseHandler handler = new TemplateParseHandler();
			// 获取模板文件流
			InputStream is = TemplateParseHandler.class.getClassLoader()
					.getResourceAsStream(filename);
			if (is == null)
				throw new ValidatorException("找不到" + filename);
			// 读取配置文件
			parser.parse(is, handler);
			templates.putAll(handler.getTemplates());
		} catch (Exception e) {
			throw new ValidatorException("解析" + filename + "失败", e);
		}
	}

	/**
	 * 根据ID获取模板。
	 *
	 * @param id 模板ID
	 * @return 模板内容
	 */
	public static String getTemplate(String id) {
		String template = templates.get(id);
		if (template == null)
			throw new ValidatorException("id为" + id + "的模板不存在");
		return template;
	}
}
