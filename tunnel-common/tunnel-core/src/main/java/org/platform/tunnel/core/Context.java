package org.platform.tunnel.core;

import java.util.*;

/**
 * @author twcao
 * @description 上下文
 * @project data-tunnel-parent
 * @classname Context
 * @date 2020/1/3 11:59
 */
public class Context {

    private Map<String, String> parameters;

    public Context() {
        this.parameters = new HashMap<>();
    }

    public Context(Map<String, String> parameters) {
        this();
        if(parameters != null) {
            this.parameters = parameters;
        }
    }

    /**
     * Removes all of the mappings from this map.
     */
    public void clear() {
        if(parameters.size() > 0) {
            parameters.clear();
        }
    }

    /**
     * Gets value mapped to key, returning null if unmapped.
     * @param key
     * @return
     */
    public Boolean getBoolean(String key) {
        return getBoolean(key, null);
    }

    /**
     * Gets value mapped to key, returning defaultValue if unmapped.
     * @param key
     * @param defaultValue
     * @return
     */
    public Boolean getBoolean(String key, Boolean defaultValue) {
        if(this.parameters.containsKey(key)) {
            return "true".equals(this.parameters.get(key)) ? true : false;
        }
        return defaultValue;
    }

    /**
     * Gets value mapped to key, returning null if unmapped.
     * @param key
     * @return
     */
    public Integer getInteger(String key) {
        return getInteger(key, null);
    }

    /**
     * Gets value mapped to key, returning defaultValue if unmapped.
     * @param key
     * @param defaultValue
     * @return
     */
    public Integer getInteger(String key, Integer defaultValue) {
        if(this.parameters.containsKey(key)) {
            try {
                return Integer.parseInt(this.parameters.get(key));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    /**
     * Gets value mapped to key, returning null if unmapped.
     * @param key
     * @return
     */
    public Long getLong(String key) {
        return getLong(key, null);
    }

    /**
     * Gets value mapped to key, returning defaultValue if unmapped.
     * @param key
     * @param defaultValue
     * @return
     */
    public Long getLong(String key, Long defaultValue) {
        if(this.parameters.containsKey(key)) {
            try {
                return Long.parseLong(this.parameters.get(key));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    /**
     *  return parameters
     * @return
     */
    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public String getString(String key) {
        return getString(key, null);
    }

    /**
     * Gets value mapped to key, returning defaultValue if unmapped.
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(String key, String defaultValue) {
        if(this.parameters.containsKey(key)) {
            return null == this.parameters.get(key) ? defaultValue : this.parameters.get(key);
        }
        return defaultValue;
    }

    /**
     * put a element to paramters
     * @param key
     * @param value
     * @param override
     */
    public void put(String key, String value, boolean override) {
        if(override) {
            this.parameters.put(key,value);
        }
    }

    /**
     * put a element to paramters, override element
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        put(key, value, true);
    }

    /**
     * put some paramters
     * @param map
     */
    public void putAll(Map<String, String> map) {
        this.parameters.putAll(map);
    }

    public List<String> getPrefix(String ... prefix) {
        List<String> prefixs = new ArrayList<>();
        Iterator<Map.Entry<String, String>> iterator = this.parameters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            for(String pre : prefix) {
                if(pre == null || pre.equals("")) {
                    continue;
                }
                if(key.startsWith(pre)) {
                    prefixs.add(entry.getValue());
                }
            }
        }
        return prefixs;
    }
    /**
     * toString
     * @return
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Iterator<Map.Entry<String, String>> iterator = this.parameters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(";");
        }
        return sb.toString();
    }
}
