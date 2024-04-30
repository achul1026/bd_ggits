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
    let loadedWorkerData = {};
    let jobList = [];
    let dangerCarAlarmed = [];
    let savedWarningFilter = [];
    let savedWarningData = [];
    let smcrsrdPredictionSelectedDate = "";
    let m_bus_features = null;
    let isDual = false;
    const SESSION_KEY = "gitsmap";
    const SESSION_PROP = GITS_ENV.SESSION_PROP;
    const LOG_LEVEL = "";
    const token = "pk.eyJ1IjoiZ2dpdHMyMDA2IiwiYSI6ImNscGt5enpmNTAxNmwya3Ftd3VheGZzZ3AifQ.K6BoOBK2NLUDWrr4sn-byQ";
    // const tileset_id = "desimin.clllyrgwf02rg2oprgc0p9wgf-5249e";
    // const tileset_layer = "ggits_node_link_dev"

    let link_tileset_id = "ggits2006.3cne999k";
    let link_tileset_layer = "ggits_link_all";
    const svc_link_tileset_id = "ggits2006.c9o8oulf";
    const svc_link_tileset_layer = "GIS_LINK_SVC_wgs84_utf8geojsonl";
    const monitoring_link_tileset_id = "ggits2006.btecxyux";
    const monitoring_link_tileset_layer = "ggits_link_monitoring";
    const node_tileset_id = "ggits2006.ba829l1p";
    const node_tileset_layer = "ggits_node_all";
    /*const grid_tileset_id = "desimin.275boefx";
    const grid_tileset_layer = "B100_1KM__202304_-_-10hnns";*/
    const grid_tileset_id = "ggits2006.0mmlsqn8";
    const grid_tileset_layer = "ggits_grid_cell_500";
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
                        if(arguments[0] && arguments[0].data && arguments[0].data.isThrow === true && !arguments[0].data.event.includes("M_")) {
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
        _Worker = new Worker(__contextPath__+"/statics/map/ggits.map.worker.js?t="+new Date().getTime());

        _Worker.onmessage = function(e){
            if(LOG_LEVEL === "DEBUG")
                console.log("Event Result Name : " + e.data.event);
            if(e.data.event) {
                if(e.data.noLogin) {
                    new ModalBuilder().init().alertBoby("로그인 세션이 만료되었습니다.").footer(4,'확인',function(button, modal){
                        window.location.href= "/login.do";
                    }).open();
                    return;
                }
                workerResultEvent[e.data.event](e);
            }else{
                console.log("Event Worker Data is empty", e.data);
            }
        };
        // icon load
        for(const icon of GITS_ENV.ICONS) {
            let data = await _Map.loadImage(icon.url, function(err, image){
                if(icon.sdf === true) {
                    _Map.addImage(icon.id, image, {sdf : true});
                }else{
                    _Map.addImage(icon.id, image);
                }
            });
        }
    }

    const _visibleChangeEventInit = function(){
        document.addEventListener("visibilitychange", () => {
            /*if (document.hidden) {
                _Worker.postMessage({pause:true});
            } else {
                _Worker.postMessage({resume:true});
            }*/
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
    core.setSavedWarningFilter = function(filter) {
        savedWarningFilter = filter;
        core.monitoring.getWarningInfo();
    }
    core.getSavedWarningFilter = function() {
        return savedWarningFilter;
    }
    core.setSavedWarningData = function(data) {
        savedWarningData = data;
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
        if(page === "MONITORING" || page === "MONITORING_DASHBOARD") {
            link_tileset_id = monitoring_link_tileset_id;
            link_tileset_layer = monitoring_link_tileset_layer;
        }
        isDual = isDualMap;

        /*
        * draggable
        * */
        $(document).on("mouseover", ".data_popup",function(){
            $(".data_popup:not(.ui-draggable)").draggable();
        });

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

            defaultLayer.drawSGGLayer();
            switch(page) {
                case "MONITORING" :
                    defaultLayer.drawLinkLayer(page, false);
                    defaultLayer.drawNodeLayer({
                        'minzoom' : 14,
                    }, function(e){

                    });
                    core.setMonitoring();
                    break;
                case "MONITORING_DASHBOARD" :
                    defaultLayer.drawLinkLayer(page);
                    core.setMonitoringDashboard();
                    break;
                default :
                    defaultLayer.drawGridLayer();
                    defaultLayer.drawLinkLayer(page);
                    defaultLayer.drawSvcLinkLayer(true);
                    defaultLayer.drawNodeLayer();
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
        core.defaultLayer = this;

        this.drawNodeLayer = function(obj){
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
            let color = STYLES.NODE_COLOR;
            try {
                if (_Map.getStyle().sprite.includes("light"))
                    color = STYLES.NODE_COLOR_DARK
            }catch(e){}
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
                    'circle-color' : color
                },
            }
            if(obj)
                nodeLayerObj = $.extend(nodeLayerObj, obj, {});
            nodeLayerObj.filter = ["has","NODE_ID"];
            _Map.addLayer(nodeLayerObj);
        }

        this.drawSvcLinkLayer = function(isHide = false){
            if(_Map.getLayer(LAYER.SVC_LINK)) {
                _Map.removeLayer(LAYER.SVC_LINK)
            }

            if(_Map.getSource(LAYER.SVC_LINK)) {
                _Map.removeSource(LAYER.SVC_LINK);
            }

            let linkSourceObj = {
                "type": "vector",
                "url": "mapbox://"+svc_link_tileset_id,
                'minzoom': 6,
                'maxzoom': 22,
                "promoteId" : {"GIS_LINK_SVC_wgs84_utf8geojsonl":"LINK_ID"}
            }
            _Map.addSource(LAYER.SVC_LINK, linkSourceObj);
            let linkLayerObj = {
                'id': LAYER.SVC_LINK,
                'type': 'line',
                'source': LAYER.SVC_LINK, // reference the data source
                'source-layer': svc_link_tileset_layer,
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
                        15, 7,
                        18, 8
                    ]
                },
                filter : ["==",'linkLvl',"10"]
                /*filter : [
                    "==", ["zoom"],
                    ["match", ["get", "LINK_LVL"],
                        3,  // rank
                        8, // minimum zoom level
                        4,  // etc.
                        9,
                        5,
                        10,
                        6,
                        11,
                        7,
                        12,
                        8,
                        13,
                        9,
                        14,
                        10,
                        15,
                        15
                    ]
                ]*/
            }
            if(isHide === true) {
                linkLayerObj.layout.visibility = "none";
            }
            _Map.addLayer(linkLayerObj);

            _Map.on("click", LAYER.SVC_LINK, MapEvents.TEST);
        }

        this.drawLinkLayer = function(page, isHide = false){

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
                "promoteId" : {"ggits_link_all":"LINK_ID"}
            }
            if(page === "MONITORING" || page === "MONITORING_DASHBOARD") {
                linkSourceObj.promoteId = {"ggits_link_monitoring" :"LINK_ID"};
            }
            let color = STYLES.LINK_COLOR;
            try {
                if (_Map.getStyle().sprite.includes("light"))
                    color = STYLES.LINK_COLOR_DARK
            }catch(e){}
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
                        15, 7,
                        18, 8
                    ]
                }
            }
            if(isHide === true) {
                linkLayerObj.layout.visibility = "none";
            }
            if(page === "MONITORING" || page === "MONITORING_DASHBOARD") {
                linkLayerObj.filter = [
                    ">=", ["zoom"],
                    ["match", ["get", "ROAD_RANK"],
                        '101',  // rank
                        8, // minimum zoom level
                        '102',  // etc.
                        8,
                        '103',
                        8,
                        /*'104',
                        14,
                        '105',
                        14,
                        '106',
                        14,
                        '107',
                        14,
                        '108',
                        14,*/
                        12  // fallback for ranks > 4
                    ]
                ];
            }else{
                linkLayerObj.filter = ["has","LINK_ID"];
            }

            /*if(city) {
                linkLayerObj.filter = ["all", ["has","LINK_ID"], ["==","layer", city+"_link"]];
            }*/
            _Map.addLayer(linkLayerObj);
        }
        this.drawGridLayer = function(){

            if(_Map.getLayer(LAYER.GRID)) {
                _Map.removeLayer(LAYER.GRID)
            }

            if(_Map.getSource(LAYER.GRID)) {
                _Map.removeSource(LAYER.GRID);
            }

            // 그리드
            _Map.addSource(LAYER.GRID, {
                "type": "vector",
                "url": "mapbox://"+grid_tileset_id,
                'minzoom': 6,
                'maxzoom': 22,
                "promoteId" : {"ggits_grid_cell_500":"OBJECTID"}
            });
            let flatLayer = {
                'id': LAYER.GRID,
                'type': 'fill',
                'source': LAYER.GRID,
                'source-layer': grid_tileset_layer,
                'layout': {
                    "visibility" : "none"
                },
                'paint': {
                    'fill-color': 'transparent',
                    /*'fill-outline-color' : '#ffffff',*/
                    'fill-outline-color' : [
                        'case',
                        ['==', ['feature-state','sggNm'], null],
                        "rgba(0,0,0,0)",
                        "rgba(255,255,255,0.5)"
                    ],
                    'fill-opacity' : 0.7
                }
            };
            let thrDLayer ={
                'id': LAYER.GRID,
                'type': 'fill-extrusion',
                'source': LAYER.GRID,
                'source-layer': grid_tileset_layer,
                'layout': {
                    "visibility" : "none"
                },
                'paint': {
                    'fill-extrusion-color': "rgba(0,0,0,0)",
                    'fill-extrusion-height': 0,
                    'fill-extrusion-opacity': 0.5
                }
            }
            _Map.addLayer(thrDLayer);
        }

        this.drawSGGLayer = function(){
            // 시군구
            if(typeof _Map.getSource(LAYER.SGG) === "undefined") {
                _Map.addSource(LAYER.SGG, {
                    "type": "vector",
                    "url": "mapbox://ggits2006.cjqhjk4k",
                    'minzoom': 9,
                    'maxzoom': 22
                });
            }

            let colorCaseSGG = ['case'];
            let sggInfo = GITS_ENV.SGG_INFO;
            for(const sggNm in GITS_ENV.SGG_INFO) {
                const sgg = sggInfo[sggNm];
                colorCaseSGG.push(['in', sgg.CODE.substring(0,4), ['string', ['get', 'SIG_CD']]]);
                colorCaseSGG.push(sgg.COLOR);
            }
            colorCaseSGG.push("transparent");
            if(typeof _Map.getLayer(LAYER.SGG) === "undefined") {
                _Map.addLayer({
                        'id': LAYER.SGG,
                        'type': 'fill',
                        'source': LAYER.SGG, // reference the data source
                        'source-layer': "sgg_new",
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
                    'source-layer': "sgg_new",
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
        facility.getSmartIntersection = function(type = 'all', loop = false, loopTime = 60000){
            _Worker.postMessage({
                event : "F_SMART",
                type: type,
                loop : loop,
                loopTime : loopTime,
                jobId : core.control.generateJobId("스마트교차로 정보 데이터 로딩")
            });
        }

        facility.removeSmartIntersection = function(){
            core.control.removeCustomSource(LAYER.FACILITY_SMART);
            stopWorkerInterval("F_SMART");
        }
        
        facility.getVds = function(loop = false, loopTime = 60000){
            _Worker.postMessage({
                event : "F_VDS",
                loop : loop,
                loopTime : loopTime,
                jobId : core.control.generateJobId("VDS 정보 데이터 로딩")
            });
        }

        facility.removeVds = function(){
            core.control.removeCustomSource(LAYER.FACILITY_VDS);
            stopWorkerInterval("F_VDS");
        }
        
        facility.getDSRC = function(type = "all"){
            _Worker.postMessage({
                event : "F_DSRC",
                type : type,
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
            $("#bigdataDetailInfoContainer").hide();
            $("#bigdataDetailInfoContainerDual").hide();
            $("#bigdataDetailInfoContainer").removeClass("off");
            $("#bigdataDetailInfoContainerDual").removeClass("off");
            $("#bigdataDetailContentWrap").empty();
            $("#bigdataDetailContentWrapDual").empty();
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
                        if (layer.id.includes("GITS_BD_") || layer.id.includes("GITS_BUS_ROUTE") ||  layer.id.includes("GITS_BUS_STATION")) {
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

                    core.control.removeCustomSource(LAYER.FACILITY_SMART);
                    core.control.removeCustomSource(LAYER.FACILITY_VDS);
                    core.control.removeCustomSource(LAYER.FACILITY_DSRC);
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
            $(".mapboxgl-popup").remove();
            $(".job-item.filter-item").remove();
            $('.optimization_container').hide();
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

        bigdata.getPopulationExcelDownload = function(hh){
            const rows = loadedWorkerData["BD_POPULATION"].filter((obj) => obj.timezn == hh);
            fnDownloadExcelWorker({
                exportType : "default",
                filename : hh+"시간대 전체 유동인구예측데이터.xlsx",
                header : ['날짜','시군구','행정동','시간대','유동인구'],
                rows :  rows,
                metadata : ['baseymd','sggNm','adstsdgNm','timezn','fltPop']
            });
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
                routeId: so.routeId
            });
            _Worker.postMessage({
                event: "CM_BUS_STATION",
                searchOption : searchOption
            });
        }
        bigdata.getBusRouteAndStationInfo = function(searchOption){
            const so = _Util.convertParamToObject(searchOption);
            _Worker.postMessage({
                event: "CM_BUS_ROUTE",
                routeId: so.routeId,
                direction : so.direction
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
                jobId : core.control.generateJobId("대중교통 안전운행 데이터 로딩")
            });
            gitsApp.startLoading();
            /*_Worker.postMessage({
                event: "BD_BUS_STATION",
                searchOption : searchOption,
                jobId: core.control.generateJobId("버스 정류장 정보 로딩중")
            });*/
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

            const so = _Util.convertParamToObject(searchOption);
            _Worker.postMessage({
                event : "BD_PATTERN_TRAFFIC_QUANTITY",
				searchOption  : searchOption,
                jobId : core.control.generateJobId((so.type === "speed" ? "평균속도" : "교통량")+" 데이터 로딩")
            });
            core.control.removeCustomSource(LAYER.FACILITY_SMART);
            core.control.removeCustomSource(LAYER.FACILITY_VDS);
            core.control.removeCustomSource(LAYER.FACILITY_DSRC);
            if(so.collectType === "smc"){
                _Worker.postMessage({
                    event : "F_SMART",
                    type: "crsrd",
                    jobId : core.control.generateJobId("스마트교차로 레이어 로딩")
                });
            }else if(so.collectType === "vds") {
                core.facility.getVds();
            }else if(so.collectType === "dsrc") {
                core.facility.getDSRC("dsrc");
            }
        }

        /**
         * 상습 정체구간 데이터 조회
         */
        bigdata.getPatternTrafficCongDestiny = function(searchOption = ''){
            _Worker.postMessage({
                event : "BD_PATTERN_TRAFFIC_CNGSTN",
                searchOption  : searchOption,
                jobId : core.control.generateJobId("정체구간 데이터 로딩")
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
            const so = _Util.convertParamToObject(searchOption);

            let smartObj = {
                event : "F_SMART",
                type: "crsrd",
                jobId : core.control.generateJobId("스마트교차로 레이어 로딩")
            };
            if(so.searchLocation && so.searchLocation !== "searchAllLocation"){
                for(const sggNm in GITS_ENV.SGG_INFO) {
                    if(GITS_ENV.SGG_INFO[sggNm].CODE === so.searchLocation) {
                        smartObj.mngInstCd = GITS_ENV.SGG_INFO[sggNm].MNGCD;
                        break;
                    }
                }
            }
            _Worker.postMessage(smartObj);
        }

        /**
         * 교통활동 효과분석 데이터 조회 - 서비스링크
         */
        bigdata.getTrafficActiveEffectAnalysisSvcLink = function(searchOption = ''){
            _Worker.postMessage({
                event : "BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS_SVC_LINK",
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
                jobId : core.control.generateJobId("교통사고 위험지역 데이터 로딩")
            });
        }

        bigdata.getAccidentDangerAreaInfoHeatMap = function(searchOption){
            _Worker.postMessage({
                event : "BD_DANGER_ZONE_BY_TYPE_HEATMAP",
                searchOption : searchOption,
                jobId : core.control.generateJobId("교통사고 다발지역 데이터 로딩")
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
            /*_Worker.postMessage({
                event: "BD_BUS_STATION",
                searchOption : searchOption,
                jobId: core.control.generateJobId("버스정류장 정보 로딩중")
            });*/
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
                searchOption : "routeId="+selectedSearchOption["BD_BUS_ROUTE_CURVE_ANL"].routeId+"&directLine=true",
                disabledClickEvent : false,
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

            /*_Worker.postMessage({
                event: "BD_BUS_STATION",
                searchOption : "routeId="+selectedSearchOption["BD_BUS_ROUTE_PASSENGER_ANL"].routeId,
                jobId: core.control.generateJobId("버스정류장 정보 로딩중")
            });*/
            _Worker.postMessage({
                event: "BD_PUBLIC_TRANSFER_LNDI",
                /*searchOption : "routeId="+selectedSearchOption["BD_BUS_ROUTE_PASSENGER_ANL"].routeId,*/
                searchOption : searchOption,
                jobId: core.control.generateJobId("버스정류장 승하차인원 데이터 로딩중")
            });
        }

        bigdata.getPublicTransferPassengerAnalysisAll = function(searchOption) {
            selectedSearchOption["BD_PUBLIC_TRANSFER_LNDI_ALL"] = _Util.convertParamToObject(searchOption);
            gitsApp.startLoading()
            _Worker.postMessage({
                event: "BD_PUBLIC_TRANSFER_LNDI_ALL",
                searchOption : searchOption,
                jobId: core.control.generateJobId("대중교통 하차인원 데이터 로딩중")
            });
        }

        

        bigdata.getPublicTransferDuplicateRoute = function(searchOption){
            selectedSearchOption["BD_BUS_ROUTE_DUPLICATE_ROUTE"] = _Util.convertParamToObject(searchOption);
            _Worker.postMessage({
                event: "BD_BUS_ROUTE_DUPLICATE_ROUTE",
                searchOption : searchOption,
                stStationId : selectedSearchOption["BD_BUS_ROUTE_DUPLICATE_ROUTE"].stStationId,
                edStationId : selectedSearchOption["BD_BUS_ROUTE_DUPLICATE_ROUTE"].edStationId,
                jobId: core.control.generateJobId("버스 중복 구간 정보 로딩중")
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

        bigdata.getTrafficVolumeOrdtmNlrd = function(searchOption) {
            selectedSearchOption["BD_TRFVLM_ORDTM"] = _Util.convertParamToObject(searchOption);
            _Worker.postMessage({
                event: "BD_TRFVLM_ORDTM",
                searchOption : searchOption,
                jobId: core.control.generateJobId("국도 상시 교통량 정보 로딩중")
            });
        }
        bigdata.removeTrafficVolumeOrdtmNlrd = function(){
            core.control.removeCustomSource(LAYER.BD_TRAFFIC_VOLUME_ORDTM_LNRD);
        }

        bigdata.getTrafficVolumeAnyTmNlrd = function(searchOption) {
            selectedSearchOption["BD_TRFVLM_ANYTM_NLRD"] = _Util.convertParamToObject(searchOption);
            _Worker.postMessage({
                event: "BD_TRFVLM_ANYTM_NLRD",
                searchOption : searchOption,
                jobId: core.control.generateJobId("국도 수시 교통량 정보 로딩중")
            });
        }

        bigdata.removeTrafficVolumeAnyTmNlrd = function(){
            core.control.removeCustomSource(LAYER.BD_TRAFFIC_VOLUME_ANYTM_LNRD);
        }

        bigdata.getTrafficVolumeAnyTmHghw = function(searchOption) {
            selectedSearchOption["BD_TRFVLM_ANYTM_HGHW"] = _Util.convertParamToObject(searchOption);
            _Worker.postMessage({
                event: "BD_TRFVLM_ANYTM_HGHW",
                searchOption : searchOption,
                jobId: core.control.generateJobId("고속도로 수시 교통량 정보 로딩중")
            });
        }

        bigdata.removeTrafficVolumeAnyTmHghw = function(){
            core.control.removeCustomSource(LAYER.BD_TRAFFIC_VOLUME_ANYTM_HGHW);
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
            monitoring.getTrafficInfoMinimize();
            monitoring.getTrafficInfo(true, 60000);
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
            monitoring.removeDggdMoveInfo();
            const EMER_TMP_LAYER = GITS_ENV.LAYER.EMERGENCY+"_TMP"
            if(_Map.getLayer(EMER_TMP_LAYER)) _Map.removeLayer(EMER_TMP_LAYER);
            if(_Map.getLayer(EMER_TMP_LAYER+"_POINT")) _Map.removeLayer(EMER_TMP_LAYER+"_POINT");
            if(_Map.getLayer(EMER_TMP_LAYER+"_LOC")) _Map.removeLayer(EMER_TMP_LAYER+"_LOC");
            if(_Map.getSource(EMER_TMP_LAYER)) _Map.removeSource(EMER_TMP_LAYER);
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
                loopTime : 3000
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
                core.bigdata.removeBigDataLayers();
            }
            _Worker.postMessage({event : "CM_BUS_STATION", searchOption : searchOption, jobId : core.control.generateJobId("버스 정류장 레이어 로딩중")});
        }

        /**
         * 버스정류장 레이어 제거
         */
        monitoring.removeBusStationInfo = function(){
            core.control.removeCustomSource(LAYER.BUS_STATION);
        }

        monitoring.getBusStartEndStationInfo = function(routeId) {
            _Worker.postMessage({
                event : "CM_STARTENDSTATIONBYROUTE",
                loop : false,
                routeId : routeId,
                jobId: core.control.generateJobId("버스 기종점 정보 로딩중")
            });
        }

        /**
         * 버스 실시간 이동현황
         */
        monitoring.getBusMoveInfo = function(clear  = true){
            if(clear) {
                monitoring.removeAddedMonitoringLayer();
            }
            /*_Worker.postMessage({event : "CM_BUS_STATION", jobId : core.control.generateJobId("버스 정류장 레이어 로딩중")});*/
            _Worker.postMessage({
                event : "M_BUS",
                loop : true,
                loopTime : 30000,
                jobId: core.control.generateJobId("실시간 버스 이동현황 정보 로딩중")
            });
        }

        monitoring.getDggdMoveInfo = function(clear  = true){
            if(clear) {
                monitoring.removeAddedMonitoringLayer();
            }
            _Worker.postMessage({
                event : "M_DANGER_VEHICLE",
                loop : true,
                loopTime : 10000,
                jobId: core.control.generateJobId("위험물 차량 이동현황 정보 로딩중")
            });
        }

        monitoring.removeDggdMoveInfo = function(){
            core.control.removeCustomSource(LAYER.DANGER);
            stopWorkerInterval("M_DANGER_VEHICLE");
        }

        /**
         * 버스 실시간 이동현황 제거
         */
        monitoring.removeBusMoveInfo = function(){
            core.control.removeCustomSource(LAYER.BUS);
            core.control.removeCustomSource(LAYER.BUS_ROUTE);
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
                updown : "updown",
                jobId: core.control.generateJobId("버스 노선 정보 로딩중")
            });
        }

        monitoring.focusBus = function(routeId, companyId, plateNo){
            let focusId = routeId+"_"+plateNo+"_"+companyId;
            let bus = m_bus_features.filter((feature) => feature.properties.id === focusId);
            if(bus.length === 0) {
                alert("맵상에 해당 노선정보가 존재하지 않습니다.");
                return;
            }
            core.control.moveMap(bus[0].geometry.coordinates, 16);
            _Map.setPaintProperty(LAYER.BUS, 'icon-opacity', [
                "case",
                ['==', ['get',"id"], focusId],
                1,
                0
            ]);
            core.control.generateFilterReset("버스 실시간위치 투명도 초기화", LAYER.BUS, 'icon-opacity', 1);
            let x = bus[0].geometry.coordinates[0];
            let y = bus[0].geometry.coordinates[1];
            /*setTimeout(function(){
                const coords = new mapboxgl.LngLat(x, y);
                const point = _Map.project(coords);
                _Map.fire('click', {lngLat : coords});
            },2000);*/

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
                loopTime : 60000*5,
                jobId : core.control.generateJobId("돌발현황 레이어 로딩중")
            });
            _Worker.postMessage({
                event : "CM_WARNING_ALARM",
                loop : true,
                loopTime : 60000*5
            });
        }
        let warningInfoTodyAjax = null;
        monitoring.getWarningInfoToday = async function(){
            if(warningInfoTodyAjax !== null) warningInfoTodyAjax.abort();
            return new Promise(function(resolve, reject){
                if($("#m_warning_chart_wrap").length === 0) {
                    reject();
                    return;
                }
                warningInfoTodyAjax = $.ajax({
                    url : __contextPath__ + "/monitoring/getWarningInfoToday.ajax",
                    type : "get",
                    beforeSend : function(){
                        $("#m_warning_chart_wrap.has-preloading .chart-preloading-wrap").remove();
                        $("#m_warning_chart_wrap.has-preloading").append(GITS_ENV.UI.CHART_PRELOADING());
                    },
                    error : function(){
                        $("#m_warning_chart_wrap.has-preloading .chart-preloading-wrap").remove();
                    },
                    success : function(list) {
                        $("#m_warning_chart_wrap.has-preloading .chart-preloading-wrap").remove();
                        $("#m_warning_chart").remove();
                        $("#m_warning_chart_wrap").html(`<canvas id="m_warning_chart" width="400" height="250"></canvas>`);

                        /*데이터 가공*/
                        let srcOrg = [
                            "GITS","UTIC","SK","EX","119","SISUL"
                        ];
                        let srcOrgNm = [
                            "경기도교통센터","도로교통공단","T-MAP","도로공사","경기소방본부","경기도 내 터널"
                        ];
                        let srcOrgTypeCnts = list.reduce(function(prev, cur){
                            prev[cur.inciCate][srcOrg.indexOf(cur.infoSrcOrg)] += 1;
                            return prev;
                        }, {
                            "1" : [0,0,0,0,0,0],
                            "2" : [0,0,0,0,0,0],
                            "3" : [0,0,0,0,0,0],
                            "4" : [0,0,0,0,0,0],
                            "5" : [0,0,0,0,0,0],
                            "6" : [0,0,0,0,0,0],
                            "7" : [0,0,0,0,0,0],
                            "8" : [0,0,0,0,0,0],
                            "9" : [0,0,0,0,0,0],
                            "10" : [0,0,0,0,0,0],
                            "11" : [0,0,0,0,0,0],
                            "12" : [0,0,0,0,0,0],
                            "13" : [0,0,0,0,0,0],
                        });
                        const util = new GitsMapUtil();
                        const warningDataSetArray = [];
                        for(const inciCate in srcOrgTypeCnts) {
                            warningDataSetArray.push({
                                label : GITS_ENV.INCI_CATE[inciCate],
                                data : srcOrgTypeCnts[inciCate],
                                backgroundColor: util.hexToRgbA(GITS_ENV.INCI_COLOR[inciCate],0.6),
                                borderColor : GITS_ENV.INCI_COLOR[inciCate],
                                borderWidth : 1,
                                borderRadius:1,
                                fill: true
                            })
                        }
                        new GITSChart(GITSChartType.BAR).init("m_warning_chart")
                            .setDataSetArrayLabel(srcOrgNm)
                            .setDataArraySet(warningDataSetArray)
                            .setOption({
                                indexAxis: 'x',
                                plugins: {
                                    legend: {
                                        usePointStyle:false,
                                        labels: {
                                            color:"#fff",
                                            padding:10,
                                            boxWidth:5,
                                            font: {
                                                family:'Pretendard',
                                                size:11,
                                            }
                                        },
                                    },
                                    datalabels: {
                                        anchor: 'middle', // remove this line to get label in middle of the bar
                                        align: 'end',
                                        clamp : true,
                                        formatter: function(val) {
                                            return numberComma(val);
                                        },
                                        labels: {
                                            value: {
                                                color: '#fff'
                                            }
                                        }
                                    }
                                },
                                interaction: {
                                    mode: 'index',
                                    axis: 'x',
                                    intersect: false
                                },
                                scales : {
                                    x : {
                                        ticks : {
                                            autoSkip : false,
                                            color:"white",
                                            font: {
                                                size : 11
                                            }
                                        }
                                    },
                                    y : {
                                        ticks : {
                                            autoSkip : false,
                                            color:"white",
                                            font: {
                                                size : 11
                                            },
                                            stepSize: 20
                                        },
                                        grid: {
                                            color : "rgba(255,255,255,0.2)",
                                            tickColor : "rgba(255,255,255,0.2)",
                                            display:true
                                        }
                                    }
                                }
                            })
                            .draw();
                        resolve(list);
                    },
                    error : function() {
                        reject();
                    },
                    complete : function(){
                        warningInfoTodyAjax = null;
                    }
                })
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
        monitoring.getTrafficInfoMinimize = function(){
            gitsRoadData.event = "M_TRAFFIC_MINIMIZE";
            gitsRoadData.loop = false;
            _Worker.postMessage(gitsRoadData);
        }

        monitoring.getTrafficInfo = function(loop = false, loopTime = 5000){
            gitsRoadData.event = "M_TRAFFIC";
            gitsRoadData.loop = true;
            gitsRoadData.loopTime = loopTime;
            gitsRoadData.jobId = core.control.generateJobId("실시간 교통현황 로딩중");
            _Worker.postMessage(gitsRoadData);
        }

        /**
         * 실시간 스마트교차로 교통량
         * @param loop
         * @param looptime
         */
        monitoring.getTrafficVolumeSmart = function(loop = false, loopTime = 60000){
            gitsRoadData.event = "M_VOLUME_SMART";
            gitsRoadData.loop = loop;
            gitsRoadData.loopTime = loopTime;
            gitsRoadData.jobId = core.control.generateJobId("실시간 스마트교차로 교통량 로딩중");
            _Worker.postMessage(gitsRoadData);
        }

        monitoring.removeTrafficVolumeSmart = function(){
            core.control.removeCustomSource(LAYER.M_TRAFFIC_VOLUME_SMART_CLUSTER);
            core.control.removeCustomSource(LAYER.M_TRAFFIC_VOLUME_SMART_DRCT);
            stopWorkerInterval("M_VOLUME_SMART");
        }

        /**
         * 실시간 DSRC 교통량
         * @param loop
         * @param looptime
         */
        monitoring.getTrafficVolumeDSRC = function(loop = false, loopTime = 60000){
            gitsRoadData.event = "M_VOLUME_DSRC";
            gitsRoadData.loop = loop;
            gitsRoadData.loopTime = loopTime;
            gitsRoadData.jobId = core.control.generateJobId("실시간 DSRC 교통량 로딩중");
            _Worker.postMessage(gitsRoadData);
        }

        /**
         * 실시간 DSRC 교통량
         * @param loop
         * @param looptime
         */
        monitoring.getTrafficVolumeVDS = function(loop = false, loopTime = 60000){
            gitsRoadData.event = "M_VOLUME_VDS";
            gitsRoadData.loop = loop;
            gitsRoadData.loopTime = loopTime;
            gitsRoadData.jobId = core.control.generateJobId("실시간 VDS 교통량 로딩중");
            _Worker.postMessage(gitsRoadData);
        }

        monitoring.removeTrafficVolumeVDS = function(){
            core.control.removeCustomSource(LAYER.M_TRAFFIC_VOLUME_VDS_CLUSTER);
            stopWorkerInterval("M_VOLUME_VDS");
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
        monitoring.getWeatherInfo = function(loop = true, loopTime = 30000){
            let weatherData = {};
            weatherData.event = "M_WEATHER";
            weatherData.loop = loop;
            weatherData.loopTime = loopTime;
            weatherData.jobId = core.control.generateJobId("기상현황 로딩중");
			$("#weatherWarningDiv").removeClass('none');
            _Worker.postMessage(weatherData);
        }

        /**
         * 날씨정보 제거
         */
        monitoring.removeWeatherInfo = function(){
			$("#weatherWarningDiv").addClass('none');
            core.control.removeCustomSource(LAYER.GRID_WEATHER);
            stopWorkerInterval("M_WEATHER");
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
    let MapEvents = {
        "CHECK_TOP_LAYER" : function(e, current_layer) {
            let bool = false;
            let clickedFeatures = _Map.queryRenderedFeatures(e.point);
            if(clickedFeatures[0]?.layer.id === current_layer) bool = true;
            return bool
        },
        "TEST" : function(e) {
            const feature = e.features[0];
            const prop = e.features[0].properties;
            console.log("feature", feature);
            console.log("prop", prop);
        },
        "M_TRAFFIC" : function(e) {
            const feature = e.features[0];
            const prop = e.features[0].properties;
            const state = e.features[0].state;
            if(!MapEvents.CHECK_TOP_LAYER(e, LAYER.LINK)) return;

            /*if(!state.conggrade) {
                return;
            }*/

            $.ajax({
                type :"get",
                url : __contextPath__+"/basicinfo/nodelink/getLinkInfo.ajax?linkId="+prop.LINK_ID,
                beforeSend : function(){
                    gitsApp.startLoading();
                },
                success: function (link) {
                    if(link == null) {
                        alert("링크정보가 존재하지 않습니다.");
                        return;
                    }
                    _Map.setPaintProperty(LAYER.LINK, 'line-opacity', [
                        "case",
                        ['==', ['get',"LINK_ID"], prop.LINK_ID],
                        1,
                        0.3
                    ]);
                    core.control.generateFilterReset("소통정보 투명도 초기화", LAYER.LINK, 'line-opacity', 1);
                    let html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p class="mt8 mb8">${link.roadName}(${link.linkId})</p>
                                    <ul class="data_sub mt16">
                                    <li class="popup_item">수집일 : <span>${state.colldate ? _Util.timestampToDate(state.colldate) : "-"}</span></li>
                                    <li class="popup_item">속도 : <span>${numberComma(state ? state.spd : 0)} km/h</span></li>
                                    <li class="popup_item">혼잡도 : <span>${state.conggrade ? _Util.conggradeCodeToKor(state.conggrade) : "데이터미제공"}</span></li>
                                    <li class="popup_item">도로타입 : ${GITS_ENV.ROAD_RANK[link.roadRank]}</li>
                                    <li class="popup_item">시작노드 : ${link.fNodeNm}(${link.fNode})</li>
                                    <li class="popup_item">종료노드 : ${link.tNodeNm}(${link.tNode})</li>
                                </ul>
                                </div>
                            `;
                    let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                        .setLngLat(e.lngLat)
                        .setHTML(html)
                        .addTo(_Map)
                },
                complete : function(){
                    gitsApp.endLoading();
                }
            })
        },
        "BD_TRFVLM_ORDTM" : function(e){
            if(!MapEvents.CHECK_TOP_LAYER(e, LAYER.BD_TRAFFIC_VOLUME_ORDTM_LNRD+"_none_text")) return;
            const prop = e.features[0].properties;
            new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.features[0].geometry.coordinates)
                .setHTML(`
                        <div class="data_popup" >
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
							<p class="mt8 mb8">${prop.sectionNm}</p>
                            <ul class="data_sub mt16">
                                <li class="popup_item">전체교통량 : <span>${numberComma(prop.trfVol)}대</span></li>
                                <li class="popup_item">상행(교통량) : <span>${prop.upSection}(${numberComma(prop.trfVolUp)}대)</span></li>
                                <li class="popup_item">하행(교통량) : <span>${prop.downSection}(${numberComma(prop.trfVolDown)}대)</span></li>
                                <li class="popup_item">차로수 : <span>${prop.laneNo}</span></li>
                                <li class="popup_item">분기점 : <span>${prop.turnpoint}</span></li>
                            </ul>
                        </div>
                    `)
                .addTo(_Map);
        },
        "BD_TRFVLM_ANYTM_NLRD" : function(e){
            if(!MapEvents.CHECK_TOP_LAYER(e, LAYER.BD_TRAFFIC_VOLUME_ANYTM_LNRD+"_none_text")) return;
            const prop = e.features[0].properties;
            new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.features[0].geometry.coordinates)
                .setHTML(`
                        <div class="data_popup" >
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
							<p class="mt8 mb8">${prop.sectionNm}</p>
                            <ul class="data_sub mt16">
                                <li class="popup_item">전체교통량 : <span>${numberComma(prop.trfVol)}대</span></li>
                                <li class="popup_item">상행(교통량) : <span>${prop.upSection}(${numberComma(prop.trfVolUp)}대)</span></li>
                                <li class="popup_item">하행(교통량) : <span>${prop.downSection}(${numberComma(prop.trfVolDown)}대)</span></li>
                                <li class="popup_item">차로수 : <span>${prop.laneNo}</span></li>
                                <li class="popup_item">분기점 : <span>${prop.turnpoint}</span></li>
                            </ul>
                        </div>
                    `)
                .addTo(_Map);
        },
        "BD_TRFVLM_ANYTM_HGHW" : function(e){
            if(!MapEvents.CHECK_TOP_LAYER(e, LAYER.BD_TRAFFIC_VOLUME_ANYTM_HGHW+"_none_text")) return;
            const prop = e.features[0].properties;
            new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.features[0].geometry.coordinates)
                .setHTML(`
                        <div class="data_popup" >
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
							<p class="mt8 mb8">${prop.lineNoNm}</p>
                            <ul class="data_sub mt16">
                                <li class="popup_item">구간 : <span>${prop.section}</span></li>
                                <li class="popup_item">전체교통량 : <span>${numberComma(prop.trfVol)}대</span></li>
                                <li class="popup_item">상행(교통량) : <span>${prop.upSection}(${numberComma(prop.trfVolUp)}대)</span></li>
                                <li class="popup_item">하행(교통량) : <span>${prop.downSection}(${numberComma(prop.trfVolDown)}대)</span></li>
                                <li class="popup_item">차로수 : <span>${prop.laneNo}</span></li>
                                <li class="popup_item">구간길이(km) : <span>${prop.sectlength}</span></li>
                            </ul>
                        </div>
                    `)
                .addTo(_Map);
        },
        "M_VOLUME_SMART" : function(e){
            const prop = e.features[0].properties
            new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.features[0].geometry.coordinates)
                .setHTML(`
                        <div class="data_popup" >
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                            <div class="crsrd-viewing-map">
                                <img src="${__contextPath__}/statics/images/direction.png" alt="교차로" class="crsrd-viewing-road">
                                <p class="crsrd-road-left">[좌회전/유턴]<br/>${numberComma(prop.trnlftTrfvlm)}/${numberComma(prop.trnutrnTrfvlm)} 대</p>
                                <p class="crsrd-road-strgt">[직진]<br/>${numberComma(prop.strghtTrfvlm)} 대</p>
                                <p class="crsrd-road-right">[우회전]<br/>${numberComma(prop.trnghtTrfvlm)} 대</p>
                            </div>
                            <table class="popup_table">
                    <thead class="mapbox_tablehead">
                    <th>교차로명</th>
                    <th>접근로명</th>
                    <th>전체교통량</th>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${prop.crsrdNm}</td>
                        <td>${prop.acsRoadNm}</td>
                        <td>${numberComma(prop.trfvlmTotal)} 대</td>
                    </tr>
                    </tbody>
                    </table>
                        </div>
                    `)
                .addTo(_Map);
        },
        "BD_PREDICTION_CROSS_TRAFFIC" : function(e){
            const prop = e.features[0].properties
            new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.features[0].geometry.coordinates)
                .setHTML(`
                        <div class="data_popup" >
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                            <div class="crsrd-viewing-map">
                                <img src="${__contextPath__}/statics/images/direction.png" alt="교차로" class="crsrd-viewing-road">
                                <p class="crsrd-road-left">[좌회전]<br/>${numberComma(prop.trnlftTrfvlm)} 대</p>
                                <p class="crsrd-road-strgt">[직진]<br/>${numberComma(prop.strghtTrfvlm)} 대</p>
                                <p class="crsrd-road-right">[우회전]<br/>${numberComma(prop.trnghtTrfvlm)} 대</p>
                            </div>
                            <table class="popup_table">
                    <thead class="mapbox_tablehead">
                    <th>교차로명</th>
                    <th>접근로명</th>
                    <th>전체교통량</th>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${prop.crsrdNm}</td>
                        <td>${prop.acsRoadNm}</td>
                        <td>${numberComma(prop.trfvlmTotal)} 대</td>
                    </tr>
                    </tbody>
                    </table>
                        </div>
                    `)
                .addTo(_Map);
        },
        "M_BUS" : function(e) {
            if(!MapEvents.CHECK_TOP_LAYER(e, LAYER.BUS)) return false;
            const prop = e.features[0].properties;
            let html = `
                                <div class="data_popup" >
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <ul class="data_sub mt16">
                                        <li class="popup_item">노선번호 : <span>${prop.routeNm}</span></li>
                                        <li class="popup_item">차량번호 : <span>${prop.plateNo}</span></li>
                                        <li class="popup_item">회사명 : <span>${prop.companyNm}</span></li>
                                    </ul>
                                </div>
                            `;
            /*
            * <li class="popup_item">총좌석수 : <span>${prop.totalseatCnt ? numberComma(prop.totalseatCnt)+"개" : "데이터미제공"}</span></li>
                                        <li class="popup_item">승차인원 : <span>${prop.passengerCnt ? numberComma(prop.passengerCnt)+"명" : "데이터미제공"}</span></li>
                                        <li class="popup_item">남은좌석수 : <span>${prop.remainseatCnt ? numberComma(prop.remainseatCnt)+"개" : "데이터미제공"}</span></li>
            * */
            core.monitoring.getBusRouteInfo(prop.routeId);
            core.monitoring.getBusStationInfo(false , "routeId="+prop.routeId);
            _Map.setPaintProperty(LAYER.BUS, 'icon-opacity', [
                "case",
                ['==', ['get',"routeId"], prop.routeId],
                1,
                0
            ]);
            core.control.generateFilterReset("버스 실시간위치 투명도 초기화", LAYER.BUS, 'icon-opacity', 1);
            let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.lngLat)
                .setHTML(html)
                .addTo(_Map);
        },
        "DANGER_CAR" : function(e) {
            if(!MapEvents.CHECK_TOP_LAYER(e, LAYER.DANGER)) return false;
            const prop = e.features[0].properties;
            if(prop.type && prop.type === "danger") {
                let html = `
                                <div class="data_popup" >
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                                    <img src="${__contextPath__}/statics/images/danger.png" style="width:40px;">
                                    <ul class="data_sub mt16">
                                        ${prop.description}
                                    </ul>
                                </div>
                            `;
                let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                    .setLngLat(e.lngLat)
                    .setHTML(html)
                    .addTo(_Map)
            }
        },
        "CM_BUS_ROUTE" : function(e){
            const prop = e.features[0].properties;
            let html = `
                                <div class="data_popup" >
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
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
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
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
								<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
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
            if(!MapEvents.CHECK_TOP_LAYER(e, LAYER.FACILITY_DSRC)) return false;
            const prop = e.features[0].properties;
            const feature = e.features[0];
            $.ajax({
                type: "get",
                url: __contextPath__+"/facility/getDSRCCollectList.ajax",
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
                <th>방향</th>
                <th>구간명</th>
                <th>구간길이</th>
                <th>일평균속도</th>
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
                    <td>${numberComma(colctInfo.dsrcSctnLen)}</td>
                    <td>${colctInfo.speed} km/h</td>
                </tr>`;
                    }
                    table += "</tbody></table></div>";
                    let html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
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
                <th>일일평균속도</th>
                </thead>
                <tbody>
                `;
            for(feature of features) {
                table += `
                <tr>
                    <td>${feature.properties.dsrcSctnId}</td>
                    <td>${feature.properties.dsrcSctnNm ? feature.properties.dsrcSctnNm : "구간명없음"}</td>
                    <td>${feature.properties.speed ? feature.properties.speed+"km/h" : "데이터 미수집"} </td>
                </tr>`;
            }
            table += "</tbody></table>";
            let html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
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
            if(!MapEvents.CHECK_TOP_LAYER(e, LAYER.FACILITY_SMART)) return false;
            const prop = e.features[0].properties;
            let html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p class="mt8 mb8">${prop.crsrdNm}(${prop.crsrdId})</p>
									<ul>
	                                    <li class="popup_item">최대수용교통량 : <span>${numberComma(prop.maxTrfvlm)}대</span></li>
	                                    <li class="popup_item">최대수용보행자수 : <span>${numberComma(prop.maxPdstCnt)}명</span></li>
	                                    <li class="popup_item">최근교통량(1시간) : <span>${numberComma(prop.vhclTrfvlm)}대</span></li>
	                                    <li class="popup_item">최근보행자수(1시간) : <span>${numberComma(prop.pdstCnt)}명</span></li>
	                                    <li class="popup_item">최근평균차량속도 : <span>${prop.avgVhclSpeed ? Math.floor(prop.avgVhclSpeed) : 0} km/h</span></li>
	                                    <li class="popup_item">최근평균보행자속도 : <span>${prop.avgPdstSpeed ? Math.floor(prop.avgPdstSpeed) : 0} km/h</span></li>
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
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
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
            if(!MapEvents.CHECK_TOP_LAYER(e, LAYER.FACILITY_VDS)) return false;
            const prop = e.features[0].properties;
            const feature = e.features[0];
            $.ajax({
                type: "get",
                url: __contextPath__+"/facility/getVDSCollectList.ajax",
                data: {
                    vdsId: prop.vdsId,
                    mngInstCd : prop.mngInstCd
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
                <th>최근평균속도(1시간)</th>
                <th>최근교통량(1시간)</th>
                </thead>
                <tbody>
                `;
                    for(colctInfo of colctInfoList) {
                        table += `
                <tr>
                    <td>${colctInfo.laneNo}차로</td>
                    <td>${Math.round(colctInfo.avgSpeed)} km/h</td>
                    <td>${colctInfo.trfvlm}대</td>
                </tr>`;
                    }
                    table += "</tbody></table></div>";
                    let html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
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
            if(!MapEvents.CHECK_TOP_LAYER(e, LAYER.DANGER)) return false;
            const prop = e.features[0].properties;
            new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.lngLat)
                .setHTML(`
                    <div class="data_popup" >
						<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                        <img src="${__contextPath__}/statics/images/danger.png" style="width:40px;">
                        <ul class="data_sub mt16" style="max-width:300px;">
                            ${prop.description}
                        </ul>	
                    </div>
                `)
                .addTo(_Map)
        },
        "M_EMERGENCY" : function(e){
            const prop = e.features[0].properties;
            if(!MapEvents.CHECK_TOP_LAYER(e, LAYER.EMERGENCY)) return false;
            if(!prop.description) return false;
            let emergencyPopup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.lngLat)
                .setHTML(`
                    <div class="data_popup" >
						<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                        <i class="map_icon bus_icon data_icon"></i>
                        <ul class="data_sub mt16">
                            ${prop.description}
                        </ul>	
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
            if(typeof prop.sggNm === "undefined") {
                return;
            }
            let routeTable = `<div class="popup_scroll">
                    <table class="popup_table">
                    <thead>
                    <th>시간대</th>
                    <th>예측유동인구</th>
                    <th>엑셀다운로드</th>
                    </thead>
                    <tbody>
                    `;
            for(let i = 0; i < 24; i++){
                let hh = i< 10 ? "0"+i : i;
                routeTable += `
                        <tr>
                            <th>${hh}시</th>
                            <td>${typeof prop["fltPop_"+hh] !== "undefined" ? (prop["fltPop_"+hh] < 0 ? "0" : numberComma(prop["fltPop_"+hh]))+"명" : "데이터미제공"}</td>
                            <td><button type="button" class="is-darkgreen-btn radius" onclick="window.map.bigdata.getPopulationExcelDownload(${hh})">${hh}시간대 엑셀다운로드</button></td>
                        </tr>`;
            }
            routeTable += "</tbody></table></div>";
            new mapboxgl.Popup({maxWidth : "none"})
                .setLngLat(e.lngLat)
                .setHTML(`
                        <div class="data_popup">
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                            <i class="map_icon bus_icon data_icon"></i>
                            <p>${prop.sggNm} (${prop.adstsdgNm ? prop.adstsdgNm : "-"})</p>
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
								<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
	                            <img src="${__contextPath__}/statics/images/danger_road_${prop.icon}.png" style="width:40px;">
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
                ,pointNm : "지역"
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
								<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
	                            <img src="${__contextPath__}/statics/images/accident_${e.features[0].properties.type}.png" style="width:40px;">
								<div class="data_popup_scroll">
		                            <ul class="data_sub mt16">
										${html}
		                            </ul>
								</div>
	                        </div>
                    `)
                .addTo(_Map)
        },
        "BD_PATTERN_TRAFFIC_ABN_LOS" : function(e){
            const prop = e.features[0].properties;
            $.ajax({
                type :"get",
                url : __contextPath__+"/basicinfo/nodelink/getLinkInfo.ajax?linkId="+prop.linkId,
                beforeSend : function(){
                    gitsApp.startLoading();
                },
                success: function (link) {
                    _Map.setPaintProperty(LAYER.BD_PATTERN, 'line-opacity', [
                        "case",
                        ['==', ['get',"linkId"], link.linkId],
                        1,
                        0.3
                    ]);
                    let grade = '원활';
                    switch(prop.conggrade) {
                        case "1" :
                            grade = '원활';
                            break;
                        case "2" :
                            grade = '서행';
                            break;
                        case "3" :
                            grade = '정체';
                            break;
                    }
                    core.control.generateFilterReset("도로 투명도 초기화", LAYER.BD_PATTERN, 'line-opacity', 1);
                    let html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p class="mt8 mb8">${link.roadName}(${link.linkId})</p>
                                    <ul class="data_sub mt16">
                                    <li class="popup_item">도로타입 : ${GITS_ENV.ROAD_RANK[link.roadRank]}</li>
                                    <li class="popup_item">평균속도 : ${Math.round(prop.avgVhclSpeed)}km/h</li>
                                    <li class="popup_item">소통등급 : ${grade}</li>
                                    <li class="popup_item">시작노드 : ${link.fNodeNm}(${link.fNode})</li>
                                    <li class="popup_item">종료노드 : ${link.tNodeNm}(${link.tNode})</li>
                                </ul>
                                </div>
                            `;
                    let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                        .setLngLat(e.lngLat)
                        .setHTML(html)
                        .addTo(_Map)
                },
                complete : function(){
                    gitsApp.endLoading();
                }
            });
        },
        "BD_PATTERN_TRAFFIC_CNGSTN" : function(e) {
            const prop = e.features[0].properties;
            let html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p class="mt8 mb8">${prop.roadNm}</p>
                                    <ul class="data_sub mt16">
                                    <li class="popup_item">도로타입 : ${GITS_ENV.ROAD_RANK[prop.roadRank]}</li>
                                    <li class="popup_item">구간 : ${prop.startNm}->${prop.endNm}</li>                                    
                                    <li class="popup_item">구간길이 : ${prop.linkLen}m</li>`;
            if(prop.routeDrct) {
                html += `<li class="popup_item">방면 : ${prop.routeDrct}</li>
                        <li class="popup_item">평균속도 : ${prop.avgSpeed}km/h</li>
                        <li class="popup_item">평균정체시간 : ${prop.avgCngstnTime}</li>`;
            }
            html += `</ul> </div>
                            `;
            let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                .setLngLat(e.lngLat)
                .setHTML(html)
                .addTo(_Map)
        },
        "BD_PATTERN_TRAFFIC_QUANTITY" : function(e){
            const prop = e.features[0].properties;
            const state = e.features[0].state;
            $.ajax({
                type :"get",
                url : __contextPath__+"/basicinfo/nodelink/getLinkInfo.ajax?linkId="+prop.linkId,
                beforeSend : function(){
                    gitsApp.startLoading();
                },
                success: function (link) {
                    /*if(link == null) {
                        alert("링크정보가 존재하지 않습니다.");
                        return;
                    }*/
                    _Map.setPaintProperty(LAYER.BD_PATTERN, 'line-opacity', [
                        "case",
                        ['==', ['get',"linkId"], link.linkId],
                        1,
                        0.3
                    ]);
                    core.control.generateFilterReset("도로 투명도 초기화", LAYER.BD_PATTERN, 'line-opacity', 1);
                    let html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p class="mt8 mb8">${link.roadName}(${link.linkId})</p>
                                    <ul class="data_sub mt16">
                                    ${prop.addLabel}
                                    <li class="popup_item">도로타입 : ${GITS_ENV.ROAD_RANK[link.roadRank]}</li>
                                    <li class="popup_item">시작노드 : ${link.fNodeNm}(${link.fNode})</li>
                                    <li class="popup_item">종료노드 : ${link.tNodeNm}(${link.tNode})</li>
                                </ul>
                                </div>
                            `;
                    let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                        .setLngLat(e.lngLat)
                        .setHTML(html)
                        .addTo(_Map)
                },
                complete : function(){
                    gitsApp.endLoading();
                }
            });
        },
        "BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS_MERGE" : function(e){
            const prop = e.features[0].properties;
            let html = `<div class="data_popup" style="width:15.7rem;">
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">`;
            html += `<li class="popup_item">링크아이디 : ${prop.linkId}</li>`;
            html += `<li class="popup_item">도로명 : ${prop.roadName}</li>`;
            html += `<li class="popup_item">도로타입 : ${GITS_ENV.ROAD_RANK[prop.roadRank]}</li>`;
            html += `<li class="popup_item">개선 평균속도 : ${prop.avgVhclSpeedAvg} km/h</li>`;
            html += `<li class="popup_item">개선 누적통행량 : ${numberComma(prop.vhclTrfvlmTotal)} 대</li>`;
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
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">`;
            html += `<li class="popup_item">링크아이디 : ${prop.linkId}</li>`;
            html += `<li class="popup_item">도로명 : ${prop.roadName}</li>`;
            html += `<li class="popup_item">도로타입 : ${GITS_ENV.ROAD_RANK[prop.roadRank]}</li>`;
            html += `<li class="popup_item">평균 속도 : ${prop.avgVhclSpeedAvg ? prop.avgVhclSpeedAvg.toFixed(0) : 0} km/h</li>`;
            if(prop.vhclTrfvlmTotal) {
                html += `<li class="popup_item">누적통행량 : ${numberComma(prop.vhclTrfvlmTotal)} 대</li>`;
            }
            _Map.setPaintProperty(LAYER.BD_EFFECT, 'line-opacity', [
                "case",
                ['==', ['get',"linkId"], prop.linkId],
                1,
                0.3
            ]);
            core.control.generateFilterReset("링크 투명도 초기화", LAYER.BD_EFFECT, 'line-opacity', 1);

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
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
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
            `;
            if(prop.riskJugCnt){
                html += `<li class="popup_item">위험판정수 : ${numberComma(prop.riskJugCnt)}</li>
             <li class="popup_item">과속운행수 : ${numberComma(prop.spdngRungCnt)}</li>
             <li class="popup_item">급가속 운행 수 : ${numberComma(prop.sdacelRungCnt)}</li>
             <li class="popup_item">급감속 운행 수 : ${numberComma(prop.rpddclRungCnt)}</li>
             <li class="popup_item">급정지 운행 수 : ${numberComma(prop.sdstopRungCnt)}</li>
             <li class="popup_item">급출발 운행 수 : ${numberComma(prop.sdstrtRungCnt)}</li>
             <li class="popup_item">장기과속 운행 수 : ${numberComma(prop.lngtrmaclRungCnt)}</li>`;
            }
            new mapboxgl.Popup({offset : [0, -15]})
                .setLngLat(e.lngLat)
                .setHTML(`
                        <div class="data_popup">
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
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
            $.ajax({
                type :"get",
                url : __contextPath__+"/basicinfo/nodelink/getLinkInfo.ajax?linkId="+prop.LINK_ID,
                beforeSend : function(){
                    gitsApp.startLoading();
                },
                success: function (link) {
                    /*if(link == null) {
                        alert("링크정보가 존재하지 않습니다.");
                        return;
                    }*/
                    _Map.setPaintProperty(LAYER.LINK, 'line-opacity', [
                        "case",
                        ['==', ['get',"LINK_ID"], link.linkId],
                        1,
                        0.3
                    ]);
                    core.control.generateFilterReset("도로 투명도 초기화", LAYER.LINK, 'line-opacity', 1);
                    let html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p class="mt8 mb8">${link.roadName}(${link.linkId})</p>
                                    <ul class="data_sub mt16">
                                    <li class="popup_item">속도 : ${state.speed} km/h</li>
                                    <li class="popup_item">안전지수 : ${state.safeIdex}</li>
                                    <li class="popup_item">안전등급 : ${grd}</li>
                                    <li class="popup_item">도로타입 : ${GITS_ENV.ROAD_RANK[link.roadRank]}</li>
                                    <li class="popup_item">시작노드 : ${link.fNodeNm}(${link.fNode})</li>
                                    <li class="popup_item">종료노드 : ${link.tNodeNm}(${link.tNode})</li>
                                </ul>
                                </div>
                            `;
                    let popup = new mapboxgl.Popup({offset : [0, -15], maxWidth : "none"})
                        .setLngLat(e.lngLat)
                        .setHTML(html)
                        .addTo(_Map)
                },
                complete : function(){
                    gitsApp.endLoading();
                }
            });
        },
        "BD_PUBLIC_TRANSFER_CNDCY_ROUTE" : function(e) {
            const prop = e.features[0].properties;
        },
        "BD_PUBLIC_TRANSFER_USAGE_BY_ST_END" : function(e) {
            const prop = e.features[e.features.length - 1].properties;
            _Map.setPaintProperty(LAYER.BD_PUBLIC_TRANSFER_USAGE_BY_ST_END, 'line-opacity', [
                "case",
                ['==', ['get',"linkId"], prop.linkId],
                1,
                0.1
            ]);
            core.control.generateFilterReset("도로 투명도 초기화", LAYER.BD_PUBLIC_TRANSFER_USAGE_BY_ST_END, 'line-opacity', 1);
            let html = `<div class="data_popup">
							<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                            <i class="map_icon bus_icon data_icon"></i>
                            <ul class="data_sub mt16">`;
            html += `<li class="popup_item">노선번호 : ${prop.routeNm}</li>`;
            html += `<li class="popup_item">구간 : ${prop.stStationNm} - ${prop.edStationNm}</li>`;
            html += `<li class="popup_item">도로명 : ${prop.roadName}</li>`;
            html += `<li class="popup_item">버스사용자수 : ${prop.busUserCnt ? numberComma(prop.busUserCnt)+"명" : "데이터미제공"}</li>`;
            html += `<li class="popup_item">승객수 : ${prop.psgrCnt ? numberComma(prop.psgrCnt)+"명" : "데이터미제공"}</li>`;
            html += `<li class="popup_item">환승수 : ${prop.trsfrCnt ? numberComma(prop.trsfrCnt)+"명" : "데이터미제공"}</li>`;
            html += `<ul></div>`;
            new mapboxgl.Popup()
                .setLngLat(e.lngLat)
                .setHTML(html)
                .addTo(_Map)
        },
        "BD_BUS_BIT_STATION" : function(e) {
            const prop = e.features[0].properties;
            if(!selectedSearchOption["BD_BUS_BIT_STATION"]) {
                return;
            }
            let ajaxData = $.extend(selectedSearchOption["BD_BUS_BIT_STATION"], {edStationId:prop.stationId}, {});
            $.ajax({
                type : "get",
                url : __contextPath__+"/bigdata/getPublicTransferBIT.ajax",
                data : ajaxData,
                beforeSend : function(){
                    gitsApp.startLoading();
                },
                complete : function(){
                    gitsApp.endLoading();
                },
                success : function(list){
				let viewDateLength = 7;
				let selectStartDtStr = selectedSearchOption["BD_BUS_BIT_STATION"].startDate;
				
				let busStationDateSelectHtml = '<select id="busArvlInfoPreditionDtSelect" class="mapbox_popup_select">';
					for(let i = 0; i < viewDateLength; i++) {
						let selectStartDtForDate = new Date(selectStartDtStr);
						selectStartDtForDate.setDate(selectStartDtForDate.getDate()-i);
						
						let selectStartDtForStr = dateToStringFormat(selectStartDtForDate,'-');
						
						busStationDateSelectHtml += '<option value="'+selectStartDtForStr+'">'+selectStartDtForStr+'</option>';
					}
				busStationDateSelectHtml += '</select>';
				
                    let routeTable = `<div class="popup_scroll">
                    <table class="popup_table">
                    <thead class="mapbox_tablehead">
                    <th>회차</th>
                    <th>분석갱신시간</th>
                    <th>예상도착시간</th>
                    <th>실제도착시간</th>
                    <th>시간차이</th>
                    </thead>
                    <tbody id="busArvlInfoPreditionTbody">
                    `;
                    list = list.sort(function(a, b){
                        return parseInt(a.operSeq) - parseInt(b.operSeq);
                    });
                    for(const data of list){
                        let timeDff = '-';
                        let isNegative = false;
                        if(data.arrTm && data.realArrTm) {
                            let time1 = data.arrTm;
                            let time2 = data.realArrTm;
                            let date1 = new Date(`2000-01-01T${time1}Z`);
                            let date2 = new Date(`2000-01-01T${time2}Z`);
                            let diff = null;
                            if (date2 < date1) {
                                diff = date1 - date2;
                                isNegative = true;
                            }else{
                                diff = date2 - date1
                            }
                            let ss = Math.floor(diff / 1000) % 60;
                            let mm = Math.floor(diff / 1000 / 60) % 60;
                            let hh = Math.floor(diff / 1000 / 60 / 60);
                            timeDff = `${mm}:${ss}`;
                        }
                        routeTable += `
                        <tr>
                            <td>${data.operSeq}</td>
                            <td>${data.updateTm}</td>
                            <td>${data.arrTm}</td>
                            <td>${data.realArrTm ? data.realArrTm : '-'}</td>
                            <td style="${isNegative ? 'color:#0FB050' : 'color:#C00000' }">${isNegative ? '-':''}${timeDff}</td>
                        </tr>`;
                    }
                    routeTable += "</tbody></table></div>";
                    let html = `
                                <div class="data_popup" >
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p>${prop.stationNm}(${prop.mobileNo ?? prop.stationId})</p>
									<div>${busStationDateSelectHtml}</div>
                                    ${routeTable}
                                </div>
                            `;
                    let popup = new mapboxgl.Popup({offset : [0, -30], maxWidth : "none"})
                        .setLngLat(e.lngLat)
                        .setHTML(html)
                        .addTo(_Map)

					$("#busArvlInfoPreditionDtSelect").on('change', function(){
						let busArvlInfoPreditionDtSelectDt = $(this).val();
						let newAjaxData = ajaxData;
						
						newAjaxData.startDate = busArvlInfoPreditionDtSelectDt;
						
						 $.ajax({
			                type : "get",
			                url : __contextPath__+"/bigdata/getPublicTransferBIT.ajax",
			                data : newAjaxData,
			                beforeSend : function(){
			                    gitsApp.startLoading();
			                },
			                complete : function(){
			                    gitsApp.endLoading();
			                },
			                success : function(list){
							let newTr = 
				
							 list = list.sort(function(a, b){
			                        return parseInt(a.operSeq) - parseInt(b.operSeq);
			                    });
			                    for(const data of list){
			                        let timeDff = '-';
			                        let isNegative = false;
			                        if(data.arrTm && data.realArrTm) {
			                            let time1 = data.arrTm;
			                            let time2 = data.realArrTm;
			                            let date1 = new Date(`2000-01-01T${time1}Z`);
			                            let date2 = new Date(`2000-01-01T${time2}Z`);
			                            let diff = null;
			                            if (date2 < date1) {
			                                diff = date1 - date2;
			                                isNegative = true;
			                            }else{
			                                diff = date2 - date1
			                            }
			                            let ss = Math.floor(diff / 1000) % 60;
			                            let mm = Math.floor(diff / 1000 / 60) % 60;
			                            let hh = Math.floor(diff / 1000 / 60 / 60);
			                            timeDff = `${mm}:${ss}`;
			                        }
			                        newTr += `
			                        <tr>
			                            <td>${data.operSeq}</td>
                                        <td>${data.updateTm}</td>
			                            <td>${data.arrTm}</td>
			                            <td>${data.realArrTm ? data.realArrTm : '-'}</td>
			                            <td style="${isNegative ? 'color:#0FB050' : 'color:#C00000' }">${isNegative ? '-':''}${timeDff}</td>
			                        </tr>`;
			                    }
								$("#busArvlInfoPreditionTbody").empty().append(newTr);
							}
						});
					});
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
                url : __contextPath__+"/bigdata/getPublicTransferUsageByStation.ajax",
                data : {
                    stationId : prop.stationId,
                    searchYear : selectedSearchOption["BD_BUS_USAGE_BY_STATION"].searchYear,
                    searchPeriod : selectedSearchOption["BD_BUS_USAGE_BY_STATION"].searchPeriod,
                    searchTime : selectedSearchOption["BD_BUS_USAGE_BY_STATION"].searchTime,
                },
                beforeSend : function(){
                    gitsApp.startLoading();
                },
                complete : function(){
                    gitsApp.endLoading();
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
                    <th>이용객수</th>
                    <th>환승</th>
                    </thead>
                    <tbody>
                    `;
                    for(const route of list){
                        routeTable += `
                        <tr>
                            <th>(${GITS_ENV.ROUTE_TP[route.routeTp]})<br/>${route.routeNm ? route.routeNm : '-'}</th>
                            <td>${route.rideUserCnt}</td>
                            <td>${route.trnsitUserCnt}</td>
                        </tr>`;
                        calculateData.rideCnt += route.rideUserCnt;
                        calculateData.trnsCnt += route.trnsitUserCnt;
                    }
                    routeTable += "</tbody></table></div>";
                    let html = `
                                <div class="data_popup" >
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p>${prop.stationNm}(${prop.mobileNo ?? prop.stationId})</p>
                                    <p>
                                     이용객수 : ${numberComma(calculateData.rideCnt)}
                                    / 환승 : ${numberComma(calculateData.trnsCnt)}
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
            $.ajax({
                type : "get",
                url : __contextPath__+"/bigdata/getPublicTransferRouteCurveAnalysis.ajax",
                data : selectedSearchOption["BD_BUS_ROUTE_CURVE_ANL"],
                beforeSend : function(){
                    gitsApp.startLoading();
                },
                complete : function(){
                    gitsApp.endLoading();
                },
                success : function(data){
                    let html = '';
                    if(data) {
                        html = `
                                <div class="data_popup" >
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p>노선명 : ${data.routeNm}</p>
                                    <ul class="data_sub mt16">
                                    <li class="popup_item">총 이용객 수 : <span>${data.totUserCnt ? numberComma(data.totUserCnt) : '-'} 명</span></li>
                                    <li class="popup_item">총 정류장 수 : <span>${data.totBstpCnt ? numberComma(data.totBstpCnt) : '-'} 개</span></li>
                                    <li class="popup_item">노선거리 : <span>${data.routeLen ? numberComma(data.routeLen) : '-'}</span></li>
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
            let prop = e.features[0].properties;
            // const topFeature = e.features.reduce(function(prev, current){
            //     return (prev && prev.properties.duplCnt > current.properties.duplCnt) ? prev : current
            // });
            // prop = topFeature.properties;
            $.ajax({
                type: "get",
                url: __contextPath__+"/bigdata/getDuplicateRouteListBySectionId.ajax",
                data : {
                    stToEd : prop.stToEd
                },
                beforeSend : function(){
                    gitsApp.startLoading();
                },
                success : function(list){
                    let routeTable = `<div class="popup_scroll">
                    <table class="popup_table">
                    <thead>
                    <th>버스번호</th>
                    <th>구간-시작정류소</th>
                    <th>구간-종료정류소</th>
                    <th>버스유형</th>
                    <th>지역</th>
                    </thead>
                    <tbody>
                    `;
                    for(const route of list){
                        routeTable += `
                        <tr>
                            <td>${route.routeNm}</td>
                            <td>${route.stStaNm}(${route.startMobileNo ? route.startMobileNo : route.startBstpId})</td>
                            <td>${route.edStaNm}(${route.endMobileNo ? route.endMobileNo : route.endBstpId})</td>
                            <td>${route.routeTp}</td>
                            <td>${route.districtGnm ? route.districtGnm : route.districtSnm}</td>
                        </tr>`;
                    }
                    routeTable += "</tbody></table></div>";

                    let html = '';
                    if(list.length > 0) {
                        html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    ${routeTable}
                                </div>
                            `;
                    }else{
                        html = `<div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                   <p>중복노선이 존재하지 않습니다.</p>
                                </div>`;
                    }
                    let popup = new mapboxgl.Popup({offset : [0, -30], maxWidth : "none"})
                        .setLngLat(e.lngLat)
                        .setHTML(html)
                        .addTo(_Map)
                },
                complete : function(){
                    gitsApp.endLoading();
                }
            });
        },
        "BD_BUS_ROUTE_PASSENGER_ANL" : function(e){
            if(!selectedSearchOption["BD_BUS_ROUTE_PASSENGER_ANL"]) {
                return;
            }
            const prop = e.features[0].properties;
            let ajaxData = $.extend(
                selectedSearchOption["BD_BUS_ROUTE_PASSENGER_ANL"],
                {stStationId : prop.stationId}, {});
            $.ajax({
                type: "get",
                url: __contextPath__+"/bigdata/getBusRouteSectionPassengerInfo.ajax",
                data : ajaxData,
                beforeSend : function(){
                    gitsApp.startLoading();
                },
                success : function(list){
                    let routeTable = `<div class="popup_scroll">
                    <table class="popup_table">
                    <thead>
                    <th>구간</th>
                    <th>이용객수</th>
                    </thead>
                    <tbody>
                    `;
                    for(const route of list){
                        routeTable += `
                        <tr>
                            <td>${route.stStaNm} - ${route.edStaNm}</td>
                            <td>${numberComma(route.passengers)}명</td>
                        </tr>`;
                    }
                    routeTable += "</tbody></table></div>";
                    let html = `
                                <div class="data_popup">
									<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="${__contextPath__}/statics/images/close.png" alt="닫기"></button>
                                    <i class="map_icon bus_icon data_icon"></i>
                                    <p>${prop.stationNm}(${prop.mobileNo ?? prop.stationId})</p>
                                    ${routeTable}
                                </div>
                            `;
                    let popup = new mapboxgl.Popup({offset : [0, -30], maxWidth : "none"})
                        .setLngLat(e.lngLat)
                        .setHTML(html)
                        .addTo(_Map)
                },
                complete : function(){
                    gitsApp.endLoading();
                }
            });

        },
        "BD_BUS_STATION" : function(e){
            const prop = e.features[0].properties;
            $.ajax({
                type : "get",
                url : __contextPath__+"/monitoring/getBusStationRouteList.ajax?stationId="+prop.stationId,
                beforeSend : function(){
                    gitsApp.startLoading();
                },
                complete : function(){
                    gitsApp.endLoading();
                },
                success : function(list){
                    let routeTable = `<div class="popup_scroll">
                    <table class="popup_table">
                    <thead>
                    <th>(버스유형)<br/>노선명</th>
                    <th>(회사명)<br/>관할관청</th>
                    <th>기점정류소</th>
                    <th>종점정류소</th>
                    </thead>
                    <tbody>
                    `;
                        for(const route of list){
                            let etlDt = '-';
                            if(route.etlDt) {
                                let yyyy = etlDt.substring(0,4);
                                let mm = etlDt.substring(4,6);
                                let dd = etlDt.substring(6,8);
                                etlDt = yyyy+"-"+mm+"-"+dd;
                            }

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
                                    <p>${prop.stationNm}(${prop.mobileNo ?? prop.stationId})</p>
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

        control.fitBounds = function(bbox){
            try{
                _Map.fitBounds(bbox, {padding: 100});
            }catch(e){console.error('포커싱 에러')}
        }

        control.setDefaultPanTilt = function(){
            _Map.setPitch(0);
            _Map.rotateTo(0);
        }
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
            window.location.reload();
            return;
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


        control.generateExcelDownloadButton = function(callback){
            $("#mapExcelDownloadButton").remove();
            if(typeof callback !== "function") return;
            const html = `<button type="button" id="mapExcelDownloadButton"
                        style="margin-right:10px;" 
                        class="is-darkgreen-btn radius original_result_btn">엑셀 다운로드</button>`;
            let $html = $(html);
            $html.on("click", function(){
               callback();
            });
            $html.prependTo("#aside_dashboard_container .bottom_btn");

        }

        control.generateBigdataDetailContainer = function(){
            let $container = $("#bigdataDetailInfoContainer"+(isDual ? "Dual" : ""));
            let $wrap = $("#bigdataDetailContentWrap"+(isDual ? "Dual" : ""));
            let callbackArray = [];
            $wrap.empty();
            let _this = this;
            if(window.dualMap) {
                $(".bigdataDetailInfoContainer").addClass("hasDual");
                $("#bigdataDetailInfoContainer").addClass("is-center");
            }else{
                $(".bigdataDetailInfoContainer").removeClass("hasDual");
                $(".bigdataDetailInfoContainer").removeClass("is-center");
            }
            _this.generateBigdataDetailItem = function(title, content, afterFunc){
                let html = `<div class="bigdataDetailContentItem">
                    <h4 class="bigdataDetailContentTitle">${title}</h4>
                    ${content}
                </div>`;
                let $html = $(html);
                if($html.find("canvas")){
                    const canvasId = $html.find("canvas").attr("id");
                    const isReverse = !!$html.find("canvas").data("excelreverse");
                    let button1 = `<button type="button" style="margin-left:5px;" class="is-darkgreen-btn is-small" onclick="fnDownloadChartImage('${canvasId}','${title}')">이미지</button>`;
                    let button2 = `<button type="button" style="margin-left:5px;" class="is-darkgreen-btn is-small" onclick="fnDownloadExcelChartWorker('${canvasId}','${title}',${isReverse})">엑셀</button>`;
                    $html.find("h4.bigdataDetailContentTitle").append(button1).append(button2);
                }
                $wrap.append($html);
                callbackArray.push(afterFunc.bind({element : $html}));
                return _this;
            }
            _this.show = function(){
                $container.show();
                for(const fnc of callbackArray){
                    if(typeof fnc === "function") fnc();
                }
            }
            return _this;
        }

        /**
         * gits worker job id 생성
         * @param jobName
         * @returns {string}
         */

		control.generateJobId = function(jobName){
            $(".mapboxgl-popup").remove();
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

        control.generateFilterReset = function(title, layername, property, defaultValue) {
            let jobId = layername+"_"+property;
            if($("#mapDataLoadingJobList .job-item[data-job-id='"+jobId+"']").length > 0) {
                return;
            }
            let jobItem = $(`
                <div data-job-id="${jobId}" class="job-item filter-item" style="cursor:pointer">
                    <img src="${__contextPath__}/statics/images/close.png" alt="초기화" style="width:12px;vertical-align:middle;">
                    <div style="margin-left:4px;">${title}</div>
                </div>`);
            $("#mapDataLoadingJobList").prepend(jobItem);
            jobItem.slideDown(250);
            jobItem.on("click", function(){
                _Map.setPaintProperty(layername, property, defaultValue);
                if(typeof __DualMap !== "undefined"){
                    try {
                        __DualMap.setPaintProperty(layername, property, defaultValue);
                    }catch(ignored) {}
                }
                $(this).remove();
            });
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

        control.drawCommonHeatMap = function(featureCollection,layerid, addlayer,weight, key, color) {
            let heatmap_color = [
                'interpolate',
                ['linear'],
                ['heatmap-density']
            ]
            if(!color) {
                heatmap_color = heatmap_color.concat([
                    0,
                    'rgba(33,102,172,0)',
                    0.2,
                    'rgb(103,169,207)',
                    0.4,
                    'rgb(209,229,240)',
                    0.6,
                    'rgb(253,219,199)',
                    0.8,
                    'rgb(239,138,98)',
                    1,
                    'rgb(178,24,43)'
                ]);
            }else{
                heatmap_color = heatmap_color.concat(color);
            }
            let layer = {
                'id': layerid,
                'type': 'heatmap',
                'source': layerid,
                'paint': {
                    'heatmap-weight': [
                        'interpolate',
                        ['linear'],
                        ["get",key],
                        0, 0,
                        weight, 1,
                    ],
                    // 줌 level 강도
                    'heatmap-intensity': [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        0,
                        3,
                        9,
                        8
                    ],
                    // 밀도에 따라 색상값 할당
                    'heatmap-color': heatmap_color,
                    // 줌에 맞게 크기 변경
                    'heatmap-radius':[
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        8,
                        3,
                        10,
                        5,
                        11,
                        ["min", ['get', key], 11],
                        14,
                        ["min", ['get', key], 25],
                        17,
                        ["min", ['get', key], 50]
                    ],
                    // 줌에 맞게 투명도 조절
                    'heatmap-opacity': [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        7,
                        1,
                        9,
                        1,
                        13,
                        0.7
                    ]
                }
            }
            let layerarray = [layer];
            if(addlayer) {
                layerarray = layerarray.concat(addlayer);
            }
            core.control.addExpertSourceAndLayer(layerid, {}, featureCollection, layerarray);
        }

        /**
         * 클러스터용 레이어 및 소스 추가
         * @param sourceId
         * @param clusterOption
         * @param featureCollection
         * @param layerArray
         * @param afterLevelLayer
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
                        if(!_Map.getLayer(afterLevelLayer)) afterLevelLayer = null;
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

                el.addEventListener('click', e => {
                    e.stopPropagation();
                    $(".mapboxgl-popup").remove();
                    if(popup_el) {
                        new mapboxgl.Popup({offset:[0, -30], maxWidth : "none"})
                            .setHTML(popup_el.outerHTML)
                            .setLngLat([lng, lat])
                            .addTo(_Map)
                    }
                }, true);
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

        control.generatePlayerModel = function(title, content, playEvent, stopEvent, isPlayHide = false, isBodyHide = false){
            $(".chart_video_container .tab_box_title").text(title);
            $(".chart_video_container .chart_video_body").html(content);
            if(isPlayHide) {
                $(".chart_video_container .chart_play_footer").hide();
            }else {
                $(".chart_video_container .chart_play_footer").show();
                $(".chart_video_container .chart_play").off("click").on("click", function () {
                    playEvent();
                    $(this).removeClass('svg_active');
                });
                $(".chart_video_container .chart_stop").off("click").on("click", function () {
                    stopEvent();
                    $('.chart_play').addClass('svg_active');
                });
            }
            if(isBodyHide) {
                $(".chart_video_container .chart_video_body").hide();
            }else{
                $(".chart_video_container .chart_video_body").show();
            }
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
            flyToObj.duration = 250;
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
            if(typeof lng == "string") lng = parseFloat(lng);
            if(typeof lat == "string") lat = parseFloat(lat);
            if(lat > lng) {
                let swapLng = lat;
                let swapLat = lng;
                lng = swapLng;
                lat = swapLat;
            }
            $('.mapboxgl-popup').remove();
            _Map.once('moveend', function(){
                const coords = new mapboxgl.LngLat(lng, lat);
                const point = _Map.project(coords);
                setTimeout(function(){
                    _Map.fire('click', {point: point, lngLat : coords});
                },1500)

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
                            html += `<li class="popup_item">노드ID : <span>${prop['NODE_ID']}</span></li>`;
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
            _Map.off('click', layerName, MapEvents.BD_BUS_STATION);
            _Map.on('click', layerName, MapEvents.BD_BUS_STATION);
            core.control.showLayer(LAYER.BUS_STATION);
        },
        "CM_BUS_ROUTE" : function(e, lineWidth, otherFeatureCollection, isViewDirection = false){
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
                    'line-color': [
                        'case',
                        ['==', ['get',"updown"], "up"],
                        "#14e776",
                        ['==', ['get',"updown"], "down"],
                        "#ff0062",
                        "#00a5ff"
                    ],

                    'line-width': lineWidth ? lineWidth : 2,
                    'line-opacity': 0.8,
                },
            }
            let dLayer = {
                id: layerName+"_DIRECTION",
                type: 'symbol',
                source: sourceName,
                paint: {},
                layout: {
                    'symbol-placement': 'line',
                    'icon-image': 'arrow',
                    'icon-rotate': 270,
                    'icon-rotation-alignment': 'map',
                    'icon-allow-overlap': true,
                    'icon-ignore-placement': true
                },
                "filter" : ['has', "route"]
            }
            let layerArray = [layer];
            if(isViewDirection === true) {
                layerArray.push(dLayer);
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, otherFeatureCollection ? otherFeatureCollection : e.data.featureCollection, layerArray, LAYER.SGG);
        },
        "CM_STARTENDSTATIONBYROUTE" : function(e) {
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
            _Map.off('click', layerName, MapEvents.BD_BUS_STATION);
            _Map.on('click', layerName, MapEvents.BD_BUS_STATION);
            core.control.showLayer(LAYER.BUS_STATION);
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
            _Map.off('click', layerName, MapEvents.BD_BUS_STATION);
            _Map.on('click', layerName, MapEvents.BD_BUS_STATION);
            core.control.showLayer(LAYER.BUS_STATION);
        },
        "F_SMART" : function(e){
            const sourceName = LAYER.FACILITY_SMART;
            const layerName = LAYER.FACILITY_SMART;
            const linkLayerName = LAYER.FACILITY_SMART_LINK;
            if(_Map.getLayer(sourceName)){
                _Map.getSource(sourceName).setData(e.data.featureCollection);
                return;
            }
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
                        13, 0.15,
                        15, 0.5
                    ]
                },
                filter : ['has', 'nodeId']
            }
            let textLayer = {
                'id': layerName+"_TEXT",
                'type': 'symbol',
                'source': sourceName,
                "layout": {
                    "text-field": ["get", "crsrdNm"],
                    "text-anchor": "bottom",
                    "text-size": 10,
                    "text-offset": [0, -1.5],
                },
                'paint' : {
                    "text-color": "black",
                    "text-halo-color": "white",
                    "text-halo-width": 2,
                    "text-halo-blur": 1
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
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [lineLayer,layer,textLayer],LAYER.NODE);

            _Map.off("click", layerName, MapEvents.F_SMART);
            _Map.on("click", layerName, MapEvents.F_SMART);
            /*_Map.off("click", linkLayerName, MapEvents.F_SMART_LINK);
            _Map.on("click", linkLayerName, MapEvents.F_SMART_LINK);*/
            if(document.querySelector("#layerControlList [data-layer='smc']"))
                document.querySelector("#layerControlList [data-layer='smc']").disabled = false;
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
            let textLayer = {
                'id': layerName+"_TEXT",
                'type': 'symbol',
                'source': sourceName,
                "layout": {
                    "text-field": ["get", "vdsNm"],
                    "text-anchor": "bottom",
                    "text-size": 10,
                    "text-offset": [0, -1.5],
                },
                'paint' : {
                    "text-color": "black",
                    "text-halo-color": "white",
                    "text-halo-width": 2,
                    "text-halo-blur": 1
                }
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [layer,textLayer], LAYER.NODE);

            _Map.off("click", layerName, MapEvents.F_VDS);
            _Map.on("click", layerName, MapEvents.F_VDS);
            if(document.querySelector("#layerControlList [data-layer='vds']"))
                document.querySelector("#layerControlList [data-layer='vds']").disabled = false;
        },
        "F_DSRC" : function(e) {
            const sourceName = LAYER.FACILITY_DSRC;
            const layerName = LAYER.FACILITY_DSRC;
            const linkLayerName = LAYER.FACILITY_DSRC_LINK;
            core.control.removeCustomSource(sourceName);
            core.control.removeCustomSource(linkLayerName);
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
                'filter' : ["==", 'type', "dsrc"]
            }
            let textLayer = {
                'id': layerName+"_TEXT",
                'type': 'symbol',
                'source': sourceName,
                "layout": {
                    "text-field": ["get", "rseNm"],
                    "text-anchor": "bottom",
                    "text-size": 10,
                    "text-offset": [0, -1.5],
                },
                'paint' : {
                    "text-color": "black",
                    "text-halo-color": "white",
                    "text-halo-width": 2,
                    "text-halo-blur": 1
                },
                'filter' : ["==", 'type', "dsrc"]
            }
            let lineLayer = {
                'id': linkLayerName,
                'type': 'line',
                'source': linkLayerName,
                'layout': {
                    'line-join': 'round',
                    'line-cap': 'round'
                },
                'paint': {
                    'line-color': '#f1f075',
                    'line-width': [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        10, 2,
                        15, 7,
                        18, 8
                    ],
                    'line-opacity': 1,
                },
                'filter' : ["==", 'type', "dsrc_link"]
            }
            if($("#layerControlList [data-layer='dsrc']").length > 0) {
                lineLayer.layout.visibility = "none";
            }
            let lineFeature = e.data.featureCollection.features.filter((obj) => obj.properties.type == "dsrc_link");
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [layer,textLayer]);
            core.control.addExpertSourceAndLayer(linkLayerName,{}, _Util.wrapFeatureCollection(lineFeature).featureCollection, [lineLayer]);

            _Map.off("click", layerName, MapEvents.F_DSRC);
            _Map.on("click", layerName, MapEvents.F_DSRC);
            _Map.off("click", linkLayerName, MapEvents.F_DSRC_LINK);
            _Map.on("click", linkLayerName, MapEvents.F_DSRC_LINK);
            if(document.querySelector("#layerControlList [data-layer='dsrc']"))
                document.querySelector("#layerControlList [data-layer='dsrc']").disabled = false;
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
        "M_TRAFFIC_MINIMIZE" : function(e){
            workerResultEvent.M_TRAFFIC(e);
        },
        "M_VOLUME_VDS" : function(e){
            const sourceName = LAYER.M_TRAFFIC_VOLUME_VDS_CLUSTER;
            const clusterLayerName = LAYER.M_TRAFFIC_VOLUME_VDS_CLUSTER;
            if(_Map.getLayer(sourceName)){
                _Map.getSource(sourceName).setData(e.data.featureCollection);
                return;
            }
            core.control.removeCustomSource(sourceName);
            let clusterLayer = {
                id: clusterLayerName,
                type: 'circle',
                source: sourceName,
                paint: {
                    'circle-color': [
                        'interpolate',
                        ['linear'],
                        ['get', 'trfVolTotal'],
                        0, '#51bbd6',
                        20000, '#f1f075',
                        50000, '#f28cb1',
                        100000, '#ec1346',
                    ],
                    'circle-radius': 35,
                    'circle-opacity' : 0.8,
                    'circle-stroke-color' : STYLES.VDS_COLOR,
                    'circle-stroke-width' : 2
                },
                filter : ['has', 'point_count']
            }
            let clusterTextLayer = {
                id: clusterLayerName+"_text",
                type: 'symbol',
                source: sourceName,
                layout: {
                    'text-allow-overlap' : true,
                    'text-field': ['number-format',['get', 'trfVolTotal'],{locale:'kr'}],
                    'text-size': 14
                },
                filter : ['has', 'point_count']
            }
            let unClusterLayer = {
                id: clusterLayerName+"_none",
                type: 'circle',
                source: sourceName,
                paint: {
                    'circle-color': [
                        'interpolate',
                        ['linear'],
                        ['get', 'trfVol'],
                        0, '#51bbd6',
                        20000, '#f1f075',
                        50000, '#f28cb1',
                        100000, '#ec1346',
                    ],
                    'circle-radius': 20,
                    'circle-opacity' : 0.8,
                    'circle-stroke-color' : STYLES.VDS_COLOR,
                    'circle-stroke-width' : 2
                },
                'filter': ['!=', 'cluster', true]
            }
            let unclusterTextLayer = {
                id: clusterLayerName+"_none_text",
                type: 'symbol',
                source: sourceName,
                layout: {
                    'text-allow-overlap' : true,
                    'text-field': ['number-format',['get', 'trfVol'],{locale:'kr'}],
                    'text-size': 10
                },
                'filter': ['!=', 'cluster', true]
            }
            const clusterOption = {
                cluster : true,
                clusterMaxZoom: 11, // Max zoom to cluster points on
                clusterRadius: 35,
                clusterProperties: {
                    'trfVolTotal': ["+", ["get", "trfVol"]],
                },
                tolerance : 0.5
            }
            core.control.addExpertSourceAndLayer(sourceName,clusterOption, e.data.featureCollection, [clusterLayer, clusterTextLayer, unClusterLayer, unclusterTextLayer]);
        },
        "M_VOLUME_SMART" : function(e) {
            const sourceName = LAYER.M_TRAFFIC_VOLUME_SMART_CLUSTER;
            const clusterLayerName = LAYER.M_TRAFFIC_VOLUME_SMART_CLUSTER;

            const sourceNameDrct = LAYER.M_TRAFFIC_VOLUME_SMART_DRCT;
            const layerNameDrct = LAYER.M_TRAFFIC_VOLUME_SMART_DRCT;
            if(_Map.getLayer(sourceName)){
                _Map.getSource(sourceName).setData(e.data.crsrd.featureCollection);
                _Map.getSource(sourceNameDrct).setData(e.data.drct.featureCollection);
                return;
            }
            core.control.removeCustomSource(sourceName);
            core.control.removeCustomSource(sourceNameDrct);
            let clusterLayer = {
                id: clusterLayerName,
                type: 'circle',
                source: sourceName,
                paint: {
                    'circle-color': [
                        'interpolate',
                        ['linear'],
                        ['get', 'trfVolTotal'],
                        0, '#51bbd6',
                        20000, '#f1f075',
                        50000, '#f28cb1',
                        100000, '#ec1346',
                    ],
                    'circle-radius': 35,
                    'circle-opacity' : 0.8,
                    'circle-stroke-color' : STYLES.SMCRD_COLOR,
                    'circle-stroke-width' : 2
                },
                filter : ['has', 'point_count']
            }
            let clusterTextLayer = {
                id: clusterLayerName+"_text",
                type: 'symbol',
                source: sourceName,
                layout: {
                    'text-allow-overlap' : true,
                    'text-field': ['number-format',['get', 'trfVolTotal'],{locale:'kr'}],
                    'text-size': 14
                },
                filter : ['has', 'point_count']
            }
            let unClusterLayer = {
                id: clusterLayerName+"_none",
                type: 'circle',
                source: sourceName,
                paint: {
                    'circle-color': [
                        'interpolate',
                        ['linear'],
                        ['get', 'trfVol'],
                        0, '#51bbd6',
                        20000, '#f1f075',
                        50000, '#f28cb1',
                        100000, '#ec1346',
                    ],
                    'circle-radius': 20,
                    'circle-opacity' : 0.8,
                    'circle-stroke-color' : STYLES.SMCRD_COLOR,
                    'circle-stroke-width' : 2
                },
                'filter': ['!=', 'cluster', true]
            }
            let unclusterTextLayer = {
                id: clusterLayerName+"_none_text",
                type: 'symbol',
                source: sourceName,
                layout: {
                    'text-allow-overlap' : true,
                    'text-field': ['number-format',['get', 'trfVol'],{locale:'kr'}],
                    'text-size': 10
                },
                'filter': ['!=', 'cluster', true]
            }
            const clusterOption = {
                cluster : true,
                clusterMaxZoom: 11, // Max zoom to cluster points on
                clusterRadius: 35,
                clusterProperties: {
                    'trfVolTotal': ["+", ["get", "trfVol"]],
                },
                tolerance : 0.5
            }
            core.control.addExpertSourceAndLayer(sourceName,clusterOption, e.data.crsrd.featureCollection, [clusterLayer, clusterTextLayer, unClusterLayer, unclusterTextLayer]);


            // 방향별
            let arrowLayer = {
                'id': layerNameDrct,
                'type': 'symbol',
                'source': sourceNameDrct,
                'maxzoom': 23,
                'minzoom': 14,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': 'road_direction_straight',
                    'icon-rotate': ['get','angle'],
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        14, 0.4,
                        15, 0.5,
                        22, 1
                    ]
                },
                'paint': {
                    'icon-color' : [
                        'interpolate',
                        ['linear'],
                        ['get', 'strghtTrfvlm'],
                        0, LEGEND_COLOR.WEIGHT[0],
                        5000, LEGEND_COLOR.WEIGHT[2],
                        10000, LEGEND_COLOR.WEIGHT[4],
                        20000, LEGEND_COLOR.WEIGHT[6],
                    ]
                }
            }
            let circleLayer = {
                'id': layerNameDrct+"_CIRCLE",
                'type': 'circle',
                'source': sourceNameDrct,
                'maxzoom': 23,
                'minzoom': 14,
                'paint' : {
                    'circle-color': 'white',
                    /*'circle-radius': ['get', 'acdntCnt'],*/
                    'circle-radius': [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        14, 14,
                        15, 15,
                        22, 30
                    ],
                    /*'circle-radius': 30,*/
                    'circle-opacity' : 0.8,
                    'circle-stroke-color' : "#000000",
                    'circle-stroke-width' : 2
                }
            }
            let leftArrowLayer = {
                'id': layerNameDrct+"_LEFT",
                'type': 'symbol',
                'source': sourceNameDrct,
                'maxzoom': 23,
                'minzoom': 14,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': 'road_direction_left',
                    'icon-rotate': ['get','angle'],
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        14, 0.4,
                        15, 0.5,
                        22, 1
                    ]
                },
                'paint': {
                    'icon-color' : [
                        'interpolate',
                        ['linear'],
                        ['get', 'trnghtTrfvlm'],
                        0, LEGEND_COLOR.WEIGHT[0],
                        5000, LEGEND_COLOR.WEIGHT[2],
                        10000, LEGEND_COLOR.WEIGHT[4],
                        20000, LEGEND_COLOR.WEIGHT[6],
                    ]
                }
            }
            let rightArrowLayer = {
                'id': layerNameDrct+"_RIGHT",
                'type': 'symbol',
                'source': sourceNameDrct,
                'maxzoom': 23,
                'minzoom': 14,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': 'road_direction_right',
                    'icon-rotate': ['get','angle'],
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        14, 0.4,
                        15, 0.5,
                        22, 1
                    ]
                },
                'paint': {
                    'icon-color' : [
                        'interpolate',
                        ['linear'],
                        ['get', 'trnghtTrfvlm'],
                        0, LEGEND_COLOR.WEIGHT[0],
                        5000, LEGEND_COLOR.WEIGHT[2],
                        10000, LEGEND_COLOR.WEIGHT[4],
                        20000, LEGEND_COLOR.WEIGHT[6],
                    ]
                }
            }

            core.control.addExpertSourceAndLayer(layerNameDrct,{}, e.data.drct.featureCollection, [circleLayer,arrowLayer,leftArrowLayer,rightArrowLayer]);
            _Map.off("click", layerNameDrct, MapEvents.M_VOLUME_SMART);
            _Map.on("click", layerNameDrct, MapEvents.M_VOLUME_SMART);
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
                    conggrade: item.congGrade,
                    spd: item.spd,
                    vol: item.vol,
                    trvl_time: item.trvlTime,
                    colldate : item.collDate
                });
            }
            const colors = [
                [null , STYLES.LINK_COLOR],
                ["0" , STYLES.LINK_COLOR],
                ["1" , "#11ff00"],
                ["2" , "#ffde00"],
                ["3" , "#de0909"],
            ];
            if(!JSON.stringify(_Map.getPaintProperty(LAYER.LINK, 'line-color')).includes("conggrade"))
                core.control.setPaintFilterColorByState(LAYER.LINK, "conggrade", colors, "#11ff00");

            _Map.off("click",LAYER.LINK, MapEvents.M_TRAFFIC);
            _Map.on("click",LAYER.LINK, MapEvents.M_TRAFFIC);

        },
        "M_DANGER_VEHICLE" :function(e) {
            const layer = LAYER.DANGER;
            $("#danger_move_cnt").text(e.data.featureCollection.features.length);
            if(_Map.getLayer(layer)){
                _Map.getSource(layer).setData(e.data.featureCollection);
            } else {
                let dangerLayer = {
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
                core.control.addExpertSourceAndLayer(layer, {}, e.data.featureCollection, [dangerLayer]);
            }
            _Map.off("click", layer, MapEvents.M_DANGER_VEHICLE);
            _Map.on("click", layer, MapEvents.M_DANGER_VEHICLE);
            if(document.querySelector("#layerControlList [data-layer='dngr']"))
                document.querySelector("#layerControlList [data-layer='dngr']").disabled = false;

            let dangerListDiv = document.getElementById("dangerTbody");
            if(dangerListDiv){
                let listHtml = "";
                const sortedFeatures = e.data.featureCollection.features.sort(function(a, b){
                    return parseInt(b.properties.speed) - parseInt(a.properties.speed);
                });
                for(const dc of sortedFeatures) {
                    const dangerCar = dc.properties;
                    listHtml += `<tr>
                        <td>${dangerCar.dggdNm}</td>
                        <td onclick="map.control.moveMap([${dangerCar.vhclLcLon},${dangerCar.vhclLcLat}], 16);">
                            ${dangerCar.vhclRegistNo}
                        </td>
                        <td>
                            ${dangerCar.acdntYn === 'Y' ? '사고이력있음' : '-'}
                        </td>
                    </tr>`;
                }
                dangerListDiv.innerHTML = listHtml;
            }
        },
        "M_EMERGENCY" : function(e){
            const layer = LAYER.EMERGENCY;
            const routelayer = LAYER.EMERGENCY_ROUTE;
            const routePointLayer = LAYER.EMERGENCY_ROUTE_POINT;
            if(_Map.getLayer(layer)){
                _Map.getSource(layer).setData(e.data.collection.featureCollection);
                _Map.getSource(routelayer).setData(e.data.routeCollection.featureCollection);
            }else{
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
                            10, 0.4,
                            15, 0.4
                        ],
                        "icon-offset": [
                            "case",
                            ["==", ["get", "icon"], "end_icon"],
                            ["literal", [0, 0]],
                            ["literal", [0, 0]]
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
                core.control.addExpertSourceAndLayer(layer, {}, e.data.collection.featureCollection, [emergencyLayer,alertAreaLayer], LAYER.LINK);
            }
            /*let pushedEmergencyService = [];
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
            });*/

            if(e.data.dangerCar) {
                for (const item of e.data.dangerCar) {
                    let id = item.vhclRegistNo;
                    const warningCount = parseInt($("#warningAlarmCount").text())+1;
                    $("#warningAlarmCount").text(warningCount);
                    if ($(".outbreak_push_item[data-id='" + id + "']").length === 0 && !_getAlarmClosed(id)) {
                        let dt = _Util.parseYYMMDDHHMMtoKorean(item.occurDt);
                        let subject = "[위험물차량접근] " + dt+"<br/>차량번호 : "+id+" / "+item.dggdNm;
                        gitsApp.generatePushElement("WARNING", subject, id, function () {
                            _setAlarmClosed(id);
                        }, function(){
                            core.control.moveMap([item.targetLon, item.targetLat], 11);
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
                                </div>
                            `)
                            .addTo(_Map)
                        core.animate.setOpenedPopup(newDataPopup);
                    }
                }else{
                    core.animate.removeFocus();
                }
            }
            _Map.off("click", layer, MapEvents.M_EMERGENCY);
            _Map.on("click", layer, MapEvents.M_EMERGENCY);
            if(document.querySelector("#layerControlList [data-layer='emrg']"))
                document.querySelector("#layerControlList [data-layer='emrg']").disabled = false;
        },
        "M_BUS" : function(e){
            const layer = LAYER.BUS;
            m_bus_features = e.data.featureCollection.features;
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
            _Map.off("click", layer, MapEvents.M_BUS);
            _Map.on("click", layer, MapEvents.M_BUS);
            if(document.querySelector("#layerControlList [data-layer='bus']"))
                document.querySelector("#layerControlList [data-layer='bus']").disabled = false;

            if($("#searchBtn[data-type='bus']").length > 0){
                /*갱신*/
                $("#searchBtn[data-type='bus']").click();
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
                core.control.addExpertSourceAndLayer(layer, {}, e.data.collection.featureCollection, [alertAreaLayer], LAYER.LINK);
                core.control.addExpertSourceAndLayer(layer, {}, e.data.collection.featureCollection, [carLayer]);
            }
            if(e.data.dangerCar) {
                for (const item of e.data.dangerCar) {
                    let id = item.vhclRegistNo;
                    const warningCount = parseInt($("#warningAlarmCount").text())+1;
                    $("#warningAlarmCount").text(warningCount);
                    if ($(".outbreak_push_item[data-id='" + id + "']").length === 0 && !_getAlarmClosed(id)) {
                        let dt = _Util.parseYYMMDDHHMMtoKorean(item.occurDt);
                        let subject = "[위험물차량접근] " + dt+"<br/>차량번호 : "+id+" / "+item.dggdNm;
                        gitsApp.generatePushElement("WARNING", subject, id, function () {
                            _setAlarmClosed(id);
                        }, function(){
                            core.control.moveMap([item.targetLon, item.targetLat], 11);
                        });
                    }
                }
            }

            core.control.marker ? core.control.marker.clearAll() : null;
            //const msgbody = e.data.serviceresult.msgbody;
            //for (const item of msgbody.itemlist) {
            let active_incicate = [];
            if($(".danger-remark-btn.is-active").length > 0) {
                $(".danger-remark-btn.is-active").each(function () {
                    if ($(this).data('incicate') == "8") {
                        active_incicate.push(8); // 지진
                        active_incicate.push(9); // 산사태
                        active_incicate.push(10); // 홍수
                        active_incicate.push(12); // 태풍
                    }
                    if ($(this).data('incicate') == "2") {
                        active_incicate.push(2); // 차량사고
                        active_incicate.push(4); // 차량고장
                        active_incicate.push(5); // 차량화재
                    } else {
                        active_incicate.push($(this).data('incicate'));
                    }
                });
            }else{
                active_incicate = [1,2,3,4,5,6,7,8,9,10,11,12,13,14]
            }
			for (const item of e.data.list) {
                if(savedWarningFilter.indexOf(item.infoSrcOrg) !== -1) continue;
                const markerWrap = document.createElement("div");
                const markerIcon = document.createElement("i");
              //const markerText = document.createElement('div');
                markerWrap.append(markerIcon);
              //markerWrap.append(markerText);
                let inciCate = item.inciCate;
                // 카테고리가 정상적으로 들어오지 않는경우가 있음
                if(item.description.includes("사고")) {
                    inciCate = '2';
                }
                if(item.description.includes("공사")) {
                    inciCate = '6';
                }
                if(inciCate && active_incicate.indexOf(parseInt(inciCate)) < 0) {
                    continue;
                }
                markerWrap.className = "map_icon_wrap";
                markerIcon.className = "map_icon is-small icon_outbreak"+inciCate;
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
                popupWrap.innerHTML =`<div class="data_popup" style="max-width:300px;">
								<button type="button" class="mapbox_popup_close" onclick="mapPopupClose()"><img src="/statics/images/close.png" alt="닫기"></button>
                                <i class="map_icon bus_icon data_icon"></i>
								<ul class="mt16">
								    <li class="data_sub popup_item">내용 : <span>${item.description}</span></li>
								    <li class="data_sub popup_item">수집처 : <span>${item.infoSrcOrg}</span></li>
	                                <li class="data_sub popup_item">발생시간 : <span>${_Util.parseYYYYMMDDHHMMtoKorean(item.beginDate)}</span></li>
	                                <li class="data_sub popup_item">종료(예정)시간 : <span>${item.endDate ? _Util.parseYYYYMMDDHHMMtoKorean(item.endDate) : "미정"}</span></li>
	                                <li class="data_sub popup_item">발생차선 : <span>${item.occurredLane}</span></li>
	                                <li class="data_sub popup_item">통제차선 : <span>${item.closedLane}</span></li>
	                                <li class="data_sub popup_item">돌발유형 : <span>${GITS_ENV.INCI_CATE[item.inciCate] ? GITS_ENV.INCI_CATE[item.inciCate] : (item.inciCateNm ? item.inciCateNm : "유형없음")}</span></li>
	                                <li class="data_sub popup_item">도로명 : <span>`+roadName+`</span></li>
	                                <li class="data_sub popup_item">방향 : <span>`+(roadDirection ? roadDirection : '-')+`</span></li>
								</ul>
                            </div>
				`;

                core.control.marker.drawMakerByLatLng(markerWrap, item.gpsX, item.gpsY, popupWrap)
            }

            core.monitoring.getWarningInfoToday().then(function(list){

            }).catch(function(reject){

            });
            _Map.off('click', layer, MapEvents.DANGER_CAR);
            _Map.on('click', layer, MapEvents.DANGER_CAR);
            if(document.querySelector("#layerControlList [data-layer='warn']"))
                document.querySelector("#layerControlList [data-layer='warn']").disabled = false;
        },
        "M_WEATHER" : function(e){
            core.control.removeCustomSource(LAYER.GRID_WEATHER);
            core.control.addSourceAndLayer("geojson", LAYER.GRID_WEATHER,LAYER.GRID_WEATHER,e.data.featureCollection, false, true);
        },
        "BD_PREDICTION_ACCIDENT" : function(e) {
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg).footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
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
                /*['==', ['feature-state',"safeGrd"], "-3"],
                LEGEND_COLOR.TRAFFIC_6LEVEL[0],
                ['==', ['feature-state',"safeGrd"], "-2"],
                LEGEND_COLOR.TRAFFIC_6LEVEL[1],
                ['==', ['feature-state',"safeGrd"], "-1"],
                LEGEND_COLOR.TRAFFIC_6LEVEL[2],*/
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

            _Map.off("click", LAYER.LINK, MapEvents.BD_PREDICTION_ACCIDENT);
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
                            /*props.noneLinkCntBySgg,
                            props.speedOverCntBySgg,
                            props.speedUnderCntBySgg,*/
                            props.safeCntBySgg,
                            props.warnCntBySgg,
                            props.dangerCntBySgg,
                            props.seriousCntBySgg
                        ], [
                            /*LEGEND_COLOR.TRAFFIC_6LEVEL[0],
                            LEGEND_COLOR.TRAFFIC_6LEVEL[1],
                            LEGEND_COLOR.TRAFFIC_6LEVEL[2],*/
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
                                        <li class="popup_item">안전 링크 갯수 : <span>${props.safeCntBySgg ? numberComma(props.safeCntBySgg) : 0}개</span></li>
                                        <li class="popup_item">주의 링크 갯수 : <span>${props.warnCntBySgg ? numberComma(props.warnCntBySgg) : 0}개</span></li>
                                        <li class="popup_item">위험 링크 갯수 : <span>${props.dangerCntBySgg ? numberComma(props.dangerCntBySgg) : 0}개</span></li>
                                        <li class="popup_item">심각 링크 갯수 : <span>${props.seriousCntBySgg ? numberComma(props.seriousCntBySgg) : 0}개</span></li>
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
            loadedWorkerData["BD_POPULATION"] = e.data.gridList;
            defaultLayer.drawGridLayer();
            if(!e.data.gridList || e.data.gridList.length === 0) {
				new ModalBuilder().init().alertBoby("해당일자의 데이터가 존재하지 않습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
                return;
            }
            const so = _Util.convertParamToObject(e.data.searchOption);
            for(const item of e.data.gridList) {
                let state = {};
                state["fltPop_"+item.timezn] = item.fltPop;
                state['sggNm'] = _Util.getSGGInfoByCode(item.cityCd+"0",GITS_ENV).sggNm;
                state['cell500'] = item.cell500;
                state['adstsdgNm'] = item.adstsdgNm;
                _Map.setFeatureState({
                    source: LAYER.GRID,
                    sourceLayer: grid_tileset_layer,
                    id: item.cell500,
                }, state);
            }
            function changeColorByLegnedTime(legend){
                /*_Map.setPaintProperty(LAYER.GRID, 'fill-color', [
                    'case',
                    ['==', ['feature-state',legend], null],
                    "rgba(0,0,0,0)",
                    ['<', ['feature-state',legend], 0],
                    LEGEND_COLOR.TRAFFIC_AMT[0],
                    ['all', ['>=', ['feature-state',legend], 0], ['<', ['feature-state',legend], 2000]],
                    LEGEND_COLOR.TRAFFIC_AMT[0],
                    ['all', ['>=', ['feature-state',legend], 2000], ['<', ['feature-state',legend], 3000]],
                    LEGEND_COLOR.TRAFFIC_AMT[1],
                    ['all', ['>=', ['feature-state',legend], 3000], ['<', ['feature-state',legend], 4000]],
                    LEGEND_COLOR.TRAFFIC_AMT[2],
                    ['all', ['>=', ['feature-state',legend], 4000], ['<', ['feature-state',legend], 5000]],
                    LEGEND_COLOR.TRAFFIC_AMT[3],
                    ['>=', ['feature-state',legend], 5000],
                    LEGEND_COLOR.TRAFFIC_AMT[4],
                    LEGEND_COLOR.TRAFFIC_AMT[4]
                ]);*/

                _Map.setPaintProperty(LAYER.GRID, 'fill-extrusion-color', [
                    'case',
                    ['==', ['feature-state',legend], null],
                    "rgba(0,0,0,0)",
                    ['<', ['feature-state',legend], 0],
                    LEGEND_COLOR.TRAFFIC_AMT[0],
                    ['all', ['>=', ['feature-state',legend], 0], ['<', ['feature-state',legend], 2000]],
                    LEGEND_COLOR.TRAFFIC_AMT[0],
                    ['all', ['>=', ['feature-state',legend], 2000], ['<', ['feature-state',legend], 3000]],
                    LEGEND_COLOR.TRAFFIC_AMT[1],
                    ['all', ['>=', ['feature-state',legend], 3000], ['<', ['feature-state',legend], 4000]],
                    LEGEND_COLOR.TRAFFIC_AMT[2],
                    ['all', ['>=', ['feature-state',legend], 4000], ['<', ['feature-state',legend], 5000]],
                    LEGEND_COLOR.TRAFFIC_AMT[3],
                    ['>=', ['feature-state',legend], 5000],
                    LEGEND_COLOR.TRAFFIC_AMT[4],
                    LEGEND_COLOR.TRAFFIC_AMT[4]
                ]);
                if(so.viewMode === "3d"){
                    _Map.setPaintProperty(LAYER.GRID, 'fill-extrusion-height', ['number',['feature-state',legend], 0]);
                }else{
                    _Map.setPaintProperty(LAYER.GRID, 'fill-extrusion-height', 0);
                }
                /*_Map.setPaintProperty(GITS_ENV.LAYER.GRID, 'fill-extrusion-height', [
                    'case',
                    ['==', ['feature-state',"fltPop_00"], null],
                    0,
                    ['<', ['feature-state',"fltPop_00"], 0],
                    0,
                    ['all', ['>=', ['feature-state',"fltPop_00"], 0], ['<', ['feature-state',"fltPop_00"], 2000]],
                    2000,
                    ['all', ['>=', ['feature-state',"fltPop_00"], 2000], ['<', ['feature-state',"fltPop_00"], 3000]],
                    2000,
                    ['all', ['>=', ['feature-state',"fltPop_00"], 3000], ['<', ['feature-state',"fltPop_00"], 4000]],
                    3000,
                    ['all', ['>=', ['feature-state',"fltPop_00"], 4000], ['<', ['feature-state',"fltPop_00"], 5000]],
                    4000,
                    ['>=', ['feature-state',"fltPop_00"], 5000],
                    5000,
                    0
                ]);*/
            }
            if(e.data.matrixChartData) {
                e.data.matrixChartData.data.datasets[0].backgroundColor = function(c) {
                    const value = c.dataset.data[c.dataIndex].v;
                    if (value < 0) {
                        return LEGEND_COLOR.TRAFFIC_AMT[0]
                    } else if (value >= 0 && value < 2000) {
                        return LEGEND_COLOR.TRAFFIC_AMT[0]
                    } else if (value >= 2000 && value < 3000) {
                        return LEGEND_COLOR.TRAFFIC_AMT[1]
                    } else if (value >= 3000 && value < 4000) {
                        return LEGEND_COLOR.TRAFFIC_AMT[2]
                    } else if (value >= 4000 && value < 5000) {
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
                    return ['예측인구(MAX) : ' + numberComma(v < 0 ? 0 : v)+'명'];
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
                core.control.generatePlayerModel("시간대 유동인구 밀집 예측 분석","", function(){

                    if(!playerInterval) clearInterval(playerInterval);
                    if(!progressInterval) clearInterval(progressInterval);
                    playerInterval = setInterval(function(){
                        const value = (time < 10 ? "0"+time : time);
                        const legend = "fltPop_"+value;
                        if(time === 24) {
                            clearInterval(playerInterval);
                            clearInterval(progressInterval);
                            $(".chart_video_container .tab_box_title").text("시간대 유동인구 밀집 예측 분석");
                            time = 0;
                            barTime = 0;
                            changeColorByLegnedTime("fltPop_00");
                            return;
                        }else{
                            changeColorByLegnedTime(legend);
                            $(".chart_video_container .tab_box_title").text("시간대 유동인구 밀집 예측 분석 - "+value+":00 데이터");
                        }
                        time++;
                    }, 3000);
                }, function(){
                    const legend = "fltPop_00";
                    changeColorByLegnedTime(legend);
                    $(".chart_video_container .tab_box_title").text("시간대 유동인구 밀집 예측 분석");
                    clearInterval(playerInterval);
                    clearInterval(progressInterval);
                }, false, true);
                //const chart = new Chart('danger_matrix_chart_canvas', e.data.matrixChartData);
            }
            core.control.showLayer(LAYER.GRID);
            changeColorByLegnedTime('fltPop_00');
            _Map.off('click', LAYER.GRID, MapEvents.BD_POPULATION);
            _Map.on('click', LAYER.GRID, MapEvents.BD_POPULATION);
            if(so.viewMode === "3d") {
                _Map.setPitch(65);
            }
        },
        "BD_PT_DANGER_ANALYSIS" : function(e){
            gitsApp.endLoading();
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }

            core.control.hideLayer(LAYER.LINK);
            core.control.hideLayer(LAYER.NODE);

            const sourceName = LAYER.BD_PT_DANGER_ANALYSIS;
            const layerName = LAYER.BD_PT_DANGER_ANALYSIS;
            const searchOption =  Object.fromEntries(new URLSearchParams(e.data.searchOption));
            core.control.removeCustomSource(sourceName);
            let layer = {
                'id': layerName,
                'type': 'circle',
                'source': sourceName,
                'paint': {
                    'circle-radius': 16,
                    'circle-color': 'rgb(239,138,98)',
                    'circle-stroke-color': 'white',
                    'circle-stroke-width': 1,
                    'circle-opacity': 0.7
                }
            }
            core.control.addExpertSourceAndLayer(sourceName,{},  e.data.featureCollection, [layer]);

            if(e.data.bbox) {
                try {
                    window.map.control.fitBounds(e.data.bbox);
                    _Map.fitBounds(e.data.bbox, {padding: 100});
                }catch(e){console.error('포커싱 에러')}
            }

            let header = ["차량번호","경도","위도","RPM","현재속도"];
            let metadata = ["carNo","lon","lat","rpm","spd"];
            let filename_suffix = "";
            switch(searchOption.collectType) {
                case "quickAccel" :
                    filename_suffix = "급가속";
                    header.concat(["급가속속도","급가속지속시간"]);
                    metadata.concat(["quickAccelOvdwSpd","quickAccelDuraTm"])
                    break;
                case "quickDecel" :
                    filename_suffix = "급감속";
                    header.concat(["급감속속도","급감속지속시간"]);
                    metadata.concat(["quickDecelOvdwSpd","quickDecelDuraTm"])
                    break;
                case "quickStop" :
                    filename_suffix = "급정지";
                    header.concat(["급정지속도","급정지지속시간"]);
                    metadata.concat(["quickStopOvdwSpd","quickStopDuraTm"])
                    break;
                case "quickStart" :
                    filename_suffix = "급출발";
                    header.concat(["급출발속도","급출발지속시간"]);
                    metadata.concat(["quickStartOvdwSpd","quickStartDuraTm"])
                    break;
                case "quickRouteChange" :
                    filename_suffix = "급진로변경";
                    break;
                case "quickOvertake" :
                    filename_suffix = "급앞지르기";
                    break;
                case "quickLfrtTurn" :
                    filename_suffix = "급좌우회전";
                    break;
                case "quickUturn" :
                    filename_suffix = "급유턴";
                    break;
            }


            core.control.generateExcelDownloadButton(function(){
                fnDownloadExcelWorker({
                    exportType : "featureCollection",
                    filename : "대중교통안전운행-"+filename_suffix+".xlsx",
                    header : header,
                    rows :  e.data.featureCollection.features,
                    metadata : metadata
                });
            });

        },
        "BD_DANGER_ROAD" : function(e) {
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
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

            core.control.generateExcelDownloadButton(function(){
                fnDownloadExcelWorker({
                    exportType : "featureCollection",
                    filename : "위험도로정보.xlsx",
                    header : ['위험상태명','위험상태등급','강수량유형','온도','시간강수량','주소'],
                    rows :  e.data.featureCollection.features,
                    metadata : ['dngrSttsNm','dngrSttsGrdNm','pcttTypeNm','tmprt','timePctt','roadNmAddr']
                });
            });

            core.control.addExpertSourceAndLayer(sourceName, {}, e.data.featureCollection, [layer]);

            _Map.off('click', layerId, MapEvents.BD_DANGER_ROAD);
            _Map.on('click', layerId, MapEvents.BD_DANGER_ROAD);

            const searchOptionMap =  Object.fromEntries(new URLSearchParams(e.data.searchOption));
            let title = "";
            switch(searchOptionMap.searchLocation) {
                case "searchAllLocation":
                case "" :
                    title = "경기도";
                    break;
                default :
                    let sg = _Util.getSGGInfoByCode(searchOptionMap.searchLocation, GITS_ENV);
                    if(sg) {
                        title = sg.sggNm;
                    }
            }

            let features = [...e.data.featureCollection.features];
            const sggNmList = [];
            const totalCntDataBySgg = [];
            const totalCntDataByType = {};
            const totalCntDataByTypeValues = [];
            const totalCntDataBySggAndType = {};
            const totalCntDataBySggAndYear = {};
            let yearList = [];
            const typeGroup = features.reduce(function(prev, cur, index){
                if(prev.indexOf(cur.properties.dngrSttsNm) < 0)
                    prev.push(cur.properties.dngrSttsNm);
                return prev;
            },[]);

            for(const sgg in GITS_ENV.SGG_INFO) {
                if(searchOptionMap.searchLocation && searchOptionMap.searchLocation !== "searchAllLocation") {
                    if(sgg !== searchOptionMap.searchLocation) {
                        continue;
                    }
                }
                sggNmList.push(sgg);
                const sggFilterList = features.filter((obj) => obj.properties.lotnoAddr.includes(`경기 ${sgg}`) ||  obj.properties.lotnoAddr.includes(`경기도 ${sgg}`));

                $("select[name='searchYear'] option").each(function(){
                    if($(this).attr("value") && $(this).attr("value") !== "searchAllYear") {
                        totalCntDataBySggAndYear[sgg] = totalCntDataBySggAndYear[sgg] || {};
                        let v = $(this).attr("value");
                        if(yearList.indexOf(v) < 0) yearList.push(v);
                        totalCntDataBySggAndYear[sgg][v] = sggFilterList.filter((obj) => obj.properties.etlDt.indexOf(v) === 0).length;
                    }
                });
                for(const type of typeGroup){
                    totalCntDataBySggAndType[sgg] = totalCntDataBySggAndType[sgg] || {};
                    totalCntDataBySggAndType[sgg][type] = sggFilterList.filter((obj) => obj.properties.dngrSttsNm===type).length;
                    const typeFilterList = features.filter((obj) => obj.properties.dngrSttsNm===type);
                    totalCntDataByType[type] = (totalCntDataByType[type] || 0) + typeFilterList.length;
                    totalCntDataByTypeValues.push(typeFilterList.length);
                }
                const totalLength = sggFilterList.length;
                totalCntDataBySgg.push(totalLength);
            }
            let detailContainer = core.control.generateBigdataDetailContainer()
                .generateBigdataDetailItem(`${title} 전체 데이터`, `<table>
                    <tbody>
                    <tr>
                        <th>검색연도</th>
                        <td>${searchOptionMap.searchYear ? searchOptionMap.searchYear+"년" : "전체"}</td>
                    </tr>
                    <tr>
                        <th>위험도로 수</th>
                        <td>${numberComma(features.length)}개</td>
                    </tr>
                    </tbody>
                </table>
                <div class="bigdataDetailContentCanvasWrap none-min" style="height:200px;">
                    <canvas id="bdpattern_detail_canvas_1"></canvas>
                </div>`, function() {
                    new GITSChart(GITSChartType.BAR).init("bdpattern_detail_canvas_1")
                        .setViewLabel()
                        .setDataSetArrayLabel(typeGroup)
                        .setDataSet({
                            label : '건',
                            data : totalCntDataByTypeValues,
                            backgroundColor: function(context) {
                                const chart = context.chart;
                                const {ctx, chartArea} = chart;
                                let gradient, width, height;
                                if (!chartArea) {
                                    // This case happens on initial chart load
                                    return;
                                }
                                const chartWidth = chartArea.right - chartArea.left;
                                const chartHeight = chartArea.bottom - chartArea.top;
                                if (!gradient || width !== chartWidth || height !== chartHeight) {
                                    // Create the gradient because this is either the first render
                                    // or the size of the chart has changed
                                    width = chartWidth;
                                    height = chartHeight;
                                    gradient = ctx.createLinearGradient(0, chartArea.bottom, 0, chartArea.top);
                                    gradient.addColorStop(0, "#68d8ff");
                                    gradient.addColorStop(0.7, "#9900ff");
                                    gradient.addColorStop(1, "#ff0080");
                                }

                                return gradient;
                            },
                            borderRadius:1,
                            fill: true
                        }).setOption({
                        indexAxis: 'x',
                        plugins: {
                            legend: {
                                display:false,
                                labels : {
                                    color : "white"
                                }
                            },
                            datalabels: {
                                anchor: 'middle', // remove this line to get label in middle of the bar
                                align: 'end',
                                clamp : true,
                                formatter: function(val) {
                                    return numberComma(val);
                                },
                                labels: {
                                    value: {
                                        color: '#fff'
                                    }
                                }

                            }
                        },
                        interaction: {
                            mode: 'nearest',
                            axis: 'x',
                            intersect: false
                        },
                        scales : {
                            x : {
                                ticks : {
                                    autoSkip : false,
                                    color:"white",
                                    font: {
                                        size : 10
                                    },
                                    maxRotation: 90,
                                    minRotation: 90,
                                }
                            },
                            y : {
                                ticks : {
                                    autoSkip : false,
                                    color:"white",
                                    font: {
                                        size : 10
                                    }
                                },
                                grid: {
                                    color : "rgba(255,255,255,0.2)",
                                    tickColor : "rgba(255,255,255,0.2)",
                                    display:true
                                }
                            }
                        }
                    })
                        .draw();
                });
                if(sggNmList.length > 1) {
                    detailContainer.generateBigdataDetailItem(`시군별 유형 데이터`, `<div class="bigdataDetailContentCanvasWrap none-min" style="height:300px;">
                    <canvas id="bdpattern_detail_canvas_2"></canvas>
                </div>`, function () {
                        let dataSetArray = [];
                        for (const type of typeGroup) {
                            let data = [];
                            for (const sgg in GITS_ENV.SGG_INFO) {
                                if (searchOptionMap.searchLocation && searchOptionMap.searchLocation !== "searchAllLocation") {
                                    if (sgg !== searchOptionMap.searchLocation) {
                                        continue;
                                    }
                                }
                                data.push(totalCntDataBySggAndType[sgg][type] ? totalCntDataBySggAndType[sgg][type] : 0);
                            }
                            /*const randColor = "#"+Math.floor(Math.random()*16777215).toString(16);*/
                            const randColor = GITS_ENV.ROAD_DANGER[type];
                            dataSetArray.push({
                                label: type,
                                data: data,
                                backgroundColor: _Util.hexToRgbA(randColor, 0.6),
                                borderColor: _Util.hexToRgbA(randColor, 0.6),
                                borderWidth: 1,
                                borderRadius: 1,
                                tension: 0.6
                            });
                        }
                        new GITSChart(GITSChartType.LINE).init("bdpattern_detail_canvas_2")
                            .setDataSetArrayLabel(sggNmList)
                            .setDataArraySet(dataSetArray).setOption({
                            indexAxis: 'x',
                            plugins: {
                                legend: {
                                    usePointStyle: false,
                                    labels: {
                                        color: "#fff",
                                        padding: 10,
                                        boxWidth: 5,
                                        font: {
                                            family: 'Pretendard',
                                            size: 10,
                                        }
                                    },
                                },
                                datalabels: {
                                    anchor: 'middle', // remove this line to get label in middle of the bar
                                    align: 'end',
                                    clamp: true,
                                    formatter: function (val) {
                                        return numberComma(val);
                                    },
                                    labels: {
                                        value: {
                                            color: '#fff'
                                        }
                                    }

                                }
                            },
                            interaction: {
                                mode: 'index',
                                axis: 'x',
                                intersect: false
                            },
                            scales: {
                                x: {
                                    ticks: {
                                        autoSkip: false,
                                        color: "white",
                                        font: {
                                            size: 10
                                        },
                                        maxRotation: 90,
                                        minRotation: 90,
                                    }
                                },
                                y: {
                                    ticks: {
                                        autoSkip: false,
                                        color: "white",
                                        font: {
                                            size: 10
                                        }
                                    },
                                    grid: {
                                        color: "rgba(255,255,255,0.2)",
                                        tickColor: "rgba(255,255,255,0.2)",
                                        display: true
                                    }
                                }
                            }
                        })
                            .draw();
                    })
                }

                if(!searchOptionMap.searchYear) {
                    detailContainer.generateBigdataDetailItem(`년도별 시군구 위험도로수 증감추이`, `<div class="bigdataDetailContentCanvasWrap none-min" style="height:300px;">
                    <canvas id="bdpattern_detail_canvas_3"></canvas>
                </div>`, function () {
                        let dataSetArray = [];
                        yearList = yearList.sort(function(a,b){
                            return a - b;
                        });
                        for (const sgg in totalCntDataBySggAndYear) {
                            /*const randColor = "#"+Math.floor(Math.random()*16777215).toString(16);*/
                            let data = [];
                            for(const year of yearList) {
                                data.push(totalCntDataBySggAndYear[sgg][year])
                            }
                            let color = GITS_ENV.SGG_INFO[sgg] ? GITS_ENV.SGG_INFO[sgg].COLOR : "#df6100";
                            dataSetArray.push({
                                label: sgg,
                                data: data,
                                backgroundColor: _Util.hexToRgbA(color, 0.6),
                                borderColor: _Util.hexToRgbA(color, 0.6),
                                borderWidth: 1,
                                borderRadius: 1,
                                tension: 0.6
                            });
                        }
                        new GITSChart(GITSChartType.LINE).init("bdpattern_detail_canvas_3")
                            .setDataSetArrayLabel(yearList)
                            .setDataArraySet(dataSetArray).setOption({
                            indexAxis: 'x',
                            plugins: {
                                legend: {
                                    usePointStyle: false,
                                    labels: {
                                        color: "#fff",
                                        padding: 10,
                                        boxWidth: 5,
                                        font: {
                                            family: 'Pretendard',
                                            size: 10,
                                        }
                                    },
                                },
                                datalabels: {
                                    anchor: 'middle', // remove this line to get label in middle of the bar
                                    align: 'end',
                                    clamp: true,
                                    formatter: function (val) {
                                        return numberComma(val);
                                    },
                                    labels: {
                                        value: {
                                            color: '#fff'
                                        }
                                    }

                                }
                            },
                            interaction: {
                                mode: 'index',
                                axis: 'x',
                                intersect: false
                            },
                            scales: {
                                x: {
                                    ticks: {
                                        autoSkip: false,
                                        color: "white",
                                        font: {
                                            size: 10
                                        },
                                        maxRotation: 90,
                                        minRotation: 90,
                                    }
                                },
                                y: {
                                    ticks: {
                                        autoSkip: false,
                                        color: "white",
                                        font: {
                                            size: 10
                                        }
                                    },
                                    grid: {
                                        color: "rgba(255,255,255,0.2)",
                                        tickColor: "rgba(255,255,255,0.2)",
                                        display: true
                                    }
                                }
                            }
                        })
                            .draw();
                    })
                }
            detailContainer.show();
        },
        "BD_DANGER_ZONE_BY_TYPE_HEATMAP" : function(e) {
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
            const sourceName = GITS_ENV.LAYER_PREFIX+"BD_DANGER_ZONE_BY_TYPE";
            const layerId = GITS_ENV.LAYER_PREFIX+"BD_DANGER_ZONE_BY_TYPE";
            const circlelayerId = GITS_ENV.LAYER_PREFIX+"BD_DANGER_ZONE_BY_TYPE_CIRCLE";
            core.control.removeCustomSource(sourceName);

            let layer = {
                'id': layerId,
                'type': 'heatmap',
                'source': sourceName,
                'maxzoom': 22,
                'minzoom': 8,
                'paint': {
                    'heatmap-weight': [
                        'interpolate',
                        ['linear'],
                        ["get","acdntCnt"],
                        0, 0,
                        200, 1,
                    ],
                    // 줌 level 강도
                    'heatmap-intensity': [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        0,
                        3,
                        9,
                        8
                    ],
                    // 밀도에 따라 색상값 할당
                    'heatmap-color': [
                        'interpolate',
                        ['linear'],
                        ['heatmap-density'],
                        0,
                        'rgba(33,102,172,0)',
                        0.2,
                        'rgb(103,169,207)',
                        0.4,
                        'rgb(209,229,240)',
                        0.6,
                        'rgb(253,219,199)',
                        0.8,
                        'rgb(239,138,98)',
                        1,
                        'rgb(178,24,43)'
                    ],
                    // 줌에 맞게 크기 변경
                    'heatmap-radius':[
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        0,
                        2,
                        9,
                        20
                    ],
                    // 줌에 맞게 투명도 조절
                    'heatmap-opacity': [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        7,
                        1,
                        9,
                        1,
                        13,
                        0.7
                    ]
                }
            }
            let circleLayer = {
                'id': circlelayerId,
                'type': 'circle',
                'source': sourceName,
                'minzoom': 13,
                'paint': {
                    'circle-radius': [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        7,
                        ['interpolate', ['linear'], ['get', 'acdntCnt'], 1, 1, 6, 1],
                        16,
                        ['interpolate', ['linear'], ['get', 'acdntCnt'], 1, 2, 6, 5]
                    ],
                    'circle-color': [
                        'interpolate',
                        ['linear'],
                        ['get', 'acdntCnt'],
                        1,
                        'rgba(33,102,172,0)',
                        2,
                        'rgb(103,169,207)',
                        3,
                        'rgb(209,229,240)',
                        4,
                        'rgb(253,219,199)',
                        5,
                        'rgb(239,138,98)',
                        6,
                        'rgb(178,24,43)'
                    ],
                    'circle-stroke-color': 'white',
                    'circle-stroke-width': 1,
                    'circle-opacity': [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        7,
                        0,
                        8,
                        1
                    ]
                }
            }
            core.control.addExpertSourceAndLayer(sourceName, {}, e.data.collection.featureCollection, [circleLayer,layer], LAYER.NODE);
            if(document.querySelector("#layerControlList [data-layer='taas']"))
                document.querySelector("#layerControlList [data-layer='taas']").disabled = false;
        },
        "BD_DANGER_ZONE_BY_TYPE" : function(e) {
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
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

            core.control.generateExcelDownloadButton(function(){
                fnDownloadExcelWorker({
                    exportType : "featureCollection",
                    filename : "교통사고 위험지역 정보.xlsx",
                    header : ['지점명','사고수','사상자수','사망자수','중상자수','경상지수','부상신고수','타입'],
                    rows :  e.data.collection.featureCollection.features,
                    metadata : ['pointNm','acdntCnt','casltCnt','dcsdCnt','swpsnCnt','sinjpsnCnt','injDclrCnt','type']
                });
            });

            core.control.addExpertSourceAndLayer(sourceName, {}, e.data.collection.featureCollection, [layer]);
            _Map.off('click', layerId, MapEvents.BD_DANGER_ZONE_BY_TYPE);
            _Map.on('click', layerId, MapEvents.BD_DANGER_ZONE_BY_TYPE);

            const searchOptionMap =  Object.fromEntries(new URLSearchParams(e.data.searchOption));
            let title = "";
            switch(searchOptionMap.searchLocation) {
                case "searchAllLocation":
                case "" :
                    title = "경기도";
                    break;
                default :
                    let sg = _Util.getSGGInfoByCode(searchOptionMap.searchLocation, GITS_ENV);
                    if(sg) {
                        title = sg.sggNm;
                    }
            }
            const barCommonOption = {
                indexAxis: 'x',
                plugins: {
                    legend: {
                        display:true,
                        labels : {
                            color:"#fff",
                            padding:10,
                            boxWidth:5,
                            font: {
                                family:'Pretendard',
                                size:10,
                            }
                        }
                    },
                },
                interaction: {
                    mode: 'index',
                    axis: 'x',
                    intersect: false
                },
                scales : {
                    x : {
                        ticks : {
                            autoSkip : false,
                            color:"white",
                            font: {
                                size : 10
                            },
                            maxRotation: 90,
                            minRotation: 90,
                        }
                    },
                    y : {
                        ticks : {
                            autoSkip : false,
                            color:"white",
                            font: {
                                size : 10
                            }
                        },
                        grid: {
                            color : "rgba(255,255,255,0.2)",
                            tickColor : "rgba(255,255,255,0.2)",
                            display:true
                        }
                    }
                }
            }
            let features = [...e.data.collection.featureCollection.features];
            const sggNmList = [];
            const totalCntDataBySggAndType = {};
            const totalDcsdCntDataBySggAndType = {};
            const totalCntDataBySgg = {};
            const totalDcsdCntDataBySgg = {};
            const typeGroup = features.reduce(function(prev, cur, index){
                if(prev.indexOf(GITS_ENV.ACCIDENT_TYPE[cur.properties.type]) < 0)
                    prev.push(GITS_ENV.ACCIDENT_TYPE[cur.properties.type]);
                return prev;
            },[]);
            typeGroup.sort(function(a,b){
                return a < b ? -1 : a > b ? 1 : 0;
            });
            for(const sgg in GITS_ENV.SGG_INFO) {
                if(searchOptionMap.searchLocation && searchOptionMap.searchLocation !== "searchAllLocation") {
                    if(GITS_ENV.SGG_INFO[sgg].CODE !== searchOptionMap.searchLocation) {
                        continue;
                    }
                }
                sggNmList.push(sgg);
                const sggFilterList = features.filter((obj) => obj.properties.sggCd+"0" === GITS_ENV.SGG_INFO[sgg].CODE);
                totalCntDataBySgg[sgg] = sggFilterList
                    .reduce(function(prev, cur){
                        return prev + cur.properties.acdntCnt;
                    }, 0);
                totalDcsdCntDataBySgg[sgg] = sggFilterList
                    .reduce(function(prev, cur){
                        return prev + cur.properties.casltCnt;
                    }, 0);
                for(const type of typeGroup){
                    totalCntDataBySggAndType[sgg] = totalCntDataBySggAndType[sgg] || {};
                    totalDcsdCntDataBySggAndType[sgg] = totalDcsdCntDataBySggAndType[sgg] || {};
                    totalCntDataBySggAndType[sgg][type] = sggFilterList
                        .filter((obj) => GITS_ENV.ACCIDENT_TYPE[obj.properties.type] === type)
                        .reduce(function(prev, cur){
                            return prev + cur.properties.acdntCnt;
                        }, 0);
                    totalDcsdCntDataBySggAndType[sgg][type] = sggFilterList
                        .filter((obj) => GITS_ENV.ACCIDENT_TYPE[obj.properties.type] === type)
                        .reduce(function(prev, cur){
                            return prev + cur.properties.casltCnt;
                        }, 0);
                }
            }


            let totalAcdntCount = 0;
            let totalCasltCnt = 0;
            let totalDcsdCnt = 0;
            let totalSwpsnCnt = 0;
            let totalSinjpsnCnt = 0;
            let totalInjDclrCnt = 0;
            const typeAcdntTotalGroup = features.reduce(function(prev, cur) {
                prev["acdntCnt"] = prev["acdntCnt"] || [];
                prev["casltCnt"] = prev["casltCnt"] || [];
                prev["dcsdCnt"] = prev["dcsdCnt"] || [];
                prev["swpsnCnt"] = prev["swpsnCnt"] || [];
                prev["sinjpsnCnt"] = prev["sinjpsnCnt"] || [];
                prev["injDclrCnt"] = prev["injDclrCnt"] || [];
                totalAcdntCount = totalAcdntCount + cur.properties.acdntCnt;
                totalCasltCnt = totalCasltCnt + cur.properties.casltCnt;
                totalDcsdCnt = totalDcsdCnt + cur.properties.dcsdCnt;
                totalSwpsnCnt = totalSwpsnCnt + cur.properties.swpsnCnt;
                totalSinjpsnCnt = totalSinjpsnCnt + cur.properties.sinjpsnCnt;
                totalInjDclrCnt = totalInjDclrCnt + cur.properties.injDclrCnt;
                let typeIdx = 0;
                for(const type of typeGroup){
                    prev["acdntCnt"][typeIdx] = (prev["acdntCnt"][typeIdx] || 0) + (GITS_ENV.ACCIDENT_TYPE[cur.properties.type] === type ? cur.properties.acdntCnt : 0);
                    prev["casltCnt"][typeIdx] = (prev["casltCnt"][typeIdx] || 0) + (GITS_ENV.ACCIDENT_TYPE[cur.properties.type] === type ? cur.properties.casltCnt : 0);
                    prev["dcsdCnt"][typeIdx] = (prev["dcsdCnt"][typeIdx] || 0) + (GITS_ENV.ACCIDENT_TYPE[cur.properties.type] === type ? cur.properties.dcsdCnt : 0);
                    prev["swpsnCnt"][typeIdx] = (prev["swpsnCnt"][typeIdx] || 0) + (GITS_ENV.ACCIDENT_TYPE[cur.properties.type] === type ? cur.properties.swpsnCnt : 0);
                    prev["sinjpsnCnt"][typeIdx] = (prev["sinjpsnCnt"][typeIdx] || 0) + (GITS_ENV.ACCIDENT_TYPE[cur.properties.type] === type ? cur.properties.sinjpsnCnt : 0);
                    prev["injDclrCnt"][typeIdx] = (prev["injDclrCnt"][typeIdx] || 0) + (GITS_ENV.ACCIDENT_TYPE[cur.properties.type] === type ? cur.properties.injDclrCnt : 0);
                    typeIdx++;
                }
                return prev;
            }, {});
            core.control.generateBigdataDetailContainer()
                .generateBigdataDetailItem(`${title} 전체 데이터`, `<table>
                    <tbody>
                    <tr>
                        <th>검색연도</th>
                        <td>${searchOptionMap.searchYear}년 ${searchOptionMap.searchMonth ? searchOptionMap.searchMonth+"월" : ""}</td>
                    </tr>
                    <tr>
                        <th>전체 사고수</th>
                        <td>
                            ${numberComma(totalAcdntCount)}건
                        </td>
                    </tr>
                    <tr>
                        <th>전체 사상자수</th>
                        <td>
                            ${numberComma(totalCasltCnt)}건
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="bigdataDetailContentCanvasWrap none-min" style="height:270px;">
                    <canvas id="bdpattern_detail_canvas_1"></canvas>
                </div>`, function() {
                    new GITSChart(GITSChartType.DOUGHNUT).init("bdpattern_detail_canvas_1")
                        .setViewLabel()
                        .setData({
                            labels : ['사망자수','중상자수','경상자수','부상신고수'],
                            datasets: [{
                                label : ['사망자수','중상자수','경상자수','부상신고수'],
                                data : [totalDcsdCnt, totalSwpsnCnt, totalSinjpsnCnt, totalInjDclrCnt],
                                backgroundColor : ['#e31a1c','#fd8d3c','#FFD600','#AFED00'],
                                datalabels: {
                                    anchor: 'end'
                                }
                            }],
                        })
                        .setOption({
                            plugins : {
                                legend : {
                                    position : "left",
                                    labels: {
                                        usePointStyle: true,
                                        color : "white"
                                    },
                                },
                                datalabels: {
                                    backgroundColor: function(context) {
                                        return context.dataset.backgroundColor;
                                    },
                                    borderColor: 'white',
                                    borderRadius: 25,
                                    borderWidth: 2,
                                    color: 'white',
                                    display: function(context) {
                                        var dataset = context.dataset;
                                        var count = dataset.data.length;
                                        var value = dataset.data[context.dataIndex];
                                        return true;
                                        //return value > count * 1.5;
                                    },
                                    font: {
                                        weight: 'bold'
                                    },
                                    padding: 6,
                                    formatter: Math.round
                                }
                            },
                            cutoutPercentage: 32,
                            layout: {
                                padding: 16
                            },
                            elements: {
                                line: {
                                    fill: false
                                },
                                point: {
                                    hoverRadius: 7,
                                    radius: 5
                                }
                            },
                        })
                        .draw();
                })
                .generateBigdataDetailItem(`사고유형별 사상자수 데이터`, `<div class="bigdataDetailContentCanvasWrap none-min" style="height:270px;">
                    <canvas id="bdpattern_detail_canvas_2"></canvas>
                </div>`, function() {
                    new GITSChart(GITSChartType.BAR).init("bdpattern_detail_canvas_2")
                        .setDataSetArrayLabel(typeGroup)
                        .setDataSet({
                            label : '사고수',
                            data : typeAcdntTotalGroup.acdntCnt,
                            backgroundColor: "#3fbfde",
                            borderRadius:1,
                            fill: true
                        },{
                            label : '사망자수',
                            data : typeAcdntTotalGroup.dcsdCnt,
                            backgroundColor: "#e31a1c",
                            borderRadius:1,
                            fill: true
                        },{
                            label : '중상자수',
                            data : typeAcdntTotalGroup.swpsnCnt,
                            backgroundColor: "#fd8d3c",
                            borderRadius:1,
                            fill: true
                        },{
                            label : '경상자수',
                            data : typeAcdntTotalGroup.sinjpsnCnt,
                            backgroundColor: "#FFD600",
                            borderRadius:1,
                            fill: true
                        },{
                            label : '부상신고수',
                            data : typeAcdntTotalGroup.injDclrCnt,
                            backgroundColor: "#AFED00",
                            borderRadius:1,
                            fill: true
                        }).setOption(barCommonOption)
                        .draw();
                }).generateBigdataDetailItem(`시군 유형별 사고수 데이터`, `<div class="bigdataDetailContentCanvasWrap none-min" style="height:450px">
                    <canvas id="bdpattern_detail_canvas_3"></canvas>
                </div>`, function() {
                    let dataSetArray = [];
                    for(const type of typeGroup){
                    let data = [];
                    for(const sgg in GITS_ENV.SGG_INFO) {
                        if(searchOptionMap.searchLocation && searchOptionMap.searchLocation !== "searchAllLocation") {
                            if(GITS_ENV.SGG_INFO[sgg].CODE !== searchOptionMap.searchLocation) {
                                continue;
                            }
                        }
                        data.push(totalCntDataBySggAndType[sgg][type] ? totalCntDataBySggAndType[sgg][type] : 0);
                    }
                    dataSetArray.push({
                        label : type,
                        data : data,
                        backgroundColor: _Util.hexToRgbA(GITS_ENV.ACCIDENT_COLOR[type],0.7) ?? "red",
                        borderColor: GITS_ENV.ACCIDENT_COLOR[type],
                        borderWidth: 1,
                        borderRadius:1,
                        fill: true,
                        tension: 0.5
                    });
                }
                new GITSChart(GITSChartType.RADAR).init("bdpattern_detail_canvas_3")
                    .setDataSetArrayLabel(sggNmList)
                    .setDataArraySet(dataSetArray).setOption({
                    plugins: {
                        legend: {
                            labels : {
                                color:"#fff",
                                padding:10,
                                boxWidth:5,
                                font: {
                                    family:'Pretendard',
                                    size:10,
                                }
                            }
                        }
                    },
                    interaction: {
                        mode: 'index',
                        axis: 'r'
                    },
                    scales : {
                        r : {
                            ticks : {
                                display :false
                            },
                            pointLabels : {
                                color : "white",
                            },
                            angleLines : {color:"white"},
                            grid : {
                                color: "white"
                            }
                        }
                    }
                })
                    .draw();
            }).generateBigdataDetailItem(`시군 유형별 사상자수 데이터`, `<div class="bigdataDetailContentCanvasWrap none-min" style="height:450px">
                    <canvas id="bdpattern_detail_canvas_4"></canvas>
                </div>`, function() {
                let dataSetArray = [];
                for(const type of typeGroup){
                    let data = [];
                    for(const sgg in GITS_ENV.SGG_INFO) {
                        if(searchOptionMap.searchLocation && searchOptionMap.searchLocation !== "searchAllLocation") {
                            if(GITS_ENV.SGG_INFO[sgg].CODE !== searchOptionMap.searchLocation) {
                                continue;
                            }
                        }
                        data.push(totalDcsdCntDataBySggAndType[sgg][type] ? totalDcsdCntDataBySggAndType[sgg][type] : 0);
                    }
                    dataSetArray.push({
                        label : type,
                        data : data,
                        backgroundColor: _Util.hexToRgbA(GITS_ENV.ACCIDENT_COLOR[type],0.7) ?? "red",
                        borderColor: GITS_ENV.ACCIDENT_COLOR[type],
                        borderWidth: 1,
                        borderRadius:1,
                        fill: true,
                        tension: 0.5
                    });
                }
                new GITSChart(GITSChartType.RADAR).init("bdpattern_detail_canvas_4")
                    .setDataSetArrayLabel(sggNmList)
                    .setDataArraySet(dataSetArray).setOption({
                    plugins: {
                        legend: {
                            labels : {
                                color:"#fff",
                                padding:10,
                                boxWidth:5,
                                font: {
                                    family:'Pretendard',
                                    size:10,
                                }
                            }
                        }
                    },
                    interaction: {
                        mode: 'index',
                        axis: 'r'
                    },
                    scales : {
                        r : {
                            ticks : {
                                display :false
                            },
                            pointLabels : {
                                color : "white",
                            },
                            angleLines : {color:"white"},
                            grid : {
                                color: "white"
                            }
                        }
                    }
                })
                    .draw();
            }).show();

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
            for(const m in markersOnScreen) {
                markersOnScreen[m].remove();
            }
            for(const maker of openMarkers) {
                maker.remove();
            }
            openMarkers= [];
            markersOnScreen = {};
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
                                        <li class="popup_item">사상자수 : <span>${props.casltCnt ? numberComma(props.casltCnt) : 0}명</span></li>
                                        <li class="popup_item">사망자수 : <span>${props.dcsdCnt ? numberComma(props.dcsdCnt) : 0}명</span></li>
                                        <li class="popup_item">중상자수 : <span>${props.swpsnCnt ? numberComma(props.swpsnCnt) : 0}명</span></li>
                                        <li class="popup_item">경상자수 : <span>${props.sinjpsnCnt ? numberComma(props.sinjpsnCnt) : 0}명</span></li>
                                        <li class="popup_item">부상자신고수 : <span>${props.injDclrCnt ? numberComma(props.injDclrCnt) : 0}명</span></li>
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
        "BD_PATTERN_TRAFFIC_CNGSTN" : function(e){
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
            const sourceName = LAYER.BD_PATTERN_CONGESTION;
            const layerName = LAYER.BD_PATTERN_CONGESTION;
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
                    'line-width': [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        10, 2,
                        15, 7,
                        18, 8
                    ],
                    'line-opacity': 1,
                },
                filter : ["==",'linkLvl',"10"]
                /*filter : [
                    "==", ["zoom"],
                    ["match", ['to-number',["get", "linkLvl"]],
                        3,  // rank
                        8, // minimum zoom level
                        4,  // etc.
                        9,
                        5,
                        10,
                        6,
                        11,
                        7,
                        12,
                        8,
                        13,
                        9,
                        14,
                        10,
                        15,
                        15
                    ]
                ]*/
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [lineLayer])
            core.control.hideLayer(LAYER.NODE);

            _Map.off('click', layerName, MapEvents.BD_PATTERN_TRAFFIC_CNGSTN);
            _Map.on('click', layerName, MapEvents.BD_PATTERN_TRAFFIC_CNGSTN);

            let excelData = e.data.featureCollection.features.filter((obj) => obj.properties.linkLvl === "10");
            core.control.generateExcelDownloadButton(function(){
                fnDownloadExcelWorker({
                    exportType : "featureCollection",
                    filename : "상습정체구간.xlsx",
                    header : ['날짜','도로구분','노선명','서비스링크ID','도로명','시작지점','종료지점','방면','구간길이','평균속도','평균지체시간'],
                    rows :  excelData,
                    metadata : ['anlsMm','roadDiv','routeNm','linkId','roadNm','startNodeNm','endNodeNm','routeDrct','sctnLen','avgSpeed','avgCngstnTime']
                });
            });

            const so = _Util.convertParamToObject(e.data.searchOption);
            function congetionDetailCommonFnc(_this, roadRank, label, canvasId){
                $.ajax({
                    type : "get",
                    url : __contextPath__+"/bigdata/getPatternSvcCongetionTop10.ajax",
                    data : {
                        startDate : so.startDate,
                        searchRoadRank : roadRank
                    },
                    beforeSend : function(){
                        _this.element.find(".chart-preloading-wrap").remove();
                        _this.element.find(".bigdataDetailContentCanvasWrap").append(GITS_ENV.UI.CHART_PRELOADING());
                    },
                    error : function(){
                        _this.element.find(".bigdataDetailContentCanvasWrap").addClass("is-complete");
                        _this.element.find(".chart-preloading-wrap").remove();
                    },
                    success : function(data){
                        _this.element.find(".bigdataDetailContentCanvasWrap").addClass("is-complete");
                        _this.element.find(".chart-preloading-wrap").remove();
                        let xArray = [];
                        let valueArray = [];
                        data.forEach((item) => {
                            xArray.push([item.routeNm,"["+item.startNodeNm+"->"+item.endNodeNm+"]["+item.routeDrct+"]"]);
                            valueArray.push(item["avgCngstnTime"]);
                        });
                        new GITSChart(GITSChartType.BAR).init(canvasId)
                            .setViewLabel()
                            .setDataSetArrayLabel(xArray)
                            .setDataSet({
                                label : label,
                                data : valueArray,
                                backgroundColor: function(context) {
                                    const chart = context.chart;
                                    const {ctx, chartArea} = chart;
                                    let gradient, width, height;
                                    if (!chartArea) {
                                        // This case happens on initial chart load
                                        return;
                                    }
                                    const chartWidth = chartArea.right - chartArea.left;
                                    const chartHeight = chartArea.bottom - chartArea.top;
                                    if (!gradient || width !== chartWidth || height !== chartHeight) {
                                        // Create the gradient because this is either the first render
                                        // or the size of the chart has changed
                                        width = chartWidth;
                                        height = chartHeight;
                                        gradient = ctx.createLinearGradient(0, chartArea.top, chartArea.right, chartArea.bottom);
                                        gradient.addColorStop(0, "#68d8ff");
                                        gradient.addColorStop(0.7, "#9900ff");
                                        gradient.addColorStop(1, "#ff0080");
                                    }
                                    return gradient;
                                },
                                borderRadius:1,
                                fill: true
                            }).setOption({
                            indexAxis: 'y',
                            plugins: {
                                legend: {
                                    display:false,
                                    labels : {
                                        color : "white"
                                    }
                                },
                                datalabels: {
                                    anchor: 'end', // remove this line to get label in middle of the bar
                                    align: 'start',
                                    clamp : true,
                                    formatter: function(val) {
                                        return numberComma(val)+"시간";
                                    },
                                    labels: {
                                        value: {
                                            color: '#fff'
                                        }
                                    }

                                }
                            },
                            interaction: {
                                mode: 'nearest',
                                axis: 'x',
                                intersect: false
                            },
                            scales : {
                                y : {
                                    ticks : {
                                        autoSkip : false,
                                        color:"white",
                                        font: {
                                            size : 10
                                        }
                                    }
                                },
                                x : {
                                    ticks : {
                                        autoSkip : false,
                                        color:"white",
                                        font: {
                                            size : 10
                                        }
                                    },
                                    grid: {
                                        color : "rgba(255,255,255,0.2)",
                                        tickColor : "rgba(255,255,255,0.2)",
                                        display:true
                                    }
                                }
                            }
                        })
                            .draw();
                    }
                })
            }
            core.control.generateBigdataDetailContainer()
                .generateBigdataDetailItem("[고속국도] 상습정체구간 TOP 10",'<div class="bigdataDetailContentCanvasWrap" style="height:320px;"><canvas id="bdpattern_detail_canvas_1"></canvas></div>', function(){
                    congetionDetailCommonFnc(this,'고속국도','고속도로 일평균정체시간', "bdpattern_detail_canvas_1");
                }).generateBigdataDetailItem("[도시고속국도] 상습정체구간 TOP 10",'<div class="bigdataDetailContentCanvasWrap" style="height:320px;"><canvas id="bdpattern_detail_canvas_2"></canvas></div>', function(){
                    congetionDetailCommonFnc(this,'도시고속국도','도시고속도로 일평균정체시간', "bdpattern_detail_canvas_2");
                 }).generateBigdataDetailItem("[일반국도] 상습정체구간 TOP 10",'<div class="bigdataDetailContentCanvasWrap" style="height:320px;"><canvas id="bdpattern_detail_canvas_3"></canvas></div>', function(){
                congetionDetailCommonFnc(this,'일반국도','일반국도 일평균정체시간', "bdpattern_detail_canvas_3");
            }).generateBigdataDetailItem("[지방도] 상습정체구간 TOP 10",'<div class="bigdataDetailContentCanvasWrap" style="height:320px;"><canvas id="bdpattern_detail_canvas_4"></canvas></div>', function(){
                congetionDetailCommonFnc(this,'지방도','지방도 일평균정체시간', "bdpattern_detail_canvas_4");
            }).generateBigdataDetailItem("[시군도] 상습정체구간 TOP 10",'<div class="bigdataDetailContentCanvasWrap" style="height:320px;"><canvas id="bdpattern_detail_canvas_5"></canvas></div>', function(){
                congetionDetailCommonFnc(this,'시군도','시군도 일평균정체시간', "bdpattern_detail_canvas_5");
            }).show();
        },
        "BD_PATTERN_TRAFFIC_ABN_LOS" : function(e) {
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
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
                    'line-width': [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        10, 2,
                        15, 7,
                        18, 8
                    ],
                    'line-opacity': 1,
                },
                filter : ['has', 'linkId']
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [lineLayer])
            core.control.hideLayer(LAYER.NODE);

            _Map.off('click', layerName, MapEvents.BD_PATTERN_TRAFFIC_QUANTITY);
            _Map.off('click', layerName, MapEvents.BD_PATTERN_TRAFFIC_ABN_LOS);
            _Map.on('click', layerName, MapEvents.BD_PATTERN_TRAFFIC_ABN_LOS);
        },
        "BD_PATTERN_TRAFFIC_QUANTITY" : function(e) {
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
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
                    'line-width': [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        10, 2,
                        15, 7,
                        18, 8
                    ],
                    'line-opacity': 1,
                },
                filter : ['has', 'linkId']
            }
            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [lineLayer])
            core.control.hideLayer(LAYER.NODE);

            _Map.off('click', layerName, MapEvents.BD_PATTERN_TRAFFIC_ABN_LOS);
            _Map.off('click', layerName, MapEvents.BD_PATTERN_TRAFFIC_QUANTITY);
            _Map.on('click', layerName, MapEvents.BD_PATTERN_TRAFFIC_QUANTITY);

            core.control.generateExcelDownloadButton(function(){
                fnDownloadExcelWorker({
                    exportType : "featureCollection",
                    filename : "교통패턴.xlsx",
                    header : ['링크아이디','도로명','평균속도','평균교통량', '누적교통량'],
                    rows :  e.data.featureCollection.features,
                    metadata : ['linkId','roadName','avgVhclSpeedAvg','vhclTrfvlmAvg','vhclTrfvlmTotal']
                });
            });


            /* 디테일 모달 생성*/
            let features = e.data.featureCollection.features;
            const total = features.reduce((accumulator, object) => {
                return accumulator + object.properties.vhclTrfvlmTotal;
            }, 0);

            let date = "";
            if(searchOption.startDate && searchOption.endDate) {
                date = searchOption.startDate;
                date += " ~ " + searchOption.endDate;
            }else{
                date = searchOption.searchYear+"년";
            }
            switch(searchOption.searchPeriod) {
                case "weekday" :
                    date += " 평일"
                    break;
                case "weekend" :
                    date += " 주말"
                    break;
            }
            let detailItem_1 = `<table>
                    <tbody>
                    <tr>
                        <th>수집원</th>
                        <td>${GITS_ENV.COLLECT_TYPE[searchOption.collectType]}</td>
                    </tr>
                    <tr>
                        <th>수집기간</th>
                        <td>${date}</td>
                    </tr>
                    <tr>
                        <th>전체교통량</th>
                        <td>${numberComma(total)}대</td>
                    </tr>
                    </tbody>
                </table>
                <div class="bigdataDetailContentCanvasWrap" style="height:130px;">
                    <canvas id="bdpattern_detail_canvas_1" data-excelreverse="true"></canvas>
                </div>`;
            let title = "";
            switch(searchOption.searchLocation) {
                case "searchAllLocation":
                case "" :
                    title = "경기도";
                    break;
                default :
                    let sg = _Util.getSGGInfoByCode(searchOption.searchLocation, GITS_ENV);
                    if(sg) {
                        title = sg.sggNm;
                    }
            }
            let dataValue = searchOption.type === "quantity" ? "vhclTrfvlmTotal" : "avgVhclSpeedAvg";
            let dataText = searchOption.type === "quantity" ? "교통량" : "평균속도";
            
            let d1 = new Date(searchOption.startDate);
            let d2 = new Date(searchOption.endDate);
            let groupDay = false;
            if(d2-d1 > 0) groupDay = true;
            let detailContainer = core.control.generateBigdataDetailContainer()
                .generateBigdataDetailItem(title+" 전체 "+dataText+" 데이터",detailItem_1, function(){
                    let _this = this
                    $.ajax({
                        type : "get",
                        url : __contextPath__+"/bigdata/"+searchOption.collectType+"/total"+(groupDay ? "-day":"")+"/getPatternTrafficQuantityChart.ajax?"+e.data.searchOption,
                        beforeSend : function(){
                            _this.element.find(".chart-preloading-wrap").remove();
                            _this.element.find(".bigdataDetailContentCanvasWrap").append(GITS_ENV.UI.CHART_PRELOADING());
                        },
                        error : function(){
                            _this.element.find(".chart-preloading-wrap").remove();
                        },
                        success : function(data){
                            _this.element.find(".chart-preloading-wrap").remove();
                            let timeLabel = [];
                            let dataArray = [];
                            data.forEach((item) => {
                                timeLabel.push(item.time);
                                dataArray.push(item[dataValue]);
                            });
                            timeLabel = timeLabel.sort(function(d1, d2){
                                const d1Time = new Date(d1).getTime();
                                const d2Time = new Date(d2).getTime();
                                return d1Time - d2Time;
                            });
                            new GITSChart(GITSChartType.LINE).init("bdpattern_detail_canvas_1")
                                .setDataSetArrayLabel(timeLabel)
                                .setDataSet({
                                    label:"전체",
                                    data: dataArray,
                                    borderColor : "#6fcad2",
                                    borderRadius:2,
                                    borderWidth:1,
                                    fill: false,
                                    tension : 0.3,
                                })
                                .setOption({
                                    interaction: {
                                        mode: 'nearest',
                                        axis: 'x',
                                        intersect: false
                                    },
                                    plugins : {
                                        legend: {
                                            display: false
                                        }
                                    }
                                })
                                .setTicksStep(100000)
                                .draw();
                        }
                    })
                }).generateBigdataDetailItem("시간대별 시군구 "+dataText+" 추이",`<div class="bigdataDetailContentCanvasWrap" style="height:150px;"><canvas id="bdpattern_detail_canvas_2" data-excelreverse="true"></canvas></div>`, function(){
                    let _this = this;
                    $.ajax({
                        type : "get",
                        url : __contextPath__+"/bigdata/"+searchOption.collectType+"/sgg/getPatternTrafficQuantityChart.ajax?"+e.data.searchOption,
                        beforeSend : function(){
                            _this.element.find(".chart-preloading-wrap").remove();
                            _this.element.find(".bigdataDetailContentCanvasWrap").append(GITS_ENV.UI.CHART_PRELOADING());
                        },
                        error : function(){
                            _this.element.find(".chart-preloading-wrap").remove();
                        },
                        success : function(data){
                            _this.element.find(".chart-preloading-wrap").remove();
                            let timeLabel = [];
                            let sggGroupData = data.reduce((acc, curr) => {
                                const { mngInstCd } = curr;
                                if (acc[mngInstCd]) acc[mngInstCd].push(curr);
                                else acc[mngInstCd] = [curr];
                                return acc;
                            }, {});
                            data.forEach((item) => {
                                if(timeLabel.indexOf(item.time) == -1) timeLabel.push(item.time);
                            });
                            timeLabel = timeLabel.sort(function(d1, d2){
                                const d1Time = new Date(d1).getTime();
                                const d2Time = new Date(d2).getTime();
                                return d1Time - d2Time;
                            });
                            let dataSets = [];
                            let dataKey = dataValue;
                            for(const sggNm in GITS_ENV.SGG_INFO){
                                const staticSggInfo = GITS_ENV.SGG_INFO[sggNm];
                                if(typeof sggGroupData[searchOption.collectType === "dsrc" ? staticSggInfo.MNGCD : staticSggInfo.CODE] !== "undefined") {
                                    let data = [];
                                    for(const time of timeLabel) {
                                        const d = sggGroupData[searchOption.collectType === "dsrc" ? staticSggInfo.MNGCD : staticSggInfo.CODE].find((d) => d['time'] === time);
                                        if(d) {
                                            data.push(d[dataKey]);
                                        }else{
                                            data.push(0);
                                        }
                                    }
                                    let dataSet = {
                                        label:sggNm,
                                        data: data,
                                        backgroundColor: staticSggInfo.COLOR,
                                        borderColor : staticSggInfo.COLOR,
                                        borderRadius:2,
                                        borderWidth:1,
                                        fill: false,
                                        tension : 0.3,
                                    };
                                    dataSets.push(dataSet);
                                }
                            }
                            new GITSChart(GITSChartType.LINE).init("bdpattern_detail_canvas_2")
                                .setDataSetArrayLabel(timeLabel)
                                .setDataArraySet(dataSets)
                                .setOption({
                                    interaction: {
                                        mode: 'nearest',
                                        axis: 'x',
                                        intersect: false
                                    },
                                    plugins: {
                                        legend: {
                                            position:'left',
                                            usePointStyle:false,
                                            labels: {
                                                color:"#fff",
                                                padding:10,
                                                boxWidth:5,
                                                font: {
                                                    family:'Pretendard',
                                                    size:10,
                                                }
                                            },

                                        },
                                    },
                                })
                                .setTicksStep(10)
                                .draw();
                        }
                    })
                });
                if(groupDay) {
                    detailContainer.generateBigdataDetailItem("요일별 시군구 "+dataText+" 추이",`<div class="bigdataDetailContentCanvasWrap" style="height:150px;"><canvas id="bdpattern_detail_canvas_6" data-excelreverse="true"></canvas></div>`, function(){
                        let _this = this;
                        $.ajax({
                            type : "get",
                            url : __contextPath__+"/bigdata/"+searchOption.collectType+"/sgg-day/getPatternTrafficQuantityChart.ajax?"+e.data.searchOption,
                            beforeSend : function(){
                                _this.element.find(".chart-preloading-wrap").remove();
                                _this.element.find(".bigdataDetailContentCanvasWrap").append(GITS_ENV.UI.CHART_PRELOADING());
                            },
                            error : function(){
                                _this.element.find(".chart-preloading-wrap").remove();
                            },
                            success : function(data){
                                _this.element.find(".chart-preloading-wrap").remove();
                                let timeLabel = [];
                                let sggGroupData = data.reduce((acc, curr) => {
                                    const { mngInstCd } = curr;
                                    if (acc[mngInstCd]) acc[mngInstCd].push(curr);
                                    else acc[mngInstCd] = [curr];
                                    return acc;
                                }, {});
                                let timeTextLabel = []
                                data.forEach((item) => {
                                    if(timeLabel.indexOf(item.time) == -1){
                                        timeLabel.push(item.time);
                                        timeTextLabel.push(GITS_ENV.DWK_CD[item.time]);
                                    }
                                });
                                let dataSets = [];
                                let dataKey = dataValue;
                                for(const sggNm in GITS_ENV.SGG_INFO){
                                    const staticSggInfo = GITS_ENV.SGG_INFO[sggNm];
                                    if(typeof sggGroupData[searchOption.collectType === "dsrc" ? staticSggInfo.MNGCD : staticSggInfo.CODE] !== "undefined") {
                                        let data = [];
                                        for(const time of timeLabel) {
                                            const d = sggGroupData[searchOption.collectType === "dsrc" ? staticSggInfo.MNGCD : staticSggInfo.CODE].find((d) => d['time'] === time);
                                            if(d) {
                                                data.push(d[dataKey]);
                                            }else{
                                                data.push(0);
                                            }
                                        }
                                        let dataSet = {
                                            label:sggNm,
                                            data: data,
                                            backgroundColor: staticSggInfo.COLOR,
                                            borderColor : staticSggInfo.COLOR,
                                            borderRadius:2,
                                            borderWidth:1,
                                            tension : 0.3,
                                            fill: false,
                                        };
                                        dataSets.push(dataSet);
                                    }
                                }
                                new GITSChart(GITSChartType.LINE).init("bdpattern_detail_canvas_6")
                                    .setDataSetArrayLabel(timeTextLabel)
                                    .setDataArraySet(dataSets)
                                    .setOption({
                                        interaction: {
                                            mode: 'nearest',
                                            axis: 'x',
                                            intersect: false
                                        },
                                        plugins: {
                                            legend: {
                                                position:'left',
                                                usePointStyle:false,
                                                labels: {
                                                    color:"#fff",
                                                    padding:10,
                                                    boxWidth:5,
                                                    font: {
                                                        family:'Pretendard',
                                                        size:10,
                                                    }
                                                },

                                            },
                                        },
                                    })
                                    .setTicksStep(10)
                                    .draw();
                            }
                        })
                    })
                }
                detailContainer.generateBigdataDetailItem(dataText+" 순위 TOP 10",'<div class="bigdataDetailContentCanvasWrap" style="height:200px;"><canvas id="bdpattern_detail_canvas_3"></canvas></div>', function(){
                    let _this = this;
                    $.ajax({
                        type : "get",
                        url : __contextPath__+"/bigdata/"+searchOption.collectType+"/top10/getPatternTrafficQuantityChart.ajax?"+e.data.searchOption,
                        beforeSend : function(){
                            _this.element.find(".chart-preloading-wrap").remove();
                            _this.element.find(".bigdataDetailContentCanvasWrap").append(GITS_ENV.UI.CHART_PRELOADING());
                        },
                        error : function(){
                            _this.element.find(".bigdataDetailContentCanvasWrap").addClass("is-complete");
                            _this.element.find(".chart-preloading-wrap").remove();
                        },
                        success : function(data){
                            _this.element.find(".bigdataDetailContentCanvasWrap").addClass("is-complete");
                            _this.element.find(".chart-preloading-wrap").remove();
                            let xArray = [];
                            let valueArray = [];
                            data.forEach((item) => {
                                let sggNm = "";
                                if(searchOption.collectType === "dsrc"){
                                    sggNm = Object.keys(GITS_ENV.SGG_INFO).find((key) => GITS_ENV.SGG_INFO[key].MNGCD === item.mngInstCd);
                                }else{
                                    sggNm = Object.keys(GITS_ENV.SGG_INFO).find((key) => GITS_ENV.SGG_INFO[key].CODE === item.mngInstCd);
                                }

                                xArray.push(sggNm+"-"+item.roadName);
                                valueArray.push(item[dataValue]);
                            });
                            new GITSChart(GITSChartType.BAR).init("bdpattern_detail_canvas_3")
                                .setViewLabel()
                                .setDataSetArrayLabel(xArray)
                                .setDataSet({
                                    label : dataText,
                                    data : valueArray,
                                    backgroundColor: function(context) {
                                        const chart = context.chart;
                                        const {ctx, chartArea} = chart;
                                        let gradient, width, height;
                                        if (!chartArea) {
                                            // This case happens on initial chart load
                                            return;
                                        }
                                        const chartWidth = chartArea.right - chartArea.left;
                                        const chartHeight = chartArea.bottom - chartArea.top;
                                        if (!gradient || width !== chartWidth || height !== chartHeight) {
                                            // Create the gradient because this is either the first render
                                            // or the size of the chart has changed
                                            width = chartWidth;
                                            height = chartHeight;
                                            gradient = ctx.createLinearGradient(0, chartArea.top, chartArea.right, chartArea.bottom);
                                            gradient.addColorStop(0, "#68d8ff");
                                            gradient.addColorStop(0.7, "#9900ff");
                                            gradient.addColorStop(1, "#ff0080");
                                        }

                                        return gradient;
                                    },
                                    borderRadius:1,
                                    fill: true
                                }).setOption({
                                indexAxis: 'y',
                                plugins: {
                                    legend: {
                                        display:false,
                                        labels : {
                                            color : "white"
                                        }
                                    },
                                    datalabels: {
                                        anchor: 'end', // remove this line to get label in middle of the bar
                                        align: 'start',
                                        clamp : true,
                                        formatter: function(val) {
                                            return numberComma(val);
                                        },
                                        labels: {
                                            value: {
                                                color: '#fff'
                                            }
                                        }

                                    }
                                },
                                interaction: {
                                    mode: 'nearest',
                                    axis: 'x',
                                    intersect: false
                                },
                                scales : {
                                    y : {
                                        ticks : {
                                            autoSkip : false,
                                            color:"white",
                                            font: {
                                                size : 10
                                            }
                                        }
                                    },
                                    x : {
                                        ticks : {
                                            autoSkip : false,
                                            color:"white",
                                            font: {
                                                size : 10
                                            }
                                        },
                                        grid: {
                                            color : "rgba(255,255,255,0.2)",
                                            tickColor : "rgba(255,255,255,0.2)",
                                            display:true
                                        }
                                    }
                                }
                            })
                                .draw();
                        }
                    })
                }).show();
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
            if(e.data.bbox) {
                try {
                    window.map.control.fitBounds(e.data.bbox);
                    _Map.fitBounds(e.data.bbox, {padding: 100});
                }catch(e){console.error('포커싱 에러')}
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
            if(searchOption.searchResultType === "vhclTrfvlm") {
                lineColor = [
                    'case',
                    ['all', ['>=', ['get','vhclTrfvlmTotal'], 0], ['<', ['get','vhclTrfvlmTotal'], 50000]],
                    LEGEND_COLOR.TRAFFIC_AMT[0],
                    ['all', ['>=', ['get','vhclTrfvlmTotal'], 50000], ['<', ['get','vhclTrfvlmTotal'], 100000]],
                    LEGEND_COLOR.TRAFFIC_AMT[1],
                    ['all', ['>=', ['get','vhclTrfvlmTotal'], 100000], ['<', ['get','vhclTrfvlmTotal'], 150000]],
                    LEGEND_COLOR.TRAFFIC_AMT[2],
                    ['all', ['>=', ['get','vhclTrfvlmTotal'], 150000], ['<', ['get','vhclTrfvlmTotal'], 200000]],
                    LEGEND_COLOR.TRAFFIC_AMT[3],
                    ['>=', ['get','vhclTrfvlmTotal'], 200000],
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


            core.control.generateBigdataDetailContainer()
                .generateBigdataDetailItem(`${searchOption.startDate} ~ ${searchOption.endDate} 데이터`, `
                <div class="bigdataDetailContentCanvasWrap none-min" style="height:200px;">
                    <canvas id="bdpattern_detail_canvas_1${isDual ? '_dual' :''}"></canvas>
                </div>`, function() {
                    let _this = this;
                    $.ajax({
                        type: "get",
                        url: __contextPath__ + "/bigdata/getTrafficEffectAnalysisChart.ajax?" + e.data.searchOption,
                        beforeSend : function(){
                            _this.element.find(".chart-preloading-wrap").remove();
                            _this.element.find(".bigdataDetailContentCanvasWrap").append(GITS_ENV.UI.CHART_PRELOADING());
                        },
                        error : function(){
                            _this.element.find(".bigdataDetailContentCanvasWrap").addClass("is-complete");
                            _this.element.find(".chart-preloading-wrap").remove();
                        },
                        success : function(list) {
                            _this.element.find(".bigdataDetailContentCanvasWrap").addClass("is-complete");
                            _this.element.find(".chart-preloading-wrap").remove();
                            let dateLabel = [];
                            let totalDataArray = [];
                            for(const data of list) {
                                dateLabel.push(data.yyyymmdd);
                                if(searchOption.searchResultType === "vhclTrfvlm"){
                                    totalDataArray.push(data.vhclTrfvlmTotal);
                                }else{
                                    totalDataArray.push(data.avgVhclSpeedAvg);
                                }
                            }
                            new GITSChart(GITSChartType.LINE).init("bdpattern_detail_canvas_1"+(isDual ? '_dual' :''))
                                .setDataSetArrayLabel(dateLabel)
                                .setDataSet({
                                    label:"전체",
                                    data: totalDataArray,
                                    borderColor : "#6fcad2",
                                    borderRadius:2,
                                    borderWidth:1,
                                    fill: false,
                                    tension : 0.2
                                })
                                .setOption({
                                    interaction: {
                                        mode: 'nearest',
                                        axis: 'x',
                                        intersect: false
                                    },
                                    plugins : {
                                        legend: {
                                            display: false
                                        }
                                    }
                                })
                                .setTicksStep(10)
                                .draw();
                        }
                    })
                }).show();
        },
        "BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS_SVC_LINK" : function(e) {
            if(e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg).footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
            if(e.data.bbox) {
                try {
                    window.map.control.fitBounds(e.data.bbox);
                    _Map.fitBounds(e.data.bbox, {padding: 100});
                }catch(e){console.error('포커싱 에러')}
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


            core.control.generateBigdataDetailContainer()
                .generateBigdataDetailItem(`${searchOption.startDate} ~ ${searchOption.endDate} 데이터`, `
                    <div class="bigdataDetailContentCanvasWrap none-min" style="height:200px;">
                        <canvas id="bdpattern_detail_canvas_1${isDual ? '_dual' :''}"></canvas>
                    </div>`, function() {
                    let _this = this;
                    $.ajax({
                        type: "get",
                        url: __contextPath__ + "/bigdata/getSvcLinkTrafficEffectAnalysisChart.ajax?" + e.data.searchOption,
                        beforeSend : function(){
                            _this.element.find(".chart-preloading-wrap").remove();
                            _this.element.find(".bigdataDetailContentCanvasWrap").append(GITS_ENV.UI.CHART_PRELOADING());
                        },
                        error : function(){
                            _this.element.find(".bigdataDetailContentCanvasWrap").addClass("is-complete");
                            _this.element.find(".chart-preloading-wrap").remove();
                        },
                        success : function(list) {
                            _this.element.find(".bigdataDetailContentCanvasWrap").addClass("is-complete");
                            _this.element.find(".chart-preloading-wrap").remove();
                            let dateLabel = [];
                            let totalDataArray = [];
                            for(const data of list) {
                                dateLabel.push(data.yyyymmdd);
                                if(searchOption.searchResultType === "vhclTrfvlm"){
                                    totalDataArray.push(data.vhclTrfvlmTotal);
                                }else{
                                    totalDataArray.push(data.avgVhclSpeedAvg);
                                }
                            }
                            new GITSChart(GITSChartType.LINE).init("bdpattern_detail_canvas_1"+(isDual ? '_dual' :''))
                                .setDataSetArrayLabel(dateLabel)
                                .setDataSet({
                                    label:"전체",
                                    data: totalDataArray,
                                    borderColor : "#6fcad2",
                                    borderRadius:2,
                                    borderWidth:1,
                                    fill: false,
                                    tension : 0.2
                                })
                                .setOption({
                                    interaction: {
                                        mode: 'nearest',
                                        axis: 'x',
                                        intersect: false
                                    },
                                    plugins : {
                                        legend: {
                                            display: false
                                        }
                                    }
                                })
                                .setTicksStep(10)
                                .draw();
                        }
                    })
                }).show();

        },
        "BD_DANGER_ZONE" : function(e) {
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
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
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
            const so = _Util.convertParamToObject(e.data.searchOption);
            const layerName = LAYER.CROSSROAD_TRF_QUANTITY;
            core.control.removeCustomSource(layerName);
            let arrowLayer = {
                'id': layerName,
                'type': 'symbol',
                'source': layerName,
                'maxzoom': 23,
                'minzoom': 14,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': 'road_direction_straight',
                    'icon-rotate': ['get','angle'],
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        14, 0.4,
                        15, 0.5,
                        22, 1
                    ]
                },
                'paint': {
                    'icon-color' : [
                        'interpolate',
                        ['linear'],
                        ['get', 'strghtTrfvlm'],
                        0, LEGEND_COLOR.WEIGHT[0],
                        5000, LEGEND_COLOR.WEIGHT[2],
                        10000, LEGEND_COLOR.WEIGHT[4],
                        20000, LEGEND_COLOR.WEIGHT[6],
                    ]
                }
            }
            let circleLayer = {
                'id': layerName+"_CIRCLE",
                'type': 'circle',
                'source': layerName,
                'maxzoom': 23,
                'minzoom': 14,
                'paint' : {
                    'circle-color': 'white',
                    /*'circle-radius': ['get', 'acdntCnt'],*/
                    'circle-radius': [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        14, 14,
                        15, 15,
                        22, 30
                    ],
                    /*'circle-radius': 30,*/
                    'circle-opacity' : 0.8,
                    'circle-stroke-color' : "#000000",
                    'circle-stroke-width' : 2
                }
            }
            let leftArrowLayer = {
                'id': layerName+"_LEFT",
                'type': 'symbol',
                'source': layerName,
                'maxzoom': 23,
                'minzoom': 14,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': 'road_direction_left',
                    'icon-rotate': ['get','angle'],
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        14, 0.4,
                        15, 0.5,
                        22, 1
                    ]
                },
                'paint': {
                    'icon-color' : [
                        'interpolate',
                        ['linear'],
                        ['get', 'trnghtTrfvlm'],
                        0, LEGEND_COLOR.WEIGHT[0],
                        5000, LEGEND_COLOR.WEIGHT[2],
                        10000, LEGEND_COLOR.WEIGHT[4],
                        20000, LEGEND_COLOR.WEIGHT[6],
                    ]
                }
            }
            let rightArrowLayer = {
                'id': layerName+"_RIGHT",
                'type': 'symbol',
                'source': layerName,
                'maxzoom': 23,
                'minzoom': 14,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': 'road_direction_right',
                    'icon-rotate': ['get','angle'],
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        14, 0.4,
                        15, 0.5,
                        22, 1
                    ]
                },
                'paint': {
                    'icon-color' : [
                        'interpolate',
                        ['linear'],
                        ['get', 'trnghtTrfvlm'],
                        0, LEGEND_COLOR.WEIGHT[0],
                        5000, LEGEND_COLOR.WEIGHT[2],
                        10000, LEGEND_COLOR.WEIGHT[4],
                        20000, LEGEND_COLOR.WEIGHT[6],
                    ]
                }
            }
            let layer = {
                'id': layerName+"_HEATMAP",
                'type': 'heatmap',
                'source': layerName,
                'minzoom': 7,
                'paint': {
                    'heatmap-weight': [
                        'interpolate',
                        ['linear'],
                        ["get","trfvlmTotal"],
                        0, 0,
                        20000, 1,
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
                        0, "rgba(33,102,172,0)",
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
                            [6, 2],
                            [12, 9],
                            [16, 50]
                        ]
                    },
                    // 줌에 맞게 투명도 조절
                    'heatmap-opacity': {
                        default: 1,
                        stops: [
                            [14, 0.9],
                            [15, 0.5]
                        ]
                    }
                }
            }

            core.control.addExpertSourceAndLayer(layerName,{}, e.data.collection.featureCollection, [layer, circleLayer,arrowLayer,leftArrowLayer,rightArrowLayer]);
            _Map.off("click", layerName, MapEvents.BD_PREDICTION_CROSS_TRAFFIC);
            _Map.on("click", layerName, MapEvents.BD_PREDICTION_CROSS_TRAFFIC);

            const searchOptionMap =  Object.fromEntries(new URLSearchParams(e.data.searchOption));
            let title = "";
            switch(searchOptionMap.searchLocation) {
                case "searchAllLocation":
                case "" :
                    title = "경기도";
                    break;
                default :
                    let sg = _Util.getSGGInfoByCode(searchOptionMap.searchLocation, GITS_ENV);
                    if(sg) {
                        title = sg.sggNm;
                    }
            }

            core.control.generateBigdataDetailContainer()
                .generateBigdataDetailItem(`${title} 전체 예측 데이터`, `<table>
                    <tbody>
                    <tr>
                        <th>조회날짜</th>
                        <td>${searchOptionMap.startDate}</td>
                    </tr>
                    <tr>
                        <th>전체 교통량</th>
                        <td id="bdcrosspd_total_trfvlm"></td>
                    </tr>
                    </tbody>
                </table>
                <div id="bdpattern_detail_canvas_wrap_1" class="bigdataDetailContentCanvasWrap none-min" style="height:200px;">
                    <canvas id="bdpattern_detail_canvas_1" data-excelreverse="true"></canvas>
                </div>`, function() {
                    let _this = this
                    $.ajax({
                        type : "get",
                        url : __contextPath__+"/bigdata/getCrossRoadTrafficQuantityPredictionGroupTime.ajax?"+e.data.searchOption,
                        beforeSend : function(){
                            $("#bdpattern_detail_canvas_wrap_1").find(".chart-preloading-wrap").remove();
                            $("#bdpattern_detail_canvas_wrap_2").find(".chart-preloading-wrap").remove();
                            $("#bdpattern_detail_canvas_wrap_1.bigdataDetailContentCanvasWrap").append(GITS_ENV.UI.CHART_PRELOADING());
                            $("#bdpattern_detail_canvas_wrap_2.bigdataDetailContentCanvasWrap").append(GITS_ENV.UI.CHART_PRELOADING());
                        },
                        error : function(){
                            $("#bdpattern_detail_canvas_wrap_1").find(".chart-preloading-wrap").remove();
                            $("#bdpattern_detail_canvas_wrap_2").find(".chart-preloading-wrap").remove();
                        },
                        success : function(list){
                            $("#bdpattern_detail_canvas_wrap_1").find(".chart-preloading-wrap").remove();
                            $("#bdpattern_detail_canvas_wrap_2").find(".chart-preloading-wrap").remove();
                            let timeLabel = [];
                            for(let i = 0; i < 24; i++){
                                timeLabel.push((i < 10 ? "0"+i : i)+":00");
                            }
                            let totalCount = 0;
                            let totalDataArray = list.reduce(function(prev,cur){
                                prev[parseInt(cur.prdctnTime)] += cur.trfvlmTotal;
                                totalCount += cur.trfvlmTotal;
                                return prev;
                            },[0,0,0,0,0,0
                                ,0,0,0,0,0,0
                                ,0,0,0,0,0,0
                                ,0,0,0,0,0,0]);
                            $("#bdcrosspd_total_trfvlm").text(numberComma(totalCount)+"대");

                            let sggTrfvlmData = list.reduce(function(prev,cur){
                                if(!prev[cur.mngInstCd])
                                    prev[cur.mngInstCd] = [0,0,0,0,0,0
                                                            ,0,0,0,0,0,0
                                                            ,0,0,0,0,0,0
                                                            ,0,0,0,0,0,0];
                                prev[cur.mngInstCd][parseInt(cur.prdctnTime)] += cur.trfvlmTotal;
                                return prev;
                            },{});


                            new GITSChart(GITSChartType.LINE).init("bdpattern_detail_canvas_1")
                                .setDataSetArrayLabel(timeLabel)
                                .setDataSet({
                                    label:"전체",
                                    data: totalDataArray,
                                    borderColor : "#6fcad2",
                                    borderRadius:2,
                                    borderWidth:1,
                                    fill: false,
                                    tension : 0.2
                                })
                                .setOption({
                                    interaction: {
                                        mode: 'nearest',
                                        axis: 'x',
                                        intersect: false
                                    },
                                    plugins : {
                                        legend: {
                                            display: false
                                        }
                                    }
                                })
                                .setTicksStep(10)
                                .draw();

                            let dataSets = [];
                            for(const mngInstCd in sggTrfvlmData) {
                                const sggInfo = _Util.getSGGInfoByMngInstCode(mngInstCd, GITS_ENV);
                                let dataSet = {
                                    label: sggInfo.sggNm,
                                    data: sggTrfvlmData[mngInstCd],
                                    backgroundColor: sggInfo.COLOR,
                                    borderColor : sggInfo.COLOR,
                                    borderRadius:2,
                                    borderWidth:1,
                                    fill: false,
                                    tension : 0.2
                                };
                                dataSets.push(dataSet);
                            }

                            new GITSChart(GITSChartType.LINE).init("bdpattern_detail_canvas_2")
                                .setDataSetArrayLabel(timeLabel)
                                .setDataArraySet(dataSets)
                                .setOption({
                                    interaction: {
                                        mode: 'nearest',
                                        axis: 'x',
                                        intersect: false
                                    },
                                    plugins: {
                                        legend: {
                                            position:'left',
                                            usePointStyle:false,
                                            labels: {
                                                color:"#fff",
                                                padding:10,
                                                boxWidth:5,
                                                font: {
                                                    family:'Pretendard',
                                                    size:10,
                                                }
                                            },

                                        },
                                    },
                                })
                                .setTicksStep(10)
                                .draw();
                        }
                    })
                })
                .generateBigdataDetailItem(`시간대별 시군구 예측 교통량`, `<div id="bdpattern_detail_canvas_wrap_2" class="bigdataDetailContentCanvasWrap" style="height:150px;">
                    <canvas id="bdpattern_detail_canvas_2" data-excelreverse="true"></canvas>
                </div>`, function() {

                })
                .generateBigdataDetailItem(`예측 교통량 순위 TOP 10`, `<div id="bdpattern_detail_canvas_wrap_3" class="bigdataDetailContentCanvasWrap" style="height:200px;">
                    <canvas id="bdpattern_detail_canvas_3"></canvas>
                </div>`, function() {
                    let _this = this;
                    $.ajax({
                        type : "get",
                        url : __contextPath__+"/bigdata/getCrossRoadTrafficQuantityPredictionTop10.ajax?"+e.data.searchOption,
                        beforeSend : function(){
                            _this.element.find(".chart-preloading-wrap").remove();
                            _this.element.find(".bigdataDetailContentCanvasWrap").append(GITS_ENV.UI.CHART_PRELOADING());
                        },
                        error : function(){
                            _this.element.find(".bigdataDetailContentCanvasWrap").addClass("is-complete");
                            _this.element.find(".chart-preloading-wrap").remove();
                        },
                        success : function(data) {
                            _this.element.find(".bigdataDetailContentCanvasWrap").addClass("is-complete");
                            _this.element.find(".chart-preloading-wrap").remove();
                            let xArray = [];
                            let valueArray = [];
                            data.forEach((item) => {
                                const sggNm = Object.keys(GITS_ENV.SGG_INFO).find((key) => GITS_ENV.SGG_INFO[key].MNGCD === item.mngInstCd);
                                xArray.push(sggNm + "-" + item.acsRoadNm);
                                valueArray.push(item.trfvlmTotal);
                            });
                            new GITSChart(GITSChartType.BAR).init("bdpattern_detail_canvas_3")
                                .setViewLabel()
                                .setDataSetArrayLabel(xArray)
                                .setDataSet({
                                    label: "교통량",
                                    data: valueArray,
                                    backgroundColor: function (context) {
                                        const chart = context.chart;
                                        const {ctx, chartArea} = chart;
                                        let gradient, width, height;
                                        if (!chartArea) {
                                            // This case happens on initial chart load
                                            return;
                                        }
                                        const chartWidth = chartArea.right - chartArea.left;
                                        const chartHeight = chartArea.bottom - chartArea.top;
                                        if (!gradient || width !== chartWidth || height !== chartHeight) {
                                            // Create the gradient because this is either the first render
                                            // or the size of the chart has changed
                                            width = chartWidth;
                                            height = chartHeight;
                                            gradient = ctx.createLinearGradient(0, chartArea.top, chartArea.right, chartArea.bottom);
                                            gradient.addColorStop(0, "#68d8ff");
                                            gradient.addColorStop(0.7, "#9900ff");
                                            gradient.addColorStop(1, "#ff0080");
                                        }

                                        return gradient;
                                    },
                                    borderRadius: 1,
                                    fill: true
                                }).setOption({
                                indexAxis: 'y',
                                plugins: {
                                    legend: {
                                        display: false,
                                        labels: {
                                            color: "white"
                                        }
                                    },
                                    datalabels: {
                                        anchor: 'end', // remove this line to get label in middle of the bar
                                        align: 'start',
                                        clamp: true,
                                        formatter: function (val) {
                                            return numberComma(val);
                                        },
                                        labels: {
                                            value: {
                                                color: '#fff'
                                            }
                                        }

                                    }
                                },
                                interaction: {
                                    mode: 'nearest',
                                    axis: 'x',
                                    intersect: false
                                },
                                scales: {
                                    y: {
                                        ticks: {
                                            autoSkip: false,
                                            color: "white",
                                            font: {
                                                size: 10
                                            }
                                        }
                                    },
                                    x: {
                                        ticks: {
                                            autoSkip: false,
                                            color: "white",
                                            font: {
                                                size: 10
                                            }
                                        },
                                        grid: {
                                            color: "rgba(255,255,255,0.2)",
                                            tickColor: "rgba(255,255,255,0.2)",
                                            display: true
                                        }
                                    }
                                }
                            })
                                .draw();
                        }
                    })
                })
                .show();

        },
        "BD_PUBLIC_TRANSFER_CNDCY_ROUTE" : function(e) {
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
            const searchOption =  Object.fromEntries(new URLSearchParams(e.data.searchOption));
            const sourceName = LAYER.BD_PUBLIC_TRANSFER_CNDCY;
            const linkLayerName = LAYER.BD_PUBLIC_TRANSFER_CNDCY;
            const stationLayerName = LAYER.BD_PUBLIC_TRANSFER_CNDCY_STATION;
            core.control.removeCustomSource(sourceName);
            /*const color = _Util.getRandomHexColor();*/
            const color = "#ee05cb";
            if(e.data.bbox) {
                try {
                    window.map.control.fitBounds(e.data.bbox);
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
                    'line-cap': 'round',
                },
                'paint': {
                    'line-color': color,
                    'line-width': 2,
                    'line-opacity': 0.8
                },
                "filter" : ['has', "route"]
            }
            let directionLayer = {
                id: linkLayerName+"_DIRECTION",
                type: 'symbol',
                source: sourceName,
                paint: {},
                layout: {
                    'symbol-placement': 'line',
                    'icon-image': 'arrow',
                    'icon-rotate': 270,
                    'icon-rotation-alignment': 'map',
                    'icon-allow-overlap': true,
                    'icon-ignore-placement': true
                },
                "filter" : ['has', "route"]
            }
            let stationLayer = {
                'id': stationLayerName,
                'type': 'symbol',
                'source': sourceName,
                'maxzoom': 22,
                'minzoom': 9,
                'layout': {
                    'icon-allow-overlap': true,
                    'icon-image': "bus_station_2",
                    "icon-size": [
                        'interpolate',
                        ['linear'],
                        ['zoom'],
                        10, 0.05,
                        15, 0.25
                    ]
                },
                "filter" : ['has', "station"]
            }

            core.control.addExpertSourceAndLayer(sourceName,{}, e.data.featureCollection, [linkLayer, directionLayer, stationLayer]);
            const length = e.data.featureCollection.features[0].properties.length;
            const score = e.data.featureCollection.features[0].properties.score;
            const baseym = e.data.featureCollection.features[0].properties.baseym;
            const btcId = e.data.featureCollection.features[0].properties.btcId;
            const lengthRatio = e.data.featureCollection.features[0].properties.lengthRatio;
            const lengthVar = e.data.featureCollection.features[0].properties.lengthVar;
            const numPsngr = e.data.featureCollection.features[0].properties.numPsngr;
            const numPsngrVar = e.data.featureCollection.features[0].properties.numPsngrVar;
            const routeNm = searchOption.routeNm;
            const routeId = searchOption.routeId;

            let dt = baseym.substring(0, 4) + "-" + baseym.substring(4, 6);
            let drct = btcId.split("_")[1];

            $(".optimization_container").remove();
            var busOptimizationItem = $(`
			<div class="optimization_container">
				<div class="optimization_wrap">
					<div class="optimization_title_box">
						<div class="optimization_title_set mb8">
							<div class="optimization_title">- 버스 노선 정보</div>
							<button type="button" class="optimization_close"><img src="/statics/images/close.png" alt="닫기"></button>
						</div>
						<ul class="optimization_txt">
							<li class="mb8">노선명 : <span>${routeNm}</span></li>
							<li>노선ID : <span>${routeId}</span></li>
						</ul>
					</div>
					<div class="optimization_content_box">
						<div class="optimization_title mb8">- 최적화 노선 정보</div>
						<div class="optimization_table_box">
							<table>
							    <tr>
									<th>분석일</th>
									<td>${dt}</td>
								</tr>
								<tr>
									<th>방향</th>
									<td>${drct === "01" ? "정방향" : "역방향"}</td>
								</tr>
								<tr>
									<th>길이 (증감)</th>
									<td>${numberComma(length)}m (${numberComma(lengthVar.toFixed(2))}m)</td>
								</tr>
								<tr>
									<th>예측승객수 (증감)</th>
									<td>${numberComma(numPsngr)}명 (${numberComma((numPsngrVar))}명)</td>
								</tr>
								<tr>
									<th>굴곡도</th>
									<td>${lengthRatio}</td>
								</tr>
							</table>
						</div>
					</div>
					<div><button type="button" class="is-darkgreen-btn" id="hideButton">기존노선 숨기기</button></div>
				</div>
			</div>			
		`)
            busOptimizationItem.find("#hideButton").on("click", function(){
                if($(this).hasClass("is-hide")) {
                    $(this).removeClass("is-hide");
                    $(this).text("기존노선 숨기기");
                    core.control.showLayer(LAYER.BUS_ROUTE);
                    core.control.showLayer(LAYER.BUS_STATION);
                }else{
                    $(this).addClass("is-hide");
                    $(this).text("기존노선 보이기");
                    core.control.hideLayer(LAYER.BUS_ROUTE)
                    core.control.hideLayer(LAYER.BUS_STATION);
                }
            });
            $('#map-container').append(busOptimizationItem);
            $('.optimization_close').on('click', function(){
                $(this).closest('.optimization_container').remove();
            })
        },
        "BD_PUBLIC_TRANSFER_USAGE_BY_SGG" : function(e) {
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
            for(const m in markersOnScreen) {
                markersOnScreen[m].remove();
            }
            for(const maker of openMarkers) {
                maker.remove();
            }
            openMarkers= [];
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
                            props.rideUserCnt,
                            props.trnsitUserCnt
                        ], [LEGEND_COLOR.TRANSFER_USAGE_BY_SGG[0],LEGEND_COLOR.TRANSFER_USAGE_BY_SGG[1]]);
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
                                        <li class="popup_item">전체 : <span>${typeof props.total !== "undefined" ? numberComma(props.total)+"명" : "제공 데이터 없음"}</span></li>
                                        <li class="popup_item">이용객수 : <span>${props.rideUserCnt != null ? numberComma(props.rideUserCnt)+"명" : "제공 데이터 없음"}</span></li>
                                        <li class="popup_item">환승수 : <span>${props.trnsitUserCnt != null ?numberComma(props.trnsitUserCnt)+"명" : "제공 데이터 없음"}</span></li>
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
        "BD_PUBLIC_TRANSFER_USAGE_BY_ST_END" : function(e) {
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
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
                ['==', ['get','busUserCnt'], null],
                "rgba(0,0,0,0.5)",
                ['all', ['>=', ['get','busUserCnt'], 0], ['<', ['get','busUserCnt'], 200]],
                LEGEND_COLOR.TRAFFIC_AMT[0],
                ['all', ['>=', ['get','busUserCnt'], 200], ['<', ['get','busUserCnt'], 400]],
                LEGEND_COLOR.TRAFFIC_AMT[1],
                ['all', ['>=', ['get','busUserCnt'], 400], ['<', ['get','busUserCnt'], 600]],
                LEGEND_COLOR.TRAFFIC_AMT[2],
                ['all', ['>=', ['get','busUserCnt'], 600], ['<', ['get','busUserCnt'], 800]],
                LEGEND_COLOR.TRAFFIC_AMT[3],
                ['>=', ['get','busUserCnt'], 800],
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
        },
        "BD_BUS_USAGE_BY_STATION": function(e) {
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
            workerResultEvent.BD_BUS_STATION(e);

            _Map.off('click', LAYER.BD_BUS_STATION, MapEvents.BD_BUS_STATION);
            _Map.off('click', LAYER.BD_BUS_STATION, MapEvents.BD_BUS_USAGE_BY_STATION);
            _Map.off("click", LAYER.BD_BUS_STATION, MapEvents.BD_BUS_BIT_STATION);
            if(e.data.disabledClickEvent !== false)
                _Map.on('click', LAYER.BD_BUS_STATION, MapEvents.BD_BUS_USAGE_BY_STATION);
        },
        "BD_BUS_STATION" : function(e) {
            const sourceName = LAYER.BD_BUS_STATION;
            const layerName = LAYER.BD_BUS_STATION;
            const so = _Util.convertParamToObject(e.data.searchOption);
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
                        13, 0.15,
                        15, 0.5
                    ]
                },
                filter : ["has","icon"]
            }
            let layerArray = [busStationLayer];
            /*if(so.routeId && so.directLine) {
                let routeDirectLineLayer = {
                    'id': layerName+"_DIRECTLINE",
                    'type': 'line',
                    'source': sourceName,
                    'maxzoom': 22,
                    'minzoom': 9,
                    'layout': {
                        'line-join': 'round',
                        'line-cap': 'round'
                    },
                    'paint': {
                        'line-color': "#de00ff",
                        'line-opacity': 0.8,
                        'line-width': 5
                    }
                }
                layerArray.push(routeDirectLineLayer);
            }*/
            core.control.addExpertSourceAndLayer(sourceName, {}, e.data.featureCollection, layerArray);
            _Map.off('click', layerName, MapEvents.BD_BUS_STATION);
            _Map.off('click', layerName, MapEvents.BD_BUS_USAGE_BY_STATION);
            _Map.off("click", layerName, MapEvents.BD_BUS_BIT_STATION);
            if(e.data.disabledClickEvent !== false)
                _Map.on('click', layerName, MapEvents.BD_BUS_STATION);
        },
        "BD_BUS_ROUTE_CURVE_ANL" : function(e) {
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
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
            _Map.off('click', LAYER.BD_BUS_STATION, MapEvents.BD_BUS_STATION);
            _Map.off('click', LAYER.BD_BUS_STATION, MapEvents.BD_BUS_USAGE_BY_STATION);
            _Map.off("click", LAYER.BD_BUS_STATION, MapEvents.BD_BUS_BIT_STATION);
            _Map.off("click", LAYER.BUS_ROUTE, MapEvents.BD_BUS_ROUTE_CURVE_ANL);
            _Map.off("click", LAYER.BD_BUS_STATION, MapEvents.BD_BUS_ROUTE_PASSENGER_ANL);
            _Map.on("click", LAYER.BUS_ROUTE, MapEvents.BD_BUS_ROUTE_CURVE_ANL);

            function commonDrawScatter(_this, type, label, canvasId){
                $.ajax({
                    type : "get",
                    url : __contextPath__+"/bigdata/type"+type+"/getPublicTransferRouteCurveAnalysisTop10.ajax",
                    beforeSend : function(){
                        _this.element.find(".chart-preloading-wrap").remove();
                        _this.element.find(".bigdataDetailContentCanvasWrap").append(GITS_ENV.UI.CHART_PRELOADING());
                    },
                    error : function(){
                        _this.element.find(".bigdataDetailContentCanvasWrap").addClass("is-complete");
                        _this.element.find(".chart-preloading-wrap").remove();
                    },
                    success : function(data){
                        _this.element.find(".bigdataDetailContentCanvasWrap").addClass("is-complete");
                        _this.element.find(".chart-preloading-wrap").remove();
                        let dataSets = [];
                        data.forEach((item) => {
                            const randColor = "#"+Math.floor(Math.random()*16777215).toString(16);
                            dataSets.push({
                                label: `${item.routeNm}(${item.districtGnm ? item.districtGnm : item.districtSnm})`,
                                data: [{
                                    x: item.routeLen,
                                    y: item.curvt
                                }],
                                backgroundColor : _Util.hexToRgbA(randColor, 0.5),
                                radius : 5,
                                borderWidth : 2,
                                borderColor : randColor
                            });
                        });
                        new GITSChart(GITSChartType.SCATTER).init(canvasId)
                            /*.setDataSetArrayLabel(xArray)*/
                            .setDataArraySet(dataSets)
                            .setOption({
                                plugins : {
                                    legend : {
                                        usePointStyle:false,
                                        labels: {
                                            color:"#fff",
                                            padding:10,
                                            boxWidth:5,
                                            font: {
                                                family:'Pretendard',
                                                size:11,
                                            }
                                        },
                                    },
                                    tooltip : {
                                        callbacks : {
                                            label : function(context) {
                                                return context.dataset.label + "굴곡도 : " + context.dataset.data[context.dataIndex].y+ " 노선길이 : " + context.dataset.data[context.dataIndex].x;
                                            }
                                        }
                                    }
                                },
                                interaction: {
                                    mode: 'nearest',
                                    axis: 'x',
                                    intersect: false
                                },
                                scales : {
                                    x: {
                                        title : {
                                            display: true,
                                            text : "노선길이",
                                            color : "white"
                                        },
                                        beginAtZero: true,
                                        ticks: {
                                            color:'#fff',
                                            font: {
                                                family:'Pretendard',
                                                size : 10
                                            },
                                        },
                                        grid: {
                                            color:'rgba(255, 255, 255, 0.44)',
                                            tickColor:'transparent',
                                            display:true
                                        }
                                    },
                                    y: {
                                        title : {
                                            display: true,
                                            text : "굴곡도",
                                            color : "white"
                                        },
                                        beginAtZero: true,
                                        ticks: {
                                            color:'#fff',
                                            font: {
                                                size : 10,
                                                family:'Pretendard',
                                            }
                                        },
                                        grid: {
                                            color:'rgba(255, 255, 255, 0.44)',
                                            tickColor:'transparent',
                                            display:true
                                        }
                                    },
                                }
                            })
                            .draw();
                    }
                })
            }
            core.control.generateBigdataDetailContainer()
                .generateBigdataDetailItem("[일반] 노선별 굴곡도 TOP 20",'<div class="bigdataDetailContentCanvasWrap" style="height:300px;"><canvas id="bdpattern_detail_canvas_1"></canvas></div>', function(){
                    commonDrawScatter(this,'1','노선별 굴곡도', "bdpattern_detail_canvas_1")
                }).generateBigdataDetailItem("[직좌] 노선별 굴곡도 TOP 20",'<div class="bigdataDetailContentCanvasWrap" style="height:300px;"><canvas id="bdpattern_detail_canvas_2"></canvas></div>', function(){
                    commonDrawScatter(this,'2','노선별 굴곡도', "bdpattern_detail_canvas_2")
                }).generateBigdataDetailItem("[마을] 노선별 굴곡도 TOP 20",'<div class="bigdataDetailContentCanvasWrap" style="height:300px;"><canvas id="bdpattern_detail_canvas_3"></canvas></div>', function(){
                    commonDrawScatter(this,'3','노선별 굴곡도', "bdpattern_detail_canvas_3")
                }).generateBigdataDetailItem("[시외/공항] 노선별 굴곡도 TOP 20",'<div class="bigdataDetailContentCanvasWrap" style="height:300px;"><canvas id="bdpattern_detail_canvas_4"></canvas></div>', function(){
                    commonDrawScatter(this,'4','노선별 굴곡도', "bdpattern_detail_canvas_4")
                }).show();

        },
        "BD_BUS_ROUTE_USE_CALC" : function(e) {
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
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
        "BD_PUBLIC_TRANSFER_LNDI_ALL" : function(e) {
            gitsApp.endLoading()
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
            workerResultEvent['BD_PUBLIC_TRANSFER_LNDI'](e, 50000, true, 'passengers');
            core.control.generateExcelDownloadButton(function(){
                fnDownloadExcelWorker({
                    exportType : "featureCollection",
                    filename : "정류장별 이용현황.xlsx",
                    header : ['정류장ID','정류장모바일No','정류장명','승차인원','하차인원'],
                    rows :  e.data.featureCollection.features,
                    metadata : ['stationId','mobileNo','stationNm','ridePassengers','lndiPassengers']
                });
            });
        },
        "BD_PUBLIC_TRANSFER_LNDI" : function(e, weight = 5000, isOtherEvent = false, key) {
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }

            const layerId = LAYER.BD_PUBLIC_TRANSFER_LNDICNT_HEATMAP;
            const circlelayerId = LAYER.BD_PUBLIC_TRANSFER_LNDICNT_HEATMAP+"_STATION";
            const testlayerId = LAYER.BD_PUBLIC_TRANSFER_LNDICNT_HEATMAP+"_TEXT";
            core.control.removeCustomSource(layerId);

            const addLayer = [
                {
                    'id': testlayerId,
                    'type': 'symbol',
                    'source': layerId,
                    'minzoom': 14,
                    "layout": {
                        "text-field": ["get", "stationNm"],
                        "text-anchor": "bottom",
                        "text-size": 10,
                        "text-offset": [0, -2.5],
                    },
                    'paint' : {
                        "text-color": "black",
                        "text-halo-color": "white",
                        "text-halo-width": 2,
                        "text-halo-blur": 1
                    }
                },
                {
                    id: circlelayerId,
                    type: 'circle',
                    'minzoom': 14,
                    source: layerId,
                    paint: {
                        'circle-color': [
                            'interpolate',
                            ['linear'],
                            ['get', 'totalPassengers'],
                            0, '#51bbd6',
                            1000, '#f1f075',
                            5000, '#f28cb1',
                            10000, '#ec1346',
                        ],
                        'circle-radius': 20,
                        'circle-opacity' : 0.8,
                        'circle-stroke-color' : STYLES.SMCRD_COLOR,
                        'circle-stroke-width' : 2
                    },
                    'filter': ['!=', 'cluster', true]
                },
                {
                    'id': testlayerId+"_VOL",
                    'type': 'symbol',
                    'minzoom': 14,
                    'source': layerId,
                    layout: {
                        'text-allow-overlap' : true,
                        'text-field': [
                            'concat',
                            ['concat','승차:',['get', 'ridePassengers']],
                            ['concat','\n',['concat','하차:',['get', 'lndiPassengers']]]
                        ],
                        'text-size': 10
                    }
                    ,
                    'paint' : {
                        "text-color": "black",
                        "text-halo-color": "white",
                        "text-halo-width": 2,
                        "text-halo-blur": 1
                    }
                }
            ];
            const color = null;
            core.control.drawCommonHeatMap(e.data.featureCollection, layerId, addLayer, weight, key ? key : "totalPassengers", color);

            core.control.generateExcelDownloadButton(function(){
                fnDownloadExcelWorker({
                    exportType : "featureCollection",
                    filename : "노선별 이용객수.xlsx",
                    header : ['정류장ID','정류장모바일No','정류장명','승차인원','하차인원'],
                    rows :  e.data.featureCollection.features,
                    metadata : ['stationId','mobileNo','stationNm','ridePassengers','lndiPassengers']
                });
            });


            let features = [...e.data.featureCollection.features];
            core.control.generateBigdataDetailContainer()
                .generateBigdataDetailItem(`이용객수 TOP 10 정류장`, `
                <div class="bigdataDetailContentCanvasWrap none-min" style="height:200px;">
                    <canvas id="bdpattern_detail_canvas_1"></canvas>
                </div>`, function() {
                    const top10Array = features.filter((obj) => obj.properties.stationNm).sort(function(a,b){
                        return b.properties.totalPassengers - a.properties.totalPassengers
                    }).slice(0,10);
                    let labelArray = top10Array.map((obj) => obj.properties.stationNm);
                    let data = top10Array.map((obj) => obj.properties.totalPassengers);
                    new GITSChart(GITSChartType.BAR).init("bdpattern_detail_canvas_1")
                        .setViewLabel()
                        .setDataSetArrayLabel(labelArray)
                        .setDataSet({
                            label : '이용객수',
                            data : data,
                            backgroundColor: function(context) {
                                const chart = context.chart;
                                const {ctx, chartArea} = chart;
                                let gradient, width, height;
                                if (!chartArea) {
                                    // This case happens on initial chart load
                                    return;
                                }
                                const chartWidth = chartArea.right - chartArea.left;
                                const chartHeight = chartArea.bottom - chartArea.top;
                                if (!gradient || width !== chartWidth || height !== chartHeight) {
                                    // Create the gradient because this is either the first render
                                    // or the size of the chart has changed
                                    width = chartWidth;
                                    height = chartHeight;
                                    gradient = ctx.createLinearGradient(0, chartArea.bottom, 0, chartArea.top);
                                    gradient.addColorStop(0, "#68d8ff");
                                    gradient.addColorStop(0.7, "#9900ff");
                                    gradient.addColorStop(1, "#ff0080");
                                }

                                return gradient;
                            },
                            borderRadius:1,
                            fill: true
                        }).setOption({
                        indexAxis: 'y',
                        plugins: {
                            legend: {
                                display:false,
                                labels : {
                                    color : "white"
                                }
                            },
                            datalabels: {
                                anchor: 'end', // remove this line to get label in middle of the bar
                                align: 'start',
                                clamp : true,
                                formatter: function(val) {
                                    return numberComma(val)+"명";
                                },
                                labels: {
                                    value: {
                                        color: '#fff'
                                    }
                                }

                            }
                        },
                        interaction: {
                            mode: 'nearest',
                            axis: 'x',
                            intersect: false
                        },
                        scales : {
                            y : {
                                ticks : {
                                    autoSkip : false,
                                    color:"white",
                                    font: {
                                        size : 10
                                    }
                                }
                            },
                            x : {
                                ticks : {
                                    autoSkip : false,
                                    color:"white",
                                    font: {
                                        size : 10
                                    }
                                },
                                grid: {
                                    color : "rgba(255,255,255,0.2)",
                                    tickColor : "rgba(255,255,255,0.2)",
                                    display:true
                                }
                            }
                        }
                    })
                        .draw();
                }).show();
        },
        "BD_BUS_ROUTE_PASSENGER_ANL" : function(e) {
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
            workerResultEvent['CM_BUS_ROUTE'](e, 5);

            for(const ev in MapEvents) {
                if(ev.indexOf("BD_BUS_ROUTE") === 0) {
                    _Map.off("click", LAYER.BUS_ROUTE, MapEvents[ev]);
                }
                if(ev.indexOf("BD_BUS_STATION") === 0) {
                    _Map.off("click", LAYER.BD_BUS_STATION, MapEvents[ev]);
                    _Map.off("click", LAYER.BUS_STATION, MapEvents[ev]);
                }
            }

            _Map.off('click', LAYER.BD_BUS_STATION, MapEvents.BD_BUS_STATION);
            _Map.off('click', LAYER.BD_BUS_STATION, MapEvents.BD_BUS_USAGE_BY_STATION);
            _Map.off("click", LAYER.BD_BUS_STATION, MapEvents.BD_BUS_BIT_STATION);
            _Map.off("click", LAYER.BUS_ROUTE, MapEvents.BD_BUS_ROUTE_CURVE_ANL);
            _Map.off("click", LAYER.BD_BUS_STATION, MapEvents.BD_BUS_ROUTE_PASSENGER_ANL);
            _Map.on("click", LAYER.BD_BUS_STATION, MapEvents.BD_BUS_ROUTE_PASSENGER_ANL);



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
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
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
                        ['==', ['get','duplCnt'], null],
                        LEGEND_COLOR.TRAFFIC_AMT[0],
                        ['==', ['get','duplCnt'], 0],
                        LEGEND_COLOR.TRAFFIC_AMT[0],
                        ['all', ['>=', ['get','duplCnt'], 1], ['<', ['get','duplCnt'], 6]],
                        LEGEND_COLOR.TRAFFIC_AMT[1],
                        ['all', ['>=', ['get','duplCnt'], 6], ['<', ['get','duplCnt'], 11]],
                        LEGEND_COLOR.TRAFFIC_AMT[2],
                        ['all', ['>=', ['get','duplCnt'], 11], ['<', ['get','duplCnt'], 21]],
                        LEGEND_COLOR.TRAFFIC_AMT[3],
                        ['>=', ['get','duplCnt'], 20],
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

            core.control.generateBigdataDetailContainer()
                .generateBigdataDetailItem("중복구간 TOP 10",'<div class="bigdataDetailContentCanvasWrap" style="height:200px;"><canvas id="bdpattern_detail_canvas_1"></canvas></div>', function(){
                    let _this = this;
                    $.ajax({
                        type : "get",
                        url : __contextPath__+"/bigdata/getDuplicateRouteTop10.ajax",
                        data : selectedSearchOption["BD_BUS_ROUTE_DUPLICATE_ROUTE"],
                        beforeSend : function(){
                            _this.element.find(".chart-preloading-wrap").remove();
                            _this.element.find(".bigdataDetailContentCanvasWrap").append(GITS_ENV.UI.CHART_PRELOADING());
                        },
                        error : function(){
                            _this.element.find(".bigdataDetailContentCanvasWrap").addClass("is-complete");
                            _this.element.find(".chart-preloading-wrap").remove();
                        },
                        success : function(data){
                            _this.element.find(".bigdataDetailContentCanvasWrap").addClass("is-complete");
                            _this.element.find(".chart-preloading-wrap").remove();
                            let xArray = [];
                            let valueArray = [];
                            data.forEach((item) => {
                                xArray.push(`${item.stStaNm}->${item.edStaNm}`);
                                valueArray.push(item.duplCnt);
                            });
                            new GITSChart(GITSChartType.BAR).init('bdpattern_detail_canvas_1')
                                .setViewLabel()
                                .setDataSetArrayLabel(xArray)
                                .setDataSet({
                                    label : '중복수',
                                    data : valueArray,
                                    backgroundColor: function(context) {
                                        const chart = context.chart;
                                        const {ctx, chartArea} = chart;
                                        let gradient, width, height;
                                        if (!chartArea) {
                                            // This case happens on initial chart load
                                            return;
                                        }
                                        const chartWidth = chartArea.right - chartArea.left;
                                        const chartHeight = chartArea.bottom - chartArea.top;
                                        if (!gradient || width !== chartWidth || height !== chartHeight) {
                                            // Create the gradient because this is either the first render
                                            // or the size of the chart has changed
                                            width = chartWidth;
                                            height = chartHeight;
                                            gradient = ctx.createLinearGradient(0, chartArea.top, chartArea.right, chartArea.bottom);
                                            gradient.addColorStop(0, "#68d8ff");
                                            gradient.addColorStop(0.7, "#9900ff");
                                            gradient.addColorStop(1, "#ff0080");
                                        }
                                        return gradient;
                                    },
                                    borderRadius:1,
                                    fill: true
                                }).setOption({
                                indexAxis: 'y',
                                plugins: {
                                    legend: {
                                        display:false,
                                        labels : {
                                            color : "white"
                                        }
                                    },
                                    datalabels: {
                                        anchor: 'end', // remove this line to get label in middle of the bar
                                        align: 'start',
                                        clamp : true,
                                        formatter: function(val) {
                                            return numberComma(val);
                                        },
                                        labels: {
                                            value: {
                                                color: '#fff'
                                            }
                                        }

                                    }
                                },
                                interaction: {
                                    mode: 'nearest',
                                    axis: 'x',
                                    intersect: false
                                },
                                scales : {
                                    y : {
                                        ticks : {
                                            autoSkip : false,
                                            color:"white",
                                            font: {
                                                size : 10
                                            }
                                        }
                                    },
                                    x : {
                                        ticks : {
                                            autoSkip : false,
                                            color:"white",
                                            font: {
                                                size : 10
                                            }
                                        },
                                        grid: {
                                            color : "rgba(255,255,255,0.2)",
                                            tickColor : "rgba(255,255,255,0.2)",
                                            display:true
                                        }
                                    }
                                }
                            })
                                .draw();
                        }
                    })
                }).show();

            return;
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
                        13, 0.15,
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
                        13, 0.15,
                        15, 0.5
                    ]
                }
            }
            core.control.addExpertSourceAndLayer(sourceName, {}, e.data.featureCollection, [busStationLayer]);


            _Map.off("click", layerName, MapEvents.BD_BUS_STATION);
            _Map.off("click", layerName, MapEvents.BD_BUS_USAGE_BY_STATION);
            _Map.off("click", layerName, MapEvents.BD_BUS_BIT_STATION);
            _Map.on("click", layerName, MapEvents.BD_BUS_BIT_STATION);

            const chunkSize = 9;
            const width = 100/chunkSize;
            /*e.data.featureCollection.features*/
            let stations = e.data.featureCollection.features;

            stations = stations.sort(function(a, b){
                return parseInt(a.properties.staOrder) - parseInt(b.properties.staOrder);
            });
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
                    $stationBITItem.data("gpsx",stationItem.properties.mapX);
                    $stationBITItem.data("gpsy",stationItem.properties.mapY);
                    $stationBITItem.css("width",width+"%");
                    $stationBITItem.addClass(row%2 === 0 ? "direction-left" : "direction-right");
                    $stationBITItemInner.html(`
                    <img src="/statics/images/bus_icon1.png" />
                    <p>${stationItem.properties.stationNm}</p>
                    <p>(${stationItem.properties.mobileNo ?? stationItem.properties.stationId})</p>
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
            $stationBITWrapper.find(".bit-station").on("click", function(){
                const x = $(this).data("gpsx");
                const y = $(this).data("gpsy");
                core.control.highlightingTarget(null, x, y);
            });
            core.control.generatePlayerModel("대중교통 정류장별 도착예상시간", $stationBITWrapper, null, null, true);
        },
        "BD_COMMON_TRFVLM_CLUSTER" : function(e, color, layerName, event) {
            if (e.data.error) {
                new ModalBuilder().init().alertBoby(e.data.errorMsg ? e.data.errorMsg : "조회된 데이터가 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
                modalAlertWrap();
                return;
            }
            core.control.removeCustomSource(layerName);
            let clusterLayer = {
                id: layerName,
                type: 'circle',
                source: layerName,
                paint: {
                    'circle-color': [
                        'interpolate',
                        ['linear'],
                        ['get', 'trfVolTotal'],
                        0, '#8ac9a3',
                        100000, '#0bf393',
                    ],
                    'circle-radius': 35,
                    'circle-opacity' : 0.8,
                    'circle-stroke-color' : color,
                    'circle-stroke-width' : 2
                },
                filter : ['has', 'point_count']
            }
            let clusterTextLayer = {
                id: layerName+"_text",
                type: 'symbol',
                source: layerName,
                layout: {
                    'text-allow-overlap' : true,
                    'text-field': ['number-format',['get', 'trfVolTotal'],{locale:'kr'}],
                    'text-size': 14
                },
                filter : ['has', 'point_count']
            }
            let unClusterLayer = {
                id: layerName+"_none",
                type: 'circle',
                source: layerName,
                paint: {
                    'circle-color': [
                        'interpolate',
                        ['linear'],
                        ['get', 'trfVol'],
                        0, '#8ac9a3',
                        100000, '#0bf393',
                    ],
                    'circle-radius': 20,
                    'circle-opacity' : 0.8,
                    'circle-stroke-color' : color,
                    'circle-stroke-width' : 2
                },
                'filter': ['!=', 'cluster', true]
            }
            let unclusterTextLayer = {
                id: layerName+"_none_text",
                type: 'symbol',
                source: layerName,
                layout: {
                    'text-allow-overlap' : true,
                    'text-field': ['number-format',['get', 'trfVol'],{locale:'kr'}],
                    'text-size': 10
                },
                'filter': ['!=', 'cluster', true]
            }
            const clusterOption = {
                cluster : true,
                clusterMaxZoom: 11, // Max zoom to cluster points on
                clusterRadius: 35,
                clusterProperties: {
                    'trfVolTotal': ["+", ["get", "trfVol"]],
                },
                tolerance : 0.5
            }
            core.control.addExpertSourceAndLayer(layerName,clusterOption, e.data.featureCollection, [clusterLayer, clusterTextLayer, unClusterLayer, unclusterTextLayer]);
            if(typeof event === "function") event();
        },
        "BD_TRFVLM_ANYTM_HGHW" : function (e){
            workerResultEvent["BD_COMMON_TRFVLM_CLUSTER"](e, STYLES.TRF_ANYTM_HGHW_COLOR, LAYER.BD_TRAFFIC_VOLUME_ANYTM_HGHW, function(){
                _Map.off("click", LAYER.BD_TRAFFIC_VOLUME_ANYTM_HGHW+"_none_text", MapEvents.BD_TRFVLM_ANYTM_HGHW);
                _Map.on("click", LAYER.BD_TRAFFIC_VOLUME_ANYTM_HGHW+"_none_text", MapEvents.BD_TRFVLM_ANYTM_HGHW);
            });
            if(document.querySelector("#layerControlList [data-layer='anytmHghw']"))
                document.querySelector("#layerControlList [data-layer='anytmHghw']").disabled = false
        },
        "BD_TRFVLM_ANYTM_NLRD" : function (e){
            workerResultEvent["BD_COMMON_TRFVLM_CLUSTER"](e, STYLES.TRF_ANYTM_HGHW_COLOR, LAYER.BD_TRAFFIC_VOLUME_ANYTM_LNRD, function(){
                _Map.off("click", LAYER.BD_TRAFFIC_VOLUME_ANYTM_LNRD+"_none_text", MapEvents.BD_TRFVLM_ANYTM_NLRD);
                _Map.on("click", LAYER.BD_TRAFFIC_VOLUME_ANYTM_LNRD+"_none_text", MapEvents.BD_TRFVLM_ANYTM_NLRD);
            });
            if(document.querySelector("#layerControlList [data-layer='anytmNlrd']"))
                document.querySelector("#layerControlList [data-layer='anytmNlrd']").disabled = false
        },
        "BD_TRFVLM_ORDTM" : function (e){
            workerResultEvent["BD_COMMON_TRFVLM_CLUSTER"](e, STYLES.TRF_ORDTM_NLRD_COLOR, LAYER.BD_TRAFFIC_VOLUME_ORDTM_LNRD, function(){
                _Map.off("click", LAYER.BD_TRAFFIC_VOLUME_ORDTM_LNRD+"_none_text", MapEvents.BD_TRFVLM_ORDTM);
                _Map.on("click", LAYER.BD_TRAFFIC_VOLUME_ORDTM_LNRD+"_none_text", MapEvents.BD_TRFVLM_ORDTM);
            });
            if(document.querySelector("#layerControlList [data-layer='ordtmNlrd']"))
                document.querySelector("#layerControlList [data-layer='ordtmNlrd']").disabled = false
        }
    }

    workerResultEvent = _RemoveJobItem(workerResultEvent);
}