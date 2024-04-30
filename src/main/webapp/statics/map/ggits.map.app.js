const GitsDataApp = function(map){
    const app = this;
    const menuIdMapping = {
        /*모니터링*/
        "EVC000" : "M_TRAFFIC", // 교통현황
        "EVC001" : "M_SIGNAL", // 실시간 교통신호
        "EVC002" : "M_WARNING", // 돌발현황
        "EVC023" : "M_DANGER", // 위험물 차량 이동현황
        "EVC003" : "M_WEATHER", // 기상현황
        "EVC004" : "M_EMERGENCY", // 긴급차량 이동현황
        "EVC005" : "M_BUS", // 시내버스 이동현황
        "EVC006" : "BD_POPULATION", // 유동인구 밀집예측
        "EVC007" : "M_LINKDATA", // 연계대상 데이터
        "EVC008" : "M_SERVICE_OPERATION", // 서비스 운영 현황
        "EVC009" : "M_USECASE", // 유스케이스 항목접속현황
        "EVC010" : "M_BIGDATA", // 빅데이터 플랫폼 운영 상황판
        "EVC011" : "M_METADATA", // 메타데이터 관리
        /*빅데이터 분석*/
        "EVC012" : "BD_PATTERN", // 교통패턴 분석
        "EVC013" : "BD_EFFECT_ANALYSIS", // 교통활동 효과분석
        "EVC014" : "BD_DANGER_ZONE", // 교통위험 구간분석
        "EVC015" : "BD_PREDICTION", // 교통예측분석
        "EVC016" : "BD_LINK", // 교통신호 연동분석
        "EVC017" : "BD_PB_TRANS", // 대중교통 이용현황 분석
        "EVC018" : "BD_BUS_ROAD", // 버스 노선별분석
        "EVC019" : "BD_BUS_DANGER", // 버스 위험운영 구간분석
        "EVC020" : "BD_PB_PREDICTION", // 대중교통 예측분석
        /*기초정보조회*/
        "EVC021" : "B_NODELINK", // 노드링크 조회
        /*연계시설물조회*/
        "EVC022" : "F_FACILITY" // 시설물정보 조회
    };
    window.dualMap = null;
    let menuObject = {
        "M_TRAFFIC" : {
            callback : function(){
                map.control.hideLayer(GITS_ENV.LAYER.GRID);
                map.animate.removeFocus();
            },
            aside : [
            {
                menuNm : "시간대별<br/>교통량<br/>그래프",
                title : "금일 시간대별 교통량 그래프",
                code : "001",
                dataUrl : "/map/monitoring/traffic/",
                hasModal : true,
				disableDirectActive : true,
                afterCallback : function(){

                },
                mapCallback : function(data){

                }
            },
                {
                    menuNm : "도로/시간대별<br/>교통량",
                    title : "일일 도로/시간대별 교통량",
                    code : "006",
                    dataUrl : "/map/monitoring/traffic/",
                    hasModal : true,
                    disableDirectActive : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                },
            {
                menuNm : "시간대별<br/>평균통행속도<br/>그래프",
                title : "일일 시간대별 평균통행속도 그래프",
                code : "002",
                dataUrl : "/map/monitoring/traffic/",
                hasModal : true,
				disableDirectActive : true,
                afterCallback : function(){

                },
                mapCallback : function(data){
                }
            },
            /*{
                menuNm : "도로별<br/>누적교통량",
                title : "일일 도로별 누적교통량",
                code : "003",
                dataUrl : "/map/monitoring/traffic/",
                hasModal : true,
				disableDirectActive : true,
                afterCallback : function(){
                },
                mapCallback : async function(data){
                }
            },
            {
                menuNm: "도로별<br/>평균통행속도",
                title: "일일 도로별 평균 통행속도",
                code : "004",
                dataUrl: "/map/monitoring/traffic/",
                hasModal : true,
				disableDirectActive : true,
                afterCallback: function () {
                },
                mapCallback: async function (data) {
                }
            },*/

            {
                menuNm : "도로/시간대별<br/>평균통행속도",
                title : "일일 도로/시간대별 평균통행속도",
                code : "007",
                dataUrl : "/map/monitoring/traffic/",
                hasModal : true,
                disableDirectActive : true,
                afterCallback : function(){

                },
                mapCallback : async function(data){
                }
            },{
                menuNm : "스마트 교차로<br/>차종별 교통량<br/>그래프",
                title : "일일 스마트 교차로 차종별 교통량",
                code : "008",
                dataUrl : "/map/monitoring/traffic/",
                hasModal : true,
                disableDirectActive : true,
                afterCallback : function(){

                },
                mapCallback : async function(data){
                }
            }
            /*{
                menuNm : "소통정보",
                title : "일일 소통정보",
                code : "005",
                dataUrl : "/map/monitoring/traffic/",
                hasModal : true,
				disableDirectActive : true,
                afterCallback : function(){

                },
                mapCallback : function(data){
                }
            },*/
        ]},
        "M_SIGNAL" : {
            callback : function(){
                map.control.hideLayer(GITS_ENV.LAYER.GRID);
                map.animate.removeFocus();
            },
            aside : [
            {
                menuNm : "실시간<br/>교통신호 현황",
                title : "실시간 교통신호 현황",
                code : "001",
                dataUrl : "/map/monitoring/signal/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : async function(data){
                }
            }
        ]},
        "M_DANGER" : {
            callback : async function(){

            },
            aside : [
                {
                    menuNm : "위험물 차량 이동현황",
                    title : "위험물 차량 이동현황",
                    code : "001",
                    dataUrl : "/map/monitoring/danger/",
                    hasModal : true,
                    afterCallback : function(){
                        if(!$("#layerControlList [data-layer='dngr']").is(":checked")) {
                            $("#layerControlList [data-layer='dngr']")
                                .prop("checked", true)
                                .trigger("change");
                        }
                    },
                    mapCallback : function(data){
                    }
                }
            ]
        },
        "M_WARNING" : {
            callback : async function(){
                map.control.hideLayer(GITS_ENV.LAYER.GRID);
                map.animate.removeFocus();
            },
            aside : [
            {
                menuNm : "돌발 현황<br/>목록",
                title : "돌발 현황 목록",
                code : "001",
                dataUrl : "/map/monitoring/warning/",
                hasModal : true,
                afterCallback : function(){
                    if(!$("#layerControlList [data-layer='warn']").is(":checked")) {
                        $("#layerControlList [data-layer='warn']")
                            .prop("checked", true)
                            .trigger("change");
                    }
                },
                mapCallback : async function(data){
                }
            },
                {
                    menuNm : "일일 돌발<br/>현황 통계",
                    title : "일일 돌발 발생 현황 통계",
                    code : "002",
                    dataUrl : "/map/monitoring/warning/",
                    hasModal : true,
                    afterCallback : function(){
                        if(!$("#layerControlList [data-layer='warn']").is(":checked")) {
                            $("#layerControlList [data-layer='warn']")
                                .prop("checked", true)
                                .trigger("change");
                        }
                    },
                    mapCallback : async function(data){
                    }
                }
            /*,{
                menuNm : "돌발<br/>발생 위치",
                title : "돌발 발생 위치",
                code : "002",
                dataUrl : "/map/monitoring/warning/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : async function(data){
                }
            }*/
        ]},
        "M_WEATHER" : {
            callback : function(){
                map.control.hideLayer(GITS_ENV.LAYER.GRID);
                map.monitoring.getWeatherInfo();
                map.animate.removeFocus();
            },
            removeCallback : function(){
                map.monitoring.removeWeatherInfo();
            },
            aside : [
            {
                menuNm : "기상현황",
                title : "기상현황",
                code : "001",
                dataUrl : "/map/monitoring/weather/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : async function(data){
                }
            }
        ]},
        "M_EMERGENCY" : {
            callback : function(){
                map.control.hideLayer(GITS_ENV.LAYER.GRID);
                console.log($("#layerControlList [data-layer='emrg']").is(":checked"));
                if(!$("#layerControlList [data-layer='emrg']").is(":checked")) {
                    $("#layerControlList [data-layer='emrg']")
                        .prop("checked", true)
                        .trigger("change");
                }
            },
            aside : [
			{
                menuNm : "광역 긴급 차량<br/>운행 현황",
                title : "광역 긴급 차량 운행 현황",
                code : "002",
                dataUrl : "/map/monitoring/emergency/",
                hasModal : false,
                afterCallback : function(){
					if(!isNull(dataTimer)){
						clearInterval(dataTimer)
						dataTimer = null;
					}
                },
                mapCallback : function(){

                }
            },
            {
                menuNm : "일일 긴급차량<br/>이동 이력",
                title : "일일 긴급차량 이동 이력",
                code : "001",
                dataUrl : "/map/monitoring/emergency/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : async function(data){
                }
            }
			
        ]},
        "M_BUS" : {
            callback : function(){
                map.control.hideLayer(GITS_ENV.LAYER.GRID);
                if(!$("#layerControlList [data-layer='bus']").is(":checked")) {
                    $("#layerControlList [data-layer='bus']")
                        .prop("checked", true)
                        .trigger("change");
                }
            },
            aside : [
            {
                menuNm : "버스노선별<br/>버스이동현황",
                title : "버스노선별 버스이동현황",
                code : "001",
                dataUrl : "/map/monitoring/bus/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : function(data){
                }
            }
            ,{
                menuNm : "일일 버스노선<br/>이탈 이력",
                title : "일일 버스노선 이탈 이력",
                tooltip : "- 버스 노선별 해당 노선을 이탈 시 집계되는 정보입니다. 이탈 여부만 확인할 수 있습니다. 버스 실시간 위치정보와 노선이탈 정보의 지속시간(5분이상)을 비교해서 판단합니다. 이탈이력은 실제정보와 다를 수 있습니다.",
                code : "002",
                dataUrl : "/map/monitoring/bus/",
                hasModal : true,
                afterCallback : function(){
                    
                },
                mapCallback : function(data){
                }
            }
        ]},
		"M_LINKDATA" : {
            callback : function(){
                map.control.hideLayer(GITS_ENV.LAYER.GRID);
                map.animate.removeFocus();
            },
            aside : [
            {
                menuNm : "데이터<br/>수집현황",
                title : "데이터 수집현황",
                code : "001",
                dataUrl : "/map/monitoring/linkdata/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : async function(data){
                }
            },{
	            menuNm : "데이터<br/>수집이력",
                title : "데이터 수집이력",
                code : "002",
                dataUrl : "/map/monitoring/linkdata/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : async function(data){
                }
            },{
	            menuNm : "데이터<br/>수집장애",
                title : "데이터 수집장애",
                code : "003",
                dataUrl : "/map/monitoring/linkdata/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : async function(data){
                }
			},
        ]},
        "M_SERVICE_OPERATION" : {
            callback : function(){
                map.control.hideLayer(GITS_ENV.LAYER.GRID);
                map.animate.removeFocus();
            },
         	  aside : [
            {
                menuNm : "사용자<br/>로그인현황",
                title : "사용자 로그인 현황",
                code : "001",
                dataUrl : "/map/monitoring/operation/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : async function(data){
                }
            },{
	            menuNm : "유스케이스<br/>항목접속현황",
                title : "유스케이스 항목접속현황",
                code : "002",
                dataUrl : "/map/monitoring/operation/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : async function(data){
                }
            },{
	            menuNm : "시군이용<br/>대상별접속현황",
                title : "시군 이용 대상별 접속 현황",
                code : "003",
                dataUrl : "/map/monitoring/operation/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : async function(data){
                }
			},
        ]},
 	/*	 "M_SERVICE_OPERATION" : {
            callback : function(){
                map.control.hideLayer(GITS_ENV.LAYER.GRID);
                map.animate.removeFocus();
            },
            onlyContent : true,
            code : "001",
            dataUrl : "/map/monitoring/operation/M_SERVICE_OPERATION_001.ajax",
            hasModal : true,
            afterCallback : function(){

            },
            mapCallback : async function(data){
            }
        },*/
        "M_USECASE" : {
            callback : function(){
                map.control.hideLayer(GITS_ENV.LAYER.GRID);
                map.animate.removeFocus();
            },
            onlyContent : true,
            code : "001",
            dataUrl : "/map/monitoring/usecase/",
            hasModal : true,
            afterCallback : function(){

            },
            mapCallback : async function(data){
            }
        },
        "M_BIGDATA" : {
            callback : function(){
                map.control.hideLayer(GITS_ENV.LAYER.GRID);
                map.animate.removeFocus();
            },
            onlyContent : true,
            code : "001",
            dataUrl : "/map/monitoring/bigdata/M_BIGDATA_001.ajax",
            hasModal : true,
            afterCallback : function(){

            },
            mapCallback : async function(data){
            }
        },
        "BD_PATTERN" : {
            callback : function(){
                map.monitoring.removeBusRouteInfo();
            },
            aside : [
           	{
                menuNm : "교통량",
                title : "교통량 분석",
                code : "002",
                dataUrl : "/map/bigdata/pattern/",
                hasModal : true,
                afterCallback : function(){
					
                },
                mapCallback : function(data){
                    map.bigdata.removeBigDataLayers();
                    map.control.hideLayer(GITS_ENV.LAYER.SVC_LINK);
                    map.control.showLayer(GITS_ENV.LAYER.LINK);
                }
            },{
                menuNm : "평균속도",
                title : "평균 속도 분석",
                code : "003",
                dataUrl : "/map/bigdata/pattern/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback :  function(){
                    map.bigdata.removeBigDataLayers();
                    map.control.hideLayer(GITS_ENV.LAYER.SVC_LINK);
                    map.control.showLayer(GITS_ENV.LAYER.LINK);
                }
            },
                {
                menuNm : "상습 정체구간",
                title : "상습 정체구간",
                code : "004",
                dataUrl : "/map/bigdata/pattern/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : function(){
                    map.bigdata.removeBigDataLayers();
                    map.control.hideLayer(GITS_ENV.LAYER.LINK);
                    map.control.showLayer(GITS_ENV.LAYER.SVC_LINK);
                }
            },
			 {
                menuNm : "더 많은<br/>데이터 보기",
                title : "더 많은 데이터 보기",
                code : "001",
                disableDirectActive : true,
                dataUrl : "/map/bigdata/pattern/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : async function(data){
                }
            }
        ]},
        "BD_EFFECT_ANALYSIS" : {
            callback : function(){
                map.monitoring.removeBusRouteInfo();
            },
            aside : [
                /*{
                    menuNm : "더 많은<br/>데이터 보기",
                    title : "더 많은 분석보기",
                    code : "001",
                    disableDirectActive : true,
                    dataUrl : "/map/bigdata/effect/analysis/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                },*/
				{
                    menuNm : "교통량<br/>개선효과",
                    title : "교통량 개선 효과 조회하기",
                    code : "002",
                    dataUrl : "/map/bigdata/effect/analysis/",
                    hasModal : true,
                    afterCallback : function(){
                        const layerName = GITS_ENV.LAYER.BD_EFFECT;
                        map.control.removeCustomSource(layerName);
                        map.control.removeCustomSource(GITS_ENV.LAYER.FACILITY_SMART);
                        if(window.dualMap) {
                            window.dualMap.control.removeCustomSource(layerName);
                            window.dualMap.control.removeCustomSource(GITS_ENV.LAYER.FACILITY_SMART);
                        }
                        $(".bigdataDetailContentWrap").empty()
                        app.drawDualMap(null, null, function(){

                        });
                    },
                    mapCallback : async function(data){

                    }
                },
                {
                    menuNm : "평균속도<br/>개선효과",
                    title : "평균속도 개선 효과 조회하기",
                    code : "003",
                    dataUrl : "/map/bigdata/effect/analysis/",
                    hasModal : true,
                    afterCallback : function(){
                        const layerName = GITS_ENV.LAYER.BD_EFFECT;
                        map.control.removeCustomSource(layerName);
                        map.control.removeCustomSource(GITS_ENV.LAYER.FACILITY_SMART);
                        if(window.dualMap){
                            window.dualMap.control.removeCustomSource(layerName);
                            window.dualMap.control.removeCustomSource(GITS_ENV.LAYER.FACILITY_SMART);
                        }
                        $(".bigdataDetailContentWrap").empty()
                        app.drawDualMap(null, null, function(){

                        });
                    },
                    mapCallback : async function(data){

                    }
                },
                /*{
                    menuNm : "긴급차량 우선<br/>신호시스템<br/>제어효과",
                    title : "긴급차량 우선 신호 제어 효과 조회하기",
                    code : "003",
                    dataUrl : "/map/bigdata/effect/analysis/",
					directUrl : "http://192.168.21.77:3000/login",
					isFirstMenu : false,
					hasDirectUrl : true,
                    hasModal : true,
                    afterCallback : function(){
                        //app.removeDualMap();
                    },
                    mapCallback : async function(data){
                    }
                }*/
            ]},
        "BD_DANGER_ZONE" : {
            callback : function(){
                map.monitoring.removeBusRouteInfo();
            },
            aside : [
               {
                    menuNm : "위험 도로<br/>정보",
                    title : "위험 도로 정보 조회하기",
                   tooltip : "*수집데이터&#10;- 경찰청 도로위험상황예보 데이터는 초단기 일기예보를 기준으로 도로 기상상황에 위험요인을 확인하여 맞춤형 교통안전정보를 제공한다.&#10;- 9종 위험요소(안전거리미확보,과속,급커브(굽은도로),급경사(내리막),미끄러운도로,침수,기상-비,기상-눈,기상-결빙)에 대한  7종 위험등급(A~G)을 제공한다.&#10;* 로직(처리개요)&#10;- 년도별, 지역별 집계처리하여 표출한다.",
                    code : "002",
                    dataUrl : "/map/bigdata/danger/zone/",
                    hasModal : true,
                    afterCallback : function(){
                        map.bigdata.removeBigDataLayers();
                    },
                    mapCallback : async function(data){

                    }
                },{
                    menuNm : "교통사고<br/>위험지역 정보",
                    title : "교통사고 위험 지역 정보 조회하기",
                    tooltip : "- TAAS에서 수집된 데이터를 기반으로 교통사고가 주로 발생하는 위험 지역에 대한 집계 및 분석 자료입니다.",
                    code : "003",
                    dataUrl : "/map/bigdata/danger/zone/",
                    hasModal : true,
                    afterCallback : function(){
                        map.bigdata.removeBigDataLayers();
                    },
                    mapCallback : function(){

                    }
                }, {
                    menuNm : "더 많은<br/>데이터 보기",
                    title : "더 많은 데이터 보기",
                    code : "001",
                    disableDirectActive : true,
                    dataUrl : "/map/bigdata/danger/zone/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                }
            ]},
        "BD_PREDICTION" : {
            callback : function(){
                map.monitoring.removeBusRouteInfo();
            },
            aside : [
               {
                    menuNm : "교차로<br/>교통량 예측",
                    title : "교차로 교통량 예측 정보 조회하기",
                    code : "002",
                   tooltip : "- 스마트교차로의 교통량을 분석하여 향후 5일에 대한 교통량을 예측하여 제시합니다.&#10;* 사용 데이터&#10;- 수집데이터: 지자체별 스마트교차로 관련 마스터정보, 방향별(1시간,15분,5분) 통계이력정보,공휴일정보 데이터.&#10;* 로직(처리개요)&#10;- 수집된 데이터를 전처리 후 학습하여 AI 알고리즘에 의해 모델을 생성하고, 향후 1주일동안의 예측값을 산출한다. &#10;- 주입력값은 과거 일주전 하루 동안의 시계열 데이터들이며, 예측값은 매주 단위로 업데이트 된다.",
                    dataUrl : "/map/bigdata/prediction/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : function(){
                        map.bigdata.removeBigDataLayers();
                    }
                },{
                    menuNm : "사고<br/>예측구간",
                    title : "사고 예측 정보 조회하기",
                    tooltip : "- 빅데이터 분석을 통해 예측된 도로별 주요 사고 발생에 대한 가능성을 예측하는 정보입니다.",
                    code : "003",
                    dataUrl : "/map/bigdata/prediction/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : function(){
                        map.bigdata.removeBigDataLayers();
                    }
                },{
                    menuNm : "더 많은<br/>데이터 보기",
                    title : "더 많은 데이터 보기",
                    code : "001",
                    disableDirectActive : true,
                    dataUrl : "/map/bigdata/prediction/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                }
            ]},
        "BD_LINK" : {
            callback : function(){
                map.monitoring.removeBusRouteInfo();
            },
            aside : [
               /*{
                    menuNm : "교통신호<br/>연동분석",
                    title : "교통 신호 연동 분석",
                    code : "002",
                    dataUrl : "/map/bigdata/link/",
					directUrl : "http://192.168.21.77:3000/login",
					hasDirectUrl : true,
					isFirstMenu : true,
                    hasModal : true,
                    afterCallback : function(){
						location.href="http://192.168.21.77:3000/login";
                        map.bigdata.removeBigDataLayers();
                    },
                    mapCallback : async function(data){
                    }
                },{
                    menuNm : "더 많은<br/>데이터 보기",
                    title : "더 많은 데이터 보기",
                    code : "001",
                    disableDirectActive : true,
                    dataUrl : "/map/bigdata/link/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : function(){
                    }
                }
            */]
		},
        "BD_PB_TRANS" : {
            callback : function(){
                /*map.monitoring.getBusRouteInfo();
                map.monitoring.getBusStationInfo();*/
            },
            aside : [
               {
                    menuNm : "기종점<br/>대중교통<br/>이용량",
                    title : "기종점 대중교통 이용량 분석",
                    code : "002",
                    dataUrl : "/map/bigdata/tb/trans/",
					directUrl : "https://ggdata.gg.go.kr:7007/",
					hasDirectUrl : true,
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : function(){
						map.bigdata.removeBigDataLayers();
                    }
                },/*{
                    menuNm : "권역별<br/>대중교통<br/>이용현황",
                    title : "권역별 대중교통 이용 현황 분석",
                    code : "003",
                    dataUrl : "/map/bigdata/tb/trans/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : function(){
						map.bigdata.removeBigDataLayers();
                    }
                }*/
                /*
                2023-10-31 메뉴 통합
                ,{
                    menuNm : "권역별<br/>환승현황<br/>분석",
                    title : "대중교통 환승 현황 분석",
                    code : "004",
                    dataUrl : "/map/bigdata/tb/trans/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                }*/{
                    menuNm : "정류장별<br/>대중교통 및 <br/>노선",
                    title : "정류장별 대중교통 및 노선 조회",
                    code : "005",
                    dataUrl : "/map/bigdata/tb/trans/",
                    hasModal : true,
                    afterCallback : function(){
                    },
                    mapCallback : function(){
                        map.bigdata.removeBigDataLayers();
                        map.bigdata.getPublicTransferStation();
                    }
                }, {
                    menuNm: "정류장별<br/>이용현황",
                    title: "정류장별 이용현황 조회",
                    code: "006",
                    dataUrl: "/map/bigdata/tb/trans/",
                    hasModal : true,
                    hasChart: false,
                    afterCallback: function () {
                        map.bigdata.removeBigDataLayers();
                    },
                    mapCallback: function () {

                    }
                },
                {
                    menuNm: "노선별<br/>교통카드<br/>이용현황",
                    title: "노선별 교통카드 이용현황",
                    code: "008",
                    dataUrl: "/map/bigdata/tb/trans/",
                    hasModal : true,
                    hasChart: false,
                    afterCallback: function () {

                    },
                    mapCallback: async function (data) {
                        map.bigdata.removeBigDataLayers();
                    }
                }
                ,{
                    menuNm : "버스 도착정보<br/>예측",
                    title : "버스 도착정보 예측 조회",
                    code : "007",
                    tooltip : true,
                    tooltipFnc : "openBusArrivePrdctnInfo",
                    dataUrl : "/map/bigdata/tb/trans/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    	map.bigdata.removeBigDataLayers();
                    }
                }, {
                    menuNm : "더 많은<br/>데이터 보기",
                    title : "더 많은 데이터 보기",
                    code : "001",
                    disableDirectActive : true,
                    dataUrl : "/map/bigdata/tb/trans/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                }
            ]},
        "BD_BUS_ROAD" : {
            callback : function(){
            },
            aside : [
               {
                    menuNm : "노선별 승하차자 수",
                    title : "노선별 승하차자 조회",
                    code : "002",
                    dataUrl : "/map/bigdata/bus/road/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    	map.bigdata.removeBigDataLayers();
                    }
                },{
                    menuNm : "굴곡도 분석",
                    title : "굴곡도 분석",
                    tooltip : "- 굴곡도란 노선의 기종점을 기준으로 노선 선형의 굴곡도 정도를 의미합니다. 기종점이 직선(최단거리)인 경우 굴곡도는 1입니다.&#10;- 노선의 굴곡도가 높을수록 통행시간 및 거리 증가가 발생합니다.",
                    code : "003",
                    dataUrl : "/map/bigdata/bus/road/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    	map.bigdata.removeBigDataLayers();
                    }
                },{
                    menuNm : "중복구간 도출<br/>및 적정성 분석",
                    title : "중복구간 도출 및 적정성 조회",
                    code : "004",
                    dataUrl : "/map/bigdata/bus/road/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    	map.bigdata.removeBigDataLayers();
                    }
                },{
                    menuNm : "더 많은<br/>데이터 보기",
                    title : "더 많은 데이터 보기",
                    code : "001",
                    disableDirectActive : true,
                    dataUrl : "/map/bigdata/bus/road/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                }
            ]},
        "BD_BUS_DANGER" : {
            callback : function(){
            },
            aside : [
               {
                    menuNm : "대중교통<br/>안전 운행분석",
                    title : "대중교통 안전 운행 분석",
                    tooltip : "Digital Tachograph (DTG) 장치로 기록된 차량 운행 데이터를 실시간으로 저장하여, 대중교통의 안전 운행을 분석하는 데 활용할 수 있습니다. 이를 통해 차량 운전자들의 운행 습관과 패턴을 파악하여 8가지 위험 유형(급가속, 급감속, 급정지, 급출발, 급진로변경, 급앞지르기, 급좌우회전, 급유턴)에 대한 정보를 차량번호별로 조회하고 활용할 수 있습니다.",
                    code : "002",
                    dataUrl : "/map/bigdata/bus/danger/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : function(){
                    }
                },{
                    menuNm : "더 많은<br/>데이터 보기",
                    title : "더 많은 데이터 보기",
                    code : "001",
                    disableDirectActive : true,
                    dataUrl : "/map/bigdata/bus/danger/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                }
            ]},
        "BD_PB_PREDICTION" : {
            callback : function(){
            },
            aside : [
                {
                    menuNm : "유동인구 밀집<br/>예측 분석",
                    title : "유동인구 밀집 예측 분석 조회",
                    /*tooltip : "- KT의 유동인구 데이터와 버스 승차자 데이터 분석을 통해 예측된 주요 유동인구 밀집 지역을 조회하고, 시간대별 추이를 확인합니다.",*/
                    code : "002",
                    dataUrl : "/map/bigdata/pb/prediction/",
                    tooltip : true,
                    tooltipFnc : "openPopulationPrdctnInfo",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                        map.bigdata.removeBigDataLayers();
                        app.removeDualMap();
                    }
                },{
                    menuNm : "버스노선<br/>최적화<br/>예측분석",
                    title : "버스노선 최적화 예측 분석 조회",
                    code : "003",
                    tooltip : true,
                    tooltipFnc : "openRoutePrdctnInfo",
                    dataUrl : "/map/bigdata/pb/prediction/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                        map.bigdata.removeBigDataLayers();
                        map.control.setDefaultPanTilt();
                    }
                },{
                    menuNm : "더 많은<br/>데이터 보기",
                    title : "더 많은 데이터 보기",
                    code : "001",
                    dataUrl : "/map/bigdata/pb/prediction/",
                    hasModal : true,
                    disableDirectActive : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                },
            ]},
        "F_FACILITY" : {
            callback : function(){

            },
            aside : [
                {
                    menuNm : "VDS",
                    title : "VDS 시설물 찾기",
                    code : "001",
                    dataUrl : "/map/facility/information/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : function(){
                        map.facility.removeDSRC();
                        map.facility.removeSmartIntersection();
                        map.facility.removeSignal();
                        map.facility.getVds();
                    }
                },
/*                {
                    menuNm : "AVI",
                    title : "AVI 시설물 찾기",
                    code : "002",
                    dataUrl : "/map/facility/information/",
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                },*/
                {
                    menuNm : "DSRC",
                    title : "DSRC 시설물 찾기",
                    code : "002",
                    dataUrl : "/map/facility/information/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : function(){
                        map.facility.removeSignal();
                        map.facility.removeSmartIntersection();
                        map.facility.removeVds();
                        map.facility.getDSRC();
                    }
                },
                {
                    menuNm : "스마트 교차로",
                    title : "스마트교차로 시설물 찾기",
                    code : "003",
                    dataUrl : "/map/facility/information/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : function(){
                        map.facility.removeDSRC();
                        map.facility.removeSignal();
                        map.facility.removeVds();
                        map.facility.getSmartIntersection();
                    }
                },
                {
                    menuNm : "신호제어기",
                    /*title : this.menuNm + "시설물 찾기",*/
					title: "신호제어기 시설물 찾기",
                    code : "004",
                    dataUrl : "/map/facility/information/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : function(){
                        map.facility.removeDSRC();
                        map.facility.removeSmartIntersection();
                        map.facility.removeVds();
                        map.facility.getSignal();
                    }
                }
            ]},
        "B_NODELINK" : {
            callback : function(){
            },
            aside : [
                {
                    menuNm : "노드",
                    title : "노드 찾기",
                    code : "001",
                    dataUrl : "/map/basicinfo/nodelink/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                },
                {
                    menuNm : "링크",
                    title : "링크 찾기",
                    code : "002",
                    dataUrl : "/map/basicinfo/nodelink/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                },
                /*{
                    menuNm : "노드/링크<br/>현황보기",
                    title : "노드/링크 현황보기",
                    code : "003",
                    dataUrl : "/map/basicinfo/nodelink/",
                    afterCallback : function(){
                        window.location.href="/basicinfo/nodelink/current/list.do";
                    },
                    mapCallback : async function(data){
                    }
                },*/
                {
                    menuNm : "노드/링크<br/>자료실",
                    title : "노드/링크 자료실",
                    code : "004",
                    dataUrl : "/map/basicinfo/nodelink/",
                    afterCallback : function(){
                        window.location.href="/basicinfo/nodelink/reference/list.do";
                    },
                    mapCallback : async function(data){

                    }
                }
            ]},
    }

    menuObject = new Proxy(menuObject, {
        get : function(obj, prop){
            if(prop.indexOf("BD_") === 0){
                app.removeDualMap();
                map.bigdata.removeBigDataLayers();
                map.control.setDefaultPanTilt();
                map.control.hideLayer(GITS_ENV.LAYER.SVC_LINK);
                map.control.showLayer(GITS_ENV.LAYER.LINK);
                $('#map-container').find(".remarks_container").remove();
            }
            if(prop.indexOf("M_") === 0) {
                const EMER_TMP_LAYER = GITS_ENV.LAYER.EMERGENCY+"_TMP"
                if(__Map.getLayer(EMER_TMP_LAYER)) __Map.removeLayer(EMER_TMP_LAYER);
                if(__Map.getLayer(EMER_TMP_LAYER+"_POINT")) __Map.removeLayer(EMER_TMP_LAYER+"_POINT");
                if(__Map.getLayer(EMER_TMP_LAYER+"_LOC")) __Map.removeLayer(EMER_TMP_LAYER+"_LOC");
                if(__Map.getSource(EMER_TMP_LAYER)) __Map.removeSource(EMER_TMP_LAYER);
            }
            $('.mapboxgl-popup').remove();
            return obj[prop];
        }
    })
    let $wrap = $("#asideSectionWrap");
    const sigungu = null;

    const generateAside = function (){
        const $section = $("<section></section>");
        $section.addClass("dashboard_section tab_side mt56 tab_one");
        return $section;
    }

    const generateAsideMenu = function(type, obj){
        const $aside = $("<aside></aside>");
        $aside.addClass("mr32 tab_btn");
        const $ul = $("<ul></ul>");
        $ul.appendTo($aside);
        for(const item of obj) {
            if(item.title.includes("더 많은")){
                continue;
            }
            const $li = $("<li></li>");
            $li.addClass("side_item");
            if (item.disableDirectActive === true) {
                $li.attr("data-disabled-active", 'true');
            } else {
                $li.attr("data-disabled-active", 'false');
            }
            if(item.hasDirectUrl === true){
				const $a = $(`<a href="${item.directUrl}" class="map_load_data_btn ${item.isFirstMenu ? 'active' : ''}" target="_blank;">${item.menuNm}</a>`);
	            $a.addClass("is-side-btn");
	            $li.append($a);
				if(item.isFirstMenu === true){
					window.open(item.directUrl, '_blank');
				}
			}else{
				const $button = $(`<button class="map_load_data_btn" data-hasmodal="${item.hasModal}" data-type="${type}" data-title="${item.title}" data-url="${item.dataUrl}" data-code="${item.code}" data-chart="${item.hasChart}" data-tooltip="${item.tooltip ? item.tooltip : ''}" data-tooltip-fnc="${item.tooltipFnc ? item.tooltipFnc : ''}">${item.menuNm}</button>`);
	            $button.addClass("is-side-btn");
	            $li.append($button);

	            $button.on("click.gits_after", function () {
	                if (typeof item.afterCallback === "function") item.afterCallback();
	            });
	            $button.on("click.gits_map", function () {
	                if (typeof item.mapCallback === "function") item.mapCallback();
	            });
				$button.on("click", function(){
					$('.remarks_container').remove();
				})
			}
            $ul.append($li);
        }
        return $aside;
    }

    const generateDashboardContentDefault = function(title, tooltip,tooltipFnc){
        let $contentBox = $(`<div class='dashboard_box'></div>`);
        let $contentWrap = $("<div class='dashboard_wrap dash_bg'></div>");
        let $contentHeader = $("<div class='tab_box_header tab_title_box'></div>");
        let $contentBody = $("<div class='tab_box_body'></div>");
        let $contentFooter = $("<div class='tab_box_footer'></div>");
        $contentWrap.append($contentHeader).append($contentBody).append($contentFooter);
        $contentBox.append($contentWrap);
        let tooltipHtml = "";
        if(tooltip) {
            if(tooltipFnc){
                tooltipHtml = `<span onclick="${tooltipFnc}()" class="tooltip-common is-modal-title">?</span>`
            }else{
                tooltipHtml = `<span title="${tooltip}" class="tooltip-common is-modal-title">?</span>`
            }

        }
        // header set
        $contentHeader.append(`<div class='tab_box_title'>${title} ${tooltipHtml}</div>`);
        let $contentHeaderRightWrap = $("<div class='tab_box_close'><div class='opa_slider'></div></div>");
        /*let $contentHeaderMinimalize = $("<div class='tab_hamburger'><span></span><span></span><span className='mj0'></span></div>");*/
        let $contentHeaderClose = $("<button><img src='/statics/images/wh_close.png' style='width:0.75rem;height:0.75rem'></button>");
        $contentHeaderRightWrap.append($contentHeaderClose);
        $contentHeader.append($contentHeaderRightWrap);
        $contentHeaderClose.on("click", function(){
            $contentBox.remove();
            $(".map_load_data_btn.active").removeClass("active");
        });
        $(document).tooltip({
            track : true,
            position: {
                my: "center bottom-20",
                at: "center top",
                using: function( position, feedback ) {
                    $( this ).css( position );
                    $( "<div>" )
                        .addClass( "arrow" )
                        .addClass( feedback.vertical )
                        .addClass( feedback.horizontal )
                        .appendTo( this );
                }
            }
        });
        this.getBody = function(){
            return $contentBody;
        }
        this.setContent = function(html) {
            $contentBody.html(html);
        }
        this.getWrapper = function(){
            return $contentBox;
        }
        return this;
    }
    const generateDashboardContent = function(title, content, isClear = true, tooltip,tooltipFnc){
        let $dashboardContainer = $("#aside_dashboard_container");
        if(isClear) $dashboardContainer.empty();
        let dashboardContent = generateDashboardContentDefault(title, tooltip,tooltipFnc);
        let $contentWrapper = dashboardContent.getWrapper();
        dashboardContent.setContent(content);
        $dashboardContainer.append($contentWrapper);
        if($("#aside_dashboard_container").hasClass("ui-resizable")) {
            $("#aside_dashboard_container").resizable("destroy")
        }
        $("#aside_dashboard_container").resizable({
            handles: "e",
            minWidth: 350,
            distance: 30
        });
        $("#aside_dashboard_container:not(.ui-draggable)").draggable({"handle":".tab_box_header"});
    }

    const dashboardContentOpenEvent = function(){
        $(".opa_slider").slider({
            animate : true,
            range : "min",
            min: 30,
            max:100,
            value : 100,
            slide : function(event,ui){
                $(this).parents().eq(3).css("opacity", ui.value/100);
            }
        });
    }
    app.setMap = function(globalMapCore) {
        map = globalMapCore;
    }
    app.asideClear = function(){

    }
    app.drawContent = async function(target, url, data) {
        $.ajax({
            type : "get",
            url : url,
            data : data,
            success : function(html){
                target.html(html);
            }
        });
    }
    app.getContent = async function(url, data){
        return new Promise(function(resolve){
            $.ajax({
                type : "get",
                url : url,
                data : data,
                beforeSend : function(){
                    app.startLoading();
                },
                success : function(html){
                    resolve(html);
                },
                error : function(){
                    resolve("컨텐츠를 찾을 수 없습니다.");
                },
                complete : function(){
                    app.endLoading();
                }
            });
        });
    }
    app.toggleRemarkModal = function(){
        $("#dataRemarkContent").slideToggle(200);
        $("#dataRemarkModalHeader .remarks_title").toggleClass("rotate");
    }

    /**
     * 듀얼맵 실행
     * @param page
     * @param city
     */
    app.drawDualMap = function(page, city, fnc){
        if(window.dualMap) return;
        $("body").addClass("map-double");
        setTimeout(function(){
            window.dualMap = new GITSMapCore("compare_map").init(page, city, true);
            __Map.resize();
            if(typeof fnc === "function") {
                fnc();
            }
        }, 500);
    }

    app.mapToggleLayer = function(_this) {
        const layer = $(_this).data("layer");
        const checked = $(_this).is(":checked");
        console.log("layer toggle - ", layer, checked);
        switch(layer) {
            case "trf" :
                if(checked)
                    map.control.showLayer(GITS_ENV.LAYER.LINK);
                else
                    map.control.hideLayer(GITS_ENV.LAYER.LINK);
                break;
            case "node" :
                if(checked)
                    map.control.showLayer(GITS_ENV.LAYER.NODE);
                else
                    map.control.hideLayer(GITS_ENV.LAYER.NODE);
                break;
            case "emrg" :
                if(checked)
                    map.monitoring.getEmergencyMoveInfo(false);
                else
                    map.monitoring.removeEmergencyMoveInfo()
                break;
            case "dngr" :
                if(checked)
                    map.monitoring.getDggdMoveInfo(false);
                else
                    map.monitoring.removeDggdMoveInfo()
                break;
            case "warn" :
                if(checked)
                    map.monitoring.getWarningInfo(false);
                else
                    map.monitoring.removeWarningInfo()
                break;
            case "bus" :
                if(checked)
                    map.monitoring.getBusMoveInfo(false);
                else
                    map.monitoring.removeBusMoveInfo();
                break;
            case "smc" :
                if(checked) {
                    map.monitoring.getTrafficVolumeSmart(true, 60000);
                    map.facility.getSmartIntersection("crsrd");
                }else {
                    map.monitoring.removeTrafficVolumeSmart();
                    map.facility.removeSmartIntersection();
                }
                break;
            case "dsrc" :
                if(checked)
                    map.facility.getDSRC("dsrc");
                else
                    map.facility.removeDSRC();
                break;
            case "vds" :
                if(checked) {
                    map.monitoring.getTrafficVolumeVDS(true, 60000);
                    map.facility.getVds();
                }else {
                    map.monitoring.removeTrafficVolumeVDS();
                    map.facility.removeVds();
                }
                break;
            case "taas" :
                if(checked)
                    map.bigdata.getAccidentDangerAreaInfoHeatMap("searchYear=2023");
                else
                    map.control.removeCustomSource(GITS_ENV.LAYER_PREFIX+"BD_DANGER_ZONE_BY_TYPE");
                break;
            case "ordtmNlrd" :
                if(checked)
                    map.bigdata.getTrafficVolumeOrdtmNlrd("searchYear=2022");
                else
                    map.bigdata.removeTrafficVolumeOrdtmNlrd();
                break;
            case "anytmNlrd" :
                if(checked)
                    map.bigdata.getTrafficVolumeAnyTmNlrd("searchYear=2022");
                else
                    map.bigdata.removeTrafficVolumeAnyTmNlrd();
                break;
            case "anytmHghw" :
                if(checked)
                    map.bigdata.getTrafficVolumeAnyTmHghw("searchYear=2022");
                else
                    map.bigdata.removeTrafficVolumeAnyTmHghw();
                break;
        }
    }

    app.drawMergeButton = function(event){
        if($("#dualMapMergeButton").length > 0) {
            return;
        }
        const button = $(`
	        <div class="map_fusion_box">
	            <button type="button" id="dualMapMergeButton" class="map_fusion_titlebox">
	                <div><img src="/statics/images/plus.png" alt="플러스"></div>
	                <div class="map_fusion_titie">지도 융합하기</div>
	            </button>
	        </div>
        `);
		const fusionDate = $(`
            <div class="map_fusion_databox">
                <div class="map_fusion_flexbox">
                    <div class="map_fusion_dataitem">
                        <div class="flex-between fusion_data_titlebox">
                            <div class="map_fusion_data_title">개선 활동 전</div>
                            <button type="button" class="map_fusion_data_arrow"></button>
                        </div>
                        <div class="fusion_data_inputbox">
							<div class="start_date input_same"></div>
							<div class="start_time input_same"></div>
                        </div>
                    </div>
                    <div class="map_fusion_dataitem">
                        <div class="flex-between fusion_data_titlebox">
                            <div class="map_fusion_data_title">개선 활동 후</div>
                            <button type="button" class="map_fusion_data_arrow"></button>
                        </div>
                        <div class="fusion_data_inputbox">
							<div class="end_date input_same"></div>
							<div class="end_time input_same"></div>
                        </div>
                    </div>
                </div>
            </div>
		`);
		
        button.on("click", event);
        $("#compare_map").append(button, fusionDate);
		imporovement();
		
		button.on("click", function(){
	        var trafficRemrks = $(`
		        <div class="remarks_container">
			        <div class="remarks_title_box">
			            <h6 class="remarks_title">범례 - 교통량 증감수</h6>
			        </div>
		        	<div class="remarks_wrap">
		            	<div>
			                <ul class="check_line_container">
			                    <li class="check_line_box remarks-red">14,001 - 100,000</li>
			                    <li class="check_line_box remarks-orange">8,001 - 14,000</li>
			                    <li class="check_line_box remarks-light-orange">5,001 - 8,000</li>
			                    <li class="check_line_box remarks-light-green">2,001 - 5,000</li>
			                    <li class="check_line_box remarks-green">- 2,000</li>
			                </ul>
		            	</div>
			            <div class="unit">단위 : (일/대)</div>
		        	</div>
		    	</div>`);

	        var speendRemrks = $(`
		        <div class="remarks_container">
			        <div class="remarks_title_box">
			            <h6 class="remarks_title">범례 - 평균속도 증감수</h6>
			        </div>
		        	<div class="remarks_wrap">
		            	<div>
			                <ul class="check_line_container">
			                    <li class="check_line_box remarks-red">정체</li>
			                    <li class="check_line_box remarks-orange">지체(서행)</li>
			                    <li class="check_line_box remarks-green">원활</li>
			                </ul>
		            	</div>
			            <div class="unit">단위 : 소통등급</div>
		        	</div>
		    	</div>`);

				if($('.remarks_container').hasClass('speed')){
					$('.remarksClose').remove();
					$('#map-container').append(speendRemrks);
				} else {
					$('.remarksClose').remove();
					$('#map-container').append(trafficRemrks);
				}     
	        	legendToggle();

		// 지도 융합 한 후에 되돌리기 
			const fusionPrev = $(`
				<li class="side_item">
					<button type="button" class="is-side-btn" onclick="">
						PrevTest
					</button>
				</li>
			`);
			
			$(".tab_btn").find("ul").append(fusionPrev);
			
		})
		
    }
    app.startLoading = function(){
        if($("#mapLoadingLayer").length === 0) {
            const layer = $(`
				<div id="mapLoadingLayer">
					<img src="/statics/images/loading_loop.gif">
					<div class="loading_data_txt">데이터를 분석하고 있습니다. 조회 시간이 다소 걸릴 수 있습니다.</div>
				</div>

			`);
            $("body").append(layer);
        }
    }
    app.endLoading = function(){
        /*if(window.map.getJobList().length == 0) {*/
            $("#mapLoadingLayer").fadeOut(250, function () {
                $("#mapLoadingLayer").remove();
            });
        /*}*/
    }

    /**
     * 듀얼맵 제거
     * @param callback
     */
    app.removeDualMap = function(callback){
        $("body").removeClass("map-double");
        if(window.dualMap) window.dualMap.getMapboxGl().remove();
        $("#compare_map").empty();
        delete window.dualMap;
        delete window.__DualMap;
        if(typeof callback === "function") callback();
        setTimeout(function(){
            __Map.resize();
        }, 500);
    }

    app.drawMenu = async function(menuId = "22", menuPttrnType = "EVC000") {
        $wrap.empty();
		$.ajax({
			type : "post",
			data : {
				"menuId" : menuId
			},
			dataType  : "json",
			url : "/common/saveMenuLog.ajax",
			success : function(result) {
				//console.log(result)
			}
		});
		
		//2023-11-16 bigdata 메뉴 searchParam 초기화
		let bdMenuPtternCode = parseInt(menuPttrnType.substring(3));
		if(bdMenuPtternCode < 21 && bdMenuPtternCode > 11){
			bigdataSearchForm = "";
		}
		if(!isNull(dataTimer)){
			clearInterval(dataTimer)
			dataTimer = null;
		}
        let loadMenuObj = menuObject[menuIdMapping[menuPttrnType]];
        let $section = generateAside();
        let $dashboardContainer = $("<div id='aside_dashboard_container'></div>");
        let $asideMenu = null;
        $dashboardContainer.addClass("dashboard_container gap16 tab_box");
        if(loadMenuObj && loadMenuObj.onlyContent === true){
            let html = await app.getContent(menuObject[menuIdMapping[menuPttrnType]].dataUrl);
            $dashboardContainer.empty();
            $dashboardContainer.html(html);
        }else{
            try {
                $asideMenu = generateAsideMenu(menuIdMapping[menuPttrnType], menuObject[menuIdMapping[menuPttrnType]].aside);
                $section.append($asideMenu);
            }catch(e) {console.log('None aside');}
        }
        $section.append($dashboardContainer);
        $wrap.append($section);
        if(typeof loadMenuObj.callback === "function") loadMenuObj.callback();
        /*if($asideMenu) $asideMenu.find("li[data-disabled-active='false']").eq(0).find("button").click();*/
        if($asideMenu) $asideMenu.find("li[data-disabled-active='true']").eq(0).addClass('on');
        if($asideMenu) $asideMenu.find("button[data-type='M_TRAFFIC']").parent("li[data-disabled-active='true']").removeClass('on');
    }

    app.callMenuAction = function(menuPttrnType = "EVC000", checkedVal = false) {
        let loadMenuObj = menuObject[menuIdMapping[menuPttrnType]];
		if(checkedVal){
	        if(typeof loadMenuObj.callback === "function") loadMenuObj.callback();
		}else{
	        if(typeof loadMenuObj.removeCallback === "function") loadMenuObj.removeCallback();
		}
    }

    app.generatePushElement = function(type, title, id, event, clickEvent, addClass= ''){
        const class_type = {
            "DANGER" : "remarks-red",
            "WARNING" : "remarks-orange",
        }
        const data_type = {
            "DANGER" : "collectError",
            "WARNING" : "",
        }
        let html = $(`<li data-id="${id}" class="outbreak_push_item ${class_type[type]} ${addClass} ${data_type[type]}">
						<button class="titButton" type="button">${title}</button>
                    	<button type="button" class="outbreak_push_close"><img src="/statics/images/wh_close.png" alt="닫기"></button>
                	</li>`);
        html.find(".titButton").on("click", function(){
            if(typeof clickEvent === "function") {
                clickEvent();
            }
        });
        html.find(".outbreak_push_close").on("click", function(){
           if(typeof event === "function") {
               event();
           }
            outbreakPushClose(this);
        });
        $("#outbreak_push").append(html);
    }

    $(document).on("click",".map_load_data_btn", async function(){
        let hasChart = $(this).data("chart");
        let title = $(this).data("title");
        let tooltip = $(this).data("tooltip");
        let tooltipFnc = $(this).data("tooltipFnc");
        let menuId = $(this).data("id");
        let code = $(this).data("code");
        let url = $(this).data("url");
        let type = $(this).data("type");
        let hasmodal = $(this).data("hasmodal");
		let tagName = $(this).prop("tagName");
        // generateDashboardContent(title, "html code", true);
        resetTimer();
        if(hasmodal === true) {
            let html = await app.getContent(url + type + "_" + code + ".ajax");
            generateDashboardContent(title, html, true, tooltip, tooltipFnc);
        }else{
            let $dashboardContainer = $("#aside_dashboard_container");
            $dashboardContainer.empty();
        }
        //let html = await app.getContent(url);

        $(".map_load_data_btn.active").removeClass("active");
        $(this).addClass("active");

        dashboardContentOpenEvent();
    });

    $(document).on("click",".collectError", function(){
		window.location.href= __contextPath__+"/historymng/collect/error/list.do";
    });

}

let gitsApp = null;
$(function(){
    gitsApp = new GitsDataApp();
})

