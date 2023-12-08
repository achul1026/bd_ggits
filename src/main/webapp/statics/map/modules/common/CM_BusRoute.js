/**
 * 버스 노선정보
 * @returns {Promise<{featureCollection: {features: *, type: string}}>}
 * @constructor
 */
const CM_BusRoute = async function(routeId){
    let param = "";
    if(routeId) {
        param = "?routeId="+routeId;
    }
    let list =  await self.util.getJsonFormApi("/monitoring/getBusRouteInfo.ajax"+param);
    let features = [];
    for(const info of list) {
        const routeLength = info.routeIds.split("||").length;
        let routeTable = `<div class="popup_scroll">
				<table class="popup_table">
                <thead>
                <th>노선명</th>
                <th>관리자</th>
                <th>회사명</th>
                <th>기점정류소</th>
                <th>종점정류소</th>
                </thead>
                <tbody>
                `;
        const routeNms = info.routeNms ? info.routeNms.split("||") : [];
        const adminNms = info.adminNms ? info.adminNms.split("||") : [];
        const companyNms = info.companyNms ? info.companyNms.split("||") : [];
        const stStaNms = info.stStaNms ? info.stStaNms.split("||") : [];
        const edStaNms = info.edStaNms ? info.edStaNms.split("||") : [];
        for(let i = 0; i < routeLength; i++){
            routeTable += `
                    <tr>
                        <th>${routeNms[i] ? routeNms[i] : '-'}</th>
                        <td>${adminNms[i] ? adminNms[i] : '-'}</td>
                        <td>${companyNms[i] ? companyNms[i] : '-'}</td>
                        <td>${stStaNms[i] ? stStaNms[i] : '-'}</td>
                        <td>${edStaNms[i] ? edStaNms[i] : '-'}</td>
                    </tr>`;
        }
        routeTable += "</tbody></table></div>";
        const obj = {
            'type': 'Feature',
            'properties' : {
                routeTable : routeTable
            },
            'geometry': JSON.parse(info.geojson)
        }
        for(const prop in info){
            if(prop !== "geojson") {
                obj.properties[prop] = info[prop];
            }
        }
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}