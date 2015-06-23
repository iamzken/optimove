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
 * ftp�ϴ�����
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
	 * ftp�ϴ�����
	 * 
	 * @param path
	 *            �ϴ�·��
	 * @param filename
	 *            �ϴ�����ļ���
	 * @param input
	 * @return 1 �ϴ�FTP�ɹ�
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
			return "�ϴ��ļ������ڣ�";
		} 
		
		try {
			fis = new FileInputStream(filePathName);		
			exec = Executors.newFixedThreadPool(1);
			int reply;
			//ʹ���̳߳أ�����FTP���ӷ�������ʱ��
			Callable<String> call = new Callable<String>(){
				public String call() throws Exception {
					ftpclient.connect(url, port);// ����FTP������,�������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
					return "ftp��������½�ɹ�";
				}
			};
			Future<String> future = exec.submit(call);
			//�����������ʱ��
			future.get(connOverTime, TimeUnit.MILLISECONDS);
			ftpclient.login(userName, password);// ��¼
			ftpclient.setFileType(FTPClient.BINARY_FILE_TYPE);//����������ļ�,��������û����ѹ���ļ���
			ftpclient.setControlEncoding("GBK");
			reply = ftpclient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpclient.disconnect();
			}
			ftpclient.changeWorkingDirectory(ftpFilePath);
			ftpclient.storeFile(targetFileName, fis);
		} catch (ConnectException e) {
			logger.error("��ȡFTP���Ӿܾ�:"+e.getMessage());
			retResult = "��ȡFTP���Ӿܾ���";
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("�ϴ�FTPд�ļ�����:"+e.getMessage());
			retResult = "�ϴ�FTPд�ļ�����";
		} catch (TimeoutException e) {
			logger.error("�ϴ�FTP��ʱ����ʱʱ��Ϊ"+connOverTime+"���룩:"+e.getMessage());
			retResult = "�ϴ�FTP��ʱ����ʱʱ��Ϊ"+connOverTime+"���룩��";
		} catch (Exception e) {
			logger.error("�ϴ�FTP�ļ�����:"+e.getMessage());
			retResult = "�ϴ�FTP�ļ�����";
		}finally {
			try {
				if(fis != null)	fis.close();
				if (ftpclient.isConnected()) {
					ftpclient.logout();
					ftpclient.disconnect();
				}
				if(exec != null)	exec.shutdown();
			} catch (Exception ioe) {
				logger.error("�ر�FTP���Ӵ���:"+ioe.getMessage());
				retResult = "�ر�FTP���Ӵ���";
			}
		}
		return retResult;
	}

	/**
	 * ftp���ط���
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
			// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
			ftp.login(userName, password);// ��¼
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(remotePath);// ת�Ƶ�FTP������Ŀ¼
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
