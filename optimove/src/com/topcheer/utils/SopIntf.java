package com.topcheer.utils;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Li Yaodong
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

public class SopIntf {
	public static String CharacterEncoding = "GBK"; // 全局字符集

	public static String SOP_SUCC = "AAAAAAA";

	public static String SOP_SYSERR = "SOP";

	public static String ERR_NOTFOUND = "SOP0100"; // 数据项不存在

	public static String ERR_SYSERROR = "SOP0000"; // 系统错误

	public static String ERR_NOCONNECT = "SOP0002"; // 连接失败

	public static String ERR_IOERROR = "SOP0003"; // 网络读写错误

	public static String ERR_ASSEMBPKT = "SOP0006"; // 打包失败

	public static String ERR_DISASSPKT = "SOP0008"; // 拆包错误

	public static boolean DEBUG = false; // 打印调试信息

	public static String SYSCFG_SEP = "\t"; // syscfg文件分隔符

	public static String MAPFILE_SEP = " "; // mapfile文件分隔符

	public static String TPU_RETMSG = "TPU_RETMSG"; // sop返回状态信息存放的key

	public static String TPU_RETCODE = "TPU_RETCODE"; // “返回码”字段在syscfg中的名称

	public static String PDTRCD = "PDTRCD"; // “交易”字段在syscfg中的名称

	public static String FDCOMMLN = "FDCOMMLN"; // “交易长度”字段在syscfg中的名称

	// public static final int USE_COP = 1;
	static final int CONVERT_ERROR_SEND_MAPFILE_READ = -6;

	static final int CONVERT_ERROR_NO_ENOUGH_DATA = -7;

	static final int CONVERT_ERROR_RECV_MAPFILE_READ = -8;

	static final byte F_SPECIAL_CHAR = (byte) 0xfe;

	static final byte F_SUPER_LEN_SIGN = (byte) 0xff;

	static final int BYTE_MAX_LEN = 0x0fa;

	static final int PROT_SOP = 0;

	static final int PROT_IPP = 1;

	static final int PROT_TUX = 2;

	int flag = 0;

	int iVal;

	HashMap pool = new HashMap(30);

	HashMap pool2;

	byte[] buffer;

	String[] inmap;

	String[] outmap;

	public String code;

	public String errmsg;

	public String errcode;

	public static String home;

	public static String separator;

	static String g_aczHost;

	static String g_aczHostC;

	static int g_iPort = 6005;

	static int g_iPortC = 20032;

	static int g_iProtocol = PROT_IPP;

	public static boolean initEnv(String flag) {
		try {
			InputStream is = null;

			String s = home + separator + "syscfg" + separator + "wop.xml";
			File f = new File(s);
			if (f.exists())
				is = new FileInputStream(f);
			if (is == null)
				return false;

			org.jdom.input.SAXBuilder sb = new org.jdom.input.SAXBuilder();
			org.jdom.Document doc = sb.build(is);
			org.jdom.Element fld, param;
			fld = doc.getRootElement().getChild("Config");
			param = fld.getChild("sopurl_" + flag);

			if (param != null) {
				g_aczHost = param.getText().trim();
				int t = g_aczHost.indexOf("://");
				if (t > 0) {
					if (g_aczHost.startsWith("sop"))
						g_iProtocol = PROT_SOP;
					else if (g_aczHost.startsWith("tux"))
						g_iProtocol = PROT_TUX;
					else
						g_iProtocol = PROT_IPP;
					g_aczHost = g_aczHost.substring(t + 3);
				}
				t = g_aczHost.indexOf(':');

				if (t > 0) {
					g_iPort = Integer.parseInt(g_aczHost.substring(t + 1));
					g_aczHost = g_aczHost.substring(0, t);
				}
			}
			param = fld.getChild("crypturl_" + flag);
			if (param != null) {
				g_aczHostC = param.getText().trim();
				int t = g_aczHostC.indexOf(':');
				if (t > 0) {
					g_iPortC = Integer.parseInt(g_aczHostC.substring(t + 1));
					g_aczHostC = g_aczHostC.substring(0, t);
				}
			}
			return true;
		} catch (Exception err) {
			err.printStackTrace();
		}
		return false;
	}

	int sopCall(byte[] in, byte[] out, int ilen, int olen) {
		Socket socket;

		try {
			// InetAddress addr = InetAddress.getByName(g_aczHost);
			socket = new Socket(g_aczHost, g_iPort);
			socket.setSoTimeout(3 * 60 * 1000);
		} catch (IOException e) {
			return -2;
		}
		try {
			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();
			String prefix = null;
			if (DEBUG) {
				prefix = home + separator + "log" + separator + "SOP_" + code
						+ "_" + System.currentTimeMillis();
				String fname = prefix + "_S.dat";
				OutputStream outf = new FileOutputStream(fname);
				outf.write(in, 0, ilen);
				outf.close();
			}
			if (g_iProtocol == PROT_SOP) {
				out[0] = 0;
				out[1] = 0;
				out[2] = (byte) ((ilen >> 8) & 0x0ff);
				out[3] = (byte) (ilen & 0x0ff);
				output.write(out, 0, 4);
				int x = ilen / 1024;
				int y = ilen % 1024;
				if (x > 0) {
					out[2] = 4;
					out[3] = 0;
					int z = 0;
					for (int i = 0; i < x; i++) {
						output.write(in, z, 1024);
						z += 1024;
						if (i < x - 1)
							output.write(out, 0, 4);
					}
					if (y > 0) {
						out[2] = (byte) ((y >> 8) & 0x0ff);
						out[3] = (byte) (y & 0x0ff);
						output.write(out, 0, 4);
						output.write(in, z, y);
					}
				} else {
					out[2] = 4;
					out[3] = 0;
					output.write(in, 0, ilen);
				}
			} else
				output.write(in, 0, ilen);
			if (g_iProtocol == PROT_SOP) {
				if (input.read(out, 0, 4) < 4)
					return -3;
			}
			if (input.read(out, 0, 2) < 2)
				return -3;

			int len, left, ptr, t;
			if ((len = out[0]) < 0)
				len += 256;
			len <<= 8;
			if (out[1] < 0)
				len += 256 + out[1];
			else
				len += out[1];
			if (len > olen)
				return -1;

			if (g_iProtocol == PROT_SOP) {
				int x = len / 1024;
				int y = len % 1024;
				ptr = 2;
				if (x == 0)
					left = y - 2;
				else {
					left = 1022;
					for (int i = 0; i < x; i++) {
						while ((t = input.read(out, ptr, left)) > 0) {
							ptr += t;
							if ((left -= t) <= 0)
								break;
						}
						if (left > 0)
							return -3;

						left = 1024;
						if (y > 0 || i < x - 1) {
							if (input.read(out, ptr, 4) < 4)
								return -3;
						}
					}
					left = y;
				}
				if (left > 0) {
					while ((t = input.read(out, ptr, left)) > 0) {
						if ((left -= t) <= 0)
							break;

						ptr += t;
					}
					if (left > 0)
						return -3;
				}
			} else {
				left = len - 2;
				ptr = 2;
				while ((t = input.read(out, ptr, left)) > 0) {
					if ((left -= t) <= 0)
						break;

					ptr += t;
				}
				if (left > 0)
					return -3;
			}
			if (DEBUG) {
				String fname = prefix + "_R.dat";
				OutputStream outf = new FileOutputStream(fname);
				outf.write(out, 0, len);
				outf.close();
			}
			return len;
		} catch (IOException e) {
			return -3;
		} finally {
			try {
				socket.close();
			} catch (IOException e2) {
			}
		}
	}

