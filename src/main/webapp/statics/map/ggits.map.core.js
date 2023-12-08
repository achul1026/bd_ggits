/**
 * Map core
 * */
let GITSMapCore = function(elementId){
    let core = this;
    let _Map = null;
    let _Map2 = null;
    let _Worker = null;
    let _Util = new GitsMapUtil();
    let openMarkers = [];
    let LAYER = GITS_ENV.LAYER;
    let COORDINATES = GITS_ENV.COORDINATES;
    let STYLES = GITS_ENV.STYLES;
    let LEGEND_COLOR = GITS_ENV.LEGEND_COLOR;
    let customSourceLayerIds = [];
    let defaultLayer = null;
    let savedDataForDualMap = null;
    let markersOnScreen = {};
    const SESSION_KEY = "gitsmap";
    const SESSION_PROP = GITS_ENV.SESSION_PROP;
    const LOG_LEVEL = "";
    const token = "pk.eyJ1IjoiZGVzaW1pbiIsImEiOiJjbGxsdGt2N2oxaXJ3M3BxdXI0eGg2NTZhIn0.XXk19wwTN3uAycRqs-G7FA";
    // const tileset_id = "desimin.clllyrgwf02rg2oprgc0p9wgf-5249e";
    // const tileset_layer = "ggits_node_link_dev"
    const link_tileset_id = "desimin.ggits_link";
    const link_tileset_layer = "ggits_linkss";
    const node_tileset_id = "desimin.ggits_node";
    const node_tileset_layer = "ggits_node";
    /*const grid_tileset_id = "desimin.275boefx";
    const grid_tileset_layer = "B100_1KM__202304_-_-10hnns";*/
    const grid_tileset_id = "desimin.ggits_grid_500";
    const grid_tileset_layer = "ggits_500_grid";
    const GITS_MAP_SESSION = {}
    const languageControl = new MapboxLanguage({defaultLanguage: 'ko'});
    const initZoom = 12;
    const opt = {
        container: elementId,
        style:  GITS_ENV.MAP_STYLES.NAVIGATION,
        /*style: 'mapbox://styles/mapbox/streets-v12',*/
        zoom: initZoom,
        minZoom: 11,
        maxZoom: 22,
        center: [127.1267772, 37.4200267],
        maxBounds : [
            [126.38,36.79],
            [127.95,38.38]
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

        if(sessionObj[SESSION_PROP.WARNING_CLOSED].indexOf(value) > -1){
            return true;
        }else{
            return false;
        }
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
                            alert("Not initialized GITS Map");
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
                    value = function() {
                        if(arguments[0] && arguments[0].data && arguments[0].data.jobId) {
                            let arg  = arguments[0];
                            setTimeout(function(){
                                $("#mapDataLoadingJobList [data-job-id='"+arg.data.jobId+"']").stop().slideUp(250, function(){
                                    $(this).remove();
                                });
                            }, 1000);
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

        new MapControl();
        new Monitoring();
        new Facility();
        new BigData();
        new MapAnimate();
        defaultLayer = new GitsLayer();

        // 저장된 세션정보로 스타일세팅
        opt.style = _getSession(SESSION_PROP.MAP_STYLE) ? _getSession(SESSION_PROP.MAP_STYLE) : GITS_ENV.MAP_STYLES.NAVIGATION;

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

            defaultLayer.drawSGGLayer();

            // 링크
            _Map.addSource(LAYER.LINK, {
                "type": "vector",
                "url": "mapbox://"+link_tileset_id,
                'minzoom': 9,
                'maxzoom': 22,
                "promoteId" : {"ggits_linkss":"LINK_ID"}
            });

            // 노드
            _Map.addSource(LAYER.NODE, {
                "type": "vector",
                "url": "mapbox://"+node_tileset_id,
                'minzoom': 9,
                'maxzoom': 22,
                "promoteId" : {"ggits_node":"NODE_ID"}
            });

            // 그리드
            _Map.addSource(LAYER.GRID, {
                "type": "vector",
                "url": "mapbox://"+grid_tileset_id,
                'minzoom': 9,
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

            // grid heatmap test
            _Map.addLayer({
                'id': LAYER.GRID+"_heatmap",
                'type': 'heatmap',
                'source': LAYER.GRID,
                'source-layer': grid_tileset_layer,
                'layout': {
                    "visibility" : "none"
                },
                'paint': {
                    'heatmap-weight': [
                        'interpolate',
                        ['linear'],
                        ['get', 'val'],
                        0,
                        0,
                        30000,
                        1.5
                    ],
                    // increase intensity as zoom level increases
                    'heatmap-intensity': {
                        stops: [
                            [11, 1],
                            [15, 1]
                        ]
                    },
                    // assign color values be applied to points depending on their density
                    'heatmap-color': [
                        'interpolate',
                        ['linear'],
                        ['heatmap-density'],
                        0, 'transparent',
                        0.2, LEGEND_COLOR.CONGESTION[0],
                        0.4, LEGEND_COLOR.CONGESTION[1],
                        0.6, LEGEND_COLOR.CONGESTION[2],
                        0.8, LEGEND_COLOR.CONGESTION[3],
                        1, LEGEND_COLOR.CONGESTION[4],
                    ],
                    // increase radius as zoom increases
                    'heatmap-radius': {
                        stops: [
                            [6, 1],
                            /*[16, 100]*/
                            [16, 120]
                        ]
                    },
                    // decrease opacity to transition into the circle layer
                    'heatmap-opacity': {
                        default: 1,
                        stops: [
                            [14, 1],
                            [15, 0]
                        ]
                    },
                }
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
            if(city) {
                nodeLayerObj.filter = ["all", ["has","NODE_ID"], ["==","layer", city+"_node"]];
            }
            _Map.addLayer(nodeLayerObj);

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
                    /*'line-color': [
                        'match',
                        ['get',"ROAD_RANK"],
                        "101",
                        "#d451ff",
                        STYLES.LINK_COLOR
                    ],*/
                    /*'line-color' : ["match",
                        ["string",["feature-state","conggrade"]]
                        ,
                        "0",
                        "#74aaff",
                        "1",
                        "#ffffff",
                        "2",
                        "#ffde00",
                        "3",
                        "#de0909",
                        "#ffffff"],*/
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
            if(city) {
                linkLayerObj.filter = ["all", ["has","LINK_ID"], ["==","layer", city+"_link"]];
            }

            /*_Map.addLayer({
                id: 'directions',
                type: 'symbol',
                source: LAYER.LINK,
                "source-layer" : link_tileset_layer,
                "minzoom" : 16,
                paint: {},
                layout: {
                    'symbol-placement': 'line',
                    'icon-image': 'arrow',
                    'icon-rotate': -90,
                    'icon-rotation-alignment': 'map',
                    'icon-allow-overlap': true,
                    'icon-ignore-placement': true
                }
            });*/

            _Map.addLayer(linkLayerObj);
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
        
        facility.getVds = function(){
            _Worker.postMessage({
                event : "F_VDS",
                jobId : core.control.generateJobId("VDS 정보 데이터 로딩")
            });
        }
        
        facility.getDSRC = function(){
            _Worker.postMessage({
                event : "F_DSRC",
                jobId : core.control.generateJobId("DSRC 정보 데이터 로딩")
            });
        }
        
        facility.getSignal = function(){
            _Worker.postMessage({
                event : "F_SIGNAL",
                jobId : core.control.generateJobId("신호 정보 데이터 로딩")
            });
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
         * 유동인구 밀집현황 조회
         * @param date 날짜?
         * @param time 0100 , 0200
         */
        bigdata.getPopulationCongestionInfo = function(date, stTime= "0000", endTime = "2300"){
            let data = {};
            data.event = "BD_POPULATION";
            data.loop = false;
            data.jobId = core.control.generateJobId("인구밀집 현황 로딩중");
            data.data = {
                date : date,
                stTime : stTime,
                endTime :endTime
            }
            _Worker.postMessage(data);
        }

        /**
         * 대중교통 위험운영 구간
         * @param yyyy
         */
        bigdata.getPublicTransferDangerSectionInfo = function(searchOption = ''){
            _Worker.postMessage({
                event : "BD_PT_DANGER_ANALYSIS",
                searchOption : searchOption,
                jobId : core.control.generateJobId("대중교통 위험운영 구간 데이터 로딩")
            });
        }

        /**
         * 교통예측분석 - 교차로 교통량 예측
         */
        bigdata.getCrossPrediction = function(searchOption = ''){
           /* _Worker.postMessage({
                event : "CM_CROSSROAD_CAMERA"
            });
            // todo :: 실데이터 작업 필요
            const features =  _Map.querySourceFeatures("GITS_NODE", {
                'sourceLayer': "ggits_node"
            });*/
            // 플레이어 테스트 데이터
            /*for(const feature of features) {
                let featureStateObj = {};
                for(let i=0; i <24; i++){
                    const time = i < 10 ? "0"+i : i;
                    featureStateObj[time+"00_val"] = Math.floor(Math.random()*100);
                }

                _Map.setFeatureState({
                    source: LAYER.NODE,
                    sourceLayer: "ggits_node",
                    id: feature.id,
                }, featureStateObj);
            }*/
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
         * 행정구역별 도로안전 데이터 로딩
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
         *
         */
        bigdata.getCongestionSection = function(){
            this.removeBigDataLayers();
            if(_Map.getLayer(LAYER.GRID_WEATHER))
                core.control.hideLayer(LAYER.GRID_WEATHER);

            core.control.showLayer(LAYER.GRID);
            _Map.setPaintProperty(LAYER.GRID, "fill-color", [
                'case',
                ['<', ['get', 'val'], 0],
                LEGEND_COLOR.CONGESTION[0],
                ['<', ['get', 'val'], 5000],
                LEGEND_COLOR.CONGESTION[1],
                ['<', ['get', 'val'], 10000],
                LEGEND_COLOR.CONGESTION[2],
                ['<', ['get', 'val'], 15000],
                LEGEND_COLOR.CONGESTION[3],
                ['>=', ['get', 'val'], 15000],
                LEGEND_COLOR.CONGESTION[4],
                "transparent"
            ]);
            core.control.showLayer(LAYER.GRID);
            // core.control.showLayer(LAYER.GRID+"_heatmap");

            _Map.on('click', LAYER.GRID, (e) => {
                const prop = e.features[0].properties;
                new mapboxgl.Popup()
                    .setLngLat(e.lngLat)
                    .setHTML(`
                        <div class="data_popup" style="width:15.7rem;">
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">
								<li class="popup_item">gid : <span>${prop.gid}</span></li>
								<li class="popup_item">혼잡강도 : <span>${prop.val ? prop.val : "N/A"}</span></li>
                            </ul>
                        </div>
                    `)
                    .addTo(_Map);
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
            gitsRoadData = await _Util.getXMLfromAPI("/statics/map/modules/monitoring/temp_data/roadlist.xml");
            monitoring.getTrafficInfo();
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
            stopWorkerInterval(LAYER.EMERGENCY);
            stopWorkerInterval("M_EMERGENCY");
        }

        /**
         * 버스정류장 정보 조회
         */
        monitoring.getBusStationInfo = function(clear = true){
            if(clear) {
                monitoring.removeAddedMonitoringLayer();
            }
            _Worker.postMessage({event : "CM_BUS_STATION", jobId : core.control.generateJobId("버스 정류장 레이어 로딩중")});
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
                loop : true,
                loopTime : 10000
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
            if(_Map.getSource(LAYER.BUS_ROUTE) && _Map.getLayer(LAYER.BUS_ROUTE)){

            }else {
                _Worker.postMessage({
                    event: "CM_BUS_ROUTE",
                    routeId: routeId,
                    jobId: core.control.generateJobId("버스 노선 정보 로딩중")
                });
            }
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
            gitsRoadData.loop = loop;
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
                <button type="button" id="follow-item" style="cursor:pointer">
                    따라가기 애니메이션 종료
                </button>`);
                $("body").append(jobItem);
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
        "CM_BUS_STATION" : function(e) {
            const prop = e.features[0].properties;
            new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.features[0].geometry.coordinates)
                .setHTML(`
                        <div class="data_popup">
                            <i class="bus_icon bus_pop"></i>
                            <ul class="data_sub mt16">
								${prop.description}
                            </ul>
                        </div>
                    `)
                .addTo(_Map);
        },
        "F_DSRC" : function (e){
            const prop = e.features[0].properties;
            let colctInfoList = JSON.parse(prop.colctInfo);
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
                                <div class="data_popup" >
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p class="mt8 mb8">${prop.rseNm}(${prop.rseId})</p>
                                    ${table}
                                </div>
                            `;
            let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.features[0].geometry.coordinates)
                .setHTML(html)
                .addTo(_Map);
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
                                <div class="data_popup" >
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p class="mb8 mt8">${prop.roadName}(${prop.linkId})</p>
                                    ${table}
                                </div>
                            `;
            let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.lngLat)
                .setHTML(html)
                .addTo(_Map);
        },
        "F_SMART" : function(e){
            const prop = e.features[0].properties;
            let html = `
                                <div class="data_popup">
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p class="mt8 mb8">${prop.crsrdNm}(${prop.crsrdId})</p>
                                    <li class="popup_item">노드ID : <span>${prop.nodeId}</span></li>
                                    <li class="popup_item">최대교통량 : <span>${prop.maxTrfvlm}</span></li>
                                    <li class="popup_item">최대보행자수 : <span>${prop.maxPdstCnt}</span></li>
                                    <li class="popup_item">교통량(1시간) : <span>${prop.vhclTrfvlm}</span></li>
                                    <li class="popup_item">보행자수(1시간) : <span>${prop.pdstCnt}</span></li>
                                    <li class="popup_item">평균차량속도 : <span>${prop.avgVhclSpeed} km/h</span></li>
                                    <li class="popup_item">평균보행자속도 : <span>${prop.avgPdstSpeed} km/h</span></li>
                                </div>
                            `;
            let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.features[0].geometry.coordinates)
                .setHTML(html)
                .addTo(_Map);
        },
        "F_SMART_LINK" : function(e){
            const prop = e.features[0].properties;
            let html = `
                                <div class="data_popup">
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p class="mt8 mb8">${prop.acsRoadNm}(${prop.acsRoadId})</p>
                                    <li class="popup_item">링크ID : <span>${prop.linkId}</span></li>
                                    <li class="popup_item">차로수 : <span>${prop.laneCnt}</span></li>
                                    <li class="popup_item">교통량(1시간) : <span>${prop.vhclTrfvlm}</span></li>
                                    <li class="popup_item">보행자수(1시간) : <span>${prop.pdstCnt}</span></li>
                                    <li class="popup_item">평균차량속도 : <span>${prop.avgVhclSpeed} km/h</span></li>
                                    <li class="popup_item">평균보행자속도 : <span>${prop.avgPdstSpeed} km/h</span></li>
                                </div>
                            `;
            let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.lngLat)
                .setHTML(html)
                .addTo(_Map);
        },
        "F_VDS" : function (e){
            const prop = e.features[0].properties;
            let colctInfoList = JSON.parse(prop.colctInfo);
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
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p class="mt8 mb8">${prop.vdsNm}(${prop.vdsId})</p>
                                    ${table}
                                </div>
                            `;
            let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.features[0].geometry.coordinates)
                .setHTML(html)
                .addTo(_Map);
        },
        "M_EMERGENCY" : function(e){
            const prop = e.features[0].properties;
            let emergencyPopup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.lngLat)
                .setHTML(`
                    <div class="data_popup" >
                        <i class="map_icon bus_icon data_icon"></i>
                        <ul class="data_sub mt16">
                            ${prop.description}
                        </ul>
                        <button class="focus_button" data-id="${prop.serviceid}">따라가기</button>
                    </div>
                `)
                .addTo(_Map);
            core.animate.setOpenedPopup(emergencyPopup);
            $(document).off("click.animate").on("click.animate", ".focus_button", function(){
                console.log("따라가기 스타트");
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
            new mapboxgl.Popup()
                .setLngLat(e.lngLat)
                .setHTML(`
                        <div class="data_popup" style="width:15.7rem;">
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">
								<li class="popup_item">셀ID : <span>${prop.cellId}</span></li>
								<li class="popup_item">유동인구 : <span>${typeof prop.sum === 'undefined' ?  "N/A" : prop.sum}</span></li>
                            </ul>
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
	                            <img src="/statics/images/accident_${e.features[0].properties.type}.png" style="width:40px;">
								<div class="data_popup_scroll">
		                            <ul class="data_sub mt16">
										${html}
		                            </ul>
								</div>
	                        </div>
                    `)
                .addTo(_Map);
        },
        "BD_PATTERN_TRAFFIC_QUANTITY" : function(e){
            const prop = e.features[0].properties;
            const state = e.features[0].state;
            let html = `<div class="data_popup" style="width:15.7rem;">
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
                .addTo(_Map);
        },
        "BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS_MERGE" : function(e){
            const prop = e.features[0].properties;
            let html = `<div class="data_popup" style="width:15.7rem;">
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
                .addTo(_Map);
        },
        "BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS" : function(e) {
            const prop = e.features[0].properties;
            let html = `<div class="data_popup" style="width:15.7rem;">
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
                .addTo(_Map);
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
                            <i class="map_icon bus_icon data_icon"></i>
                            <div class="data_sub mt16">
								${html}
                            </div>
                        </div>
                    `)
                .addTo(_Map);
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
            const state = e.features[0].state;
            if(!state.riskJugCnt) {
                return;
            }
            const html = `
             <li class="popup_item">도로명 : ${prop.ROAD_NAME}</li>
             <li class="popup_item">노선번호 : ${state.busRouteNo}</li>
             <li class="popup_item">위험판정수 : ${state.riskJugCnt}</li>
             <li class="popup_item">과속운행수 : ${state.spdngRungCnt}</li>
             <li class="popup_item">급가속 운행 수 : ${state.sdacelRungCnt}</li>
             <li class="popup_item">급감속 운행 수 : ${state.rpddclRungCnt}</li>
             <li class="popup_item">급정지 운행 수 : ${state.sdstopRungCnt}</li>
             <li class="popup_item">급출발 운행 수 : ${state.sdstrtRungCnt}</li>
             <li class="popup_item">장기과속 운행 수 : ${state.lngtrmaclRungCnt}</li>
            `;
            new mapboxgl.Popup({offset : [0, -15]})
                .setLngLat(e.lngLat)
                .setHTML(`
                        <div class="data_popup">
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">
								${html}
                            </ul>
                        </div>
                    `)
                .addTo(_Map);
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
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">
								${html}
                            </ul>
                        </div>
                    `)
                .addTo(_Map);
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
            let source = Math.random().toString(36);
            let jobId = source.substring(2,source.length);
            this.jobStart(jobId, jobName);
            return jobId;
        }

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
        control.addExpertSourceAndLayer = function(sourceId, clusterOption, featureCollection, layerArray){
            if(sourceId.indexOf(GITS_ENV.LAYER_PREFIX) < 0) {
                sourceId = GITS_ENV.LAYER_PREFIX + sourceId;
            }
            let featureGeoJson = {
                type: "geojson",
                data : featureCollection
            }
            featureGeoJson = $.extend(featureGeoJson, clusterOption, {});
            if(!_Map.getSource(sourceId)) _Map.addSource(sourceId, featureGeoJson);
            let includSourceLayer = {
                sourceId : sourceId,
                layerIds : []
            }
            if(layerArray) {
                for(const layer of layerArray) {
                    if(layer.id.indexOf(GITS_ENV.LAYER_PREFIX) < 0) {
                        layer.id = GITS_ENV.LAYER_PREFIX + layer.id;
                    }
                    _Map.addLayer(layer)
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
                    alert("레이어가 선택되지 않았습니다.");
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
                let newmarker = new mapboxgl.Marker(el, {offset : [150-17, -30]})
                    .setLngLat([lng, lat]);
                if(popup_el) {
                    newmarker.setPopup(
                        new mapboxgl.Popup({offset: 50}) // add popups
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
            _Map.once('moveend', function(){
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
                    .addTo(_Map);
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
                const features = _Map.querySourceFeatures(LAYER.NODE, {sourceLayer : node_tileset_layer});
                const linkFeature = _getUniqueFeatures(features, "NODE_ID", nodeId);
                let html = '';
				if(markerHtml){
					html = markerHtml;
				}else{
					if(linkFeature) {
	                    const prop = linkFeature.properties;
                        html += `<li class="popup_item">노드명 : <span>${prop['NODE_NAME']}</span></li>`;
                        html += `<li class="popup_item">노드유형 : <span>${GITS_ENV.NODE_TYPE[prop['NODE_TYPE']]}</span></li>`;
                        html += `<li class="popup_item">회전제한유무 : <span>${prop['TURN_P'] === "0" ? "무" : "유"}</span></li>`;
	                }else{
	                    html = '정보가 존재하지 않습니다.';
	                }
				}
                new mapboxgl.Popup()
                    .setLngLat([lng, lat])
                    .setHTML(`
                        <div class="data_popup">
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">
								${html}
                            </ul>
                        </div>
                    `)
                    .addTo(_Map);
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
                control.moveMap([lng, lat], 14);
            }
            const features = _Map.querySourceFeatures(LAYER.LINK, {sourceLayer : link_tileset_layer});
            const linkFeature = _getUniqueFeatures(features, "LINK_ID", linkId);
            let html = '';
			if(markerHtml){
					html = markerHtml;
			}else{
	            if(linkFeature) {
	                const prop = linkFeature.properties;
                    html += `<li class="popup_item">링크ID : <span>${prop['LINK_ID']}</span></li>`;
                    html += `<li class="popup_item">도로명 : <span>${prop['ROAD_NAME']}</span></li>`;
                    html += `<li class="popup_item">타입 : <span>${GITS_ENV.ROAD_TYPE[prop['ROAD_TYPE']]}</span></li>`;
                    html += `<li class="popup_item">레벨 : <span>${GITS_ENV.ROAD_RANK[prop['ROAD_RANK']]}</span></li>`;
                    html += `<li class="popup_item">차선수 : <span>${prop['LANES']}</span></li>`;
                    html += `<li class="popup_item">사용여부 : <span>${GITS_ENV.ROAD_USE[prop['ROAD_USE']]}</span></li>`;
                    html += `<li class="popup_item">최고속도 : <span>${prop['MAX_SPD']} km/h</span></li>`;
                    html += `<li class="popup_item">길이 : <span>${prop['LENGTH']}</span></li>`;
	            }else{
	                html = '정보가 존재하지 않습니다.';
	            }
			}
            new mapboxgl.Popup()
                .setLngLat([lng, lat])
                .setHTML(`
                        <div class="data_popup">
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">
								${html}
                            </ul>
                        </div>
                    `)
                .addTo(_Map);
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
                    console.log(id);
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
            _Map.on('click', layerName, MapEvents.CM_BUS_STATION);
            core.control.showLayer(LAYER.BUS_STATION);
        },
        "CM_BUS_ROUTE" : function(e){
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
                    'line-width': 3,
                    'line-opacity': 1,
                },
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [layer]);
            _Map.on("click", layerName, function(e){
                const prop = e.features[0].properties;
                /*let routeTable = `<table>
                <thead>
                <th>노선명</th>
                <th>관리자</th>
                <th>회사명</th>
                <th>기점정류소</th>
                <th>종점정류소</th>
                </thead>
                <tbody>
                `;
                let insertedRouteId = [];
                for(feature of e.features) {
                    if(insertedRouteId.indexOf(feature.properties.routeId) == -1) {
                        routeTable += `
                        <tr>
                            <th>${feature.properties.routeNm}</th>
                            <td>${feature.properties.adminNm}</td>
                            <td>${feature.properties.companyNm}</td>
                            <td>${feature.properties.stStaNm}</td>
                            <td>${feature.properties.edStaNm}</td>
                        </tr>`;
                        insertedRouteId.push(feature.properties.routeId);
                    }
                }
                routeTable += "</tbody></table>";*/
                let html = `
                                <div class="data_popup" >
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p>${prop.roadName}(${prop.linkId})</p>
                                    ${prop.routeTable}
                                </div>
                            `;
                let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                    .setLngLat(e.lngLat)
                    .setHTML(html)
                    .addTo(_Map);
            });
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
                        15, 0.5
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
            _Map.off("click", linkLayerName, MapEvents.F_SMART_LINK);
            _Map.on("click", linkLayerName, MapEvents.F_SMART_LINK);

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
            console.log(e.data);
        },
        "M_TRAFFIC" : function(e){
            if(!_Map.getLayer(LAYER.LINK)){
                return;
            }
            for(const item of e.data) {
                console.log(item);
                _Map.setFeatureState({
                    source: LAYER.LINK,
                    sourceLayer: link_tileset_layer,
                    id: item.linkId,
                }, {
                    conggrade: item.conggrade
                });
            }
            const colors = [
                ["0" , "#74aaff"],
                ["1" , "#11ff00"],
                ["2" , "#ffde00"],
                ["3" , "#de0909"],
            ];
            if(!JSON.stringify(_Map.getPaintProperty(LAYER.LINK, 'line-color')).includes("conggrade"))
                core.control.setPaintFilterColorByState(LAYER.LINK, "conggrade", colors, "#11ff00");

        },
        "M_EMERGENCY" : function(e){
            const layer = GITS_ENV.LAYER_PREFIX+"M_EMERGENCY";
            if(_Map.getLayer(layer)){
                _Map.getSource(layer).setData(e.data.featureCollection);
            }else{
                core.control.addSourceAndLayer("geojson",layer, layer, e.data.featureCollection, false, true);
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
                                <div class="data_popup" >
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <ul class="data_sub mt16">
                                         ${feature.properties.description}
                                    </ul>
                                    <button class="focus_button" data-id="${feature.properties.serviceid}">따라가기</button>
                                </div>
                            `)
                            .addTo(_Map);
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
            core.control.addSourceAndLayer("geojson",layer, layer, e.data.featureCollection, false, true);
        },
        "M_WARNING" : function (e) {
            core.control.marker ? core.control.marker.clearAll() : null;
            //const msgbody = e.data.serviceresult.msgbody;
            //for (const item of msgbody.itemlist) {
			for (const item of e.data) {
                const markerWrap = document.createElement("div");
                const markerIcon = document.createElement("i");
                const markerText = document.createElement('div');
                markerWrap.append(markerIcon);
                markerWrap.append(markerText);
                markerWrap.className = "map_icon_wrap";
                markerIcon.className = "map_icon icon_red";
                markerText.className = "map_icon_text red";
                markerText.innerHTML = item.description;
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
				            <div class="data_popup event_pop">
                                <i class="map_icon bus_icon data_icon"></i>
								<ul class="mt16">
								    <li class="data_sub popup_item">내용 : <span>${item.description}</span></li>
	                                <li class="data_sub popup_item">발생시간 : <span>${item.beginDate}</span></li>
	                                <li class="data_sub popup_item">종료(예정)시간 : <span>${item.endDate}</span></li>
	                                <li class="data_sub popup_item">발생차선 : <span>${item.occurredLane}차선</span></li>
	                                <li class="data_sub popup_item">통제차선 : <span>${item.closedLane}차선</span></li>
	                                <li class="data_sub popup_item">돌발유형 : <span>${item.inciCate}</span></li>
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
            core.control.hideLayer(LAYER.NODE);
            for(const item of e.data) {
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
        },
        "BD_POPULATION" : function(e) {
            let stHour = 0;
            if(e.data.stTime.length === 4){
                stHour = parseInt(e.data.stTime.substring(0,2));
            }else{
                stHour =  e.data.stTime;
            }
            let endHour = 0;
            if(e.data.endTime.length === 4) {
                endHour = parseInt(e.data.endTime.substring(0,2));
            }else{
                endHour = e.data.endTime;
            }

            for(const item of e.data.list) {
                let state = {sum : 0};
                for(const key in item){
                    if(key.includes("tz")
                        && (parseInt(key.replace("tz","")) >= stHour
                            && parseInt(key.replace("tz","")) <= endHour)
                    ){
                        state.sum += item[key];
                    }

                    state[key] = item[key];
                }
                _Map.setFeatureState({
                    source: LAYER.GRID,
                    sourceLayer: grid_tileset_layer,
                    id: item.cellId,
                }, state);
            }

            // TODO :: 범례조정필요
            _Map.setPaintProperty(LAYER.GRID, 'fill-color', [
                'case',
                ['all', ['>=', ['feature-state','sum'], 0], ['<', ['feature-state','sum'], 10000]],
                LEGEND_COLOR.TRAFFIC_AMT[0],
                ['all', ['>=', ['feature-state','sum'], 10000], ['<', ['feature-state','sum'], 10500]],
                LEGEND_COLOR.TRAFFIC_AMT[0],
                ['all', ['>=', ['feature-state','sum'], 10500], ['<', ['feature-state','sum'], 11000]],
                LEGEND_COLOR.TRAFFIC_AMT[1],
                ['all', ['>=', ['feature-state','sum'], 11000], ['<', ['feature-state','sum'], 12000]],
                LEGEND_COLOR.TRAFFIC_AMT[2],
                ['all', ['>=', ['feature-state','sum'], 12000], ['<', ['feature-state','sum'], 13000]],
                LEGEND_COLOR.TRAFFIC_AMT[3],
                ['>=', ['feature-state',"sum"], 13000],
                LEGEND_COLOR.TRAFFIC_AMT[4],
                STYLES.LINK_COLOR
            ]);
            core.control.showLayer(LAYER.GRID);

            _Map.on('click', LAYER.GRID, MapEvents.BD_POPULATION);
        },
        "BD_PT_DANGER_ANALYSIS" : function(e){
            for(const item of e.data) {
                let featureStateProp = {
                    busRouteNo : item.busRouteNo,
                    coId : item.coId,  //회사 아이디
                    vhclNo : item.vhclNo,  //차량 번호
                    lngtrmaclRungCnt : item.lngtrmaclRungCnt, //장기과속 운행 수
                    riskJugCnt : item.riskJugCnt, // 위험 판정 수
                    rpddclRungCnt : item.rpddclRungCnt, //급감속 운행 수
                    sdacelRungCnt : item.sdacelRungCnt, //급가속 운행 수
                    sdstopRungCnt : item.sdstopRungCnt,  //급정지 운행 수
                    sdstrtRungCnt : item.sdstrtRungCnt, //급출발 운행 수
                    spdngRungCnt : item.spdngRungCnt //과속 운행 수
                }

                _Map.setFeatureState({
                    source: LAYER.LINK,
                    sourceLayer: link_tileset_layer,
                    id: item.linkId,
                }, featureStateProp);
            }
            const legend = "riskJugCnt";
            _Map.setPaintProperty(LAYER.LINK, 'line-color', [
                'case',
                ['==', ['feature-state',legend], null],
                STYLES.LINK_COLOR,
                ['all', ['>=', ['feature-state',legend], 0], ['<', ['feature-state',legend], 10]],
                LEGEND_COLOR.TRAFFIC_AMT[0],
                ['all', ['>=', ['feature-state',legend], 10], ['<', ['feature-state',legend], 20]],
                LEGEND_COLOR.TRAFFIC_AMT[0],
                ['all', ['>=', ['feature-state',legend], 20], ['<', ['feature-state',legend], 30]],
                LEGEND_COLOR.TRAFFIC_AMT[1],
                ['all', ['>=', ['feature-state',legend], 30], ['<', ['feature-state',legend], 40]],
                LEGEND_COLOR.TRAFFIC_AMT[2],
                ['all', ['>=', ['feature-state',legend], 40], ['<', ['feature-state',legend], 50]],
                LEGEND_COLOR.TRAFFIC_AMT[3],
                ['>=', ['feature-state',legend], 50],
                LEGEND_COLOR.TRAFFIC_AMT[4],
                STYLES.LINK_COLOR
            ]);
            _Map.on('click', LAYER.LINK, MapEvents.BD_PT_DANGER_ANALYSIS);
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
                'minzoom': 11,
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
            core.control.addExpertSourceAndLayer(sourceName, {}, e.data.featureCollection, [layer]);
            _Map.on('click', layerId, MapEvents.BD_DANGER_ZONE_BY_TYPE);
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
                'minzoom': 11,
                paint: {
                    'circle-radius': 1,
                    'circle-opacity' : 0,
                    'circle-stroke-color' : "#000000",
                    'circle-stroke-width' : 2
                }
            }
            const newMarkers = {};
            for (const feature of e.data.featureCollection.features) {
                const coords = feature.geometry.coordinates;
                const props = feature.properties;
                const id = props.sggNm;
                let marker = openMarkers[id];
                if (!marker) {
                    let el = null;
                    if(props.acdntCntTotal && props.acdntCntTotal !== 0) {
                        el = _Util.createDonutChart(props, id, [
                            props.dcsdCntTotal,
                            props.injDclrCntTotal,
                            props.injpsnCntTotal,
                            props.sinjpsnCntTotal,
                            props.swpsnCntTotal
                        ], ['#fed976', '#feb24c', '#fd8d3c', '#fc4e2a', '#e31a1c']);
                    }else{
                        el = _Util.createDonutChart(props, id, [1], ['#999999']);
                    }
                    marker = openMarkers[id] = new mapboxgl.Marker({
                        element: el
                    }).setPopup(
                        new mapboxgl.Popup({offset: 25}) // add popups
                            .setHTML(`
                                <div class="data_popup" style="width:14rem;">
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <div class="ftsize16 mt8 mb8">${props.sggNm}</div>
                                    <ul>
                                        <li class="popup_item">전체사고수 : <span>${props.acdntCntTotal ? props.acdntCntTotal : 0}</span></li>
                                        <li class="popup_item">사망자수 : <span>${props.dcsdCntTotal ? props.dcsdCntTotal : 0}</span></li>
                                        <li class="popup_item">부상자수 : <span>${props.injpsnCntTotal ? props.injpsnCntTotal : 0}</span></li>
                                        <li class="popup_item">중상자수 : <span>${props.swpsnCntTotal ? props.swpsnCntTotal : 0}</span></li>
                                        <li class="popup_item">경상자수 : <span>${props.sinjpsnCntTotal ? props.sinjpsnCntTotal : 0}</span></li>
                                        <li class="popup_item">부상자신고수 : <span>${props.injDclrCntTotal ? props.injDclrCntTotal : 0}</span></li>
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
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [sggAccidentLayer]);
        },
        "BD_PATTERN_TRAFFIC_QUANTITY" : function(e) {
            const sourceName = LAYER.BD_PATTERN;
            const layerName = LAYER.BD_PATTERN;
            core.control.removeCustomSource(sourceName);
            let lineLayer = {
                'id': layerName,
                'type': 'line',
                'source': sourceName,
                'layout': {
                    'line-join': 'round',
                    'line-cap': 'round'
                },
                'paint': {
                    'line-color': [
                        'match',
                        ['get',"conggrade"],
                        "3",
                        LEGEND_COLOR.TRAFFIC_6LEVEL[6],
                        "2",
                        LEGEND_COLOR.TRAFFIC_6LEVEL[4],
                        "1",
                        LEGEND_COLOR.TRAFFIC_6LEVEL[3],
                        LEGEND_COLOR.TRAFFIC_6LEVEL[0]
                    ],
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
                alert(e.data.errorMsg);
                return;
            }
            savedDataForDualMap = e.data;
            const sourceName = LAYER.BD_EFFECT;
            const layerName = LAYER.BD_EFFECT;
            core.control.removeCustomSource(sourceName);
            let lineLayer = {
                'id': layerName,
                'type': 'line',
                'source': sourceName,
                'layout': {
                    'line-join': 'round',
                    'line-cap': 'round'
                },
                'paint': {
                    'line-color': [
                        'match',
                        ['get', "conggrade"],
                        "3",
                        LEGEND_COLOR.TRAFFIC_6LEVEL[6],
                        "2",
                        LEGEND_COLOR.TRAFFIC_6LEVEL[4],
                        "1",
                        LEGEND_COLOR.TRAFFIC_6LEVEL[3],
                        LEGEND_COLOR.TRAFFIC_6LEVEL[0]
                    ],
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
                alert(e.data.errorMsg);
                return;
            }
            savedDataForDualMap = e.data;
            const sourceName = LAYER.BD_EFFECT;
            const layerName = LAYER.BD_EFFECT;
            core.control.removeCustomSource(sourceName);
            let lineLayer = {
                'id': layerName,
                'type': 'line',
                'source': sourceName,
                'layout': {
                    'line-join': 'round',
                    'line-cap': 'round'
                },
                'paint': {
                    'line-color': [
                        'match',
                        ['get',"conggrade"],
                        "3",
                        LEGEND_COLOR.TRAFFIC_6LEVEL[6],
                        "2",
                        LEGEND_COLOR.TRAFFIC_6LEVEL[4],
                        "1",
                        LEGEND_COLOR.TRAFFIC_6LEVEL[3],
                        LEGEND_COLOR.TRAFFIC_6LEVEL[0]
                    ],
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
            const layerName = LAYER.CROSSROAD_TRF_QUANTITY;
            let layer = {
                'id': layerName,
                'type': 'heatmap',
                'source': layerName,
                'paint': {
                    'heatmap-weight': [
                        'interpolate',
                        ['linear'],
                        ["get","trfvlmTotal"],
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
            core.control.addExpertSourceAndLayer(layerName,{}, e.data.featureCollection, [layer]);

            _Map.on("click", layerName, function(e){
                const prop = e.features[0].properties
                new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                    .setLngLat(e.features[0].geometry.coordinates)
                    .setHTML(`
                        <div class="data_popup" style="width:15.7rem;">
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
            })
            let c = $(`
                <div>
                    <div class="progress-wrap">
                        <div class="progress-bar"></div>
                        <div class="progress-separate-wrap"></div>
                    </div>    
                </div>`);
            for(let i=0; i <24; i++) {
                const t = i < 10 ? "0"+i : i;
                c.find(".progress-separate-wrap").append(`<div class="progress-separate" style="width:${100/24}%;float:left;">${t}:00</div>`)
            }

            let playerInterval = null;
            let progressInterval = null;
            let time = 0;
            let barTime = 0;
            let totalBarTime = 24000;
            let duration = 50;
            core.control.generatePlayerModel("교차로 교통량 변화", c, function(){
                if(!playerInterval) clearInterval(playerInterval);
                if(!progressInterval) clearInterval(progressInterval);
                playerInterval = setInterval(function(){
                    const value = (time < 10 ? "0"+time : time)+"00_val";
                    _Map.setPaintProperty(layerName, "heatmap-weight", [
                        'interpolate',
                        ['linear'],
                        ["feature-state",value],
                        0, 0,
                        100, 1,
                    ]);
                    if(time === 24) {
                        clearInterval(playerInterval);
                        clearInterval(progressInterval);
                        time = 0;
                        barTime = 0;
                        return;
                    }
                    time++;
                }, 1000);
                progressInterval = setInterval(function(){
                    if(barTime > totalBarTime) {
                        clearInterval(progressInterval);
                        return;
                    }
                    c.find(".progress-bar").css("width", ((barTime/totalBarTime)*100)+"%");
                    barTime += duration;
                }, duration);
            }, function(){
                clearInterval(playerInterval);
                clearInterval(progressInterval);
                barTime = 0;
                time = 0;
                c.find(".progress-bar").css("width", "0%");
                _Map.setPaintProperty(layerName, "heatmap-weight", [
                    'interpolate',
                    ['linear'],
                    ["feature-state","0000_val"],
                    0, 0,
                    100, 1,
                ]);
            });
        }
    }
    workerResultEvent = _RemoveJobItem(workerResultEvent);
}