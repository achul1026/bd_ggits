<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <div class="main_container">
            <aside class="snb_container">
                <div class="snb_wrap">
                    <h3 class="side_title">교통 DB화 통계</h3>
                    <div class="side_txt">
                        교통 DB화 통계 정보를 <br>조회합니다.
                    </div>
                    <div class="side_btn">
                        <a href="${pageContext.request.contextPath}/statistics/traffic/database/impact/list.do"  class="on" onclick="startLoading()">교통영향평가</a>
                    </div>
                </div>
            </aside>
            <section class="main_section">
            	<c:import url="/WEB-INF/jsp/ggits/statistics/searchTrfImpact.jsp" />      
                <div class="search_container mt8">
                	<div class="search_table_width">
                		<c:choose>
		        		<c:when test="${fn:length(impactReportList) > 0}">
		        		<c:forEach var="impactInfo" items="${impactReportList}" varStatus="status">
		        		<div class="flex">
							<c:if test="${status.index == 0}">
							<div class="row1 flex-column">
								<div class="flex">
									<div class="table_middle table_title">지점명칭</div>
									<!-- 지점명칭 for start -->
									<div class="flex">
										<c:forEach var="headerInfo" items="${headerList}">
										<div class="table_column300 table_title"><c:out value='${headerInfo.pointNm}'/></div>
										</c:forEach>
									</div>
									<!-- 지점명칭 for end -->
								</div>
								<div class="flex">
									<div class="table_middle table_title">시간대</div>
									<!-- for start -->
									<div class="flex">
										<div class="table_small table_title">보행(명)</div>
										<div class="table_small table_title">자전거(대)</div>
										<div class="table_small table_title">PM(대)</div>
									</div>
									<div class="flex">
										<div class="table_small table_title">보행(명)</div>
										<div class="table_small table_title">자전거(대)</div>
										<div class="table_small table_title">PM(대)</div>
									</div>
									<div class="flex">
										<div class="table_small table_title">보행(명)</div>
										<div class="table_small table_title">자전거(대)</div>
										<div class="table_small table_title">PM(대)</div>
									</div>
									<div class="flex">
										<div class="table_small table_title">보행(명)</div>
										<div class="table_small table_title">자전거(대)</div>
										<div class="table_small table_title">PM(대)</div>
									</div>
									<div class="flex">
										<div class="table_small table_title">보행(명)</div>
										<div class="table_small table_title">자전거(대)</div>
										<div class="table_small table_title">PM(대)</div>
									</div>
									<div class="flex">
										<div class="table_small table_title">보행(명)</div>
										<div class="table_small table_title">자전거(대)</div>
										<div class="table_small table_title">PM(대)</div>
									</div>
									<!-- for end -->
								</div>
							</div>
							</c:if>
		        		</div>
		        		<!-- row start -->
						<div class="row_value flex max-content">
							<div class="table_middle ${fn:contains(impactInfo.timeSctnNm, '~') ? 'table_txt' : 'table_title'}"><c:out value="${fn:contains(impactInfo.timeSctnNm, '~') ? impactInfo.timeSctnNm : '합계(대)'}"/></div>
							<c:set var="ipcssResultList" value="${impactInfo.ipcssResultList}"/>
							<c:if test="${fn:length(ipcssResultList) > 0}">
							<c:set var="detailLength" value="${fn:length(ipcssResultList)}"/>
							<c:forEach var="impactDetail" items="${ipcssResultList}" varStatus="status">
							<ul class="flex">
								<!-- column start -->
								<li class="table_small ${fn:contains(impactDetail.timeSctnNm, '~') ? 'table_txt' : 'table_title'}"><c:out value='${impactDetail.pdstCnt}'/></li>
								<li class="table_small ${fn:contains(impactDetail.timeSctnNm, '~') ? 'table_txt' : 'table_title'}"><c:out value='${impactDetail.bcyclCnt}'/></li>
								<li class="table_small ${fn:contains(impactDetail.timeSctnNm, '~') ? 'table_txt' : 'table_title'}"><c:out value='${impactDetail.indvslMvmnEqpmntCnt}'/></li>
								<!-- value end -->
							</ul>
							</c:forEach>
							</c:if>
						</div>
						<!-- time value for end -->
						</c:forEach>
		        		</c:when>
						<c:otherwise>
						</c:otherwise>
		        		</c:choose>
	                </div>
		            <div class="table_chart_wrap">
		              <div class="table_chart_list">
		              	<c:forEach var="chartInfo" items="${headerList}" varStatus="status">
		              		<div class="chart chart_height">
			                  	<div class="tab_box_title left mb16"><c:out value='${chartInfo.pointNm}'/></div>
			                  	<div id="chart<c:out value='${status.index+1}'/>"></div>
			                 </div>
		              	</c:forEach>
		              </div>
		            </div>
            	</div>        	            	
            </section> 
        </div>
	<script>
	var dataTotalCnt = '<c:out value="${paging.totalCount}"/>';

	$("#totalCnt").text(numberComma(dataTotalCnt))
	
	/* 검색결과 */
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none');
	});
	
	$(document).ready(function() {
		
		var searchFilter = $(`
	            <div class="group2">
	            	<div class="group_text2">요일 구분</div>
					<div class="dywkDivTrgt">
						<input type="hidden" id="dywkDiv" value="${dywkDiv}">
						<select class="selectBox" name="dywkDivSelectBox" id="dywkDivSelectBox">
							<option value="평일">평일</option>
							<option value="주말">주말</option>
						</select>
					</div>      
	        	</div>		
			`)
			$('.contents_wrap').append(searchFilter);
		
		$('#selectBox').change(function() {
			var type = $('#selectBox option:selected').val();
			var ipcssMngNo = $("#ipcssMngNo").val();
	    	location.href="${pageContext.request.contextPath}/statistics/traffic/database/assessment/"+type+"/list.do?ipcssMngNo="+ipcssMngNo
	  	}); 

		var selectedType = $("#selectedType").val();
		$('#selectBox').val(selectedType).prop("selected",true);
		
		$('.dywkDivTrgt').change('#dywkDivSelectBox', function() {
			var type = $('#selectBox option:selected').val();
			var ipcssMngNo = $("#ipcssMngNo").val();
			var dywkDiv = $('#dywkDivSelectBox option:selected').val();
	    	location.href="${pageContext.request.contextPath}/statistics/traffic/database/assessment/"+type+"/list.do?ipcssMngNo="+ipcssMngNo+"&dywkDiv="+dywkDiv;
	  	}); 

		var dywkDiv = $("#dywkDiv").val();
		$('#dywkDivSelectBox').val(dywkDiv).prop("selected",true);
		
		var tableTitleDay =$(`
			<div class="flex-center gap8">
				<div class="ftsize14">현황조사 일자(요일)</div>
				:
				<div class="ftsize14">${exmnYmd}</div>
			</div>		
		`)
		$('#trafficDownload').append(tableTitleDay)
		$('#trafficDownload').css({'justify-content':'space-between', 'flex-direction':'row-reverse', 'align-items':'flex-end'});
		
	});
	
	var pointNo1 = '<c:out value="${pointNo1}"/>';
	var pointNo2 = '<c:out value="${pointNo2}"/>';
	var pointNo3 = '<c:out value="${pointNo3}"/>';
	var pointNo4 = '<c:out value="${pointNo4}"/>';
	var pointNo5 = '<c:out value="${pointNo5}"/>';
	var pointNo6 = '<c:out value="${pointNo6}"/>';

 	$(function(){
		var data = [{
            type: "sunburst",
            ids: [
                "root",
                "am", "pm",
                "am07", "am08", "pm17", "pm18",
                "am07Child1", "am07Child2", "am07Child3",
                "am08Child1", "am08Child2", "am08Child3",
                "pm17Child1", "pm17Child2", "pm17Child3",
                "pm18Child1", "pm18Child2", "pm18Child3",
			],
			labels:["", "오전", "오후", "08:00~09:00", "07:00~8:00", "18:00~19:00", "17:00~18:00",
			          "보행", "자전거", "PM", 
			          "보행", "자전거", "PM", 
			          "보행", "자전거", "PM", 
			          "보행", "자전거", "PM", 
			         ],
			parents:["", "root", "root", "am", "am", "pm", "pm",
                        "am07", "am07", "am07", 
                        "am08", "am08", "am08",
                        "pm17", "pm17", "pm17",
                        "pm18", "pm18", "pm18",
			          ],
				values: pointNo1.split(','),
				leaf: {"opacity": 0.9},
				marker: {
					"line": {"width": 2},
					 colors: ["", "#1f77b4", "#ff7f0e"]
				},
                branchvalues: 'total',
                insidetextfont: {
                	color:'#fff',
                	family: 'Pretendard'
                },
                hoverlabel: {
                	font:{color:'#fff'},
                	bordercolor:'#fff'
                }
			}];
	
			var layout = {
			  margin: {"l": 0, "r": 0, "b": 0, "t": 5}
			};

            Plotly.newPlot('chart1', data, layout, {showSendToCloud: true, displayModeBar:false})
            myPlot = document.getElementById("chart1");	
		
    		var data2 = [{
                type: "sunburst",
                ids: [
                    "root",
                    "am", "pm",
                    "am07", "am08", "pm17", "pm18",
                    "am07Child1", "am07Child2", "am07Child3",
                    "am08Child1", "am08Child2", "am08Child3",
                    "pm17Child1", "pm17Child2", "pm17Child3",
                    "pm18Child1", "pm18Child2", "pm18Child3",
    			],
    			labels:["", "오전", "오후", "07:00~8:00", "08:00~09:00", "17:00~18:00", "18:00~19:00",
    			          "보행", "자전거", "PM", 
    			          "보행", "자전거", "PM", 
    			          "보행", "자전거", "PM", 
    			          "보행", "자전거", "PM", 
    			         ],
    			parents:["", "root", "root", "am", "am", "pm", "pm",
                            "am07", "am07", "am07", 
                            "am08", "am08", "am08",
                            "pm17", "pm17", "pm17",
                            "pm18", "pm18", "pm18",
    			          ],
    				values: pointNo2.split(','),
    				leaf: {"opacity": 0.9},
					marker: {
						"line": {"width": 2},
						 colors: ["", "#1f77b4", "#ff7f0e"]
					},
	                branchvalues: 'total',
	                insidetextfont: {
	                	color:'#fff',
	                	family: 'Pretendard'
	                },
	                hoverlabel: {
	                	font:{color:'#fff'},
	                	bordercolor:'#fff'
	                }
    			}];

		var layout2 = {
		  "margin": {"l": 0, "r": 0, "b": 0, "t": 5},
		}

		Plotly.newPlot('chart2', data2, layout2, {showSendToCloud: true, displayModeBar:false})

		myPlot = document.getElementById("chart2");
		
		var data3 = [{
            type: "sunburst",
            ids: [
                "root",
                "am", "pm",
                "am07", "am08", "pm17", "pm18",
                "am07Child1", "am07Child2", "am07Child3",
                "am08Child1", "am08Child2", "am08Child3",
                "pm17Child1", "pm17Child2", "pm17Child3",
                "pm18Child1", "pm18Child2", "pm18Child3",
			],
			labels:["", "오후", "오전", "18:00~19:00", "17:00~18:00", "08:00~09:00", "07:00~8:00",
			          "보행", "자전거", "PM", 
			          "보행", "자전거", "PM", 
			          "보행", "자전거", "PM", 
			          "보행", "자전거", "PM", 
			         ],
			parents:["", "root", "root", "am", "am", "pm", "pm",
                        "am07", "am07", "am07", 
                        "am08", "am08", "am08",
                        "pm17", "pm17", "pm17",
                        "pm18", "pm18", "pm18",
			          ],
				values: pointNo3.split(','),
				leaf: {"opacity": 0.9},
				marker: {
					"line": {"width": 2},
					 colors: ["", "#1f77b4", "#ff7f0e"]
				},
                branchvalues: 'total',
                insidetextfont: {
                	color:'#fff',
                	family: 'Pretendard'
                },
                hoverlabel: {
                	font:{color:'#fff'},
                	bordercolor:'#fff'
                }
			}];

	var layout3 = {
	  "margin": {"l": 0, "r": 0, "b": 0, "t": 5},
	}

	Plotly.newPlot('chart3', data3, layout3, {showSendToCloud: true, displayModeBar:false})

	myPlot = document.getElementById("chart3");
	
	var data4 = [{
        type: "sunburst",
        ids: [
            "root",
            "am", "pm",
            "am07", "am08", "pm17", "pm18",
            "am07Child1", "am07Child2", "am07Child3",
            "am08Child1", "am08Child2", "am08Child3",
            "pm17Child1", "pm17Child2", "pm17Child3",
            "pm18Child1", "pm18Child2", "pm18Child3",
		],
		labels:["", "오전", "오후", "07:00~8:00", "08:00~09:00", "18:00~19:00", "17:00~18:00",
		          "보행", "자전거", "PM", 
		          "보행", "자전거", "PM", 
		          "보행", "자전거", "PM", 
		          "보행", "자전거", "PM", 
		         ],
		parents:["", "root", "root", "am", "am", "pm", "pm",
                    "am07", "am07", "am07", 
                    "am08", "am08", "am08",
                    "pm17", "pm17", "pm17",
                    "pm18", "pm18", "pm18",
		          ],
			values: pointNo4.split(','),
			leaf: {"opacity": 0.9},
			marker: {
				"line": {"width": 2},
				 colors: ["", "#1f77b4", "#ff7f0e"]
			},
            branchvalues: 'total',
            insidetextfont: {
            	color:'#fff',
            	family: 'Pretendard'
            },
            hoverlabel: {
            	font:{color:'#fff'},
            	bordercolor:'#fff'
            }
		}];
		
		var layout4 = {
		  "margin": {"l": 0, "r": 0, "b": 0, "t": 5},
		}
		
		Plotly.newPlot('chart4', data4, layout4, {showSendToCloud: true, displayModeBar:false})
		
		myPlot = document.getElementById("chart4");
		
		var data5 = [{
            type: "sunburst",
            ids: [
                "root",
                "am", "pm",
                "am07", "am08", "pm17", "pm18",
                "am07Child1", "am07Child2", "am07Child3",
                "am08Child1", "am08Child2", "am08Child3",
                "pm17Child1", "pm17Child2", "pm17Child3",
                "pm18Child1", "pm18Child2", "pm18Child3",
			],
			labels:["", "오후", "오전", "18:00~19:00", "17:00~18:00", "07:00~08:00", "08:00~09:00",
			          "보행", "자전거", "PM", 
			          "보행", "자전거", "PM", 
			          "보행", "자전거", "PM", 
			          "보행", "자전거", "PM", 
			         ],
			parents:["", "root", "root", "am", "am", "pm", "pm",
                        "am07", "am07", "am07", 
                        "am08", "am08", "am08",
                        "pm17", "pm17", "pm17",
                        "pm18", "pm18", "pm18",
			          ],
				values: pointNo5.split(','),
				leaf: {"opacity": 0.9},
				marker: {
					"line": {"width": 2},
					 colors: ["", "#1f77b4", "#ff7f0e"]
				},
                branchvalues: 'total',
                insidetextfont: {
                	color:'#fff',
                	family: 'Pretendard'
                },
                hoverlabel: {
                	font:{color:'#fff'},
                	bordercolor:'#fff'
                }
			}];

		var layout5 = {
		  "margin": {"l": 0, "r": 0, "b": 0, "t": 5},
		}
	
		Plotly.newPlot('chart5', data5, layout5, {showSendToCloud: true, displayModeBar:false})
	
		myPlot = document.getElementById("chart5");
		
		var data6 = [{
            type: "sunburst",
            ids: [
                "root",
                "am", "pm",
                "am07", "am08", "pm17", "pm18",
                "am07Child1", "am07Child2", "am07Child3",
                "am08Child1", "am08Child2", "am08Child3",
                "pm17Child1", "pm17Child2", "pm17Child3",
                "pm18Child1", "pm18Child2", "pm18Child3",
			],
			labels:["", "오전", "오후", "07:00~8:00", "08:00~09:00", "18:00~19:00", "17:00~18:00",
			          "보행", "자전거", "PM", 
			          "보행", "자전거", "PM", 
			          "보행", "자전거", "PM", 
			          "보행", "자전거", "PM", 
			         ],
			parents:["", "root", "root", "am", "am", "pm", "pm",
                        "am07", "am07", "am07", 
                        "am08", "am08", "am08",
                        "pm17", "pm17", "pm17",
                        "pm18", "pm18", "pm18",
			          ],
				values: pointNo6.split(','),
				leaf: {"opacity": 0.9},
				marker: {
					"line": {"width": 2},
					 colors: ["", "#1f77b4", "#ff7f0e"]
				},
                branchvalues: 'total',
                insidetextfont: {
                	color:'#fff',
                	family: 'Pretendard'
                },
                hoverlabel: {
                	font:{color:'#fff'},
                	bordercolor:'#fff'
                }
			}];

	var layout6 = {
	  "margin": {"l": 0, "r": 0, "b": 0, "t": 5},
	}

	Plotly.newPlot('chart6', data6, layout6, {showSendToCloud: true, displayModeBar:false})

	myPlot = document.getElementById("chart6");
})

