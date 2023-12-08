package com.neighbor21.ggits.common.component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.neighbor21.ggits.common.util.BDDateFormatUtil;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;

@Component
public class FileUploadComponent {
	// TODO 파일 업로드 경로
	private final String uploadPath = Paths.get("C:", "bluedus", "upload-files").toString();
	
	/**
     * @Method Name : uploadFileList
     * @작성일 : 2023. 9. 11.
     * @작성자 : KC.KIM
     * @Method 설명 : 리스트 파일 업로드
     * @return
     */
	public void uploadFileList(List<MultipartFile> fileList) {
		for(MultipartFile file : fileList) {
			if(file.isEmpty()) {
				continue;
			}
			uploadFile(file);
		}
	}
	
	/**
     * @Method Name : uploadFile
     * @작성일 : 2023. 9. 11.
     * @작성자 : KC.KIM
     * @Method 설명 : 단일 파일 업로드
     * @return
     */
	public void uploadFile(MultipartFile file) {
		String fileNm = generateFileNm(file.getOriginalFilename());
		String today = BDDateFormatUtil.isNowStr("yyyyMMdd").toString();
		String uploadPath = getUploadPath(today) + File.separator + fileNm;
		
		File uploadFile = new File(uploadPath);
		
		try {
			if(!file.isEmpty()) {
                FileOutputStream fo = new FileOutputStream(uploadFile);
                byte[] fileBytes = file.getBytes();
                fo.write(fileBytes);
                fo.close();
            }
		} catch (IOException e) {
			throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL);
		}
	}
	
	
    /**
     * @Method Name : chkFileExt
     * @작성일 : 2023. 9. 11.
     * @작성자 : KC.KIM
     * @Method 설명 : 파일 확장자 체크
     * @return
     */
	public boolean chkFileExt(String fileNm, String[] arrExt) {
		boolean isContatin = false;
		
		String ext = fileNm.substring(fileNm.lastIndexOf(".")+1, fileNm.length());
		
		isContatin = Arrays.asList(arrExt).contains(ext);
		
		return isContatin;
	}
	
    /**
     * @Method Name : generateFileNm
     * @작성일 : 2023. 9. 11.
     * @작성자 : KC.KIM
     * @Method 설명 : 저장 파일명 생성
     * @return
     */
	private String generateFileNm(String fileNm) {		
		String uuid = GgitsCommonUtils.getUuid();
		String ext = StringUtils.getFilenameExtension(fileNm);
		return uuid + "." + ext;
	}

	/**
     * @Method Name : getUploadPath
     * @작성일 : 2023. 9. 11.
     * @작성자 : KC.KIM
     * @Method 설명 : 업로드 경로
     * @return
     */
	private String getUploadPath(String addPath) {
		return makeDirectory(uploadPath + File.separator + addPath);
	}
	
	/**
     * @Method Name : makeDirectory
     * @작성일 : 2023. 9. 11.
     * @작성자 : KC.KIM
     * @Method 설명 : 파일 경로 생성
     * @return
     */
	private String makeDirectory(String path) {
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		return dir.getPath();
	}
}
