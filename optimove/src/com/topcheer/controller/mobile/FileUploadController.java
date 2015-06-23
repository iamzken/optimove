package com.topcheer.controller.mobile;   
  
import java.io.BufferedInputStream;    
import java.io.BufferedOutputStream;    
import java.io.File;    
import java.io.FileInputStream;    
import java.io.IOException;    
import java.net.BindException;    
import java.util.ArrayList;    
  
import java.util.List;    
import java.util.Map;    
  
import javax.servlet.http.HttpServletRequest;    
import javax.servlet.http.HttpServletResponse;    
  
import org.springframework.stereotype.Controller;    
import org.springframework.util.FileCopyUtils;    
import org.springframework.web.bind.annotation.PathVariable;    
import org.springframework.web.bind.annotation.RequestMapping;    
import org.springframework.web.bind.annotation.RequestMethod;    
import org.springframework.web.bind.annotation.RequestParam;    
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;    
import org.springframework.web.multipart.MultipartHttpServletRequest;    
import org.springframework.web.multipart.commons.CommonsMultipartFile;    
import org.springframework.web.servlet.ModelAndView;    

import com.topcheer.utils.ResultJsonUtil;
  
/**  
 * Title: Description: Copyright: Copyright (c) 2011  
 * Company:http://liuzidong.iteye.com/ Makedate:2011-5-27 ����01:52:17  
 *   
 * @author liuzidong  
 * @version 1.0  
 * @since 1.0  
 *   
 */  
@Controller   
public class FileUploadController {    
  
    //@RequestMapping(value = "/upload", method = RequestMethod.POST)   
	@RequestMapping("/upload")
    @ResponseBody
    public Map<String,Object> onSubmit(HttpServletRequest request,    
            HttpServletResponse response, BindException errors)   
            throws Exception {    
  
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest   
                .getFile("file");    
  
        String name = multipartRequest.getParameter("name");    
        System.out.println("name: " + name);    
        // ����ļ�����    
        String realFileName = file.getOriginalFilename();   
        System.out.println("����ļ�����" + realFileName);    
        // ��ȡ·��    
        String ctxPath = request.getSession().getServletContext().getRealPath(   
                "/")    
                + "mobileimages/";    
        // �����ļ�    
        File dirPath = new File(ctxPath);    
        if (!dirPath.exists()) {    
            dirPath.mkdir();   
        }   
        File uploadFile = new File(ctxPath + realFileName);    
        FileCopyUtils.copy(file.getBytes(), uploadFile);   
        request.setAttribute("files", loadFiles(request));  
        return ResultJsonUtil.getResultMap();
        //return new ModelAndView("success");    
    }   
  
    @RequestMapping("/upload2")
    @ResponseBody
    public Map<String,Object> onSubmit2(HttpServletRequest request,    
            HttpServletResponse response, BindException errors)   
            throws Exception {    
  
        // ת��ΪMultipartHttpRequest    
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
        // ����ǰ̨��name���Ƶõ��ϴ����ļ�    
        MultipartFile file = multipartRequest.getFile("file");    
        // ����ļ�����    
        String realFileName = file.getOriginalFilename();   
        // ��ȡ·��    
        String ctxPath = request.getSession().getServletContext().getRealPath(   
                "/")    
                + "/" + "mobileimages/";    
        // �����ļ�    
        File dirPath = new File(ctxPath);    
        if (!dirPath.exists()) {    
            dirPath.mkdir();   
        }   
        File uploadFile = new File(ctxPath + realFileName);    
        FileCopyUtils.copy(file.getBytes(), uploadFile);   
        request.setAttribute("files", loadFiles(request));    
        return ResultJsonUtil.getResultMap(); 
    }   
  
