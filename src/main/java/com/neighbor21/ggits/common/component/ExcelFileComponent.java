package com.neighbor21.ggits.common.component;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtAccntLog;
import com.neighbor21.ggits.common.entity.MrtRoadAccntAnal;
import com.neighbor21.ggits.common.entity.MrtTrfFcltsSttsAnls;
import com.neighbor21.ggits.common.mapper.MrtAccntLogMapper;
import com.neighbor21.ggits.common.mapper.MrtRoadAccntAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtTrfFcltsSttsAnlsMapper;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;

@Component
public class ExcelFileComponent {
	
	@Value("#{commonProperties['excel.sample.file.download.path']}")
	public String trafficImpactReportSampleFileDownload;
	
	@Autowired
	MrtTrfFcltsSttsAnlsMapper mrtTrfFcltsSttsAnlsMapper;
	
	@Autowired
	MrtAccntLogMapper mrtAccntLogMapper;
	
	@Autowired
	MrtRoadAccntAnalMapper mrtRoadAccntAnalMapper;
	
	/**
     * @Method Name : uploadTrafficImpactReport
     * @작성일 : 2023. 10. 19.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통영향평가 신규 등록
     * @return
	 * @throws IOException 
     */
	public void uploadTrafficImpactReport(MultipartFile file, String type) throws IOException {
		List<List<String>> duplList = new ArrayList<>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(file.getInputStream(), "MS949"));
			String line = "";
			
