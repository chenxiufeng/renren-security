package io.renren.common.utils;


import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {


	private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);


	private final static ObjectMapper objectMapper = new ObjectMapper();

	private JsonUtils() {

	}

	public static ObjectMapper getInstance() {

		return objectMapper;
	}

	/**
	 * javaBean,list,array convert to json string
	 */
	public static String objectTojson(Object obj) throws Exception {
		return objectMapper.writeValueAsString(obj);
	}

	/**
	 * json string convert to javaBean
	 */
	public static <T> T json2pojo(String jsonStr, Class<T> clazz)
			throws Exception {
		return objectMapper.readValue(jsonStr, clazz);
	}

	/**
	 * json string convert to map
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<String, Object> json2map(String jsonStr)
			throws Exception {
		return objectMapper.readValue(jsonStr, Map.class);
	}

	/**
	 * json string convert to map with javaBean
	 */
	public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz)
			throws Exception {
		Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr,
				new TypeReference<Map<String, T>>() {
				});
		Map<String, T> result = new HashMap<String, T>();
		for (Entry<String, Map<String, Object>> entry : map.entrySet()) {
			result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
		}
		return result;
	}

	/**
	 * json array string convert to list with javaBean
	 */
	public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz)
			throws Exception {
		List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr,
				new TypeReference<List<T>>() {
				});
		List<T> result = new ArrayList<T>();
		for (Map<String, Object> map : list) {
			result.add(map2pojo(map, clazz));
		}
		return result;
	}

	/**
	 * map convert to javaBean
	 */
	public static <T> T map2pojo(Map map, Class<T> clazz) {
		return objectMapper.convertValue(map, clazz);
	}


	/**
	 * 将对象转换为 JSON 的字符串格式
	 *
	 * @param object
	 * @return
	 */
	public static String object2String(Object object) {
		return object2String(object, false);
	}

	public static String object2String(Object object, boolean prettyFormat) {
		StringWriter writer = new StringWriter();
		try {
			if (prettyFormat) {
				objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, object);
			} else {
				objectMapper.writeValue(writer, object);
			}
		} catch (Exception e) {
			log.error("将 object 转换为 json 字符串时发生异常:{}", e);
			return null;
		}
		return writer.toString();
	}


}
