package com.topcheer.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * ftp上传下载
 * 
 * @author lhs
 * 
 */
public class FTPUtil {
	private static final Logger logger = Logger.getLogger(FTPUtil.class);
	static String url = ""; 
	static int port = 0;
	static String userName = "";
	static String password = "";
	static int connOverTime = 0;
	static String ftpFilePath = "";
	
	static {
		url = JavaUtils.getPropertiesVal("config", "url");
		port = Integer.parseInt(JavaUtils.getPropertiesVal("config", "port"));
		userName = JavaUtils.getPropertiesVal("config", "userName");
		password = JavaUtils.getPropertiesVal("config", "password");
		connOverTime = Integer.parseInt(JavaUtils.getPropertiesVal("config", "connOverTime"));
		ftpFilePath = JavaUtils.getPropertiesVal("config", "ftpFilePath");
	}
	/**
	 * ftp上传方法
	 * 
	 * @param path
	 *            上传路径
	 * @param filename
	 *            上传后的文件名
	 * @param input
	 * @return 1 上传FTP成功
	 */
	public static String uploadFile(String sourcePath, String sourceFileName, String targetFileName) {
		String retResult = "1";
		FileInputStream fis = null;
		final FTPClient ftpclient =  new FTPClient();;
		ExecutorService exec = null;
		if(!new File(sourcePath).exists()) FileUtil.createFolder(sourcePath);
		String filePathName = sourcePath + File.separator + sourceFileName;
		if(!new File(filePathName).exists()){
			System.out.println("filePathName="+filePathName);
			return "上传文件不存在！";
		} 
		
		try {
			fis = new FileInputStream(filePathName);		
			exec = Executors.newFixedThreadPool(1);
			int reply;
			//使用线程池，控制FTP连接服务器的时间
			Callable<String> call = new Callable<String>(){
				public String call() throws Exception {
					ftpclient.connect(url, port);// 连接FTP服务器,如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
					return "ftp服务器登陆成功";
				}
			};
			Future<String> future = exec.submit(call);
			//设置最大连接时间
			future.get(connOverTime, TimeUnit.MILLISECONDS);
			ftpclient.login(userName, password);// 登录
			ftpclient.setFileType(FTPClient.BINARY_FILE_TYPE);//传输二进制文件,如果不设置会造成压缩文件损坏
			ftpclient.setControlEncoding("GBK");
			reply = ftpclient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpclient.disconnect();
			}
			ftpclient.changeWorkingDirectory(ftpFilePath);
			ftpclient.storeFile(targetFileName, fis);
		} catch (ConnectException e) {
			logger.error("获取FTP连接拒绝:"+e.getMessage());
			retResult = "获取FTP连接拒绝！";
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("上传FTP写文件错误:"+e.getMessage());
			retResult = "上传FTP写文件错误！";
		} catch (TimeoutException e) {
			logger.error("上传FTP超时（超时时间为"+connOverTime+"毫秒）:"+e.getMessage());
			retResult = "上传FTP超时（超时时间为"+connOverTime+"毫秒）！";
		} catch (Exception e) {
			logger.error("上传FTP文件错误:"+e.getMessage());
			retResult = "上传FTP文件错误！";
		}finally {
			try {
				if(fis != null)	fis.close();
				if (ftpclient.isConnected()) {
					ftpclient.logout();
					ftpclient.disconnect();
				}
				if(exec != null)	exec.shutdown();
			} catch (Exception ioe) {
				logger.error("关闭FTP连接错误:"+ioe.getMessage());
				retResult = "关闭FTP连接错误";
			}
		}
		return retResult;
	}

	/**
	 * ftp下载方法
	 * 
	 * @param url
	 * @param port
	 * @param username
	 * @param password
	 * @param remotePath
	 * @param fileName
	 * @param localPath
	 * @return
	 */
	public static boolean downFile(String remotePath, String localPath, String fileName) throws Exception {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(userName, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());

					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}

			ftp.logout();
			success = true;
		} catch(ConnectException e){
			throw e;
		}catch (Exception e) {
			throw e;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					throw ioe;
				}
			}
		}
		return success;
	}
	
	public static void main(String[] args) {
		FTPUtil ftpUtil = new FTPUtil();
		try {
			System.out.println(ftpUtil.uploadFile("", "d:\\7linc_table_data0630.pde","cc.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
