package com.neighbor21.ggits.common.component;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neighbor21.ggits.common.dto.TimeInoutflExmnDTO;
import com.neighbor21.ggits.common.dto.TimePassDistrbDTO;
import com.neighbor21.ggits.common.dto.TrfvlmStatisticsDTO;
import com.neighbor21.ggits.common.dto.TrfvlmStatisticsDTO.TrfvlmStatistics;
import com.neighbor21.ggits.common.dto.TrfvlmStatisticsDTO.TrfvlmStatistics.TimeTrfvlmData;
import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.EqpLogDTO;
import com.neighbor21.ggits.common.entity.MOpCode;
import com.neighbor21.ggits.common.entity.MrtAccntLog;
import com.neighbor21.ggits.common.entity.MrtIpcssAnal;
import com.neighbor21.ggits.common.entity.MrtRoadAccntAnal;
import com.neighbor21.ggits.common.entity.MrtTrfFcltsSttsAnls;
import com.neighbor21.ggits.common.entity.TaasAdsiAcdntDstrct;
import com.neighbor21.ggits.common.entity.TrfIpcssActPopltnBsunt;
import com.neighbor21.ggits.common.entity.TrfIpcssEtcTrfvlm;
import com.neighbor21.ggits.common.entity.TrfIpcssExmnBizInfo;
import com.neighbor21.ggits.common.entity.TrfIpcssMeanShareRt;
import com.neighbor21.ggits.common.entity.TrfIpcssNbopl;
import com.neighbor21.ggits.common.entity.TrfIpcssParkngOccurBsunt;
import com.neighbor21.ggits.common.entity.TrfIpcssTimePassDistrb;
import com.neighbor21.ggits.common.mapper.AdsiRseSttsInfoMapper;
import com.neighbor21.ggits.common.mapper.AdsiSmcrsrdCameraSttsInfoMapper;
import com.neighbor21.ggits.common.mapper.AdsiVdsSttsInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MrtAccntLogMapper;
import com.neighbor21.ggits.common.mapper.MrtIpcssAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtRoadAccntAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtTrfFcltsSttsAnlsMapper;
import com.neighbor21.ggits.common.mapper.TaasAdsiAcdntDstrctMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssActPopltnBsuntMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssCrsrdTrfvlmMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssEtcTrfvlmMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssExmnBizInfoMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssMeanShareRtMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssNboplMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssParkngOccurBsuntMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssStrsctTrfvlmMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssTimeInoutflExmnMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssTimePassDistrbMapper;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
import com.neighbor21.ggits.common.util.ExcelUploadUtil;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;

@Component
public class ExcelDownloadComponent {
	@Value("#{commonProperties['excel.sample.file.download.path']}")
	public String trafficImpactReportSampleFileDownload;

	@Autowired
	MrtTrfFcltsSttsAnlsMapper mrtTrfFcltsSttsAnlsMapper;

	@Autowired
	MrtAccntLogMapper mrtAccntLogMapper;

	@Autowired
	MrtRoadAccntAnalMapper mrtRoadAccntAnalMapper;

	@Autowired
	AdsiSmcrsrdCameraSttsInfoMapper adsiSmcrsrdCameraSttsInfoMapper;

	@Autowired
	AdsiRseSttsInfoMapper adsiRseSttsInfoMapper;

	@Autowired
	AdsiVdsSttsInfoMapper adsiVdsSttsInfoMapper;

	@Autowired
	TrfIpcssExmnBizInfoMapper trfIpcssExmnBizInfoMapper;

	@Autowired
	TrfIpcssActPopltnBsuntMapper trfIpcssActPopltnBsuntMapper;

	@Autowired
	MOpCodeMapper mOpCodeMapper;

	@Autowired
	TrfIpcssMeanShareRtMapper trfIpcssMeanShareRtMapper;

	@Autowired
	TrfIpcssStrsctTrfvlmMapper trfIpcssStrsctTrfvlmMapper;

	@Autowired
	TrfIpcssEtcTrfvlmMapper trfIpcssEtcTrfvlmMapper;

	@Autowired
	TrfIpcssNboplMapper trfIpcssNboplMapper;

	@Autowired
	TrfIpcssTimeInoutflExmnMapper trfIpcssTimeInoutflExmnMapper;

	@Autowired
	TrfIpcssParkngOccurBsuntMapper trfIpcssParkngOccurBsuntMapper;

	@Autowired
	TrfIpcssTimePassDistrbMapper trfIpcssTimePassDistrbMapper;

	@Autowired
	TrfIpcssCrsrdTrfvlmMapper trfIpcssCrsrdTrfvlmMapper;

	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	MrtIpcssAnalMapper mrtIpcssAnalMapper;
	
	@Autowired
	TaasAdsiAcdntDstrctMapper taasAdsiAcdntDstrctMapper;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
	 * @throws ParseException
	 */
	public void downLoadExcelFile(HttpServletResponse resp, String type, CommonEntity commonEntity)
			throws IOException, ParseException {
		String fileNm = BDDateFormatUtil.isNowStr("yyyyMMddhhmmss");
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = null;
		int rowNo = 0;
		Row headerRow = null;

		commonEntity.setPage(0);

		SimpleDateFormat inputSdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat outputSdf = new SimpleDateFormat("yyyy-MM-dd");

		switch (type) {
		case "traffic_facility_obtc_colt": // 통계분석 > 교통시설물 통계 > 교통시설물 장애통계
			sheet = workbook.createSheet("교통시설물 장애통계");
			fileNm = fileNm + "_교통시설물_장애통계";

			headerRow = sheet.createRow(rowNo++);
			headerRow.createCell(0).setCellValue("도로명");
			headerRow.createCell(1).setCellValue("도로유형");
			headerRow.createCell(2).setCellValue("방향");
			headerRow.createCell(3).setCellValue("시설물");
			headerRow.createCell(4).setCellValue("ID");
			headerRow.createCell(5).setCellValue("장애 발생 횟수");

			List<MrtTrfFcltsSttsAnls> trfFcltsSttsAnlsList = mrtTrfFcltsSttsAnlsMapper
					.findAllTrfFcltsSttsAnls(commonEntity);
			for (MrtTrfFcltsSttsAnls trfFcltsSttsAnls : trfFcltsSttsAnlsList) {
				Row row = sheet.createRow(rowNo++);
				row.createCell(0).setCellValue(trfFcltsSttsAnls.getRoadName());
				row.createCell(1).setCellValue(trfFcltsSttsAnls.getRoadGrd());
				row.createCell(2).setCellValue(trfFcltsSttsAnls.getRoadDrct());
				row.createCell(3).setCellValue(trfFcltsSttsAnls.getFcltsType());
				row.createCell(4).setCellValue(trfFcltsSttsAnls.getFcltsId());
				row.createCell(5).setCellValue(trfFcltsSttsAnls.getTrblOccurCnt());
			}
			break;
		case "traffic_facility_equipment_log_info": // 통계분석 > 교통시설물 통계 > 교통시설물 장비 로그 상세
			sheet = workbook.createSheet("교통시설물 장비 로그 상세");
			fileNm = fileNm + "_교통시설물_장비_로그_상세";

			headerRow = sheet.createRow(rowNo++);
			headerRow.createCell(0).setCellValue("시설물 ID");
			headerRow.createCell(1).setCellValue("시설물 명");
			headerRow.createCell(2).setCellValue("상태");
			headerRow.createCell(3).setCellValue("날짜");

			commonEntity.setPage(0);

			List<EqpLogDTO> eqpLogList = new ArrayList<EqpLogDTO>();
			if (commonEntity.getSearchType().equals("VDS")) {
				eqpLogList = adsiVdsSttsInfoMapper.findAllVdsInfoList(commonEntity);
			} else if (commonEntity.getSearchType().equals("RSE")) {
				eqpLogList = adsiRseSttsInfoMapper.findAllRseInfoList(commonEntity);
			} else if (commonEntity.getSearchType().equals("SMC")) {
				eqpLogList = adsiSmcrsrdCameraSttsInfoMapper.findAllSmcInfoList(commonEntity);
			}

			for (EqpLogDTO trfFcltsEqpLog : eqpLogList) {
				Row row = sheet.createRow(rowNo++);
				row.createCell(0).setCellValue(trfFcltsEqpLog.getId());
				row.createCell(1).setCellValue(trfFcltsEqpLog.getName());
				row.createCell(2).setCellValue(trfFcltsEqpLog.getStts());
				row.createCell(3).setCellValue(trfFcltsEqpLog.getClctDt());
			}
			break;
		case "traffic_acndt_gen_log": // 통계분석 > 도로안전 > 교통사고 발생이력
			sheet = workbook.createSheet("교통사고 발생이력");
			fileNm = fileNm + "_교통사고_발생이력";

			headerRow = sheet.createRow(rowNo);
			sheet.addMergedRegion(new CellRangeAddress((int) 0, (int) 0, (short) 0, (short) 0)); // 시군명
			sheet.addMergedRegion(new CellRangeAddress((int) 0, (int) 0, (short) 1, (short) 1)); // 발생건 수
			sheet.addMergedRegion(new CellRangeAddress((int) 0, (int) 0, (short) 2, (short) 2)); // 사상자 수
			sheet.addMergedRegion(new CellRangeAddress((int) 0, (int) 0, (short) 3, (short) 3)); // 사망자 수
			sheet.addMergedRegion(new CellRangeAddress((int) 0, (int) 0, (short) 4, (short) 4)); // 중상자 수
			sheet.addMergedRegion(new CellRangeAddress((int) 0, (int) 0, (short) 5, (short) 5)); // 경상자 수
			sheet.addMergedRegion(new CellRangeAddress((int) 0, (int) 0, (short) 6, (short) 6)); // 부상 신고 수

			headerRow.createCell(0).setCellValue(new HSSFRichTextString("시군명"));
			headerRow.createCell(1).setCellValue(new HSSFRichTextString("발생건 수"));
			headerRow.createCell(2).setCellValue(new HSSFRichTextString("사상자 수"));
			headerRow.createCell(3).setCellValue(new HSSFRichTextString("사망자 수"));
			headerRow.createCell(4).setCellValue(new HSSFRichTextString("중상자 수"));
			headerRow.createCell(5).setCellValue(new HSSFRichTextString("경상자 수"));
			headerRow.createCell(6).setCellValue(new HSSFRichTextString("부상 신고 수"));
			
			List<TaasAdsiAcdntDstrct> acdntGenLogInfo = taasAdsiAcdntDstrctMapper.findAllAcdntGenLogInfo(commonEntity);

			for (TaasAdsiAcdntDstrct item : acdntGenLogInfo) {
				Row row = sheet.createRow(++rowNo);
				row.createCell(0).setCellValue(item.getCdNm());
				row.createCell(1).setCellValue(item.getAcdntCnt());
				row.createCell(2).setCellValue(item.getCasltCnt());
				row.createCell(3).setCellValue(item.getDcsdCnt());
				row.createCell(4).setCellValue(item.getSwpsnCnt());
				row.createCell(5).setCellValue(item.getSinjpsnCnt());
				row.createCell(6).setCellValue(item.getInjDclrCnt());
			}
			break;
		case "traffic_acdnt_catg_log_colt": // 통계분석 > 도로안전 > 교통사고 유형별 이력집계
			sheet = workbook.createSheet("교통사고 유형별 이력집계");
			fileNm = fileNm + "_교통사고_유형별_이력집계";

			headerRow = sheet.createRow(rowNo++);
			headerRow.createCell(0).setCellValue("도로명");
			if (commonEntity.getSelAcdntTp().equals("link")) {
				headerRow.createCell(2).setCellValue("도로유형");
			} else {
				headerRow.createCell(2).setCellValue("사고유형");
			}
			headerRow.createCell(3).setCellValue("사고건수");
			headerRow.createCell(4).setCellValue("사상자수");
			headerRow.createCell(5).setCellValue("사망사고");
			headerRow.createCell(6).setCellValue("치사율(%)");

			List<MrtRoadAccntAnal> acdntCatgLogColtList = mrtRoadAccntAnalMapper.findAllAcdntCatgLogColt(commonEntity);

			long totAcdntCnt = 0; // 전체 사고 건수
			long totCasltCnt = 0; // 전체 사고 건수
			long dedAcdntCnt = 0; // 사망 사고 건수

			for (MrtRoadAccntAnal acdntCatgLogColt : acdntCatgLogColtList) {
				totAcdntCnt += acdntCatgLogColt.getAcdntOccurCnt();
				totCasltCnt += acdntCatgLogColt.getTotCasltCnt();
				dedAcdntCnt += acdntCatgLogColt.getDcsdCnt();
				Row row = sheet.createRow(++rowNo);
				row.createCell(0).setCellValue(acdntCatgLogColt.getRoadName());
				if (commonEntity.getSelAcdntTp().equals("link")) {
					row.createCell(2).setCellValue(acdntCatgLogColt.getRoadRank());
				} else {
					row.createCell(2).setCellValue(acdntCatgLogColt.getAcdntTypeCd());
				}
				row.createCell(3).setCellValue(acdntCatgLogColt.getAcdntOccurCnt());
				row.createCell(4).setCellValue(acdntCatgLogColt.getTotCasltCnt());
				row.createCell(5).setCellValue(acdntCatgLogColt.getDcsdCnt());
				row.createCell(6).setCellValue(acdntCatgLogColt.getFtltyRate());
			}

			headerRow = sheet.createRow(1);
			sheet.addMergedRegion(new CellRangeAddress((int) 0, (int) 0, (short) 0, (short) 1)); // 합계
			Row row = sheet.createRow(1);
			row.createCell(0).setCellValue("합계");
			row.createCell(3).setCellValue(totAcdntCnt);
			row.createCell(4).setCellValue(totCasltCnt);
			row.createCell(5).setCellValue(dedAcdntCnt);
			row.createCell(6).setCellValue(String.format("%.2f", (double) dedAcdntCnt / totAcdntCnt * 100));
			break;

		case "reg_enty_exit_data_tnt":
			sheet = workbook.createSheet("교통영향평가 데이터 집계");
			fileNm = fileNm + "_교통영향평가_데이터_집계";

			headerRow = sheet.createRow(rowNo++);
			headerRow.createCell(0).setCellValue("평가명");
			headerRow.createCell(1).setCellValue("기간");
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));