	static int crypt(byte[] in, int ilen, byte[] out, int olen) {
		Socket socket;

		try {
			socket = new Socket(g_aczHostC, g_iPortC);
		} catch (IOException e) {
			return -5;
		}
		try {
			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();
			output.write(in, 0, ilen);
			int left = olen;
			int ptr = 0;
			int t;
			while ((t = input.read(out, ptr, left)) > 0) {
				if ((left -= t) <= 0)
					break;

				ptr += t;
			}
			if (left > 0)
				return -5;

			return olen;
		} catch (IOException e) {
		} finally {
			try {
				socket.close();
			} catch (IOException e2) {
			}
		}
		return -5;
	}

	public static String getEnv(String env) {
		try {
			String s;
			Process pp;
			if (separator.equals("/")) {
				String ss[] = { "sh", "-c", "echo $" + env };
				pp = Runtime.getRuntime().exec(ss);
			} else {
				s = "cmd /C echo %" + env + "%";
				pp = Runtime.getRuntime().exec(s);
			}
			InputStream out = pp.getInputStream();
			byte[] b = new byte[200];
			int t = out.read(b);
			if (t > 0) {
				s = new String(b, 0, t);
				int t1;
				t = s.indexOf('\r');
				t1 = s.indexOf('\n');
				if (t > 0 || t1 > 0) {
					if (t <= 0)
						t = t1;
					s = s.substring(0, t);
				}
				return s;
			}
		} catch (Exception e) {
		}
		// String ss = "D:/LENOVO_DEVELOPMENT/DEV PROJECTS/JBUILDER
		// 9/JAVAAPI/sop";
		// return ss;
		return "";
	}

	static int split(String parentStr, String subStr, String temp[], int size) {
		int cnt = 0;
		int oldPos = 0;

		if (parentStr == null)
			parentStr = "";

		int newPos = parentStr.indexOf(subStr);
		int parentLength = parentStr.length();
		int subStrLength = subStr.length();
		if (newPos != -1)
			newPos += subStrLength;
		while ((newPos <= parentLength) && (newPos != -1)) {
			if (cnt < size)
				temp[cnt] = parentStr.substring(oldPos, newPos - subStrLength);

			else
				return cnt;

			cnt++;
			oldPos = newPos;
			newPos = parentStr.indexOf(subStr, oldPos);
			if (newPos != -1)
				newPos += subStrLength;
		}
		if (oldPos < parentLength) {
			if (cnt < size)
				temp[cnt] = parentStr.substring(oldPos);

			else
				return cnt;

			cnt++;
		}
		return cnt;
	}

	public boolean put(String obj, String key, String value) {
		try {
			if (obj != null)
				key = obj + "." + key;
			pool.put(key, value);
			return true;
		} catch (Exception err) {
			err.printStackTrace();
			errmsg = err.getMessage();
		}
		errcode = ERR_SYSERROR;
		return false;
	}

	public boolean put(String obj, String key, byte[] value) {
		try {
			if (obj != null)
				key = obj + "." + key;
			pool.put(key, value);
			return true;
		} catch (Exception err) {
			err.printStackTrace();
			errmsg = err.getMessage();
		}
		errcode = ERR_SYSERROR;
		return false;
	}



	public String getStr(String obj, String key) {
		try {
			if (obj != null)
				key = obj + "." + key;
			Object val = pool.get(key);
			if (val == null) {
				if (pool.containsKey(key))
					return "";
				else {
					errmsg = "该字段不存在";
					errcode = ERR_NOTFOUND;
					return null;
				}
			}
			if (val instanceof byte[])
				return new String((byte[]) val, CharacterEncoding);

			if (val instanceof String)
				return (String) val;

			errmsg = "该字段类型不正确";
		} catch (Exception err) {
			err.printStackTrace();
			errmsg = err.getMessage();
		}
		errcode = ERR_SYSERROR;
		return null;
	}

	public String[] getStrs(String obj, String form, String key) {
		try {
			if (form != null)
				key = form + "_" + key;
			if (obj != null)
				key = obj + "." + key;
			Object val = pool.get(key);
			if (val == null) {
				if (pool.containsKey(key))
					return new String[0];
				else {
					errmsg = "该字段不存在";
					errcode = ERR_NOTFOUND;
					return null;
				}
			}
			if (val instanceof byte[][]) {
				int cnt = ((byte[][]) val).length;
				String[] arr = new String[cnt];
				for (int i = 0; i < cnt; i++)
					arr[i] = new String(((byte[][]) val)[i], CharacterEncoding);
				return arr;
			}
			if (val instanceof String[])
				return (String[]) val;

			errmsg = "该字段类型不正确";
		} catch (Exception ex) {
			errmsg = ex.getMessage();
		}
		errcode = ERR_SYSERROR;
		return null;
	}

	public byte[] get(String obj, String key) {
		try {
			if (obj != null)
				key = obj + "." + key;
			Object val = pool.get(key);
			if (val == null) {
				if (pool.containsKey(key))
					return new byte[0];
				else {
					errmsg = "该字段不存在";
					errcode = ERR_NOTFOUND;
					return null;
				}
			}
			if (val instanceof byte[])
				return (byte[]) val;

			if (val instanceof String)
				return ((String) val).getBytes(CharacterEncoding);

			errmsg = "该字段类型不正确";
		} catch (Exception err) {
			err.printStackTrace();
			errmsg = err.getMessage();
		}
		errcode = ERR_SYSERROR;
		return null;
	}

	public byte[][] gets(String obj, String form, String key) {
		try {
			if (form != null)
				key = form + "_" + key;
			if (obj != null)
				key = obj + "." + key;
			Object val = pool.get(key);
			if (val == null) {
				if (pool.containsKey(key))
					return new byte[0][0];
				else {
					errmsg = "该字段不存在";
					errcode = ERR_NOTFOUND;
					return null;
				}
			}
			if (val instanceof byte[][])
				return (byte[][]) val;

			if (val instanceof String[]) {
				int cnt = ((byte[][]) val).length;
				byte[][] arr = new byte[cnt][];
				for (int i = 0; i < cnt; i++)
					arr[i] = ((String[]) val)[i].getBytes(CharacterEncoding);
				return arr;
			}
			errmsg = "该字段类型不正确";
		} catch (Exception ex) {
			errmsg = ex.getMessage();
		}
		errcode = ERR_SYSERROR;
		return null;
	}

	public byte[] getAttrib(String obj, String form, String key) {
		try {
			if (form != null)
				key = form + "_" + key;
			if (obj != null)
				key = obj + "." + key;
			Object val = pool.get(key + "_ATTRIB");
			return (byte[]) val;
		} catch (Exception ex) {
			errmsg = ex.getMessage();
		}
		return null;
	}

	public int getSize(String obj, String form) {
		int ret = 0;
		try {
			String val = getStr(obj, form);
			if (val == null)
				return -1;

			if (!val.equals(""))
				ret = Integer.parseInt(val);
		} catch (Exception ex) {
			errmsg = ex.getMessage();
			ret = -1;
		}
		return ret;
	}

