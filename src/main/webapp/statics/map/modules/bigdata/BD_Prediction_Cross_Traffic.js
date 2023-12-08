/**
 * 교차로 교통량 예측
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Prediction_Cross_Traffic = async function(searchOption = ''){
    let list = await self.util.getJsonFormApi("/bigdata/getCrossRoadTrafficQuantityPrediction.ajax"+searchOption);
    let features = [];
    for(const item of list) {
        let popup = `
        `;
        const obj = {
            'type': 'Feature',
            'id' : item.nodeId,
            'properties' : {
                description : popup
            },
            'geometry': {
                'type': 'Point',
                'coordinates': [item.lonCrdn, item.latCrdn]
            }
        }

        for(const prop in item){
            obj.properties[prop] = item[prop];
        }
        const timeContentArray = item.timeGroupTxt.split("$$");
        item.timer = {};
        for(const a of timeContentArray) {
            const cont = a.split("|");
            const time = cont[0];
            const strghtTrfvlm = cont[1];
            const trnghtTrfvlm = cont[2];
            const trnlftTrfvlm = cont[3];
            obj.properties[time+"_trfvlm"] = strghtTrfvlm+trnghtTrfvlm+trnlftTrfvlm;
        }
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}