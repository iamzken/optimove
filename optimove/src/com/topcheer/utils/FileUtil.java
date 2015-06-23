package com.topcheer.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class FileUtil {
	/**
	 * �½�Ŀ¼
	 * 
	 * @param folderPath
	 *            Ŀ¼
	 * @return ����Ŀ¼�������·��
	 */
	public static String createFolder(String folderPath) {
		String txt = folderPath;
		try {
			java.io.File myFilePath = new java.io.File(txt);
			txt = folderPath;
			if (!myFilePath.exists()) {
				myFilePath.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return txt;
	}
	
	/**
	 * �½��ļ�
	 * 
	 * @param folderPath
	 *            �ļ�
	 * @return �����ļ�
	 */
	public static void createEmptyFile(String fileName) {
		File f=new File(fileName);
		try{
			if(!f.exists())		f.createNewFile();
		} catch(IOException e) {
			System.out.println("�����ļ�ʧ��"); 
		}
	}

	/**
	 * �༶Ŀ¼����
	 * @param folderPath ׼��Ҫ�ڱ���Ŀ¼�´�����Ŀ¼��Ŀ¼·�� ���� c:myf
	 * @param paths ���޼�Ŀ¼����������Ŀ¼�Ե��������� ���� a|b|c
	 * @return ���ش����ļ����·�� ���� c:myfa c
	 */
	public static String createFolders(String folderPath, String paths) {
		String txts = folderPath;
		try {
			String txt;
			txts = folderPath;
			StringTokenizer st = new StringTokenizer(paths, "|");
			for (int i = 0; st.hasMoreTokens(); i++) {
				txt = st.nextToken().trim();
				if (txts.lastIndexOf("/") != -1) {
					txts = createFolder(txts + txt);
				} else {
					txts = createFolder(txts + txt + "/");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return txts;
	}
	
	/**
	 * ��ȡ�ı��ļ�����
	 * @param filePathAndName ������������·�����ļ���
	 * @param encoding �ı��ļ��򿪵ı��뷽ʽ
	 * @return �����ı��ļ�������
	 */
	public static String readTxt(String filePathAndName, String encoding) throws IOException {
		encoding = encoding.trim();
		StringBuffer str = new StringBuffer("");
		String st = "";
		try {
			FileInputStream fs = new FileInputStream(filePathAndName);
			InputStreamReader isr;
			if (encoding.equals("")) {
				isr = new InputStreamReader(fs);
			} else {
				isr = new InputStreamReader(fs, encoding);
			}
			BufferedReader br = new BufferedReader(isr);
			try {
				String data = "";
				while ((data = br.readLine()) != null) {
					str.append(data + " ");
				}
			} catch (Exception e) {
				str.append(e.toString());
			}
			st = str.toString();
		} catch (IOException es) {
			st = "";
		}
		return st;
	}

	/**
	 * �½��ļ�
	 * 
	 * @param filePathAndName
	 *            �ı��ļ���������·�����ļ���
	 * @param fileContent
	 *            �ı��ļ�����
	 * @return
	 */
	public static void createFile(String filePathAndName, String fileContent) {

		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			myFile.close();
			resultFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ɾ���ļ�
	 * 
	 * @param filePathAndName �ı��ļ���������·�����ļ���
	 * @return Boolean �ɹ�ɾ������true�����쳣����false
	 */
	public static boolean delFile(String filePathAndName) {
		boolean bea = false;
		try {
			String filePath = filePathAndName;
			File myDelFile = new File(filePath);
			if (myDelFile.exists()) {
				myDelFile.delete();
				bea = true;
			} else {
				bea = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bea;
	}

	/**
	 * ɾ��ָ���ļ����ļ����������ļ�
	 * @param path �ļ�����������·��
	 * @return
	 * @return
	 */
	public static boolean delFolder(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return false;
		}
		if (!file.isDirectory()) {
			file.delete();
			return true;
		}
		File[] tempList = file.listFiles();
		for (int i = 0; i < tempList.length; i++) {
			if(tempList[i].isDirectory()){
				delFolder(tempList[i].getPath());
				tempList[i].delete();
			}
			else{
				tempList[i].delete();
			}
		}
		file.delete();
		return true;
	}

	/**
	 * ���Ƶ����ļ�
	 * @param oldPathFile׼�����Ƶ��ļ�Դ
	 * @param newPathFile
	 *            �������¾���·�����ļ���
	 * @return
	 */
	public static void copyFile(String oldPathFile, String newPathFile) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPathFile);
			if (oldfile.exists()) { // �ļ�����ʱ
				InputStream inStream = new FileInputStream(oldPathFile); // ����ԭ�ļ�
				FileOutputStream fs = new FileOutputStream(newPathFile);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // �ֽ��� �ļ���С
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���������ļ��е�����
	 * 
	 * @param oldPath
	 *            ׼��������Ŀ¼
	 * @param newPath
	 *            ָ������·������Ŀ¼
	 * @return
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			new File(newPath).mkdirs(); // ����ļ��в����� �������ļ���
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}
				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// ��������ļ���
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ƶ��ļ�
	 * 
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);
	}

	/**
	 * �ƶ�Ŀ¼
	 * 
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
	}
	
	/**
	 * �������ļ�����
	 * @return
	 */
	public static String getFileRenameName(){
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
		String timeNow = simpleDateFormat.format(date);
		return timeNow;
	}
	
	
	/**
	 * ���ַ�����д�� BufferedWriter and FileWriter
	 * @param FileName
	 * @param FileContent
	 */
	public static void fileWrite(String FileName, String FileContent) throws Exception{
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			File file = new File(FileName);
			 if (!file.exists()) {  
	             file.createNewFile();
	         }  
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write(FileContent);
			bw.flush();
		} catch (FileNotFoundException e) {
			throw e;
		} finally {
			try {
				if(bw != null) bw.close();
				if(fw != null) fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/** 
     * �����ļ� 
     * @param response  HttpServletResponse 
     * @param path  �ļ�·��,�����ļ���,����
     * @return 
     */  
    public static boolean downloadFile(HttpServletResponse response, File file) {
		ServletOutputStream out = null;
		FileInputStream fis = null;
		boolean bool = false;
		
		try{
			response.setContentType("application/unknow");
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));

			out = response.getOutputStream();
			fis = new FileInputStream(file);
			byte[] buf = new byte[1024];
			int readLen = 0;
			while ((readLen = fis.read(buf, 0, 1024))!= -1){
				out.write(buf, 0, readLen);
			}
			bool = true;
		}catch (Exception e){
			bool = false;
			e.printStackTrace();
			System.out.println("�ļ������쳣��"+e.getMessage());
		}finally{
			try {
				if(out != null)	out.close();
				if(fis != null)	fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bool;
	}  
	
	public static void main(String [] args){
	
	}
}