    @RequestMapping("/upload3")
    @ResponseBody  
    public Map<String,Object> upload(@RequestParam("file")    
    MultipartFile image, HttpServletRequest request) throws IOException {    
  
        String ctxPath = request.getSession().getServletContext().getRealPath(   
                "/")    
                + "/" + "mobileimages/";    
        System.out.println("·����" + ctxPath);    
        File file = new File(ctxPath + "/" + image.getOriginalFilename());    
        // FileCopyUtils.copy(image.getBytes(),new    
        // File(ctxPath+"/"+image.getOriginalFilename()));    
        try {    
            image.transferTo(file); // �����ϴ����ļ�    
        } catch (IllegalStateException e) {    
            e.printStackTrace();   
        } catch (IOException e) {    
            e.printStackTrace();   
        }   
        request.setAttribute("files", loadFiles(request));    
        return ResultJsonUtil.getResultMap();  
    }   
  
    // ���ļ��ϴ�    
    @RequestMapping("/upload4")
    @ResponseBody   
    public Map<String,Object> fileUpload(HttpServletRequest request,    
            HttpServletResponse response, BindException errors)   
            throws Exception {    
  
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();   
        String ctxPath = request.getSession().getServletContext().getRealPath(   
                "/")    
                + "/" + "mobileimages/";    
  
        File file = new File(ctxPath);    
        if (!file.exists()) {    
            file.mkdir();   
        }   
        String fileName = null;    
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {    
            // �ϴ��ļ���    
            // System.out.println("key: " + entity.getKey());    
            MultipartFile mf = entity.getValue();   
            fileName = mf.getOriginalFilename();   
            File uploadFile = new File(ctxPath + fileName);    
            FileCopyUtils.copy(mf.getBytes(), uploadFile);   
        }   
        request.setAttribute("files", loadFiles(request));    
        return ResultJsonUtil.getResultMap();   
    }   
  
    // @ModelAttribute("files")//���������ڳ�ʼ��ʱ����,���ϴ��ļ�����ʱʱ��Ӧ�ϴ��ļ�����,���ʺ϶�̬����    
    public List<String> loadFiles(HttpServletRequest request) {    
        List<String> files = new ArrayList<String>();    
        String ctxPath = request.getSession().getServletContext().getRealPath(   
                "/")    
                + "/" + "mobileimages/";    
        File file = new File(ctxPath);    
        if (file.exists()) {    
            File[] fs = file.listFiles();   
            String fname = null;    
            for (File f : fs) {    
                fname = f.getName();   
                if (f.isFile()) {    
                    files.add(fname);   
                }   
            }   
        }   
        return files;    
    }   
  
    //@RequestMapping("/download/{fileName}")   
    @RequestMapping("/download")  
    @ResponseBody
    public Map<String,Object> download(HttpServletRequest request, HttpServletResponse response)   
            throws Exception {    
  
        response.setContentType("text/html;charset=utf-8");    
        request.setCharacterEncoding("UTF-8");    
        BufferedInputStream bis = null;    
        BufferedOutputStream bos = null;    
        String fileName=request.getParameter("filename");
        String ctxPath = request.getSession().getServletContext().getRealPath(   
                "/")    
                + "/" + "mobileimages/";    
        String downLoadPath = ctxPath + fileName;   
        System.out.println(downLoadPath);   
        try {    
            long fileLength = new File(downLoadPath).length();    
            response.setContentType("application/x-msdownload;");    
            response.setHeader("Content-disposition", "attachment; filename="   
                    + new String(fileName.getBytes("utf-8"), "ISO8859-1"));    
            response.setHeader("Content-Length", String.valueOf(fileLength));    
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));    
            bos = new BufferedOutputStream(response.getOutputStream());    
            byte[] buff = new byte[2048];    
            int bytesRead;    
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {    
                bos.write(buff, 0, bytesRead);    
            }   
        } catch (Exception e) {    
            e.printStackTrace();   
        } finally {    
            if (bis != null)    
                bis.close();   
            if (bos != null)    
                bos.close();   
        }   
        return ResultJsonUtil.getResultMap();   
    }   
}  
