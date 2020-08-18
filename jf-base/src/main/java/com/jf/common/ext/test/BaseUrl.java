package com.jf.common.ext.test;

/**
 * 目标url
 * 
 * @author Chen Wei
 *
 */
public class BaseUrl {
	/**
	 * 域名，如"127.0.0.1"
	 */
	private String domain;
	
	/**
	 * 端口, 如 8080。默认为80
	 */
	private int port;
	
	/**
	 * 路径,如/test/goodsList。默认为"/"
	 */
	private String path;
	
	private boolean isSecure = false;

	public BaseUrl(String domain, int port, String path) {
		this.domain = domain;
		this.port = port;
		this.path = path;
	}
	
	public BaseUrl(String domain, int port, String path, boolean isSecure) {
		this.domain = domain;
		this.port = port;
		this.path = path;
		this.isSecure = isSecure;
	}
	
	public BaseUrl(String domain, String path) {
		this(domain, 80, path);
	}
	
	public BaseUrl(String domain) {
		this(domain, "/");
	}
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public boolean isSecure() {
		return isSecure;
	}

	public void setSecure(boolean isSecure) {
		this.isSecure = isSecure;
	}

	@Override
	public String toString() {
		if (isSecure) {
			if (port == 80) {
				return "https://" + domain + path;
			}
			
			return "https://" + domain + ":" + port + path;
		}
		
		if (port == 80) {
			return "http://" + domain + path;
		}
		return "http://" + domain + ":" + port + path;
	}
	
}