			int startHeaderIndex = 2;
			int endHeaderIndex = 3;
			List<MOpCode> usgCdList = mOpCodeMapper.findAllCodeListByGrpCdId("USG_CD");
			for (MOpCode usgCdInfo : usgCdList) {
				headerRow.createCell(startHeaderIndex).setCellValue(usgCdInfo.getCdNm());
				sheet.addMergedRegion(new CellRangeAddress(0, 0, startHeaderIndex, startHeaderIndex + 1));
				headerRow.createCell(endHeaderIndex).setCellValue("-");
				startHeaderIndex += 2;
				endHeaderIndex += 2;
			}
			headerRow = sheet.createRow(rowNo++);
			for (int j = 2; j <= 70; j++) {
				if (j % 2 == 1) {
					headerRow.createCell(j - 1).setCellValue("상주/상근");
					headerRow.createCell(j).setCellValue("방문/이동");
				}
			}

			// 데이터 삽입
			List<MrtIpcssAnal> ipcssAnalList = mrtIpcssAnalMapper.findAllIpcssAnalList(commonEntity);

			int rowHeaderIndex = 2;
			for (MrtIpcssAnal ipcssInfo : ipcssAnalList) {
				Row ipcssRow = sheet.createRow(rowNo++);
				ipcssRow.createCell(0).setCellValue(ipcssInfo.getExmnDivNm());

				Date exmnYmdDate = inputSdf.parse(ipcssInfo.getExmnYmd());
				String exmnYmd = outputSdf.format(exmnYmdDate);

				ipcssRow.createCell(1).setCellValue(exmnYmd);

				List<Map<String, Object>> ipcssResultList = mrtIpcssAnalMapper.findOneIpcssAnalInfo(ipcssInfo);
				for (Map<String, Object> ipcssResult : ipcssResultList) {
					String resdngBsunt = String.valueOf(ipcssResult.get("resdng_bsunt"));
					String visitBsunt = String.valueOf(ipcssResult.get("visit_bsunt"));
					ipcssRow.createCell(rowHeaderIndex).setCellValue(Double.parseDouble(resdngBsunt));
					ipcssRow.createCell(++rowHeaderIndex).setCellValue(Double.parseDouble(visitBsunt));
					rowHeaderIndex++;
				}
				if (rowHeaderIndex == 70) {
					rowHeaderIndex = 2;
				}
			}