			int rowNum = 0;
			while((line = br.readLine()) != null) {
				List<String> strList = new ArrayList<String>();
				String strArray[] = line.split(",", -1);
				strList = Arrays.asList(strArray);
                
				switch (type) {
				case "1": // 교차로 교통량
					
					break;
				case "2": // 가로구간 교통량
					
					break;
				case "3": // 보행,자전거,PM 교통량(필요시)
					
					break;
				case "4": // 유사시설 활동인구 원단위
					
					break;
				case "5": // 교통수단 분담율
					if(rowNum >= 5) {
						for(int i = 0; i <= strList.size(); i++) {
							// cellNull 0~7이면 구분 시간데이터
								// 저장
							// 8이상이고 16단위마다 dto 하나씩 비주거용도 비주거용도
								// 저장
						}
					}
					break;
				case "6": // 재차인원
					if(rowNum >= 5) {
						for(int i = 0; i <= strList.size(); i++) {
							// cellNull 0~7이면 구분 시간데이터
								// 저장
							// 8이상이고 5단위마다 dto 하나씩 비주거용도 비주거용도
								// 저장
						}
					}
					break;
				case "7": // 시간대별 유출입 통행분포 조사
					if(rowNum >= 8) {
						for(int i = 0; i <= strList.size(); i++) {
							// cellNull 0~이면 구분 시간데이터
								// 저장
							// 1이상이고 12단위마다 dto 하나씩 비주거용도 비주거용도
								// 저장
						}
					}
					break;
				case "8": // 주차발생 원단위 조사자료
					if(rowNum >= 4) {
						for(int i = 0; i <= strList.size(); i++) {
							// cellNull 0~7이면 주거용도 상주
								// 저장
							// 7이상이고 5단위마다 dto 하나씩 비주거용도 비주거용도
								// 저장
						}
					}
					break;
				case "9": // 시간대별 통행 분포비
					if(rowNum >= 8) {
						for(int i = 0; i <= strList.size(); i++) {
							// cellNull 0~8이면 주거용도 상주
								// 저장
							// 9이상이고 5단위마다 dto 하나씩 비주거용도 비주거용도
								// 저장	
						}
					}
					break;
				}
				duplList.add(strList);
				rowNum = ++rowNum;
			}
		} catch (FileNotFoundException e) {
			
		} finally {
				try {
					if(br != null) {
						br.close();
					}					
				} catch (IOException e) {
					
				}
		}
	}

	
	/**
	 * @Method Name : downLoadExcelFile
     * @작성일 : 2023. 10. 23.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계 화면 엑셀 다운로드
     * @param : resp
     * @param : type
     * @param : commonEntity
     * @return
	 * @throws IOException 
     */
	public void downLoadExcelFile(HttpServletResponse resp, String type, CommonEntity commonEntity) throws IOException {
		String fileNm = BDDateFormatUtil.isNowStr("yyyyMMddhhmmss");
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = null;
		int rowNo = 0;
		Row headerRow = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		switch (type) {
		case "traffic_facility_obtc_colt": // 통계분석 > 교통시설물 통계 > 교통시설물 장애통계
			sheet = workbook.createSheet("교통시설물 장애통계");
			fileNm = fileNm+"_교통시설물_장애통계";
			
			headerRow = sheet.createRow(rowNo++);
	        headerRow.createCell(0).setCellValue("도로명");
	        headerRow.createCell(1).setCellValue("도로유형");
	        headerRow.createCell(2).setCellValue("방향");
	        headerRow.createCell(3).setCellValue("시설물");
	        headerRow.createCell(4).setCellValue("ID");
	        headerRow.createCell(5).setCellValue("장애 발생 횟수");
	        
			List<MrtTrfFcltsSttsAnls> trfFcltsSttsAnlsList = mrtTrfFcltsSttsAnlsMapper.findAllTrfFcltsSttsAnls(commonEntity);
	        for(MrtTrfFcltsSttsAnls trfFcltsSttsAnls : trfFcltsSttsAnlsList) {
	        	 Row row = sheet.createRow(rowNo++);
	        	 row.createCell(0).setCellValue(trfFcltsSttsAnls.getRoadName());
	             row.createCell(1).setCellValue(trfFcltsSttsAnls.getRoadGrd());
	             row.createCell(2).setCellValue(trfFcltsSttsAnls.getRoadDrct());
	             row.createCell(3).setCellValue(trfFcltsSttsAnls.getFcltsType());
	             row.createCell(4).setCellValue(trfFcltsSttsAnls.getFcltsId());
	             row.createCell(5).setCellValue(trfFcltsSttsAnls.getTrblOccurCnt());
	        }
			break;
		case "traffic_facility_equipment_log_info" : // 통계분석 > 교통시설물 통계 > 교통시설물 장비 로그 상세
			sheet = workbook.createSheet("교통시설물 장비 로그 상세");
			fileNm = fileNm+"_교통시설물_장비_로그_상세";
			
			headerRow = sheet.createRow(rowNo++);
	        headerRow.createCell(0).setCellValue("도로명");
	        headerRow.createCell(1).setCellValue("도로유형");
	        headerRow.createCell(2).setCellValue("방향");
	        headerRow.createCell(3).setCellValue("시설물");
	        headerRow.createCell(4).setCellValue("ID");
	        headerRow.createCell(5).setCellValue("날짜");
	        headerRow.createCell(6).setCellValue("상태");
	        headerRow.createCell(7).setCellValue("상세내용");
	        
	        List<MrtTrfFcltsSttsAnls> trfFcltsEqpLogList = mrtTrfFcltsSttsAnlsMapper.findAllTrfFcltsEqpLogList(commonEntity);
	        
	        for(MrtTrfFcltsSttsAnls trfFcltsEqpLog : trfFcltsEqpLogList) {
	        	Row row = sheet.createRow(rowNo++);
	        	row.createCell(0).setCellValue(trfFcltsEqpLog.getRoadName());
	            row.createCell(1).setCellValue(trfFcltsEqpLog.getRoadGrd());
	            row.createCell(2).setCellValue(trfFcltsEqpLog.getRoadDrct());
	            row.createCell(3).setCellValue(trfFcltsEqpLog.getFcltsType());
	            row.createCell(4).setCellValue(trfFcltsEqpLog.getFcltsId());
	            row.createCell(5).setCellValue(sdf.format(trfFcltsEqpLog.getAnlsDt()));
	            row.createCell(6).setCellValue("-");
	            row.createCell(7).setCellValue("-");
	        }
	        break;
		case "traffic_acndt_gen_log": // 통계분석 > 도로안전 > 교통사고 발생이력 
			sheet = workbook.createSheet("교통사고 발생이력");
			fileNm = fileNm+"_교통사고_발생이력";
			
			headerRow = sheet.createRow(rowNo);
			sheet.addMergedRegion(new CellRangeAddress((int)0, (int)1, (short)0, (short)0));	// 도로명
			sheet.addMergedRegion(new CellRangeAddress((int)0, (int)1, (short)1, (short)1));	// 발생건수
			sheet.addMergedRegion(new CellRangeAddress((int)0, (int)1, (short)2, (short)2));	// 사상자수
			sheet.addMergedRegion(new CellRangeAddress((int)0, (int)0, (short)3, (short)5)); 	// 사고건수
			sheet.addMergedRegion(new CellRangeAddress((int)0, (int)0, (short)6, (short)8)); 	// 사상자수
			sheet.addMergedRegion(new CellRangeAddress((int)0, (int)1, (short)9, (short)9)); 	// 교통문화지수
			
			headerRow.createCell(0).setCellValue (new HSSFRichTextString("도로명"));
			headerRow.createCell(1).setCellValue (new HSSFRichTextString("발생건수"));
			headerRow.createCell(2).setCellValue (new HSSFRichTextString("사상자수"));
			headerRow.createCell(3).setCellValue (new HSSFRichTextString("사고건수"));
			headerRow.createCell(6).setCellValue (new HSSFRichTextString("사상자수"));
			headerRow.createCell(9).setCellValue (new HSSFRichTextString("교통문화지수"));
			
			headerRow = sheet.createRow(++rowNo);
			headerRow.createCell(3).setCellValue (new HSSFRichTextString("인구 10만명당"));
			headerRow.createCell(4).setCellValue (new HSSFRichTextString("자동차 1만대당"));
			headerRow.createCell(5).setCellValue (new HSSFRichTextString("도로 100만km당"));
			headerRow.createCell(6).setCellValue (new HSSFRichTextString("인구 10만명당"));
			headerRow.createCell(7).setCellValue (new HSSFRichTextString("자동차 1만대당"));
			headerRow.createCell(8).setCellValue (new HSSFRichTextString("도로 100만km당"));
			
			
			List<MrtAccntLog> acdntGenLogInfoList = mrtAccntLogMapper.findAllAcdntGenLogInfo(commonEntity);
			
			for(MrtAccntLog acdntGenLogInfo : acdntGenLogInfoList) {
				Row row = sheet.createRow(++rowNo);
	        	row.createCell(0).setCellValue(acdntGenLogInfo.getRoadName());
	            row.createCell(1).setCellValue(acdntGenLogInfo.getAcdntCnt());
	            row.createCell(2).setCellValue(acdntGenLogInfo.getDcsdCnt());
	            row.createCell(3).setCellValue(acdntGenLogInfo.getPopltn100kAcdntCnt());
	            row.createCell(4).setCellValue(acdntGenLogInfo.getVhcl10kAcdntCnt());
	            row.createCell(5).setCellValue("-");
	            row.createCell(6).setCellValue("-");
	            row.createCell(7).setCellValue("-");
	            row.createCell(8).setCellValue("-");
	            row.createCell(9).setCellValue("-");
			}
			break;
		case "traffic_acdnt_catg_log_colt": // 통계분석 > 도로안전 > 교통사고 유형별 이력집계
			sheet = workbook.createSheet("교통사고 유형별 이력집계");
			fileNm = fileNm+"_교통사고_유형별_이력집계";
			
			headerRow = sheet.createRow(rowNo++);
	        headerRow.createCell(0).setCellValue("도로명");
	        headerRow.createCell(1).setCellValue("위반유형");
	        headerRow.createCell(2).setCellValue("사고건수");
	        headerRow.createCell(3).setCellValue("사망사고");
	        headerRow.createCell(4).setCellValue("대형사고");
	        headerRow.createCell(5).setCellValue("중상사고");
	        headerRow.createCell(6).setCellValue("경상사고");
	        headerRow.createCell(7).setCellValue("치사율(%)");
	        
	        List<MrtRoadAccntAnal> acdntCatgLogColtList = mrtRoadAccntAnalMapper.findAllAcdntCatgLogColt(commonEntity);
	        
        	long totAcdntCnt = 0; // 전체 사고 건수
        	long dedAcdntCnt = 0; // 사망 사고 건수
        	long majAcdntCnt = 0; // 대형 사고 건수
        	long serusInjrAcdntCnt = 0; // 중상 사고 건수
        	long minrAcdntCnt = 0; // 경상 사고 건수
        	
	        for(MrtRoadAccntAnal acdntCatgLogColt : acdntCatgLogColtList) {
	        	totAcdntCnt += acdntCatgLogColt.getAcdntOccurCnt();
	        	dedAcdntCnt += acdntCatgLogColt.getDcsdCnt();
        		Row row = sheet.createRow(++rowNo);
        		row.createCell(0).setCellValue(acdntCatgLogColt.getRoadName());
        		row.createCell(1).setCellValue(acdntCatgLogColt.getCdNm());
        		row.createCell(2).setCellValue(acdntCatgLogColt.getAcdntOccurCnt());
        		row.createCell(3).setCellValue("-");
        		row.createCell(4).setCellValue("-");
        		row.createCell(5).setCellValue("-");
        		row.createCell(6).setCellValue("-");
        		row.createCell(7).setCellValue(acdntCatgLogColt.getFtltyRate());
	        }
	        
	        headerRow = sheet.createRow(1);
	        sheet.addMergedRegion(new CellRangeAddress((int)0, (int)0, (short)0, (short)1));	// 합계
	        Row row = sheet.createRow(1);
	        row.createCell(0).setCellValue("합계");
	        row.createCell(2).setCellValue(totAcdntCnt);
	        row.createCell(3).setCellValue(dedAcdntCnt);
	        row.createCell(4).setCellValue(majAcdntCnt);
	        row.createCell(5).setCellValue(serusInjrAcdntCnt);
	        row.createCell(6).setCellValue(minrAcdntCnt);
	        row.createCell(7).setCellValue(String.format("%.2f", (double)dedAcdntCnt/totAcdntCnt * 100));
			break;
		}
		
		resp.setContentType("ms-vnd/excel");
		String outputFileName = new String(fileNm.getBytes("KSC5601"), "8859_1");
		resp.setHeader("Content-Disposition", "attachment;filename="+outputFileName+".xls");
		
		workbook.write(resp.getOutputStream());
        workbook.close();
	}

	/**
	 * @Method Name : downloadTrafficImpactReportSampleFile
     * @작성일 : 2023. 10. 27.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통영향평가 양식 다운로드
     * @param : res
     * @param : fileNm
     * @return 
     */
	public void downloadTrafficImpactReportSampleFile(HttpServletResponse res, String fileNm) {
		try {
			String filePath = trafficImpactReportSampleFileDownload+fileNm;
        	ClassPathResource resource = new ClassPathResource(filePath);
        	File file = new File(resource.getURL().getPath());
        	
            int fileSize = (int) file.length();
            if (fileSize > 0) {
                String encodedFilename = "attachment; filename*=" + "UTF-8" + "''" + URLEncoder.encode(fileNm, "UTF-8");
                res.setContentType("text/csv; charset=UTF-8");
                res.setHeader("Content-Disposition", encodedFilename);
                res.setContentLengthLong(fileSize);

                BufferedInputStream in = null;
                BufferedOutputStream out = null;

                in = new BufferedInputStream(new FileInputStream(file));
                out = new BufferedOutputStream(res.getOutputStream());

                try {
                    byte[] buffer = new byte[4096];
                    int bytesRead = 0;
                    while ((bytesRead = in .read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                    out.flush();
                } finally {
                    in.close();
                    out.close();
                }
            } else {
                throw new FileNotFoundException("양식이 존재하지 않습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
