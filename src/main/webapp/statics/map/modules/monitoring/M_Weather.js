/**
 * 날씨정보
 * @param roadData
 * @returns {Promise<any>}
 * @constructor
 */
const M_Weather = async function(){
    let list = await self.util.getJsonFormApi("/monitoring/getWeatherInfo.ajax");
    let features = [];
    for(const weather of list) {
        const latlng = self.util.dfs_xy_conv(null, weather.frcstPointXcord, weather.frcstPointYcord);
        features.push({
            'type': 'Feature',
            'properties' : {
                'icon' : self.util.getWeatherIcon(weather.frcstVal)
            },
            'geometry': {
                'type': 'Point',
                'coordinates': [latlng.lng, latlng.lat]
            }
        });
    }
    return self.util.wrapFeatureCollection(features);
}