	int getDduStringMem(byte[] buf, byte[] data, int iCurrPos, int iLen) {
		int len, iLen2;

		try {
			iLen2 = 0;
			while (buf[iCurrPos] == F_SPECIAL_CHAR) {
				iCurrPos++;
				if ((len = buf[iCurrPos]) < 0)
					len = 256 + len;
				iCurrPos += len + 1;
			}
			while (buf[iCurrPos] == F_SUPER_LEN_SIGN) {
				len = BYTE_MAX_LEN;
				iCurrPos++;
				if ((iLen2 + len) <= iLen)
					System.arraycopy(buf, iCurrPos, data, 0, len);
				iLen2 += len;
				iCurrPos += len;
			}
			if ((len = buf[iCurrPos]) < 0)
				len = 256 + len;
			if (len <= BYTE_MAX_LEN) {
				iCurrPos++;
				if ((iLen2 + len) <= iLen)
					System.arraycopy(buf, iCurrPos, data, 0, len);
				iLen2 += len;
				iCurrPos += len;
			}
			if (iLen2 <= iLen) {
				// System.out.println("数字["+iLen2+"]和数字["+iVal+"]");
				iVal = iLen2;
			} else
				iVal = 0;
		} catch (Exception ex) {
			iVal = 0;
			iCurrPos = -1;
		}
		return iCurrPos;
	}

	int calDduStringMem(byte[] buf, int iCurrPos) {
		int len;

		while (buf[iCurrPos] == F_SPECIAL_CHAR) {
			iCurrPos++;
			if ((len = buf[iCurrPos]) < 0)
				len = 256 + len;
			iCurrPos += len + 1;
		}
		while (buf[iCurrPos] == F_SUPER_LEN_SIGN) {
			iCurrPos += BYTE_MAX_LEN + 1;
		}
		if ((len = buf[iCurrPos]) < 0)
			len = 256 + len;
		if (len <= BYTE_MAX_LEN) {
			iCurrPos += len + 1;
		}
		return iCurrPos;
	}

	int putDduString(byte[] buf, String value, int iCurrPos) {
		int iLen;
		int iTmp;

		try {
			if (value == null) {
				buf[iCurrPos++] = 0;
				return iCurrPos;
			}
			byte val[] = value.getBytes(CharacterEncoding);
			iLen = val.length;

			if (iLen <= BYTE_MAX_LEN) {
				buf[iCurrPos++] = (byte) iLen;
				System.arraycopy(val, 0, buf, iCurrPos, iLen);
				iCurrPos += iLen;
				return iCurrPos;
			}
			int value_pos = 0;
			while (iLen > 0) {
				if (iLen > BYTE_MAX_LEN) {
					iTmp = BYTE_MAX_LEN;
					buf[iCurrPos++] = F_SUPER_LEN_SIGN;
				} else {
					iTmp = iLen;
					buf[iCurrPos++] = (byte) iLen;
				}
				System.arraycopy(val, value_pos, buf, iCurrPos, iTmp);
				iCurrPos += iTmp;
				iLen -= iTmp;
				value_pos += iTmp;
			}
		} catch (Exception err) {
			iCurrPos = -1;
		}
		return iCurrPos;
	}

	int putDduStringMem(byte[] buf, byte[] val, int iCurrPos, int iLen) {
		int iTmp;

		try {
			if (iLen == 0) {
				buf[iCurrPos++] = 0;
				return iCurrPos;
			}
			if (iLen <= BYTE_MAX_LEN) {
				buf[iCurrPos++] = (byte) iLen;
				System.arraycopy(val, 0, buf, iCurrPos, iLen);
				iCurrPos += iLen;
				return iCurrPos;
			}
			int value_pos = 0;
			while (iLen > 0) {
				if (iLen > BYTE_MAX_LEN) {
					iTmp = BYTE_MAX_LEN;
					buf[iCurrPos++] = F_SUPER_LEN_SIGN;
				} else {
					iTmp = iLen;
					buf[iCurrPos++] = (byte) iLen;
				}
				System.arraycopy(val, value_pos, buf, iCurrPos, iTmp);
				iCurrPos += iTmp;
				iLen -= iTmp;
				value_pos += iTmp;
			}
		} catch (Exception err) {
			iCurrPos = -1;
		}
		return iCurrPos;
	}

	public int convertPoolToSopServer(byte[] buffer, int len, String imapf) {
		return convertPoolToSopServer(buffer, len, new String[] { imapf });
	}

	int convertPoolToSopServer(byte[] buffer, int len, String[] imapf) {
		this.buffer = buffer;
		int res = convertPoolToPktServer(buffer, imapf);
		if (res < 0) {
			errmsg = "请检查交易[" + code + "]的mapfile是否已更新(" + res + ")";
			errcode = ERR_ASSEMBPKT;
		}
		this.buffer = null;
		return res;
	}