$('#upload_btn').on('click', function(){
		var type = $('#selectBox option:selected').val();
		var ipcssMngNo = $("#ipcssMngNo").val();
        new ModalBuilder().init('교통영향평가 수정').ajaxBody("${pageContext.request.contextPath}/common/modal/csvupdate/list.do?type="+type+"&ipcssMngNo="+ipcssMngNo).footer(1,'수정',function(button, modal){
       		// validation 체크
        	$.ajax({
       			url : "${pageContext.request.contextPath}/statistics/traffic/database/impact/"+type+"/update.ajax?ipcssMngNo="+ipcssMngNo,
       			type : "POST",
       			data : new FormData($("#frmAttachedFiles")[0]),
       			dataType: "json",
       			processData: false,
       			contentType: false,
       			beforeSend : function(){
	    			startLoading();
	    		},
       			success: function(data) {
       				if(data.code == 200){
						new ModalBuilder().init().successBody("교통영향평가를 수정하였습니다.").footer(4,'확인',function(button, modal){
							modal.close();
							location.reload();
						}).open();
						modalAlertWrap();						
					} else {
						new ModalBuilder().init().alertBoby(data.message).footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();						
					}
       			},
       			error: function(error){
       				new ModalBuilder().init().alertBoby("교통영향평가 수정에 실패하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();	
       			},
	    		 complete : function(){
	    			 impactEndLoading();
	             }
       		});
        }).open();
       	$('.modal_footer').removeClass('none');
    });
</script>