			break;
		}

		resp.setContentType("ms-vnd/excel");
		String outputFileName = new String(fileNm.getBytes("KSC5601"), "8859_1");
		resp.setHeader("Content-Disposition", "attachment;filename=" + outputFileName + ".xls");

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
			String filePath = trafficImpactReportSampleFileDownload + fileNm;
			ClassPathResource resource = new ClassPathResource(filePath);
			File file = new File(resource.getURL().getPath());

			int fileSize = (int) file.length();
			if (fileSize > 0) {
				fileNm = "별도붙임_별지1호서식 붙임(경기도 교통영향평가 교통 데이터 제출파일)★.xlsx";
				String encodedFilename = "attachment; filename*=" + "UTF-8" + "''" + URLEncoder.encode(fileNm, "UTF-8");
				encodedFilename = encodedFilename.replaceAll("\\+", "%20");
				res.setContentType("ms-vnd/excel; charset=UTF-8");
				res.setHeader("Content-Disposition", encodedFilename);
				res.setContentLengthLong(fileSize);

				BufferedInputStream in = null;
				BufferedOutputStream out = null;

				try {
					in = new BufferedInputStream(new FileInputStream(file));
					out = new BufferedOutputStream(res.getOutputStream());

					byte[] buffer = new byte[4096];
					int bytesRead = 0;
					while ((bytesRead = in.read(buffer)) != -1) {
						out.write(buffer, 0, bytesRead);
					}
					out.flush();
				} finally {
					if(in != null) {
						in.close();
					}
					if(out != null) {
						out.close();
					}
				}
			} else {
				throw new FileNotFoundException("양식이 존재하지 않습니다.");
			}
		} catch (IOException e) {
			logger.info("파일 오류 발생");
		}
	}
	
	/**
	 * @Method Name : downloadImactReportExcel
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 다운로드
	 * @param : res
	 * @param : ipcssMngNo
	 * @return
	 * @throws ParseException 
	 */
	public void downloadImactReportExcel(String ipcssMngNo, HttpServletRequest req, HttpServletResponse resp) throws ParseException {
		String fileNm = "traffic_import_report_sample.xlsx";
		XSSFWorkbook workbook = null;
		try {
			TrfvlmStatisticsDTO trfvlmStatisticsDTO = new TrfvlmStatisticsDTO();
			trfvlmStatisticsDTO.setIpcssMngNo(ipcssMngNo);


			String filePath = trafficImpactReportSampleFileDownload + fileNm;
			ClassPathResource resource = new ClassPathResource(filePath);
			File file = new File(resource.getURL().getPath());

			TrfIpcssExmnBizInfo trfIpcssExmnBizInfo = new TrfIpcssExmnBizInfo(); // 교통 영향평가 조사 사업 정보
			trfIpcssExmnBizInfo = trfIpcssExmnBizInfoMapper.findOneExmnBizInfoByipcssMngNo(ipcssMngNo);
			
			fileNm = trfIpcssExmnBizInfo.getBizNm()+".xlsx";
			
			String encodedFilename = "attachment; filename*=" + "UTF-8" + "''" + URLEncoder.encode(fileNm, "UTF-8");
			encodedFilename = encodedFilename.replaceAll("\\+", "%20");
			resp.setContentType("ms-vnd/excel; charset=UTF-8");
			resp.setHeader("Content-Disposition", encodedFilename);

			
			
			workbook = new XSSFWorkbook(new FileInputStream(file));

			// 용도 코드 리스트
			List<MOpCode> usgCdList = new ArrayList<MOpCode>();
			usgCdList = mOpCodeMapper.findAllCodeListByGrpCdId("USG_CD");

			int sheetSize = workbook.getNumberOfSheets();
			for (int sheetIndex = 0; sheetIndex <= sheetSize; sheetIndex++) {
				// 필독 시트 제외
				if (sheetIndex == 0)
					continue;

				if (sheetIndex == 1) {
					// 교통량 개요 조사
					XSSFSheet sheet = workbook.getSheetAt(1);
					
					sheet.getRow(2).getCell(2).setCellValue(trfIpcssExmnBizInfo.getExmnYy() + "년");
					sheet.getRow(2).getCell(4).setCellValue(trfIpcssExmnBizInfo.getEvalCoNm());
					sheet.getRow(2).getCell(6).setCellValue(trfIpcssExmnBizInfo.getEvalPicNm());
					sheet.getRow(3).getCell(2).setCellValue(trfIpcssExmnBizInfo.getBizNm());
					sheet.getRow(4).getCell(2).setCellValue(trfIpcssExmnBizInfo.getBizUsg());
					sheet.getRow(5).getCell(2).setCellValue(trfIpcssExmnBizInfo.getExmnDd());
				} else if (sheetIndex == 2) {
					// 평일 교차로 교통량
					trfvlmStatisticsDTO.setDywkDiv("평일");
					XSSFSheet sheet = workbook.getSheetAt(2);
					List<TrfvlmStatisticsDTO> crsrdTrfvlmListForDwell = trfIpcssCrsrdTrfvlmMapper.findAllCrsrdTrfvlmStatisticsList(trfvlmStatisticsDTO);
					
					if(!crsrdTrfvlmListForDwell.isEmpty()) {
						setExcelDataCrsrdTrfvlm(crsrdTrfvlmListForDwell, sheet);
					}
				} else if (sheetIndex == 3) {
					// 주말 교차로 교통량
					trfvlmStatisticsDTO.setDywkDiv("주말");
					XSSFSheet sheet = workbook.getSheetAt(3);
					List<TrfvlmStatisticsDTO> crsrdTrfvlmListForNonDwell = trfIpcssCrsrdTrfvlmMapper.findAllCrsrdTrfvlmStatisticsList(trfvlmStatisticsDTO);
					
					if(!crsrdTrfvlmListForNonDwell.isEmpty()) {
						setExcelDataCrsrdTrfvlm(crsrdTrfvlmListForNonDwell, sheet);
					}
				} else if (sheetIndex == 4) {
					// 평일 가로구간 교통량
					List<TrfvlmStatisticsDTO> strsctTrfvlmListForDwell = new ArrayList<TrfvlmStatisticsDTO>();
					XSSFSheet sheet = workbook.getSheetAt(4);
					trfvlmStatisticsDTO.setDywkDiv("평일");
					strsctTrfvlmListForDwell = trfIpcssStrsctTrfvlmMapper.findAllStrsctTrfvlmStatisticsList(trfvlmStatisticsDTO);

					if (!strsctTrfvlmListForDwell.isEmpty()) {
						setExcelDataStrsctTrfvlm(strsctTrfvlmListForDwell, sheet);
					}
				} else if (sheetIndex == 5) {
					// 주말 가로구간 교통량
					List<TrfvlmStatisticsDTO> strsctTrfvlmListForNonDwell = new ArrayList<TrfvlmStatisticsDTO>();
					XSSFSheet sheet = workbook.getSheetAt(5);
					trfvlmStatisticsDTO.setDywkDiv("주말");
					strsctTrfvlmListForNonDwell = trfIpcssStrsctTrfvlmMapper.findAllStrsctTrfvlmStatisticsList(trfvlmStatisticsDTO);

					if (!strsctTrfvlmListForNonDwell.isEmpty()) {
						setExcelDataStrsctTrfvlm(strsctTrfvlmListForNonDwell, sheet);
					}
				} else if (sheetIndex == 6) {
					// 보행, 자전거, PM 교통량
					List<TrfIpcssEtcTrfvlm> etcTrfvlmList = new ArrayList<TrfIpcssEtcTrfvlm>(); // 교통 영향평가 기타 교통량
					String[] timeArray = new String[] { "07:00~07:15", "07:15~07:30", "07:30~07:45", "07:45~08:00",
							"08:00", "08:00~08:15", "08:15~08:30", "08:30~08:45", "08:45~09:00", "09:00", "17:00~17:15",
							"17:15~17:30", "17:30~17:45", "17:45~18:00", "18:00", "18:00~18:15", "18:15~18:30",
							"18:30~18:45", "18:45~19:00", "19:00" };
					List<String> timeSctrnList = Arrays.asList(timeArray);
					XSSFSheet sheet = workbook.getSheetAt(6);
					// 평일
					trfvlmStatisticsDTO.setDywkDiv("평일");
					List<TrfIpcssEtcTrfvlm> headerList = trfIpcssEtcTrfvlmMapper
							.findAllPointNmList(trfvlmStatisticsDTO);
					for (String timeSctrn : timeSctrnList) {
						TrfIpcssEtcTrfvlm etcDTO = new TrfIpcssEtcTrfvlm();
						etcDTO.setIpcssMngNo(trfvlmStatisticsDTO.getIpcssMngNo());
						etcDTO.setTimeSctnNm(timeSctrn);
						etcDTO.setDywkDiv(trfvlmStatisticsDTO.getDywkDiv());
						etcDTO.setIpcssResultList(trfIpcssEtcTrfvlmMapper.findOneEtcTrfvlmInfo(etcDTO));
						etcTrfvlmList.add(etcDTO);
					}

					if (!etcTrfvlmList.isEmpty()) {
						String exmnYmd = trfIpcssEtcTrfvlmMapper.findExmnYmd(trfvlmStatisticsDTO);
						setExcelDataEtc(headerList, etcTrfvlmList, sheet, "평일", exmnYmd);
					}

					headerList.clear();
					etcTrfvlmList.clear();
					// 주말
					trfvlmStatisticsDTO.setDywkDiv("주말");
					headerList = trfIpcssEtcTrfvlmMapper.findAllPointNmList(trfvlmStatisticsDTO);
					for (String timeSctrn : timeSctrnList) {
						TrfIpcssEtcTrfvlm etcDTO = new TrfIpcssEtcTrfvlm();
						etcDTO.setIpcssMngNo(trfvlmStatisticsDTO.getIpcssMngNo());
						etcDTO.setTimeSctnNm(timeSctrn);
						etcDTO.setDywkDiv(trfvlmStatisticsDTO.getDywkDiv());
						etcDTO.setIpcssResultList(trfIpcssEtcTrfvlmMapper.findOneEtcTrfvlmInfo(etcDTO));
						etcTrfvlmList.add(etcDTO);
					}
					if (!etcTrfvlmList.isEmpty()) {
						String exmnYmd = trfIpcssEtcTrfvlmMapper.findExmnYmd(trfvlmStatisticsDTO);
						setExcelDataEtc(headerList, etcTrfvlmList, sheet, "주말", exmnYmd);
					}
				} else if (sheetIndex == 7) {
					// 유사시설 활동인구 원단위
					List<TrfIpcssActPopltnBsunt> actPopltnBsuntList = new ArrayList<TrfIpcssActPopltnBsunt>();
					XSSFSheet sheet = workbook.getSheetAt(7);
					actPopltnBsuntList = trfIpcssActPopltnBsuntMapper.findAllActPopltnBsuntList(trfvlmStatisticsDTO);
					for (TrfIpcssActPopltnBsunt actPopltnBsunt : actPopltnBsuntList) {
						actPopltnBsunt.setIpcssResultList(
								trfIpcssActPopltnBsuntMapper.findOneActPopltnBsuntInfo(actPopltnBsunt));
					}

					if (!actPopltnBsuntList.isEmpty()) {
						setExcelDataActPopltnBsunt(actPopltnBsuntList, sheet, usgCdList, workbook);
					}
				} else if (sheetIndex == 8) {
					// 주거용도 교통수단 분담율 조사
					List<TrfIpcssMeanShareRt> meanShareRtList = new ArrayList<TrfIpcssMeanShareRt>(); // 교통 영향평가 수단 분담율
					XSSFSheet sheet = workbook.getSheetAt(8);
					trfvlmStatisticsDTO.setDwellYn("Y");
					meanShareRtList = trfIpcssMeanShareRtMapper.findAllMeanShareRtList(trfvlmStatisticsDTO);
					for (TrfIpcssMeanShareRt meanShareRt : meanShareRtList) {
						meanShareRt.setIpcssResultList(
								trfIpcssMeanShareRtMapper.findOneMeanShareRtInfoForDwell(meanShareRt));
					}

					if (!meanShareRtList.isEmpty()) {
						setExcelDataMeanShareRt(meanShareRtList, sheet, usgCdList, "dwell", workbook);
					}
				} else if (sheetIndex == 9) {
					// 비주거용도 교통수단 분담율 조사
					List<TrfIpcssMeanShareRt> meanShareRtList = new ArrayList<TrfIpcssMeanShareRt>(); // 교통 영향평가 수단 분담율
					XSSFSheet sheet = workbook.getSheetAt(9);
					trfvlmStatisticsDTO.setDwellYn("N");
					meanShareRtList = trfIpcssMeanShareRtMapper.findAllMeanShareRtList(trfvlmStatisticsDTO);
					for (TrfIpcssMeanShareRt meanShareRt : meanShareRtList) {
						meanShareRt.setIpcssResultList(
								trfIpcssMeanShareRtMapper.findOneMeanShareRtInfoForNonDwell(meanShareRt));
					}

					if (!meanShareRtList.isEmpty()) {
						setExcelDataMeanShareRt(meanShareRtList, sheet, usgCdList, "nonDwell", workbook);
					}
				} else if (sheetIndex == 10) {
					// 주거용도 재차인차인원 조사
					List<TrfIpcssNbopl> nboplList = new ArrayList<TrfIpcssNbopl>(); // 교통 영향평가 재차인원
					XSSFSheet sheet = workbook.getSheetAt(10);
					trfvlmStatisticsDTO.setDwellYn("Y");
					nboplList = trfIpcssNboplMapper.findAllNboplList(trfvlmStatisticsDTO);
					for (TrfIpcssNbopl nboplInfo : nboplList) {
						nboplInfo.setIpcssResultList(trfIpcssNboplMapper.findOneNboplInfoForDwell(nboplInfo));
					}
					if (!nboplList.isEmpty()) {
						setExcelDataNbopl(nboplList, sheet, usgCdList, "dwell", workbook);
					}
				} else if (sheetIndex == 11) {
					// 비주거용도 재차인차인원 조사
					List<TrfIpcssNbopl> nboplList = new ArrayList<TrfIpcssNbopl>(); // 교통 영향평가 재차인원
					XSSFSheet sheet = workbook.getSheetAt(11);
					trfvlmStatisticsDTO.setDwellYn("N");
					nboplList = trfIpcssNboplMapper.findAllNboplList(trfvlmStatisticsDTO);
					for (TrfIpcssNbopl nboplInfo : nboplList) {
						nboplInfo.setIpcssResultList(trfIpcssNboplMapper.findOneNboplInfoForNonDwell(nboplInfo));
					}

					if (!nboplList.isEmpty()) {
						setExcelDataNbopl(nboplList, sheet, usgCdList, "nonDwell", workbook);
					}
				} else if (sheetIndex == 12) {
					// 시간대별 유출입 통행분포 조사
					List<TimeInoutflExmnDTO> timeInoutflExmnList = new ArrayList<TimeInoutflExmnDTO>(); // 교통 영향평가 시간대
					XSSFSheet sheet = workbook.getSheetAt(12);
					Map<String, Object> inAndOutUsgCdNmMap = trfIpcssTimeInoutflExmnMapper
							.findOneUsgCdNmList(trfvlmStatisticsDTO);
					timeInoutflExmnList = trfIpcssTimeInoutflExmnMapper.findAllTimeInoutflExmnList(trfvlmStatisticsDTO);
					if (!timeInoutflExmnList.isEmpty()) {
						setExcelDataTimeInoutfl(timeInoutflExmnList, sheet, inAndOutUsgCdNmMap, workbook);
					}
				} else if (sheetIndex == 13) {
					// 주차발생 원단위 조사자료
					List<TrfIpcssParkngOccurBsunt> parkngOccurBsuntList = new ArrayList<TrfIpcssParkngOccurBsunt>();
					XSSFSheet sheet = workbook.getSheetAt(13);
					parkngOccurBsuntList = trfIpcssParkngOccurBsuntMapper
							.findAllParkngOccurBsuntList(trfvlmStatisticsDTO);
					for (TrfIpcssParkngOccurBsunt parkngOccurBsunt : parkngOccurBsuntList) {
						parkngOccurBsunt.setIpcssResultList(
								trfIpcssParkngOccurBsuntMapper.findOneParkngOccurBsuntInfo(parkngOccurBsunt));
					}

					if (!parkngOccurBsuntList.isEmpty()) {
						setExcelDataParkngOccurBsunt(parkngOccurBsuntList, sheet, usgCdList, workbook);
					}
				} else if (sheetIndex == 14) {
					// 시간대별 통행 분포비
					List<TimePassDistrbDTO> timePassDistrbList = new ArrayList<TimePassDistrbDTO>(); // 교통 영향평가 시간대별
					XSSFSheet sheet = workbook.getSheetAt(14);
					// 사업명, 사업용도 조회
					TrfIpcssTimePassDistrb trfIpcssTimePassDistrb = new TrfIpcssTimePassDistrb();
//					trfIpcssTimePassDistrb = trfIpcssTimePassDistrbMapper.findOneBizInfo(trfvlmStatisticsDTO);
					// 평일
					trfvlmStatisticsDTO.setDywkDiv("평일");
					Map<String, Object> usgCdNmMap = trfIpcssTimePassDistrbMapper
							.findOneUsgCdNmList(trfvlmStatisticsDTO);
					timePassDistrbList = trfIpcssTimePassDistrbMapper.findAllTimePassDistrbList(trfvlmStatisticsDTO);
					if (!timePassDistrbList.isEmpty()) {
						setExcelDataTimePassDistrb(timePassDistrbList, sheet, "평일", trfIpcssTimePassDistrb, usgCdNmMap, workbook, usgCdList);
					}
					usgCdList.clear();
					// 주말
					trfvlmStatisticsDTO.setDywkDiv("주말");
					usgCdNmMap = trfIpcssTimePassDistrbMapper.findOneUsgCdNmList(trfvlmStatisticsDTO);
					timePassDistrbList = trfIpcssTimePassDistrbMapper.findAllTimePassDistrbList(trfvlmStatisticsDTO);
					if (!timePassDistrbList.isEmpty()) {
						setExcelDataTimePassDistrb(timePassDistrbList, sheet, "주말", trfIpcssTimePassDistrb, usgCdNmMap, workbook, usgCdList);
					}
				}
			}

			workbook.write(resp.getOutputStream());
		} catch (FileNotFoundException e) {
			throw new  CommonException(ErrorCode.FILE_NOT_FOUND);
		} catch (IOException e) {
			throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL);
		} finally {
			try {
				if (workbook != null)
					workbook.close();
			} catch (IOException e) {
				throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL);
			}
		}
	}
	
	/**
	 * @Method Name : setExcelDataCrsrdTrfvlm
	 * @작성일 : 2023. 11. 27.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 교차로 교통량 데이터 저장
	 * @param : crsrdTrfvlmListForDwell
	 * @param sheet
	 * @return :
	 * @throws ParseException 
	 */
	private void setExcelDataCrsrdTrfvlm(List<TrfvlmStatisticsDTO> crsrdTrfvlmListForDwell, XSSFSheet sheet) throws ParseException {
		int rowIndex = 4;
		int cellIndex = 0;
		int headerDrictIndex = 3;
		int headerCellIndex = 5;
		int detailCellIndex = 5;
		
		String exmnYmd = BDDateFormatUtil.formatDate(crsrdTrfvlmListForDwell.get(0).getExmnYmd());
		sheet.getRow(2).createCell(4).setCellValue(exmnYmd);
		
		for(TrfvlmStatisticsDTO crsrdTrfvlmInfo : crsrdTrfvlmListForDwell) {
			if(rowIndex == 3 || rowIndex == 26 || rowIndex == 49 || rowIndex == 72 || rowIndex == 95
					|| rowIndex == 118 || rowIndex == 141 || rowIndex == 164 || rowIndex == 187 || rowIndex == 210 || rowIndex == 233
					|| rowIndex == 256 || rowIndex == 279 || rowIndex == 302 || rowIndex == 325 || rowIndex == 348) {
				
				rowIndex++;
			}
			
			if(rowIndex == 4 || rowIndex == 27 || rowIndex == 50 || rowIndex == 73 || rowIndex == 96
					|| rowIndex == 119 || rowIndex == 142 || rowIndex == 165 || rowIndex == 188 || rowIndex == 211 || rowIndex == 234
					|| rowIndex == 257 || rowIndex == 280 || rowIndex == 303 || rowIndex == 326 || rowIndex == 349) {
				CellStyle cellStyle = sheet.getRow(rowIndex).getCell(0).getCellStyle();
				sheet.getRow(rowIndex).createCell(0).setCellValue(crsrdTrfvlmInfo.getDataNo());
				sheet.getRow(rowIndex).getCell(0).setCellStyle(cellStyle);
				CellStyle cellStyle2 = sheet.getRow(rowIndex).getCell(1).getCellStyle();
				sheet.getRow(rowIndex).createCell(1).setCellValue(crsrdTrfvlmInfo.getName());
				sheet.getRow(rowIndex).getCell(1).setCellStyle(cellStyle2);
				
				sheet.getRow(rowIndex).createCell(2).setCellValue(crsrdTrfvlmInfo.getLat());
				sheet.getRow(rowIndex).getCell(2).setCellStyle(cellStyle2);
				sheet.getRow(rowIndex).createCell(3).setCellValue(crsrdTrfvlmInfo.getLon());
				sheet.getRow(rowIndex).getCell(3).setCellStyle(cellStyle2);
				rowIndex++;
				headerDrictIndex+=23;
			}
			
			if(rowIndex == 5 || rowIndex == 28 || rowIndex == 51 || rowIndex == 74 || rowIndex == 97
					|| rowIndex == 120 || rowIndex == 143 || rowIndex == 166 || rowIndex == 189 || rowIndex == 212 || rowIndex == 235
					|| rowIndex == 258 || rowIndex == 281 || rowIndex == 304 || rowIndex == 327 || rowIndex == 350) {
				rowIndex++;
			}
			// 데이터 저장
			for(TrfvlmStatistics dataList : crsrdTrfvlmInfo.getTimeTrfvlmResultList()) {
				List<TimeTrfvlmData> timeTrfvlmDataList = dataList.getTimeTrfvlmDataList();
				ObjectMapper mapper = new ObjectMapper();
				List<TimeTrfvlmData> list = mapper.convertValue(timeTrfvlmDataList, new TypeReference<List<TimeTrfvlmData>>(){});
				for(TimeTrfvlmData dataInfo : list) {
					CellStyle psgvhclTrfvlmStyle = sheet.getRow(rowIndex).getCell(headerCellIndex).getCellStyle();
					sheet.getRow(rowIndex).createCell(detailCellIndex).setCellValue(dataInfo.getPsgvhclTrfvlm());	
					sheet.getRow(rowIndex).getCell(detailCellIndex++).setCellStyle(psgvhclTrfvlmStyle);
					
					CellStyle busTrfvlmStyle = sheet.getRow(rowIndex).getCell(headerCellIndex).getCellStyle();
					sheet.getRow(rowIndex).createCell(detailCellIndex).setCellValue(dataInfo.getBusTrfvlm());
					sheet.getRow(rowIndex).getCell(detailCellIndex++).setCellStyle(busTrfvlmStyle);
					
					CellStyle busLrgszTrfvlmStyle = sheet.getRow(rowIndex).getCell(headerCellIndex).getCellStyle();
					sheet.getRow(rowIndex).createCell(detailCellIndex).setCellValue(dataInfo.getBusLrgszTrfvlm());		
					sheet.getRow(rowIndex).getCell(detailCellIndex++).setCellStyle(busLrgszTrfvlmStyle);
					
					CellStyle frghtSmlszTrfvlmStyle = sheet.getRow(rowIndex).getCell(headerCellIndex).getCellStyle();
					sheet.getRow(rowIndex).createCell(detailCellIndex).setCellValue(dataInfo.getFrghtSmlszTrfvlm());	
					sheet.getRow(rowIndex).getCell(detailCellIndex++).setCellStyle(frghtSmlszTrfvlmStyle);
					
					CellStyle frghtMdmszTrfvlmStyle = sheet.getRow(rowIndex).getCell(headerCellIndex).getCellStyle();
					sheet.getRow(rowIndex).createCell(detailCellIndex).setCellValue(dataInfo.getFrghtMdmszTrfvlm());	
					sheet.getRow(rowIndex).getCell(detailCellIndex++).setCellStyle(frghtMdmszTrfvlmStyle);
					
					CellStyle frghtLrgszTrfvlmStyle = sheet.getRow(rowIndex).getCell(headerCellIndex).getCellStyle();
					sheet.getRow(rowIndex).createCell(detailCellIndex).setCellValue(dataInfo.getFrghtLrgszTrfvlm());
					sheet.getRow(rowIndex).getCell(detailCellIndex++).setCellStyle(frghtLrgszTrfvlmStyle);
					
					CellStyle totalCntStyle = sheet.getRow(rowIndex).getCell(headerCellIndex).getCellStyle();
					sheet.getRow(rowIndex).createCell(detailCellIndex).setCellValue(dataInfo.getTotalCnt());
					sheet.getRow(rowIndex).getCell(detailCellIndex++).setCellStyle(totalCntStyle);
				}
				rowIndex++;
				detailCellIndex = 5;
			}
			
			cellIndex = 0;
		}
	}

	/**
	 * @Method Name : setExcelDataStrsctTrfvlm
	 * @작성일 : 2023. 11. 27.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 가로구간 교통량 데이터 저장
	 * @param : strsctTrfvlmListForDwell
	 * @param : sheet
	 * @return :
	 * @throws ParseException 
	 */
	private void setExcelDataStrsctTrfvlm(List<TrfvlmStatisticsDTO> strsctTrfvlmListForDwell, XSSFSheet sheet) throws ParseException {
		int headerRowIndex = 3;
		int headerCellIndex = 3;
		int headerDrictIndex = 3;
		int rowIndex = 3;
		int cellIndex = 0;
		int detailCellIndex = 3;
		
		String exmnYmd = BDDateFormatUtil.formatDate(strsctTrfvlmListForDwell.get(0).getExmnYmd());
		sheet.getRow(2).createCell(2).setCellValue(exmnYmd);
		
		for (TrfvlmStatisticsDTO strsctTrfvlm : strsctTrfvlmListForDwell) {
			
			// 헤더 파트 저장
			if(rowIndex == 3 || rowIndex == 26 || rowIndex == 49 || rowIndex == 72 || rowIndex == 95
					|| rowIndex == 118 || rowIndex == 141 || rowIndex == 164 || rowIndex == 187 || rowIndex == 210 || rowIndex == 233
					|| rowIndex == 256 || rowIndex == 279 || rowIndex == 302 || rowIndex == 325 || rowIndex == 348) {
				for (Object headerInfo : strsctTrfvlm.getHeaderList()) {
					CellStyle cellStyle = sheet.getRow(rowIndex).getCell(headerCellIndex).getCellStyle();
					sheet.getRow(rowIndex).createCell(headerCellIndex).setCellValue(String.valueOf(headerInfo));
					sheet.getRow(rowIndex).getCell(headerCellIndex).setCellStyle(cellStyle);
					headerCellIndex+=7;
				}
				headerCellIndex = 3;
				rowIndex++;
			}

			if(rowIndex == 4 || rowIndex == 27 || rowIndex == 50 || rowIndex == 73 || rowIndex == 96
					|| rowIndex == 119 || rowIndex == 142 || rowIndex == 165 || rowIndex == 188 || rowIndex == 211 || rowIndex == 234
					|| rowIndex == 257 || rowIndex == 280 || rowIndex == 303 || rowIndex == 326 || rowIndex == 349) {
				CellStyle cellStyle = sheet.getRow(rowIndex).getCell(0).getCellStyle();
				sheet.getRow(rowIndex).createCell(0).setCellValue(strsctTrfvlm.getDataNo());
				sheet.getRow(rowIndex).getCell(0).setCellStyle(cellStyle);
				CellStyle cellStyle2 = sheet.getRow(rowIndex).getCell(1).getCellStyle();
				sheet.getRow(rowIndex).createCell(1).setCellValue(strsctTrfvlm.getName());
				sheet.getRow(rowIndex).getCell(1).setCellStyle(cellStyle2);
				rowIndex++;
				headerDrictIndex+=23;
			}
			
			if(rowIndex == 5 || rowIndex == 28 || rowIndex == 51 || rowIndex == 74 || rowIndex == 97
					|| rowIndex == 120 || rowIndex == 143 || rowIndex == 166 || rowIndex == 189 || rowIndex == 212 || rowIndex == 235
					|| rowIndex == 258 || rowIndex == 281 || rowIndex == 304 || rowIndex == 327 || rowIndex == 350) {
				rowIndex++;
			}
			// 데이터 저장
			for(TrfvlmStatistics dataList : strsctTrfvlm.getTimeTrfvlmResultList()) {
				List<TimeTrfvlmData> timeTrfvlmDataList = dataList.getTimeTrfvlmDataList();
				ObjectMapper mapper = new ObjectMapper();
				List<TimeTrfvlmData> list = mapper.convertValue(timeTrfvlmDataList, new TypeReference<List<TimeTrfvlmData>>(){});
				for(TimeTrfvlmData dataInfo : list) {
					CellStyle psgvhclTrfvlmStyle = sheet.getRow(rowIndex).getCell(headerCellIndex).getCellStyle();
					sheet.getRow(rowIndex).createCell(detailCellIndex).setCellValue(dataInfo.getPsgvhclTrfvlm());	
					sheet.getRow(rowIndex).getCell(detailCellIndex++).setCellStyle(psgvhclTrfvlmStyle);
					
					CellStyle busTrfvlmStyle = sheet.getRow(rowIndex).getCell(headerCellIndex).getCellStyle();
					sheet.getRow(rowIndex).createCell(detailCellIndex).setCellValue(dataInfo.getBusTrfvlm());
					sheet.getRow(rowIndex).getCell(detailCellIndex++).setCellStyle(busTrfvlmStyle);
					
					CellStyle busLrgszTrfvlmStyle = sheet.getRow(rowIndex).getCell(headerCellIndex).getCellStyle();
					sheet.getRow(rowIndex).createCell(detailCellIndex).setCellValue(dataInfo.getBusLrgszTrfvlm());		
					sheet.getRow(rowIndex).getCell(detailCellIndex++).setCellStyle(busLrgszTrfvlmStyle);
					
					CellStyle frghtSmlszTrfvlmStyle = sheet.getRow(rowIndex).getCell(headerCellIndex).getCellStyle();
					sheet.getRow(rowIndex).createCell(detailCellIndex).setCellValue(dataInfo.getFrghtSmlszTrfvlm());	
					sheet.getRow(rowIndex).getCell(detailCellIndex++).setCellStyle(frghtSmlszTrfvlmStyle);
					
					CellStyle frghtMdmszTrfvlmStyle = sheet.getRow(rowIndex).getCell(headerCellIndex).getCellStyle();
					sheet.getRow(rowIndex).createCell(detailCellIndex).setCellValue(dataInfo.getFrghtMdmszTrfvlm());	
					sheet.getRow(rowIndex).getCell(detailCellIndex++).setCellStyle(frghtMdmszTrfvlmStyle);
					
					CellStyle frghtLrgszTrfvlmStyle = sheet.getRow(rowIndex).getCell(headerCellIndex).getCellStyle();
					sheet.getRow(rowIndex).createCell(detailCellIndex).setCellValue(dataInfo.getFrghtLrgszTrfvlm());
					sheet.getRow(rowIndex).getCell(detailCellIndex++).setCellStyle(frghtLrgszTrfvlmStyle);
					
					CellStyle totalCntStyle = sheet.getRow(rowIndex).getCell(headerCellIndex).getCellStyle();
					totalCntStyle.setBorderRight((short) 0x1);
					sheet.getRow(rowIndex).createCell(detailCellIndex).setCellValue(dataInfo.getTotalCnt());
					sheet.getRow(rowIndex).getCell(detailCellIndex++).setCellStyle(totalCntStyle);
				}
				rowIndex++;
				detailCellIndex = 3;
			}
			
			cellIndex = 0;
		}
	}

	/**
	 * @Method Name : setExcelDataEtc
	 * @작성일 : 2023. 11. 27.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 보행,자전거,PM 교통량 데이터 저장
	 * @param : headerList
	 * @param : etcTrfvlmList
	 * @param : exmnYmd
	 * @return :
	 * @throws ParseException 
	 */
	private void setExcelDataEtc(List<TrfIpcssEtcTrfvlm> headerList, List<TrfIpcssEtcTrfvlm> etcTrfvlmList,
			XSSFSheet sheet, String type, String exmnYmd) throws ParseException {
		int rowIndex = 0;
		if (type.equals("평일"))
			rowIndex = 5;
		if (type.equals("주말"))
			rowIndex = 31;
		int cellIndex = 1;

		if (type.equals("평일")) {
			int headerRowIndex = 3;
			int headerCellIndex = 1;
			
			sheet.getRow(2).createCell(1).setCellValue(BDDateFormatUtil.formatDate(exmnYmd));
			for (TrfIpcssEtcTrfvlm headerNm : headerList) {
				CellStyle pointNmStyle = sheet.getRow(headerRowIndex).getCell(headerCellIndex).getCellStyle();
				sheet.getRow(headerRowIndex).createCell(headerCellIndex).setCellValue(headerNm.getPointNm());
				sheet.getRow(headerRowIndex).getCell(headerCellIndex++).setCellStyle(pointNmStyle);
				headerCellIndex += 2;
			}
		} else if (type.equals("주말")) {
			int headerRowIndex = 29;
			int headerCellIndex = 1;
			sheet.getRow(28).createCell(1).setCellValue(BDDateFormatUtil.formatDate(exmnYmd));
			for (TrfIpcssEtcTrfvlm headerNm : headerList) {
				CellStyle pointNmStyle = sheet.getRow(headerRowIndex).getCell(headerCellIndex).getCellStyle();
				sheet.getRow(headerRowIndex).createCell(headerCellIndex).setCellValue(headerNm.getPointNm());
				sheet.getRow(headerRowIndex).getCell(headerCellIndex++).setCellStyle(pointNmStyle);
				headerCellIndex += 2;
			}
		}

		for (TrfIpcssEtcTrfvlm etcTrfvlmInfo : etcTrfvlmList) {
			for (TrfIpcssEtcTrfvlm etcTrfvlmDatail : etcTrfvlmInfo.getIpcssResultList()) {
				CellStyle pdstCntStyle = sheet.getRow(rowIndex).getCell(cellIndex).getCellStyle();
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(etcTrfvlmDatail.getPdstCnt());
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(pdstCntStyle);
				
				CellStyle bcyclCntStyle = sheet.getRow(rowIndex).getCell(cellIndex).getCellStyle();
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(etcTrfvlmDatail.getBcyclCnt());
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(bcyclCntStyle);
				
				CellStyle indvslMvmnEqpmntCntStyle = sheet.getRow(rowIndex).getCell(cellIndex).getCellStyle();
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(etcTrfvlmDatail.getIndvslMvmnEqpmntCnt());
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(indvslMvmnEqpmntCntStyle);
			}
			rowIndex++;
			cellIndex = 1;
		}
	}

	/**
	 * @Method Name : setExcelDataActPopltnBsunt
	 * @작성일 : 2023. 11. 27.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 유사시설 활동인구 원단위 데이터 저장
	 * @param : actPopltnBsuntList
	 * @param : sheet
	 * @param : usgCdList
	 * @param : workbook 
	 * @return :
	 */
	private void setExcelDataActPopltnBsunt(List<TrfIpcssActPopltnBsunt> actPopltnBsuntList, XSSFSheet sheet, List<MOpCode> usgCdList, XSSFWorkbook workbook) {
		XSSFCellStyle style1 = workbook.createCellStyle();
		style1.setBorderLeft(BorderStyle.THIN);
        style1.setBorderRight(BorderStyle.THIN);
        style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(226, 239, 218)));
        style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        XSSFCellStyle style2 = workbook.createCellStyle();
		style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 242, 204)));
        style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        XSSFCellStyle style3 = workbook.createCellStyle();
		style3.setBorderLeft(BorderStyle.THIN);
        style3.setBorderRight(BorderStyle.THIN);
        
		int rowIndex = 4;
		int cellIndex = 1;
		for (TrfIpcssActPopltnBsunt actPopltnBsunt : actPopltnBsuntList) {
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(actPopltnBsunt.getSmlfactNm());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(actPopltnBsunt.getSmlfactAddr());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(actPopltnBsunt.getScale());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);

			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(actPopltnBsunt.getTotfrar());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(actPopltnBsunt.getExmnDivNm());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(actPopltnBsunt.getExmnYmd());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(actPopltnBsunt.getExmnDocNm());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			for (TrfIpcssActPopltnBsunt actPopltnDetail : actPopltnBsunt.getIpcssResultList()) {
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(actPopltnDetail.getCdNm());
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style1);
				
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(actPopltnDetail.getUsgTotfrar());
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style2);
				
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(actPopltnDetail.getResdngBsunt());
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
				
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(actPopltnDetail.getVisitBsunt());
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
				
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(actPopltnDetail.getBsuntUnit());
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			}
			rowIndex++;
			cellIndex = 1;
		}
	}

	/**
	 * @Method Name : setExcelDataMeanShareRt
	 * @작성일 : 2023. 11. 27.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 교통수단 분담율 데이터 저장
	 * @param : meanShareRtList
	 * @param sheet
	 * @param usgCdList
	 * @param workbook 
	 * @return :
	 */
	private void setExcelDataMeanShareRt(List<TrfIpcssMeanShareRt> meanShareRtList, XSSFSheet sheet, List<MOpCode> usgCdList, String type, XSSFWorkbook workbook) {
		XSSFCellStyle style1 = workbook.createCellStyle();
		style1.setBorderLeft(BorderStyle.THIN);
        style1.setBorderRight(BorderStyle.THIN);
        style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(226, 239, 218)));
        style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        XSSFCellStyle style3 = workbook.createCellStyle();
		style3.setBorderLeft(BorderStyle.THIN);
        style3.setBorderRight(BorderStyle.THIN);
		
		int rowIndex = 0;
		if (type.equals("dwell"))
			rowIndex = 6;
		if (type.equals("nonDwell"))
			rowIndex = 5;
		int cellIndex = 1;
		for (TrfIpcssMeanShareRt meanShareRt : meanShareRtList) {
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(meanShareRt.getSmlfactNm());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(meanShareRt.getSmlfactAddr());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(meanShareRt.getScale());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(meanShareRt.getTotfrar());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(meanShareRt.getExmnDivNm());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(meanShareRt.getExmnYmd());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(meanShareRt.getExmnDocNm());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			for (int i = 0; i < meanShareRt.getIpcssResultList().size(); i++) {
				if (type.equals("dwell")) {
					if (i == 0 || i % 20 == 0) {
						sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(meanShareRt.getIpcssResultList().get(i).getCdNm());
						sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style1);
					}
				} else if (type.equals("nonDwell")) {
					if (i == 0 || i % 10 == 0) {
						sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(meanShareRt.getIpcssResultList().get(i).getCdNm());
						sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style1);
					}
				}
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(meanShareRt.getIpcssResultList().get(i).getShareRt());
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			}
			rowIndex++;
			cellIndex = 1;
		}
	}

	/**
	 * @Method Name : setExcelDataNbopl
	 * @작성일 : 2023. 11. 27.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 주거용도 재차인차인원 조사 데이터 저장
	 * @param : nboplList
	 * @param : sheet
	 * @param : usgCdList
	 * @param : workbook 
	 * @return :
	 */
	private void setExcelDataNbopl(List<TrfIpcssNbopl> nboplList, XSSFSheet sheet, List<MOpCode> usgCdList, String type, XSSFWorkbook workbook) {
		XSSFCellStyle style1 = workbook.createCellStyle();
		style1.setBorderLeft(BorderStyle.THIN);
        style1.setBorderRight(BorderStyle.THIN);
        style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(226, 239, 218) ));
        style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        XSSFCellStyle style3 = workbook.createCellStyle();
		style3.setBorderLeft(BorderStyle.THIN);
        style3.setBorderRight(BorderStyle.THIN);
        
		int rowIndex = 0;
		if (type.equals("dwell"))
			rowIndex = 6;
		if (type.equals("nonDwell"))
			rowIndex = 5;
		int cellIndex = 1;
		for (TrfIpcssNbopl nboplInfo : nboplList) {
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(nboplInfo.getSmlfactNm());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(nboplInfo.getSmlfactAddr());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(nboplInfo.getScale());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(nboplInfo.getTotfrar());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(nboplInfo.getExmnDivNm());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(nboplInfo.getExmnYmd());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(nboplInfo.getExmnDocNm());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			for (int i = 0; i < nboplInfo.getIpcssResultList().size(); i++) {
				if (type.equals("dwell")) {
					if (i == 0 || i % 8 == 0) {
						sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(nboplInfo.getIpcssResultList().get(i).getCdNm());
						sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style1);
					}
				} else if (type.equals("nonDwell")) {
					if (i == 0 || i % 4 == 0) {
						sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(nboplInfo.getIpcssResultList().get(i).getCdNm());
						sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style1);
					}
				}
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(nboplInfo.getIpcssResultList().get(i).getNboplCnt());
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			}
			rowIndex++;
			cellIndex = 1;
		}
	}

	/**
	 * @Method Name : setExcelDataTimeInoutfl
	 * @작성일 : 2023. 11. 27.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 시간대별 유출입 통행분포 조사
	 * @param : timeInoutflExmnList
	 * @param sheet
	 * @param inAndOutUsgCdNmMap
	 * @param workbook 
	 * @return :
	 */
	private void setExcelDataTimeInoutfl(List<TimeInoutflExmnDTO> timeInoutflExmnList, XSSFSheet sheet, Map<String, Object> inAndOutUsgCdNmMap, XSSFWorkbook workbook) {
		XSSFCellStyle style3 = workbook.createCellStyle();
		style3.setBorderLeft(BorderStyle.NONE);
        style3.setBorderRight(BorderStyle.NONE);
        style3.setBorderBottom(BorderStyle.NONE);
        style3.setBorderTop(BorderStyle.NONE);
        
        XSSFCellStyle style2 = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        style2.setDataFormat(format.getFormat("0%"));
        
        XSSFCellStyle style4 = workbook.createCellStyle();
        style4.setBorderLeft(BorderStyle.NONE);
        style4.setBorderRight(BorderStyle.THIN);
        style4.setBorderBottom(BorderStyle.NONE);
        style4.setBorderTop(BorderStyle.NONE);
        
        XSSFCellStyle style5 = workbook.createCellStyle();
        DataFormat format2 = workbook.createDataFormat();
        style5.setBorderRight(BorderStyle.THIN);
        style5.setDataFormat(format2.getFormat("0%"));
        
        XSSFCellStyle style6 = workbook.createCellStyle();
        DataFormat format3 = workbook.createDataFormat();
        style6.setBorderRight(BorderStyle.THICK);
        style6.setDataFormat(format3.getFormat("0%"));
        
		int rowIndex = 8;
		int cellIndex = 1;

		String[] usgNmArr = new String[] {};
		String usgNmStr = (String) inAndOutUsgCdNmMap.get("cdnmarr");
		usgNmArr = usgNmStr.split(",");
		List<String> usgNmList = new ArrayList<String>();
		usgNmList = Arrays.asList(usgNmArr);

		int headerCellIndex = 2;
		for (String usgNm : usgNmList) {
			sheet.getRow(4).createCell(headerCellIndex).setCellValue(usgNm);
			sheet.getRow(4).getCell(headerCellIndex).setCellStyle(style3);
			headerCellIndex += 12;
		}
		String formula= "";
		String newFormula = "";
		for (TimeInoutflExmnDTO timeInoutfl : timeInoutflExmnList) {
			for (int i = 0; i < timeInoutfl.getTrfvlmStatisticsList().size(); i++) {
				if (i > 0 && i % 2 != 0) {
					continue;
				}
				Double resdngInfl = timeInoutfl.getTrfvlmStatisticsList().get(i).getResdngInfl();
				Double resdngOutfl = timeInoutfl.getTrfvlmStatisticsList().get(i).getResdngOutfl();
				Double visitInfl = timeInoutfl.getTrfvlmStatisticsList().get(i).getVisitInfl();
				Double visitOutfl = timeInoutfl.getTrfvlmStatisticsList().get(i).getVisitOutfl();
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(resdngInfl);
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
				
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(resdngOutfl);
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style4);
				
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(visitInfl);
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
				
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(visitOutfl);
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style4);
				
				formula = sheet.getRow(rowIndex).getCell(cellIndex).getCellFormula();
				newFormula = "IFERROR(" + formula + ", 0)";
				sheet.getRow(rowIndex).createCell(cellIndex).setCellFormula(newFormula);
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
				
				formula = sheet.getRow(rowIndex).getCell(cellIndex).getCellFormula();
				newFormula = "IFERROR(" + formula + ", 0)";
				sheet.getRow(rowIndex).createCell(cellIndex).setCellFormula(newFormula);
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style4);
				
				formula = sheet.getRow(rowIndex).getCell(cellIndex).getCellFormula();
				newFormula = "IFERROR(" + formula + ", 0)";
				sheet.getRow(rowIndex).getCell(cellIndex).setCellFormula(newFormula);
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style2);
				
				formula = sheet.getRow(rowIndex).getCell(cellIndex).getCellFormula();
				newFormula = "IFERROR(" + formula + ", 0)";
				sheet.getRow(rowIndex).getCell(cellIndex).setCellFormula(newFormula);
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style5);
				
				formula = sheet.getRow(rowIndex).getCell(cellIndex).getCellFormula();
				newFormula = "IFERROR(" + formula + ", 0)";
				sheet.getRow(rowIndex).getCell(cellIndex).setCellFormula(newFormula);
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style2);
				
				formula = sheet.getRow(rowIndex).getCell(cellIndex).getCellFormula();
				newFormula = "IFERROR(" + formula + ", 0)";
				sheet.getRow(rowIndex).getCell(cellIndex).setCellFormula(newFormula);
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style5);
				
				formula = sheet.getRow(rowIndex).getCell(cellIndex).getCellFormula();
				newFormula = "IFERROR(" + formula + ", 0)";
				sheet.getRow(rowIndex).getCell(cellIndex).setCellFormula(newFormula);
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style2);
				
				formula = sheet.getRow(rowIndex).getCell(cellIndex).getCellFormula();
				newFormula = "IFERROR(" + formula + ", 0)";
				sheet.getRow(rowIndex).getCell(cellIndex).setCellFormula(newFormula);
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style6);
			}
			rowIndex++;
			cellIndex = 1;
		}
		
		// 합계 로우
		for(int i = 1; i < sheet.getRow(32).getPhysicalNumberOfCells(); i++) {
			formula = sheet.getRow(32).getCell(i).getCellFormula();
			newFormula = "IFERROR(" + formula + ", 0)";
			sheet.getRow(32).getCell(i).setCellFormula(newFormula);
		}
	}

	/**
	 * @Method Name : setExcelDataParkngOccurBsunt
	 * @작성일 : 2023. 11. 27.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 주차발생 원단위 조사자료 데이터 저장
	 * @param : trfIpcssParkngOccurBsuntList
	 * @param sheet
	 * @param usgCdList
	 * @param workbook 
	 * @return :
	 */
	private void setExcelDataParkngOccurBsunt(List<TrfIpcssParkngOccurBsunt> trfIpcssParkngOccurBsuntList, XSSFSheet sheet, List<MOpCode> usgCdList, XSSFWorkbook workbook) {
		XSSFCellStyle style1 = workbook.createCellStyle();
		style1.setBorderLeft(BorderStyle.THIN);
        style1.setBorderRight(BorderStyle.THIN);
        style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(226, 239, 218) ));
        style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        XSSFCellStyle style2 = workbook.createCellStyle();
		style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 242, 204) ));
        style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        XSSFCellStyle style3 = workbook.createCellStyle();
		style3.setBorderLeft(BorderStyle.THIN);
        style3.setBorderRight(BorderStyle.THIN);
		int rowIndex = 4;
		int cellIndex = 1;
		for (TrfIpcssParkngOccurBsunt trfIpcssParkngOccurBsunt : trfIpcssParkngOccurBsuntList) {
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(trfIpcssParkngOccurBsunt.getSmlfactNm());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(trfIpcssParkngOccurBsunt.getSmlfactAddr());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(trfIpcssParkngOccurBsunt.getScale());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(trfIpcssParkngOccurBsunt.getTotfrar());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(trfIpcssParkngOccurBsunt.getExmnDivNm());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(trfIpcssParkngOccurBsunt.getExmnYmd());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			
			sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(trfIpcssParkngOccurBsunt.getExmnDocNm());
			sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			for (TrfIpcssParkngOccurBsunt detailInfo : trfIpcssParkngOccurBsunt.getIpcssResultList()) {
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(ExcelUploadUtil.findUsgCdNm(detailInfo.getUsgCd(), usgCdList));
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style1);
				
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(detailInfo.getUsgTotfrar());
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style2);
				
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(detailInfo.getWkdayBsunt());
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
				
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(detailInfo.getWkendBsunt());
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
				
				sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(detailInfo.getBsuntUnit());
				sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
			}
			rowIndex++;
			cellIndex = 1;
		}
	}

	/**
	 * @Method Name : setExcelDataTimePassDistrb
	 * @작성일 : 2023. 11. 27.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 시간대별 통행 분포비
	 * @param : timePassDistrbList
	 * @param sheet
	 * @param type
	 * @param trfIpcssTimePassDistrb
	 * @param usgCdNmMap
	 * @param usgCdList 
	 * @return :
	 */
	private void setExcelDataTimePassDistrb(List<TimePassDistrbDTO> timePassDistrbList, XSSFSheet sheet, String type, TrfIpcssTimePassDistrb trfIpcssTimePassDistrb, Map<String, Object> usgCdNmMap, XSSFWorkbook workbook, List<MOpCode> usgCdList) {
		XSSFCellStyle style1 = workbook.createCellStyle();
		style1.setBorderLeft(BorderStyle.THIN);
		style1.setBorderRight(BorderStyle.NONE);
        style1.setBorderBottom(BorderStyle.NONE);
        style1.setBorderTop(BorderStyle.NONE);
		
		XSSFCellStyle style2 = workbook.createCellStyle();
		style2.setBorderLeft(BorderStyle.THIN);
		style2.setBorderRight(BorderStyle.THIN);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);
        
		XSSFCellStyle style3 = workbook.createCellStyle();
		style3.setBorderLeft(BorderStyle.NONE);
        style3.setBorderRight(BorderStyle.NONE);
        style3.setBorderBottom(BorderStyle.NONE);
        style3.setBorderTop(BorderStyle.NONE);
        
        XSSFCellStyle style4 = workbook.createCellStyle();
		style4.setBorderLeft(BorderStyle.NONE);
        style4.setBorderRight(BorderStyle.THIN);
        style4.setBorderBottom(BorderStyle.NONE);
        style4.setBorderTop(BorderStyle.NONE);
        
		XSSFCellStyle style5 = workbook.createCellStyle();
		style5.setBorderLeft(BorderStyle.THICK);
		style5.setBorderRight(BorderStyle.THIN);
        style5.setBorderBottom(BorderStyle.THIN);
        style5.setBorderTop(BorderStyle.NONE);
        XSSFFont boldFont = workbook.createFont();
        boldFont.setBold(true);
        style5.setFont(boldFont);
       
		int rowIndex = 0;
		if (type.equals("평일"))
			rowIndex = 8;
		if (type.equals("주말"))
			rowIndex = 39;
		int cellIndex = 1;
		int headerRowIndex = 0;
		int headerCellIndex = 0;
		String[] usgNmArr = new String[] {};
		String usgNmStr = (String) usgCdNmMap.get("cdnmarr");
		usgNmArr = usgNmStr.split(",");
		List<String> usgCdStrList = new ArrayList<String>();
		List<String> usgCdNmList = new ArrayList<String>();
		usgCdStrList = Arrays.asList(usgNmArr);
		String formula= "";
		for(String usgNm : usgCdStrList) {
			for(MOpCode usgCd : usgCdList) {
				if(usgNm.equals(usgCd.getCdNm())) {
					usgCdNmList.add(usgCd.getCdNm());
				}
			}
		}
		
		for (TimePassDistrbDTO timePassDistrb : timePassDistrbList) {
			for (int i = 0; i < timePassDistrb.getTrfvlmStatisticsList().size(); i++) {
				if (i >= 0 && i <= 3) {
					sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(timePassDistrb.getTrfvlmStatisticsList().get(i).getInflRt());
					if(i == 0) {
						sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style1);						
					}else {
						sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);						
					}
					
					sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(timePassDistrb.getTrfvlmStatisticsList().get(i).getOutflRt());
					sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
				} else if (i >= 4) {
					if (i % 2 == 0) {
						cellIndex++;
					}
					sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(timePassDistrb.getTrfvlmStatisticsList().get(i).getInflRt());
					sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
					
					sheet.getRow(rowIndex).createCell(cellIndex).setCellValue(timePassDistrb.getTrfvlmStatisticsList().get(i).getOutflRt());
					sheet.getRow(rowIndex).getCell(cellIndex++).setCellStyle(style3);
				}
			}
			rowIndex++;
			cellIndex = 1;
		}
		
		if (type.equals("평일")) {
			sheet.getRow(1).createCell(1).setCellValue(trfIpcssTimePassDistrb.getBizNm());
			sheet.getRow(1).getCell(1).setCellStyle(style2);
			
			sheet.getRow(2).createCell(1).setCellValue(trfIpcssTimePassDistrb.getBizUsg());
			sheet.getRow(2).getCell(1).setCellStyle(style2);
			headerCellIndex = 9;
			headerRowIndex = 7;
			for (String usgNm : usgCdNmList) {
				sheet.getRow(headerRowIndex).createCell(headerCellIndex).setCellValue(usgNm);
				sheet.getRow(headerRowIndex).getCell(headerCellIndex).setCellStyle(style5);
				headerCellIndex += 5;
			}
			
			for(int i = 1; i < sheet.getRow(32).getPhysicalNumberOfCells(); i++) {
				if(i == 9 || i == 14 || i == 19 || i == 24 || i == 29 || i == 34 || i == 39 || i == 44 
						|| i == 49 || i == 54 || i == 59 || i == 64 || i == 69 || i == 74 
						|| i == 79 || i == 84 || i == 89 || i == 94 || i == 99 || i == 104) {
					continue;
				}
				formula = sheet.getRow(32).getCell(i).getCellFormula();
				sheet.getRow(32).getCell(i).setCellFormula(formula);
				sheet.getRow(32).getCell(i).setCellStyle(style2);
			}
			
		} else if (type.equals("주말")) {
			headerCellIndex = 38;
			headerRowIndex = 7;
			for (String usgNm : usgCdNmList) {
				sheet.getRow(headerRowIndex).createCell(headerCellIndex).setCellValue(usgNm);
				sheet.getRow(headerRowIndex).getCell(headerCellIndex).setCellStyle(style5);
				headerCellIndex += 5;
			}
			for(int i = 1; i < sheet.getRow(63).getPhysicalNumberOfCells(); i++) {
				if(i == 9 || i == 14 || i == 19 || i == 24 || i == 29 || i == 34 || i == 39 || i == 44 
						|| i == 49 || i == 54 || i == 59 || i == 64 || i == 69 || i == 74 
						|| i == 79 || i == 84 || i == 89 || i == 94 || i == 99 || i == 104) {
					continue;
				}
				formula = sheet.getRow(63).getCell(i).getCellFormula();
				sheet.getRow(63).getCell(i).setCellFormula(formula);
				sheet.getRow(63).getCell(i).setCellStyle(style2);
			}
		}
	}
	
	/**
	 * @Method Name : downloadImpactReportPartExcel
	 * @작성일 : 2023. 11. 30.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 수정용 엑셀 다운로드
	 * @param : req
	 * @param resp
	 * @param type
	 * @param ipcssMngNo
	 * @return :
	 * @throws ParseException 
	 */
	public void downloadImpactReportPartExcel(HttpServletRequest req, HttpServletResponse resp, String type, String ipcssMngNo) throws ParseException {
		String fileNm = "";
		String sheetNm = "";
		switch (type) {
		case "crossroad_by_traffic_weekday":
			// 평일 교차로 교통량(default / TRF_IPCSS_CRSRD_TRFVLM)
			fileNm = "traffic_import_report_crsrd_weekday.xlsx";
			sheetNm = "_평일_교차로_교통량_조사";
			break;
		case "crossroad_by_traffic_weekend":
			// 주말 교차로 교통량(default / TRF_IPCSS_CRSRD_TRFVLM)
			fileNm = "traffic_import_report_crsrd_weekend.xlsx";
			sheetNm = "_주말_교차로_교통량_조사";
			break;
		case "section_by_traffic_weekday":
			// 평일 구간별 교통량(TRF_IPCSS_STRSCT_TRFVLM)
			fileNm = "traffic_import_report_strsct_weekday.xlsx";
			sheetNm = "_평일_구간별_교통량_조사";
			break;
		case "section_by_traffic_weekend":
			// 주말 구간별 교통량(TRF_IPCSS_STRSCT_TRFVLM)
			fileNm = "traffic_import_report_strsct_weekend.xlsx";
			sheetNm = "_주말_구간별_교통량_조사";
			break;
		case "mean_by_traffic":
			// 이용수단별(TRF_IPCSS_ETC_TRFVLM)
			fileNm = "traffic_import_report_etc.xlsx";
			sheetNm = "_이용수단별_교통량_조사";
			break;
		case "similar_facilities_by_floating_population":
			// 유사시설별 유동인구(TRF_IPCSS_ACT_POPLTN_BSUNT)
			fileNm = "traffic_import_report_act_popltn.xlsx";
			sheetNm = "_유사시설_활동인구_조사";
			break;
		case "use_by_traffic_mean_share_rate_dwell":
			// 주거용도 교통수단 분담률(TRF_IPCSS_MEAN_SHARE_RT)
			fileNm = "traffic_import_report_mean_share_rt_dwell.xlsx";
			sheetNm = "_주거용도_교통수단_분담률_조사";
			break;
		case "use_by_traffic_mean_share_rate_non_dwell":
			// 비주거용도 교통수단 분담률(TRF_IPCSS_MEAN_SHARE_RT)
			fileNm = "traffic_import_report_mean_share_rt_non_dwell.xlsx";
			sheetNm = "_비주거용도_교통수단_분담률_조사";
			break;
		case "use_by_nbopl_cnt_dwell":
			// 주거용도 재차인차인원(TRF_IPCSS_NBOPL)
			fileNm = "traffic_import_report_nbopl_dwell.xlsx";
			sheetNm = "_주거용도_재차인차인원_조사";
			break;
		case "use_by_nbopl_cnt_non_dwell":
			// 비주거용도 재차인차인원(TRF_IPCSS_NBOPL)
			fileNm = "traffic_import_report_nbopl_non_dwell.xlsx";
			sheetNm = "_비주거용도_재차인차인원_조사";
			break;
		case "parking_occurrence":
			// 주차발생(TRF_IPCSS_PARKNG_OCCUR_BSUNT)
			fileNm = "traffic_import_report_parkng_occur.xlsx";
			sheetNm = "_주차발생_원단위_조사";
			break;
		case "time_by_in_and_out_pass":
			// 시간대별 유출입 통행(TRF_IPCSS_TIME_INOUTFL_EXMN)
			fileNm = "traffic_import_report_time_inoutfl.xlsx";
			sheetNm = "_시간대별_유출입_통행_조사";
			break;
		case "time_by_pass_distribution":
			// 시간대별 통행 분포(TRF_IPCSS_TIME_PASS_DISTRB)
			fileNm = "traffic_import_report_time_pass_distrb.xlsx";
			sheetNm = "_시간대별_통행_분포_조사";
			break;
		}
		
		XSSFWorkbook workbook = null;
		try {
			TrfvlmStatisticsDTO trfvlmStatisticsDTO = new TrfvlmStatisticsDTO();
			trfvlmStatisticsDTO.setIpcssMngNo(ipcssMngNo);
			
			String filePath = trafficImpactReportSampleFileDownload + fileNm;
			ClassPathResource resource = new ClassPathResource(filePath);
			File file = new File(resource.getURL().getPath());

			TrfIpcssExmnBizInfo trfIpcssExmnBizInfo = new TrfIpcssExmnBizInfo(); // 교통 영향평가 조사 사업 정보
			trfIpcssExmnBizInfo = trfIpcssExmnBizInfoMapper.findOneExmnBizInfoByipcssMngNo(ipcssMngNo);
			
			fileNm = trfIpcssExmnBizInfo.getBizNm()+sheetNm+".xlsx";
			
			String encodedFilename = "attachment; filename*=" + "UTF-8" + "''" + URLEncoder.encode(fileNm, "UTF-8");
			encodedFilename = encodedFilename.replaceAll("\\+", "%20");
			resp.setContentType("ms-vnd/excel; charset=UTF-8");
			resp.setHeader("Content-Disposition", encodedFilename);

			workbook = new XSSFWorkbook(new FileInputStream(file));
			
			List<MOpCode> usgCdList = new ArrayList<MOpCode>();
			usgCdList = mOpCodeMapper.findAllCodeListByGrpCdId("USG_CD");
			XSSFSheet sheet = workbook.getSheetAt(0);
			switch (type) {
			case "crossroad_by_traffic_weekday":
				// 평일 교차로별 교통량(default / TRF_IPCSS_CRSRD_TRFVLM)
				trfvlmStatisticsDTO.setDywkDiv("평일");
				List<TrfvlmStatisticsDTO> crsrdTrfvlmListForDwell = trfIpcssCrsrdTrfvlmMapper.findAllCrsrdTrfvlmStatisticsList(trfvlmStatisticsDTO);
				
				if(!crsrdTrfvlmListForDwell.isEmpty()) {
					setExcelDataCrsrdTrfvlm(crsrdTrfvlmListForDwell, sheet);
				}
				break;
			case "crossroad_by_traffic_weekend":
				// 주말 교차로별 교통량(TRF_IPCSS_CRSRD_TRFVLM)
				trfvlmStatisticsDTO.setDywkDiv("주말");
				List<TrfvlmStatisticsDTO> crsrdTrfvlmListForNonDwell = trfIpcssCrsrdTrfvlmMapper.findAllCrsrdTrfvlmStatisticsList(trfvlmStatisticsDTO);
				
				if(!crsrdTrfvlmListForNonDwell.isEmpty()) {
					setExcelDataCrsrdTrfvlm(crsrdTrfvlmListForNonDwell, sheet);
				}
				break;
			case "section_by_traffic_weekday":
				// 평일 구간별 교통량(TRF_IPCSS_STRSCT_TRFVLM)
				List<TrfvlmStatisticsDTO> strsctTrfvlmListForDwell = new ArrayList<TrfvlmStatisticsDTO>();
				trfvlmStatisticsDTO.setDywkDiv("평일");
				strsctTrfvlmListForDwell = trfIpcssStrsctTrfvlmMapper.findAllStrsctTrfvlmStatisticsList(trfvlmStatisticsDTO);

				if (!strsctTrfvlmListForDwell.isEmpty()) {
					setExcelDataStrsctTrfvlm(strsctTrfvlmListForDwell, sheet);
				}
				break;
			case "section_by_traffic_weekend":
				// 주말 구간별 교통량(TRF_IPCSS_STRSCT_TRFVLM)
				List<TrfvlmStatisticsDTO> strsctTrfvlmListForNonDwell = new ArrayList<TrfvlmStatisticsDTO>();
				trfvlmStatisticsDTO.setDywkDiv("주말");
				strsctTrfvlmListForNonDwell = trfIpcssStrsctTrfvlmMapper.findAllStrsctTrfvlmStatisticsList(trfvlmStatisticsDTO);

				if (!strsctTrfvlmListForNonDwell.isEmpty()) {
					setExcelDataStrsctTrfvlm(strsctTrfvlmListForNonDwell, sheet);
				}
				break;
			case "mean_by_traffic":
				// 이용수단별(TRF_IPCSS_ETC_TRFVLM)
				List<TrfIpcssEtcTrfvlm> etcTrfvlmList = new ArrayList<TrfIpcssEtcTrfvlm>(); // 교통 영향평가 기타 교통량
				String[] timeArray = new String[] { "07:00~07:15", "07:15~07:30", "07:30~07:45", "07:45~08:00",
						"08:00", "08:00~08:15", "08:15~08:30", "08:30~08:45", "08:45~09:00", "09:00", "17:00~17:15",
						"17:15~17:30", "17:30~17:45", "17:45~18:00", "18:00", "18:00~18:15", "18:15~18:30",
						"18:30~18:45", "18:45~19:00", "19:00" };
				List<String> timeSctrnList = Arrays.asList(timeArray);
				// 평일
				trfvlmStatisticsDTO.setDywkDiv("평일");
				List<TrfIpcssEtcTrfvlm> headerList = trfIpcssEtcTrfvlmMapper.findAllPointNmList(trfvlmStatisticsDTO);
				for (String timeSctrn : timeSctrnList) {
					TrfIpcssEtcTrfvlm etcDTO = new TrfIpcssEtcTrfvlm();
					etcDTO.setIpcssMngNo(trfvlmStatisticsDTO.getIpcssMngNo());
					etcDTO.setTimeSctnNm(timeSctrn);
					etcDTO.setDywkDiv(trfvlmStatisticsDTO.getDywkDiv());
					etcDTO.setIpcssResultList(trfIpcssEtcTrfvlmMapper.findOneEtcTrfvlmInfo(etcDTO));
					etcTrfvlmList.add(etcDTO);
				}

				if (!etcTrfvlmList.isEmpty()) {
					String exmnYmd = trfIpcssEtcTrfvlmMapper.findExmnYmd(trfvlmStatisticsDTO);
					setExcelDataEtc(headerList, etcTrfvlmList, sheet, "평일", exmnYmd);
				}

				headerList.clear();
				etcTrfvlmList.clear();
				// 주말
				trfvlmStatisticsDTO.setDywkDiv("주말");
				headerList = trfIpcssEtcTrfvlmMapper.findAllPointNmList(trfvlmStatisticsDTO);
				for (String timeSctrn : timeSctrnList) {
					TrfIpcssEtcTrfvlm etcDTO = new TrfIpcssEtcTrfvlm();
					etcDTO.setIpcssMngNo(trfvlmStatisticsDTO.getIpcssMngNo());
					etcDTO.setTimeSctnNm(timeSctrn);
					etcDTO.setDywkDiv(trfvlmStatisticsDTO.getDywkDiv());
					etcDTO.setIpcssResultList(trfIpcssEtcTrfvlmMapper.findOneEtcTrfvlmInfo(etcDTO));
					etcTrfvlmList.add(etcDTO);
				}
				if (!etcTrfvlmList.isEmpty()) {
					String exmnYmd = trfIpcssEtcTrfvlmMapper.findExmnYmd(trfvlmStatisticsDTO);
					setExcelDataEtc(headerList, etcTrfvlmList, sheet, "주말", exmnYmd);
				}
				break;
			case "similar_facilities_by_floating_population":
				// 유사시설별 유동인구(TRF_IPCSS_ACT_POPLTN_BSUNT)
				List<TrfIpcssActPopltnBsunt> actPopltnBsuntList = new ArrayList<TrfIpcssActPopltnBsunt>();
				actPopltnBsuntList = trfIpcssActPopltnBsuntMapper.findAllActPopltnBsuntList(trfvlmStatisticsDTO);
				for (TrfIpcssActPopltnBsunt actPopltnBsunt : actPopltnBsuntList) {
					actPopltnBsunt.setIpcssResultList(
							trfIpcssActPopltnBsuntMapper.findOneActPopltnBsuntInfo(actPopltnBsunt));
				}
				if (!actPopltnBsuntList.isEmpty()) {
					setExcelDataActPopltnBsunt(actPopltnBsuntList, sheet, usgCdList, workbook);
				}
				break;
			case "use_by_traffic_mean_share_rate_dwell":
				// 주거용도 교통수단 분담률(TRF_IPCSS_MEAN_SHARE_RT)
				List<TrfIpcssMeanShareRt> meanShareRtListForDwell = new ArrayList<TrfIpcssMeanShareRt>(); // 교통 영향평가 수단 분담율
				trfvlmStatisticsDTO.setDwellYn("Y");
				meanShareRtListForDwell = trfIpcssMeanShareRtMapper.findAllMeanShareRtList(trfvlmStatisticsDTO);
				for (TrfIpcssMeanShareRt meanShareRt : meanShareRtListForDwell) {
					meanShareRt.setIpcssResultList(
							trfIpcssMeanShareRtMapper.findOneMeanShareRtInfoForDwell(meanShareRt));
				}
				if (!meanShareRtListForDwell.isEmpty()) {
					setExcelDataMeanShareRt(meanShareRtListForDwell, sheet, usgCdList, "dwell", workbook);
				}
				break;
			case "use_by_traffic_mean_share_rate_non_dwell":
				// 비주거용도 교통수단 분담률(TRF_IPCSS_MEAN_SHARE_RT)
				List<TrfIpcssMeanShareRt> meanShareRtListForNonDwell = new ArrayList<TrfIpcssMeanShareRt>();
				trfvlmStatisticsDTO.setDwellYn("N");
				meanShareRtListForNonDwell = trfIpcssMeanShareRtMapper.findAllMeanShareRtList(trfvlmStatisticsDTO);
				for (TrfIpcssMeanShareRt meanShareRt : meanShareRtListForNonDwell) {
					meanShareRt.setIpcssResultList(
							trfIpcssMeanShareRtMapper.findOneMeanShareRtInfoForNonDwell(meanShareRt));
				}
				if (!meanShareRtListForNonDwell.isEmpty()) {
					setExcelDataMeanShareRt(meanShareRtListForNonDwell, sheet, usgCdList, "nonDwell", workbook);
				}
				break;
			case "use_by_nbopl_cnt_dwell":
				// 주거용도 재차인차인원(TRF_IPCSS_NBOPL)
				List<TrfIpcssNbopl> nboplListForDwell = new ArrayList<TrfIpcssNbopl>();
				trfvlmStatisticsDTO.setDwellYn("Y");
				nboplListForDwell = trfIpcssNboplMapper.findAllNboplList(trfvlmStatisticsDTO);
				for (TrfIpcssNbopl nboplInfo : nboplListForDwell) {
					nboplInfo.setIpcssResultList(trfIpcssNboplMapper.findOneNboplInfoForDwell(nboplInfo));
				}
				if (!nboplListForDwell.isEmpty()) {
					setExcelDataNbopl(nboplListForDwell, sheet, usgCdList, "dwell", workbook);
				}
				break;
			case "use_by_nbopl_cnt_non_dwell":
				// 비주거용도 재차인차인원(TRF_IPCSS_NBOPL)
				List<TrfIpcssNbopl> nboplListForNonDwell = new ArrayList<TrfIpcssNbopl>();
				trfvlmStatisticsDTO.setDwellYn("N");
				nboplListForNonDwell = trfIpcssNboplMapper.findAllNboplList(trfvlmStatisticsDTO);
				for (TrfIpcssNbopl nboplInfo : nboplListForNonDwell) {
					nboplInfo.setIpcssResultList(trfIpcssNboplMapper.findOneNboplInfoForNonDwell(nboplInfo));
				}

				if (!nboplListForNonDwell.isEmpty()) {
					setExcelDataNbopl(nboplListForNonDwell, sheet, usgCdList, "nonDwell", workbook);
				}
				break;
			case "parking_occurrence":
				// 주차발생(TRF_IPCSS_PARKNG_OCCUR_BSUNT)
				List<TrfIpcssParkngOccurBsunt> parkngOccurBsuntList = new ArrayList<TrfIpcssParkngOccurBsunt>();
				parkngOccurBsuntList = trfIpcssParkngOccurBsuntMapper
						.findAllParkngOccurBsuntList(trfvlmStatisticsDTO);
				for (TrfIpcssParkngOccurBsunt parkngOccurBsunt : parkngOccurBsuntList) {
					parkngOccurBsunt.setIpcssResultList(
							trfIpcssParkngOccurBsuntMapper.findOneParkngOccurBsuntInfo(parkngOccurBsunt));
				}
				if (!parkngOccurBsuntList.isEmpty()) {
					setExcelDataParkngOccurBsunt(parkngOccurBsuntList, sheet, usgCdList, workbook);
				}
				break;
			case "time_by_in_and_out_pass":
				// 시간대별 유출입 통행(TRF_IPCSS_TIME_INOUTFL_EXMN)
				List<TimeInoutflExmnDTO> timeInoutflExmnList = new ArrayList<TimeInoutflExmnDTO>();
				Map<String, Object> inAndOutUsgCdNmMap = trfIpcssTimeInoutflExmnMapper
						.findOneUsgCdNmList(trfvlmStatisticsDTO);
				timeInoutflExmnList = trfIpcssTimeInoutflExmnMapper.findAllTimeInoutflExmnList(trfvlmStatisticsDTO);
				if (!timeInoutflExmnList.isEmpty()) {
					setExcelDataTimeInoutfl(timeInoutflExmnList, sheet, inAndOutUsgCdNmMap, workbook);
				}
				break;
			case "time_by_pass_distribution":
				// 시간대별 통행 분포(TRF_IPCSS_TIME_PASS_DISTRB)
				List<TimePassDistrbDTO> timePassDistrbList = new ArrayList<TimePassDistrbDTO>();
				// 사업명, 사업용도 조회
				TrfIpcssTimePassDistrb trfIpcssTimePassDistrb = new TrfIpcssTimePassDistrb();
				trfIpcssTimePassDistrb.setBizNm(trfIpcssExmnBizInfo.getBizNm());
				trfIpcssTimePassDistrb.setBizUsg(trfIpcssExmnBizInfo.getBizUsg());
//				trfIpcssTimePassDistrb = trfIpcssTimePassDistrbMapper.findOneBizInfo(trfvlmStatisticsDTO);
				// 평일
				trfvlmStatisticsDTO.setDywkDiv("평일");
				Map<String, Object> usgCdNmMap = trfIpcssTimePassDistrbMapper.findOneUsgCdNmList(trfvlmStatisticsDTO);
				timePassDistrbList = trfIpcssTimePassDistrbMapper.findAllTimePassDistrbList(trfvlmStatisticsDTO);
				if (!timePassDistrbList.isEmpty()) {
					setExcelDataTimePassDistrb(timePassDistrbList, sheet, "평일", trfIpcssTimePassDistrb, usgCdNmMap, workbook, usgCdList);
				}
				usgCdList.clear();
				// 주말
				trfvlmStatisticsDTO.setDywkDiv("주말");
				usgCdNmMap = trfIpcssTimePassDistrbMapper.findOneUsgCdNmList(trfvlmStatisticsDTO);
				timePassDistrbList = trfIpcssTimePassDistrbMapper.findAllTimePassDistrbList(trfvlmStatisticsDTO);
				if (!timePassDistrbList.isEmpty()) {
					setExcelDataTimePassDistrb(timePassDistrbList, sheet, "주말", trfIpcssTimePassDistrb, usgCdNmMap, workbook, usgCdList);
				}
				break;
			}
			workbook.write(resp.getOutputStream());
		} catch (FileNotFoundException e) {
			throw new CommonException(ErrorCode.FILE_NOT_FOUND);
		} catch (IOException e) {
			throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL);
		} finally {
			try {
				if (workbook != null)
					workbook.close();
			} catch (IOException e) {
				throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL);
			}
		}
	}
}