	public int convertPoolToPktServer(byte[] buffer, String[] imapf) {
		FileReader fileReader = null;
		int iTotalLen = 0;

		/* try 内容：前『 */
		try {
			/* try 内容：前『 */
			/** ***************系统信息头——system_head.cfg************************************* */
			File myFile = new File(home + separator + "syscfg" + separator
					+ "system_head.cfg");
			fileReader = new FileReader(myFile);
			BufferedReader fileReaders = new BufferedReader(fileReader);
			String s; /* 读取mapfile一行数据 */
			String flds[] = new String[5]; /* 存放一行数据的数组 */
			int len;
			while ((s = fileReaders.readLine()) != null) {
				if (split(s, SYSCFG_SEP, flds, 3) >= 3) {
					if (flds[2].equals("I")) {
						len = 2;
					} else {
						len = Integer.parseInt(flds[1]);
					}
					Object val = pool.get(flds[0]);
					byte[] bs = null;
					if (val != null) {
						if (val instanceof String) {
							bs = ((String) val).getBytes();
						} else if (val instanceof byte[]) {
							bs = (byte[]) val;
						}
					}
					if (bs != null) {
						System.arraycopy(bs, 0, buffer, iTotalLen, bs.length);
					}
					iTotalLen += len;
				}
			}
			fileReader.close();
			fileReader = null;
			/** ***************系统信息头——system_head.cfg************************************* */
			// System.out.println("HELLO
			// WORLD!!System_head.cfg!["+iTotalLen+"]");
			/** ***************交易公共头——cmtran_rcv_head.cfg********************************* */
			myFile = new File(home + separator + "syscfg" + separator
					+ "cmtran_rcv_head.cfg");
			fileReader = new FileReader(myFile);
			fileReaders = new BufferedReader(fileReader);

			while ((s = fileReaders.readLine()) != null) {
				if (split(s, SYSCFG_SEP, flds, 3) >= 3) {
					if (flds[2].equals("I")) {
						len = 2;
					} else {
						len = Integer.parseInt(flds[1]);
					}

					Object val = pool.get(flds[0]);
					byte[] bs = null;
					if (val != null) {
						if (val instanceof String) {
							bs = ((String) val).getBytes();
						} else if (val instanceof byte[]) {
							bs = (byte[]) val;
						}
					}
					if (bs != null) {
						System.arraycopy(bs, 0, buffer, iTotalLen, bs.length);
					}
					iTotalLen += len;
				}
			}
			fileReader.close();
			fileReader = null;
			/** ***************交易公共头——cmtran_rcv_head.cfg********************************* */
			// System.out.println("HELLO
			// WORLD!!System_head.cfg!["+iTotalLen+"]");
			/** ***************判断TPU_RETCODE的值决定如何打包********************************* */
			String strRetCode = getStr(null, TPU_RETCODE);
			/** ***************判断TPU_RETCODE的值决定如何打包********************************* */
			// System.out.println("===["+strRetCode+"]");
			/** ***************如果TPU_RETCODE等于AAAAAAA,则按照mapfile打包******************** */
			if (strRetCode.equals("AAAAAAA")) {
				if (imapf instanceof String[]) {
					int row = ((String[]) imapf).length;
					if (row > 0) {
						iTotalLen = putDduString(buffer, ((String[]) imapf)[0],
								iTotalLen);
					}

				}
				int cnt = imapf.length;
				for (int ii = 0; ii < cnt; ii++) {
					int pos = 0;
					int t = iTotalLen;

					code = imapf[ii].substring(1, 5);
					myFile = new File(home + separator + "mapfile" + separator
							+ imapf[ii]);
					fileReader = new FileReader(myFile);
					fileReaders = new BufferedReader(fileReader);
					if ((s = fileReaders.readLine()) == null) {
						return CONVERT_ERROR_SEND_MAPFILE_READ;
					}
					while ((s = fileReaders.readLine()) != null) {
						if (s.indexOf("FLD:") >= 0) {
							if (split(s, MAPFILE_SEP, flds, 1) >= 1) {
								s = flds[0].substring(4);
								Object val = pool.get(imapf[ii] + "." + s);
								if (val != null) {
									if (val instanceof String) {

										iTotalLen = putDduString(buffer,
												(String) val, iTotalLen);
									} else if (val instanceof byte[]) {
										iTotalLen = putDduStringMem(buffer,
												(byte[]) val, iTotalLen,
												((byte[]) val).length);
									}
								} else {
									buffer[iTotalLen++] = 0;
								}
							}
						} else if (s.indexOf("GRD:") >= 0) {
							FileReader fileReader2 = null;
							int j, i = 0;
							int col = 0, row = 0;
							int pos2 = 0;
							Object[] arr = new Object[100];

							try {
								String ss = s.substring(4);
								iTotalLen = putDduString(buffer, ss, iTotalLen);
								pos2 = iTotalLen;
								iTotalLen += 2;

								File myFile2 = new File(home + separator
										+ "mapfile" + separator + ss);
								fileReader2 = new FileReader(myFile2);
								BufferedReader fileReaders2 = new BufferedReader(
										fileReader2);
								if ((s = fileReaders2.readLine()) == null) {
									// System.out.println("PoolToPkt_TEST0003["+strRetCode+"]");
									return CONVERT_ERROR_SEND_MAPFILE_READ;
								}
								ss = imapf[ii] + "." + ss;
								while ((s = fileReaders2.readLine()) != null) {
									if (s.indexOf("FLD:") >= 0) {
										if (split(s, MAPFILE_SEP, flds, 1) >= 1) {
											Object val = pool.get(ss + "_"
													+ flds[0].substring(4));
											if (val != null) {
												if (val instanceof byte[][]) {
													row = ((byte[][]) val).length;
													if (row > 0)
														iTotalLen = putDduStringMem(
																buffer,
																((byte[][]) val)[0],
																iTotalLen,
																((byte[][]) val)[0].length);
												} else if (val instanceof String[]) {
													row = ((String[]) val).length;
													if (row > 0)
														iTotalLen = putDduString(
																buffer,
																((String[]) val)[0],
																iTotalLen);
												}
												arr[col] = val;
											} else {
												iTotalLen = putDduString(
														buffer, (String) val,
														iTotalLen);
												arr[col] = null;
											}
										}
									}
									col++;
								}
							} catch (Exception err) {
							} finally {
								if (fileReader2 != null)
									fileReader2.close();
							}
							/*
							 * if (i != col) return
							 * CONVERT_ERROR_COP_MAPFILE_READ;
							 */
							if (row == 0) {
								buffer[pos2] = 0;
								buffer[pos2 + 1] = 0;
								iTotalLen = pos2 + 2;
							} else {
								buffer[pos2] = (byte) row;
								buffer[pos2 + 1] = (byte) col;
								for (j = 1; j < row; j++) {
									for (i = 0; i < col; i++) {
										if (arr[i] == null)
											iTotalLen = putDduString(buffer,
													null, iTotalLen);
										else {
											if (arr[i] instanceof byte[][]) {
												iTotalLen = putDduStringMem(
														buffer,
														((byte[][]) arr[i])[j],
														iTotalLen,
														((byte[][]) arr[i])[j].length);
											} else if (arr[i] instanceof String[]) {
												iTotalLen = putDduString(
														buffer,
														((String[]) arr[i])[j],
														iTotalLen);
											}
										}
									}
								}
							}
						}
					}
					if (pos > 0) {
						buffer[pos] = (byte) (((iTotalLen - t) >> 8) & 0x0ff);
						buffer[pos + 1] = (byte) ((iTotalLen - t) & 0x0ff);
					}
				}
				buffer[0] = (byte) ((iTotalLen >> 8) & 0x0ff);
				buffer[1] = (byte) (iTotalLen & 0x0ff);
			}
			/** ***************如果TPU_RETCODE等于AAAAAAA,则按照mapfile打包******************** */

			/** ***************如果TPU_RETCODE不为AAAAAAA,则按照ERR000打包********************* */
			else {
				imapf = new String[] { "ERR000" };
				if (imapf instanceof String[]) {
					int row = ((String[]) imapf).length;
					if (row > 0) {
						iTotalLen = putDduString(buffer, ((String[]) imapf)[0],
								iTotalLen);
					}

				}
				myFile = new File(home + separator + "syscfg" + separator
						+ "ERR000");
				fileReader = new FileReader(myFile);
				fileReaders = new BufferedReader(fileReader);

				while ((s = fileReaders.readLine()) != null) {
					if (s.indexOf("FLD:") >= 0) {
						if (split(s, SYSCFG_SEP, flds, 3) >= 3) {
							s = flds[0].substring(4);
							Object val = pool.get(s);
							if (val != null) {
								if (val instanceof String)
									iTotalLen = putDduString(buffer,
											(String) val, iTotalLen);
								else if (val instanceof byte[])
									iTotalLen = putDduStringMem(buffer,
											(byte[]) val, iTotalLen,
											((byte[]) val).length);
							} else
								buffer[iTotalLen++] = 0;
						}
					}

				}
				fileReader.close();
				fileReader = null;
				buffer[0] = (byte) ((iTotalLen >> 8) & 0x0ff);
				buffer[1] = (byte) (iTotalLen & 0x0ff);
			}
			/** ***************如果TPU_RETCODE不为AAAAAAA,则按照ERR000打包********************* */
			// System.out.println("HELLO
			// WORLD!!cmtran_rcv_head.cfg!["+iTotalLen+"]");
			/* try 内容：后』 */
		} catch (Exception err) {
			// System.out.println("err:"+err);
			return CONVERT_ERROR_SEND_MAPFILE_READ;

		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (Exception errf) {
				}
			}
		}
		return iTotalLen;
		/* try 内容：后』 */
	}

	public int convertPoolToSop(byte[] buffer, int len, String imapf) {
		return convertPoolToSop(buffer, len, new String[] { imapf });
	}

	int convertPoolToSop(byte[] buffer, int len, String[] imapf) {
		this.buffer = buffer;
		int res = convertPoolToPkt(buffer, imapf);
		if (res < 0) {
			errmsg = "请检查交易[" + code + "]的mapfile是否已更新(" + res + ")";
			errcode = ERR_ASSEMBPKT;
		}
		this.buffer = null;
		return res;
	}

	public int convertPoolToPkt(byte[] buffer, String[] imapf) {
		FileReader fileReader = null;
		int iTotalLen = 0;

		try {
			File myFile = new File(home + separator + "syscfg" + separator
					+ "system_head.cfg");
			fileReader = new FileReader(myFile);
			BufferedReader fileReaders = new BufferedReader(fileReader);
			String s;
			String flds[] = new String[5];
			int len;

			while ((s = fileReaders.readLine()) != null) {
				if (split(s, SYSCFG_SEP, flds, 3) >= 3) {
					if (flds[2].equals("I"))
						len = 2;
					else
						len = Integer.parseInt(flds[1]);
					Object val = pool.get(flds[0]);
					byte[] bs = null;
					if (val != null) {
						if (val instanceof String) {
							bs = ((String) val).getBytes();
						} else if (val instanceof byte[])
							bs = (byte[]) val;
					}
					if (bs != null)
						System.arraycopy(bs, 0, buffer, iTotalLen, bs.length);
					iTotalLen += len;
				}
			}
			fileReader.close();
			fileReader = null;

			myFile = new File(home + separator + "syscfg" + separator
					+ "cmtran_head.cfg");
			fileReader = new FileReader(myFile);
			fileReaders = new BufferedReader(fileReader);
			while ((s = fileReaders.readLine()) != null) {
				if (split(s, SYSCFG_SEP, flds, 3) >= 3) {
					if (flds[2].equals("I"))
						len = 2;
					else
						len = Integer.parseInt(flds[1]);
					Object val = pool.get(flds[0]);
					byte[] bs = null;
					if (val != null) {
						if (val instanceof String) {
							bs = ((String) val).getBytes();
						} else if (val instanceof byte[])
							bs = (byte[]) val;
					}
					if (bs != null)
						System.arraycopy(bs, 0, buffer, iTotalLen, bs.length);
					iTotalLen += len;
				}
			}
			fileReader.close();
			fileReader = null;

			int cnt = imapf.length;
			for (int ii = 0; ii < cnt; ii++) {
				int pos = 0;
				int t = iTotalLen;

				code = imapf[ii].substring(1, 5);
				myFile = new File(home + separator + "syscfg" + separator
						+ "tran_head.cfg");
				fileReader = new FileReader(myFile);
				fileReaders = new BufferedReader(fileReader);
				while ((s = fileReaders.readLine()) != null) {
					if (split(s, SYSCFG_SEP, flds, 3) >= 3) {
						if (flds[2].equals("I"))
							len = 2;
						else
							len = Integer.parseInt(flds[1]);
						if (flds[0].equals(FDCOMMLN)) {
							pos = iTotalLen;
							iTotalLen += len;
							continue;
						} else if (flds[0].equals(PDTRCD)) {
							System.arraycopy(code.getBytes(), 0, buffer,
									iTotalLen, 4);
							iTotalLen += len;
							continue;
						}
						Object val = pool.get(imapf[ii] + "." + flds[0]);
						byte[] bs = null;
						if (val != null) {
							if (val instanceof String) {
								bs = ((String) val).getBytes();
							} else if (val instanceof byte[])
								bs = (byte[]) val;
						}
						if (bs != null)
							System.arraycopy(bs, 0, buffer, iTotalLen,
									bs.length);
						iTotalLen += len;
					}
				}
				fileReader.close();
				fileReader = null;

				myFile = new File(home + separator + "mapfile" + separator
						+ imapf[ii]);
				fileReader = new FileReader(myFile);
				fileReaders = new BufferedReader(fileReader);
				if ((s = fileReaders.readLine()) == null)
					return CONVERT_ERROR_SEND_MAPFILE_READ;

				while ((s = fileReaders.readLine()) != null) {
					if (s.indexOf("FLD:") >= 0) {
						if (split(s, MAPFILE_SEP, flds, 1) >= 1) {
							s = flds[0].substring(4);
							Object val = pool.get(imapf[ii] + "." + s);
							if (val != null) {
								if (val instanceof String)
									iTotalLen = putDduString(buffer,
											(String) val, iTotalLen);
								else if (val instanceof byte[])
									iTotalLen = putDduStringMem(buffer,
											(byte[]) val, iTotalLen,
											((byte[]) val).length);
							} else
								buffer[iTotalLen++] = 0;
						}
					} else if (s.indexOf("GRD:") >= 0) {
						FileReader fileReader2 = null;
						int j, i = 0;
						int col = 0, row = 0;
						int pos2 = 0;
						Object[] arr = new Object[100];

						try {
							String ss = s.substring(4);
							iTotalLen = putDduString(buffer, ss, iTotalLen);
							pos2 = iTotalLen;
							iTotalLen += 2;

							File myFile2 = new File(home + separator
									+ "mapfile" + separator + ss);
							fileReader2 = new FileReader(myFile2);
							BufferedReader fileReaders2 = new BufferedReader(
									fileReader2);
							if ((s = fileReaders2.readLine()) == null)
								return CONVERT_ERROR_SEND_MAPFILE_READ;

							ss = imapf[ii] + "." + ss;
							while ((s = fileReaders2.readLine()) != null) {
								if (s.indexOf("FLD:") >= 0) {
									if (split(s, MAPFILE_SEP, flds, 1) >= 1) {
										Object val = pool.get(ss + "_"
												+ flds[0].substring(4));
										if (val != null) {
											if (val instanceof byte[][]) {
												row = ((byte[][]) val).length;
												if (row > 0)
													iTotalLen = putDduStringMem(
															buffer,
															((byte[][]) val)[0],
															iTotalLen,
															((byte[][]) val)[0].length);
											} else if (val instanceof String[]) {
												row = ((String[]) val).length;
												if (row > 0)
													iTotalLen = putDduString(
															buffer,
															((String[]) val)[0],
															iTotalLen);
											}
											arr[col] = val;
										} else {
											iTotalLen = putDduString(buffer,
													(String) val, iTotalLen);
											arr[col] = null;
										}
									}
								}
								col++;
							}
						} catch (Exception err) {
						} finally {
							if (fileReader2 != null)
								fileReader2.close();
						}
						/*
						 * if (i != col) return CONVERT_ERROR_COP_MAPFILE_READ;
						 */
						if (row == 0) {
							buffer[pos2] = 0;
							buffer[pos2 + 1] = 0;
							iTotalLen = pos2 + 2;
						} else {
							buffer[pos2] = (byte) row;
							buffer[pos2 + 1] = (byte) col;
							for (j = 1; j < row; j++) {
								for (i = 0; i < col; i++) {
									if (arr[i] == null)
										iTotalLen = putDduString(buffer, null,
												iTotalLen);
									else {
										if (arr[i] instanceof byte[][]) {
											iTotalLen = putDduStringMem(
													buffer,
													((byte[][]) arr[i])[j],
													iTotalLen,
													((byte[][]) arr[i])[j].length);
										} else if (arr[i] instanceof String[]) {
											iTotalLen = putDduString(buffer,
													((String[]) arr[i])[j],
													iTotalLen);
										}
									}
								}
							}
						}
					}
				}
				if (pos > 0) {
					buffer[pos] = (byte) (((iTotalLen - t) >> 8) & 0x0ff);
					buffer[pos + 1] = (byte) ((iTotalLen - t) & 0x0ff);
				}
			}
			buffer[0] = (byte) ((iTotalLen >> 8) & 0x0ff);
			buffer[1] = (byte) (iTotalLen & 0x0ff);

		} catch (Exception err) {
			return CONVERT_ERROR_SEND_MAPFILE_READ;
		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (Exception errf) {
				}
			}
		}
		return iTotalLen;
	}


	public boolean convertSopToPoolServer(byte[] buffer, int iLen, String strMap) {
		int res;

		pool.clear();
		if ((res = convertPktToPoolServer(buffer, iLen, strMap)) >= 0)
			return true;
		else {
			errmsg = "请检查交易[" + code + "]的mapfile是否已更新(" + res + ")";
			errcode = ERR_DISASSPKT;
		}
		return false;
	}

	public int convertPktToPoolServer(byte[] buffer, int iLen, String strMap) {
		FileReader fileReader = null;
		int iTotalLen = 0;
		try {
			/** ***************系统信息头——system_head.cfg************************************* */
			File myFile = new File(home + separator + "syscfg" + separator
					+ "system_head.cfg");
			// System.out.println("========>1
			// 创建文件"+myFile.getAbsolutePath());/***/
			fileReader = new FileReader(myFile);
			BufferedReader fileReaders = new BufferedReader(fileReader);
			String s;
			String flds[] = new String[5];
			byte[] data = new byte[1024];
			int len;

			while ((s = fileReaders.readLine()) != null) {
				if (split(s, SYSCFG_SEP, flds, 3) >= 3) {
					if (flds[2].equals("I")) {
						len = 2;
					} else {
						len = Integer.parseInt(flds[1]);
					}
					if (iLen - iTotalLen < len) {
						return CONVERT_ERROR_NO_ENOUGH_DATA;
					}
					byte[] ss = new byte[len];
					if (len > 0) {
						System.arraycopy(buffer, iTotalLen, ss, 0, len);
					}
					pool.put(flds[0], ss);
					iTotalLen += len;
				}
			}
			fileReader.close();
			fileReader = null;
			/** ***************系统信息头——system_head.cfg************************************* */

			/** ***************交易公共头——cmtran_head.cfg************************************* */
			myFile = new File(home + separator + "syscfg" + separator
					+ "cmtran_head.cfg");
			// System.out.println("============>2
			// 创建文件:"+myFile.getAbsolutePath());
			fileReader = new FileReader(myFile);
			fileReaders = new BufferedReader(fileReader);
			while ((s = fileReaders.readLine()) != null) {
				if (split(s, SYSCFG_SEP, flds, 3) >= 3) {
					if (flds[2].equals("I")) {
						len = 2;
					} else {
						len = Integer.parseInt(flds[1]);
					}
					if (iLen - iTotalLen < len) {
						return CONVERT_ERROR_NO_ENOUGH_DATA;
					}
					byte[] ss = new byte[len];
					if (len > 0) {
						System.arraycopy(buffer, iTotalLen, ss, 0, len);
					}
					pool.put(flds[0], ss);
					iTotalLen += len;
				}
			}
			fileReader.close();
			fileReader = null;
			/** ***************交易公共头——cmtran_head.cfg************************************* */

			/** ***************交易数据头——tran_head.cfg************************************* */
			myFile = new File(home + separator + "syscfg" + separator
					+ "tran_head.cfg");
			// System.out.println("==========>2
			// 创建文件："+myFile.getAbsolutePath());
			fileReader = new FileReader(myFile);
			fileReaders = new BufferedReader(fileReader);
			while ((s = fileReaders.readLine()) != null) {
				if (split(s, SYSCFG_SEP, flds, 3) >= 3) {
					if (flds[2].equals("I")) {
						len = 2;
					} else {
						len = Integer.parseInt(flds[1]);
					}
					if (iLen - iTotalLen < len) {
						return CONVERT_ERROR_NO_ENOUGH_DATA;
					}
					byte[] ss = new byte[len];
					if (len > 0) {
						System.arraycopy(buffer, iTotalLen, ss, 0, len);
					}
					pool.put(flds[0], ss);
					iTotalLen += len;
				}
			}
			fileReader.close();
			fileReader = null;
			/** ***************交易数据头——tran_head.cfg************************************* */
			String outmap = null;
			int cnt = 0;
			while (iTotalLen < iLen) {
				String sss = strMap;
				myFile = new File(home + separator + "mapfile" + separator
						+ sss);

				// System.out.println("=========>4
				// 创建文件："+myFile.getAbsolutePath()); /********/
				fileReader = new FileReader(myFile);
				// System.out.println("=========>5 创建文件成功");
				fileReaders = new BufferedReader(fileReader);
				// System.out.println("=========>3 ");

				// System.out.println("=====1 total:"+iTotalLen);
				// System.out.println("=====1 length:"+iLen);
				if ((s = fileReaders.readLine()) == null) {
					return CONVERT_ERROR_RECV_MAPFILE_READ;
				}
				boolean po = false;
				if (s.indexOf("PRINTER") >= 0) {
					po = true;
				}
				int kkk = 0;
				while ((s = fileReaders.readLine()) != null) {
					if (s.indexOf("FLD:") >= 0) {
						if (split(s, MAPFILE_SEP, flds, 1) >= 1) {
							if (po) {
								byte[] attri = new byte[3];
								attri[0] = buffer[iTotalLen++];
								attri[1] = buffer[iTotalLen++];
								attri[2] = buffer[iTotalLen++];
								pool.put(sss + "." + flds[0].substring(4)
										+ "_ATTRIB", attri);
							} else {
								// System.out.println("---->total:"+iTotalLen);
								iTotalLen = getDduStringMem(buffer, data,
										iTotalLen, 1024);
								if (iTotalLen < 0) {
									return CONVERT_ERROR_RECV_MAPFILE_READ;
								}
								// System.out.println("<---total:"+iTotalLen);
								byte[] ss = new byte[iVal];
								if (iVal > 0) {
									System.arraycopy(data, 0, ss, 0, iVal);
								}
								pool.put(sss + "." + flds[0].substring(4), ss);
								// System.out.println(flds[0]);
								// System.out.println("["+flds[0].substring(4)+"]=["+getStr(null,sss
								// + "." + flds[0].substring(4))+"]");
							}
							// System.out.println("=====2 total:"+iTotalLen);
							// System.out.println("=====2 length:"+iLen);
						}
					} else if (s.indexOf("GRD:") >= 0) {
						FileReader fileReader2 = null;
						int j, i = 0;
						int col = 0, row = 0;

						iTotalLen = calDduStringMem(buffer, iTotalLen);
						if ((row = buffer[iTotalLen]) < 0) {
							row = 256 + row;
						}
						if ((col = buffer[iTotalLen + 1]) < 0) {
							col = 256 + col;
						}
						iTotalLen += 2;
						String ss = s.substring(4);
						pool.put(sss + "." + ss, String.valueOf(row));
						if (row == 0) {
							continue;
						}
						int pos = 0;
						if (po) {
							pos = iTotalLen;
							iTotalLen += 1 + 2 * col;
						}

						byte[][][] arr = new byte[col][row][];
						try {
							File myFile2 = new File(home + separator
									+ "mapfile" + separator + ss);
							// System.out.println("==========>6
							// 创建文件："+myFile.getAbsolutePath());
							fileReader2 = new FileReader(myFile2);
							BufferedReader fileReaders2 = new BufferedReader(
									fileReader2);
							if ((s = fileReaders2.readLine()) == null) {
								return CONVERT_ERROR_RECV_MAPFILE_READ;
							}
							ss = sss + "." + ss;
							while ((s = fileReaders2.readLine()) != null) {
								if (s.indexOf("FLD:") >= 0) {
									if (split(s, MAPFILE_SEP, flds, 1) >= 1) {
										iTotalLen = getDduStringMem(buffer,
												data, iTotalLen, 1024);
										if (iTotalLen < 0) {
											return CONVERT_ERROR_RECV_MAPFILE_READ;
										}
										byte[] sx = new byte[iVal];
										if (iVal > 0) {
											System.arraycopy(data, 0, sx, 0,
													iVal);
										}
										arr[i][0] = sx;
										pool.put(ss + "_"
												+ flds[0].substring(4), arr[i]);

										if (po) {
											byte[] attri = new byte[3];
											attri[0] = buffer[pos];
											attri[1] = buffer[pos + i * 2 + 1];
											attri[2] = buffer[pos + i * 2 + 2];
											System.arraycopy(buffer, iTotalLen,
													attri, 0, 3);
											pool.put(ss + "_"
													+ flds[0].substring(4)
													+ "_ATTRIB", attri);
										}
									}
								}
								i++;
							}
						} catch (Exception err) {
							// System.out.println("sop exception 1:");
							err.printStackTrace();
						} finally {
							if (fileReader2 != null) {
								fileReader2.close();
							}
						}
						if (i != col) {
							return CONVERT_ERROR_RECV_MAPFILE_READ;
						}
						for (j = 1; j < row; j++) {
							for (i = 0; i < col; i++) {
								iTotalLen = getDduStringMem(buffer, data,
										iTotalLen, 1024);
								if (iTotalLen < 0) {
									return CONVERT_ERROR_RECV_MAPFILE_READ;
								}
								byte[] sx = new byte[iVal];
								if (iVal > 0) {
									System.arraycopy(data, 0, sx, 0, iVal);
								}
								arr[i][j] = sx;
							}
						}
					}
					kkk++;
				}
			}
			if (cnt > 0) {
				this.outmap = new String[cnt];
				split(outmap, ",", this.outmap, cnt);
			}
		} catch (Exception err) {
			// System.out.println("===============> sop exception:");
			err.printStackTrace();
			return CONVERT_ERROR_RECV_MAPFILE_READ;
		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (Exception errf) {
				}
			}
		}
		return 0;
	}

	public boolean convertSopToPool(byte[] buffer, int iLen) {
		int res;

		pool.clear();
		if ((res = convertPktToPool(buffer, iLen)) >= 0) {
			String s = getStr(null, TPU_RETCODE);
			// if (DEBUG)
			// System.out.println("SopIntf[" + code + "]:" + TPU_RETCODE + ":" +
			// s);
			if (s.equals(SOP_SUCC))
				return true;

			errcode = s;
			errmsg = getStr(null, TPU_RETMSG);
			if (errmsg == null)
				errmsg = "";
			// if (DEBUG)
			// System.out.println("SopIntf[" + code + "]:" + TPU_RETMSG + ":" +
			// errmsg);
			if (errmsg.trim().equals(""))
				errmsg = "(空)[" + s + "]";
		} else {
			errmsg = "请检查交易[" + code + "]的mapfile是否已更新(" + res + ")";
			errcode = ERR_DISASSPKT;
		}
		return false;
	}

	public int convertPktToPool(byte[] buffer, int iLen) {
		FileReader fileReader = null;
		int iTotalLen = 0;
		try {
			File myFile = new File(home + separator + "syscfg" + separator
					+ "system_head.cfg");
			fileReader = new FileReader(myFile);
			BufferedReader fileReaders = new BufferedReader(fileReader);
			String s;
			String flds[] = new String[5];
			byte[] data = new byte[1024];
			int len;

			while ((s = fileReaders.readLine()) != null) {
				if (split(s, SYSCFG_SEP, flds, 3) >= 3) {
					if (flds[2].equals("I"))
						len = 2;
					else
						len = Integer.parseInt(flds[1]);
					if (iLen - iTotalLen < len)
						return CONVERT_ERROR_NO_ENOUGH_DATA;

					byte[] ss = new byte[len];
					if (len > 0)
						System.arraycopy(buffer, iTotalLen, ss, 0, len);
					pool.put(flds[0], ss);
					iTotalLen += len;
				}
			}
			fileReader.close();
			fileReader = null;

			myFile = new File(home + separator + "syscfg" + separator
					+ "cmtran_rcv_head.cfg");
			fileReader = new FileReader(myFile);
			fileReaders = new BufferedReader(fileReader);
			while ((s = fileReaders.readLine()) != null) {
				if (split(s, SYSCFG_SEP, flds, 3) >= 3) {
					if (flds[2].equals("I"))
						len = 2;
					else
						len = Integer.parseInt(flds[1]);
					if (iLen - iTotalLen < len)
						return CONVERT_ERROR_NO_ENOUGH_DATA;

					byte[] ss = new byte[len];
					if (len > 0)
						System.arraycopy(buffer, iTotalLen, ss, 0, len);
					pool.put(flds[0], ss);
					iTotalLen += len;
				}
			}
			fileReader.close();
			fileReader = null;

			s = getStr(null, TPU_RETCODE);
			if (s == null || !s.equals(SOP_SUCC)) {
				if (iTotalLen < iLen) {
					iTotalLen = getDduStringMem(buffer, data, iTotalLen, 1024);
					byte[] ss = new byte[iVal];
					if (iVal > 0)
						System.arraycopy(data, 0, ss, 0, iVal);
					pool.put("TPU_CTX1", ss);
				}
				if (iTotalLen < iLen) {
					iTotalLen = getDduStringMem(buffer, data, iTotalLen, 1024);
					byte[] ss = new byte[iVal];
					if (iVal > 0)
						System.arraycopy(data, 0, ss, 0, iVal);
					pool.put("TPU_CTX2", ss);
					if (iTotalLen >= iLen)
						pool.put(TPU_RETCODE, ss);
				}
				if (iTotalLen < iLen) {
					iTotalLen = getDduStringMem(buffer, data, iTotalLen, 1024);
					byte[] ss = new byte[iVal];
					if (iVal > 0)
						System.arraycopy(data, 0, ss, 0, iVal);
					pool.put(TPU_RETCODE, ss);
				}
				if (iTotalLen < iLen) {
					iTotalLen = getDduStringMem(buffer, data, iTotalLen, 1024);
					byte[] ss = new byte[iVal];
					if (iVal > 0)
						System.arraycopy(data, 0, ss, 0, iVal);
					pool.put(TPU_RETMSG, ss);
				}
			} else {
				String outmap = null;
				int cnt = 0;
				// while (true) {
				while (iTotalLen < iLen) {
					iTotalLen = getDduStringMem(buffer, data, iTotalLen, 256);
					if (iVal == 0)
						break;

					String sss;
					try {
						sss = new String(data, 0, iVal, CharacterEncoding);
						// System.out.println(sss);
					} catch (Exception err) {
						sss = "";
					}
					if (cnt == 0)
						outmap = sss;
					else
						outmap += "," + sss;
					cnt++;
					/*
					 * if (!s.equals(outmap)) { if ((iTotalLen = skipObject(s,
					 * buffer, iTotalLen)) < 0) return iTotalLen; } else break; }
					 */
					myFile = new File(home + separator + "mapfile" + separator
							+ sss);
					fileReader = new FileReader(myFile);
					fileReaders = new BufferedReader(fileReader);
					// System.out.println("密码过期测试"+sss+"密码过期测试");
					if ((s = fileReaders.readLine()) == null) {
						return CONVERT_ERROR_RECV_MAPFILE_READ;
					}

					boolean po = false;
					if (s.indexOf("PRINTER") >= 0)
						po = true;

					while ((s = fileReaders.readLine()) != null) {
						if (s.indexOf("FLD:") >= 0) {
							if (split(s, MAPFILE_SEP, flds, 1) >= 1) {
								if (po) {
									byte[] attri = new byte[3];
									attri[0] = buffer[iTotalLen++];
									attri[1] = buffer[iTotalLen++];
									attri[2] = buffer[iTotalLen++];
									pool.put(sss + "." + flds[0].substring(4)
											+ "_ATTRIB", attri);
								}
								iTotalLen = getDduStringMem(buffer, data,
										iTotalLen, 1024);
								byte[] ss = new byte[iVal];
								if (iVal > 0)
									System.arraycopy(data, 0, ss, 0, iVal);
								pool.put(sss + "." + flds[0].substring(4), ss);
							}
						} else if (s.indexOf("GRD:") >= 0) {
							FileReader fileReader2 = null;
							int j, i = 0;
							int col = 0, row = 0;

							iTotalLen = calDduStringMem(buffer, iTotalLen);
							if ((row = buffer[iTotalLen]) < 0)
								row = 256 + row;
							if ((col = buffer[iTotalLen + 1]) < 0)
								col = 256 + col;
							iTotalLen += 2;
							String ss = s.substring(4);
							pool.put(sss + "." + ss, String.valueOf(row));
							if (row == 0)
								continue;

							int pos = 0;
							if (po) {
								pos = iTotalLen;
								iTotalLen += 1 + 2 * col;
							}

							byte[][][] arr = new byte[col][row][];
							try {
								File myFile2 = new File(home + separator
										+ "mapfile" + separator + ss);
								fileReader2 = new FileReader(myFile2);
								BufferedReader fileReaders2 = new BufferedReader(
										fileReader2);
								if ((s = fileReaders2.readLine()) == null)
									return CONVERT_ERROR_RECV_MAPFILE_READ;

								ss = sss + "." + ss;
								while ((s = fileReaders2.readLine()) != null) {
									if (s.indexOf("FLD:") >= 0) {
										if (split(s, MAPFILE_SEP, flds, 1) >= 1) {
											iTotalLen = getDduStringMem(buffer,
													data, iTotalLen, 1024);
											byte[] sx = new byte[iVal];
											if (iVal > 0)
												System.arraycopy(data, 0, sx,
														0, iVal);
											arr[i][0] = sx;
											pool.put(ss + "_"
													+ flds[0].substring(4),
													arr[i]);

											if (po) {
												byte[] attri = new byte[3];
												attri[0] = buffer[pos];
												attri[1] = buffer[pos + i * 2
														+ 1];
												attri[2] = buffer[pos + i * 2
														+ 2];
												System.arraycopy(buffer,
														iTotalLen, attri, 0, 3);
												pool.put(ss + "_"
														+ flds[0].substring(4)
														+ "_ATTRIB", attri);
											}
										}
									}
									i++;
								}
							} catch (Exception err) {
							} finally {
								if (fileReader2 != null)
									fileReader2.close();
							}
							if (i != col)
								return CONVERT_ERROR_RECV_MAPFILE_READ;

							for (j = 1; j < row; j++) {
								for (i = 0; i < col; i++) {
									iTotalLen = getDduStringMem(buffer, data,
											iTotalLen, 1024);
									byte[] sx = new byte[iVal];
									if (iVal > 0)
										System.arraycopy(data, 0, sx, 0, iVal);
									arr[i][j] = sx;
								}
							}
						}
					}
				}
				if (cnt > 0) {
					this.outmap = new String[cnt];
					split(outmap, ",", this.outmap, cnt);
				}
			}
		} catch (Exception err) {
			return CONVERT_ERROR_RECV_MAPFILE_READ;
		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (Exception errf) {
				}
			}
		}
		return 0;
	}

	public SopIntf() {
		pool.put("PDWSNO", new byte[] { 'i', 'p' });
	}

	public void clear() {
		pool.clear();
	}

	public boolean exec(String inmap) {
		return exec(new String[] { inmap });
	}

	boolean exec(String[] inmap) {
		int res = 0;
		byte out[] = new byte[1024 * 32];
		if (buffer == null)
			buffer = new byte[8192];

		this.inmap = inmap;
		if ((res = convertPoolToPkt(buffer, this.inmap)) > 0) {
			// System.out.println("阿瑟大苏打"+res);
			res = sopCall(buffer, out, res, 1024 * 32);

			// if (DEBUG)
			// System.out.println("SopIntf[" + code + "]:sopCall:" + res);
		}
		if (res > 0) {
			if (flag != 0) {
				pool2 = pool;
				pool = new HashMap(30);
			} else
				pool.clear();
			if ((res = convertPktToPool(out, res)) >= 0) {
				String s = getStr(null, TPU_RETCODE);
				// if (DEBUG)
				// System.out.println("SopIntf[" + code + "]:" + TPU_RETCODE +
				// ":" + s);
				if (s.equals(SOP_SUCC))
					return true;

				errcode = s;
				errmsg = getStr(null, TPU_RETMSG);
				if (errmsg == null)
					errmsg = "";
				// if (DEBUG)
				// System.out.println("SopIntf[" + code + "]:" + TPU_RETMSG +
				// ":" + errmsg);
				if (errmsg.trim().equals(""))
					errmsg = "(空)[" + s + "]";
				return false;
			}
		}
		switch (res) {
		case 0:
			errmsg = "打包/拆包失败(0)";
			errcode = ERR_SYSERROR;
			break;
		case -2:
			errmsg = "IPP连接失败(" + res + ")";
			errcode = ERR_NOCONNECT;
			break;
		case -3:
			errmsg = "交易通讯失败，请查询上笔交易是否成功(" + res + ")";
			errcode = ERR_IOERROR;
			break;
		case -6:
			errmsg = "请检查交易[" + code + "]的mapfile是否已更新(" + res + ")";
			errcode = ERR_ASSEMBPKT;
		case -7:
		case -8:
			errmsg = "请检查交易[" + code + "]的mapfile是否已更新(" + res + ")";
			errcode = ERR_DISASSPKT;
			break;
		default:
			errmsg = "SOP调用失败(" + res + ")";
			errcode = ERR_SYSERROR;
		}
		return false;
	}

	 static {
		  separator = System.getProperty("file.separator");
		  //home = SopIntf.getEnv("SOPHOME");
		 //home=取config.properties文件里的配置路径（配置一个sop_path属性）
		  home = JavaUtils.getPropertiesVal("sop_path");
		  initEnv("ww");
		 }

	public static void main(String[] args) {
	}
}