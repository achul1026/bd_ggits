 package com.neighbor21.ggits.common.component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.neighbor21.ggits.common.dto.UploadFileInfo;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;

@Component
public class FileUploadComponent {

	@Value("#{commonProperties['atchFile.upload.path']}")
	public String uploadPath;

//	private final String uploadPath = Paths.get("C:", "bluedus", "upload-files").toString();
	
	/**
     * @Method Name : uploadFileList
     * @작성일 : 2023. 9. 11.
     * @작성자 : KC.KIM
     * @Method 설명 : 리스트 파일 업로드
     * @return
     */
	public void uploadFileList(List<MultipartFile> fileList){
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
	public void uploadFile(MultipartFile file){
//		String fileNm = generateFileNm(encodeFileOrgName(file));
		String fileNm = generateFileNm(file.getOriginalFilename());
		String today = BDDateFormatUtil.isNowStr("yyyyMMdd").toString();
		String uploadPath = getUploadPath(today) + File.separator + fileNm;
		
		File uploadFile = new File(uploadPath);
		FileOutputStream fo = null;
		try {
			if(!file.isEmpty()) {
                fo = new FileOutputStream(uploadFile);
                byte[] fileBytes = file.getBytes();
                fo.write(fileBytes);
            }
		} catch (IOException e) {
			throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL);
		} finally {
			if(fo != null) {
				try {
					fo.close();
				} catch (IOException e) {
					throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL);
				}
			}
		}
	}

	/**
	 * @Method Name : uploadFileToUploadFileInfo
	 * @작성일 : 2023. 10. 31.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 단일 파일 업로드
	 * @return UploadFileInfo (업로드 파일 정보)
	 */
	public UploadFileInfo uploadFileToUploadFileInfo(MultipartFile file){
		UploadFileInfo uploadFileInfo = new UploadFileInfo();
		
//		String fileOrginalNm = encodeFileOrgName(file);
		String fileNm = generateFileNm(file.getOriginalFilename());
		String today = BDDateFormatUtil.isNowStr("yyyyMMdd").toString();
		String uploadPath = getUploadPath(today) + File.separator + fileNm;
		Long fileSize = file.getSize();
		
		File uploadFile = new File(uploadPath);
		FileOutputStream fo = null;
		try {
			if(!file.isEmpty()) {
				fo = new FileOutputStream(uploadFile);
				byte[] fileBytes = file.getBytes();
				fo.write(fileBytes);
				uploadFileInfo.setAtchFile(fileBytes);
			}
		} catch (IOException e) {
			throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL);
		} finally {
			if(fo != null) {
				try {
					fo.close();
				} catch (IOException e) {
					throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL);
				}
			}
		}
		uploadFileInfo.setFileNm(fileNm);
		uploadFileInfo.setOriginalFileNm(file.getOriginalFilename());
		uploadFileInfo.setUploadTime(today);
		uploadFileInfo.setFilePath(uploadPath);
		uploadFileInfo.setFileSize(fileSize);
		
		return uploadFileInfo;
	}
	
	/**
	 * @Method Name : uploadFileToUploadFileInfoChkExtension
	 * @작성일 : 2023. 12. 19.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 단일 파일 업로드 (확장자검사)
	 * @return UploadFileInfo (업로드 파일 정보)
	 */
	public UploadFileInfo uploadFileToUploadFileInfoChkExtension(MultipartFile file,String[] extArr){
		UploadFileInfo uploadFileInfo = new UploadFileInfo();
		
//		String fileOrginalNm = encodeFileOrgName(file);
		String fileNm = generateFileNm(file.getOriginalFilename());
		String today = BDDateFormatUtil.isNowStr("yyyyMMdd").toString();
		String uploadPath = getUploadPath(today) + File.separator + fileNm;
		Long fileSize = file.getSize();
		
		if(!chkFileExt(file.getOriginalFilename(),extArr)) {
			throw new CommonException(ErrorCode.FILE_EXTENTION_MISMATCH);
		}
		
		File uploadFile = new File(uploadPath);
		FileOutputStream fo = null;
		try {
			if(!file.isEmpty()) {
				fo = new FileOutputStream(uploadFile);
				byte[] fileBytes = file.getBytes();
				fo.write(fileBytes);
				uploadFileInfo.setAtchFile(fileBytes);
			}
		} catch (IOException e) {
			throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL);
		} finally {
			if(fo != null) {
				try {
					fo.close();
				} catch (IOException e) {
					throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL);
				}
			}
		}
		uploadFileInfo.setFileNm(fileNm);
		uploadFileInfo.setOriginalFileNm(file.getOriginalFilename());
		uploadFileInfo.setUploadTime(today);
		uploadFileInfo.setFilePath(uploadPath);
		uploadFileInfo.setFileSize(fileSize);
		
		return uploadFileInfo;
	}
	
	/**
	 * @Method Name : uploadFilesToUploadFileInfoList
	 * @작성일 : 2023. 11. 1.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 복수 파일 업로드
	 * @return List<UploadFileInfo> (업로드 파일 정보)
	 */
	public List<UploadFileInfo> uploadFilesToUploadFileInfoList(MultipartFile[] uploadFiles){
		List<UploadFileInfo> result = new ArrayList<UploadFileInfo>();
		
		if(uploadFiles != null && uploadFiles.length > 0) {
			for(MultipartFile file : uploadFiles) {
				UploadFileInfo uploadFileInfo = new UploadFileInfo();
				
//				String fileOrginalNm = encodeFileOrgName(file);
				String fileNm = generateFileNm(file.getOriginalFilename());
				String today = BDDateFormatUtil.isNowStr("yyyyMMdd").toString();
				String uploadPath = getUploadPath(today) + File.separator + fileNm;
				Long fileSize = file.getSize();
				
				File uploadFile = new File(uploadPath);
				FileOutputStream fo = null;
				try {
					if(!file.isEmpty()) {
						fo = new FileOutputStream(uploadFile);
						byte[] fileBytes = file.getBytes();
						fo.write(fileBytes);
						uploadFileInfo.setAtchFile(fileBytes);
					}
				} catch (IOException e) {
					throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL);
				} finally {
					if(fo != null) {
						try {
							fo.close();
						} catch (IOException e) {
							throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL);
						}
					}
				}
				uploadFileInfo.setFileNm(fileNm);
				uploadFileInfo.setOriginalFileNm(file.getOriginalFilename());
				uploadFileInfo.setUploadTime(today);
				uploadFileInfo.setFilePath(uploadPath);
				uploadFileInfo.setFileSize(fileSize);
				result.add(uploadFileInfo);
			}
		}
		return result;
	}
	
	/**
	 * @Method Name : uploadFilesToUploadFileInfoListChkExtension
	 * @작성일 : 2023. 12. 19.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 복수 파일 업로드 (확장자 검사)
	 * @return List<UploadFileInfo> (업로드 파일 정보)
	 */
	public List<UploadFileInfo> uploadFilesToUploadFileInfoListChkExtension(MultipartFile[] uploadFiles, String[] extArr){
		List<UploadFileInfo> result = new ArrayList<UploadFileInfo>();
		
		if(uploadFiles != null && uploadFiles.length > 0) {
			if(!chkfileExtList(uploadFiles,extArr)) {
				throw new CommonException(ErrorCode.FILE_EXTENTION_MISMATCH);
			}
			
			for(MultipartFile file : uploadFiles) {
				UploadFileInfo uploadFileInfo = new UploadFileInfo();
				
//				String fileOrginalNm = encodeFileOrgName(file);
				String fileNm = generateFileNm(file.getOriginalFilename());
				String today = BDDateFormatUtil.isNowStr("yyyyMMdd").toString();
				String uploadPath = getUploadPath(today) + File.separator + fileNm;
				Long fileSize = file.getSize();
				
				File uploadFile = new File(uploadPath);
				FileOutputStream fo = null;
				try {
					if(!file.isEmpty()) {
						fo = new FileOutputStream(uploadFile);
						byte[] fileBytes = file.getBytes();
						fo.write(fileBytes);
						uploadFileInfo.setAtchFile(fileBytes);
					}
				} catch (IOException e) {
					throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL);
				} finally {
					if(fo != null) {
						try {
							fo.close();
						} catch (IOException e) {
							throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL);
						}
					}
				}
				uploadFileInfo.setFileNm(fileNm);
				uploadFileInfo.setOriginalFileNm(file.getOriginalFilename());
				uploadFileInfo.setUploadTime(today);
				uploadFileInfo.setFilePath(uploadPath);
				uploadFileInfo.setFileSize(fileSize);
				result.add(uploadFileInfo);
			}
		}
		return result;
	}
	
	
	/**
     * @Method Name : encodeFileOrgName
     * @작성일 : 2023. 10. 31.
     * @작성자 : KY.LEE
     * @Method 설명 : 파일이름 인코딩
     * @return
     */
	public static String encodeFileOrgName(MultipartFile file){
		String encFileNm = "";
		try {
			encFileNm = new String(file.getOriginalFilename().getBytes("8859_1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL);
		}
		return encFileNm;
	}
	
	/**
     * @Method Name : deleteUploadFile
     * @작성일 : 2023. 10. 31.
     * @작성자 : KY.LEE
     * @Method 설명 : 파일 삭제
     * @return
     */
	public void deleteUploadFile(String filePath){
		File uploadFile = new File(filePath);
		
		if(uploadFile.exists()) {
			uploadFile.delete();
		} else {
//			throw new CommonException(ErrorCode.FILE_NOT_FOUND);
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
		if(fileNm != null) {
			String ext = fileNm.substring(fileNm.lastIndexOf(".")+1, fileNm.length());
			
			isContatin = Arrays.asList(arrExt).contains(ext);
		}
		return isContatin;
	}
	
	/**
	 * @Method Name : chkfileExtList
	 * @작성일 : 2023. 12. 19.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 파일 리스트 확장자 체크
	 * @return
	 */
	public boolean chkfileExtList(MultipartFile[] uploadFiles, String[] arrExt) {
		boolean isContatin = false;
		if(uploadFiles != null && uploadFiles.length > 0) {
			for(MultipartFile file : uploadFiles) {
					String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1, file.getOriginalFilename().length());
					
					isContatin = Arrays.asList(arrExt).contains(ext);
					
					if(!isContatin) {
						return false;
					}
			}
		}
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
