package com.topcheer.utils;



import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.commons.lang.StringUtils;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      CES
 * @author liuhs
 * @version 1.0
 */
/**
 * ������Ҫ�����й�String���͵�һЩ�������磺�滻�ַ����������ַ����ĸ�������һ��String[]
 * ת����һ��int[]����һ��String[]�е�ĳ���ַ������˵�����һ��String[]�е�ĳ���ַ����Ƶ�
 * �����ĩβ����Stringת����gb2312�ı����ʽ����һ��Stringת����String[].
 */
public class StringUtil
{

    public StringUtil()
    {
    }
    
	/**
	 * �ж�NULL
	 * @param value
	 * @return
	 */
	public static boolean isNull(String value) {
		try {
			if (value == null) return true;
			if ("null".equals(value))	return true;
			if (value.trim().length() == 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * �ж϶����Ƿ�ΪNULL����ΪNullת��Ϊ���ַ���
	 * @param value
	 * @return
	 */
	public static String repNullToStr(String val) {
		return val == null ? "" : val;
	}
	
	/**
	 * ����Ƿ��ַ�������0-31��ASCII��Ϊ�Ƿ��ַ���
	 * @param s
	 * @return
	 */
	public static boolean isIllegalChar(String s) {
		boolean flag = false;
		char[] dest = s.trim().toCharArray();
		for (int i = 0; i < dest.length; i++) {
			if (((int) dest[i] > 0 && (int) dest[i] < 32)) {
				//Debug("���зǷ��ַ�!" + (int) dest[i]);
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * �ж��Ƿ�Ϊ����
	 * @param value
	 * @return
	 */
	public static boolean checkNum(String value) {
		try {
			if (value != null && value.trim().length() > 0) {
				if ((value.charAt(0) == '-')) {
					return false;
				} else {
					String[] temp = value.split("\\.");
					if (temp.length > 2) {
						return false;
					}
					value = value.replaceAll("\\.", "");
					char[] dest = value.trim().toCharArray();
					for (int i = 0; i < dest.length; i++) {
						if (!Character.isDigit(dest[i])) {
							return false;
						}
					}
				}
			} else {
				return false;
			}			
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
    /**
     * �滻String�е��ַ���.
     * �ڱ�׼��String����ֻ�н�String�е�ĳһ���ַ��滻����һ���ַ����ú������Խ�String
     * �е�ĳһ�ַ����滻����һ���ַ���.
     * @param str0 Դ�ַ��� tag ��Ҫ���滻���ַ��� news ��Ҫ�滻���ַ���
     * @return ��Դ�ַ����滻����ַ���
     */
    public static String replace(String str,String tag,String news)
    {
    	if(str==null||str.length()==0||tag==null||tag.length()==0)
    		return(str);
    	String ret=str,temp="";
    	String l_str;
    	int len_s=str.length();
    	int len_t=tag.length();
    	int pos_1=0,pos_2=0;
    	int i=0;
    	while(i<len_s)
    	{
    		if(i>=(len_s-len_t))
    			l_str=str.substring(i);
    		else
    			l_str=str.substring(i,i+len_t);
    		if(l_str.equals(tag))
    		{
    			pos_2=i;
    			temp=temp+str.substring(pos_1,pos_2)+news;
    			i=i+len_t;
    			pos_1=i;
    		}
    		else
    			i++;
    	}
    	if(pos_1<len_s)
    		temp=temp+str.substring(pos_1);

    	if(temp.length()>0)
    		ret=temp;

    	return(ret);
    }
    /**
     * ����ĳһ�ַ���������һ�ַ����ĸ���.
     * �˴��涨��ƥ������ַ�
     * @param str1 Դ�ַ��� str2 ��Ҫ���滻���ַ���
     * @return �����ĸ���
     */
    public static int hasStr(String str1,String str2)
    {
        int sum=0;
        String sTemp=new String(str1);
        while(sTemp.indexOf(str2)!=-1)
        {
            sum++;
            sTemp=sTemp.substring(sTemp.indexOf(str2)+str2.length());
        }
        return sum;
    }
    
    /**
     * ���ַ���ת����gb2312�ı����ʽ
     */
    public static String changeEncoding(String strChinese, String soureceEncoding, String targetEncoding)
    {
    	if(strChinese == null) return "";
        try
        {
            return new String(strChinese.trim().getBytes(soureceEncoding),targetEncoding);
        }catch( Exception e)
        {
            return "";
        }
    }
    
    /**
     * ��һ���ַ�������һ����ʾ��ֳ�һ���ַ�������.����",1,2,3,"���ʹ�á�,���ֽ⣬�����ֳɳ���Ϊ5���ַ�������
     * @param source Դ�ַ���,str ��ֱ�ʾ�ַ���
     * @return ����ֵ��ַ���
     */
    public static String[] toArray(String source,String str)
    {
    	Vector vResult = new Vector();
        if(source==null || source.equals(""))
            return new String[0];
        if(str==null||"".equals(str))return new String[]{source};
        int pos1 = 0;
        int pos2 = 0;
        pos2 = source.indexOf(str);
        String strItem = "";
        if(pos2<0){
        	pos2=source.length();
        	strItem = source;
        }
        while(pos2 >= pos1 && pos1 < source.length())
        {
    		strItem = source.substring(pos1,pos2);
        	vResult.add(strItem);
        	pos1 = pos2 + 1;
    		pos2 = source.indexOf(str,pos1);
    		if(pos2<=0)pos2=source.length();
        	
        }
        return toArray(vResult);
    }
    
    public static String[] split(String source,String token){
    	return stringToArray(source,token);
    }
    
    public static String[] splitString(String source,String token){
    	return stringToArray(source,token);
    }
    public static String[] stringToArray(String source,String str)
    {
    	
    	Vector vResult = new Vector();
        if(source==null || source.equals("")){
        	String tmp[] = {""};
        	return tmp;
        }
            
        if(str==null||"".equals(str))return new String[]{source};
        int pos1 = 0;
        int pos2 = 0;
        pos2 = source.indexOf(str);
        String strItem = "";
        if(pos2<0){
        	pos2=source.length();
        	strItem = source;
        }
        while(pos2 >= pos1 && pos1 <= source.length())
        {
        	if(pos2==source.length()&&pos1==source.length()){
        		strItem = source.substring(pos2);
        		vResult.add(strItem);
        		break;
        	}
    		strItem = source.substring(pos1,pos2);
        	vResult.add(strItem);
        	pos1 = pos2 + 1;
    		pos2 = source.indexOf(str,pos1);
    		if(pos2<=0)pos2=source.length();
        }
        return toArray(vResult);
    }
    
    public static String[] toArray(Vector vec)
    {
        int intSize = vec.size();
        String str[] = new String[intSize];
        for(int i=0;i<intSize;i++)
            str[i]=vec.elementAt(i).toString();
        return str;
    }
    public static String[] clearElement(String[] sources,String str)
    {
        sources=StringUtil.moveToEnd(sources,str);
        int size=0;
        for(;size<sources.length;size++)
        {
            if(sources[size].equals(str))
                break;
        }
        String[] temp=new String[size];
        for(int i=0;i<size;i++)
            temp[i]=sources[i];
        return temp;
    }
    /**
     * ��һ��String[]�е�ĳ���ַ����Ƶ������ĩβ.
     * @param sources Դ�ַ������� str ��Ҫ�ƶ����ַ���
     * @return �ƶ�����ַ�������
     */
    public static String[] moveToEnd(String[] sources,String str)
    {
        int head=0;
        int end=sources.length-1;
        while(head!=end)
        {
            while(head!=end)
            {
                if(sources[head].equals(str))
                    break;
                head++;
            }
            while(head!=end)
            {
                if(!sources[end].equals(str))
                    break;
                end--;
            }
            if(head!=end)
            {
                String temp=sources[head];
                sources[head]=sources[end];
                sources[end]=temp;
            }
        }
        return sources;
    }
    /**
     * ���ַ�������ת����int������.
     * @param sources Դ�ַ�������
     * @return int[]
     */
    public static int[] pareInts(String[] sources)
    {
        int[] temp=new int[sources.length];
        for(int i=0;i<sources.length;i++)
        {
            temp[i]=Integer.parseInt(sources[i]);
        }
        return temp;
    }
    
    /**
     * ������vectorת��Ϊ�ַ���
     */
    public static String toString(Vector vec,String str)
    {
        String s="";
        for(int i=0;i<vec.size();i++)
        {
            if(i!=0)
                s+=str;
            s+=vec.elementAt(i).toString().trim();
        }
        return s;
    }
    
    /**
     * ������[]ת��Ϊ�ַ���
     */
    public static String toString(String[] scr,String str)
    {
        String s="";
        for(int i=0;i<scr.length;i++)
        {
            if(i!=0)
                s+=str;
            s+=scr[i];
        }
        return s;
    }
    
    /**
     * ���ַ�����ȡ��һ������
     */
    public static String getWord(String str,int index) throws Exception
    {
        if(index < 1)
            throw new Exception("getWord error,index less than 1");
        String word=null;
        for(int i = 0;i < index;i++)
        {
            word = StringUtil.getFirstWord(str,true);
            str = str.substring(word.length());
        }
        return word;
    }
    
    /**
     * ���ַ�����ȡ����һ������,���ҽ��ָ�����'��'���� ����Ҳ��Ϊһ������
     */
    public static String getFirstWord(String str,boolean hasSeparator) throws Exception
    {
        if(str.equals(""))
            return "";
        char ch = str.charAt(0);
        if(ch == '(' || ch == '[' || ch == '{')
            return StringUtil.getBracket(str,ch);
        if(ch == '\'')
            return StringUtil.getString(str,ch);
        int offs = str.indexOf(" ")==-1?str.length():str.indexOf(" ");
        String separator=" ";
        if(str.indexOf(",") != -1 && str.indexOf(",") < offs )
        {
            offs = str.indexOf(",");
            separator = ",";
        }
        if(str.indexOf("-") != -1 && str.indexOf("-") < offs )
        {
            offs = str.indexOf("-");
            separator = "-";
        }
        if(str.indexOf("+") != -1 && str.indexOf("+") < offs )
        {
            offs = str.indexOf("+");
            separator = "+";
        }
        if(str.indexOf("*") != -1 && str.indexOf("*") < offs )
        {
            offs = str.indexOf("*");
            separator = "*";
        }
        if(str.indexOf("=") != -1 && str.indexOf("=") < offs )
        {
            offs = str.indexOf("=");
            separator = "=";
        }
        if(str.indexOf("||") != -1 && str.indexOf("||") < offs)
        {
            offs = str.indexOf("||");
            separator = "||";
        }
        if(str.indexOf("(") != -1 && str.indexOf("(") < offs)
        {
            offs = str.indexOf("(");
            //System.out.println("(:"+str.substring(0,offs)+"   ==  "+str.substring(offs));
            return str.substring(0,offs)+StringUtil.getBracket(str.substring(offs),'(');
        }
        if(offs == -1)
            return str;
        if(hasSeparator && offs == 0)
            return separator;
        return str.substring(0,offs);
    }
    /**
     * ���ַ�����ȡ����һ������,�����ָ�����'��'���� ����Ҳ��Ϊһ������
     */
    public static String getFirstWord(String str) throws Exception
    {
        return StringUtil.getFirstWord(str,false);
    }
    /**
     * ƥ��һ���ַ����еĵ�һ���κ�.
     * ����ٶ����ַ����ĵĹκ�����ȷ��
     */
    public static String getBracket(String str,char bracketType) throws Exception
    {
        //ȷ���κŵ�����
        //System.out.println("ȷ���κŵ�����:"+str);
        char endType=')';
        if(bracketType == '[')
            endType=']';
        else if(bracketType == '{')
            endType = '}';
        else if(bracketType != '(')
            throw new Exception(bracketType + " not a type of bracket");

        char[] c = str.toCharArray();
        int sum=1;
        String s=null;
        for(int i=1;i<c.length;i++)
        {
            if(c[i] == bracketType)
            {
                sum++;
                continue;
            }
            if( c[i] == endType)
            {
                if(--sum == 0)
                {
                    s = String.valueOf(c,0,i+1);
                    break;
                }
            }
        }
        if(s == null)
            throw new Exception("bracket not suited");
        //System.out.println("ȷ���κŵ����� new:"+s);
        return s;
    }
    public static String ltrim(String str)
    {
        char[] c = str.toCharArray();
        for(int i=0;i<c.length;i++)
        {
            if(c[i] != ' ')
            {
                str = String.valueOf(c,i,c.length-i);
                break;
            }
        }
        return str;
    }
    /**
     * ���ݱ�ʾ��ȡһ���ַ���
     */
    public static String getString(String str,char c)
    {
        char[] ch = str.toCharArray();
        for(int i=1;i<ch.length;i++)
        {
            if(ch[i] == c)
            {
                if(i==ch.length-1)
                {
                    str = String.valueOf(ch,0,i+1);
                    break;
                }
                if(ch[i+1] != c)
                {
                    str = String.valueOf(ch,0,i+1);
                    break;
                }
                else
                {
                    i++;
                }
            }
        }
        return str;
    }
    
    /**
     * ת���� 
     **/
	public static String changeCnCode(String oldStr) {
		String value = null;
		try {
			value = new String(oldStr.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * ����ո��滻Ϊһ��
	 * @param sourceStr
	 * @return
	 */
	public static String repSingleSpace(String sourceStr) {
		String regEx = "['   ']+";

		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(sourceStr);
		return m.replaceAll(" ");
	}
	
    public static void main(String[] args)
    {
    	
    }

    /**
     * �ַ���Ϊ�ղ�0.00
     * @param string
     * @return
     */
	public static String toBd0(String str) {
		return str != null ? str : "0.00" ;
	}
}
