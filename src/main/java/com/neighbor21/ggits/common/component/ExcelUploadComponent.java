package com.neighbor21.ggits.common.component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.neighbor21.ggits.common.dto.TrfvlmStatisticsDTO;
import com.neighbor21.ggits.common.entity.MOpCode;
import com.neighbor21.ggits.common.entity.TrfIpcssActPopltnBsunt;
import com.neighbor21.ggits.common.entity.TrfIpcssCrsrdTrfvlm;
import com.neighbor21.ggits.common.entity.TrfIpcssEtcTrfvlm;
import com.neighbor21.ggits.common.entity.TrfIpcssExmnBizInfo;
import com.neighbor21.ggits.common.entity.TrfIpcssMeanShareRt;
import com.neighbor21.ggits.common.entity.TrfIpcssNbopl;
import com.neighbor21.ggits.common.entity.TrfIpcssParkngOccurBsunt;
import com.neighbor21.ggits.common.entity.TrfIpcssStrsctTrfvlm;
import com.neighbor21.ggits.common.entity.TrfIpcssTimeInoutflExmn;
import com.neighbor21.ggits.common.entity.TrfIpcssTimePassDistrb;
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
import com.neighbor21.ggits.common.mapper.AdsiRseSttsInfoMapper;
import com.neighbor21.ggits.common.mapper.AdsiSmcrsrdCameraSttsInfoMapper;
import com.neighbor21.ggits.common.mapper.AdsiVdsSttsInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MrtAccntLogMapper;
import com.neighbor21.ggits.common.mapper.MrtIpcssAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtRoadAccntAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtTrfFcltsSttsAnlsMapper;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
import com.neighbor21.ggits.common.util.ExcelUploadUtil;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;
@Component
public class ExcelUploadComponent {
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
	
