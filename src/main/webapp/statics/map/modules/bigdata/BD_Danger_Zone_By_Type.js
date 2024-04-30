/**
 * 교통위험 구간분석
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Danger_Zone_By_Type = async function(searchOption = ''){
    let data = await self.util.getJsonFormApi("/bigdata/getAllAccidentInfo.ajax?"+searchOption);
    if(data?.noLogin){
        return {
            error : true,
            noLogin : true
        }
    }
    if(data.positions.length === 0) {
        return {
            error : true,
            errorMsg : "해당일자에 조회된 데이터가 없습니다."
        }
    }
    let features = [];
    for(const point of data.positions) {
        const obj = {
            'type': 'Feature',
            'properties' : {},
            'geometry': {
                'type': 'Point',
                'coordinates': [point.lonCrdn, point.latCrdn]
            }
        }
        /*const accidentPolygon = {
            'type': 'Feature',
            'properties' : {},
            'geometry': JSON.parse(point.acdntDstrctPyn)
        }*/
        /*
        BCYCL : 자전거 사고 구역
        JAYWK : 무단횡단 보행자 사고구역
        LAWVLTN : 법위반 보행자 사고구역
        HLDY : 휴일기간 보행자 사고구역
        FROST : 결빙사고 구역
        TWHLVH : 이륜차 사고 구역
        PDSN : 보행자 사고 구역
        DRNKG : 음주 사고 구역
        TRUCK : 화물차 사고 구역
        OLMAN : 노인 보행자 사고 구역
         */
        for(const prop in point){
            /*accidentPolygon.properties[prop] = point[prop];*/
            obj.properties[prop] = point[prop];
            if(prop === "type" && !obj.properties.icon) {
                obj.properties.icon = "accident_"+point["type"];
            }
        }
        features.push(obj);
        /*features.push(accidentPolygon);*/
    }
    /*
    acdntCnt : 사고수
    casltCnt : 사상자수
    dcsdCnt : 사망자수
    swpsnCnt : 중상자수
    sinjpsnCnt : 경상자수
    injDclrCnt : 부상신고수
     */
    let sggFeatures = self.util.getSGGFeatures(self.env);
    // 행정구역별로 그룹
    for(const sggCd in data.sggGroup) {
        let point = data.sggGroup[sggCd];
        let sgg = sggFeatures.filter((obj) => obj.sggCode === sggCd+"0")[0];
        if(sgg) {
            sgg.properties.acdntCnt = point.acdntCnt;
            sgg.properties.casltCnt = point.casltCnt;
            sgg.properties.dcsdCnt = point.dcsdCnt;
            sgg.properties.swpsnCnt = point.swpsnCnt;
            sgg.properties.sinjpsnCnt = point.sinjpsnCnt;
            sgg.properties.injDclrCnt = point.injDclrCnt;
        }
    }
    return {
        collection : self.util.wrapFeatureCollection(features),
        sggCollection : self.util.wrapFeatureCollection(sggFeatures)
    };
}