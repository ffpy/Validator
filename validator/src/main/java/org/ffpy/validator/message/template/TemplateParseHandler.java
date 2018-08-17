package org.ffpy.validator.message.template;

import org.ffpy.validator.exception.ValidatorException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板配置文件解析器。
 */
public class TemplateParseHandler extends DefaultHandler {
	/** 模板节点的名称 */
	private static final String NODE_TEMPLATE = "template";
	/** 模板ID的名称 */
	private static final String ATTR_ID = "id";
	/** 模板ID与模板内容的映射 */
	private Map<String, String> templates = new HashMap<>();
	/** 模板ID */
	private String id;
	/** 模板内容 */
	private StringBuilder value = new StringBuilder();

	/**
	 * 获取模板。
	 *
	 * @return 模板
	 */
	public Map<String, String> getTemplates() {
		return templates;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// 只处理message节点
		if (NODE_TEMPLATE.equals(qName)) id = attributes.getValue(ATTR_ID);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		value.append(new String(ch, start, length));
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (id == null) return;
		// 检查模板ID是否为空
		if (id.isEmpty())
			throw new ValidatorException(qName + "的" + ATTR_ID + "属性不能为空");
		// 检查模板内容是否为空
		if (value.length() == 0)
			throw new ValidatorException(qName + "的内容不能为空");
		// 放入到映射中
		templates.put(id, value.toString().trim());
		// 重置
		value.delete(0, value.length());
		id = null;
	}
}