	/**
	 * @Method Name : uploadTrafficImpactReport
	 * @작성일 : 2023. 10. 19.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 신규 등록
	 * @param : file (excel 파일)
	 * @return
	 * @throws IOException
	 * @throws ParseException 
	 */
	@Transactional
	public void uploadTrafficImpactReport(MultipartFile file) throws IOException, ParseException {
		XSSFWorkbook workbook = null;
		String errorSheet = "";
		int errorRow = 0;
		try {
			workbook = new XSSFWorkbook(file.getInputStream());

			// 용도 코드 리스트
			List<MOpCode> usgCdList = new ArrayList<MOpCode>();
			usgCdList = mOpCodeMapper.findAllCodeListByGrpCdId("USG_CD");

			// 교통 수단 리스트
			List<MOpCode> trfUseCdList = new ArrayList<MOpCode>();
			trfUseCdList = mOpCodeMapper.findAllCodeListByGrpCdId("TRF_USE_CD");

			// 교통 사용 리스트
			List<MOpCode> trfMeanVhcclsList = new ArrayList<MOpCode>();
			trfMeanVhcclsList = mOpCodeMapper.findAllCodeListByGrpCdId("TRF_MEAN_VHCCLS");

			// 시트 수 확인
			int sheetSize = workbook.getNumberOfSheets();

			// 데이터 세팅
			TrfIpcssExmnBizInfo trfIpcssExmnBizInfo = new TrfIpcssExmnBizInfo(); // 교통 영향평가 조사 사업 정보

			List<TrfIpcssCrsrdTrfvlm> crsrdTrfvlmList = new ArrayList<TrfIpcssCrsrdTrfvlm>(); // 교통 영향평가 교차로 교통량
			List<TrfIpcssStrsctTrfvlm> strsctTrfvlmList = new ArrayList<TrfIpcssStrsctTrfvlm>(); // 교통 영향평가 가로구간 교통량
			List<TrfIpcssEtcTrfvlm> etcTrfvlmList = new ArrayList<TrfIpcssEtcTrfvlm>(); // 교통 영향평가 기타 교통량
			List<TrfIpcssActPopltnBsunt> actPopltnBsuntList = new ArrayList<TrfIpcssActPopltnBsunt>(); // 교통 영향평가 활동 인구 원단위
			List<TrfIpcssMeanShareRt> meanShareRtList = new ArrayList<TrfIpcssMeanShareRt>(); // 교통 영향평가 수단 분담율
			List<TrfIpcssNbopl> nboplList = new ArrayList<TrfIpcssNbopl>(); // 교통 영향평가 재차인원
			List<TrfIpcssTimeInoutflExmn> timeInoutflExmnList = new ArrayList<TrfIpcssTimeInoutflExmn>(); // 교통 영향평가 시간대 유출입 조사
			List<TrfIpcssParkngOccurBsunt> parkngOccurBsuntList = new ArrayList<TrfIpcssParkngOccurBsunt>();// 교통 영향평가 주차 발생 원단위
			List<TrfIpcssTimePassDistrb> timePassDistrbList = new ArrayList<TrfIpcssTimePassDistrb>(); // 교통 영향평가 시간대별 통행분포
			String exmnYmd = "";
			
			if (sheetSize > 0) {
				// 시트반복
				for (int sheetIndex = 0; sheetIndex < sheetSize; sheetIndex++) {
					// 필독 시트 제외
					if (sheetIndex == 0)
						continue;

					XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
					int sheetRowSize = sheet.getPhysicalNumberOfRows();

					if (sheetIndex == 1) {
						errorSheet = "교통량 조사 개요";
						// 교통량 조사 개요 시트
						for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
							errorRow = rowIndex;
							XSSFRow row = sheet.getRow(rowIndex);

							if (row == null)
								continue;

							int cells = row.getPhysicalNumberOfCells();

							// 공통 정보 세팅
							for (int cellIndex = 0; cellIndex <= cells; cellIndex++) {
								XSSFCell cell = row.getCell(cellIndex);
								String exmnYy = getCellValue(cell);
								if (rowIndex == 2 && cellIndex == 2) {
									trfIpcssExmnBizInfo.setExmnYy(exmnYy.substring(0, 4));
								}
								if (rowIndex == 2 && cellIndex == 4) {
									trfIpcssExmnBizInfo.setEvalCoNm(getCellValue(cell));
								}
								if (rowIndex == 2 && cellIndex == 6) {
									trfIpcssExmnBizInfo.setEvalPicNm(getCellValue(cell));
								}
								if (rowIndex == 3 && cellIndex == 2) {
									trfIpcssExmnBizInfo.setBizNm(getCellValue(cell));
								}
								if (rowIndex == 4 && cellIndex == 2) {
									trfIpcssExmnBizInfo.setBizUsg(getCellValue(cell));
								}
								if (rowIndex == 5 && cellIndex == 2) {
									trfIpcssExmnBizInfo.setExmnDd(getCellValue(cell));
									exmnYmd = trfIpcssExmnBizInfo.getExmnDd();
								}
							}
						}
					} else if (sheetIndex == 2 || sheetIndex == 3) {
						if(sheetIndex == 2) {
							errorSheet = "평일 교차로 교통량";
						}else {
							errorSheet = "주말 교차로 교통량";
						}
						// 평일 교차로 교통량, 주말 교차로 교통량(2,3)
						TrfIpcssCrsrdTrfvlm trfIpcssCrsrdTrfvlm = new TrfIpcssCrsrdTrfvlm();
						String exmnYmdStr = "";
						int crsrdRowNum = 4;
						int startHeaderRow = 3;
						int endHeaderRow = 5;
						int timeSctrnNum = 0;
						for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
							errorRow = rowIndex;
							XSSFRow row = sheet.getRow(rowIndex);
							if (row == null)
								continue;
							
							if(rowIndex == 2) {
								exmnYmdStr = getCellValue(row.getCell(4));
							}
							
							if (crsrdRowNum == rowIndex) {
								// 교차로명
								trfIpcssCrsrdTrfvlm.setCrsrdNo(getCellValue(row.getCell(0)));
								trfIpcssCrsrdTrfvlm.setCrsrdNm(getCellValue(row.getCell(1)));
								
								// 위도 
								String latStr = getCellValue(row.getCell(2));
								if(!GgitsCommonUtils.isNull(latStr) && GgitsCommonUtils.isDouble(latStr)) {
									trfIpcssCrsrdTrfvlm.setPointLat(Double.parseDouble(latStr));									
								}
								// 경도
								String lonStr = getCellValue(row.getCell(3));
								if(!GgitsCommonUtils.isNull(lonStr) && GgitsCommonUtils.isDouble(lonStr)) {
									trfIpcssCrsrdTrfvlm.setPointLon(Double.parseDouble(lonStr));									
								}
								crsrdRowNum += 23;
							}
							
							

							// 불필요 로우 제거(header 로우)
							if (startHeaderRow <= rowIndex && endHeaderRow >= rowIndex) {
								startHeaderRow += 23;
								endHeaderRow += 23;
								continue;
							}

							String timeSctrnNm = getCellValue(row.getCell(4));
							if (GgitsCommonUtils.chkContainString(timeSctrnNm)
									&& !ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(0)))) {
								// 합계일 경우
								timeSctrnNum++;

								if (timeSctrnNum == 4) {
									timeSctrnNum = 0;
								}
							}

							// row 데이터를 넘겨서 리스트객체 반환
							List<TrfIpcssCrsrdTrfvlm> dataList = new ArrayList<TrfIpcssCrsrdTrfvlm>();
							dataList = getCrsrdTrfvlmDataList(rowIndex, row, trfIpcssCrsrdTrfvlm, timeSctrnNum,
									sheetIndex, exmnYmdStr);

							// 전체 데이터 리스트에 추가
							crsrdTrfvlmList = Stream.concat(crsrdTrfvlmList.stream(), dataList.stream())
									.collect(Collectors.toList());
						}
					} else if (sheetIndex == 4 || sheetIndex == 5) {
						// 평일 가로구간 교통량, 주말 가로구간 교통량(4,5)
						if(sheetIndex == 4) {
							errorSheet = "평일 가로구간 교통량";
						}else {
							errorSheet = "주말 가로구간 교통량";
						}
						String strsctNm = "";
						String forword = "";
						String backword = "";
						String exmnYmdStr = "";
						List<String> directList = new ArrayList<String>();
						int directTermNum = 3;
						int strsctTermNum = 4;
						int skipTermNum = 5;
						int timeSctrnNum = 0;
						String strsctNo = "";
						for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
							errorRow = rowIndex;
							XSSFRow row = sheet.getRow(rowIndex);
							if (row == null)
								continue;
							
							if(rowIndex == 2) {
								exmnYmdStr = getCellValue(row.getCell(2));
							}
							
							// 가로 구간 방향명
							if (rowIndex == 3 || directTermNum == rowIndex) {
								directList = getRowValueToList(row, true);
								forword = directList.get(3);
								backword = directList.get(4);
								directTermNum += 23;
								continue;
							}
							// 가로 구간 명칭
							if (rowIndex == 4 || strsctTermNum == rowIndex) {
								List<String> strsctNmList = getRowValueToList(row, false);
								strsctNm = strsctNmList.get(1);
								strsctNo = getCellValue(row.getCell(0));
								strsctTermNum += 23;
								continue;
							}
							// 불필요 로우
							if (rowIndex == 5 || skipTermNum == rowIndex) {
								skipTermNum += 23;
								continue;
							}
							// row 데이터를 넘겨서 리스트객체 반환
							List<TrfIpcssStrsctTrfvlm> dataList = new ArrayList<TrfIpcssStrsctTrfvlm>();
							dataList = getStrsctTrfvlmDataList(rowIndex, row, strsctNm, forword, backword, timeSctrnNum,
									sheetIndex, strsctNo, exmnYmdStr);

							String timeSctrnNm = getCellValue(row.getCell(2));
							if (GgitsCommonUtils.chkContainString(timeSctrnNm)
									&& !ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(0)))) {
								// 합계일 경우
								timeSctrnNum++;
								if (timeSctrnNum == 4) {
									timeSctrnNum = 0;
								}
							}

							// 전체 데이터 리스트에 추가
							strsctTrfvlmList = Stream.concat(strsctTrfvlmList.stream(), dataList.stream())
									.collect(Collectors.toList());
						}
					} else if (sheetIndex == 6) {
						// 보행, 자전거, PM 교통량(6)
						errorSheet = "보행, 자전거, PM 교통량";
						String exmnYmdStr = "";
						String dywkDiv = "";
						List<String> pointNmList = new ArrayList<String>();
						for (int rowIndex = 0; rowIndex <= sheetRowSize; rowIndex++) {
							errorRow = rowIndex;
							XSSFRow row = sheet.getRow(rowIndex);
							if (row == null)
								continue;

							// 요일 구분
							if (rowIndex == 0 || rowIndex == 26) {
								dywkDiv = getCellValue(row.getCell(0));
							}

							if (rowIndex == 2 || rowIndex == 28) {
								exmnYmdStr = getCellValue(row.getCell(1));
								continue;
							}
							// 지점 로우
							if (rowIndex == 3 || rowIndex == 29) {
								pointNmList = getRowValueToList(row, false);
								continue;
							}

							// row 데이터를 넘겨서 리스트객체 반환
							List<TrfIpcssEtcTrfvlm> dataList = new ArrayList<TrfIpcssEtcTrfvlm>();
							if ((rowIndex >= 5 && rowIndex <= 24) || (rowIndex >= 31 && rowIndex <= 50)) {
								dataList = getEtcTrfvlmDataList(rowIndex, row, exmnYmdStr, pointNmList, dywkDiv);
							}

							// 전체 데이터 리스트에 추가
							etcTrfvlmList = Stream.concat(etcTrfvlmList.stream(), dataList.stream())
									.collect(Collectors.toList());
						}
					} else if (sheetIndex == 7) {
						// 유사시설 활동인구 원단위(7)
						errorSheet = "유사시설 활동인구 원단위";
						int actPopltnNo = 1;
						for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
							errorRow = rowIndex;
							XSSFRow row = sheet.getRow(rowIndex);
							if (rowIndex >= 0 && rowIndex <= 3 || row == null)
								continue;

							List<TrfIpcssActPopltnBsunt> dataList = new ArrayList<TrfIpcssActPopltnBsunt>();
							dataList = getActPopltnBsuntDataList(rowIndex, row, usgCdList, actPopltnNo);

							actPopltnBsuntList = Stream.concat(actPopltnBsuntList.stream(), dataList.stream())
									.collect(Collectors.toList());
							actPopltnNo++;
						}
					} else if (sheetIndex == 8) {
						// 주거용도 교통수단분담률 조사(8)
						errorSheet = "주거용도 교통수단분담률 조사";
						List<String> trfUseCdRowList = new ArrayList<String>(); // 교통 사용 코드
						List<String> trfMeanVhcclsRowList = new ArrayList<String>(); // 교통 용도 코드
						int meanShareNo = 1;
						for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
							errorRow = rowIndex;
							XSSFRow row = sheet.getRow(rowIndex);
							if (rowIndex == 4) {
								trfUseCdRowList = getRowValueToList(row, false);
							}

							if (rowIndex == 5) {
								trfMeanVhcclsRowList = getRowValueToList(row, false);
							}
							if (rowIndex >= 0 && rowIndex <= 5 || row == null)
								continue;

							List<TrfIpcssMeanShareRt> dataList = new ArrayList<TrfIpcssMeanShareRt>();
							dataList = getMeanShareRtDataListForDwell(rowIndex, row, usgCdList, trfUseCdRowList,
									trfMeanVhcclsRowList, trfUseCdList, trfMeanVhcclsList, meanShareNo);

							meanShareRtList = Stream.concat(meanShareRtList.stream(), dataList.stream())
									.collect(Collectors.toList());
							meanShareNo++;
						}
					} else if (sheetIndex == 9) {
						// 비주거용도 교통수단 분담률 조사(9)
						errorSheet = "비주거용도 교통수단 분담률 조사";
						List<String> trfUseCdRowList = new ArrayList<String>(); // 교통 사용 코드
						List<String> trfMeanVhcclsRowList = new ArrayList<String>(); // 교통 용도 코드
						int meanShareNo = 1;
						for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
							errorRow = rowIndex;
							XSSFRow row = sheet.getRow(rowIndex);
							if (rowIndex == 3) {
								trfUseCdRowList = getRowValueToList(row, false);
							}

							if (rowIndex == 4) {
								trfMeanVhcclsRowList = getRowValueToList(row, false);
							}
							if (rowIndex >= 0 && rowIndex <= 4 || row == null)
								continue;

							List<TrfIpcssMeanShareRt> dataList = new ArrayList<TrfIpcssMeanShareRt>();
							dataList = getMeanShareRtDataListForNonDwell(rowIndex, row, usgCdList, trfUseCdRowList,
									trfMeanVhcclsRowList, trfUseCdList, trfMeanVhcclsList, meanShareNo);

							meanShareRtList = Stream.concat(meanShareRtList.stream(), dataList.stream())
									.collect(Collectors.toList());
							meanShareNo++;
						}
					} else if (sheetIndex == 10) {
						// 주거용도 재차인차인원 조사(10)
						errorSheet = "주거용도 재차인차인원 조사";
						List<String> trfUseCdRowList = new ArrayList<String>(); // 교통 사용 코드
						List<String> trfMeanVhcclsRowList = new ArrayList<String>(); // 교통 용도 코드
						int nboplNo = 1;
						for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
							errorRow = rowIndex;
							XSSFRow row = sheet.getRow(rowIndex);
							if (rowIndex == 4) {
								trfUseCdRowList = getRowValueToList(row, false);
							}

							if (rowIndex == 5) {
								trfMeanVhcclsRowList = getRowValueToList(row, false);
							}
							if (rowIndex >= 0 && rowIndex <= 4 || row == null)
								continue;

							List<TrfIpcssNbopl> dataList = new ArrayList<TrfIpcssNbopl>();
							dataList = getNboplDataListForDwell(rowIndex, row, usgCdList, trfUseCdRowList,
									trfMeanVhcclsRowList, trfUseCdList, trfMeanVhcclsList, nboplNo);

							nboplList = Stream.concat(nboplList.stream(), dataList.stream())
									.collect(Collectors.toList());
							nboplNo++;
						}
					} else if (sheetIndex == 11) {
						// 비주거용도 재차인차인원 조사(11)
						errorSheet = "비주거용도 재차인차인원 조사";
						List<String> trfUseCdRowList = new ArrayList<String>(); // 교통 사용 코드
						List<String> trfMeanVhcclsRowList = new ArrayList<String>(); // 교통 용도 코드
						int nboplNo = 1;
						for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
							errorRow = rowIndex;
							XSSFRow row = sheet.getRow(rowIndex);
							if (rowIndex == 3) {
								trfUseCdRowList = getRowValueToList(row, false);
							}

							if (rowIndex == 4) {
								trfMeanVhcclsRowList = getRowValueToList(row, false);
							}
							if (rowIndex >= 0 && rowIndex <= 4 || row == null)
								continue;

							List<TrfIpcssNbopl> dataList = new ArrayList<TrfIpcssNbopl>();
							dataList = getNboplDataListForNonDwell(rowIndex, row, usgCdList, trfUseCdRowList,
									trfMeanVhcclsRowList, trfUseCdList, trfMeanVhcclsList, nboplNo);

							nboplList = Stream.concat(nboplList.stream(), dataList.stream())
									.collect(Collectors.toList());
							nboplNo++;
						}
					} else if (sheetIndex == 12) {
						// 시간대별 유출입 통행 분포 조사(12)
						errorSheet = "시간대별 유출입 통행 분포 조사";
						TrfIpcssTimeInoutflExmn trfIpcssTimeInoutflExmn = new TrfIpcssTimeInoutflExmn();
						String dywkDiv = ""; // 요일 구분
						List<String> usgNoList = new ArrayList<String>(); // 용도 번호
						List<String> usgNmList = new ArrayList<String>(); // 용도 명

						for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
							errorRow = rowIndex;
							XSSFRow row = sheet.getRow(rowIndex);
							if (row == null)
								continue;

							if (rowIndex >= 0 && rowIndex <= 5) {
								// 요일 구분
								if (rowIndex == 0) {
									dywkDiv = getCellValue(row.getCell(0));
									trfIpcssTimeInoutflExmn.setDywkDiv(dywkDiv);
								}
								// 용도 번호
								if (rowIndex == 3) {
									usgNoList = getRowValueToList(row, true);
									trfIpcssTimeInoutflExmn.setUsgNoList(usgNoList);
								}
								// 용도 명
								if (rowIndex == 4) {
									usgNmList = getRowValueToList(row, true);
									trfIpcssTimeInoutflExmn.setUsgNmList(usgNmList);
								}
							}
							if (rowIndex >= 8) {
								List<TrfIpcssTimeInoutflExmn> dataList = new ArrayList<TrfIpcssTimeInoutflExmn>();
								dataList = getTimeInoutflExmnDataList(trfIpcssExmnBizInfo, trfIpcssTimeInoutflExmn,
										rowIndex, row);

								timeInoutflExmnList = Stream.concat(timeInoutflExmnList.stream(), dataList.stream())
										.collect(Collectors.toList());
							}
						}
					} else if (sheetIndex == 13) {
						// 주차발생 원단위 조사자료(13)
						errorSheet = "주차발생 원단위 조사자료";
						int parkngOccurNo = 1;
						for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
							errorRow = rowIndex;
							XSSFRow row = sheet.getRow(rowIndex);
							if (rowIndex >= 0 && rowIndex <= 3 || row == null)
								continue;

							List<TrfIpcssParkngOccurBsunt> dataList = new ArrayList<TrfIpcssParkngOccurBsunt>();
							dataList = getParkngOccurBsuntDataList(rowIndex, row, usgCdList, parkngOccurNo);

							parkngOccurBsuntList = Stream.concat(parkngOccurBsuntList.stream(), dataList.stream())
									.collect(Collectors.toList());
							parkngOccurNo++;
						}
					} else if (sheetIndex == 14) {
						// 시간대별 통행 분포비(14)
						errorSheet = "시간대별 통행 분포비";
						TrfIpcssTimePassDistrb paramDto = new TrfIpcssTimePassDistrb();
						String bizNm = "";
						String bizUsg = "";
						List<String> rowUsgCdList = new ArrayList<String>(); // 용도 코드 리스트
						for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
							errorRow = rowIndex;
							XSSFRow row = sheet.getRow(rowIndex);
							if (row == null)
								continue;

							if (rowIndex == 1)
								paramDto.setBizNm(getCellValue(row.getCell(1)));
							if (rowIndex == 2)
								paramDto.setBizUsg(getCellValue(row.getCell(1)));

							if (rowIndex == 4 || rowIndex == 35)
								paramDto.setDywkDiv(getCellValue(row.getCell(0))); // 요일 구분
							if (rowIndex == 7 || rowIndex == 38) {
								rowUsgCdList = getRowValueToList(row, false);
								paramDto.setUsgCdList(rowUsgCdList);
							}

							if (rowIndex >= 8 && rowIndex <= 31 || rowIndex >= 39 && rowIndex <= 62) {
								List<TrfIpcssTimePassDistrb> dataList = new ArrayList<TrfIpcssTimePassDistrb>();
								dataList = getTimePassDistrbDataList(paramDto, rowIndex, row, usgCdList);

								timePassDistrbList = Stream.concat(timePassDistrbList.stream(), dataList.stream())
										.collect(Collectors.toList());
							}
						}
					}
				}
				// 각 DTO List 저장
				// 기초 정보 저장
				long ipcssMngNo = trfIpcssExmnBizInfoMapper.findOneByMngNoNextVal();
				String ipcssMngNoStr = String.valueOf(ipcssMngNo);

				trfIpcssExmnBizInfo.setIpcssMngNo(ipcssMngNoStr);
				trfIpcssExmnBizInfoMapper.saveExmnBizInfo(trfIpcssExmnBizInfo);

				// 평일 교차로 교통량 / 주말 교차로 교통량
				if (!crsrdTrfvlmList.isEmpty()) {
					for (TrfIpcssCrsrdTrfvlm crsrdTrfvlm : crsrdTrfvlmList) {
						crsrdTrfvlm.setIpcssMngNo(ipcssMngNoStr);
						crsrdTrfvlm.setEtlDt(BDDateFormatUtil.isNowStr("yyyyMMddhhmmss"));
						trfIpcssCrsrdTrfvlmMapper.saveCrsrdTrfvlm(crsrdTrfvlm);
					}
				}
				// 평일 가로구간 교통량 / 주말 가로구간 교통량
				if (!strsctTrfvlmList.isEmpty()) {
					for (TrfIpcssStrsctTrfvlm strsctTrfvlm : strsctTrfvlmList) {
						strsctTrfvlm.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssStrsctTrfvlmMapper.saveStrsctTrfvlm(strsctTrfvlm);
					}
				}
				// 보행,자전거,PM 교통량
				if (!etcTrfvlmList.isEmpty()) {
					for (TrfIpcssEtcTrfvlm etcTrfvlm : etcTrfvlmList) {
						etcTrfvlm.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssEtcTrfvlmMapper.saveEtcTrfvlm(etcTrfvlm);
					}
				}
				// 유사시설 활동인구 원단위
				if (!actPopltnBsuntList.isEmpty()) {
					for (TrfIpcssActPopltnBsunt actPopltnBsunt : actPopltnBsuntList) {
						actPopltnBsunt.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssActPopltnBsuntMapper.saveActPopltnBsunt(actPopltnBsunt);
					}
				}
				// 교통수단 분담률 조사(주거용도, 비주거용도)
				if (!meanShareRtList.isEmpty()) {
					for (TrfIpcssMeanShareRt meanShareRt : meanShareRtList) {
						meanShareRt.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssMeanShareRtMapper.saveMeanShareRt(meanShareRt);
					}
				}
				// 재차인차인원 조사(주거용도, 비주거용도)
				if (!nboplList.isEmpty()) {
					for (TrfIpcssNbopl nbopl : nboplList) {
						nbopl.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssNboplMapper.saveNbopl(nbopl);
					}
				}
				// 시간대별 유출입 통행분포 조사
				if (!timeInoutflExmnList.isEmpty()) {
					for (TrfIpcssTimeInoutflExmn timeInoutflExmn : timeInoutflExmnList) {
						timeInoutflExmn.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssTimeInoutflExmnMapper.saveTimeInoutflExmn(timeInoutflExmn);
					}
				}
				// 주차발생 원단위 조사자료
				if (!parkngOccurBsuntList.isEmpty()) {
					for (TrfIpcssParkngOccurBsunt parkngOccurBsunt : parkngOccurBsuntList) {
						parkngOccurBsunt.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssParkngOccurBsuntMapper.saveParkngOccurBsunt(parkngOccurBsunt);
					}
				}
				// 시간대별 통행 분포비
				if (!timePassDistrbList.isEmpty()) {
					for (TrfIpcssTimePassDistrb timePassDistrb : timePassDistrbList) {
						timePassDistrb.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssTimePassDistrbMapper.saveTimePassDistrb(timePassDistrb);
					}
				}
			}
		} catch (FileNotFoundException e) {
			throw new CommonException(ErrorCode.FILE_NOT_FOUND);
		} catch (Exception e) {
			throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL, errorSheet + " 시트 " + errorRow + "행을 확인해주세요.");
		} finally {
			if(workbook != null) {
				try {
					workbook.close();
				} catch (Exception e) {
					throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL, errorSheet + " 시트 " + errorRow + "행을 확인해주세요.");
				} 
			}
		}
	}
	
	
	/**
	 * @Method Name : getRowValueToList
	 * @작성일 : 2023. 11. 16.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 헤더 로우 리스트로 반환
	 * @param : row
	 * @return :
	 */
	private List<String> getRowValueToList(XSSFRow row, boolean type) {
		List<String> resultList = new ArrayList<String>();

		int cells = row.getPhysicalNumberOfCells();

		for (int cellIndex = 0; cellIndex < cells; cellIndex++) {
			String cellStr = getCellValue(row.getCell(cellIndex));

			if (type) {
				if (!GgitsCommonUtils.isNull(cellStr) && !cellStr.equals("false")) {
					// false 제외
					resultList.add(cellStr);
				}
			} else {
				// false 포함
				resultList.add(cellStr);
			}
		}

		return resultList;
	}
	
	/**
	 * @param timeSctrnNum
	 * @param exmnYmdStr 
	 * @Method Name : getCrsrdTrfvlmDataList
	 * @작성일 : 2023. 11. 14.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 교차로 교통량 데이터 세팅
	 * @param : ipcssBaseInfoDTO
	 * @param : row
	 * @param : trfIpcssCrsrdTrfvlm
	 * @param : sheetIndex
	 * @return :
	 */
	private List<TrfIpcssCrsrdTrfvlm> getCrsrdTrfvlmDataList(int rowIndex, XSSFRow row, TrfIpcssCrsrdTrfvlm paramDTO,
			int timeSctrnNum, int sheetIndex, String exmnYmdStr) {
		List<TrfIpcssCrsrdTrfvlm> resultList = new ArrayList<TrfIpcssCrsrdTrfvlm>();
		String[] sumRowStr = new String[] { "19:00", "08:00", "09:00", "18:00" };
		int lastCellNum = row.getPhysicalNumberOfCells();
		int termNum = 10;
		int sumCellNum = 9;
		int crsrdDrctCdNum = 1;
		int timeInterval = 6;
		for (int cellIndex = 0; cellIndex <= lastCellNum; cellIndex++) {
			boolean chkCell = false;
			TrfIpcssCrsrdTrfvlm trfIpcssCrsrdTrfvlm = new TrfIpcssCrsrdTrfvlm();
			// 합계 열 제거
			if (sumCellNum != 9) {
				sumCellNum += 7;
				if (crsrdDrctCdNum > 15) {
					crsrdDrctCdNum = 1;
				}
			}

			if (cellIndex == 10 || cellIndex == termNum) {
				String dywkDiv = sheetIndex == 2 ? "평일" : "주말"; // 요일 구분
				String crsrdNm = paramDTO.setCrsrdNm(paramDTO.getCrsrdNm()); // 교차로명
				Double lat = paramDTO.getPointLat();	// 위도
				Double lon = paramDTO.getPointLon();	// 경도
				String crsrdDrctCd = String.valueOf(crsrdDrctCdNum); // 교차로 방향 코드
				String timeSctnNm = getCellValue(row.getCell(cellIndex - timeInterval)); // 시간 구분
				String psgvhclTrfvlm = getCellValue(row.getCell(cellIndex - 5)); // 승용차 교통량
				String busTrfvlm = getCellValue(row.getCell(cellIndex - 4)); // 버스 소형 교통량
				String busLrgszTrfvlm = getCellValue(row.getCell(cellIndex - 3)); // 버스 대형 교통량
				String frghtSmlszTrfvlm = getCellValue(row.getCell(cellIndex - 2)); // 화물 소형 교통량
				String frghtMdmszTrfvlm = getCellValue(row.getCell(cellIndex - 1)); // 화물 중형 교통량
				String frghtLrgszTrfvlm = getCellValue(row.getCell(cellIndex)); // 화물 대형 교통량

				trfIpcssCrsrdTrfvlm.setDywkDiv(dywkDiv);
				trfIpcssCrsrdTrfvlm.setExmnYmd(GgitsCommonUtils.extractDateWithComma(exmnYmdStr));
				trfIpcssCrsrdTrfvlm.setCrsrdNo(paramDTO.getCrsrdNo());
				if (ExcelUploadUtil.checkCellValue(crsrdNm)) {
					trfIpcssCrsrdTrfvlm.setCrsrdNm(crsrdNm);
				}
				if (lat != null) {
					trfIpcssCrsrdTrfvlm.setPointLat(lat);
				}
				if (lon != null) {
					trfIpcssCrsrdTrfvlm.setPointLon(lon);
				}
				if (ExcelUploadUtil.checkCellValue(crsrdDrctCd)) {
					trfIpcssCrsrdTrfvlm.setCrsrdDrctCd(crsrdDrctCd);
				}
				if (ExcelUploadUtil.checkCellValue(timeSctnNm)) {
					if (GgitsCommonUtils.chkContainString(timeSctnNm)) {
						timeSctnNm = sumRowStr[timeSctrnNum];
						trfIpcssCrsrdTrfvlm.setTimeSctnNm(timeSctnNm);
					} else {
						trfIpcssCrsrdTrfvlm.setTimeSctnNm(timeSctnNm);
					}
				}
				if (ExcelUploadUtil.checkCellValue(psgvhclTrfvlm) && GgitsCommonUtils.isLong(psgvhclTrfvlm)) {
					trfIpcssCrsrdTrfvlm.setPsgvhclTrfvlm(Long.parseLong(psgvhclTrfvlm));
					chkCell = true;
				}
				if (ExcelUploadUtil.checkCellValue(busTrfvlm) && GgitsCommonUtils.isLong(busTrfvlm)) {
					trfIpcssCrsrdTrfvlm.setBusTrfvlm(Long.parseLong(busTrfvlm));
					chkCell = true;
				}
				if (ExcelUploadUtil.checkCellValue(busLrgszTrfvlm) && GgitsCommonUtils.isLong(busLrgszTrfvlm)) {
					trfIpcssCrsrdTrfvlm.setBusLrgszTrfvlm(Long.parseLong(busLrgszTrfvlm));
					chkCell = true;
				}
				if (ExcelUploadUtil.checkCellValue(frghtSmlszTrfvlm) && GgitsCommonUtils.isLong(frghtSmlszTrfvlm)) {
					trfIpcssCrsrdTrfvlm.setFrghtSmlszTrfvlm(Long.parseLong(frghtSmlszTrfvlm));
					chkCell = true;
				}
				if (ExcelUploadUtil.checkCellValue(frghtMdmszTrfvlm) && GgitsCommonUtils.isLong(frghtMdmszTrfvlm)) {
					trfIpcssCrsrdTrfvlm.setFrghtMdmszTrfvlm(Long.parseLong(frghtMdmszTrfvlm));
					chkCell = true;
				}
				if (ExcelUploadUtil.checkCellValue(frghtLrgszTrfvlm) && GgitsCommonUtils.isLong(frghtLrgszTrfvlm)) {
					trfIpcssCrsrdTrfvlm.setFrghtLrgszTrfvlm(Long.parseLong(frghtLrgszTrfvlm));
					chkCell = true;
				}
				if (chkCell) {
					resultList.add(trfIpcssCrsrdTrfvlm);
				}
				termNum += 7;
				crsrdDrctCdNum++;
				timeInterval += 7;
			}

			if (sumCellNum == cellIndex) {
				sumCellNum += 7;
				continue;
			}
		}
		return resultList;
	}

	/**
	 * @param exmnYmd 
	 * @Method Name : getStrsctTrfvlmDataList
	 * @작성일 : 2023. 11. 14.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 가로구간 교통량 데이터 세팅
	 * @param : row
	 * @param : backword
	 * @param : forword
	 * @param : strsctNm
	 * @param : timeSctrnNum
	 * @param : sheetIndex
	 * @param : strsctNo
	 * @return :
	 */
	private List<TrfIpcssStrsctTrfvlm> getStrsctTrfvlmDataList(int rowIndex, XSSFRow row, String strsctNm,
			String forword, String backword, int timeSctrnNum, int sheetIndex, String strsctNo, String exmnYmdStr) {
		String[] sumRowStr = new String[] { "08:00", "09:00", "18:00", "19:00" };
		List<TrfIpcssStrsctTrfvlm> resultList = new ArrayList<TrfIpcssStrsctTrfvlm>();
		int lastCellNum = row.getPhysicalNumberOfCells();

		for (int cellIndex = 2; cellIndex <= lastCellNum; cellIndex++) {
			TrfIpcssStrsctTrfvlm trfIpcssStrsctTrfvlm = new TrfIpcssStrsctTrfvlm();

			boolean chkCell = false;
			if ((cellIndex == 8 || cellIndex == 15)) {
				String dywkDiv = sheetIndex == 4 ? "평일" : "주말";
				String strsctDrctNo = cellIndex == 8 ? "1" : "2";
				String timeSctnNm = "";
				if (cellIndex == 8) {
					timeSctnNm = getCellValue(row.getCell(cellIndex - 6)); // 시간 구분
				} else {
					timeSctnNm = getCellValue(row.getCell(cellIndex - 13)); // 시간 구분
				}
				String psgvhclTrfvlm = getCellValue(row.getCell(cellIndex - 5));
				String busTrfvlm = getCellValue(row.getCell(cellIndex - 4));
				String busLrgszTrfvlm = getCellValue(row.getCell(cellIndex - 3));
				String frghtSmlszTrfvlm = getCellValue(row.getCell(cellIndex - 2));
				String frghtMdmszTrfvlm = getCellValue(row.getCell(cellIndex - 1));
				String frghtLrgszTrfvlm = getCellValue(row.getCell(cellIndex));

				trfIpcssStrsctTrfvlm.setDywkDiv(dywkDiv);
				trfIpcssStrsctTrfvlm.setStrsctNo(strsctNo);
				trfIpcssStrsctTrfvlm.setExmnYmd(GgitsCommonUtils.extractDateWithComma(exmnYmdStr));
				trfIpcssStrsctTrfvlm.setStrsctDrctNo(strsctDrctNo);
				trfIpcssStrsctTrfvlm.setStrsctNm(strsctNm); // 가로구간명
				if (cellIndex == 8) {
					trfIpcssStrsctTrfvlm.setStrsctDrctNm(forword); // 가로구간 방향명
				} else {
					trfIpcssStrsctTrfvlm.setStrsctDrctNm(backword); // 가로구간 방향명
				}
				if (ExcelUploadUtil.checkCellValue(timeSctnNm)) {
					if (GgitsCommonUtils.chkContainString(timeSctnNm)) {
						timeSctnNm = sumRowStr[timeSctrnNum];
						trfIpcssStrsctTrfvlm.setTimeSctnNm(timeSctnNm);
					} else {
						trfIpcssStrsctTrfvlm.setTimeSctnNm(timeSctnNm);
					}
				}
				if (ExcelUploadUtil.checkCellValue(psgvhclTrfvlm) && GgitsCommonUtils.isLong(psgvhclTrfvlm)) {
					trfIpcssStrsctTrfvlm.setPsgvhclTrfvlm(Long.parseLong(psgvhclTrfvlm)); // 승용차 교통량
					chkCell = true;
				}
				if (ExcelUploadUtil.checkCellValue(busTrfvlm) && GgitsCommonUtils.isLong(busTrfvlm)) {
					trfIpcssStrsctTrfvlm.setBusTrfvlm(Long.parseLong(busTrfvlm)); // 버스 소형 교통량
					chkCell = true;
				}
				if (ExcelUploadUtil.checkCellValue(busLrgszTrfvlm) && GgitsCommonUtils.isLong(busLrgszTrfvlm)) {
					trfIpcssStrsctTrfvlm.setBusLrgszTrfvlm(Long.parseLong(busLrgszTrfvlm)); // 버스 대형 교통량
					chkCell = true;
				}
				if (ExcelUploadUtil.checkCellValue(frghtSmlszTrfvlm) && GgitsCommonUtils.isLong(frghtSmlszTrfvlm)) {
					trfIpcssStrsctTrfvlm.setFrghtSmlszTrfvlm(Long.parseLong(frghtSmlszTrfvlm));// 화물 소형 교통량
					chkCell = true;
				}
				if (ExcelUploadUtil.checkCellValue(frghtMdmszTrfvlm) && GgitsCommonUtils.isLong(frghtMdmszTrfvlm)) {
					trfIpcssStrsctTrfvlm.setFrghtMdmszTrfvlm(Long.parseLong(frghtMdmszTrfvlm));// 화물 중형 교통량
					chkCell = true;
				}
				if (ExcelUploadUtil.checkCellValue(frghtLrgszTrfvlm) && GgitsCommonUtils.isLong(frghtLrgszTrfvlm)) {
					trfIpcssStrsctTrfvlm.setFrghtLrgszTrfvlm(Long.parseLong(frghtLrgszTrfvlm));// 화물 대형 교통량
					chkCell = true;
				}

				if (chkCell) {
					resultList.add(trfIpcssStrsctTrfvlm);
				}
			}
		}

		return resultList;
	}

	/**
	 * @Method Name : getEtcTrfvlmDataList
	 * @작성일 : 2023. 11. 14.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 보행, 자전거, PM 교통량 데이터 세팅
	 * @param : row
	 * @param : exmnYmdStr
	 * @param : pointNmList
	 * @param : dywkDiv
	 * @return :
	 */
	private List<TrfIpcssEtcTrfvlm> getEtcTrfvlmDataList(int rowIndex, XSSFRow row, String exmnYmdStr,
			List<String> pointNmList, String dywkDiv) {
		String[] sumRowStr = new String[] { "08:00", "09:00", "18:00", "19:00" };
		List<TrfIpcssEtcTrfvlm> resultList = new ArrayList<TrfIpcssEtcTrfvlm>();
		int lastCellNum = row.getPhysicalNumberOfCells();
		int pointCnt = 1;
		int timeSctrnNum = 0;
		if (rowIndex == 14 || rowIndex == 40) {
			timeSctrnNum = 1;
		} else if (rowIndex == 19 || rowIndex == 45) {
			timeSctrnNum = 2;
		} else if (rowIndex == 24 || rowIndex == 50) {
			timeSctrnNum = 3;
		}

		for (int cellIndex = 1; cellIndex <= lastCellNum; cellIndex++) {
			boolean chkCell = false;
			TrfIpcssEtcTrfvlm trfIpcssEtcTrfvlm = new TrfIpcssEtcTrfvlm();
			// 보행(명) / 자전거(대) / PM(대)
			if ((cellIndex % 3) == 0) {
				trfIpcssEtcTrfvlm.setExmnYmd(GgitsCommonUtils.extractDateWithComma(exmnYmdStr));// 조사일자
				String pointNm = pointNmList.get(cellIndex - 2);

				if (GgitsCommonUtils.isNull(pointNm) || pointNm.equals("false")) {
					throw new CommonException(ErrorCode.PARAMETER_DATA_INVALID, "셀의 정보가 올바르지 않습니다.");
				}
				trfIpcssEtcTrfvlm.setPointNm(pointNm);// 지점명
				trfIpcssEtcTrfvlm.setPointNo(String.valueOf(pointCnt));
				trfIpcssEtcTrfvlm.setDywkDiv(dywkDiv);
				trfIpcssEtcTrfvlm.setTimeSctnNm(getCellValue(row.getCell(0))); // 시간구간명
				String pdstCnt = ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(cellIndex - 2)))
						? getCellValue(row.getCell(cellIndex - 2))
						: "0";
				String bcyclCnt = ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(cellIndex - 1)))
						? getCellValue(row.getCell(cellIndex - 1))
						: "0";
				String indvslMvmnEqpmntCnt = ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(cellIndex)))
						? getCellValue(row.getCell(cellIndex))
						: "0";
				if (GgitsCommonUtils.chkContainString(trfIpcssEtcTrfvlm.getTimeSctnNm())) {
					// 합계 로우 추가
					String timeSctnNm = sumRowStr[timeSctrnNum];
					trfIpcssEtcTrfvlm.setTimeSctnNm(timeSctnNm);
				} else {

				}
				if (ExcelUploadUtil.checkCellValue(pdstCnt) && GgitsCommonUtils.isLong(pdstCnt)) {
					trfIpcssEtcTrfvlm.setPdstCnt(Long.parseLong(pdstCnt));// 보행자 수
					chkCell = true;
				}
				if (ExcelUploadUtil.checkCellValue(bcyclCnt) && GgitsCommonUtils.isLong(bcyclCnt)) {
					trfIpcssEtcTrfvlm.setBcyclCnt(Long.parseLong(bcyclCnt));// 자전거 수
					chkCell = true;
				}
				if (ExcelUploadUtil.checkCellValue(indvslMvmnEqpmntCnt)
						&& GgitsCommonUtils.isLong(indvslMvmnEqpmntCnt)) {
					trfIpcssEtcTrfvlm.setIndvslMvmnEqpmntCnt(Long.parseLong(indvslMvmnEqpmntCnt));// 개인 이동 장비 수
					chkCell = true;
				}
				if (chkCell) {
					resultList.add(trfIpcssEtcTrfvlm);
					pointCnt++;
				}
			}
		}

		return resultList;
	}

	/**
	 * @Method Name : getActPopltnBsuntDataList
	 * @작성일 : 2023. 11. 14.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 유사시설 활동인구 원단위
	 * @param : rowIndex
	 * @param : row
	 * @param : usgCdList
	 * @param : actPopltnNo
	 * @return :
	 */
	private List<TrfIpcssActPopltnBsunt> getActPopltnBsuntDataList(int rowIndex, XSSFRow row, List<MOpCode> usgCdList,
			int actPopltnNo) {
		List<TrfIpcssActPopltnBsunt> resultList = new ArrayList<TrfIpcssActPopltnBsunt>();
		String startRowValue = getCellValue(row.getCell(1));
		if (ExcelUploadUtil.checkCellValue(startRowValue)) {
			int lastCellNum = row.getLastCellNum();
			int termNum = 0;
			int usgNo = 1;
			for (int cellIndex = 12; cellIndex <= lastCellNum; cellIndex++) {
				String partValue = getCellValue(row.getCell(cellIndex));
				if (ExcelUploadUtil.checkCellValue(partValue)) {
					TrfIpcssActPopltnBsunt trfIpcssActPopltnBsunt = new TrfIpcssActPopltnBsunt();
					// 로우의 기초 데이터 세팅
					trfIpcssActPopltnBsunt.setSmlfactNm(getCellValue(row.getCell(1))); // 유사시설 명칭
					trfIpcssActPopltnBsunt.setSmlfactAddr(getCellValue(row.getCell(2))); // 유사시설 주소
					trfIpcssActPopltnBsunt.setScale(getCellValue(row.getCell(3))); // 규모
					String totrar = getCellValue(row.getCell(4)) == null ? (String) "0" : getCellValue(row.getCell(4));
					if (ExcelUploadUtil.checkCellValue(totrar)) {
						trfIpcssActPopltnBsunt.setTotfrar(Double.parseDouble(totrar)); // 전체 연면적						
					}
					trfIpcssActPopltnBsunt.setExmnDivNm(getCellValue(row.getCell(5))); // 조사 구분
					if (ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(6)))) {
						trfIpcssActPopltnBsunt.setExmnYmd(GgitsCommonUtils.extractDateWithComma(getCellValue(row.getCell(6))));					
					}
					if (ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(7)))) {
						trfIpcssActPopltnBsunt.setExmnDocNm(getCellValue(row.getCell(7))); // 문헌조사(참고문헌)
					}

					if (cellIndex == 12 || (cellIndex - termNum) == 12) {
						String usgCdStr = getCellValue(row.getCell(cellIndex - 4));
						String usgCd = ExcelUploadUtil.findUsgCd(usgCdStr, usgCdList);

						trfIpcssActPopltnBsunt.setActPopltnNo(String.valueOf(actPopltnNo));
						trfIpcssActPopltnBsunt.setUsgCd(usgCd); // 용도 명칭
						trfIpcssActPopltnBsunt.setUsgNo(String.valueOf(usgNo));
						trfIpcssActPopltnBsunt.setUsgTotfrar(getCellValue(row.getCell(cellIndex - 3))); // 용도별 연면적
						String resdngBsunt = getCellValue(row.getCell(cellIndex - 2)) == null ? (String) "0"
								: getCellValue(row.getCell(cellIndex - 2));
						trfIpcssActPopltnBsunt.setResdngBsunt(Double.parseDouble(resdngBsunt)); // 상주/상근
						String visitBsunt = getCellValue(row.getCell(cellIndex - 1)) == null ? (String) "0"
								: getCellValue(row.getCell(cellIndex - 1));
						trfIpcssActPopltnBsunt.setVisitBsunt(Double.parseDouble(visitBsunt)); // 방문/이용
						trfIpcssActPopltnBsunt.setBsuntUnit(getCellValue(row.getCell(cellIndex))); // 단위
						resultList.add(trfIpcssActPopltnBsunt);
						termNum += 5;
						usgNo++;
					}
				}
			}
		}
		return resultList;
	}

	/**
	 * @Method Name : getMeanShareRtDataListForDwell
	 * @작성일 : 2023. 11. 15.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 주거용도 교통수단분담율 조사
	 * @param :                    rowIndex
	 * @param :                    row
	 * @param trfMeanVhcclsRowList
	 * @param trfUseCdRowList
	 * @param :                    usgCdList
	 * @param trfMeanVhcclsList
	 * @param trfUseCdList
	 * @param meanShareNo
	 * @return :
	 */
	private List<TrfIpcssMeanShareRt> getMeanShareRtDataListForDwell(int rowIndex, XSSFRow row, List<MOpCode> usgCdList,
			List<String> trfUseCdRowList, List<String> trfMeanVhcclsRowList, List<MOpCode> trfUseCdList,
			List<MOpCode> trfMeanVhcclsList, int meanShareNo) {
		List<TrfIpcssMeanShareRt> resultList = new ArrayList<TrfIpcssMeanShareRt>();
		String startRowValue = getCellValue(row.getCell(1));
		if (ExcelUploadUtil.checkCellValue(startRowValue)) {
			int lastCellNum = row.getLastCellNum();
			int usgCdNum = 8;
			int trfUseCdNum = 0;
			int usgNo = 1;
			for (int cellIndex = 9; cellIndex <= lastCellNum; cellIndex++) {
				String partCellValue = getCellValue(row.getCell(cellIndex));
				if (ExcelUploadUtil.checkCellValue(partCellValue)) {
					TrfIpcssMeanShareRt trfIpcssMeanShareRt = new TrfIpcssMeanShareRt();
					// 로우의 기초 데이터 세팅
					trfIpcssMeanShareRt.setSmlfactNm(getCellValue(row.getCell(1))); // 유사시설 명칭
					trfIpcssMeanShareRt.setSmlfactAddr(getCellValue(row.getCell(2))); // 유사시설 주소
					trfIpcssMeanShareRt.setScale(getCellValue(row.getCell(3))); // 규모
					String totrar = getCellValue(row.getCell(4)) == null ? (String) "0" : getCellValue(row.getCell(4));
					if (ExcelUploadUtil.checkCellValue(totrar)) {
						trfIpcssMeanShareRt.setTotfrar(Double.parseDouble(totrar)); // 전체 연면적
					}
					trfIpcssMeanShareRt.setExmnDivNm(getCellValue(row.getCell(5))); // 조사 구분
					if (ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(6)))) {
						trfIpcssMeanShareRt.setExmnYmd(GgitsCommonUtils.extractDateWithComma(getCellValue(row.getCell(6))));	// 현황조사 일자						
					}
					if (ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(7)))) {
						trfIpcssMeanShareRt.setExmnDocNm(getCellValue(row.getCell(7))); // 문헌조사(참고문헌)
					}
					trfIpcssMeanShareRt.setUsgNo(String.valueOf(usgNo));

					// 용도 명칭 cell은 반복 제외
					if (cellIndex == 8 || cellIndex == 29 || cellIndex == 50 || cellIndex == 71 || cellIndex == 92
							|| cellIndex == 113 || cellIndex == 134 || cellIndex == 155) {
						usgCdNum = cellIndex;
						continue;
					}

					// 교통 사용 코드
					String trfUseCdStr = trfUseCdRowList.get(cellIndex - trfUseCdNum);
					if (!trfUseCdStr.equals("방문") && !trfUseCdStr.equals("false")) {
						trfUseCdStr = "상주-" + trfUseCdStr;
					} else if (trfUseCdStr.equals("false")) {
						trfUseCdStr = "방문";
					}
					String trfUseCd = ExcelUploadUtil.findUsgCd(trfUseCdStr, trfUseCdList);

					// 교통 수단 코드
					String trfMeanVhcclsStr = trfMeanVhcclsRowList.get(cellIndex);
					trfMeanVhcclsStr = trfMeanVhcclsStr.replaceAll("\n", "");
					String trfMeanVhccls = ExcelUploadUtil.findUsgCd(trfMeanVhcclsStr, trfMeanVhcclsList);

					String usgCdStr = getCellValue(row.getCell(usgCdNum));
					String usgCd = ExcelUploadUtil.findUsgCd(usgCdStr, usgCdList);

					// 용도 명칭이 주거용도가 아닌 경우 Exception
					if (!ExcelUploadUtil.checkUsgCd(usgCd, "dwell")) {
						throw new CommonException(ErrorCode.PARAMETER_DATA_INVALID, "셀의 정보가 올바르지 않습니다.");
					}

					trfIpcssMeanShareRt.setMeanShareNo(String.valueOf(meanShareNo));
					trfIpcssMeanShareRt.setUsgCd(usgCd); // 용도 코드
					trfIpcssMeanShareRt.setTrfUseCd(trfUseCd); // 교통 사용 코드
					trfIpcssMeanShareRt.setTrfMeanVhccls(trfMeanVhccls); // 교통 수단 코드
					trfIpcssMeanShareRt.setShareRt(Double.parseDouble(getCellValue(row.getCell(cellIndex)))); // 분담 비율
					resultList.add(trfIpcssMeanShareRt);
					trfUseCdNum++;
					usgNo++;
					if (trfUseCdNum == 5) {
						trfUseCdNum = 0;
					}
				}
			}
		}
		return resultList;
	}

	/**
	 * @Method Name : getMeanShareRtDataListForNonDwell
	 * @작성일 : 2023. 11. 15.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 비주거용도 교통수단분담율 조사
	 * @param : rowIndex
	 * @param : row
	 * @param : usgCdList
	 * @return :
	 */
	private List<TrfIpcssMeanShareRt> getMeanShareRtDataListForNonDwell(int rowIndex, XSSFRow row,
			List<MOpCode> usgCdList, List<String> trfUseCdRowList, List<String> trfMeanVhcclsRowList,
			List<MOpCode> trfUseCdList, List<MOpCode> trfMeanVhcclsList, int meanShareNo) {
		List<TrfIpcssMeanShareRt> resultList = new ArrayList<TrfIpcssMeanShareRt>();
		String startRowValue = getCellValue(row.getCell(1));
		if (ExcelUploadUtil.checkCellValue(startRowValue)) {
			int lastCellNum = row.getLastCellNum();
			int usgCdNum = 8;
			int trfUseCdNum = 0;
			int usgNo = 1;
			for (int cellIndex = 9; cellIndex <= lastCellNum; cellIndex++) {
				String partCellValue = getCellValue(row.getCell(cellIndex));
				if (ExcelUploadUtil.checkCellValue(partCellValue)) {
					TrfIpcssMeanShareRt trfIpcssMeanShareRt = new TrfIpcssMeanShareRt();
					// 로우의 기초 데이터 세팅
					trfIpcssMeanShareRt.setSmlfactNm(getCellValue(row.getCell(1))); // 유사시설 명칭
					trfIpcssMeanShareRt.setSmlfactAddr(getCellValue(row.getCell(2))); // 유사시설 주소
					trfIpcssMeanShareRt.setScale(getCellValue(row.getCell(3))); // 규모
					String totrar = getCellValue(row.getCell(4)) == null ? (String) "0" : getCellValue(row.getCell(4));
					if (ExcelUploadUtil.checkCellValue(totrar)) {
						trfIpcssMeanShareRt.setTotfrar(Double.parseDouble(totrar)); // 전체 연면적
					}
					trfIpcssMeanShareRt.setExmnDivNm(getCellValue(row.getCell(5))); // 조사 구분
					if (ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(6)))) {
						trfIpcssMeanShareRt.setExmnYmd(GgitsCommonUtils.extractDateWithComma(getCellValue(row.getCell(6))));	// 현황조사 일자						
					}
					if (ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(7)))) {
						trfIpcssMeanShareRt.setExmnDocNm(getCellValue(row.getCell(7))); // 문헌조사(참고문헌)
					}
					trfIpcssMeanShareRt.setUsgNo(String.valueOf(usgNo));
					// 용도 명칭 cell은 반복 제외
					if (cellIndex == 8 || cellIndex == 19 || cellIndex == 30 || cellIndex == 41 || cellIndex == 52
							|| cellIndex == 63 || cellIndex == 74 || cellIndex == 85 || cellIndex == 96 || cellIndex == 107
							|| cellIndex == 118 || cellIndex == 129 || cellIndex == 140 || cellIndex == 151 || cellIndex == 162
							|| cellIndex == 173 || cellIndex == 184 || cellIndex == 195 || cellIndex == 206 || cellIndex == 217) {
						usgCdNum = cellIndex;
						continue;
					}

					// 교통 사용 코드
					String trfUseCdStr = trfUseCdRowList.get(cellIndex - trfUseCdNum);
					String trfUseCd = ExcelUploadUtil.findUsgCd(trfUseCdStr, trfUseCdList);

					// 교통 수단 코드
					String trfMeanVhcclsStr = trfMeanVhcclsRowList.get(cellIndex);
					trfMeanVhcclsStr = trfMeanVhcclsStr.replaceAll("\n", "");
					String trfMeanVhccls = ExcelUploadUtil.findUsgCd(trfMeanVhcclsStr, trfMeanVhcclsList);

					String usgCdStr = getCellValue(row.getCell(usgCdNum));
					String usgCd = ExcelUploadUtil.findUsgCd(usgCdStr, usgCdList);

					// 용도 명칭이 주거용도가 아닌 경우 Exception
					if (!ExcelUploadUtil.checkUsgCd(usgCd, "nonDwell")) {
						throw new CommonException(ErrorCode.PARAMETER_DATA_INVALID, "셀의 정보가 올바르지 않습니다.");
					}

					trfIpcssMeanShareRt.setMeanShareNo(String.valueOf(meanShareNo));
					trfIpcssMeanShareRt.setUsgCd(usgCd); // 용도 코드
					trfIpcssMeanShareRt.setTrfUseCd(trfUseCd); // 교통 사용 코드
					trfIpcssMeanShareRt.setTrfMeanVhccls(trfMeanVhccls); // 교통 수단 코드
					trfIpcssMeanShareRt.setShareRt(Double.parseDouble(getCellValue(row.getCell(cellIndex)))); // 분담 비율
					resultList.add(trfIpcssMeanShareRt);
					trfUseCdNum++;
					usgNo++;
					if (trfUseCdNum == 5) {
						trfUseCdNum = 0;
					}
				}
			}
		}
		return resultList;
	}

	/**
	 * @Method Name : getNboplDataListForDwell
	 * @작성일 : 2023. 11. 15.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 주거용도 재차인차인원 조사
	 * @param :                    rowIndex
	 * @param :                    row
	 * @param :                    usgCdList
	 * @param trfMeanVhcclsList
	 * @param trfUseCdList
	 * @param trfMeanVhcclsRowList
	 * @param trfUseCdRowList
	 * @return :
	 */
	private List<TrfIpcssNbopl> getNboplDataListForDwell(int rowIndex, XSSFRow row, List<MOpCode> usgCdList,
			List<String> trfUseCdRowList, List<String> trfMeanVhcclsRowList, List<MOpCode> trfUseCdList,
			List<MOpCode> trfMeanVhcclsList, int nboplNo) {
		List<TrfIpcssNbopl> resultList = new ArrayList<TrfIpcssNbopl>();
		String startRowValue = getCellValue(row.getCell(1));
		if (ExcelUploadUtil.checkCellValue(startRowValue)) {
			int lastCellNum = row.getLastCellNum();
			int usgCdNum = 8;
			int trfUseCdNum = 0;
			int usgNo = 1;
			for (int cellIndex = 9; cellIndex <= lastCellNum; cellIndex++) {
				String partCellValue = getCellValue(row.getCell(cellIndex));
				if (ExcelUploadUtil.checkCellValue(partCellValue)) {
					TrfIpcssNbopl trfIpcssNbopl = new TrfIpcssNbopl();
					// 로우의 기초 데이터 세팅
					trfIpcssNbopl.setSmlfactNm(getCellValue(row.getCell(1))); // 유사시설 명칭
					trfIpcssNbopl.setSmlfactAddr(getCellValue(row.getCell(2))); // 유사시설 주소
					trfIpcssNbopl.setScale(getCellValue(row.getCell(3))); // 규모
					String totrar = getCellValue(row.getCell(4)) == null ? (String) "0" : getCellValue(row.getCell(4));
					if (ExcelUploadUtil.checkCellValue(totrar)) {
						trfIpcssNbopl.setTotfrar(Double.parseDouble(totrar)); // 전체 연면적
					}
					trfIpcssNbopl.setExmnDivNm(getCellValue(row.getCell(5))); // 조사 구분
					if (ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(6)))) {
						trfIpcssNbopl.setExmnYmd(GgitsCommonUtils.extractDateWithComma(getCellValue(row.getCell(6))));	// 현황조사 일자						
					}
					if (ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(7)))) {
						trfIpcssNbopl.setExmnDocNm(getCellValue(row.getCell(7))); // 문헌조사(참고문헌)
					}
					trfIpcssNbopl.setUsgNo(String.valueOf(usgNo));
					// 용도 명칭 cell은 반복 제외
					if (cellIndex == 8 || cellIndex == 17 || cellIndex == 26 || cellIndex == 35 || cellIndex == 44
							|| cellIndex == 53 || cellIndex == 62 || cellIndex == 71) {
						usgCdNum = cellIndex;
						continue;
					}

					String usgCdStr = getCellValue(row.getCell(usgCdNum));
					String usgCd = ExcelUploadUtil.findUsgCd(usgCdStr, usgCdList);

					String trfUseCdStr = trfUseCdRowList.get(cellIndex - trfUseCdNum);
					if (!trfUseCdStr.equals("방문") && !trfUseCdStr.equals("false")) {
						trfUseCdStr = "상주-" + trfUseCdStr;
					} else if (trfUseCdStr.equals("false")) {
						trfUseCdStr = "방문";
					}
					String trfUseCd = ExcelUploadUtil.findUsgCd(trfUseCdStr, trfUseCdList);

					// 교통 수단 코드
					String trfMeanVhcclsStr = trfMeanVhcclsRowList.get(cellIndex);
					trfMeanVhcclsStr = trfMeanVhcclsStr.replaceAll("\n", "");
					String trfMeanVhccls = ExcelUploadUtil.findUsgCd(trfMeanVhcclsStr, trfMeanVhcclsList);

					// 용도 명칭이 주거용도가 아닌 경우 Exception
					if (!ExcelUploadUtil.checkUsgCd(usgCd, "dwell")) {
						throw new CommonException(ErrorCode.PARAMETER_DATA_INVALID, "셀의 정보가 올바르지 않습니다.");
					}

					trfIpcssNbopl.setNboplNo(String.valueOf(nboplNo));
					trfIpcssNbopl.setUsgCd(usgCd); // 용도 코드
					trfIpcssNbopl.setTrfUseCd(trfUseCd); // 교통 사용 코드
					trfIpcssNbopl.setTrfMeanVhccls(trfMeanVhccls); // 교통 수단 코드
					trfIpcssNbopl.setNboplCnt(Double.parseDouble(partCellValue)); // 재차인원 수
					resultList.add(trfIpcssNbopl);
					trfUseCdNum++;
					usgNo++;
					if (trfUseCdNum == 2) {
						trfUseCdNum = 0;
					}
				}
			}
		}

		return resultList;
	}

	/**
	 * @Method Name : getNboplDataListForNonDwell
	 * @작성일 : 2023. 11. 15.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 비주거용도 재차인차인원 조사
	 * @param :                    rowIndex
	 * @param :                    row
	 * @param :                    usgCdList
	 * @param trfMeanVhcclsList
	 * @param trfUseCdList
	 * @param trfMeanVhcclsRowList
	 * @param trfUseCdRowList
	 * @return :
	 */
	private List<TrfIpcssNbopl> getNboplDataListForNonDwell(int rowIndex, XSSFRow row, List<MOpCode> usgCdList,
			List<String> trfUseCdRowList, List<String> trfMeanVhcclsRowList, List<MOpCode> trfUseCdList,
			List<MOpCode> trfMeanVhcclsList, int nboplNo) {
		List<TrfIpcssNbopl> resultList = new ArrayList<TrfIpcssNbopl>();
		String startRowValue = getCellValue(row.getCell(1));
		if (ExcelUploadUtil.checkCellValue(startRowValue)) {
			int lastCellNum = row.getLastCellNum();
			int usgCdNum = 8;
			int trfUseCdNum = 0;
			int usgNo = 1;
			for (int cellIndex = 9; cellIndex <= lastCellNum; cellIndex++) {
				String partCellValue = getCellValue(row.getCell(cellIndex));
				if (ExcelUploadUtil.checkCellValue(partCellValue)) {
					TrfIpcssNbopl trfIpcssNbopl = new TrfIpcssNbopl();
					// 로우의 기초 데이터 세팅
					trfIpcssNbopl.setSmlfactNm(getCellValue(row.getCell(1))); // 유사시설 명칭
					trfIpcssNbopl.setSmlfactAddr(getCellValue(row.getCell(2))); // 유사시설 주소
					trfIpcssNbopl.setScale(getCellValue(row.getCell(3))); // 규모
					String totrar = getCellValue(row.getCell(4)) == null ? (String) "0" : getCellValue(row.getCell(4));
					if (ExcelUploadUtil.checkCellValue(totrar)) {
						trfIpcssNbopl.setTotfrar(Double.parseDouble(totrar)); // 전체 연면적
					}
					trfIpcssNbopl.setExmnDivNm(getCellValue(row.getCell(5))); // 조사 구분
					if (ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(6)))) {
						trfIpcssNbopl.setExmnYmd(GgitsCommonUtils.extractDateWithComma(getCellValue(row.getCell(6))));	// 현황조사 일자						
					}
					if (ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(7)))) {
						trfIpcssNbopl.setExmnDocNm(getCellValue(row.getCell(7))); // 문헌조사(참고문헌)
					}
					trfIpcssNbopl.setUsgNo(String.valueOf(usgNo));

					// 용도 명칭 cell은 반복 제외
					if (cellIndex == 8 || cellIndex == 13 || cellIndex == 18 || cellIndex == 23 || cellIndex == 28
							|| cellIndex == 33 || cellIndex == 38 || cellIndex == 43 || cellIndex == 48
							|| cellIndex == 53 || cellIndex == 58 || cellIndex == 63 || cellIndex == 68
							|| cellIndex == 73 || cellIndex == 78 || cellIndex == 83 || cellIndex == 88
							|| cellIndex == 93 || cellIndex == 98 || cellIndex == 103) {
						usgCdNum = cellIndex;
						continue;
					}
					String usgCdStr = getCellValue(row.getCell(usgCdNum));
					String usgCd = ExcelUploadUtil.findUsgCd(usgCdStr, usgCdList);

					// 교통 사용 코드
					String trfUseCdStr = trfUseCdRowList.get(cellIndex - trfUseCdNum);
					String trfUseCd = ExcelUploadUtil.findUsgCd(trfUseCdStr, trfUseCdList);

					// 교통 수단 코드
					String trfMeanVhcclsStr = trfMeanVhcclsRowList.get(cellIndex);
					trfMeanVhcclsStr = trfMeanVhcclsStr.replaceAll("\n", "");
					String trfMeanVhccls = ExcelUploadUtil.findUsgCd(trfMeanVhcclsStr, trfMeanVhcclsList);

					// 용도 명칭이 비주거용도가 아닌 경우 Exception
					if (!ExcelUploadUtil.checkUsgCd(usgCd, "nonDwell")) {
						throw new CommonException(ErrorCode.PARAMETER_DATA_INVALID, "셀의 정보가 올바르지 않습니다.");
					}

					trfIpcssNbopl.setNboplNo(String.valueOf(nboplNo));
					trfIpcssNbopl.setUsgCd(usgCd); // 용도 코드
					trfIpcssNbopl.setTrfUseCd(trfUseCd); // 교통 사용 코드
					trfIpcssNbopl.setTrfMeanVhccls(trfMeanVhccls); // 교통 수단 코드
					trfIpcssNbopl.setNboplCnt(Double.parseDouble(getCellValue(row.getCell(cellIndex))));// 재차인원 수
					resultList.add(trfIpcssNbopl);
					trfUseCdNum++;
					usgNo++;
					if (trfUseCdNum == 2) {
						trfUseCdNum = 0;
					}
				}
			}
		}
		return resultList;
	}

	/**
	 * @Method Name : getTimeInoutflExmnDataList
	 * @작성일 : 2023. 11. 15.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 시간대별 유출입 통행분포 조사
	 * @param : trfIpcssExmnBizInfo
	 * @param : trfIpcssTimeInoutflExmn
	 * @param : rowIndex
	 * @param : row
	 * @return :
	 */
	private List<TrfIpcssTimeInoutflExmn> getTimeInoutflExmnDataList(TrfIpcssExmnBizInfo trfIpcssExmnBizInfo,
			TrfIpcssTimeInoutflExmn trfIpcssTimeInoutflExmn, int rowIndex, XSSFRow row) {
		List<TrfIpcssTimeInoutflExmn> resultList = new ArrayList<TrfIpcssTimeInoutflExmn>();
		int lastCellNum = row.getLastCellNum();
		int termNum = 0;
		for (int cellIndex = 6; cellIndex <= lastCellNum; cellIndex++) {
			TrfIpcssTimeInoutflExmn timeInoutflExmn = ExcelUploadUtil
					.getTimeInoutflExmnForUsgNoAndUsgNm(trfIpcssTimeInoutflExmn, cellIndex); // 용도 번호, 용도 명, 활동인구 조사 유형
			if (!ExcelUploadUtil.checkCellValue(timeInoutflExmn.getUsgNm())
					|| timeInoutflExmn.getUsgNm().equals("(작성하세요)")) {
				continue;
			}
			if ((cellIndex % 6) == 0 && (cellIndex - termNum) / 6 == 1) {
				// 활동인구(인)
				timeInoutflExmn.setActPopltnExmnType("1");
				timeInoutflExmn.setDywkDiv(trfIpcssTimeInoutflExmn.getDywkDiv()); // 요일 구분
				timeInoutflExmn.setExmnYmd(trfIpcssExmnBizInfo.getExmnDd().split("~")[0]);
				String resdngInfl = getCellValue(row.getCell(cellIndex - 5));
				if (!ExcelUploadUtil.checkCellValue(resdngInfl))
					resdngInfl = "0.0";
				String resdngOutfl = getCellValue(row.getCell(cellIndex - 4));
				if (!ExcelUploadUtil.checkCellValue(resdngOutfl))
					resdngOutfl = "0.0";
				String visitInfl = getCellValue(row.getCell(cellIndex - 3));
				if (!ExcelUploadUtil.checkCellValue(visitInfl))
					visitInfl = "0.0";
				String visitOutfl = getCellValue(row.getCell(cellIndex - 2));
				if (!ExcelUploadUtil.checkCellValue(visitOutfl))
					visitOutfl = "0.0";
				double totInfl = Double.parseDouble(resdngInfl) + Double.parseDouble(visitInfl);
				double totOutfl = Double.parseDouble(resdngOutfl) + Double.parseDouble(visitOutfl);

				timeInoutflExmn.setTimeSctnNm(getCellValue(row.getCell(0)));
				timeInoutflExmn.setResdngInfl(Double.parseDouble(resdngInfl));
				timeInoutflExmn.setResdngOutfl(Double.parseDouble(resdngOutfl));
				timeInoutflExmn.setVisitInfl(Double.parseDouble(visitInfl));
				timeInoutflExmn.setVisitOutfl(Double.parseDouble(visitOutfl));
				timeInoutflExmn.setTotInfl(totInfl);
				timeInoutflExmn.setTotOutfl(totOutfl);

				resultList.add(timeInoutflExmn);

			} else if ((cellIndex % 6) == 0 && (cellIndex - termNum) / 6 == 2) {
				// 활동인구(비율)
				timeInoutflExmn.setActPopltnExmnType("2");
				timeInoutflExmn.setDywkDiv(trfIpcssTimeInoutflExmn.getDywkDiv()); // 요일 구분
				timeInoutflExmn.setExmnYmd(trfIpcssExmnBizInfo.getExmnDd().split("~")[0]);
				String resdngInfl = getCellValue(row.getCell(cellIndex - 11));
				if (!ExcelUploadUtil.checkCellValue(resdngInfl))
					resdngInfl = "0.0";
				String resdngOutfl = getCellValue(row.getCell(cellIndex - 10));
				if (!ExcelUploadUtil.checkCellValue(resdngOutfl))
					resdngOutfl = "0.0";
				String visitInfl = getCellValue(row.getCell(cellIndex - 9));
				if (!ExcelUploadUtil.checkCellValue(visitInfl))
					visitInfl = "0.0";
				String visitOutfl = getCellValue(row.getCell(cellIndex - 8));
				if (!ExcelUploadUtil.checkCellValue(visitOutfl))
					visitOutfl = "0.0";
				double totInfl = Double.parseDouble(resdngInfl) + Double.parseDouble(visitInfl);
				double totOutfl = Double.parseDouble(resdngOutfl) + Double.parseDouble(visitOutfl);
				double resdngInflRt = Double.parseDouble(resdngInfl) / totInfl * 100;
				double resdngOutflRt = Double.parseDouble(resdngOutfl) / totOutfl * 100;
				double visitInflRt = Double.parseDouble(visitInfl) / totInfl * 100;
				double visitOutflRt = Double.parseDouble(visitOutfl) / totOutfl * 100;
				double totInflRt = resdngInflRt + visitInflRt;
				double totOutflRt = resdngOutflRt + visitOutflRt;

				timeInoutflExmn.setTimeSctnNm(getCellValue(row.getCell(0)));
				if (!resdngInfl.equals("false") && !Double.isNaN(resdngInflRt))
					timeInoutflExmn.setResdngInfl(resdngInflRt);
				if (!resdngOutfl.equals("false") && !Double.isNaN(resdngOutflRt))
					timeInoutflExmn.setResdngOutfl(resdngOutflRt);
				if (!visitInfl.equals("false") && !Double.isNaN(visitInflRt))
					timeInoutflExmn.setVisitInfl(visitInflRt);
				if (!visitOutfl.equals("false") && !Double.isNaN(visitOutflRt))
					timeInoutflExmn.setVisitOutfl(visitOutflRt);
				if (!Double.isNaN(totInflRt))
					timeInoutflExmn.setTotInfl(totInflRt);
				if (!Double.isNaN(totOutflRt))
					timeInoutflExmn.setTotOutfl(totOutflRt);

				resultList.add(timeInoutflExmn);
				termNum += 12;
			}
		}
		return resultList;
	}

	/**
	 * @Method Name : getParkngOccurBsuntDataList
	 * @작성일 : 2023. 11. 15.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 주차발생 원단위 조사
	 * @param : rowIndex
	 * @param : row
	 * @param : usgCdList
	 * @return :
	 */
	private List<TrfIpcssParkngOccurBsunt> getParkngOccurBsuntDataList(int rowIndex, XSSFRow row,
			List<MOpCode> usgCdList, int parkngOccurNo) {
		List<TrfIpcssParkngOccurBsunt> resultList = new ArrayList<TrfIpcssParkngOccurBsunt>();
		String startRowValue = getCellValue(row.getCell(1));
		if (ExcelUploadUtil.checkCellValue(startRowValue)) {
			int lastCellNum = row.getLastCellNum();
			int termNum = 0;
			int usgNo = 1;
			for (int cellIndex = 12; cellIndex <= lastCellNum; cellIndex++) {
				String partValue = getCellValue(row.getCell(cellIndex));
				if (ExcelUploadUtil.checkCellValue(partValue)) {
					TrfIpcssParkngOccurBsunt trfIpcssParkngOccurBsunt = new TrfIpcssParkngOccurBsunt();
					// 로우의 기초 데이터 세팅
					trfIpcssParkngOccurBsunt.setSmlfactNm(getCellValue(row.getCell(1))); // 유사시설 명칭
					trfIpcssParkngOccurBsunt.setSmlfactAddr(getCellValue(row.getCell(2))); // 유사시설 주소
					trfIpcssParkngOccurBsunt.setScale(getCellValue(row.getCell(3))); // 규모
					String totrar = getCellValue(row.getCell(4)) == null ? (String) "0" : getCellValue(row.getCell(4));
					trfIpcssParkngOccurBsunt.setTotfrar(Double.parseDouble(totrar)); // 전체 연면적
					trfIpcssParkngOccurBsunt.setExmnDivNm(getCellValue(row.getCell(5))); // 조사 구분
					if (ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(6)))) {
						trfIpcssParkngOccurBsunt.setExmnYmd(GgitsCommonUtils.extractDateWithComma(getCellValue(row.getCell(6))));	// 현황조사 일자						
					}
					if (ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(7)))) {
						trfIpcssParkngOccurBsunt.setExmnDocNm(getCellValue(row.getCell(7))); // 문헌조사(참고문헌)
					}
					trfIpcssParkngOccurBsunt.setUsgNo(String.valueOf(usgNo));
					if (cellIndex == 12 || (cellIndex - termNum) == 12) {
						String usgCdStr = getCellValue(row.getCell(cellIndex - 4));
						String usgCd = ExcelUploadUtil.findUsgCd(usgCdStr, usgCdList);

						trfIpcssParkngOccurBsunt.setParkngOccurNo(String.valueOf(parkngOccurNo));
						trfIpcssParkngOccurBsunt.setUsgCd(usgCd); // 용도 명칭
						trfIpcssParkngOccurBsunt.setUsgTotfrar(getCellValue(row.getCell(cellIndex - 3))); // 용도별 연면적
						String resdngBsunt = GgitsCommonUtils.isNull(getCellValue(row.getCell(cellIndex - 2)))
								? (String) "0"
								: getCellValue(row.getCell(cellIndex - 2));
						trfIpcssParkngOccurBsunt.setWkdayBsunt(Double.parseDouble(resdngBsunt)); // 상주/상근
						String visitBsunt = GgitsCommonUtils.isNull(getCellValue(row.getCell(cellIndex - 1)))
								? (String) "0"
								: getCellValue(row.getCell(cellIndex - 1));
						trfIpcssParkngOccurBsunt.setWkendBsunt(Double.parseDouble(visitBsunt)); // 방문/이용
						trfIpcssParkngOccurBsunt.setBsuntUnit(getCellValue(row.getCell(cellIndex))); // 단위
						resultList.add(trfIpcssParkngOccurBsunt);
						termNum += 5;
						usgNo++;
					}
				}
			}
		}
		return resultList;
	}

	/**
	 * @Method Name : getTimePassDistrbDataList
	 * @작성일 : 2023. 11. 15.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 시간대별 통행 분포비
	 * @param paramDto
	 * @param :        rowIndex
	 * @param :        row
	 * @param :        usgCdList
	 * @return :
	 */
	private List<TrfIpcssTimePassDistrb> getTimePassDistrbDataList(TrfIpcssTimePassDistrb paramDto, int rowIndex,
			XSSFRow row, List<MOpCode> usgCdList) {
		List<TrfIpcssTimePassDistrb> resultList = new ArrayList<TrfIpcssTimePassDistrb>();
		int lastCellNum = row.getLastCellNum();

		int dewllTermNum = 0;
		int nonDwellTermNum = 0;
		int startDwellNum = 2;
		int startNonDwellNum = 11;
		int timeSctnCellNum = 0;
		int trfUseCdTermNum = 6;
		int usgNo = 2;
		String trfUseCd = "5";
		for (int cellIndex = 0; cellIndex <= lastCellNum; cellIndex++) {

			// 시간구간명 cell은 반복 제외
			if (cellIndex == 14 || cellIndex == 19 || cellIndex == 24 || cellIndex == 29 || cellIndex == 34
					|| cellIndex == 39 || cellIndex == 44 || cellIndex == 49 || cellIndex == 54 || cellIndex == 59
					|| cellIndex == 64 || cellIndex == 69 || cellIndex == 74 || cellIndex == 79 || cellIndex == 84
					|| cellIndex == 94 || cellIndex == 99 || cellIndex == 104) {
				nonDwellTermNum += 3;
				trfUseCdTermNum += 3;
				timeSctnCellNum = cellIndex;
				continue;
			}

			TrfIpcssTimePassDistrb trfIpcssTimePassDistrb = new TrfIpcssTimePassDistrb();
			trfIpcssTimePassDistrb.setBizNm(paramDto.getBizNm());
			trfIpcssTimePassDistrb.setBizUsg(paramDto.getBizUsg());
			// 요일구분
			trfIpcssTimePassDistrb.setDywkDiv(paramDto.getDywkDiv());
			// 시간 구간명
			trfIpcssTimePassDistrb.setTimeSctnNm(getCellValue(row.getCell(timeSctnCellNum)));

			if (cellIndex >= 0 && cellIndex <= 8) {
				if (cellIndex == 2 || (startDwellNum + dewllTermNum) == cellIndex) {
					// 주거 용도 파트 데이터 세팅
					// 용도 코드
					trfIpcssTimePassDistrb.setUsgCd("001");
					// 교통 사용 코드(통근:1,통학:2,기타:3,방문:4)
					trfIpcssTimePassDistrb.setTrfUseCd(String.valueOf(cellIndex / 2));
					trfIpcssTimePassDistrb.setUsgNo("1");

					String inflRtStr = getCellValue(row.getCell(cellIndex - 1));
					String outFlRtStr = getCellValue(row.getCell(cellIndex));

					if (!ExcelUploadUtil.checkCellValue(inflRtStr)) {
						inflRtStr = "0.0";
					}
					if (!ExcelUploadUtil.checkCellValue(outFlRtStr)) {
						outFlRtStr = "0.0";
					}

					trfIpcssTimePassDistrb.setInflRt(Double.parseDouble(inflRtStr));
					trfIpcssTimePassDistrb.setOutflRt(Double.parseDouble(outFlRtStr));
					resultList.add(trfIpcssTimePassDistrb);
					dewllTermNum += 2;
				}
			} else if (cellIndex >= 9) {
				if (cellIndex == 11 || (startNonDwellNum + nonDwellTermNum) == cellIndex) {
					// 비주거 용도 파트 데이터 세팅
					// 용도 코드
					int usgCdCellNum = timeSctnCellNum == 0 ? 9 : timeSctnCellNum;
					String usgCdStr = paramDto.getUsgCdList().get(usgCdCellNum);
					String usgCd = ExcelUploadUtil.findUsgCd(usgCdStr, usgCdList);

					if (!GgitsCommonUtils.isNull(usgCd)) {
						trfIpcssTimePassDistrb.setUsgCd(usgCd);
						// 교통 사용 코드(상근:5, 이용:6)
						trfIpcssTimePassDistrb.setTrfUseCd(trfUseCd);
						trfIpcssTimePassDistrb.setUsgNo(String.valueOf(usgNo));

						String inflRtStr = getCellValue(row.getCell(cellIndex - 1));
						String outFlRtStr = getCellValue(row.getCell(cellIndex));
						if (!ExcelUploadUtil.checkCellValue(inflRtStr)) {
							inflRtStr = "0.0";
						}
						if (!ExcelUploadUtil.checkCellValue(outFlRtStr)) {
							outFlRtStr = "0.0";
						}
						trfIpcssTimePassDistrb.setInflRt(Double.parseDouble(inflRtStr));
						trfIpcssTimePassDistrb.setOutflRt(Double.parseDouble(outFlRtStr));
						resultList.add(trfIpcssTimePassDistrb);
						nonDwellTermNum += 2;
						trfUseCdTermNum += 2;
						usgNo++;
						if (trfUseCd.equals("5")) {
							trfUseCd = "6";
						} else if (trfUseCd.equals("6")) {
							trfUseCd = "5";
						}
					}
				}
			}
		}
		return resultList;
	}

	private String getCellValue(XSSFCell cell) {
		String value = "";
		if (cell == null) {

		} else {
			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_FORMULA:
				value = cell.getRawValue();
				break;
			case XSSFCell.CELL_TYPE_NUMERIC:
				double cellValue = cell.getNumericCellValue();
				if (cellValue == Math.rint(cellValue)) {
					value = String.valueOf((int) cellValue);
				} else {
					value = String.valueOf(cellValue);
				}
				break;
			case XSSFCell.CELL_TYPE_STRING:
				value = cell.getStringCellValue() + "";
				break;
			case XSSFCell.CELL_TYPE_BLANK:
				value = cell.getBooleanCellValue() + "";
				break;
			case XSSFCell.CELL_TYPE_ERROR:
				value = cell.getErrorCellValue() + "";
				break;
			default:
				break;
			}
		}
		return value;
	}
	
	/**
	 * @param type 
	 * @Method Name : updateTrafficImpactReport
	 * @작성일 : 2023. 11. 30.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 수정
	 * @param : file
	 * @param : ipcssMngNo
	 * @return :
	 */
	@Transactional
	public void updateTrafficImpactReport(MultipartFile file, String ipcssMngNo, String type) {
		XSSFWorkbook workbook = null;
		String errorSheet = "";
		int errorRow = 0;
		try {
			workbook = new XSSFWorkbook(file.getInputStream());

			// 용도 코드 리스트
			List<MOpCode> usgCdList = new ArrayList<MOpCode>();
			usgCdList = mOpCodeMapper.findAllCodeListByGrpCdId("USG_CD");

			// 교통 수단 리스트
			List<MOpCode> trfUseCdList = new ArrayList<MOpCode>();
			trfUseCdList = mOpCodeMapper.findAllCodeListByGrpCdId("TRF_USE_CD");

			// 교통 사용 리스트
			List<MOpCode> trfMeanVhcclsList = new ArrayList<MOpCode>();
			trfMeanVhcclsList = mOpCodeMapper.findAllCodeListByGrpCdId("TRF_MEAN_VHCCLS");
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			int sheetRowSize = sheet.getPhysicalNumberOfRows();
			
			String ipcssMngNoStr = ipcssMngNo;
			
			TrfIpcssExmnBizInfo trfIpcssExmnBizInfo = new TrfIpcssExmnBizInfo();
			trfIpcssExmnBizInfo = trfIpcssExmnBizInfoMapper.findOneExmnBizInfoByipcssMngNo(ipcssMngNoStr);
			
			if(type.equals("crossroad_by_traffic_weekday")) {
				// 평일 교차로별 교통량(TRF_IPCSS_CRSRD_TRFVLM)
				errorSheet = "평일 교차로별 교통량";
				List<TrfIpcssCrsrdTrfvlm> crsrdTrfvlmListForWeekday = new ArrayList<TrfIpcssCrsrdTrfvlm>();
				TrfIpcssCrsrdTrfvlm trfIpcssCrsrdTrfvlm = new TrfIpcssCrsrdTrfvlm();
				String exmnYmdStr = "";
				int crsrdRowNum = 4;
				int startHeaderRow = 3;
				int endHeaderRow = 5;
				int timeSctrnNum = 0;
				for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
					errorRow = rowIndex;
					XSSFRow row = sheet.getRow(rowIndex);
					if (row == null)
						continue;
					
					if(rowIndex == 2) {
						exmnYmdStr = getCellValue(row.getCell(4));
					}
					
					// 교차로명
					if (crsrdRowNum == rowIndex) {
						trfIpcssCrsrdTrfvlm.setCrsrdNo(getCellValue(row.getCell(0)));
						trfIpcssCrsrdTrfvlm.setCrsrdNm(getCellValue(row.getCell(1)));
						// 위도 
						String latStr = getCellValue(row.getCell(2));
						if(!GgitsCommonUtils.isNull(latStr) && GgitsCommonUtils.isDouble(latStr)) {
							trfIpcssCrsrdTrfvlm.setPointLat(Double.parseDouble(latStr));									
						}
						// 경도
						String lonStr = getCellValue(row.getCell(3));
						if(!GgitsCommonUtils.isNull(lonStr) && GgitsCommonUtils.isDouble(lonStr)) {
							trfIpcssCrsrdTrfvlm.setPointLon(Double.parseDouble(lonStr));									
						}
						crsrdRowNum += 23;
					}

					// 불필요 로우 제거(header 로우)
					if (startHeaderRow <= rowIndex && endHeaderRow >= rowIndex) {
						startHeaderRow += 23;
						endHeaderRow += 23;
						continue;
					}

					String timeSctrnNm = getCellValue(row.getCell(4));
					if (GgitsCommonUtils.chkContainString(timeSctrnNm)
							&& !ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(0)))) {
						// 합계일 경우
						timeSctrnNum++;

						if (timeSctrnNum == 4) {
							timeSctrnNum = 0;
						}
					}

					// row 데이터를 넘겨서 리스트객체 반환
					List<TrfIpcssCrsrdTrfvlm> dataList = new ArrayList<TrfIpcssCrsrdTrfvlm>();
					dataList = getCrsrdTrfvlmDataList(rowIndex, row, trfIpcssCrsrdTrfvlm, timeSctrnNum,
							2, exmnYmdStr);

					// 전체 데이터 리스트에 추가
					crsrdTrfvlmListForWeekday = Stream.concat(crsrdTrfvlmListForWeekday.stream(), dataList.stream())
							.collect(Collectors.toList());
				}
				if (!crsrdTrfvlmListForWeekday.isEmpty()) {
					TrfvlmStatisticsDTO trfvlmStatisticsDTO = new TrfvlmStatisticsDTO();
					trfvlmStatisticsDTO.setIpcssMngNo(ipcssMngNoStr);
					trfvlmStatisticsDTO.setDywkDiv("평일");
					trfIpcssCrsrdTrfvlmMapper.deleteTrafficImpactReportForDywkDiv(trfvlmStatisticsDTO);
					for (TrfIpcssCrsrdTrfvlm crsrdTrfvlm : crsrdTrfvlmListForWeekday) {
						crsrdTrfvlm.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssCrsrdTrfvlmMapper.saveCrsrdTrfvlm(crsrdTrfvlm);
					}
				}
			}else if(type.equals("crossroad_by_traffic_weekend")) {
				// 주말 교차로별 교통량(TRF_IPCSS_CRSRD_TRFVLM)
				errorSheet = "주말 교차로별 교통량";
				List<TrfIpcssCrsrdTrfvlm> crsrdTrfvlmListForWeekend = new ArrayList<TrfIpcssCrsrdTrfvlm>();
				TrfIpcssCrsrdTrfvlm trfIpcssCrsrdTrfvlm = new TrfIpcssCrsrdTrfvlm();
				String exmnYmdStr = "";
				int crsrdRowNum = 4;
				int startHeaderRow = 3;
				int endHeaderRow = 5;
				int timeSctrnNum = 0;
				for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
					errorRow = rowIndex;
					XSSFRow row = sheet.getRow(rowIndex);
					if (row == null)
						continue;
					
					if(rowIndex == 2) {
						exmnYmdStr = getCellValue(row.getCell(2));
					}
					
					// 교차로명
					if (crsrdRowNum == rowIndex) {
						trfIpcssCrsrdTrfvlm.setCrsrdNo(getCellValue(row.getCell(0)));
						trfIpcssCrsrdTrfvlm.setCrsrdNm(getCellValue(row.getCell(1)));
						// 위도 
						trfIpcssCrsrdTrfvlm.setPointLat(Double.parseDouble(getCellValue(row.getCell(2))));

						// 경도
						trfIpcssCrsrdTrfvlm.setPointLon(Double.parseDouble(getCellValue(row.getCell(3))));
						crsrdRowNum += 23;
					}

					// 불필요 로우 제거(header 로우)
					if (startHeaderRow <= rowIndex && endHeaderRow >= rowIndex) {
						startHeaderRow += 23;
						endHeaderRow += 23;
						continue;
					}

					String timeSctrnNm = getCellValue(row.getCell(2));
					if (GgitsCommonUtils.chkContainString(timeSctrnNm)
							&& !ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(0)))) {
						// 합계일 경우
						timeSctrnNum++;

						if (timeSctrnNum == 4) {
							timeSctrnNum = 0;
						}
					}

					// row 데이터를 넘겨서 리스트객체 반환
					List<TrfIpcssCrsrdTrfvlm> dataList = new ArrayList<TrfIpcssCrsrdTrfvlm>();
					dataList = getCrsrdTrfvlmDataList(rowIndex, row, trfIpcssCrsrdTrfvlm, timeSctrnNum,
							3, exmnYmdStr);

					// 전체 데이터 리스트에 추가
					crsrdTrfvlmListForWeekend = Stream.concat(crsrdTrfvlmListForWeekend.stream(), dataList.stream())
							.collect(Collectors.toList());
				}
				if (!crsrdTrfvlmListForWeekend.isEmpty()) {
					TrfvlmStatisticsDTO trfvlmStatisticsDTO = new TrfvlmStatisticsDTO();
					trfvlmStatisticsDTO.setIpcssMngNo(ipcssMngNoStr);
					trfvlmStatisticsDTO.setDywkDiv("주말");
					trfIpcssCrsrdTrfvlmMapper.deleteTrafficImpactReportForDywkDiv(trfvlmStatisticsDTO);
					for (TrfIpcssCrsrdTrfvlm crsrdTrfvlm : crsrdTrfvlmListForWeekend) {
						crsrdTrfvlm.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssCrsrdTrfvlmMapper.saveCrsrdTrfvlm(crsrdTrfvlm);
					}
				}
			}else if(type.equals("section_by_traffic_weekday")) {
				// 평일 구간별 교통량(TRF_IPCSS_STRSCT_TRFVLM)
				errorSheet = "평일 가로구간 교통량";
				List<TrfIpcssStrsctTrfvlm> strsctTrfvlmListForWeekday = new ArrayList<TrfIpcssStrsctTrfvlm>();
				String strsctNm = "";
				String forword = "";
				String backword = "";
				String exmnYmd = "";
				List<String> directList = new ArrayList<String>();
				int directTermNum = 3;
				int strsctTermNum = 4;
				int skipTermNum = 5;
				int timeSctrnNum = 0;
				String strsctNo = "";
				for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
					errorRow = rowIndex;
					XSSFRow row = sheet.getRow(rowIndex);
					if (row == null)
						continue;
					
					if(rowIndex == 2) {
						exmnYmd = getCellValue(row.getCell(2));
					}
					
					// 가로 구간 방향명
					if (rowIndex == 3 || directTermNum == rowIndex) {
						directList = getRowValueToList(row, true);
						forword = directList.get(3);
						backword = directList.get(4);
						directTermNum += 23;
						continue;
					}
					// 가로 구간 명칭
					if (rowIndex == 4 || strsctTermNum == rowIndex) {
						List<String> strsctNmList = getRowValueToList(row, false);
						strsctNm = strsctNmList.get(1);
						strsctNo = getCellValue(row.getCell(0));
						strsctTermNum += 23;
						continue;
					}
					// 불필요 로우
					if (rowIndex == 5 || skipTermNum == rowIndex) {
						skipTermNum += 23;
						continue;
					}
					// row 데이터를 넘겨서 리스트객체 반환
					List<TrfIpcssStrsctTrfvlm> dataList = new ArrayList<TrfIpcssStrsctTrfvlm>();
					dataList = getStrsctTrfvlmDataList(rowIndex, row, strsctNm, forword, backword, timeSctrnNum,
							4, strsctNo, exmnYmd);

					String timeSctrnNm = getCellValue(row.getCell(2));
					if (GgitsCommonUtils.chkContainString(timeSctrnNm)
							&& !ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(0)))) {
						// 합계일 경우
						timeSctrnNum++;
						if (timeSctrnNum == 4) {
							timeSctrnNum = 0;
						}
					}

					// 전체 데이터 리스트에 추가
					strsctTrfvlmListForWeekday = Stream.concat(strsctTrfvlmListForWeekday.stream(), dataList.stream())
							.collect(Collectors.toList());
				}
				if (!strsctTrfvlmListForWeekday.isEmpty()) {
					TrfvlmStatisticsDTO trfvlmStatisticsDTO = new TrfvlmStatisticsDTO();
					trfvlmStatisticsDTO.setIpcssMngNo(ipcssMngNoStr);
					trfvlmStatisticsDTO.setDywkDiv("평일");
					trfIpcssStrsctTrfvlmMapper.deleteTrafficImpactReportForDywkDiv(trfvlmStatisticsDTO);
					
					for (TrfIpcssStrsctTrfvlm strsctTrfvlm : strsctTrfvlmListForWeekday) {
						strsctTrfvlm.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssStrsctTrfvlmMapper.saveStrsctTrfvlm(strsctTrfvlm);
					}
				}
			}else if(type.equals("section_by_traffic_weekend")) {
				// 주말 구간별 교통량(TRF_IPCSS_STRSCT_TRFVLM)
				errorSheet = "주말 가로구간 교통량";
				List<TrfIpcssStrsctTrfvlm> strsctTrfvlmListForWeekend = new ArrayList<TrfIpcssStrsctTrfvlm>();
				String strsctNm = "";
				String forword = "";
				String backword = "";
				String exmnYmd = "";
				List<String> directList = new ArrayList<String>();
				int directTermNum = 3;
				int strsctTermNum = 4;
				int skipTermNum = 5;
				int timeSctrnNum = 0;
				String strsctNo = "";
				for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
					errorRow = rowIndex;
					XSSFRow row = sheet.getRow(rowIndex);
					if (row == null)
						continue;
					
					if(rowIndex == 2) {
						exmnYmd = getCellValue(row.getCell(2));
					}
					
					// 가로 구간 방향명
					if (rowIndex == 3 || directTermNum == rowIndex) {
						directList = getRowValueToList(row, true);
						forword = directList.get(3);
						backword = directList.get(4);
						directTermNum += 23;
						continue;
					}
					// 가로 구간 명칭
					if (rowIndex == 4 || strsctTermNum == rowIndex) {
						List<String> strsctNmList = getRowValueToList(row, false);
						strsctNm = strsctNmList.get(1);
						strsctNo = getCellValue(row.getCell(0));
						strsctTermNum += 23;
						continue;
					}
					// 불필요 로우
					if (rowIndex == 5 || skipTermNum == rowIndex) {
						skipTermNum += 23;
						continue;
					}
					// row 데이터를 넘겨서 리스트객체 반환
					List<TrfIpcssStrsctTrfvlm> dataList = new ArrayList<TrfIpcssStrsctTrfvlm>();
					dataList = getStrsctTrfvlmDataList(rowIndex, row, strsctNm, forword, backword, timeSctrnNum,
							5, strsctNo, exmnYmd);

					String timeSctrnNm = getCellValue(row.getCell(2));
					if (GgitsCommonUtils.chkContainString(timeSctrnNm)
							&& !ExcelUploadUtil.checkCellValue(getCellValue(row.getCell(0)))) {
						// 합계일 경우
						timeSctrnNum++;
						if (timeSctrnNum == 4) {
							timeSctrnNum = 0;
						}
					}

					// 전체 데이터 리스트에 추가
					strsctTrfvlmListForWeekend = Stream.concat(strsctTrfvlmListForWeekend.stream(), dataList.stream())
							.collect(Collectors.toList());
				}
				if (!strsctTrfvlmListForWeekend.isEmpty()) {
					TrfvlmStatisticsDTO trfvlmStatisticsDTO = new TrfvlmStatisticsDTO();
					trfvlmStatisticsDTO.setIpcssMngNo(ipcssMngNoStr);
					trfvlmStatisticsDTO.setDywkDiv("주말");
					trfIpcssStrsctTrfvlmMapper.deleteTrafficImpactReportForDywkDiv(trfvlmStatisticsDTO);
					
					for (TrfIpcssStrsctTrfvlm strsctTrfvlm : strsctTrfvlmListForWeekend) {
						strsctTrfvlm.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssStrsctTrfvlmMapper.saveStrsctTrfvlm(strsctTrfvlm);
					}
				}
			}else if(type.equals("mean_by_traffic")) {
				// 이용수단별(TRF_IPCSS_ETC_TRFVLM)
				errorSheet = "보행, 자전거, PM 교통량";
				List<TrfIpcssEtcTrfvlm> etcTrfvlmList = new ArrayList<TrfIpcssEtcTrfvlm>();
				String exmnYmdStr = "";
				String dywkDiv = "";
				List<String> pointNmList = new ArrayList<String>();
				for (int rowIndex = 0; rowIndex <= sheetRowSize; rowIndex++) {
					errorRow = rowIndex;
					XSSFRow row = sheet.getRow(rowIndex);
					if (row == null)
						continue;

					// 요일 구분
					if (rowIndex == 0 || rowIndex == 26) {
						dywkDiv = getCellValue(row.getCell(0));
					}

					if (rowIndex == 2 || rowIndex == 28) {
						exmnYmdStr = getCellValue(row.getCell(1));
						continue;
					}
					// 지점 로우
					if (rowIndex == 3 || rowIndex == 29) {
						pointNmList = getRowValueToList(row, false);
						continue;
					}

					// row 데이터를 넘겨서 리스트객체 반환
					List<TrfIpcssEtcTrfvlm> dataList = new ArrayList<TrfIpcssEtcTrfvlm>();
					if ((rowIndex >= 5 && rowIndex <= 24) || (rowIndex >= 31 && rowIndex <= 50)) {
						dataList = getEtcTrfvlmDataList(rowIndex, row, exmnYmdStr, pointNmList, dywkDiv);
					}

					// 전체 데이터 리스트에 추가
					etcTrfvlmList = Stream.concat(etcTrfvlmList.stream(), dataList.stream())
							.collect(Collectors.toList());
				}
				if (!etcTrfvlmList.isEmpty()) {
					trfIpcssEtcTrfvlmMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNoStr);
					for (TrfIpcssEtcTrfvlm etcTrfvlm : etcTrfvlmList) {
						etcTrfvlm.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssEtcTrfvlmMapper.saveEtcTrfvlm(etcTrfvlm);
					}
				}
			}else if(type.equals("similar_facilities_by_floating_population")) {
				// 유사시설별 유동인구(TRF_IPCSS_ACT_POPLTN_BSUNT)
				errorSheet = "유사시설별 유동인구 원단위";
				List<TrfIpcssActPopltnBsunt> actPopltnBsuntList = new ArrayList<TrfIpcssActPopltnBsunt>();
				int actPopltnNo = 1;
				for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
					errorRow = rowIndex;
					XSSFRow row = sheet.getRow(rowIndex);
					if (rowIndex >= 0 && rowIndex <= 3 || row == null)
						continue;

					List<TrfIpcssActPopltnBsunt> dataList = new ArrayList<TrfIpcssActPopltnBsunt>();
					dataList = getActPopltnBsuntDataList(rowIndex, row, usgCdList, actPopltnNo);

					actPopltnBsuntList = Stream.concat(actPopltnBsuntList.stream(), dataList.stream())
							.collect(Collectors.toList());
					actPopltnNo++;
				}
				if (!actPopltnBsuntList.isEmpty()) {
					trfIpcssActPopltnBsuntMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNoStr);
					for (TrfIpcssActPopltnBsunt actPopltnBsunt : actPopltnBsuntList) {
						actPopltnBsunt.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssActPopltnBsuntMapper.saveActPopltnBsunt(actPopltnBsunt);
					}
				}
			}else if(type.equals("use_by_traffic_mean_share_rate_dwell")) {
				// 주거용도 교통수단 분담률(TRF_IPCSS_MEAN_SHARE_RT)
				errorSheet = "주거용도 교통수단 분담률";
				List<TrfIpcssMeanShareRt> meanShareRtListForDwell = new ArrayList<TrfIpcssMeanShareRt>();
				List<String> trfUseCdRowListForDwell = new ArrayList<String>(); // 교통 사용 코드
				List<String> trfMeanVhcclsRowListForDwell = new ArrayList<String>(); // 교통 용도 코드
				int meanShareNoForDwell = 1;
				for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
					errorRow = rowIndex;
					XSSFRow row = sheet.getRow(rowIndex);
					if (rowIndex == 4) {
						trfUseCdRowListForDwell = getRowValueToList(row, false);
					}

					if (rowIndex == 5) {
						trfMeanVhcclsRowListForDwell = getRowValueToList(row, false);
					}
					if (rowIndex >= 0 && rowIndex <= 5 || row == null)
						continue;

					List<TrfIpcssMeanShareRt> dataList = new ArrayList<TrfIpcssMeanShareRt>();
					dataList = getMeanShareRtDataListForDwell(rowIndex, row, usgCdList, trfUseCdRowListForDwell,
							trfMeanVhcclsRowListForDwell, trfUseCdList, trfMeanVhcclsList, meanShareNoForDwell);

					meanShareRtListForDwell = Stream.concat(meanShareRtListForDwell.stream(), dataList.stream())
							.collect(Collectors.toList());
					meanShareNoForDwell++;
				}
				if (!meanShareRtListForDwell.isEmpty()) {
					TrfvlmStatisticsDTO trfvlmStatisticsDTO = new TrfvlmStatisticsDTO();
					trfvlmStatisticsDTO.setIpcssMngNo(ipcssMngNoStr);
					trfvlmStatisticsDTO.setDwellYn("Y");
					trfIpcssMeanShareRtMapper.deleteTrafficImpactReportForUsg(trfvlmStatisticsDTO);
					for (TrfIpcssMeanShareRt meanShareRt : meanShareRtListForDwell) {
						meanShareRt.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssMeanShareRtMapper.saveMeanShareRt(meanShareRt);
					}
				}
			}else if(type.equals("use_by_traffic_mean_share_rate_non_dwell")) {
				// 비주거용도 교통수단 분담률(TRF_IPCSS_MEAN_SHARE_RT)
				errorSheet = "비주거용도 교통수단 분담률";
				List<TrfIpcssMeanShareRt> meanShareRtListForNonDwell = new ArrayList<TrfIpcssMeanShareRt>();
				List<String> trfUseCdRowListForNonDwell = new ArrayList<String>(); // 교통 사용 코드
				List<String> trfMeanVhcclsRowListForNonDwell = new ArrayList<String>(); // 교통 용도 코드
				int meanShareNoForNonDwell = 1;
				for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
					errorRow = rowIndex;
					XSSFRow row = sheet.getRow(rowIndex);
					if (rowIndex == 3) {
						trfUseCdRowListForNonDwell = getRowValueToList(row, false);
					}

					if (rowIndex == 4) {
						trfMeanVhcclsRowListForNonDwell = getRowValueToList(row, false);
					}
					if (rowIndex >= 0 && rowIndex <= 4 || row == null)
						continue;

					List<TrfIpcssMeanShareRt> dataList = new ArrayList<TrfIpcssMeanShareRt>();
					dataList = getMeanShareRtDataListForNonDwell(rowIndex, row, usgCdList, trfUseCdRowListForNonDwell,
							trfMeanVhcclsRowListForNonDwell, trfUseCdList, trfMeanVhcclsList, meanShareNoForNonDwell);

					meanShareRtListForNonDwell = Stream.concat(meanShareRtListForNonDwell.stream(), dataList.stream())
							.collect(Collectors.toList());
					meanShareNoForNonDwell++;
				}
				if (!meanShareRtListForNonDwell.isEmpty()) {
					TrfvlmStatisticsDTO trfvlmStatisticsDTO = new TrfvlmStatisticsDTO();
					trfvlmStatisticsDTO.setIpcssMngNo(ipcssMngNoStr);
					trfvlmStatisticsDTO.setDwellYn("N");
					trfIpcssMeanShareRtMapper.deleteTrafficImpactReportForUsg(trfvlmStatisticsDTO);
					for (TrfIpcssMeanShareRt meanShareRt : meanShareRtListForNonDwell) {
						meanShareRt.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssMeanShareRtMapper.saveMeanShareRt(meanShareRt);
					}
				}
			}else if(type.equals("use_by_nbopl_cnt_dwell")) {
				// 주거용도 재차인차인원(TRF_IPCSS_NBOPL)
				errorSheet = "주거용도 재차인차인원";
				List<TrfIpcssNbopl> nboplListForDwell = new ArrayList<TrfIpcssNbopl>();
				List<String> trfUseCdRowListForDwell = new ArrayList<String>(); // 교통 사용 코드
				List<String> trfMeanVhcclsRowListForDwell = new ArrayList<String>(); // 교통 용도 코드
				int nboplNoForDwell = 1;
				for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
					errorRow = rowIndex;
					XSSFRow row = sheet.getRow(rowIndex);
					if (rowIndex == 4) {
						trfUseCdRowListForDwell = getRowValueToList(row, false);
					}

					if (rowIndex == 5) {
						trfMeanVhcclsRowListForDwell = getRowValueToList(row, false);
					}
					if (rowIndex >= 0 && rowIndex <= 4 || row == null)
						continue;

					List<TrfIpcssNbopl> dataList = new ArrayList<TrfIpcssNbopl>();
					dataList = getNboplDataListForDwell(rowIndex, row, usgCdList, trfUseCdRowListForDwell,
							trfMeanVhcclsRowListForDwell, trfUseCdList, trfMeanVhcclsList, nboplNoForDwell);

					nboplListForDwell = Stream.concat(nboplListForDwell.stream(), dataList.stream())
							.collect(Collectors.toList());
					nboplNoForDwell++;
				}
				if (!nboplListForDwell.isEmpty()) {
					TrfvlmStatisticsDTO trfvlmStatisticsDTO = new TrfvlmStatisticsDTO();
					trfvlmStatisticsDTO.setIpcssMngNo(ipcssMngNoStr);
					trfvlmStatisticsDTO.setDwellYn("Y");
					trfIpcssNboplMapper.deleteTrafficImpactReportForUsg(trfvlmStatisticsDTO);
					for (TrfIpcssNbopl nbopl : nboplListForDwell) {
						nbopl.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssNboplMapper.saveNbopl(nbopl);
					}
				}
			}else if(type.equals("use_by_nbopl_cnt_non_dwell")) {
				// 비주거용도 재차인차인원(TRF_IPCSS_NBOPL)
				errorSheet = "비주거용도 재차인차인원";
				List<TrfIpcssNbopl> nboplListForNonDwell = new ArrayList<TrfIpcssNbopl>();
				List<String> trfUseCdRowListForNonDwell = new ArrayList<String>(); // 교통 사용 코드
				List<String> trfMeanVhcclsRowListForNonDwell = new ArrayList<String>(); // 교통 용도 코드
				int nboplNoForNonDwell = 1;
				for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
					errorRow = rowIndex;
					XSSFRow row = sheet.getRow(rowIndex);
					if (rowIndex == 3) {
						trfUseCdRowListForNonDwell = getRowValueToList(row, false);
					}

					if (rowIndex == 4) {
						trfMeanVhcclsRowListForNonDwell = getRowValueToList(row, false);
					}
					if (rowIndex >= 0 && rowIndex <= 4 || row == null)
						continue;

					List<TrfIpcssNbopl> dataList = new ArrayList<TrfIpcssNbopl>();
					dataList = getNboplDataListForNonDwell(rowIndex, row, usgCdList, trfUseCdRowListForNonDwell,
							trfMeanVhcclsRowListForNonDwell, trfUseCdList, trfMeanVhcclsList, nboplNoForNonDwell);

					nboplListForNonDwell = Stream.concat(nboplListForNonDwell.stream(), dataList.stream())
							.collect(Collectors.toList());
					nboplNoForNonDwell++;
				}
				
				if (!nboplListForNonDwell.isEmpty()) {
					TrfvlmStatisticsDTO trfvlmStatisticsDTO = new TrfvlmStatisticsDTO();
					trfvlmStatisticsDTO.setIpcssMngNo(ipcssMngNoStr);
					trfvlmStatisticsDTO.setDwellYn("N");
					trfIpcssNboplMapper.deleteTrafficImpactReportForUsg(trfvlmStatisticsDTO);
					for (TrfIpcssNbopl nbopl : nboplListForNonDwell) {
						nbopl.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssNboplMapper.saveNbopl(nbopl);
					}
				}
			}else if(type.equals("parking_occurrence")) {
				// 주차발생(TRF_IPCSS_PARKNG_OCCUR_BSUNT)
				errorSheet = "주차발생 원단위";
				List<TrfIpcssParkngOccurBsunt> parkngOccurBsuntList = new ArrayList<TrfIpcssParkngOccurBsunt>();
				int parkngOccurNo = 1;
				for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
					errorRow = rowIndex;
					XSSFRow row = sheet.getRow(rowIndex);
					if (rowIndex >= 0 && rowIndex <= 3 || row == null)
						continue;

					List<TrfIpcssParkngOccurBsunt> dataList = new ArrayList<TrfIpcssParkngOccurBsunt>();
					dataList = getParkngOccurBsuntDataList(rowIndex, row, usgCdList, parkngOccurNo);

					parkngOccurBsuntList = Stream.concat(parkngOccurBsuntList.stream(), dataList.stream())
							.collect(Collectors.toList());
					parkngOccurNo++;
				}
				if (!parkngOccurBsuntList.isEmpty()) {
					trfIpcssParkngOccurBsuntMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNoStr);
					
					for (TrfIpcssParkngOccurBsunt parkngOccurBsunt : parkngOccurBsuntList) {
						parkngOccurBsunt.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssParkngOccurBsuntMapper.saveParkngOccurBsunt(parkngOccurBsunt);
					}
				}
			}else if(type.equals("time_by_in_and_out_pass")) {
				// 시간대별 유출입 통행(TRF_IPCSS_TIME_INOUTFL_EXMN)
				errorSheet = "시간대별 유출입 통행분포";
				List<TrfIpcssTimeInoutflExmn> timeInoutflExmnList = new ArrayList<TrfIpcssTimeInoutflExmn>();
				TrfIpcssTimeInoutflExmn trfIpcssTimeInoutflExmn = new TrfIpcssTimeInoutflExmn();
				String dywkDiv = ""; // 요일 구분
				List<String> usgNoList = new ArrayList<String>(); // 용도 번호
				List<String> usgNmList = new ArrayList<String>(); // 용도 명

				for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
					errorRow = rowIndex;
					XSSFRow row = sheet.getRow(rowIndex);
					if (row == null)
						continue;

					if (rowIndex >= 0 && rowIndex <= 5) {
						// 요일 구분
						if (rowIndex == 0) {
							dywkDiv = getCellValue(row.getCell(0));
							trfIpcssTimeInoutflExmn.setDywkDiv(dywkDiv);
						}
						// 용도 번호
						if (rowIndex == 3) {
							usgNoList = getRowValueToList(row, true);
							trfIpcssTimeInoutflExmn.setUsgNoList(usgNoList);
						}
						// 용도 명
						if (rowIndex == 4) {
							usgNmList = getRowValueToList(row, true);
							trfIpcssTimeInoutflExmn.setUsgNmList(usgNmList);
						}
					}
					if (rowIndex >= 8) {
						List<TrfIpcssTimeInoutflExmn> dataList = new ArrayList<TrfIpcssTimeInoutflExmn>();
						dataList = getTimeInoutflExmnDataList(trfIpcssExmnBizInfo, trfIpcssTimeInoutflExmn, rowIndex, row);

						timeInoutflExmnList = Stream.concat(timeInoutflExmnList.stream(), dataList.stream())
								.collect(Collectors.toList());
					}
				}
				
				if (!timeInoutflExmnList.isEmpty()) {
					trfIpcssTimeInoutflExmnMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNoStr);
					for (TrfIpcssTimeInoutflExmn timeInoutflExmn : timeInoutflExmnList) {
						timeInoutflExmn.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssTimeInoutflExmnMapper.saveTimeInoutflExmn(timeInoutflExmn);
					}
				}
			}else if(type.equals("time_by_pass_distribution")) {
				// 시간대별 통행 분포(TRF_IPCSS_TIME_PASS_DISTRB)
				errorSheet = "시간대별 통행 분포";
				List<TrfIpcssTimePassDistrb> timePassDistrbList = new ArrayList<TrfIpcssTimePassDistrb>();
				// 데이터 세팅
				TrfIpcssTimePassDistrb paramDto = new TrfIpcssTimePassDistrb();
				String bizNm = "";
				String bizUsg = "";
				List<String> rowUsgCdList = new ArrayList<String>(); // 용도 코드 리스트
				for (int rowIndex = 0; rowIndex < sheetRowSize; rowIndex++) {
					errorRow = rowIndex;
					XSSFRow row = sheet.getRow(rowIndex);
					if (row == null)
						continue;

					if (rowIndex == 1) paramDto.setBizNm(getCellValue(row.getCell(1)));
					if (rowIndex == 2) paramDto.setBizUsg(getCellValue(row.getCell(1)));
					if (rowIndex == 4 || rowIndex == 35) paramDto.setDywkDiv(getCellValue(row.getCell(0))); // 요일 구분
					if (rowIndex == 7 || rowIndex == 38) {
						rowUsgCdList = getRowValueToList(row, false);
						paramDto.setUsgCdList(rowUsgCdList);
					}

					if (rowIndex >= 8 && rowIndex <= 31 || rowIndex >= 39 && rowIndex <= 62) {
						List<TrfIpcssTimePassDistrb> dataList = new ArrayList<TrfIpcssTimePassDistrb>();
						dataList = getTimePassDistrbDataList(paramDto, rowIndex, row, usgCdList);

						timePassDistrbList = Stream.concat(timePassDistrbList.stream(), dataList.stream())
								.collect(Collectors.toList());
					}
				}
				if (!timePassDistrbList.isEmpty()) {
					trfIpcssTimePassDistrbMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNoStr);
					
					for (TrfIpcssTimePassDistrb timePassDistrb : timePassDistrbList) {
						timePassDistrb.setIpcssMngNo(ipcssMngNoStr);
						trfIpcssTimePassDistrbMapper.saveTimePassDistrb(timePassDistrb);
					}
				}
			}
		} catch (FileNotFoundException e) {
			throw new CommonException(ErrorCode.FILE_NOT_FOUND);
		} catch (Exception e) {
			throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL, errorSheet + " 시트 " + errorRow + "행을 확인해주세요.");
		} finally {
			if(workbook != null) {
				try {
					workbook.close();
				} catch (Exception e) {
					throw new CommonException(ErrorCode.FILE_UPLOAD_FAIL, errorSheet + " 시트 " + errorRow + "행을 확인해주세요.");
				} 
			}
		}
	}
}
