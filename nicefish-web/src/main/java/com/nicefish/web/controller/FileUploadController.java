package com.nicefish.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import com.nicefish.web.po.POSysParam;
import com.nicefish.web.service.SysParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles requests for the application file upload requests
 */
@Controller
@RequestMapping("/file")
public class FileUploadController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private SysParamService sysParamService;

	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFileHandler(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if (!file.isEmpty()) {
			try {
				String origFileName=file.getOriginalFilename();
				String newFileName= UUID.randomUUID().toString();
				int dotIndex=origFileName.lastIndexOf(".");
				if(dotIndex!=-1){
					newFileName=newFileName+origFileName.substring(dotIndex);
				}

//				String rootPath = request.getServletContext().getRealPath("/");
				String rootPath="E://github-my//NiceFish//dist";
				POSysParam poSysParam=sysParamService.findByParamKey("UPLOAD_FILE_DIR");
				String uploadFileDir=poSysParam.getParamValue();
				if(!"TOMCAT".equals(uploadFileDir)){
					rootPath=uploadFileDir;
				}

				File dir = new File(rootPath + File.separator + "upload_files");
				if (!dir.exists()){
					dir.mkdirs();
				}

				File serverFile = new File(dir.getAbsolutePath()+ File.separator +newFileName);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(file.getBytes());
				stream.close();

				logger.info("上传文件成功>"+ serverFile.getAbsolutePath());
				return "{\"success\":true,\"fileName\":\""+newFileName+"\",\"dirName\":\"upload_files\"}";
			} catch (Exception e) {
				logger.error(e.getMessage());
				return "上传文件失败>"+e.getMessage();
			}
		}
		return "{\"success\":false,\"msg\":\"上传文件失败\"}";
	}

	/**
	 * Upload multiple file using Spring Controller
	 * TODO:这个方法需要重构，抄过来之后还没有用过
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	@ResponseBody
	public String uploadMultipleFileHandler(@RequestParam("name") String[] names,@RequestParam("file") MultipartFile[] files) {
		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists()) dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="+ serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
}