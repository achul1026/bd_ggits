/**
 * Map core
 * */
let GITSMapCore = function(elementId){
    let core = this;
    let _Map = null;
    let _Worker = null;
    let _Util = new GitsMapUtil();
    let openMarkers = [];
    let LAYER = GITS_ENV.LAYER;
    let STYLES = GITS_ENV.STYLES;
    let LEGEND_COLOR = GITS_ENV.LEGEND_COLOR;
    let customSourceLayerIds = [];
    let defaultLayer = null;
    let savedDataForDualMap = null;
    let markersOnScreen = {};
    let selectedSearchOption = {};
    let jobList = [];
    const SESSION_KEY = "gitsmap";
    const SESSION_PROP = GITS_ENV.SESSION_PROP;
    const LOG_LEVEL = "";
    const token = "pk.eyJ1IjoiZGVzaW1pbiIsImEiOiJjbGxsdGt2N2oxaXJ3M3BxdXI0eGg2NTZhIn0.XXk19wwTN3uAycRqs-G7FA";
    // const tileset_id = "desimin.clllyrgwf02rg2oprgc0p9wgf-5249e";
    // const tileset_layer = "ggits_node_link_dev"
    let link_tileset_id = "desimin.89o6kai5";
    let link_tileset_layer = "ggd_link";
    const monitoring_link_tileset_id = "desimin.11qq514i";
    const monitoring_link_tileset_layer = "ggits_link_monitoring";
    const node_tileset_id = "desimin.ggits_node";
    const node_tileset_layer = "ggits_node";
    /*const grid_tileset_id = "desimin.275boefx";
    const grid_tileset_layer = "B100_1KM__202304_-_-10hnns";*/
    const grid_tileset_id = "desimin.ggits_grid_500";
    const grid_tileset_layer = "ggits_500_grid";
    const languageControl = new MapboxLanguage({defaultLanguage: 'ko'});
    const initZoom = 8;
    const opt = {
        container: elementId,
        style:  GITS_ENV.MAP_STYLES.DARK,
        /*style: 'mapbox://styles/mapbox/streets-v12',*/
        zoom: initZoom,
        minZoom: 3,
        maxZoom: 22,
        center: [127.1267772, 37.4200267],
        maxBounds : [
            [125.38,36.79],
            [128.95,38.38]
        ]
    };

    /**
     * 로컬 세션정보 가져오기
     * @private
     */
    const _getSession = function(key){
        let sessionInfo = window.localStorage.getItem(SESSION_KEY);
        if(sessionInfo == null) {
            sessionInfo = _setDefaultSession();
        }
        let sessionJsonObject =  JSON.parse(sessionInfo);
        return sessionJsonObject[key];
    }

    const _setDefaultSession = function(){
        let defaultEssionInfo = JSON.stringify({});
        window.localStorage.setItem(SESSION_KEY, defaultEssionInfo);
        return defaultEssionInfo;
    }

    /**
     * 로컬 세션정보 저장
     * @private
     */
    const _setSession = function(key, value){
        let sessionInfo = window.localStorage.getItem(SESSION_KEY);
        if(sessionInfo == null) {
            sessionInfo = _setDefaultSession();
        }
        let sessionJsonObject =  JSON.parse(sessionInfo);
        sessionJsonObject[key] = value;
        window.localStorage.setItem(SESSION_KEY, JSON.stringify(sessionJsonObject));
    }
    const _getAlarmClosed = function(value){
        let sessionInfo = window.localStorage.getItem(SESSION_KEY);
        const sessionObj = JSON.parse(sessionInfo);
        if(sessionObj[SESSION_PROP.WARNING_CLOSED] == null) {
            sessionObj[SESSION_PROP.WARNING_CLOSED] = [];
        }

        return sessionObj[SESSION_PROP.WARNING_CLOSED].indexOf(value) > -1;
    }
    const _setAlarmClosed = function(value) {
        let sessionInfo = window.localStorage.getItem(SESSION_KEY);
        const sessionObj = JSON.parse(sessionInfo);
        if (sessionObj[SESSION_PROP.WARNING_CLOSED] == null) {
            sessionObj[SESSION_PROP.WARNING_CLOSED] = [];
        }
        sessionObj[SESSION_PROP.WARNING_CLOSED].push(value);
        window.localStorage.setItem(SESSION_KEY, JSON.stringify(sessionObj));
    }

    /**
     * 메서드 reflect
     * @param target
     * @returns {*}
     * @private
     */
    const _InitCheck = function(target) {
        if(LOG_LEVEL === "DEBUG")
            console.log("GITS Core map proxy init");
        return new Proxy(target, {
            get: function(obj, prop) {
                var value, name;
                if (!Reflect.has(obj, prop)) {
                    return;
                }
                value = Reflect.get(obj, prop);
                if (typeof value === 'function') {
                    value = function() {
                        if(LOG_LEVEL == "DEBUG") console.log(`function ${prop}`);
                        if(prop !== "init" && typeof _Map == "undefined"){
							new ModalBuilder().init().alertBoby("Not initialized GITS Map").footer(4,'확인',function(button, modal){modal.close();}).open();
							modalAlertWrap();
                        }
                        let result = Reflect.apply(obj[prop], obj, arguments);
                        return result;
                    }.bind(obj);
                }
                return value;
            }
        });
    }

    const _RemoveJobItem = function(target) {
        return new Proxy(target, {
            get: function(obj, prop) {
                var value, name;
                if (!Reflect.has(obj, prop)) {
                    return;
                }
                value = Reflect.get(obj, prop);
                if (typeof value === 'function') {
                    resetTimer();
                    value = function() {
                        if(arguments[0] && arguments[0].data && arguments[0].data.jobId) {
                            let arg  = arguments[0];
                            setTimeout(function(){
                                $("#mapDataLoadingJobList [data-job-id='"+arg.data.jobId+"']").stop().slideUp(250, function(){
                                    $(this).remove();
                                    let index = jobList.indexOf(arg.data.jobId);
                                    if (index !== -1) {
                                        jobList.splice(index, 1);
                                    }
                                    if(jobList.length === 0) {
                                        gitsApp.endLoading();
                                    }
                                });
                            }, 1000);
                        }
                        if(arguments[0] && arguments[0].data && arguments[0].data.isThrow === true) {
                            // 데이터 수신에러 공통화
                            let arg  = arguments[0];
                            let msg = arg.data.eventName+" 조회 오류";
							new ModalBuilder().init().alertBoby(msg).footer(4,'확인',function(button, modal){modal.close();}).open();
							modalAlertWrap();
                            /*gitsApp.generatePushElement("DANGER", msg, arg.data.jobId, function () {
                                _setAlarmClosed(arg.data.jobId);
                            });*/
                            return;
                        }
                        let result = Reflect.apply(obj[prop], obj, arguments);
                        return result;
                    }.bind(obj);
                }
                return value;
            }
        });
    }

    /**
     * gits worker set
     * @private
     */
    const _loadSources = async function (){
        _Worker = new Worker("/statics/map/ggits.map.worker.js?t="+new Date().getTime());

        _Worker.onmessage = function(e){
            if(LOG_LEVEL === "DEBUG")
                console.log("Event Result Name : " + e.data.event);
            if(e.data.event) {
                workerResultEvent[e.data.event](e);
            }else{
                console.log("Event Worker Data is empty", e.data);
            }
        };
        // icon load
        for(const icon of GITS_ENV.ICONS) {
            let data = await _Map.loadImage(icon.url, function(err, image){
                _Map.addImage(icon.id, image)
            });
        }
    }

    const _visibleChangeEventInit = function(){
        document.addEventListener("visibilitychange", () => {
            if (document.hidden) {
                _Worker.postMessage({pause:true});
            } else {
                _Worker.postMessage({resume:true});
            }
        });
    }

    const _getUniqueFeatures = function(features, comparatorProperty, comparatorId) {
        let uniqueFeature = null;
        for (const feature of features) {
            const id = feature.properties[comparatorProperty];
            if(id == comparatorId) {
                uniqueFeature = feature;
                break;
            }
        }
        return uniqueFeature;
    }

    const _mapSameMoveToDualMap = function(){
        if(__Map && typeof __DualMap !== "undefined") {
            __DualMap.jumpTo({center: [__Map.getCenter().lng, __Map.getCenter().lat]})
            __DualMap.setZoom(__Map.getZoom());
        }
    }

    const _mapSameMoveToMap = function(){
        if(__Map && typeof __DualMap !== "undefined") {
            __Map.jumpTo({center: [__DualMap.getCenter().lng, __DualMap.getCenter().lat]})
            __Map.setZoom(__DualMap.getZoom());
        }
    }
    core.getJobList = function(){
        return jobList;
    }
    core.getMapboxGl = function(){
        return _Map;
    }
    let loaded = false;
    core.isLoaded = function(){
        return loaded;
    }

    core.getSavedData = function(){
        return savedDataForDualMap;
    }


    /**
     *
     * @returns {GITSMapCore}
     */
    core.init = function(page, city, isDualMap = false, func) {
        console.log("page", page);
        if(page === "MONITORING") {
            link_tileset_id = monitoring_link_tileset_id;
            link_tileset_layer = monitoring_link_tileset_layer;
        }

        new MapControl();
        new Monitoring();
        new Facility();
        new BigData();
        new MapAnimate();
        defaultLayer = new GitsLayer();

        // 저장된 세션정보로 스타일세팅
        opt.style = _getSession(SESSION_PROP.MAP_STYLE) ? _getSession(SESSION_PROP.MAP_STYLE) : GITS_ENV.MAP_STYLES.DARK;

        _setSession(SESSION_PROP.MAP_STYLE, opt.style);

        // 한글언어셋
        if(mapboxgl.getRTLTextPluginStatus() === "unavailable")
            mapboxgl.setRTLTextPlugin('https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-rtl-text/v0.2.3/mapbox-gl-rtl-text.js');

        mapboxgl.accessToken = token;
        if(isDualMap) {
            opt.center = [__Map.getCenter().lng, __Map.getCenter().lat];
        }
        _Map = new mapboxgl.Map(opt);

        if(isDualMap) {
            window.__DualMap = _Map;
            __Map.on("mouseover", function(e){
                __Map.on("move", _mapSameMoveToDualMap);
            });
            __Map.on("mouseout", function(e){
                __Map.off("move", _mapSameMoveToDualMap);
            });
            __DualMap.on("mouseover", function(e){
                __DualMap.on("move", _mapSameMoveToMap);
            });
            __DualMap.on("mouseout", function(){
                __DualMap.off("move", _mapSameMoveToMap);
            });
        }else{
            window.__Map = _Map;
        }

        if(!opt.style.includes("satellite")) _Map.addControl(languageControl);

        _loadSources().then();

        _visibleChangeEventInit()

        if($("#currentMapZoom").length > 0) {
            $("#currentMapZoom").text(initZoom);
        }
        _Map.on("zoom", function(){
            if($("#currentMapZoom").length > 0) {
                $("#currentMapZoom").text(parseInt(_Map.getZoom()));
            }
        });
        _Map.on('load', function() {

            _Map.addSource(LAYER.SEPARATE, {
                type: 'geojson',
                data: { type: 'FeatureCollection', features: [] }
            });
            _Map.addLayer({
                id: LAYER.SEPARATE,
                type: 'symbol',
                source: LAYER.SEPARATE
            });


            defaultLayer.drawLinkLayer(page);
            defaultLayer.drawNodeLayer();
            defaultLayer.drawSGGLayer();


            // 그리드
            _Map.addSource(LAYER.GRID, {
                "type": "vector",
                "url": "mapbox://"+grid_tileset_id,
                'minzoom': 6,
                'maxzoom': 22,
                "promoteId" : {"ggits_500_grid":"OBJECTID"}
            });

            _Map.addLayer({
                'id': LAYER.GRID,
                'type': 'fill',
                'source': LAYER.GRID,
                'source-layer': grid_tileset_layer,
                'layout': {
                    "visibility" : "none"
                },
                'paint': {
                    'fill-color': 'transparent',
                    'fill-outline-color' : '#ffffff',
                    'fill-opacity' : 0.7
                }
            });



            switch(page) {
                case "MONITORING" :
                    core.setMonitoring();
                    break;
                case "MONITORING_DASHBOARD" :
                    core.setMonitoringDashboard();
                    break;
                case "BIGDATA" :
                    break;
            }
			if(typeof func === "function") func();
        });
        _Map.on('style.load', function() {
            loaded = true;
            if(LOG_LEVEL === "DEBUG")
                console.log("loaded",loaded);
        });


        return core;
    }

    function GitsLayer(){

        this.drawNodeLayer = function(){
            if(_Map.getLayer(LAYER.NODE)) {
                _Map.removeLayer(LAYER.NODE)
            }

            if(_Map.getSource(LAYER.NODE)) {
                _Map.removeSource(LAYER.NODE);
            }

            // 노드
            _Map.addSource(LAYER.NODE, {
                "type": "vector",
                "url": "mapbox://"+node_tileset_id,
                'minzoom': 6,
                'maxzoom': 22,
                "promoteId" : {"ggits_node":"NODE_ID"}
            });

            // node layer 설정
            let nodeLayerObj = {
                'id': LAYER.NODE,
                'type': 'circle',
                'source': LAYER.NODE, // reference the data source
                'source-layer': node_tileset_layer,
                'paint': {
                    'circle-radius': {
                        'base': 1.75,
                        'stops': [
                            [12, 2],
                            [22, 180]
                        ]
                    },
                    /*'circle-color': [
                        'match',
                        ['get', 'layer'],
                        '안양시_node',
                        '#2173fd',
                        /!* other *!/
                        STYLES.NODE_COLOR
                    ],*/
                    'circle-color' : STYLES.NODE_COLOR
                },
            }
            nodeLayerObj.filter = ["has","NODE_ID"];
            _Map.addLayer(nodeLayerObj);
        }

        this.drawLinkLayer = function(page){

            if(_Map.getLayer(LAYER.LINK)) {
                _Map.removeLayer(LAYER.LINK)
            }

            if(_Map.getSource(LAYER.LINK)) {
                _Map.removeSource(LAYER.LINK);
            }

            let linkSourceObj = {
                "type": "vector",
                "url": "mapbox://"+link_tileset_id,
                'minzoom': 6,
                'maxzoom': 22,
                "promoteId" : {"ggd_link":"LINK_ID"}
            }
            if(page === "MONITORING") {
                linkSourceObj.promoteId = {"ggits_link_monitoring" :"LINK_ID"};
            }
            console.log("linkSourceObj")
            // 링크
            _Map.addSource(LAYER.LINK, linkSourceObj);
            let linkLayerObj = {
                'id': LAYER.LINK,
                'type': 'line',
                'source': LAYER.LINK, // reference the data source
                'source-layer': link_tileset_layer,
                'layout': {
                    'line-join': 'round',
                    'line-cap': 'round'
                },
                'paint': {
                    'line-color': STYLES.LINK_COLOR,
                    'line-opacity': 0.8,
                    'line-width': [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        10, 2,
                        15, 4
                    ]
                }
            }
            linkLayerObj.filter = ["has","LINK_ID"];
            /*if(city) {
                linkLayerObj.filter = ["all", ["has","LINK_ID"], ["==","layer", city+"_link"]];
            }*/
            _Map.addLayer(linkLayerObj);
        }
        this.drawSGGLayer = function(){
            // 시군구
            if(typeof _Map.getSource(LAYER.SGG) === "undefined") {
                _Map.addSource(LAYER.SGG, {
                    "type": "vector",
                    "url": "mapbox://desimin.7bl1ubuc",
                    'minzoom': 9,
                    'maxzoom': 22
                });
            }

            let colorCaseSGG = ['case'];
            let sggInfo = GITS_ENV.SGG_INFO;
            for(const sggNm in GITS_ENV.SGG_INFO) {
                const sgg = sggInfo[sggNm];
                colorCaseSGG.push(['==', ['get','COL_ADM_SE'], sgg.CODE]);
                colorCaseSGG.push(sgg.COLOR);
            }
            colorCaseSGG.push("transparent");
            if(typeof _Map.getLayer(LAYER.SGG) === "undefined") {
                _Map.addLayer({
                        'id': LAYER.SGG,
                        'type': 'fill',
                        'source': LAYER.SGG, // reference the data source
                        'source-layer': "LARD_ADM_SECT_SGG_-85ifr6",
                        'maxzoom' : 13,
                        'minzoom' : 6,
                        'layout': {},
                        'paint': {
                            'fill-color': colorCaseSGG, // blue color fill
                            'fill-opacity': 0.3
                        }
                    });
                _Map.addLayer({
                    'id': LAYER.SGG_LINE,
                    'type': 'line',
                    'source': LAYER.SGG, // reference the data source
                    'source-layer': "LARD_ADM_SECT_SGG_-85ifr6",
                    'maxzoom' : 13,
                    'minzoom' : 6,
                    'layout': {
                        'line-join': 'round',
                        'line-cap': 'round'
                    },
                    'paint': {
                        'line-color': colorCaseSGG,
                        'line-opacity': 0.8,
                        'line-width': 1
                    },
                });
            }

        }
    }


    function Facility(){
        core.facility = this;
        let facility = core.facility;
        facility.getSmartIntersection = function(){
            _Worker.postMessage({
                event : "F_SMART",
                jobId : core.control.generateJobId("스마트교차로 정보 데이터 로딩")
            });
        }

        facility.removeSmartIntersection = function(){
            core.control.removeCustomSource(LAYER.FACILITY_SMART);
        }
        
        facility.getVds = function(){
            _Worker.postMessage({
                event : "F_VDS",
                jobId : core.control.generateJobId("VDS 정보 데이터 로딩")
            });
        }

        facility.removeVds = function(){
            core.control.removeCustomSource(LAYER.FACILITY_VDS);
        }
        
        facility.getDSRC = function(){
            _Worker.postMessage({
                event : "F_DSRC",
                jobId : core.control.generateJobId("DSRC 정보 데이터 로딩")
            });
        }

        facility.removeDSRC = function(){
            core.control.removeCustomSource(LAYER.FACILITY_DSRC);
        }
        
        facility.getSignal = function(){
            _Worker.postMessage({
                event : "F_SIGNAL",
                jobId : core.control.generateJobId("신호 정보 데이터 로딩")
            });
        }

        facility.removeSignal = function(){
            core.control.removeCustomSource(LAYER.FACILITY_SIGNAL);
        }
        
        
    }

    function BigData() {
        core.bigdata = this;
        let bigdata = core.bigdata;

        bigdata.removeBigDataLayers = function(layerIds){
            try {
                if (layerIds) {

                } else {
                    for(const m in markersOnScreen) {
                        markersOnScreen[m].remove();
                    }
                    markersOnScreen = {}
                    const allLayers = _Map.getStyle().layers;
                    // 빅데이터 레이어 전체 삭제
                    for (const layer of allLayers) {
                        if (layer.id.includes("GITS_BD_")) {
                            if (_Map.getLayer(layer.id))
                                _Map.removeLayer(layer.id);
                        }
                    }
                    if (_Map.getLayer(LAYER.NODE))
                        core.control.setPaintDefault(LAYER.NODE);
                        core.control.showLayer(LAYER.NODE);
                    if (_Map.getLayer(LAYER.LINK)) {
                        core.control.setPaintDefault(LAYER.LINK);
                        _Map.setFilter(LAYER.LINK, null);
                        core.control.showLayer(LAYER.LINK);
                    }
                    if (_Map.getLayer(LAYER.GRID)) {
                        core.control.hideLayer(LAYER.GRID);
                    }
                    /*core.control.hideLayer(LAYER.LINK);
                    core.control.hideLayer(LAYER.NODE);*/
                    if (_Map.getLayer(LAYER.BUS_STATION))
                        core.control.hideLayer(LAYER.BUS_STATION);
                    if (_Map.getLayer(LAYER.CROSSROAD_CAMERA))
                        core.control.hideLayer(LAYER.CROSSROAD_CAMERA);
                }

            }catch(e) {
                console.log(e);
            }
            for(const ev in MapEvents) {
                if(ev.indexOf("BD_") === 0) {
                    _Map.off("click", LAYER.LINK, MapEvents[ev]);
                    _Map.off("click", LAYER.NODE, MapEvents[ev]);
                }
            }
            $('.chart_video_container').hide();
        }

        /**
         * 유동인구 예측 밀집 조회
         * @param date 날짜?
         * @param time 0100 , 0200
         */
        bigdata.getPopulationCongestionInfo = function(searchOption){
            let data = {};
            data.event = "BD_POPULATION";
            data.loop = false;
            data.jobId = core.control.generateJobId("인구밀집 예측데이터 로딩중");
            data.searchOption = searchOption;
            _Worker.postMessage(data);
        }

        /**
         * 대중교통 최적화 후보노선 조회
         * @param searchOption
         */
        bigdata.getPublicTransferCndcyRouteInfo = function(searchOption) {
            _Worker.postMessage({
                event : "BD_PUBLIC_TRANSFER_CNDCY_ROUTE",
                searchOption : searchOption,
                jobId : core.control.generateJobId("대중교통 최적화 후보노선 데이터 로딩")
            });
            const so = _Util.convertParamToObject(searchOption);
            _Worker.postMessage({
                event: "CM_BUS_ROUTE",
                routeId: so.routeId,
                jobId: core.control.generateJobId("기존 버스 노선 정보 로딩중")
            });
            _Worker.postMessage({
                event: "CM_BUS_STATION",
                searchOption : searchOption
            });
        }

        /**
         * 대중교통 위험운영 구간
         * @param yyyy
         */
        bigdata.getPublicTransferDangerSectionInfo = function(searchOption = ''){
            bigdata.removeBigDataLayers();
            const so = _Util.convertParamToObject(searchOption);
            _Worker.postMessage({
                event : "BD_PT_DANGER_ANALYSIS",
                searchOption : searchOption,
                jobId : core.control.generateJobId("대중교통 위험운영 구간 데이터 로딩")
            });
            _Worker.postMessage({
                event: "BD_BUS_STATION",
                searchOption : searchOption,
                jobId: core.control.generateJobId("버스 정류장 정보 로딩중")
            });
        }

        /**
         * 교통예측분석 - 교차로 교통량 예측
         */
        bigdata.getCrossPrediction = function(searchOption = ''){
            _Worker.postMessage({
                event : "BD_PREDICTION_CROSS_TRAFFIC",
                searchOption : searchOption,
                jobId : core.control.generateJobId("교차로 교통량 예측 데이터 로딩")
            });
        }

        /**
         * 교통량&평균속도 데이터 조회
         */
       	bigdata.getPatternTrafficQuantity = function(searchOption = ''){
            _Worker.postMessage({
                event : "BD_PATTERN_TRAFFIC_QUANTITY",
				searchOption  : searchOption,
                jobId : core.control.generateJobId("교통량 데이터 로딩")
            });
        }
		
		/**
         * 교통활동 효과분석 데이터 조회
         */
       	bigdata.getTrafficActiveEffectAnalysis = function(searchOption = ''){
            _Worker.postMessage({
                event : "BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS",
				searchOption  : searchOption,
                jobId : core.control.generateJobId("교통활동 효과분석 데이터 로딩")
            });
        }
        
        bigdata.getTrafficAccidentPrediction = function(searchOption = '') {
            _Worker.postMessage({
                event : "BD_PREDICTION_ACCIDENT",
                searchOption  : searchOption,
                jobId : core.control.generateJobId("사고 예측구간 데이터 로딩")
            });
        }

        /**
         * 지도병합
         * @param beforeData
         * @param afterData
         */
        bigdata.getTrafficEffectAnalysisMerge = function(searchOption){
            _Worker.postMessage({
                event : "BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS_MERGE",
                searchOption  : searchOption,
                jobId : core.control.generateJobId("교통할동 분석 병합 데이터 로딩")
            });
        }

        /**
         * 행정시 사고구역 데이터 로딩
         */
        bigdata.getCityAccidentInfo = function(searchOption = ''){
            _Worker.postMessage({
                event : "BD_DANGER_ZONE",
                searchOption  : searchOption,
                jobId : core.control.generateJobId("행정구역 사고구역 데이터 로딩")
            });
        }

        /**
         * 도로안전정보 데이터
         */
        bigdata.getRoadDangerInfo = function(searchOption){
            _Worker.postMessage({
                event : "BD_DANGER_ROAD",
                searchOption : searchOption,
                jobId : core.control.generateJobId("도로안전 정보 데이터 로딩")
            });
        }

        /**
         * 행정구역별 교통사고 위험지역 데이터 로딩
         */
        bigdata.getAccidentBySGGInfo = function(searchOption){
            _Worker.postMessage({
                event : "BD_ACCIDENT_BY_SGG",
                searchOption : searchOption,
                jobId : core.control.generateJobId("행정구역별 사고정보 데이터 로딩")
            });
        }

        /**
         * 교통사고 위험지역 정보
         */
        bigdata.getAccidentDangerAreaInfo = function(searchOption){
            _Worker.postMessage({
                event : "BD_DANGER_ZONE_BY_TYPE",
                searchOption : searchOption,
                jobId : core.control.generateJobId("사고별 데이터 로딩")
            });
        }


        /**
         * 대중교통 이용현환 분석 > 기종점 대중교통 이용량
         * @param searchOption
         */
        bigdata.getPublicTransferUsageByStEnd = function(searchOption){
            bigdata.removeBigDataLayers();
            _Worker.postMessage({
                event : "BD_PUBLIC_TRANSFER_USAGE_BY_ST_END",
                searchOption : searchOption,
                jobId : core.control.generateJobId("기종점 대중교통 이용량 데이터 로딩")
            });
            _Worker.postMessage({
                event: "BD_BUS_STATION",
                searchOption : searchOption,
                jobId: core.control.generateJobId("버스정류장 정보 로딩중")
            });
        }

        /**
         * 대중교통 이용현환 분석 > 권역별 대중교통 이용현황
         * @param searchOption
         */
        bigdata.getPublicTransferUsageGroupBySGG = function(searchOption){
            bigdata.removeBigDataLayers();

            _Worker.postMessage({
                event : "BD_PUBLIC_TRANSFER_USAGE_BY_SGG",
                searchOption : searchOption,
                jobId : core.control.generateJobId("권역별 대중교통 이용현황 데이터 로딩")
            });
        }

        /**
         * 대중교통 이용현황분석 > 정류장별 대중교통 및 노선 시설물
         */
        bigdata.getPublicTransferStation = function(){
            if(_Map.getLayer(LAYER.BD_BUS_STATION) == null) {
                bigdata.removeBigDataLayers();
                _Worker.postMessage({event: "BD_BUS_STATION", jobId: core.control.generateJobId("버스 정류장 레이어 로딩중")});
            }else{
                _Map.off('click', LAYER.BD_BUS_STATION, MapEvents.BD_BUS_STATION);
                _Map.off('click', LAYER.BD_BUS_STATION, MapEvents.BD_BUS_USAGE_BY_STATION);
                _Map.on('click', LAYER.BD_BUS_STATION, MapEvents.BD_BUS_STATION);
            }
        }

        /**
         * 대중교통 이용현황분석 > 정류장별 버스 이용률
         */
        bigdata.getPublicTransferUsageByStation = function(searchOption = ''){
            selectedSearchOption["BD_BUS_USAGE_BY_STATION"] = _Util.convertParamToObject(searchOption);
            if(_Map.getLayer(LAYER.BD_BUS_STATION) == null) {
                bigdata.removeBigDataLayers();
                _Worker.postMessage({
                    event: "BD_BUS_USAGE_BY_STATION",
                    jobId: core.control.generateJobId("정류장별 이용률 데이터 로딩중")
                });
            }else{
                _Map.off('click', LAYER.BD_BUS_STATION, MapEvents.BD_BUS_STATION);
                _Map.off('click', LAYER.BD_BUS_STATION, MapEvents.BD_BUS_USAGE_BY_STATION);
                _Map.on('click', LAYER.BD_BUS_STATION, MapEvents.BD_BUS_USAGE_BY_STATION);
            }
        }

        /**
         * 대중교통 이용현황분석 > 버스 도착정보 예측 조회
         */
        bigdata.getPublicTransferBIT = function(searchOption = ''){
            selectedSearchOption["BD_BUS_BIT_STATION"] = _Util.convertParamToObject(searchOption);
            bigdata.removeBigDataLayers();
            _Worker.postMessage({
                event: "BD_BUS_BIT_ROUTE",
                routeId: selectedSearchOption["BD_BUS_BIT_STATION"].routeId,
                jobId: core.control.generateJobId("버스 노선 정보 로딩중")
            });
            _Worker.postMessage({
                event: "BD_BUS_BIT_STATION",
                searchOption : "routeId="+selectedSearchOption["BD_BUS_BIT_STATION"].routeId,
                jobId: core.control.generateJobId("버스정류장 정보 로딩중")
            });
        }

        /**
         * 대중교통 노선별 분석 > 노선구간별 수용성 및 굴곡도 분석
         * @param searchOption
         */
        bigdata.getPublicTransferRouteCurveAnalysis = function(searchOption) {
            selectedSearchOption["BD_BUS_ROUTE_CURVE_ANL"] = _Util.convertParamToObject(searchOption);
            if(!selectedSearchOption["BD_BUS_ROUTE_CURVE_ANL"].routeId) {
				new ModalBuilder().init().alertBoby("노선을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
                return;
            }
            _Worker.postMessage({
                event: "BD_BUS_ROUTE_CURVE_ANL",
                routeId : selectedSearchOption["BD_BUS_ROUTE_CURVE_ANL"].routeId,
                jobId: core.control.generateJobId("버스 노선 정보 로딩중")
            });

            _Worker.postMessage({
                event: "BD_BUS_STATION",
                searchOption : "routeId="+selectedSearchOption["BD_BUS_ROUTE_CURVE_ANL"].routeId,
                jobId: core.control.generateJobId("버스정류장 정보 로딩중")
            });
        }

        /**
         * 대중교통 노선별 분석 > 노선구간별 승하차/재차 승객 수 조회
         * @param searchOption
         */
        bigdata.getPublicTransferRoutePassengerAnalysis = function(searchOption) {
            selectedSearchOption["BD_BUS_ROUTE_PASSENGER_ANL"] = _Util.convertParamToObject(searchOption);
            if(!selectedSearchOption["BD_BUS_ROUTE_PASSENGER_ANL"].routeId) {
				new ModalBuilder().init().alertBoby("노선을 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
                return;
            }
            _Worker.postMessage({
                event: "BD_BUS_ROUTE_PASSENGER_ANL",
                routeId: selectedSearchOption["BD_BUS_ROUTE_PASSENGER_ANL"].routeId,
                jobId: core.control.generateJobId("버스 노선 정보 로딩중")
            });

            _Worker.postMessage({
                event: "BD_BUS_STATION",
                searchOption : "routeId="+selectedSearchOption["BD_BUS_ROUTE_PASSENGER_ANL"].routeId,
                jobId: core.control.generateJobId("버스정류장 정보 로딩중")
            });
        }

        bigdata.getPublicTransferDuplicateRoute = function(searchOption){
            selectedSearchOption["BD_BUS_ROUTE_DUPLICATE_ROUTE"] = _Util.convertParamToObject(searchOption);
            _Worker.postMessage({
                event: "BD_BUS_ROUTE_DUPLICATE_ROUTE",
                searchOption : searchOption,
                stStationId : selectedSearchOption["BD_BUS_ROUTE_DUPLICATE_ROUTE"].stStationId,
                edStationId : selectedSearchOption["BD_BUS_ROUTE_DUPLICATE_ROUTE"].edStationId,
                jobId: core.control.generateJobId("버스 중복 노선 정보 로딩중")
            });
        }

        bigdata.getPublicTransferUseCalc = function(searchOption){
            selectedSearchOption["BD_BUS_ROUTE_USE_CALC"] = _Util.convertParamToObject(searchOption);
            _Worker.postMessage({
                event: "BD_BUS_ROUTE_USE_CALC",
                routeId: selectedSearchOption["BD_BUS_ROUTE_USE_CALC"].routeId,
                jobId: core.control.generateJobId("버스 노선 정보 로딩중")
            });

            _Worker.postMessage({
                event: "BD_BUS_STATION",
                searchOption : "routeId="+selectedSearchOption["BD_BUS_ROUTE_USE_CALC"].routeId,
                jobId: core.control.generateJobId("버스정류장 정보 로딩중")
            });
        }
    }



    function Monitoring(){
        core.monitoring = this;
        let monitoring = core.monitoring;
        let gitsRoadData = {};
        /**
         * 모니터링 기본 데이터 세팅
         */
        core.setMonitoring = async function(){
            monitoring.getTrafficInfo(true, 5000);
            monitoring.getWarningInfo();
            //monitoring.getBusMoveInfo();
            //monitoring.getEmergencyMoveInfo();
            return monitoring;
        }

        core.setMonitoringDashboard = async function(){
            return monitoring;
        }

        /**
         * 추가된 모니터링  레이어 제거
         */
        monitoring.removeAddedMonitoringLayer = function(){
            monitoring.removeEmergencyMoveInfo();
            monitoring.removeBusMoveInfo();

        }

        /**
         * 긴급차량 이동현황 실시간 레이어 생성 
         */
        monitoring.getEmergencyMoveInfo = function(clear = true){
            if(clear) {
                monitoring.removeAddedMonitoringLayer();
            }

            _Worker.postMessage({
                event : "M_EMERGENCY",
                jobId : core.control.generateJobId("긴급차량 이동현황 로딩중"),
                loop : true,
                loopTime : 10000
            });
        }


        /**
         * 긴급차량 이동현황 레이어 제거
         */
        monitoring.removeEmergencyMoveInfo = function(){
            core.control.removeCustomSource(LAYER.EMERGENCY);
            core.control.removeCustomSource(LAYER.EMERGENCY_ROUTE);
            stopWorkerInterval(LAYER.EMERGENCY);
            stopWorkerInterval("M_EMERGENCY");
        }

        /**
         * 버스정류장 정보 조회
         */
        monitoring.getBusStationInfo = function(clear = true, searchOption){
            if(clear) {
                monitoring.removeAddedMonitoringLayer();
            }
            core.bigdata.removeBigDataLayers();
            _Worker.postMessage({event : "CM_BUS_STATION", searchOption : searchOption, jobId : core.control.generateJobId("버스 정류장 레이어 로딩중")});
        }

        /**
         * 버스정류장 레이어 제거
         */
        monitoring.removeBusStationInfo = function(){
            core.control.removeCustomSource(LAYER.BUS_STATION);
        }

        /**
         * 버스 실시간 이동현황
         */
        monitoring.getBusMoveInfo = function(clear  = true){
            if(clear) {
                monitoring.removeAddedMonitoringLayer();
            }
            _Worker.postMessage({event : "CM_BUS_STATION", jobId : core.control.generateJobId("버스 정류장 레이어 로딩중")});
            _Worker.postMessage({
                event : "M_BUS",
                loop : false,
                loopTime : 10000,
                jobId: core.control.generateJobId("실시간 버스 이동현황 정보 로딩중")
            });
        }

        /**
         * 버스 실시간 이동현황 제거
         */
        monitoring.removeBusMoveInfo = function(){
            core.control.removeCustomSource(LAYER.BUS);
            core.control.removeCustomSource(LAYER.BUS_STATION);
            stopWorkerInterval("M_BUS");
        }

        /**
         * 버스 노선정보 조회
         * @param routeId
         */
        monitoring.getBusRouteInfo = function(routeId){
            _Worker.postMessage({
                event: "CM_BUS_ROUTE",
                routeId: routeId,
                jobId: core.control.generateJobId("버스 노선 정보 로딩중")
            });
        }

        /**
         * 버스노선 제거
         * @param routeId
         */
        monitoring.removeBusRouteInfo = function(routeId){
            core.control.removeCustomSource(LAYER.BUS_ROUTE);
        }

        /**
         * 돌발현황 조회
         */
        monitoring.getWarningInfo = function(clear  = true){
            if(clear) {
                monitoring.removeAddedMonitoringLayer();
            }
            _Worker.postMessage({
                event : "M_WARNING",
                loop : true,
                loopTime : 10000,
                jobId : core.control.generateJobId("돌발현황 레이어 로딩중")
            });
            _Worker.postMessage({
                event : "CM_WARNING_ALARM",
                loop : true,
                loopTime : 10000
            });
        }

        /**
         * 돌발현황 레이어 제거
         */
        monitoring.removeWarningInfo = function(){
            core.control.marker ? core.control.marker.clearAll() : null;
            stopWorkerInterval("M_WARNING");
        }

        /**
         * 실시간 교통현황 조회
         * @param loop
         * @param loopTime
         */
        monitoring.getTrafficInfo = function(loop = false, loopTime = 5000){
            gitsRoadData.event = "M_TRAFFIC";
            gitsRoadData.loop = false;
            gitsRoadData.loopTime = loopTime;
            gitsRoadData.jobId = core.control.generateJobId("실시간 교통현황 로딩중");
            _Worker.postMessage(gitsRoadData);
        }

        /**
         * 실시간 교통현황 제거
         */
        monitoring.removeTrafficInfo = function(){
            stopWorkerInterval("M_TRAFFIC");
            core.control.setPaintDefault(LAYER.NODE);
            core.control.setPaintDefault(LAYER.LINK);
        }

        /**
         * 날씨정보 조회
         * @param loop
         * @param loopTime
         */
        monitoring.getWeatherInfo = function(loop = false, loopTime = 30000){
            let weatherData = {};
            weatherData.event = "M_WEATHER";
            weatherData.loop = loop;
            weatherData.loopTime = loopTime;
            weatherData.jobId = core.control.generateJobId("기상현황 로딩중");
            _Worker.postMessage(weatherData);
        }

        /**
         * 날씨정보 제거
         */
        monitoring.removeWeatherInfo = function(){
            core.control.removeCustomSource(LAYER.GRID_WEATHER);
        }

        /**
         * 교통신호 조회
         */
        monitoring.getTrafficSignalInfo = function(){

        }
    }

    /**
     * 애니메이션 이벤트 객체
     * @constructor
     */
    function MapAnimate(){
        core.animate = this;
        const animate = core.animate;
        let animator = {};
        let focusProperty = null;
        let focusValue = null;
        let beforeZoom = null;
        let beforeCoordinate = null;
        let focusType = null;
        let openedPopup = null;

        animate.setOpenedPopup = function(popup){
            openedPopup = popup;
        }
        animate.getOpenedPopup = function(){
            return openedPopup;
        }

        animate.hasFocus = function(f){
            return focusType == f;
        }
        animate.getFocusKey = function(){
            return focusProperty;
        }
        animate.getFocusValue = function(){
            return focusValue;
        }
        animate.setFocus = function(_focusType, _focusProperty, _focusValue) {
            core.animate.removeFocus();
            focusProperty = _focusProperty;
            focusValue = _focusValue;
            focusType = _focusType
            beforeZoom = _Map.getZoom();
            let center = _Map.getCenter();
            beforeCoordinate = [center.lng, center.lat];
            if($("#follow-item").length === 0) {
                let jobItem = $(`
                <button type="button" id="follow-item">
                    따라가기 애니메이션 종료
                </button>`);
                $(".mapboxgl-canvas-container").append(jobItem);
                $(jobItem).on("click", function(){
                    core.animate.removeFocus();
                    $(jobItem).slideUp(250, function(){
                        $(this).remove();
                    });
                    /*core.control.moveMap(beforeCoordinate, beforeZoom);*/
                });
                jobItem.slideDown(250);
            }
        }
        animate.removeFocus = function(){
            focusProperty = null;
            focusValue = null;
            focusType = null;
            openedPopup = null;
            $("#follow-item").remove();
        }
        /**
         * 애니메이션 시작
         * @param type
         * @param k
         * @param v
         */
        animate.animateStart = function(type, k, v){
            focusProperty = k;
            focusValue = v;
            focusType = type;
            animate.setFocus(type, k,v);
        }

        /**
         * 애니메이션 스탑
         * @param type
         * @param remove 레이어까지 삭제
         */
        animate.animateStop = function(type, remove = false){
            focusType = null;
            focusProperty = null;
            focusValue = null;
            $("#follow-item").remove();
            if(remove)
                core.control.removeCustomSource(type);
        }

    }
    const MapEvents = {
        "TEST" : function(e) {
            const feature = e.features[0];
            const prop = e.features[0].properties;
            console.log("feature", feature);
            console.log("prop", prop);
        },
        "CM_BUS_ROUTE" : function(e){
            const prop = e.features[0].properties;
            let html = `
                                <div class="data_popup" >
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p>${prop.roadName}(${prop.linkId})</p>
                                    ${prop.routeTable}
                                </div>
                            `;
            let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.lngLat)
                .setHTML(html)
                .addTo(_Map)
        },
        "CM_BUS_STATION" : function(e) {
            const prop = e.features[0].properties;
            new mapboxgl.Popup({offset : [0, -25], maxWidth : "none"})
                .setLngLat(e.features[0].geometry.coordinates)
                .setHTML(`
                        <div class="data_popup">
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                            <i class="bus_icon bus_pop"></i>
							<p>${prop.stationNm}</p>
                            <ul class="data_sub mt16">
								${prop.description}
                            </ul>
                        </div>
                    `)
                .addTo(_Map)
        },
        "F_SIGNAL" : function (e){
            const prop = e.features[0].properties;
            let html = `
                            <div class="data_popup" >
								<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                <i class="map_icon bus_icon data_icon"></i>
                                <p class="mt8 mb8">${prop.intName}(${prop.intLcno})</p>
                                <ul class="data_sub mt16">
                                    <li class="popup_item">교차로유형 : <span>${GITS_ENV.INT_TYPE[prop.intType]}</span></li>
                                    <li class="popup_item">제어기유형 : <span>${GITS_ENV.INT_LCTYPE[prop.intLctype]}</span></li>
                                    <li class="popup_item">램프타입 : <span>${GITS_ENV.INT_LAMPTYPE[prop.intLamptype]}</span></li>
                                    <li class="popup_item">PPC타입 : <span>${GITS_ENV.PPC_TYPE[prop.ppcType]}</span></li>
                                </ul>
                            </div>
                        `;
            let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.features[0].geometry.coordinates)
                .setHTML(html)
                .addTo(_Map)
        },
        "F_DSRC" : function (e){
            const prop = e.features[0].properties;
            const feature = e.features[0];
            $.ajax({
                type: "get",
                url: "/facility/getDSRCCollectList.ajax",
                data: {
                    rseId: prop.rseId
                },
                beforeSend : function(){
                    gitsApp.startLoading();
                },
                success: function (colctInfoList) {
                    colctInfoList = colctInfoList.sort(function(a, b){
                        return a.laneNo - b.laneNo;
                    })
                    let table = ` <div class="popup_scroll">
				<table class="popup_table">
                <thead>
                <th>타입</th>
                <th>구간명</th>
                <th>구간길이</th>
                <th>속도</th>
                </thead>
                <tbody>
                `;
                    for(colctInfo of colctInfoList) {
                        table += `
                <tr>
                    <td>
                        ${colctInfo.startRseId === prop.rseId ? "구간 시작지점" : ""}
                        ${colctInfo.endRseId === prop.rseId ? "구간 종료지점" : ""}
                    </td>
                    <td>${colctInfo.dsrcSctnNm ? colctInfo.dsrcSctnNm : "구간명없음"}</td>
                    <td>${colctInfo.dsrcSctnLen}</td>
                    <td>${colctInfo.speed} km/h</td>
                </tr>`;
                    }
                    table += "</tbody></table></div>";
                    let html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p class="mt8 mb8">${prop.rseNm}(${prop.rseId})</p>
                                    ${table}
                                </div>
                            `;
                    let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                        .setLngLat(feature.geometry.coordinates)
                        .setHTML(html)
                        .addTo(_Map)
                },
                complete : function(){
                    gitsApp.endLoading();
                }
            });
        },
        "F_DSRC_LINK" : function (e){
            const features = e.features;
            const prop = e.features[0].properties;
            let table = ` <div class="popup_scroll">
				<table class="popup_table">
                <thead>
                <th>구간ID</th>
                <th>구간명</th>
                <th>속도</th>
                </thead>
                <tbody>
                `;
            for(feature of features) {
                table += `
                <tr>
                    <td>${feature.properties.dsrcSctnId}</td>
                    <td>${feature.properties.dsrcSctnNm ? feature.properties.dsrcSctnNm : "구간명없음"}</td>
                    <td>${feature.properties.speed} km/h</td>
                </tr>`;
            }
            table += "</tbody></table>";
            let html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p class="mb8 mt8">${prop.roadName}(${prop.linkId})</p>
                                    ${table}
                                </div>
                            `;
            let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.lngLat)
                .setHTML(html)
                .addTo(_Map)
        },
        "F_SMART" : function(e){
            const prop = e.features[0].properties;
            let html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p class="mt8 mb8">${prop.crsrdNm}(${prop.crsrdId})</p>
									<ul>
										<li class="popup_item">노드ID : <span>${prop.nodeId}</span></li>
	                                    <li class="popup_item">최대교통량 : <span>${prop.maxTrfvlm}</span></li>
	                                    <li class="popup_item">최대보행자수 : <span>${prop.maxPdstCnt}</span></li>
	                                    <li class="popup_item">교통량(1시간) : <span>${prop.vhclTrfvlm}</span></li>
	                                    <li class="popup_item">보행자수(1시간) : <span>${prop.pdstCnt}</span></li>
	                                    <li class="popup_item">평균차량속도 : <span>${prop.avgVhclSpeed} km/h</span></li>
	                                    <li class="popup_item">평균보행자속도 : <span>${prop.avgPdstSpeed} km/h</span></li>
									</ul>
                                </div>
                            `;
            let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.features[0].geometry.coordinates)
                .setHTML(html)
                .addTo(_Map)
        },
        "F_SMART_LINK" : function(e){
            const prop = e.features[0].properties;
            let html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p class="mt8 mb8">${prop.acsRoadNm}(${prop.acsRoadId})</p>
                                    <ul>
                                        <li class="popup_item">링크ID : <span>${prop.linkId}</span></li>
                                        <li class="popup_item">차로수 : <span>${prop.laneCnt}</span></li>    
                                    </ul>
                                </div>
                            `;
            let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.lngLat)
                .setHTML(html)
                .addTo(_Map)
        },
        "F_VDS" : function (e){
            const prop = e.features[0].properties;
            const feature = e.features[0];
            $.ajax({
                type: "get",
                url: "/facility/getVDSCollectList.ajax",
                data: {
                    vdsId: prop.vdsId
                },
                beforeSend : function(){
                    gitsApp.startLoading();
                },
                success: function (colctInfoList) {
                    colctInfoList = colctInfoList.sort(function(a, b){
                        return a.laneNo - b.laneNo;
                    })
                    let table = ` <div class="popup_scroll">
				<table class="popup_table">
                <thead>
                <th>차로번호</th>
                <th>평균속도</th>
                <th>교통량</th>
                <th>점유비율</th>
                </thead>
                <tbody>
                `;
                    for(colctInfo of colctInfoList) {
                        table += `
                <tr>
                    <td>${colctInfo.laneNo}차선</td>
                    <td>${colctInfo.avgSpeed} km/h</td>
                    <td>${colctInfo.trfvlm}대</td>
                    <td>${colctInfo.occpRt}</td>
                </tr>`;
                    }
                    table += "</tbody></table></div>";
                    let html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p class="mt8 mb8">${prop.vdsNm}(${prop.vdsId})</p>
                                    ${table}
                                </div>
                            `;
                    let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                        .setLngLat(feature.geometry.coordinates)
                        .setHTML(html)
                        .addTo(_Map)
                },
                complete : function(){
                    gitsApp.endLoading();
                }
            });
        },
        "M_DANGER_VEHICLE" : function(e){

        },
        "M_EMERGENCY" : function(e){
            const prop = e.features[0].properties;
            let emergencyPopup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.lngLat)
                .setHTML(`
                    <div class="data_popup" >
						<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                        <i class="map_icon bus_icon data_icon"></i>
                        <ul class="data_sub mt16">
                            ${prop.description}
                        </ul>
						<button class="focus_button ${prop.type != 'ambulance' ? 'none' :''}" data-id="${prop.serviceid}">따라가기</button>	
                    </div>
                `)
                .addTo(_Map)
            core.animate.setOpenedPopup(emergencyPopup);
            $(document).off("click.animate").on("click.animate", ".focus_button", function(){
                const key = 'serviceid';
                const value = $(this).data("id");
                core.animate.setFocus(LAYER.EMERGENCY, key, value);
                const features = _Map.querySourceFeatures(LAYER.EMERGENCY, {sourceLayer : LAYER.EMERGENCY});
                const feature = _getUniqueFeatures(features, key, value);
                core.control.moveMap(feature.geometry.coordinates);
            });
        },
        "BD_POPULATION" : function(e){
            const prop = e.features[0].state;
            let routeTable = `<div class="popup_scroll">
                    <table class="popup_table">
                    <thead>
                    <th>시간대</th>
                    <th>예측유동인구</th>
                    </thead>
                    <tbody>
                    `;
            for(let i = 0; i < 24; i++){
                let hh = i< 10 ? "0"+i : i;
                routeTable += `
                        <tr>
                            <th>${hh}시</th>
                            <td>${prop["fltPop_"+hh] ? prop["fltPop_"+hh]+"명" : "데이터미제공"}</td>
                        </tr>`;
            }
            routeTable += "</tbody></table></div>";
            new mapboxgl.Popup()
                .setLngLat(e.lngLat)
                .setHTML(`
                        <div class="data_popup" style="width:15.7rem;">
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16"> 
								<li class="popup_item">셀ID : <span>${e.features[0].id}</span></li>
                            </ul>
                            ${routeTable}
                        </div>
                    `)
                .addTo(_Map)
        },
        "BD_DANGER_ROAD" : function(e) {
            const feature = e.features[0];
            const prop = feature.properties;
            new mapboxgl.Popup({offset : [-10, -25]})
                .setLngLat(e.lngLat)
                .setHTML(`
	                        <div class="data_popup">
								<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
	                            <img src="/statics/images/danger_road_${prop.icon}.png" style="width:40px;">
								<div class="data_popup_scroll">
		                            <ul class="data_sub mt16">
										<li class="popup_item">위험상태명 : ${prop.dngrSttsNm}</li>
										<li class="popup_item">위험상태등급 : ${GITS_ENV.ROAD_DANGER_GRADE[prop.dngrSttsGrd]}</li>
										<li class="popup_item">강수량유형 : ${GITS_ENV.ROAD_DANGER_PCTT[prop.pcttType]}</li>
										<li class="popup_item">온도 : ${prop.tmprt}</li>
										<li class="popup_item">시간강수량 : ${prop.timePctt}</li>
										<li class="popup_item">주소 : ${prop.roadNmAddr}</li>
		                            </ul>
								</div>
	                        </div>
                    `)
                .addTo(_Map);
        },
        "BD_DANGER_ZONE_BY_TYPE" : function(e) {
            let html = '';
            let propName = {
                type : "타입"
                ,acdntCnt : "사고수"
                ,casltCnt : "사상자수"
                ,dcsdCnt : "사망자수"
                ,swpsnCnt : "중상자수"
                ,sinjpsnCnt : "경상자수"
                ,injDclrCnt : "부상신고수"
                /*,adstdgCd : "법정코드"*/
            }
            let index = 0;
            let feature = e.features[0];
            /*for(const feature of e.features) {*/
                const prop = feature.properties;
                for (const p in prop) {
                    if (propName[p]) {
                        html += `<li class="popup_item">${propName[p]} : <span>${p === "type" ? GITS_ENV.ACCIDENT_TYPE[prop[p]] : prop[p]}</span></li>`;
                    }
                }
                // html += "<hr class='mt16'/>";
                index++;
            /*}*/
            new mapboxgl.Popup()
                .setLngLat(e.lngLat)
                .setHTML(`
	                        <div class="data_popup">
								<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
	                            <img src="/statics/images/accident_${e.features[0].properties.type}.png" style="width:40px;">
								<div class="data_popup_scroll">
		                            <ul class="data_sub mt16">
										${html}
		                            </ul>
								</div>
	                        </div>
                    `)
                .addTo(_Map)
        },
        "BD_PATTERN_TRAFFIC_QUANTITY" : function(e){
            const prop = e.features[0].properties;
            const state = e.features[0].state;
            let html = `<div class="data_popup" style="width:15.7rem;">
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">`;
            html += `<li class="popup_item">링크아이디 : ${prop.linkId}</li>`;
            html += `<li class="popup_item">도로명 : ${prop.roadName}</li>`;
            html += `<li class="popup_item">도로타입 : ${GITS_ENV.ROAD_RANK[prop.roadRank]}</li>`;
            html += prop.addLabel;
            html += `<ul>
                        </div>`;
            new mapboxgl.Popup()
                .setLngLat(e.lngLat)
                .setHTML(html)
                .addTo(_Map)
        },
        "BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS_MERGE" : function(e){
            const prop = e.features[0].properties;
            let html = `<div class="data_popup" style="width:15.7rem;">
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">`;
            html += `<li class="popup_item">링크아이디 : ${prop.linkId}</li>`;
            html += `<li class="popup_item">도로명 : ${prop.roadName}</li>`;
            html += `<li class="popup_item">도로타입 : ${GITS_ENV.ROAD_RANK[prop.roadRank]}</li>`;
            html += `<li class="popup_item">개선 평균속도 : ${prop.avgVhclSpeedAvg} km/h</li>`;
            html += `<li class="popup_item">개선 누적통행량 : ${prop.vhclTrfvlmTotal} 대</li>`;
            html += `<ul>
                        </div>`;
            new mapboxgl.Popup()
                .setLngLat(e.lngLat)
                .setHTML(html)
                .addTo(_Map)
        },
        "BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS" : function(e) {
            const prop = e.features[0].properties;
            let html = `<div class="data_popup" style="width:15.7rem;">
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">`;
            html += `<li class="popup_item">링크아이디 : ${prop.linkId}</li>`;
            html += `<li class="popup_item">도로명 : ${prop.roadName}</li>`;
            html += `<li class="popup_item">도로타입 : ${GITS_ENV.ROAD_RANK[prop.roadRank]}</li>`;
            html += `<li class="popup_item">평균 속도 : ${prop.avgVhclSpeedAvg} km/h</li>`;
            html += `<li class="popup_item">누적통행량 : ${prop.vhclTrfvlmTotal} 대</li>`;
            html += `<ul>
                        </div>`;
            new mapboxgl.Popup()
                .setLngLat(e.lngLat)
                .setHTML(html)
                .addTo(_Map)
        },
        "BD_DANGER_ZONE_UNCLUSTER" : function(e) {
            const prop = e.features[0].properties;
            let html = '';
            let propName = {
                acdntCnt : "사고수"
                ,casltCnt : "사상자수"
                ,dcsdCnt : "사망자수"
                ,swpsnCnt : "중상자수"
                ,sinjpsnCnt : "경상자수"
                ,injDclrCnt : "부상신고수"
                ,adstdgCd : "법정코드"
            }
            for(const p in prop) {
                if(propName[p]) {
                    html += `<div class="popup_item">${propName[p]} : ${prop[p]}</div>`;
                }
            }
            new mapboxgl.Popup()
                .setLngLat(e.lngLat)
                .setHTML(`
                        <div class="data_popup">
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                            <i class="map_icon bus_icon data_icon"></i>
                            <div class="data_sub mt16">
								${html}
                            </div>
                        </div>
                    `)
                .addTo(_Map)
        },
        "BD_DANGER_ZONE_CLUSTER" : function(e) {
            const features = _Map.queryRenderedFeatures(e.point, {
                layers: [clusterLayer.id]
            });
            const clusterId = features[0].properties.cluster_id;
            _Map.getSource(sourceName).getClusterExpansionZoom(
                clusterId,
                (err, zoom) => {
                    if (err) return;

                    _Map.easeTo({
                        center: features[0].geometry.coordinates,
                        zoom: zoom
                    });
                }
            );
        },
        "BD_PT_DANGER_ANALYSIS" : function(e){
            const prop = e.features[0].properties;
            let html = `
             <li class="popup_item">도로명 : ${prop.roadName}</li>
             <li class="popup_item">노선명 : ${prop.routeNm}</li>
            `;
            if(prop.riskJugCnt){
                html += `<li class="popup_item">위험판정수 : ${prop.riskJugCnt}</li>
             <li class="popup_item">과속운행수 : ${prop.spdngRungCnt}</li>
             <li class="popup_item">급가속 운행 수 : ${prop.sdacelRungCnt}</li>
             <li class="popup_item">급감속 운행 수 : ${prop.rpddclRungCnt}</li>
             <li class="popup_item">급정지 운행 수 : ${prop.sdstopRungCnt}</li>
             <li class="popup_item">급출발 운행 수 : ${prop.sdstrtRungCnt}</li>
             <li class="popup_item">장기과속 운행 수 : ${prop.lngtrmaclRungCnt}</li>`;
            }
            new mapboxgl.Popup({offset : [0, -15]})
                .setLngLat(e.lngLat)
                .setHTML(`
                        <div class="data_popup">
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">
								${html}
                            </ul>
                        </div>
                    `)
                .addTo(_Map)
        },
        "BD_PREDICTION_ACCIDENT" : function(e) {
            const prop = e.features[0].properties;
            const state = e.features[0].state;
            if(!state.safeGrd) {
                return;
            }
            let grd = "";
            switch(state.safeGrd) {
                case "-3" :
                    grd = "링크정보누락";
                    break;
                case "-2" :
                    grd = "속도초과";
                    break;
                case "-1" :
                    grd = "속도누락";
                    break;
                case "0" :
                    grd = "안전";
                    break;
                case "1" :
                    grd = "주의";
                    break;
                case "2" :
                    grd = "위험";
                    break;
                case "3" :
                    grd = "심각";
                    break;
            }
            const html = `
            <li class="popup_item">속도 : ${state.speed} km/h</li>
            <li class="popup_item">안전지수 : ${state.safeIdex}</li>
            <li class="popup_item">안전등급 : ${grd}</li>
            `;
            new mapboxgl.Popup({offset : [0, -15]})
                .setLngLat(e.lngLat)
                .setHTML(`
                        <div class="data_popup">
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">
								${html}
                            </ul>
                        </div>
                    `)
                .addTo(_Map)
        },
        "BD_PUBLIC_TRANSFER_CNDCY_ROUTE" : function(e) {
            const prop = e.features[0].properties;
        },
        /*"BD_PUBLIC_TRANSFER_USAGE_BY_ST_END" : function(e) {
            const prop = e.features[e.features.length - 1].properties;
            let html = `<div class="data_popup">
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">`;
            html += `<li class="popup_item">링크아이디 : ${prop.linkId}</li>`;
            html += `<li class="popup_item">노선번호 : ${prop.routeNm}</li>`;
            html += `<li class="popup_item">구간 : ${prop.stStationNm} - ${prop.edStationNm}</li>`;
            html += `<li class="popup_item">도로명 : ${prop.roadName}</li>`;
            html += `<li class="popup_item">버스사용자수 : ${prop.busUserCnt ? prop.busUserCnt+"명" : "데이터미제공"}</li>`;
            html += `<li class="popup_item">승객수 : ${prop.psgrCnt ? prop.psgrCnt+"명" : "데이터미제공"}</li>`;
            html += `<li class="popup_item">환승수 : ${prop.trsfrCnt ? prop.trsfrCnt+"명" : "데이터미제공"}</li>`;
            html += `<ul></div>`;
            new mapboxgl.Popup()
                .setLngLat(e.lngLat)
                .setHTML(html)
                .addTo(_Map)
        },*/
        "BD_BUS_BIT_STATION" : function(e) {
            const prop = e.features[0].properties;
            console.log("BD_BUS_BIT_STATION PROP", prop);
            if(!selectedSearchOption["BD_BUS_BIT_STATION"]) {
                return;
            }
            let ajaxData = $.extend(selectedSearchOption["BD_BUS_BIT_STATION"], {edStationId:prop.stationId}, {});
            $.ajax({
                type : "get",
                url : "/bigdata/getPublicTransferBIT.ajax",
                data : ajaxData,
                success : function(list){
                    let routeTable = `<div class="popup_scroll">
                    <table class="popup_table">
                    <thead>
                    <th>회차</th>
                    <th>예상도착시간</th>
                    <th>실제도착시간</th>
                    </thead>
                    <tbody>
                    `;
                    for(const data of list){
                        routeTable += `
                        <tr>
                            <td>${data.operSeq}</td>
                            <td>${data.arrTm}</td>
                            <td></td>
                        </tr>`;
                    }
                    routeTable += "</tbody></table></div>";
                    let html = `
                                <div class="data_popup" >
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p>${prop.stationNm}(${prop.stationId})</p>
                                    ${routeTable}
                                </div>
                            `;
                    let popup = new mapboxgl.Popup({offset : [0, -30], maxWidth : "none"})
                        .setLngLat(e.lngLat)
                        .setHTML(html)
                        .addTo(_Map)
                }
            })
        },
        "BD_BUS_USAGE_BY_STATION" : function(e) {
            const prop = e.features[0].properties;
            if(!selectedSearchOption["BD_BUS_USAGE_BY_STATION"]) {
                return;
            }
            $.ajax({
                type : "get",
                url : "/bigdata/getPublicTransferUsageByStation.ajax",
                data : {
                    stationId : prop.stationId,
                    searchYear : selectedSearchOption["BD_BUS_USAGE_BY_STATION"].searchYear,
                    searchPeriod : selectedSearchOption["BD_BUS_USAGE_BY_STATION"].searchPeriod,
                    searchTime : selectedSearchOption["BD_BUS_USAGE_BY_STATION"].searchTime,
                },
                success : function(list){
                    let calculateData = {
                        rideCnt : 0,
                        lndiCnt : 0,
                        trnsCnt : 0
                    }
                    let routeTable = `<div class="popup_scroll">
                    <table class="popup_table">
                    <thead>
                    <th>(버스유형)<br/>노선명</th>
                    <th>전체</th>
                    <th>승차</th>
                    <th>하차</th>
                    <th>환승</th>
                    </thead>
                    <tbody>
                    `;
                    for(const route of list){
                        routeTable += `
                        <tr>
                            <th>(${GITS_ENV.ROUTE_TP[route.routeTp]})<br/>${route.routeNm ? route.routeNm : '-'}</th>
                            <td>${route.rideUserCnt+route.lndiUserCnt+route.trnsitUserCnt}</td>
                            <td>${route.rideUserCnt}</td>
                            <td>${route.lndiUserCnt}</td>
                            <td>${route.trnsitUserCnt}</td>
                        </tr>`;
                        calculateData.rideCnt += route.rideUserCnt;
                        calculateData.lndiCnt += route.lndiUserCnt;
                        calculateData.trnsCnt += route.trnsitUserCnt;
                    }
                    routeTable += "</tbody></table></div>";
                    let html = `
                                <div class="data_popup" >
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p>${prop.stationNm}(${prop.stationId})</p>
                                    <p>
                                    전체 : ${calculateData.rideCnt+calculateData.lndiCnt+calculateData.trnsCnt}
                                    / 승차 : ${calculateData.rideCnt}
                                    / 하차 : ${calculateData.lndiCnt}
                                    / 환승 : ${calculateData.trnsCnt}
                                    </p>
                                    ${routeTable}
                                </div>
                            `;
                    let popup = new mapboxgl.Popup({offset : [0, -30], maxWidth : "none"})
                        .setLngLat(e.lngLat)
                        .setHTML(html)
                        .addTo(_Map)
                }
            })
        },
        "BD_BUS_ROUTE_CURVE_ANL" : function(e){
            if(!selectedSearchOption["BD_BUS_ROUTE_CURVE_ANL"]) {
                return;
            }
            const prop = e.features[0].properties;
            console.log("prop", prop);
            $.ajax({
                type : "get",
                url : "/bigdata/getPublicTransferRouteCurveAnalysis.ajax",
                data : selectedSearchOption["BD_BUS_ROUTE_CURVE_ANL"],
                success : function(data){
                    let html = '';
                    if(data) {
                        html = `
                                <div class="data_popup" >
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p>노선명 : ${data.routeNm}</p>
                                    <ul class="data_sub mt16">
                                    <li class="popup_item">총 이용객 수 : <span>${data.totUserCnt ? data.totUserCnt : '-'} 명</span></li>
                                    <li class="popup_item">총 정류장 수 : <span>${data.totBstpCnt ? data.totBstpCnt : '-'} 개</span></li>
                                    <li class="popup_item">노선거리 : <span>${data.routeLen ? data.routeLen : '-'}</span></li>
                                    <li class="popup_item">운행간격 : <span>${data.rungIntv ? data.rungIntv : '-'}</span></li>
                                    <li class="popup_item">굴곡도 : <span>${data.curvt ? data.curvt : '-'}</span></li>
                                    </ul>
                                </div>
                            `;

                    let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                        .setLngLat(e.lngLat)
                        .setHTML(html)
                        .addTo(_Map)
                    }else{
						new ModalBuilder().init().alertBoby("데이터 미제공").footer(4,'확인',function(button, modal){modal.close();}).open();
						modalAlertWrap();
                    }
                }
            })
        },
        "BD_BUS_ROUTE_DUPLICATE_ROUTE" : function(e) {
            const prop = e.features[0].properties;
            console.log(prop);
        },
        "BD_BUS_ROUTE_PASSENGER_ANL" : function(e){
            if(!selectedSearchOption["BD_BUS_ROUTE_PASSENGER_ANL"]) {
                return;
            }
            const prop = e.features[0].properties;
            console.log("BD_BUS_ROUTE_PASSENGER_ANL", prop);
        },
        "BD_BUS_STATION" : function(e){
            const prop = e.features[0].properties;
            $.ajax({
                type : "get",
                url : "/monitoring/getBusStationRouteList.ajax?stationId="+prop.stationId,
                success : function(list){
                    let routeTable = `<div class="popup_scroll">
                    <table class="popup_table">
                    <thead>
                    <th>(버스유형)<br/>노선명</th>
                    <th>(회사명)<br/>관리자</th>
                    <th>기점정류소</th>
                    <th>종점정류소</th>
                    </thead>
                    <tbody>
                    `;
                        for(const route of list){
                            routeTable += `
                        <tr>
                            <td>(${GITS_ENV.ROUTE_TP[route.routeTp]})<br/>${route.routeNm ? route.routeNm : '-'}</td>
                            <td>(${route.companyNm ? route.companyNm : '-'})<br/>${route.adminNm ? route.adminNm : '-'}</td>
                            <td>${route.stStaNm ? route.stStaNm : '-'}</td>
                            <td>${route.edStaNm ? route.edStaNm : '-'}</td>
                        </tr>`;
                        }
                        routeTable += "</tbody></table></div>";
                    let html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p>${prop.stationNm}(${prop.stationId})</p>
                                    ${routeTable}
                                </div>
                            `;
                    let popup = new mapboxgl.Popup({offset : [0, -30], maxWidth : "none"})
                        .setLngLat(e.lngLat)
                        .setHTML(html)
                        .addTo(_Map)
                }
            })
        }
    }

    /**
     * MapControl
     * @constructor
     */
    function MapControl(){
        core.control = this;
        const control = core.control;

        /**
         * 모니터링 기본 셋
         */
        control.setDefault = function(){
            _Worker.postMessage({"clearIntervallAll" : true});
            control.setPaintDefault(LAYER.NODE);
            control.setPaintDefault(LAYER.LINK);

            for(const customSourceLayer of customSourceLayerIds){
                if(customSourceLayer.layerIds) {
                    for(const layerId of customSourceLayer.layerIds) {
                        if(LOG_LEVEL === "DEBUG")
                            console.log("removelayerId", layerId);
                        _Map.removeLayer(layerId);
                    }
                }
                if(customSourceLayer.sourceId) {
                    _Map.removeSource(customSourceLayer.sourceId);
                }
            }
            customSourceLayerIds = [];
            control.marker.clearAll();
        }

        /**
         * 맵 스타일 설정
         * @param type
         */
        control.setStyle = async function(type) {
            _Worker.postMessage({pause:true});
            function forEachLayer(cb) {
                if(typeof __DualMap !== "undefined"){
                    __DualMap.getStyle().layers.forEach((layer) => {
                        cb(layer);
                    });
                }
                _Map.getStyle().layers.forEach((layer) => {
                    cb(layer);
                });
            }
            const savedLayers = [];
            const savedSources = {};
            forEachLayer((layer) => {
                if(layer.id.includes(GITS_ENV.LAYER_PREFIX)) {
                    savedSources[layer.source] = _Map.getSource(layer.source).serialize();
                    savedLayers.push(layer);
                }
            });
            _Map.setStyle(GITS_ENV.MAP_STYLES[type]);
            if(typeof __DualMap !== "undefined"){
                __DualMap.setStyle(GITS_ENV.MAP_STYLES[type]);
            }
            _setSession(SESSION_PROP.MAP_STYLE, GITS_ENV.MAP_STYLES[type]);
            for(const icon of GITS_ENV.ICONS) {
                if(!_Map.hasImage(icon.id)) {
                    await _Map.loadImage(icon.url, function (err, image) {
                        _Map.addImage(icon.id, image);
                    });
                }
                if(typeof __DualMap !== "undefined" && !__DualMap.hasImage(icon.id)) {
                    await __DualMap.loadImage(icon.url, function (err, image) {
                        __DualMap.addImage(icon.id, image);
                    });
                }
            }
            if(type !== 'SATELLITE') {
                _Map.addControl(languageControl);
                if(typeof __DualMap !== "undefined") __DualMap.addControl(languageControl);
            }else {
                _Map.removeControl(languageControl);
                if(typeof __DualMap !== "undefined") __DualMap.removeControl(languageControl);
            }
            setTimeout(async () => {
                Object.entries(savedSources).forEach(([id, source]) => {
                    if(typeof _Map.getSource(id) === "undefined") {
                        _Map.addSource(id, source);
                    }
                    if(typeof __DualMap !== "undefined" && typeof __DualMap.getSource(id) === "undefined") {
                        __DualMap.addSource(id, source);
                    }
                });
                for (const layer of savedLayers) {
                    if(typeof _Map.getLayer(layer.id) === "undefined"){
                        _Map.addLayer(layer);
                    }
                    if(typeof __DualMap !== "undefined" && typeof __DualMap.getLayer(layer.id) === "undefined"){
                        __DualMap.addLayer(layer);
                    }
                }
                _Worker.postMessage({resume:true});

            }, 1000);
        }

        /**
         * gits worker job id 생성
         * @param jobName
         * @returns {string}
         */

		control.generateJobId = function(jobName){
		    let array = new Uint8Array(16);
		    window.crypto.getRandomValues(array);
		    let jobId = Array.from(array, byte => byte.toString(36)).join('').substring(0,16);
		    this.jobStart(jobId, jobName);
		    jobList.push(jobId);
		    return jobId;
		};

        /**
         * job 시작 로딩엘리먼트 생성
         * @param jobId
         * @param jobName
         */
        control.jobStart = function(jobId, jobName){
            let jobItem = $(`
                <div data-job-id="${jobId}" class="job-item">
                    <div class="lds-spinner"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div>
                    <div style="margin-left:4px;">${jobName}</div>
                </div>`);
            $("#mapDataLoadingJobList").prepend(jobItem);
            jobItem.slideDown(250);
        }

        /**
         * 타일 소스 제거
         * @param sourceId
         */
        control.removeCustomSource = function(sourceId){
            if(sourceId.indexOf(GITS_ENV.LAYER_PREFIX) < 0) {
                sourceId = GITS_ENV.LAYER_PREFIX + sourceId;
            }
            let idx = 0;
            let removedSourceId = [];
            for(const customSourceLayer of customSourceLayerIds){
                if(customSourceLayer.sourceId !== sourceId) {
                    continue;
                }
                if(customSourceLayer.layerIds) {
                    for(const layerId of customSourceLayer.layerIds) {
                        if(_Map.getLayer(layerId))
                            _Map.removeLayer(layerId);
                    }
                }
                if(customSourceLayer.sourceId) {
                    if(_Map.getSource(customSourceLayer.sourceId))
                        _Map.removeSource(customSourceLayer.sourceId);
                }
                removedSourceId.push(sourceId);
                idx++;
            }
            for(const sourceId of removedSourceId) {
                _Util.removeByAttr(customSourceLayerIds, "sourceId", sourceId);
            }
        }

        /**
         * 클러스터용 레이어 및 소스 추가
         * @param sourceId
         * @param featureCollection
         * @param layerArray
         */
        control.addExpertSourceAndLayer = function(sourceId, clusterOption, featureCollection, layerArray, afterLevelLayer = null){
            if(sourceId.indexOf(GITS_ENV.LAYER_PREFIX) < 0) {
                sourceId = GITS_ENV.LAYER_PREFIX + sourceId;
            }
            let featureGeoJson = {
                type: "geojson",
                data : featureCollection
            }
            featureGeoJson = $.extend(featureGeoJson, clusterOption, {});
            if(!_Map.getSource(sourceId)) {
                _Map.addSource(sourceId, featureGeoJson);
            }
            let includSourceLayer = {
                sourceId : sourceId,
                layerIds : []
            }
            if(layerArray) {
                for(const layer of layerArray) {
                    if(layer.id.indexOf(GITS_ENV.LAYER_PREFIX) < 0) {
                        layer.id = GITS_ENV.LAYER_PREFIX + layer.id;
                    }
                    if(!_Map.getLayer(layer.id)) {
                        _Map.addLayer(layer, afterLevelLayer);
                    }
                    includSourceLayer.layerIds.push(layer.id);
                }
            }
            customSourceLayerIds.push(includSourceLayer);
        }
        /**
         * 소스 추가 및 레이어 생성
         * @param sourceType
         * @param sourceId
         * @param sourceLayerId
         * @param featureCollection
         */
        control.addSourceAndLayer = function(sourceType ="geojson", sourceId, sourceLayerId, featureCollection, lineLayer = true, iconLayer = true, customLayerArray){
            if(sourceId.indexOf(GITS_ENV.LAYER_PREFIX) < 0) {
                sourceId = GITS_ENV.LAYER_PREFIX + sourceId;
            }
            if(sourceLayerId.indexOf(GITS_ENV.LAYER_PREFIX) < 0) {
                sourceLayerId = GITS_ENV.LAYER_PREFIX + sourceLayerId;
            }
            if(LOG_LEVEL === "DEBUG") console.log("Add Source and Layer. " , sourceId,  sourceLayerId);
            let featureGeoJson = {
                type: sourceType,
                data : featureCollection
            }
            if(!_Map.getSource(sourceId)) _Map.addSource(sourceId, featureGeoJson);
            if(lineLayer) {
                if(!_Map.getLayer(sourceLayerId+"_line")) {
                    _Map.addLayer({
                        'id': sourceLayerId + "_line",
                        'type': 'line',
                        'source': sourceId,
                        'maxzoom': 22,
                        'minzoom': 9,
                        'layout': {
                            'line-join': 'round',
                            'line-cap': 'round'
                        },
                        'paint': {
                            'line-color': "#ee05cb",
                            'line-opacity': 1,
                            'line-width': 3
                        },
                    });
                }
            }
            if(iconLayer) {
                if(!_Map.getLayer(sourceLayerId)) {
                    _Map.addLayer({
                        'id': sourceLayerId,
                        'type': 'symbol',
                        'source': sourceId,
                        'maxzoom': 22,
                        'minzoom': 9,
                        'layout': {
                            'icon-allow-overlap': true,
                            'icon-image': ['get', 'icon'],
                            "icon-size": [
                                'interpolate',
                                ['linear'],
                                ['zoom'],
                                10, 0.5,
                                15, 0.75
                            ]
                        }
                    });
                }
            }
            let includedSourceLayer = {
                sourceId : sourceId,
                layerIds : [sourceLayerId, sourceLayerId+"_line"]
            }
            if(customLayerArray) {
                for(const customLayer of customLayerArray) {
                    if(customLayer.id.indexOf(GITS_ENV.LAYER_PREFIX) < 0) {
                        customLayer.id = GITS_ENV.LAYER_PREFIX + customLayer.id;
                    }
                    _Map.addLayer(customLayer);
                    includedSourceLayer.layerIds.push(customLayer.id);
                }
            }
            customSourceLayerIds.push(includedSourceLayer);
        }
        /**
         * 레이어 필터
         * @param propertyKey
         * @param value
         */
        control.filterEqual = function(propertyKey, value) {
            // map.setFilter('node', ["==", 'layer', "안양시_node"]);
            // map.setFilter('link', ["==", 'layer', "안양시_node"]);
            _Map.setFilter(LAYER.NODE, ["==", propertyKey, value]);
            _Map.setFilter(LAYER.LINK, ["==", propertyKey, value]);
        }

        /**
         * 레이어 커스텀 필터
         * @param propertyKey
         * @param value
         */
        control.filterCustom = function(propertyKey, value) {
            // map.setFilter('node', ["==", 'layer', "안양시_node"]);
            // map.setFilter('link', ["==", 'layer', "안양시_node"]);
            _Map.setFilter(LAYER.NODE, ["==", propertyKey, value]);
            _Map.setFilter(LAYER.LINK, ["==", propertyKey, value]);
        }

        /**
         * 조건에 맞게 색상지정
         * @param layer 레이어
         * @param searchKey 조건키
         * @param conditions [[value, color],[value, color]]
         */
        control.setPaintFilterColor = function(layer, searchKey, conditions) {
            let propKey = "";
            let color = [
                'match',
                ['get', searchKey],
            ];
            for(const condition of conditions) {
                color.push(condition[0])
                color.push(condition[1])
            }
            switch(layer) {
                case LAYER.LINK :
                    propKey = "line-color";
                    color.push(STYLES.LINK_COLOR);
                    break;
                case LAYER.NODE :
                    propKey = "circle-color";
                    color.push(STYLES.NODE_COLOR);
                    break;
            }
            _Map.setPaintProperty(layer, propKey, color);
        }

        control.setGridColor = function(searchKey, conditions){

            _Map.setPaintProperty(layer, propKey, color);
        }

        /**
         * 투명도 설정
         * @param layer
         * @param searchKey
         * @param conditions
         */
        control.setPaintFilterOpacity = function(layer, searchKey, conditions, default_opacity = 1) {
            let propKey = "line-opacity";
            let color = [
                'match',
                ['get', searchKey],
            ];
            if(conditions) {
                for (const condition of conditions) {
                    color.push(condition[0])
                    color.push(condition[1])
                }
            }
            switch(layer) {
                case LAYER.LINK :
                    propKey = "line-opacity";
                    color.push(default_opacity);
                    break;
                case LAYER.NODE :
                    propKey = "circle-opacity";
                    color.push(default_opacity);
                    break;
            }
            if(conditions)
                _Map.setPaintProperty(layer, propKey, color);
            else
                _Map.setPaintProperty(layer, propKey, default_opacity);
        }

        /**
         * 라인 두께
         * @param layer
         * @param searchKey
         * @param conditions
         */
        control.setPaintFilterLineWidth = function(searchKey, conditions, default_line_width = 1) {
            let propKey = "line-opacity";
            let obj = [
                'match',
                ['get', searchKey],
            ];
            for(const condition of conditions) {
                obj.push(condition[0])
                obj.push(condition[1])
            }
            propKey = "line-width";
            obj.push(default_line_width);
            _Map.setPaintProperty(LAYER.LINK, propKey, obj);
        }

        control.setPaintFilterLineWidthDefault = function(){
            _Map.setPaintProperty(LAYER.LINK, "line-width", 1);
        }

        /**
         * 투명도 초기화
         */
        control.setPaintOpacityDefault = function(){
            _Map.setPaintProperty(LAYER.NODE, "circle-opacity", 1);
            _Map.setPaintProperty(LAYER.LINK, "line-opacity", 1);
        }

        /**
         *
         * @param layer
         * @param searchKey
         * @param conditions
         * @param otherColor
         */
        control.setPaintFilterColorByState = function(layer, searchKey, conditions, otherColor) {
            let propKey = "";
            let color = [
                'case',
            ];
            for(const condition of conditions) {
                const c = ['==', ['feature-state',searchKey], condition[0]];
                color.push(c);
                color.push(condition[1]);
            }
            switch(layer) {
                case LAYER.LINK :
                    propKey = "line-color";
                    color.push(!otherColor ? STYLES.LINK_COLOR : otherColor);
                    break;
                case LAYER.NODE :
                    propKey = "circle-color";
                    color.push(!otherColor ? STYLES.NODE_COLOR : otherColor);
                    break;
            }
            if(LOG_LEVEL === "DEBUG")
                console.log(color);
            _Map.setPaintProperty(layer, propKey, color);
        }

        /**
         * 색상 기본설정
         * @param layer 레이어
         */
        control.setPaintDefault = function(layer) {
            let propKey = "";
            let color = "";
            switch(layer) {
                case LAYER.LINK :
                    propKey = "line-color";
                    color = STYLES.LINK_COLOR;
                    break;
                case LAYER.NODE :
                    propKey = "circle-color";
                    color = STYLES.NODE_COLOR;
                    break;
                default :
					new ModalBuilder().init().alertBoby("레이어가 선택되지 않았습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
                    return;
            }
            _Map.setPaintProperty(layer, propKey, color);
        }

        /**
         * 레이어 숨기기
         * @param layer
         */
        control.hideLayer = function(layer) {
            if(_Map.getLayer(layer))
                _Map.setLayoutProperty(layer, 'visibility', 'none');
        }

        /**
         * 레이어 보여주기
         * @param layer
         */
        control.showLayer = function(layer) {
            if(_Map.getLayer(layer))
                _Map.setLayoutProperty(layer, 'visibility', 'visible');
        }

        /**
         * 마커 marker
         * @type {{_getFeature: (function(*, *): *), byNodeType: GITSMapCore.marker.byNodeType, clearAll: GITSMapCore.marker.clearAll, byNodeId: GITSMapCore.marker.byNodeId}}
         */
        control.marker = {
            _getFeature : function(_key, _value){
                // testcode start
                control.setPaintFilterColor(LAYER.NODE, _key,[[_value,'#ffffff']]);
                control.setPaintFilterColor(LAYER.LINK, _key,[[_value,'#ffffff']]);
                // testcode end
                return _Map.querySourceFeatures('nodelink', {
                    'sourceLayer': tileset_layer,
                    'filter' : ['==', _key, _value]
                });
            },
            drawMaker : function(el, feature){
                openMarkers.push(new mapboxgl.Marker(el, {offset : [0, -20]})
                    .setLngLat(feature.geometry.coordinates)
                    .setPopup(
                        new mapboxgl.Popup({offset: 25}) // add popups
                            .setHTML(
                                `<h3 style="color:#000;font-size:12px">${feature.properties.NODE_ID}</h3><p style="color:#000;font-size:12px">${feature.properties.NODE_NAME}</p>`
                            )
                    )
                    .addTo(_Map));
            },
            drawMakerByLatLng : function(el, lng,lat, popup_el){
                let newmarker = new mapboxgl.Marker(el)
                    .setLngLat([lng, lat]);
                if(popup_el) {
                    newmarker.setPopup(
                        new mapboxgl.Popup({offset:[0, -30], maxWidth : "none"})
                            .setHTML(popup_el.outerHTML)
                    )
                }
                newmarker.addTo(_Map);
                openMarkers.push(newmarker);
            },
            clearAll : function(){
                for(const maker of openMarkers) {
                    maker.remove();
                }
                openMarkers = [];
            },
            byRoadType : function(value){
                this.clearAll();
                let features = this._getFeature("ROAD_TYPE", value);
                for(const feature of features) {
                    const el = document.createElement('div');
                    el.className = 'marker';
                    el.style.background = "#000";
                    el.style.width = "200px";
                    el.style.height = "200px";
                    el.style.fontSize = "12px";
                    el.innerHTML = `<p style="color:#fff">${feature.properties.NODE_NAME}</p>`;
                    this.drawMaker(el, feature);
                }
            },
            byNodeType : function(value){
                this.clearAll();
                let features = this._getFeature("NODE_TYPE", value);
                for(const feature of features) {
                    const el = document.createElement('div');
                    el.className = 'marker';
                    el.style.background = "#000";
                    el.style.width = "200px";
                    el.style.height = "200px";
                    el.style.fontSize = "12px";
                    el.innerHTML = `<p style="color:#fff">${feature.properties.NODE_NAME}</p>`;
                    this.drawMaker(el, feature);
                }
            },
            byNodeId : function(value){
                this.clearAll();
                let features = this._getFeature("NODE_ID", value);
                for(const feature of features) {
                    const el = document.createElement('div');
                    el.className = 'marker';
                    el.style.background = "#000";
                    el.style.width = "20px";
                    el.style.height = "20px";
                    el.style.fontSize = "12px";
                    el.innerHTML = `<p style="color:#fff">${feature.properties.NODE_NAME}</p>`;
                    this.drawMaker(el, feature);
                }
            }
        }

        control.generatePlayerModel = function(title, content, playEvent, stopEvent){
            $(".chart_video_container .tab_box_title").text("교차로 교통량 변화");
            $(".chart_video_container .chart_video_body").html(content);

            $(".chart_video_container .chart_play").off("click").on("click", function(){
                playEvent();
				$(this).removeClass('svg_active');
            });
            $(".chart_video_container .chart_stop").off("click").on("click", function(){
                stopEvent();
				$('.chart_play').addClass('svg_active');
            });
            $(".chart_video_container").show();
        }
        /**
         * 지도 이동
         * @param coordinates
         * @param zoom
         */
        control.moveMap = function(coordinates, zoom){
            let flyToObj = {
                center : coordinates
            }
            if(zoom){
                flyToObj.zoom = zoom;
            } else {
                flyToObj.zoom = _Map.getZoom();
            }
            flyToObj.duration = 500;
            _Map.flyTo(flyToObj);
        }

        /**
         * 위치 포커스 및 팝업
         * @param _this
         * @param lng
         * @param lat
         * @param popup
         */
        control.highlightingTarget = function(_this, lng, lat, popup) {
            $('.mapboxgl-popup').remove();
            _Map.once('moveend', function(){
                const coords = new mapboxgl.LngLat(lng, lat);
                const point = _Map.project(coords);
                setTimeout(function(){
                    _Map.fire('click', {point: point});
                },1000)

            /*
                new mapboxgl.Popup()
                    .setLngLat([lng, lat])
                    .setHTML(`
                        <div class="data_popup">
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">
								${popup}
                            </ul>
                        </div>
                    `)
                    .addTo(_Map);*/
            });
            if(lng && lat){
                control.moveMap([lng, lat], 18);
            }
        }
        /**
         * 노드아이디 하이라이팅
         */
        control.highlightingByNodeId = function(_this, nodeId, lng, lat){
            $('.mapboxgl-popup').remove();
            control.setPaintOpacityDefault();
            let conditions = [[nodeId, 1]]
            control.setPaintFilterOpacity(LAYER.NODE, "NODE_ID", conditions, 1);
			const markerHtml = _this.dataset.markerHtml;
            control.setPaintFilterOpacity(LAYER.LINK, "LINK_ID", null, 1);

            _Map.once('moveend', function(){
                setTimeout(function() {
                    const features = _Map.querySourceFeatures(LAYER.NODE, {sourceLayer: node_tileset_layer});
                    const linkFeature = _getUniqueFeatures(features, "NODE_ID", nodeId);
                    let html = '';
                    if (markerHtml) {
                        html = markerHtml;
                    } else {
                        if (linkFeature) {
                            const prop = linkFeature.properties;
                            html += `<li class="popup_item">노드명 : <span>${prop['NODE_NAME']}</span></li>`;
                            html += `<li class="popup_item">노드유형 : <span>${GITS_ENV.NODE_TYPE[prop['NODE_TYPE']]}</span></li>`;
                            html += `<li class="popup_item">회전제한유무 : <span>${prop['TURN_P'] === "0" ? "무" : "유"}</span></li>`;
                        } else {
                            html = '정보가 존재하지 않습니다.';
                        }
                    }
                    new mapboxgl.Popup()
                        .setLngLat([lng, lat])
                        .setHTML(`
                        <div class="data_popup">
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">
								${html}
                            </ul>
                        </div>
                    `)
                        .addTo(_Map)
                }, 1000)
            });
            if(lng && lat){
                control.moveMap([lng, lat], 18);
            }
        }

        /**
         * 링크아이디 하이라이팅
         */
        control.highlightingByLinkId = function(_this, linkId, lng, lat){
            $('.mapboxgl-popup').remove();
            control.setPaintOpacityDefault();
            control.setPaintFilterOpacity(LAYER.NODE, "NODE_ID", null, 1);
            let opacityConditions = [[linkId, 1]];
            control.setPaintFilterOpacity(LAYER.LINK, "LINK_ID", opacityConditions, 1);
            let colorConditions = [[linkId, "#FFFFFF"]];
            control.setPaintFilterColor(LAYER.LINK, "LINK_ID", colorConditions);
            let lineWidthConditions = [[linkId, 3]];
            control.setPaintFilterLineWidth("LINK_ID", lineWidthConditions);
			const markerHtml = _this.dataset.markerHtml;
            if(lng && lat){
                _Map.once('moveend', function() {
                    setTimeout(function () {
                        const features = _Map.querySourceFeatures(LAYER.LINK, {sourceLayer: link_tileset_layer});
                        const linkFeature = _getUniqueFeatures(features, "LINK_ID", linkId);
                        let html = '';
                        if (markerHtml) {
                            html = markerHtml;
                        } else {
                            if (linkFeature) {
                                const prop = linkFeature.properties;
                                html += `<li class="popup_item">링크ID : <span>${prop['LINK_ID']}</span></li>`;
                                html += `<li class="popup_item">도로명 : <span>${prop['ROAD_NAME']}</span></li>`;
                                html += `<li class="popup_item">타입 : <span>${GITS_ENV.ROAD_TYPE[prop['ROAD_TYPE']]}</span></li>`;
                                html += `<li class="popup_item">레벨 : <span>${GITS_ENV.ROAD_RANK[prop['ROAD_RANK']]}</span></li>`;
                                html += `<li class="popup_item">차선수 : <span>${prop['LANES']}</span></li>`;
                                html += `<li class="popup_item">사용여부 : <span>${GITS_ENV.ROAD_USE[prop['ROAD_USE']]}</span></li>`;
                                html += `<li class="popup_item">최고속도 : <span>${prop['MAX_SPD']} km/h</span></li>`;
                                html += `<li class="popup_item">길이 : <span>${prop['LENGTH']}</span></li>`;
                            } else {
                                html = '정보가 존재하지 않습니다.';
                            }
                        }
                        new mapboxgl.Popup()
                            .setLngLat([lng, lat])
                            .setHTML(`
                        <div class="data_popup">
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">
								${html}
                            </ul>
                        </div>
                    `)
                            .addTo(_Map)
                    }, 1000);
                });
                control.moveMap([lng, lat], 14);
            }
        }
        control.zoomIn = function(){
            _Map.zoomTo(parseInt(_Map.getZoom())+1);
            _mapSameMoveToDualMap();
            _mapSameMoveToMap();
        }
        control.zoomOut = function(){
            _Map.zoomTo(parseInt(_Map.getZoom())-1);
            _mapSameMoveToDualMap();
            _mapSameMoveToMap();
        }

    }
    const stopWorkerInterval = function(eventName){
        _Worker.postMessage({stopInterval : true, event : eventName});
    }

    let workerResultEvent = {
        "CM_WARNING_ALARM" : function(e){
            if (e.data.error) {
                console.error("현황 수신 오류");
                return;
            }
            for(const item of e.data) {
                let id = "";
                switch(item.type) {
                    case "error" :
                        id = item.data.dsetId;
                        break;
                    case "warning" :
                        id = item.data.mngId + "-"+item.data.detailSeq;
                        break;
                }
                const warningCount = e.data.filter(i => i.type ==="warning").length;
                const errorCount = e.data.filter(i => i.type ==="error").length;
                $("#warningAlarmCount").text(warningCount);
                $("#errorAlarmCount").text(errorCount);
                if($(".outbreak_push_item[data-id='"+id+"']").length === 0 && !_getAlarmClosed(id)) {
                    let subject = (item.type === "error" ? "[장애] " : "[돌발] ") + item.date + "<br/>" + item.subject;
                    gitsApp.generatePushElement(item.type === "error" ? "DANGER" : "WARNING", subject, id, function () {
                        _setAlarmClosed(id);
                    });
                }
            }

        },
        "CM_CROSSROAD_CAMERA" : function(e) {
            const sourceName = LAYER.CROSSROAD_CAMERA;
            const layerName = LAYER.CROSSROAD_CAMERA;
            core.control.removeCustomSource(sourceName);
            let layer = {
                'id': layerName,
                'type': 'symbol',
                'source': sourceName,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': "camera",
                    "icon-size": 0.05,
                    'icon-rotate': ['get', 'angl']
                }
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [layer]);
            _Map.off('click', layerName, MapEvents.CM_BUS_STATION);
            _Map.on('click', layerName, MapEvents.CM_BUS_STATION);
            core.control.showLayer(LAYER.BUS_STATION);
        },
        "CM_BUS_ROUTE" : function(e, lineWidth, otherFeatureCollection){
            const sourceName = LAYER.BUS_ROUTE;
            const layerName = LAYER.BUS_ROUTE;
            core.control.removeCustomSource(sourceName);
            let layer = {
                'id': layerName,
                'type': 'line',
                'source': sourceName,
                'maxzoom': 22,
                'minzoom': 9,
                'layout': {
                    'line-join': 'round',
                    'line-cap': 'round'
                },
                'paint': {
                    'line-color': "#00a5ff",
                    'line-width': lineWidth ? lineWidth : 2,
                    'line-opacity': 0.8,
                },
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, otherFeatureCollection ? otherFeatureCollection : e.data.featureCollection, [layer], LAYER.LINK);
        },
        "CM_BUS_STATION" : function(e){
            if(LOG_LEVEL === "DEBUG")
                console.log(e);
            const sourceName = LAYER.BUS_STATION;
            const layerName = LAYER.BUS_STATION;
            core.control.removeCustomSource(sourceName);
            let busStationLayer = {
                'id': layerName,
                'type': 'symbol',
                'source': sourceName,
                'maxzoom': 22,
                'minzoom': 9,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': ['get', 'icon'],
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        10, 0.05,
                        15, 0.25
                    ]
                }
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [busStationLayer]);
            _Map.on('click', layerName, MapEvents.CM_BUS_STATION);
            core.control.showLayer(LAYER.BUS_STATION);
        },
        "F_SMART" : function(e){
            console.log(e.data);
            const sourceName = LAYER.FACILITY_SMART;
            const layerName = LAYER.FACILITY_SMART;
            const linkLayerName = LAYER.FACILITY_SMART_LINK;
            core.control.removeCustomSource(sourceName);
            let layer = {
                'id': layerName,
                'type': 'symbol',
                'source': sourceName,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': "smart_crossroad",
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        10, 0.05,
                        15, 0.5
                    ]
                },
                filter : ['has', 'nodeId']
            }
            let lineLayer = {
                'id': linkLayerName,
                'type': 'line',
                'source': sourceName,
                'layout': {
                    'line-join': 'round',
                    'line-cap': 'round'
                },
                'paint': {
                    'line-color': '#f1f075',
                    'line-width': 3,
                    'line-opacity': 1,
                },
                filter : ['has', 'linkId']
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [lineLayer,layer]);

            _Map.off("click", layerName, MapEvents.F_SMART);
            _Map.on("click", layerName, MapEvents.F_SMART);
            /*_Map.off("click", linkLayerName, MapEvents.F_SMART_LINK);
            _Map.on("click", linkLayerName, MapEvents.F_SMART_LINK);*/
        },
        "F_VDS" : function(e) {
            const sourceName = LAYER.FACILITY_VDS;
            const layerName = LAYER.FACILITY_VDS;
            core.control.removeCustomSource(sourceName);
            let layer = {
                'id': layerName,
                'type': 'symbol',
                'source': sourceName,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': "vds",
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        10, 0.05,
                        15, 0.5
                    ]
                },
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [layer]);

            _Map.off("click", layerName, MapEvents.F_VDS);
            _Map.on("click", layerName, MapEvents.F_VDS);
        },
        "F_DSRC" : function(e) {
            const sourceName = LAYER.FACILITY_DSRC;
            const layerName = LAYER.FACILITY_DSRC;
            const linkLayerName = LAYER.FACILITY_DSRC_LINK;
            core.control.removeCustomSource(sourceName);
            let layer = {
                'id': layerName,
                'type': 'symbol',
                'source': sourceName,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': "dsrc",
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        10, 0.05,
                        15, 0.5
                    ]
                },
                filter : ['has', 'rseId']
            }
            let lineLayer = {
                'id': linkLayerName,
                'type': 'line',
                'source': sourceName,
                'layout': {
                    'line-join': 'round',
                    'line-cap': 'round'
                },
                'paint': {
                    'line-color': '#f1f075',
                    'line-width': 3,
                    'line-opacity': 1,
                },
                filter : ['has', 'linkId']
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [layer,lineLayer]);

            _Map.off("click", layerName, MapEvents.F_DSRC);
            _Map.on("click", layerName, MapEvents.F_DSRC);
            _Map.off("click", linkLayerName, MapEvents.F_DSRC_LINK);
            _Map.on("click", linkLayerName, MapEvents.F_DSRC_LINK);
        },
        "F_SIGNAL" : function(e) {
            const sourceName = LAYER.FACILITY_SIGNAL;
            const layerName = LAYER.FACILITY_SIGNAL;
            core.control.removeCustomSource(sourceName);
            let layer = {
                'id': layerName,
                'type': 'symbol',
                'source': sourceName,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': "signal",
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        10, 0.05,
                        15, 0.5
                    ]
                }
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [layer]);

            _Map.off("click", layerName, MapEvents.F_SIGNAL);
            _Map.on("click", layerName, MapEvents.F_SIGNAL);

        },
        "M_TRAFFIC" : function(e){
            if(!_Map.getLayer(LAYER.LINK)){
                return;
            }
            for(const item of e.data) {
                _Map.setFeatureState({
                    source: LAYER.LINK,
                    sourceLayer: monitoring_link_tileset_layer,
                    id: item.linkId,
                }, {
                    conggrade: item.congGrade
                });
            }
            const colors = [
                [null , STYLES.LINK_COLOR],
                ["0" , "#74aaff"],
                ["1" , "#11ff00"],
                ["2" , "#ffde00"],
                ["3" , "#de0909"],
            ];
            if(!JSON.stringify(_Map.getPaintProperty(LAYER.LINK, 'line-color')).includes("conggrade"))
                core.control.setPaintFilterColorByState(LAYER.LINK, "conggrade", colors, "#11ff00");

            _Map.on("click",LAYER.LINK, MapEvents.TEST);

        },
        "M_DANGER_VEHICLE" :function(e) {

        },
        "M_EMERGENCY" : function(e){
            const layer = LAYER.EMERGENCY;
            const routelayer = LAYER.EMERGENCY_ROUTE;
            const routePointLayer = LAYER.EMERGENCY_ROUTE_POINT;
            if(_Map.getLayer(layer)){
                _Map.getSource(layer).setData(e.data.collection.featureCollection);
                _Map.getSource(routelayer).setData(e.data.routeCollection.featureCollection);
            }else{
                let emergencyLayer = {
                    'id': layer,
                    'type': 'symbol',
                    'source': layer,
                    'maxzoom': 22,
                    'minzoom': 8,
                    'layout': {
                        'icon-allow-overlap': true,
                        'icon-image': ['get', 'icon'],
                        "icon-size": [
                            'interpolate',
                            ['linear'],
                            ['zoom'],
                            10, 0.5,
                            15, 0.75
                        ]
                    },
                    filter : ['has', 'icon']
                }
                let alertAreaLayer = {
                    'id': layer+"_alert_area",
                    'type': 'fill',
                    'source': layer,
                    'layout': {},
                    'paint': {
                        'fill-color': '#3fbfde',
                        'fill-opacity': 0.3
                    },
                    filter : ['has', 'alertArea']
                }
                core.control.addExpertSourceAndLayer(layer, {}, e.data.collection.featureCollection, [emergencyLayer,alertAreaLayer]);


                let routeRoadLayer = {
                    'id': routelayer,
                    'type': 'line',
                    'source': routelayer,
                    'layout': {
                        'line-join': 'round',
                        'line-cap': 'round'
                    },
                    'paint': {
                        'line-color': "#3fbfde",
                        'line-opacity': 1,
                        'line-width': 5
                    },
                    filter : ["==", 'type', "LineString"]
                }

                let pointLayer = {
                    id: routePointLayer,
                    type: 'circle',
                    source: routelayer,
                    paint: {
                        'circle-color': "transparent",
                        'circle-radius': 5,
                        'circle-opacity' : 0.8,
                        'circle-stroke-color' : "#3fbfde",
                        'circle-stroke-width' : 2
                    },
                    filter : ["==", 'type', "Point"]
                }

                core.control.addExpertSourceAndLayer(routelayer, {}, e.data.routeCollection.featureCollection, [routeRoadLayer,pointLayer]);
            }
            let pushedEmergencyService = [];
            if(e.data.monitoringEmergencyBoundBox) {
                for (const serviceid in e.data.monitoringEmergencyBoundBox) {
                    const monitoringEmergencyInfo = e.data.monitoringEmergencyBoundBox[serviceid];
                    let id = "EMER_"+serviceid;
                    pushedEmergencyService.push(id);
                    const warningCount = parseInt($("#warningAlarmCount").text())+1;
                    $("#warningAlarmCount").text(warningCount);
                    if ($(".outbreak_push_item[data-id='" + id + "']").length === 0 && !_getAlarmClosed(id)) {
                        let subject = "[긴급차량발생]["+monitoringEmergencyInfo.ocrtype+"] "+monitoringEmergencyInfo.evno;
                        gitsApp.generatePushElement("WARNING", subject, id, function () {
                            _setAlarmClosed(id);
                        }, function(){
                            try{
                                _Map.fitBounds(monitoringEmergencyInfo.bbox, {padding: 100});
                            }catch(e){console.error('포커싱 에러')}
                        }, "push-item-emergency");
                    }
                }
            }
            $(".outbreak_push_item.push-item-emergency").each(function(){
                const id = $(this).data("id");
                if(pushedEmergencyService.indexOf(id) < 0) {
                    $(this).slideUp(function(){
                        $(this).remove();
                    }, 250);
                }
            });

            if(e.data.dangerCar) {
                for (const item of e.data.dangerCar) {
                    let id = item.vhclRegistNo;
                    const warningCount = parseInt($("#warningAlarmCount").text())+1;
                    $("#warningAlarmCount").text(warningCount);
                    if ($(".outbreak_push_item[data-id='" + id + "']").length === 0 && !_getAlarmClosed(id)) {
                        let subject = "[위험물차량접근] " + item.occurDt+"<br/>차량번호 : "+id;
                        gitsApp.generatePushElement("WARNING", subject, id, function () {
                            _setAlarmClosed(id);
                        }, function(){
                            core.control.moveMap([item.targetLon, item.targetLat]);
                        });
                    }
                }
            }
            if(core.animate.hasFocus(layer)){
                const key = core.animate.getFocusKey();
                const value = core.animate.getFocusValue();
                const feature = _getUniqueFeatures(e.data.featureCollection.features, key, value);
                if(feature) {
                    core.control.moveMap(feature.geometry.coordinates);
                    let openedPopup = core.animate.getOpenedPopup();
                    if(openedPopup) {
                        openedPopup.close();
                        let newDataPopup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                            .setLngLat(feature.geometry.coordinates)
                            .setHTML(`
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <ul class="data_sub mt16">
                                         ${feature.properties.description}
                                    </ul>
                                    <button class="focus_button" data-id="${feature.properties.serviceid}">따라가기</button>
                                </div>
                            `)
                            .addTo(_Map)
                        core.animate.setOpenedPopup(newDataPopup);
                    }
                }else{
                    core.animate.removeFocus();
                }
            }
            _Map.on("click", layer, MapEvents.M_EMERGENCY);
        },
        "M_BUS" : function(e){
            const layer = LAYER.BUS;
            if(_Map.getLayer(layer)) {
                _Map.getSource(layer).setData(e.data.featureCollection);
            }else {
                let busLayer = {
                    'id': layer,
                    'type': 'symbol',
                    'source': layer,
                    'maxzoom': 22,
                    'minzoom': 9,
                    'layout': {
                        'icon-allow-overlap': true,
                        'icon-image': ['get', 'icon'],
                        "icon-size": [
                            'interpolate',
                            ['linear'],
                            ['zoom'],
                            10, 0.05,
                            15, 0.5
                        ]
                    }
                }
                core.control.addExpertSourceAndLayer(layer, {}, e.data.featureCollection, [busLayer]);
            }


        },
        "M_WARNING" : function (e) {
            if (e.data.error) {
                console.error("돌발현황 데이터 수신 오류");
                return;
            }
            const layer = GITS_ENV.LAYER_PREFIX+"M_WARNING";
            if(_Map.getLayer(layer)){
                _Map.getSource(layer).setData(e.data.collection.featureCollection);
            }else{
                let carLayer = {
                    'id': layer,
                    'type': 'symbol',
                    'source': layer,
                    'maxzoom': 22,
                    'minzoom': 8,
                    'layout': {
                        'icon-allow-overlap': true,
                        'icon-image': ['get', 'icon'],
                        "icon-size": [
                            'interpolate',
                            ['linear'],
                            ['zoom'],
                            10, 0.5,
                            15, 0.75
                        ]
                    },
                    filter : ['has', 'icon']
                }
                let alertAreaLayer = {
                    'id': layer+"_alert_area",
                    'type': 'fill',
                    'source': layer,
                    'layout': {},
                    'paint': {
                        'fill-color': 'red',
                        'fill-opacity': 0.3
                    },
                    filter : ['has', 'alertArea']
                }
                core.control.addExpertSourceAndLayer(layer, {}, e.data.collection.featureCollection, [alertAreaLayer,carLayer]);
            }
            if(e.data.dangerCar) {
                for (const item of e.data.dangerCar) {
                    let id = item.vhclRegistNo;
                    const warningCount = parseInt($("#warningAlarmCount").text())+1;
                    $("#warningAlarmCount").text(warningCount);
                    if ($(".outbreak_push_item[data-id='" + id + "']").length === 0 && !_getAlarmClosed(id)) {
                        let subject = "[위험물차량접근] " + item.occurDt+"<br/>차량번호 : "+id;
                        gitsApp.generatePushElement("WARNING", subject, id, function () {
                            _setAlarmClosed(id);
                        }, function(){
                            core.control.moveMap([item.targetLon, item.targetLat]);
                        });
                    }
                }
            }

            core.control.marker ? core.control.marker.clearAll() : null;
            //const msgbody = e.data.serviceresult.msgbody;
            //for (const item of msgbody.itemlist) {
			for (const item of e.data.list) {
                const markerWrap = document.createElement("div");
                const markerIcon = document.createElement("i");
              //const markerText = document.createElement('div');
                markerWrap.append(markerIcon);
              //markerWrap.append(markerText);
                markerWrap.className = "map_icon_wrap";
                markerIcon.className = "map_icon icon_construction";
              //markerText.className = "map_icon_text red";
              //markerText.innerHTML = item.description;
				let roadName = ""; 
				let roadDirection =	"";
				if(item.roadwayNm != null){
					var idx = 0;
					for(const roadwayNm of item.roadwayNm){
						if(idx == 0 && roadwayNm != null){
							roadName = item.roadwayNm.split("|")[0];
						}
						if(idx == 1 && roadwayNm != null){
							roadDirection = item.roadwayNm.split("|")[1];
						}
						idx++;
					}
				}
		
                //popup element
                const popupWrap = document.createElement("div");
                popupWrap.innerHTML =
					`
				            <div class="data_popup">
								<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                <i class="map_icon bus_icon data_icon"></i>
								<ul class="mt16">
								    <li class="data_sub popup_item">내용 : <span>${item.description}</span></li>
	                                <li class="data_sub popup_item">발생시간 : <span>${_Util.parseYYYYMMDDHHMMtoKorean(item.beginDate)}</span></li>
	                                <li class="data_sub popup_item">종료(예정)시간 : <span>${item.endDate ? _Util.parseYYYYMMDDHHMMtoKorean(item.endDate) : "미정"}</span></li>
	                                <li class="data_sub popup_item">발생차선 : <span>${item.occurredLane}차선</span></li>
	                                <li class="data_sub popup_item">통제차선 : <span>${item.closedLane}차선</span></li>
	                                <li class="data_sub popup_item">돌발유형 : <span>${GITS_ENV.INCI_CATE[item.inciCate] ? GITS_ENV.INCI_CATE[item.inciCate] : "유형없음"}</span></li>
	                                <li class="data_sub popup_item">도로명 : <span>`+roadName+`</span></li>
	                                <li class="data_sub popup_item">방향 : <span>`+roadDirection+`</span></li>
								</ul>
                            </div>
				`;

                core.control.marker.drawMakerByLatLng(markerWrap, item.gpsX, item.gpsY, popupWrap)
            }
        },
        "M_WEATHER" : function(e){
            core.control.removeCustomSource(LAYER.GRID_WEATHER);
            core.control.addSourceAndLayer("geojson", LAYER.GRID_WEATHER,LAYER.GRID_WEATHER,e.data.featureCollection, false, true);
        },
        "BD_PREDICTION_ACCIDENT" : function(e) {

            defaultLayer.drawLinkLayer();
            core.control.hideLayer(LAYER.NODE);
            for(const item of e.data.linkData) {
                let state = {};
                for(const key in item){
                    state[key] = item[key];
                }
                _Map.setFeatureState({
                    source: LAYER.LINK,
                    sourceLayer: link_tileset_layer,
                    id: item.linkId,
                }, state);
            }


            _Map.setPaintProperty(LAYER.LINK, 'line-color', [
                'case',
                ['==', ['feature-state',"safeGrd"], "-3"],
                LEGEND_COLOR.TRAFFIC_6LEVEL[0],
                ['==', ['feature-state',"safeGrd"], "-2"],
                LEGEND_COLOR.TRAFFIC_6LEVEL[1],
                ['==', ['feature-state',"safeGrd"], "-1"],
                LEGEND_COLOR.TRAFFIC_6LEVEL[2],
                ['==', ['feature-state',"safeGrd"], "0"],
                LEGEND_COLOR.TRAFFIC_6LEVEL[3],
                ['==', ['feature-state',"safeGrd"], "1"],
                LEGEND_COLOR.TRAFFIC_6LEVEL[4],
                ['==', ['feature-state',"safeGrd"], "2"],
                LEGEND_COLOR.TRAFFIC_6LEVEL[5],
                ['==', ['feature-state',"safeGrd"], "3"],
                LEGEND_COLOR.TRAFFIC_6LEVEL[6],
                STYLES.LINK_COLOR
            ]);
            _Map.setPaintProperty(LAYER.LINK, 'line-opacity', [
                'case',
                ['==', ['feature-state',"safeGrd"], null],
                1,
                1
            ]);
            _Map.on("click", LAYER.LINK, MapEvents.BD_PREDICTION_ACCIDENT);


            const newMarkers = {};
            for(const m in markersOnScreen) {
                markersOnScreen[m].remove();
            }
            for(const maker of openMarkers) {
                maker.remove();
            }
            openMarkers = [];
            markersOnScreen = {};
            for (const feature of e.data.sggDataFeatureCollection.featureCollection.features) {
                const coords = feature.geometry.coordinates;
                const props = feature.properties;
                const id = props.sggNm;
                let marker = openMarkers[id];
                if (!marker) {
                    let el = null;
                    if(props.total && props.total !== 0) {
                        el = _Util.createDonutChart(props, id, [
                            props.noneLinkCntBySgg,
                            props.speedOverCntBySgg,
                            props.speedUnderCntBySgg,
                            props.safeCntBySgg,
                            props.warnCntBySgg,
                            props.dangerCntBySgg,
                            props.seriousCntBySgg
                        ], [LEGEND_COLOR.TRAFFIC_6LEVEL[0],
                            LEGEND_COLOR.TRAFFIC_6LEVEL[1],
                            LEGEND_COLOR.TRAFFIC_6LEVEL[2],
                            LEGEND_COLOR.TRAFFIC_6LEVEL[3],
                            LEGEND_COLOR.TRAFFIC_6LEVEL[4],
                            LEGEND_COLOR.TRAFFIC_6LEVEL[5],
                            LEGEND_COLOR.TRAFFIC_6LEVEL[6]]);
                    }else{
                        el = _Util.createDonutChart(props, id, [1], ['#999999']);
                    }
                    marker = openMarkers[id] = new mapboxgl.Marker({
                        element: el
                    }).setPopup(
                        new mapboxgl.Popup()
                            .setHTML(`
                                <div class="data_popup" style="width:14rem;">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p>${props.sggNm}</p>
                                    <ul>
                                        <li class="popup_item">링크정보누락 수 : <span>${props.noneLinkCntBySgg ? props.noneLinkCntBySgg : 0}</span></li>
                                        <li class="popup_item">속도초과 수 : <span>${props.speedOverCntBySgg ? props.speedOverCntBySgg : '0'}</span></li>
                                        <li class="popup_item">속도누락 수 : <span>${props.speedUnderCntBySgg ? props.speedUnderCntBySgg : 0}</span></li>
                                        <li class="popup_item">안전 수 : <span>${props.safeCntBySgg ? props.safeCntBySgg : 0}</span></li>
                                        <li class="popup_item">주의 수 : <span>${props.warnCntBySgg ? props.warnCntBySgg : 0}</span></li>
                                        <li class="popup_item">위험 수 : <span>${props.dangerCntBySgg ? props.dangerCntBySgg : 0}</span></li>
                                        <li class="popup_item">심각 수 : <span>${props.seriousCntBySgg ? props.seriousCntBySgg : 0}</span></li>
                                    </ul>
                                </div>
                            `)
                    ).setLngLat(coords)
                }
                newMarkers[id] = marker;

                if (!markersOnScreen[id]) marker.addTo(_Map);
            }
            for (const id in markersOnScreen) {
                if (!newMarkers[id]) markersOnScreen[id].remove();
            }
            markersOnScreen = newMarkers;
        },
        "BD_POPULATION" : function(e) {
            if(!e.data.gridList || e.data.gridList.length === 0) {
				new ModalBuilder().init().alertBoby("해당일자의 데이터가 존재하지 않습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
                return;
            }
            for(const item of e.data.gridList) {
                let state = {};
                state["fltPop_"+item.timezn] = item.fltPop;
                state['sggNm'] = _Util.getSGGInfoByCode(item.cityCd+"0",GITS_ENV).sggNm;
                state['cell500'] = item.cell500
                _Map.setFeatureState({
                    source: LAYER.GRID,
                    sourceLayer: grid_tileset_layer,
                    id: item.cell500,
                }, state);
            }
            function changeColorByLegnedTime(legend){
                _Map.setPaintProperty(LAYER.GRID, 'fill-color', [
                    'case',
                    ['==', ['feature-state',legend], null],
                    "rgba(0,0,0,0)",
                    ['<', ['feature-state',legend], 0],
                    LEGEND_COLOR.TRAFFIC_AMT[0],
                    ['all', ['>=', ['feature-state',legend], 0], ['<', ['feature-state',legend], 1000]],
                    LEGEND_COLOR.TRAFFIC_AMT[0],
                    ['all', ['>=', ['feature-state',legend], 1000], ['<', ['feature-state',legend], 5000]],
                    LEGEND_COLOR.TRAFFIC_AMT[1],
                    ['all', ['>=', ['feature-state',legend], 5000], ['<', ['feature-state',legend], 10000]],
                    LEGEND_COLOR.TRAFFIC_AMT[2],
                    ['all', ['>=', ['feature-state',legend], 10000], ['<', ['feature-state',legend], 20000]],
                    LEGEND_COLOR.TRAFFIC_AMT[3],
                    ['>=', ['feature-state',legend], 20000],
                    LEGEND_COLOR.TRAFFIC_AMT[4],
                    LEGEND_COLOR.TRAFFIC_AMT[4]
                ]);
            }
            if(e.data.matrixChartData) {
                e.data.matrixChartData.data.datasets[0].backgroundColor = function(c) {
                    const value = c.dataset.data[c.dataIndex].v;
                    if (value >= 0 && value < 1000) {
                        return LEGEND_COLOR.TRAFFIC_AMT[0]
                    } else if (value >= 1000 && value <= 2000) {
                        return LEGEND_COLOR.TRAFFIC_AMT[1]
                    } else if (value >= 5000 && value <= 10000) {
                        return LEGEND_COLOR.TRAFFIC_AMT[2]
                    } else if (value >= 10000 && value <= 20000) {
                        return LEGEND_COLOR.TRAFFIC_AMT[3]
                    } else {
                        return LEGEND_COLOR.TRAFFIC_AMT[4]
                    }
                }
                e.data.matrixChartData.data.datasets[0].width = function(c) {
                    const a = c.chart.chartArea || {};
                    return (a.right - a.left) / 24 - 1;
                }
                e.data.matrixChartData.data.datasets[0].height = function(c) {
                    const a = c.chart.chartArea || {};
                    return (a.bottom - a.top) / e.data.sggNmGroupList.length - 1;
                }
                e.data.matrixChartData.options.scales.x.ticks.color =  ["#fff"]
                e.data.matrixChartData.options.scales.y.ticks.color =  ["#fff"]
                e.data.matrixChartData.options.plugins.tooltip.callbacks.label = function(context) {
                    const v = context.dataset.data[context.dataIndex].v;
                    return ['예측인구(평균) : ' + v];
                }
                e.data.matrixChartData.plugins = [
                    {
                        id: 'click-anywhere',
                        afterEvent(chart, args) {
                            if (args.event.type === 'click') {
                                let {x, y} = chart.scales;
                                let xVal = x.getLabelForValue(x.getValueForPixel(args.event.x))
                                changeColorByLegnedTime("fltPop_"+xVal.split(":")[0]);
                                $(".chart_video_container .tab_box_title").text("시간대 유동인구 밀집 예측 분석 - "+xVal+" 데이터");
                            }
                        }
                    }
                ]

                let playerInterval = null;
                let progressInterval = null;
                let time = 0;
                let barTime = 0;
                core.control.generatePlayerModel("시간대 유동인구 밀집 예측 분석","<canvas id='danger_matrix_chart_canvas'></canvas>", function(){
                    if(!playerInterval) clearInterval(playerInterval);
                    if(!progressInterval) clearInterval(progressInterval);
                    playerInterval = setInterval(function(){
                        const value = (time < 10 ? "0"+time : time);
                        const legend = "fltPop_"+value;
                        changeColorByLegnedTime(legend);
                        $(".chart_video_container .tab_box_title").text("시간대 유동인구 밀집 예측 분석 - "+value+":00 데이터");
                        if(time === 24) {
                            clearInterval(playerInterval);
                            clearInterval(progressInterval);
                            $(".chart_video_container .tab_box_title").text("시간대 유동인구 밀집 예측 분석");
                            time = 0;
                            barTime = 0;
                            return;
                        }
                        time++;
                    }, 1000);
                }, function(){
                    const legend = "fltPop_00";
                    changeColorByLegnedTime(legend);
                    $(".chart_video_container .tab_box_title").text("시간대 유동인구 밀집 예측 분석");
                    clearInterval(playerInterval);
                    clearInterval(progressInterval);
                });
                const chart = new Chart('danger_matrix_chart_canvas', e.data.matrixChartData);
            }

            core.control.showLayer(LAYER.GRID);
            changeColorByLegnedTime('fltPop_00');
            _Map.off('click', LAYER.GRID, MapEvents.BD_POPULATION);
            _Map.on('click', LAYER.GRID, MapEvents.BD_POPULATION);
        },
        "BD_PT_DANGER_ANALYSIS" : function(e){

            workerResultEvent['CM_BUS_ROUTE'](e, 5, e.data.busRouteFeatureCollection.featureCollection);
            if(e.data.bbox) {
                try{
                _Map.fitBounds(e.data.bbox, {padding: 100});
                }catch(e){console.error('포커싱 에러')}
            }
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg).footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
            function changeLineColorByLegnedTime(legend){
                _Map.setPaintProperty(LAYER.BUS_ROUTE, 'line-color', [
                    'case',
                    ['==', ['get',legend], null],
                    LEGEND_COLOR.PB_DANGER_GRADE[0],
                    ['all', ['>=', ['get',legend], 0], ['<', ['get',legend], 10]],
                    LEGEND_COLOR.PB_DANGER_GRADE[0],
                    ['all', ['>=', ['get',legend], 10], ['<', ['get',legend], 20]],
                    LEGEND_COLOR.PB_DANGER_GRADE[0],
                    ['all', ['>=', ['get',legend], 20], ['<', ['get',legend], 30]],
                    LEGEND_COLOR.PB_DANGER_GRADE[1],
                    ['all', ['>=', ['get',legend], 30], ['<', ['get',legend], 40]],
                    LEGEND_COLOR.PB_DANGER_GRADE[2],
                    ['all', ['>=', ['get',legend], 40], ['<', ['get',legend], 50]],
                    LEGEND_COLOR.PB_DANGER_GRADE[3],
                    ['>=', ['get',legend], 50],
                    LEGEND_COLOR.PB_DANGER_GRADE[4],
                    LEGEND_COLOR.PB_DANGER_GRADE[0]
                ]);
            }
            if(e.data.matrixChartData) {
                e.data.matrixChartData.data.datasets[0].backgroundColor = function(c) {
                    const value = c.dataset.data[c.dataIndex].v;
                    if (value >= 0 && value < 5) {
                        return LEGEND_COLOR.PB_DANGER_GRADE[0];
                    } else if (value >= 5 && value <= 10) {
                        return LEGEND_COLOR.PB_DANGER_GRADE[1];
                    } else if (value >= 11 && value <= 20) {
                        return LEGEND_COLOR.PB_DANGER_GRADE[2];
                    } else if (value >= 21 && value <= 30) {
                        return LEGEND_COLOR.PB_DANGER_GRADE[3];
                    } else {
                        return LEGEND_COLOR.PB_DANGER_GRADE[4];
                    }
                }
                e.data.matrixChartData.data.datasets[0].borderColor = function(c) {
                    const value = c.dataset.data[c.dataIndex].v;
                    if (value >= 0 && value < 5) {
                        return LEGEND_COLOR.PB_DANGER_GRADE[0];
                    } else if (value >= 5 && value <= 10) {
                        return LEGEND_COLOR.PB_DANGER_GRADE[1];
                    } else if (value >= 11 && value <= 20) {
                        return LEGEND_COLOR.PB_DANGER_GRADE[2];
                    } else if (value >= 21 && value <= 30) {
                        return LEGEND_COLOR.PB_DANGER_GRADE[3];
                    } else {
                        return LEGEND_COLOR.PB_DANGER_GRADE[4];
                    }
                }
                e.data.matrixChartData.data.datasets[0].width = function(c) {
                    const a = c.chart.chartArea || {};
                    return (a.right - a.left) / 24 - 1;
                }
                e.data.matrixChartData.data.datasets[0].height = function(c) {
                    const a = c.chart.chartArea || {};
                    return (a.bottom - a.top) / e.data.roadGroupList.length - 1;
                }
                e.data.matrixChartData.options.scales.x.ticks.color =  ["#fff"]
                e.data.matrixChartData.options.scales.y.ticks.color =  ["#fff"]
                e.data.matrixChartData.options.plugins.tooltip.callbacks.label = function(context) {
                    const v = context.dataset.data[context.dataIndex].v;
                    return ['위험운행수 : ' + v];
                }
                e.data.matrixChartData.plugins = [
                    {
                        id: 'click-anywhere',
                        afterEvent(chart, args) {
                            if (args.event.type === 'click') {
                                let {x, y} = chart.scales;
                                let xVal = x.getLabelForValue(x.getValueForPixel(args.event.x))
                                changeLineColorByLegnedTime(legend+xVal.split(":")[0]);
                                $(".chart_video_container .tab_box_title").text("시간대별 위험등급 발생빈도 - "+xVal+" 데이터");
                            }
                        }
                    }
                ]

                let playerInterval = null;
                let progressInterval = null;
                let time = 0;
                let barTime = 0;
                core.control.generatePlayerModel("시간대별 위험등급 발생빈도","<canvas id='danger_matrix_chart_canvas'></canvas>", function(){
                    if(!playerInterval) clearInterval(playerInterval);
                    if(!progressInterval) clearInterval(progressInterval);
                    playerInterval = setInterval(function(){
                        const value = (time < 10 ? "0"+time : time);
                        const legend = "riskJugCnt"+value;
                        changeLineColorByLegnedTime(legend);
                        $(".chart_video_container .tab_box_title").text("시간대별 위험등급 발생빈도 - "+value+":00 데이터");
                        if(time === 24) {
                            clearInterval(playerInterval);
                            clearInterval(progressInterval);
                            $(".chart_video_container .tab_box_title").text("시간대별 위험등급 발생빈도");
                            time = 0;
                            barTime = 0;
                            return;
                        }
                        time++;
                    }, 1000);
                }, function(){
                    const legend = "riskJugCnt";
                    changeLineColorByLegnedTime(legend);
                    $(".chart_video_container .tab_box_title").text("시간대별 위험등급 발생빈도");
                    clearInterval(playerInterval);
                    clearInterval(progressInterval);
                });
                const chart = new Chart('danger_matrix_chart_canvas', e.data.matrixChartData);
            }
            const legend = "riskJugCnt";
            _Map.setPaintProperty(LAYER.BUS_ROUTE, 'line-color', [
                'case',
                ['==', ['get',legend], null],
                LEGEND_COLOR.PB_DANGER_GRADE[0],
                ['all', ['>=', ['get',legend], 0], ['<', ['get',legend], 10]],
                LEGEND_COLOR.PB_DANGER_GRADE[0],
                ['all', ['>=', ['get',legend], 10], ['<', ['get',legend], 20]],
                LEGEND_COLOR.PB_DANGER_GRADE[0],
                ['all', ['>=', ['get',legend], 20], ['<', ['get',legend], 30]],
                LEGEND_COLOR.PB_DANGER_GRADE[1],
                ['all', ['>=', ['get',legend], 30], ['<', ['get',legend], 40]],
                LEGEND_COLOR.PB_DANGER_GRADE[2],
                ['all', ['>=', ['get',legend], 40], ['<', ['get',legend], 50]],
                LEGEND_COLOR.PB_DANGER_GRADE[3],
                ['>=', ['get',legend], 50],
                LEGEND_COLOR.PB_DANGER_GRADE[4],
                LEGEND_COLOR.PB_DANGER_GRADE[0]
            ]);
            _Map.off('click', LAYER.BUS_ROUTE, MapEvents.BD_PT_DANGER_ANALYSIS);
            _Map.on('click', LAYER.BUS_ROUTE, MapEvents.BD_PT_DANGER_ANALYSIS);
        },
        "BD_DANGER_ROAD" : function(e) {
            const sourceName = LAYER.BD_DANGER_ROAD;
            const layerId = LAYER.BD_DANGER_ROAD;
            core.control.removeCustomSource(sourceName);
            let layer = {
                'id': layerId,
                'type': 'symbol',
                'source': sourceName,
                'maxzoom': 22,
                'minzoom': 8,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': ['get', 'icon'],
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        10, 0.5,
                        15, 0.75
                    ]
                }
            }
            core.control.addExpertSourceAndLayer(sourceName, {}, e.data.featureCollection, [layer]);

            _Map.off('click', layerId, MapEvents.BD_DANGER_ROAD);
            _Map.on('click', layerId, MapEvents.BD_DANGER_ROAD);
        },
        "BD_DANGER_ZONE_BY_TYPE" : function(e) {
            const sourceName = GITS_ENV.LAYER_PREFIX+"BD_DANGER_ZONE_BY_TYPE";
            const layerId = GITS_ENV.LAYER_PREFIX+"BD_DANGER_ZONE_BY_TYPE";
            const polygonLayerId = GITS_ENV.LAYER_PREFIX+"BD_DANGER_ZONE_BY_TYPE_POLYGON";
            core.control.removeCustomSource(sourceName);

            let layer = {
                'id': layerId,
                'type': 'symbol',
                'source': sourceName,
                'maxzoom': 22,
                'minzoom': 8,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': ['get', 'icon'],
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        10, 0.5,
                        15, 0.75
                    ]
                }
            }
            let polygonLayer = {
                'id': polygonLayerId,
                'type': 'fill',
                'source': sourceName,
                'layout': {},
                'paint': {
                    'fill-color': 'red',
                    'fill-opacity': 0.3
                }
            }
            core.control.addExpertSourceAndLayer(sourceName, {}, e.data.collection.featureCollection, [layer]);
            _Map.off('click', layerId, MapEvents.BD_DANGER_ZONE_BY_TYPE);
            _Map.on('click', layerId, MapEvents.BD_DANGER_ZONE_BY_TYPE);

            workerResultEvent["BD_ACCIDENT_BY_SGG"](e);
        },
        "BD_ACCIDENT_BY_SGG" : function(e){
            const sourceName = GITS_ENV.LAYER_PREFIX+"BD_ACCIDENT_BY_SGG_source";
            const sggAccidentLayerName = GITS_ENV.LAYER_PREFIX+"BD_ACCIDENT_BY_SGG";
            core.control.removeCustomSource(sourceName);
            /*
            acdntCnt : 사고수
            casltCnt : 사상자수
            dcsdCnt : 사망자수
            swpsnCnt : 중상자수
            sinjpsnCnt : 경상자수
            injDclrCnt : 부상신고수
            adstdgCd : 법정코드
             */
            let sggAccidentLayer = {
                id: sggAccidentLayerName,
                type: 'circle',
                source: sourceName,
                'maxzoom': 13,
                'minzoom': 8,
                paint: {
                    'circle-radius': 1,
                    'circle-opacity' : 0,
                    'circle-stroke-color' : "#000000",
                    'circle-stroke-width' : 2
                }
            }
            const newMarkers = {};
            for (const feature of e.data.sggCollection.featureCollection.features) {
                const coords = feature.geometry.coordinates;
                const props = feature.properties;
                const id = props.sggNm;
                let marker = openMarkers[id];
                if (!marker) {
                    let el = null;
                    if(props.casltCnt && props.casltCnt !== 0) {
                        el = _Util.createDonutChart(props, id, [
                            props.dcsdCnt,
                            props.injDclrCnt,
                            props.casltCnt,
                            props.sinjpsnCnt,
                            props.swpsnCnt
                        ], ['#fed976', '#feb24c', '#fd8d3c', '#fc4e2a', '#e31a1c']);
                    }else{
                        el = _Util.createDonutChart(props, id, [1], ['#999999']);
                    }
                    marker = openMarkers[id] = new mapboxgl.Marker({
                        element: el
                    }).setPopup(
                        new mapboxgl.Popup({offset: [-14, -55]})
                            .setHTML(`
                                <div class="data_popup" style="width:14rem;">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p>${props.sggNm}</p>
                                    <ul>
                                        <li class="popup_item">사상자수 : <span>${props.casltCnt ? props.casltCnt : 0}</span></li>
                                        <li class="popup_item">사망자수 : <span>${props.dcsdCnt ? props.dcsdCnt : 0}</span></li>
                                        <li class="popup_item">중상자수 : <span>${props.swpsnCnt ? props.swpsnCnt : 0}</span></li>
                                        <li class="popup_item">경상자수 : <span>${props.sinjpsnCnt ? props.sinjpsnCnt : 0}</span></li>
                                        <li class="popup_item">부상자신고수 : <span>${props.injDclrCnt ? props.injDclrCnt : 0}</span></li>
                                    </ul>
                                </div>
                            `)
                    ).setLngLat(coords);
                }
                newMarkers[id] = marker;

                if (!markersOnScreen[id]) marker.addTo(_Map);
            }
            for (const id in markersOnScreen) {
                if (!newMarkers[id]) markersOnScreen[id].remove();
            }
            markersOnScreen = newMarkers;
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.sggCollection.featureCollection, [sggAccidentLayer]);
			
        },
        "BD_PATTERN_TRAFFIC_QUANTITY" : function(e) {
            const searchOption =  Object.fromEntries(new URLSearchParams(e.data.searchOption));
            const sourceName = LAYER.BD_PATTERN;
            const layerName = LAYER.BD_PATTERN;
            core.control.removeCustomSource(sourceName);
            let lineColor = [
                'match',
                ['get',"conggrade"],
                "3",
                LEGEND_COLOR.TRAFFIC_6LEVEL[6],
                "2",
                LEGEND_COLOR.TRAFFIC_6LEVEL[4],
                "1",
                LEGEND_COLOR.TRAFFIC_6LEVEL[3],
                LEGEND_COLOR.TRAFFIC_6LEVEL[3]
            ];
            switch(searchOption.type) {
                case "quantity" :
                    if(lineColor.searchResultType === "totalSum"){
                        lineColor = [
                            'case',
                            ['all', ['>=', ['get','vhclTrfvlmTotal'], 0], ['<', ['get','vhclTrfvlmTotal'], 141]],
                            LEGEND_COLOR.TRAFFIC_AMT[0],
                            ['all', ['>=', ['get','vhclTrfvlmTotal'], 141], ['<', ['get','vhclTrfvlmTotal'], 171]],
                            LEGEND_COLOR.TRAFFIC_AMT[1],
                            ['all', ['>=', ['get','vhclTrfvlmTotal'], 171], ['<', ['get','vhclTrfvlmTotal'], 1101]],
                            LEGEND_COLOR.TRAFFIC_AMT[2],
                            ['all', ['>=', ['get','vhclTrfvlmTotal'], 1101], ['<', ['get','vhclTrfvlmTotal'], 1131]],
                            LEGEND_COLOR.TRAFFIC_AMT[3],
                            ['>=', ['get','vhclTrfvlmTotal'], 1131],
                            LEGEND_COLOR.TRAFFIC_AMT[4],
                            LEGEND_COLOR.TRAFFIC_AMT[0]
                        ]
                    }else{
                        lineColor = [
                            'case',
                            ['all', ['>=', ['get','vhclTrfvlmAvg'], 0], ['<', ['get','vhclTrfvlmAvg'], 141]],
                            LEGEND_COLOR.TRAFFIC_AMT[0],
                            ['all', ['>=', ['get','vhclTrfvlmAvg'], 141], ['<', ['get','vhclTrfvlmAvg'], 171]],
                            LEGEND_COLOR.TRAFFIC_AMT[1],
                            ['all', ['>=', ['get','vhclTrfvlmAvg'], 171], ['<', ['get','vhclTrfvlmAvg'], 1101]],
                            LEGEND_COLOR.TRAFFIC_AMT[2],
                            ['all', ['>=', ['get','vhclTrfvlmAvg'], 1101], ['<', ['get','vhclTrfvlmAvg'], 1131]],
                            LEGEND_COLOR.TRAFFIC_AMT[3],
                            ['>=', ['get','vhclTrfvlmAvg'], 1131],
                            LEGEND_COLOR.TRAFFIC_AMT[4],
                            LEGEND_COLOR.TRAFFIC_AMT[0]
                        ]
                    }
                    break;
            }
            let lineLayer = {
                'id': layerName,
                'type': 'line',
                'source': sourceName,
                'layout': {
                    'line-join': 'round',
                    'line-cap': 'round'
                },
                'paint': {
                    'line-color': lineColor,
                    'line-width': 3,
                    'line-opacity': 1,
                },
                filter : ['has', 'linkId']
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [lineLayer])
            core.control.hideLayer(LAYER.NODE);

            _Map.off('click', layerName, MapEvents.BD_PATTERN_TRAFFIC_QUANTITY);
            _Map.on('click', layerName, MapEvents.BD_PATTERN_TRAFFIC_QUANTITY);
        },
        "BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS_MERGE" : function(e) {
            if (e.data.error) {
				new ModalBuilder().init().alertBoby(e.data.errorMsg).footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
                return;
            }
            const searchOption =  Object.fromEntries(new URLSearchParams(e.data.searchOption));
            savedDataForDualMap = e.data;
            const sourceName = LAYER.BD_EFFECT;
            const layerName = LAYER.BD_EFFECT;
            core.control.removeCustomSource(sourceName);
            let lineColor =  [
                'case',
                ['<', ['get','avgVhclSpeedAvg'], -10],
                LEGEND_COLOR.TRF_AMT_MERGE[0],
                ['all', ['>=', ['get','avgVhclSpeedAvg'], -10], ['<', ['get','avgVhclSpeedAvg'], 0]],
                LEGEND_COLOR.TRF_AMT_MERGE[1],
                ['all', ['>=', ['get','avgVhclSpeedAvg'], 0], ['<', ['get','avgVhclSpeedAvg'], 10]],
                LEGEND_COLOR.TRF_AMT_MERGE[2],
                ['all', ['>=', ['get','avgVhclSpeedAvg'], 10], ['<', ['get','avgVhclSpeedAvg'], 20]],
                LEGEND_COLOR.TRF_AMT_MERGE[3],
                ['>=', ['get','avgVhclSpeedAvg'], 20],
                LEGEND_COLOR.TRF_AMT_MERGE[4],
                LEGEND_COLOR.TRF_AMT_MERGE[0]
            ];;
            if(searchOption.type === "vhclTrfvlm") {
                lineColor = [
                    'case',
                    ['<', ['get','vhclTrfvlmTotal'], -5000],
                    LEGEND_COLOR.TRF_AMT_MERGE[0],
                    ['all', ['>=', ['get','vhclTrfvlmTotal'], -5000], ['<', ['get','vhclTrfvlmTotal'], 0]],
                    LEGEND_COLOR.TRF_AMT_MERGE[1],
                    ['all', ['>=', ['get','vhclTrfvlmTotal'], 0], ['<', ['get','vhclTrfvlmTotal'], 8000]],
                    LEGEND_COLOR.TRF_AMT_MERGE[2],
                    ['all', ['>=', ['get','vhclTrfvlmTotal'], 8000], ['<', ['get','vhclTrfvlmTotal'], 14000]],
                    LEGEND_COLOR.TRF_AMT_MERGE[3],
                    ['>=', ['get','vhclTrfvlmTotal'], 14000],
                    LEGEND_COLOR.TRF_AMT_MERGE[4],
                    LEGEND_COLOR.TRF_AMT_MERGE[0]
                ];
            }
            console.log("lineColor", lineColor);
            let lineLayer = {
                'id': layerName,
                'type': 'line',
                'source': sourceName,
                'layout': {
                    'line-join': 'round',
                    'line-cap': 'round'
                },
                'paint': {
                    'line-color': lineColor,
                    'line-width': 3,
                    'line-opacity': 1,
                },
                filter: ['has', 'linkId']
            }
            core.control.addExpertSourceAndLayer(sourceName, {}, e.data.featureCollection, [lineLayer])
            core.control.hideLayer(LAYER.NODE);
            _Map.off('click', layerName, MapEvents.BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS);
            _Map.off('click', layerName, MapEvents.BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS_MERGE);
            _Map.on('click', layerName, MapEvents.BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS_MERGE);

        },
        "BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS" : function(e) {
            if(e.data.error) {
				new ModalBuilder().init().alertBoby(e.data.errorMsg).footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
                return;
            }
            const searchOption =  Object.fromEntries(new URLSearchParams(e.data.searchOption));
            savedDataForDualMap = e.data;
            const sourceName = LAYER.BD_EFFECT;
            const layerName = LAYER.BD_EFFECT;
            core.control.removeCustomSource(sourceName);
            let lineColor = [
                'match',
                ['get',"conggrade"],
                "3",
                LEGEND_COLOR.TRAFFIC_6LEVEL[6],
                "2",
                LEGEND_COLOR.TRAFFIC_6LEVEL[4],
                "1",
                LEGEND_COLOR.TRAFFIC_6LEVEL[3],
                LEGEND_COLOR.TRAFFIC_6LEVEL[0]
            ];
            if(searchOption.type === "vhclTrfvlm") {
                lineColor = [
                    'case',
                    ['all', ['>=', ['get','vhclTrfvlmTotal'], 0], ['<', ['get','vhclTrfvlmTotal'], 2000]],
                    LEGEND_COLOR.TRAFFIC_AMT[0],
                    ['all', ['>=', ['get','vhclTrfvlmTotal'], 2000], ['<', ['get','vhclTrfvlmTotal'], 5000]],
                    LEGEND_COLOR.TRAFFIC_AMT[1],
                    ['all', ['>=', ['get','vhclTrfvlmTotal'], 5000], ['<', ['get','vhclTrfvlmTotal'], 8000]],
                    LEGEND_COLOR.TRAFFIC_AMT[2],
                    ['all', ['>=', ['get','vhclTrfvlmTotal'], 8000], ['<', ['get','vhclTrfvlmTotal'], 14000]],
                    LEGEND_COLOR.TRAFFIC_AMT[3],
                    ['>=', ['get','vhclTrfvlmTotal'], 14000],
                    LEGEND_COLOR.TRAFFIC_AMT[4],
                    LEGEND_COLOR.TRAFFIC_AMT[0]
                ];
            }
            let lineLayer = {
                'id': layerName,
                'type': 'line',
                'source': sourceName,
                'layout': {
                    'line-join': 'round',
                    'line-cap': 'round'
                },
                'paint': {
                    'line-color': lineColor,
                    'line-width': 3,
                    'line-opacity': 1,
                },
                filter : ['has', 'linkId']
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [lineLayer])
            core.control.hideLayer(LAYER.NODE);
            _Map.off('click', layerName, MapEvents.BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS);
            _Map.on('click', layerName, MapEvents.BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS);
            
        },
        "BD_DANGER_ZONE" : function(e) {
            const sourceName = GITS_ENV.LAYER_PREFIX+"BD_DANGER_ZONE_source";
            const clusterLayerName = GITS_ENV.LAYER_PREFIX+"BD_DANGER_ZONE_cluster";
            core.control.removeCustomSource(sourceName);
            /*
            acdntCnt : 사고수
            casltCnt : 사상자수
            dcsdCnt : 사망자수
            swpsnCnt : 중상자수
            sinjpsnCnt : 경상자수
            injDclrCnt : 부상신고수
            adstdgCd : 법정코드
             */
            let clusterLayer = {
                id: clusterLayerName,
                type: 'circle',
                source: sourceName,
                paint: {
                    'circle-color': [
                        'step',
                        ['get', 'acdntCnt_total'],
                        '#51bbd6',
                        0,
                        '#f1f075',
                        50,
                        '#f28cb1'
                    ],
                    'circle-radius': ["max", ['get', 'acdntCnt_total'], 30],
                    /*'circle-radius': 30,*/
                    'circle-opacity' : 0.8,
                    'circle-stroke-color' : "#000000",
                    'circle-stroke-width' : 2
                    /*[
                        'step',
                        ['get', 'point_count'],
                        20,
                        100,
                        30,
                        750,
                        40
                    ]*/
                },
                filter : ['has', 'point_count']
            }
            let clusterTextLayer = {
                id: clusterLayerName+"_text",
                type: 'symbol',
                source: sourceName,
                layout: {
                    'text-field': ['get', 'acdntCnt_total'],
                    'text-size': 12
                },
                filter : ['has', 'point_count']
            }
            let unClusterLayer = {
                id: clusterLayerName+"_none",
                type: 'circle',
                source: sourceName,
                paint: {
                    'circle-color': [
                        'step',
                        ['get', 'acdntCnt'],
                        '#51bbd6',
                        0,
                        '#f1f075',
                        50,
                        '#f28cb1'
                    ],
                    /*'circle-radius': ['get', 'acdntCnt'],*/
                    'circle-radius': ["max", ['get', 'acdntCnt'], 30],
                    /*'circle-radius': 30,*/
                    'circle-opacity' : 0.8,
                    'circle-stroke-color' : "#000000",
                    'circle-stroke-width' : 2
                    /*[
                        'step',
                        ['get', 'point_count'],
                        20,
                        100,
                        30,
                        750,
                        40
                    ]*/
                },
                'filter': ['!=', 'cluster', true]
            }
            let unclusterTextLayer = {
                id: clusterLayerName+"_none_text",
                type: 'symbol',
                source: sourceName,
                layout: {
                    'text-field': ['get', 'acdntCnt'],
                    'text-size': 12
                },
                'filter': ['!=', 'cluster', true]
            }
            const clusterOption = {
                cluster : true,
                clusterMaxZoom: 22, // Max zoom to cluster points on
                clusterRadius: 55,
                clusterProperties: {
                    'acdntCnt_total': ["+", ["get", "acdntCnt"]],
                },
                tolerance : 0.5
            }
            core.control.addExpertSourceAndLayer(sourceName,clusterOption, e.data.featureCollection, [clusterLayer, clusterTextLayer, unClusterLayer, unclusterTextLayer]);

            _Map.on('click', unClusterLayer.id, MapEvents.BD_DANGER_ZONE_UNCLUSTER);
            _Map.on('click', clusterLayer.id, MapEvents.BD_DANGER_ZONE_CLUSTER);
        },
        "BD_PREDICTION_CROSS_TRAFFIC" : function(e){
            const so = _Util.convertParamToObject(e.data.searchOption);
            const layerName = LAYER.CROSSROAD_TRF_QUANTITY;
            core.control.removeCustomSource(layerName);
            if (e.data.error) {
				new ModalBuilder().init().alertBoby("조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
                return;
            }
            let layer = {
                'id': layerName,
                'type': 'heatmap',
                'source': layerName,
                'paint': {
                    'heatmap-weight': [
                        'interpolate',
                        ['linear'],
                        ["get","trfvlmTotal_"+so.startDate],
                        0, 0,
                        300, 1,
                    ],
                    // 줌 level 강도
                    'heatmap-intensity': {
                        stops: [
                            [11, 1],
                            [15, 6]
                        ]
                    },
                    // 밀도에 따라 색상값 할당
                    'heatmap-color': [
                        'interpolate',
                        ['linear'],
                        ['heatmap-density'],
                        0, 'transparent',
                        0.2, LEGEND_COLOR.WEIGHT[0],
                        0.4, LEGEND_COLOR.WEIGHT[1],
                        0.6, LEGEND_COLOR.WEIGHT[2],
                        0.7, LEGEND_COLOR.WEIGHT[3],
                        0.9, LEGEND_COLOR.WEIGHT[5],
                        1, LEGEND_COLOR.WEIGHT[6],
                    ],
                    // 줌에 맞게 크기 변경
                    'heatmap-radius': {
                        stops: [
                            [6, 1],
                            [12, 10],
                            [16, 50]
                        ]
                    },
                    // 줌에 맞게 투명도 조절
                    'heatmap-opacity': {
                        default: 1,
                        stops: [
                            [14, 0.9],
                            [20, 0.9]
                        ]
                    },
                }
            }
            core.control.addExpertSourceAndLayer(layerName,{}, e.data.collection.featureCollection, [layer]);

            function changeColorByLegnedTime(legend){
                _Map.setPaintProperty(layerName, 'heatmap-weight', [
                    'interpolate',
                    ['linear'],
                    ["get",legend],
                    0, 0,
                    300, 1,
                ]);
            }

            if(e.data.matrixChartData) {
                const title = "날짜별 교차로 교통량 예측 분석";
                e.data.matrixChartData.data.datasets[0].backgroundColor = function(c) {
                    const value = c.dataset.data[c.dataIndex].v;
                    if (value >= 0 && value < 1000) {
                        return LEGEND_COLOR.WEIGHT[0]
                    } else if (value >= 1000 && value <= 2000) {
                        return LEGEND_COLOR.WEIGHT[1]
                    } else if (value >= 5000 && value <= 10000) {
                        return LEGEND_COLOR.WEIGHT[2]
                    } else if (value >= 10000 && value <= 20000) {
                        return LEGEND_COLOR.WEIGHT[3]
                    } else {
                        return LEGEND_COLOR.WEIGHT[4]
                    }
                }
                e.data.matrixChartData.data.datasets[0].width = function(c) {
                    const a = c.chart.chartArea || {};
                    return (a.right - a.left) / 7 - 1;
                }
                e.data.matrixChartData.data.datasets[0].height = function(c) {
                    const a = c.chart.chartArea || {};
                    return (a.bottom - a.top) / e.data.sggNmGroupList.length - 1;
                }
                e.data.matrixChartData.options.scales.x.ticks.color =  ["#fff"]
                e.data.matrixChartData.options.scales.y.ticks.color =  ["#fff"]
				/*e.data.matrixChartData.options.scales.y.ticks.padding =  [1]*/
                e.data.matrixChartData.options.plugins.tooltip.callbacks.label = function(context) {
                    const v = context.dataset.data[context.dataIndex].v;
                    return ['에측 데이터(총합) : ' + v];
                }
                e.data.matrixChartData.plugins = [
                    {
                        id: 'click-anywhere',
                        afterEvent(chart, args) {
                            if (args.event.type === 'click') {
                                let {x, y} = chart.scales;
                                let xVal = x.getLabelForValue(x.getValueForPixel(args.event.x))
                                changeColorByLegnedTime("trfvlmTotal_"+xVal.split(":")[0]);
                                $(".chart_video_container .tab_box_title").text(title+" - "+xVal+" 데이터");
                            }
                        }
                    }
                ]
				
                let playerInterval = null;
                let progressInterval = null;
                let dateGroupList = e.data.dateGroupList;
                let idx = 0;
                core.control.generatePlayerModel(title,"<canvas id='smcrd_pred_matrix_chart_canvas'></canvas>", function(){
                    if(!playerInterval) clearInterval(playerInterval);
                    if(!progressInterval) clearInterval(progressInterval);
                    playerInterval = setInterval(function(){
                        const value = dateGroupList[idx];
                        const legend = "trfvlmTotal_"+value;
                        changeColorByLegnedTime(legend);
                        $(".chart_video_container .tab_box_title").text(title+" - "+value+" 데이터");
                        if(dateGroupList.indexOf(value) >= (dateGroupList.length - 1)) {
                            clearInterval(playerInterval);
                            clearInterval(progressInterval);
                            $(".chart_video_container .tab_box_title").text(title);
                            return;
                        }
                        idx++;
                    }, 1000);
                }, function(){
                    const legend = "trfvlmTotal_"+dateGroupList[0];
                    changeColorByLegnedTime(legend);
                    $(".chart_video_container .tab_box_title").text(title);
                    clearInterval(playerInterval);
                    clearInterval(progressInterval);
                });
                const chart = new Chart('smcrd_pred_matrix_chart_canvas', e.data.matrixChartData);
            }

            _Map.on("click", layerName, function(e){
                const prop = e.features[0].properties
                new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                    .setLngLat(e.features[0].geometry.coordinates)
                    .setHTML(`
                        <div class="data_popup" style="width:15.7rem;">
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">
								<li class="popup_item">교차로명 : <span>${prop.crsrdNm}</span></li>
								<li class="popup_item">직진교통량 : <span>${prop.strghtTrfvlm}</span></li>
								<li class="popup_item">좌회전교통량 : <span>${prop.trnlftTrfvlm}</span></li>
								<li class="popup_item">우회전교통량 : <span>${prop.trnghtTrfvlm}</span></li>
								<li class="popup_item">대기열길이 : <span>${prop.wtLnLen}</span></li>
                            </ul>
                        </div>
                    `)
                    .addTo(_Map);
            });

        },
        "BD_PUBLIC_TRANSFER_CNDCY_ROUTE" : function(e) {
            const searchOption =  Object.fromEntries(new URLSearchParams(e.data.searchOption));
            const sourceName = LAYER.BD_PUBLIC_TRANSFER_CNDCY;
            const linkLayerName = LAYER.BD_PUBLIC_TRANSFER_CNDCY;
            const stationLayerName = LAYER.BD_PUBLIC_TRANSFER_CNDCY_STATION;
            core.control.removeCustomSource(sourceName);
            /*const color = _Util.getRandomHexColor();*/
            const color = "#ee05cb";
            if(e.data.bbox) {
                try {
                    _Map.fitBounds(e.data.bbox, {padding: 100});
                }catch(e){console.error('포커싱 에러')}
            }
            let linkLayer = {
                'id': linkLayerName,
                'type': 'line',
                'source': sourceName,
                'maxzoom': 22,
                'minzoom': 9,
                'layout': {
                    'line-join': 'round',
                    'line-cap': 'round'
                },
                'paint': {
                    'line-color': color,
                    'line-width': 2,
                    'line-opacity': 0.8,
                    'line-dasharray' : [2,2]
                },
                "filter" : ['has', "route"]
            }
            let stationLayer = {
                'id': stationLayerName,
                'type': 'circle',
                'source': sourceName,
                'paint': {
                    'circle-radius': {
                        'base': 1.75,
                        'stops': [
                            [12, 2],
                            [22, 180]
                        ]
                    },
                    'circle-color' : color
                },
                "filter" : ['has', "station"]
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [linkLayer, stationLayer]);
        },
        "BD_PUBLIC_TRANSFER_USAGE_BY_SGG" : function(e) {
            const newMarkers = {};
            for (const feature of e.data.featureCollection.features) {
                const coords = feature.geometry.coordinates;
                const props = feature.properties;
                const id = props.sggNm;
                let marker = openMarkers[id];
                if (!marker) {
                    let el = null;
                    if (props.total && props.total !== 0) {
                        el = _Util.createDonutChart(props, id, [
                            props.lndiUserCnt,
                            props.rideUserCnt,
                            props.trnsitUserCnt
                        ], [LEGEND_COLOR.TRANSFER_USAGE_BY_SGG[1], LEGEND_COLOR.TRANSFER_USAGE_BY_SGG[2], LEGEND_COLOR.TRANSFER_USAGE_BY_SGG[0]]);
                    } else {
                        el = _Util.createDonutChart(props, id, [1], ['#999999']);
                    }
                    marker = openMarkers[id] = new mapboxgl.Marker({
                        element: el
                    }).setPopup(
                        new mapboxgl.Popup({offset: [-15, -55]}) // add popups
                            .setHTML(`
                                <div class="data_popup" style="width:14rem;">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <div class="ftsize16 mt8 mb8">${props.sggNm}</div>
                                    <ul>
                                        <li class="popup_item">전체 : <span>${props.total !== null ? props.total : "제공 데이터 없음"}</span></li>
                                        <li class="popup_item">승차수 : <span>${props.rideUserCnt != null ? props.rideUserCnt : "제공 데이터 없음"}</span></li>
                                        <li class="popup_item">하차수 : <span>${props.lndiUserCnt != null ? props.lndiUserCnt : "제공 데이터 없음"}</span></li>
                                        <li class="popup_item">환승수 : <span>${props.trnsitUserCnt != null ?props.trnsitUserCnt : "제공 데이터 없음"}</span></li>
                                    </ul>
                                </div>
                            `)
                    ).setLngLat(coords);
                }
                newMarkers[id] = marker;

                if (!markersOnScreen[id]) marker.addTo(_Map);
            }
            for (const id in markersOnScreen) {
                if (!newMarkers[id]) markersOnScreen[id].remove();
            }
            markersOnScreen = newMarkers;
        },
        /*"BD_PUBLIC_TRANSFER_USAGE_BY_ST_END" : function(e) {
            const sourceName = LAYER.BD_PUBLIC_TRANSFER_USAGE_BY_ST_END;
            const layerName = LAYER.BD_PUBLIC_TRANSFER_USAGE_BY_ST_END;
            core.control.removeCustomSource(sourceName);
            if(e.data.bbox) {
                try {
                    _Map.fitBounds(e.data.bbox, {padding: 100});
                }catch(e){console.error('포커싱 에러')}
            }
            let lineColor = [
                'case',
                ['all', ['>=', ['get','busUserCnt'], 0], ['<', ['get','busUserCnt'], 10]],
                LEGEND_COLOR.TRAFFIC_AMT[0],
                ['all', ['>=', ['get','busUserCnt'], 10], ['<', ['get','busUserCnt'], 20]],
                LEGEND_COLOR.TRAFFIC_AMT[1],
                ['all', ['>=', ['get','busUserCnt'], 20], ['<', ['get','busUserCnt'], 30]],
                LEGEND_COLOR.TRAFFIC_AMT[2],
                ['all', ['>=', ['get','busUserCnt'], 30], ['<', ['get','busUserCnt'], 40]],
                LEGEND_COLOR.TRAFFIC_AMT[3],
                ['>=', ['get','busUserCnt'], 50],
                LEGEND_COLOR.TRAFFIC_AMT[4],
                LEGEND_COLOR.TRAFFIC_AMT[0]
            ];
            let lineLayer = {
                'id': layerName,
                'type': 'line',
                'source': sourceName,
                'layout': {
                    'line-join': 'round',
                    'line-cap': 'round'
                },
                'paint': {
                    'line-color': lineColor,
                    'line-width': 4,
                    'line-opacity': 1,
                }
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [lineLayer])
            _Map.off('click', layerName, MapEvents.CM_BUS_ROUTE);
            _Map.off('click', layerName, MapEvents.BD_PUBLIC_TRANSFER_USAGE_BY_ST_END);
            _Map.on('click', layerName, MapEvents.BD_PUBLIC_TRANSFER_USAGE_BY_ST_END);
        },*/
        "BD_BUS_USAGE_BY_STATION": function(e) {
            workerResultEvent.BD_BUS_STATION(e);
            _Map.off('click', LAYER.BD_BUS_STATION, MapEvents.BD_BUS_STATION);
            _Map.off('click', LAYER.BD_BUS_STATION, MapEvents.BD_BUS_USAGE_BY_STATION);
            _Map.on('click', LAYER.BD_BUS_STATION, MapEvents.BD_BUS_USAGE_BY_STATION);
        },
        "BD_BUS_STATION" : function(e) {
            const sourceName = LAYER.BD_BUS_STATION;
            const layerName = LAYER.BD_BUS_STATION;

            core.control.removeCustomSource(sourceName);
            let busStationLayer = {
                'id': layerName,
                'type': 'symbol',
                'source': sourceName,
                'maxzoom': 22,
                'minzoom': 9,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': ['get', 'icon'],
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        10, 0.05,
                        15, 0.5
                    ]
                }
            }
            core.control.addExpertSourceAndLayer(sourceName, {}, e.data.featureCollection, [busStationLayer]);
            _Map.off('click', layerName, MapEvents.BD_BUS_STATION);
            _Map.off('click', layerName, MapEvents.BD_BUS_USAGE_BY_STATION);
            _Map.on('click', layerName, MapEvents.BD_BUS_STATION);
        },
        "BD_BUS_ROUTE_CURVE_ANL" : function(e) {
            workerResultEvent['CM_BUS_ROUTE'](e, 5);
            for(const ev in MapEvents) {
                if(ev.indexOf("BD_BUS_ROUTE") === 0) {
                    _Map.off("click", LAYER.BUS_ROUTE, MapEvents[ev]);
                }
            }
            if(e.data.bbox) {
                try {
                    _Map.fitBounds(e.data.bbox, {padding: 100});
                }catch(e){console.error('포커싱 에러')}
            }
            _Map.on("click", LAYER.BUS_ROUTE, MapEvents.BD_BUS_ROUTE_CURVE_ANL);
        },
        "BD_BUS_ROUTE_USE_CALC" : function(e) {
            workerResultEvent['CM_BUS_ROUTE'](e, 5);
            for(const ev in MapEvents) {
                if(ev.indexOf("BD_BUS_ROUTE") === 0) {
                    _Map.off("click", LAYER.BUS_ROUTE, MapEvents[ev]);
                }
            }
            if(e.data.bbox) {
                try {
                    _Map.fitBounds(e.data.bbox, {padding: 100});
                }catch(e){console.error('포커싱 에러')}
            }

        },
        "BD_BUS_ROUTE_PASSENGER_ANL" : function(e) {
            workerResultEvent['CM_BUS_ROUTE'](e, 5);
            for(const ev in MapEvents) {
                if(ev.indexOf("BD_BUS_ROUTE") === 0) {
                    _Map.off("click", LAYER.BUS_ROUTE, MapEvents[ev]);
                }
            }
            if(e.data.bbox) {
                try {
                    _Map.fitBounds(e.data.bbox, {padding: 100});
                }catch(e){console.error('포커싱 에러')}
            }
        },
        "BD_BUS_BIT_ROUTE" : function(e) {
            workerResultEvent['CM_BUS_ROUTE'](e, 5);
            if(e.data.bbox) {
                try {
                    _Map.fitBounds(e.data.bbox, {padding: 100});
                }catch(e){console.error('포커싱 에러')}
            }
        },
        "BD_BUS_ROUTE_DUPLICATE_ROUTE" : function(e) {
            const sourceName = LAYER.BUS_ROUTE;
            const layerName = LAYER.BUS_ROUTE;
            if(e.data.bbox) {
                try {
                _Map.fitBounds(e.data.bbox, {padding: 100});
                }catch(e){console.error('포커싱 에러')}
            }
            core.control.removeCustomSource(sourceName);
            let layer = {
                'id': layerName,
                'type': 'line',
                'source': sourceName,
                'maxzoom': 22,
                'minzoom': 9,
                'layout': {
                    'line-join': 'round',
                    'line-cap': 'round'
                },
                'paint': {
                    'line-color': [
                        'case',
                        ['all', ['>=', ['get','duplLinkCnt'], 0], ['<', ['get','duplLinkCnt'], 3]],
                        LEGEND_COLOR.TRAFFIC_AMT[0],
                        ['all', ['>=', ['get','duplLinkCnt'], 3], ['<', ['get','duplLinkCnt'], 7]],
                        LEGEND_COLOR.TRAFFIC_AMT[1],
                        ['all', ['>=', ['get','duplLinkCnt'], 7], ['<', ['get','duplLinkCnt'], 10]],
                        LEGEND_COLOR.TRAFFIC_AMT[2],
                        ['all', ['>=', ['get','duplLinkCnt'], 10], ['<', ['get','duplLinkCnt'], 15]],
                        LEGEND_COLOR.TRAFFIC_AMT[3],
                        ['>=', ['get','duplLinkCnt'], 20],
                        LEGEND_COLOR.TRAFFIC_AMT[4],
                        LEGEND_COLOR.TRAFFIC_AMT[0]
                    ],
                    'line-width': 5,
                    'line-opacity': 0.8,
                },
            }
            core.control.addExpertSourceAndLayer(layerName,{}, e.data.routeFeatureCollection.featureCollection, [layer], LAYER.LINK);
            for(const ev in MapEvents) {
                if(ev.indexOf("BD_BUS_ROUTE") === 0) {
                    _Map.off("click", LAYER.BUS_ROUTE, MapEvents.BD_BUS_ROUTE_PASSENGER_ANL);
                }
            }
            _Map.on("click", LAYER.BUS_ROUTE, MapEvents.BD_BUS_ROUTE_DUPLICATE_ROUTE);

            const stationLayerName = LAYER.BUS_STATION;
            core.control.removeCustomSource(stationLayerName);
            let busStationLayer = {
                'id': stationLayerName,
                'type': 'symbol',
                'source': stationLayerName,
                'maxzoom': 22,
                'minzoom': 9,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': ['get', 'icon'],
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        10, 0.05,
                        15, 0.5
                    ]
                }
            }
            core.control.addExpertSourceAndLayer(stationLayerName,{}, e.data.stationFeatureCollection.featureCollection, [busStationLayer]);
        },
        "BD_BUS_BIT_STATION" : function(e) {
            const sourceName = LAYER.BD_BUS_STATION;
            const layerName = LAYER.BD_BUS_STATION;

            core.control.removeCustomSource(sourceName);
            let busStationLayer = {
                'id': layerName,
                'type': 'symbol',
                'source': sourceName,
                'maxzoom': 22,
                'minzoom': 9,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': ['get', 'icon'],
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        10, 0.05,
                        15, 0.5
                    ]
                }
            }
            core.control.addExpertSourceAndLayer(sourceName, {}, e.data.featureCollection, [busStationLayer]);


            _Map.off("click", layerName, MapEvents.BD_BUS_BIT_STATION);
            _Map.on("click", layerName, MapEvents.BD_BUS_BIT_STATION);

            return;
            const chunkSize = 9;
            const width = 100/chunkSize;
            /*e.data.featureCollection.features*/
            let stations = [];
            for(let i =0; i < 32;i++){
                stations.push({
                    properties : {
                        'stationNm' : '정류장'+i,
                        "staOrder" : i
                    }
                })
            }
            let row = 1;
            let stationBITDataContainer = `<div id="bit-station-container"></div>`;
            let stationBITClickResultWrapper = `<div id="bit-station-click-result-wrapper">
                <table>
                <tr>
                <th colspan="2" id="bit-station-name"></th>
                </tr>
                <tr>
                <th>회차</th>
                <th>예상도착시간</th>
                <th>실제도착시간</th>
                </tr>
                </table>
            </div>`;
            let stationBITWrapper = `<div id="bit-station-wrapper"></div>`;
            let stationBITRow = `<div class="bit-station-row"></div>`;
            let stationBITItem = `<div class="bit-station"></div>`;
            let stationBITItemInner = `<div class="bit-station-inner"></div>`;

            let $stationBITWrapper = $(stationBITWrapper);
            const stationChunkArray = stations.reduce((resultArray, item, index) => {
                const chunkIndex = Math.floor(index/chunkSize)

                if(!resultArray[chunkIndex]) {
                    resultArray[chunkIndex] = [] // start a new chunk
                }
                resultArray[chunkIndex].push(item)
                return resultArray
            }, []);
            for(const stationRow in stationChunkArray) {
                let $stationBITRow = $(stationBITRow);
                $stationBITWrapper.append($stationBITRow);
                let itemIdx = 0;
                for(const stationItem of stationChunkArray[stationRow]) {
                    let $stationBITItem = $(stationBITItem);
                    let $stationBITItemInner = $(stationBITItemInner);
                    $stationBITItem.css("width",width+"%");
                    $stationBITItem.addClass(row%2 === 0 ? "direction-left" : "direction-right");
                    $stationBITItemInner.html(`
                    <img src="/statics/images/bus_icon1.png" />
                    <p>${stationItem.properties.stationNm}</p>
                    `);
                    ;
                    if(stationChunkArray.length-1 == stationRow && stationChunkArray[stationRow].length-1 == itemIdx) {
                        $stationBITItem.addClass("is-last");
                    }
                    $stationBITItem.append($stationBITItemInner);
                    $stationBITRow.append($stationBITItem);
                    itemIdx++;
                }
                row++;
            }
            $(".chart_video_body").html($stationBITWrapper);
            $(".chart_video_container").show();

        }
    }
    workerResultEvent = _RemoveJobItem(workerResultEvent);
}