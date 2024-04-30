/**
 * 실시간 버스 이동 조회
 * @returns {Promise<any>}
 * @constructor
 */
const M_Bus = async function(){
    let list = await self.util.getJsonFormApi("/monitoring/getBusInfo.ajax");
    let features = [];
    for(const info of list) {
        let popup = `<div style="color:#000">
                        <strong>${info.plateNo}</strong>
                        <p>asdf</p>
                    </div>
                        `;
		let busIcon = "bus_icon2";
		
		switch(info.routeTp) {
			case '11':
			//직행좌석형 시내버스
			busIcon = "bus_icon2";
			break;
			case '12':
			//좌석형 시내버스
			busIcon = "bus_icon4";
			break;
			case '13':
			//일반형 시내버스
			busIcon = "bus_icon2";
			break;
			case '21':
			//직행좌석농어촌버스
			busIcon = "bus_icon4";
			break;
			case '22':
			//좌석형 농어촌버스
			busIcon = "bus_icon4";
			break;
			case '23':
			//일반형 농어촌버스
			busIcon = "bus_icon1";
			break;
			case '30':
			//마을버스
			busIcon = "bus_icon3";
			break;
			case '41':
			//고속형 시외버스
			busIcon = "bus_icon1";
			break;
			case '42':
			//좌석형 시외버스
			busIcon = "bus_icon1";
			break;
			case '43':
			//일반형 시외버스
			busIcon = "bus_icon1";
			break;
			case '51':
			//리무진형 공항버스
			busIcon = "bus_icon5";
			break;
			case '52':
			//좌석형 공항버스
			busIcon = "bus_icon5";
			break;
			case '53':
			//일반형 공항버스
			busIcon = "bus_icon5";
			break;
			default : 
			break;
		}

        let props = {
            'description' : popup,
            'icon' : busIcon
        }
        for(const k in info){
            props[k] = info[k];
        }
        props['id'] = info.routeId+"_"+info.plateNo+"_"+info.companyId;
        features.push({
            'type': 'Feature',
            'properties' : props,
            'geometry': {
                'type': 'Point',
                'coordinates': [info.gpsX, info.gpsY]
            }
        });
    }
    return self.util.wrapFeatureCollection(features);
}