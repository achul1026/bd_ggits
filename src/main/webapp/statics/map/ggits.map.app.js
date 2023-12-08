const GitsDataApp = function(map){
    const app = this;
    const menuIdMapping = {
        /*모니터링*/
        "EVC000" : "M_TRAFFIC", // 교통현황
        "EVC001" : "M_SIGNAL", // 실시간 교통신호
        "EVC002" : "M_WARNING", // 돌발현황
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
                menuNm : "시간대별<br/>누적교통량",
                title : "시간대별 누적 교통량",
                code : "001",
                dataUrl : "/map/monitoring/traffic/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : function(data){

                }
            },
            {
                menuNm : "시간대별<br/>평균통행속도",
                title : "시간대별 평균 통행속도",
                code : "002",
                dataUrl : "/map/monitoring/traffic/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : function(data){
                }
            },
            {
                menuNm : "도로별<br/>누적교통량",
                title : "도로별 누적교통량",
                code : "003",
                dataUrl : "/map/monitoring/traffic/",
                hasModal : true,
                afterCallback : function(){
                },
                mapCallback : async function(data){
                }
            },
            {
                menuNm: "도로별<br/>평균통행속도",
                title: "도로별 평균 통행속도",
                code : "004",
                dataUrl: "/map/monitoring/traffic/",
                hasModal : true,
                afterCallback: function () {
                },
                mapCallback: async function (data) {
                }
            },
            {
                menuNm : "소통정보",
                title : "소통정보",
                code : "005",
                dataUrl : "/map/monitoring/traffic/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : function(data){
                }
            },
            {
                menuNm : "도로/시간대별<br/>교통량",
                title : "도로/시간대별 교통량",
                code : "006",
                dataUrl : "/map/monitoring/traffic/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : async function(data){
                }
            },
            {
                menuNm : "도로/시간대별<br/>평균통행속도",
                title : "도로/시간대별 평균통행속도",
                code : "007",
                dataUrl : "/map/monitoring/traffic/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : async function(data){
                }
            }
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
        "M_WARNING" : {
            callback : async function(){
                map.control.hideLayer(GITS_ENV.LAYER.GRID);
                map.monitoring.getWarningInfo();
                map.animate.removeFocus();
            },
            aside : [
            {
                menuNm : "일일<br/>돌발 이력",
                title : "일일 돌발 이력",
                code : "001",
                dataUrl : "/map/monitoring/warning/",
                hasModal : true,
                afterCallback : function(){

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
                map.monitoring.getEmergencyMoveInfo();
            },
            aside : [
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
            },
            {
                menuNm : "긴급 차량<br/>운행 현황",
                title : "긴급 차량 운행 현황",
                code : "002",
                dataUrl : "/map/monitoring/emergency/",
                hasModal : false,
                afterCallback : function(){

                },
                mapCallback : function(){

                }
            }
        ]},
        "M_BUS" : {
            callback : function(){
                map.control.hideLayer(GITS_ENV.LAYER.GRID);
                map.monitoring.getBusMoveInfo();
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
                mapCallback : async function(data){
                }
            },
            {
                menuNm : "시내버스<br/>운행현황",
                title : "시내버스 운행현황",
                code : "002",
                dataUrl : "/map/monitoring/bus/",
                hasModal : true,
                afterCallback : function(){
                    
                },
                mapCallback : async function(data){
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
            onlyContent : true,
            code : "001",
            dataUrl : "/map/monitoring/operation/M_SERVICE_OPERATION_001.ajax",
            hasModal : true,
            afterCallback : function(){

            },
            mapCallback : async function(data){
            }
        },
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
            },{
                menuNm : "교통량",
                title : "교통량 분석",
                code : "002",
                dataUrl : "/map/bigdata/pattern/",
                hasModal : true,
                afterCallback : function(){
					
                },
                mapCallback : function(data){
                    //map.bigdata.getPatternTrafficQuantity();
                }
            },{
                menuNm : "평균속도",
                title : "평균 속도 분석",
                code : "003",
                dataUrl : "/map/bigdata/pattern/",
                hasModal : true,
                afterCallback : function(){

                },
                mapCallback : async function(data){
                }
            },{
                menuNm : "정체구간",
                title : "정체 구간 분석",
                code : "004",
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
                {
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
                },{
                    menuNm : "정체구간<br/>개선효과",
                    title : "정체 구간 개선 효과 조회하기",
                    code : "002",
                    dataUrl : "/map/bigdata/effect/analysis/",
                    hasModal : true,
                    afterCallback : function(){
                        app.drawDualMap(null, null, function(){

                        });
                    },
                    mapCallback : async function(data){

                    }
                },{
                    menuNm : "긴급차량 우선<br/>신호시스템<br/>제어효과",
                    title : "긴급차량 우선 신호 제어 효과 조회하기",
                    code : "003",
                    dataUrl : "/map/bigdata/effect/analysis/",
                    hasModal : true,
                    afterCallback : function(){
                        app.removeDualMap();
                    },
                    mapCallback : async function(data){
                    }
                }
            ]},
        "BD_DANGER_ZONE" : {
            callback : function(){
                map.monitoring.removeBusRouteInfo();
            },
            aside : [
                {
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
                },{
                    menuNm : "도로<br/>안전 정보",
                    title : "도로 안전 정보 조회하기",
                    code : "002",
                    dataUrl : "/map/bigdata/danger/zone/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){

                    }
                },{
                    menuNm : "교통사고<br/>위험지역 정보",
                    title : "교통사고 위험 지역 정보 조회하기",
                    code : "003",
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
                },{
                    menuNm : "교차로<br/>교통량 예측",
                    title : "교차로 교통량 예측 정보 조회하기",
                    code : "002",
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
                    code : "003",
                    dataUrl : "/map/bigdata/prediction/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : function(){
                        map.bigdata.removeBigDataLayers();
                    }
                }
            ]},
        "BD_LINK" : {
            callback : function(){
                map.monitoring.removeBusRouteInfo();
            },
            aside : [
                {
                    menuNm : "더 많은<br/>데이터 보기",
                    title : "더 많은 데이터 보기",
                    code : "001",
                    disableDirectActive : true,
                    dataUrl : "/map/bigdata/link/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                },{
                    menuNm : "교통신호<br/>연동방식",
                    title : "교통 신호 연동 분석",
                    code : "002",
                    dataUrl : "/map/bigdata/link/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                }
            ]},
        "BD_PB_TRANS" : {
            callback : function(){
                map.monitoring.getBusRouteInfo();
                map.monitoring.getBusStationInfo();
            },
            aside : [
                {
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
                },{
                    menuNm : "기종점<br/>대중교통<br/>이용량",
                    title : "기종점 대중교통 이용량 분석",
                    code : "002",
                    dataUrl : "/map/bigdata/tb/trans/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                },{
                    menuNm : "권역별<br/>대중교통<br/>이용현황",
                    title : "권역별 대중교통 이용 현황 분석",
                    code : "003",
                    dataUrl : "/map/bigdata/tb/trans/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                },{
                    menuNm : "권역별<br/>환승현황<br/>분석",
                    title : "대중교통 환승 현황 분석",
                    code : "004",
                    dataUrl : "/map/bigdata/tb/trans/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                },{
                    menuNm : "정류장별<br/>대중교통 및 <br/>노선 시설물",
                    title : "정류장별 대중교통 및 노선, 시설물 조회",
                    code : "005",
                    dataUrl : "/map/bigdata/tb/trans/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : function(data){
                        map.monitoring.getBusStationInfo();
                    }
                }, {
                    menuNm: "정류장별<br/>버스 이용률",
                    title: "정류장별 버스 이용률 조회",
                    code: "006",
                    dataUrl: "/map/bigdata/tb/trans/",
                    hasModal : true,
                    hasChart: false,
                    afterCallback: function () {

                    },
                    mapCallback: async function (data) {
                    }
                },{
                    menuNm : "정류장별<br/>버스노선<br/>및 BIT",
                    title : "정류장별 버스 노선 및 BIT 조회",
                    code : "007",
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
                },{
                    menuNm : "노선구간별<br/>승하차/재차<br/>승객 수 조회",
                    title : "노선구간별 승하차/재차 승객 수 조회",
                    code : "002",
                    dataUrl : "/map/bigdata/bus/road/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                },{
                    menuNm : "노선구간별<br/>수용성 및<br/>굴곡도분석",
                    title : "노선구간별 수용성 및 굴곡도 조회",
                    code : "003",
                    dataUrl : "/map/bigdata/bus/road/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                },{
                    menuNm : "노선구간별<br/>중복구간 도출<br/>및 적정성 분석",
                    title : "노선구간별 중복구간 도출 및 적정성 조회",
                    code : "004",
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
                },{
                    menuNm : "대중교통<br/>안전 운행분석",
                    title : "대중교통 안전 운행 분석",
                    code : "002",
                    dataUrl : "/map/bigdata/bus/danger/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : function(){
                    }
                }
            ]},
        "BD_PB_PREDICTION" : {
            callback : function(){
            },
            aside : [
                {
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
                },{
                    menuNm : "유동인구 밀집<br/>예측 분석 조회",
                    title : "유동인구 밀집 예측 분석 조회",
                    code : "002",
                    dataUrl : "/map/bigdata/pb/prediction/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){

                    }
                },{
                    menuNm : "버스노선<br/>최적화<br/>예측분석",
                    title : "버스노선 최적화 예측 분석 조회",
                    code : "003",
                    dataUrl : "/map/bigdata/pb/prediction/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
                    }
                }
            ]},
        "F_FACILITY" : {
            callback : function(){
                map.facility.getSmartIntersection();
                map.facility.getVds();
                map.facility.getDSRC();
                /*
                map.facility.getSignal();*/
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
                    mapCallback : async function(data){
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
                    mapCallback : async function(data){
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
                    }
                },
                {
                    menuNm : "신호정보",
                    /*title : this.menuNm + "시설물 찾기",*/
					title: "신호정보 시설물 찾기",
                    code : "004",
                    dataUrl : "/map/facility/information/",
                    hasModal : true,
                    afterCallback : function(){

                    },
                    mapCallback : async function(data){
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
            }
            if(prop.indexOf("M_") === 0) {
                map.monitoring.removeAddedMonitoringLayer();
                map.animate.animateStop();
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
            const $li = $("<li></li>");
            $li.addClass("side_item");
            if (item.disableDirectActive === true) {
                $li.attr("data-disabled-active", 'true');
            } else {
                $li.attr("data-disabled-active", 'false');
            }
            const $button = $(`<button class="map_load_data_btn" data-hasmodal="${item.hasModal}" data-type="${type}" data-title="${item.title}" data-url="${item.dataUrl}" data-code="${item.code}" data-chart="${item.hasChart}">${item.menuNm}</button>`);
            $button.addClass("is-side-btn");
            $li.append($button);
            $ul.append($li);
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
        return $aside;
    }

    const generateDashboardContentDefault = function(title){
        let $contentBox = $(`<div class='dashboard_box'></div>`);
        let $contentWrap = $("<div class='dashboard_wrap dash_bg'></div>");
        let $contentHeader = $("<div class='tab_box_header'></div>");
        let $contentBody = $("<div class='tab_box_body'></div>");
        let $contentFooter = $("<div class='tab_box_footer'></div>");
        $contentWrap.append($contentHeader).append($contentBody).append($contentFooter);
        $contentBox.append($contentWrap);

        // header set
        $contentHeader.append(`<div class='tab_box_title'>${title}</div>`);
        let $contentHeaderRightWrap = $("<div class='tab_box_close'><div class='opa_slider'></div></div>");
        /*let $contentHeaderMinimalize = $("<div class='tab_hamburger'><span></span><span></span><span className='mj0'></span></div>");*/
        let $contentHeaderClose = $("<button><img src='/statics/images/wh_close.png' style='width:0.75rem;height:0.75rem'></button>");
        $contentHeaderRightWrap.append($contentHeaderClose);
        $contentHeader.append($contentHeaderRightWrap);
        $contentHeaderClose.on("click", function(){
            $contentBox.remove();
            $(".map_load_data_btn.active").removeClass("active");
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
    const generateDashboardContent = function(title, content, isClear = true){
        let $dashboardContainer = $("#aside_dashboard_container");
        if(isClear) $dashboardContainer.empty();
        let dashboardContent = generateDashboardContentDefault(title);
        let $contentWrapper = dashboardContent.getWrapper();
        dashboardContent.setContent(content);
        $dashboardContainer.append($contentWrapper);
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
                <div class="flex-center gap24">
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
		dateTiemInit();
		datePickerInit();
		
		button.on("click", function(){
	        var trafficRemrks = $(`
		        <div class="remarks_container">
			        <div class="remarks_title_box">
			            <h6 class="remarks_title">범례 - 교통량 증감수</h6>
			        </div>
		        	<div class="remarks_wrap tab-none">
		            	<div>
			                <div class="check_line_container">
			                    <button type="button"class="check_line_box remarks-red">14,001 - 100,000</button>
			                    <button type="button" class="check_line_box remarks-orange">8,001 - 14,000</button>
			                    <button type="button" class="check_line_box remarks-light-orange">5,001 - 8,000</button>
			                    <button type="button" class="check_line_box remarks-light-blue">2,001 - 5,000</button>
			                    <button type="button" class="check_line_box remarks-blue">- 2,000</button>
			                </div>
		            	</div>
			            <div class="unit">단위 : (일/대)</div>
		        	</div>
		    	</div>`);

	        var speendRemrks = $(`
		        <div class="remarks_container">
			        <div class="remarks_title_box">
			            <h6 class="remarks_title">범례 - 평균속도 증감수</h6>
			        </div>
		        	<div class="remarks_wrap tab-none">
		            	<div>
			                <div class="check_line_container">
			                    <button type="button"class="check_line_box remarks-red"></button>
			                    <button type="button" class="check_line_box remarks-orange"></button>
			                    <button type="button" class="check_line_box remarks-light-orange"></button>
			                    <button type="button" class="check_line_box remarks-light-blue"></button>
			                    <button type="button" class="check_line_box remarks-blue"></button>
			                </div>
		            	</div>
			            <div class="unit">단위 : (속도/km/h)</div>
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
		})
		
    }
    app.startLoading = function(){
        if($("#mapLoadingLayer").length === 0) {
            const layer = $(`<div id="mapLoadingLayer"><div class="feeder"><div></div><div></div><div></div></div></div>`);
            $("body").append(layer);
        }
    }
    app.endLoading = function(){
        $("#mapLoadingLayer").fadeOut(250, function(){
            $("#mapLoadingLayer").remove();
        });
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

        let loadMenuObj = menuObject[menuIdMapping[menuPttrnType]];
        let $section = generateAside();
        let $dashboardContainer = $("<div id='aside_dashboard_container'></div>");
        let $asideMenu = null;
        $dashboardContainer.addClass("dashboard_container flex-center gap16 tab_box");
        if(loadMenuObj && loadMenuObj.onlyContent === true){
            let html = await app.getContent(menuObject[menuIdMapping[menuPttrnType]].dataUrl);
            $dashboardContainer.empty();
            $dashboardContainer.html(html);
        }else{
            $asideMenu = generateAsideMenu(menuIdMapping[menuPttrnType], menuObject[menuIdMapping[menuPttrnType]].aside);
            $section.append($asideMenu);
        }
        $section.append($dashboardContainer);
        $wrap.append($section);
        if(typeof loadMenuObj.callback === "function") loadMenuObj.callback();
        if($asideMenu) $asideMenu.find("li[data-disabled-active='false']").eq(0).find("button").click();
        if($asideMenu) $asideMenu.find("li[data-disabled-active='true']").eq(0).css('margin-bottom', '2rem');
    }

    app.callMenuAction = function(menuPttrnType = "EVC000", checkedVal = false) {
        let loadMenuObj = menuObject[menuIdMapping[menuPttrnType]];
		if(checkedVal){
	        if(typeof loadMenuObj.callback === "function") loadMenuObj.callback();
		}else{
	        if(typeof loadMenuObj.removeCallback === "function") loadMenuObj.removeCallback();
		}
    }

    app.generatePushElement = function(type, title, id, event){
        const class_type = {
            "DANGER" : "remarks-red",
            "WARNING" : "remarks-orange",
        }
        let html = $(`<li data-id="${id}" class="outbreak_push_item ${class_type[type]}">
                    	<button type="button">${title}</button>
                    	<button type="button" class="outbreak_push_close"><img src="statics/images/wh_close.png" alt="닫기"></button>
                	</li>`);
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
        let menuId = $(this).data("id");
        let code = $(this).data("code");
        let url = $(this).data("url");
        let type = $(this).data("type");
        let hasmodal = $(this).data("hasmodal");

        // generateDashboardContent(title, "html code", true);

        //let html = await app.getContent(url);
        if(hasmodal === true) {
            let html = await app.getContent(url + type + "_" + code + ".ajax");
            generateDashboardContent(title, html);
        }else{
            let $dashboardContainer = $("#aside_dashboard_container");
            $dashboardContainer.empty();
        }

        $(".map_load_data_btn.active").removeClass("active");
        $(this).addClass("active");

        dashboardContentOpenEvent();
    });

}

let gitsApp = null;
$(function(){
    gitsApp = new GitsDataApp();
})

