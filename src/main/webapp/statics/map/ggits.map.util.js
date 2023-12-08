
const GitsMapUtil = function(){
    
    /**
     * 혼잡도 계산
     * 1 : 원할
     * 2 : 지체(서행)
     * 3 : 정체
     */
    this.getTrafficConGrade = function(roadRank, speed){
        let grade = "1";
        if(roadRank === "101") {
            if(speed >= 0 && speed <30){
                grade = "3";
            }else if(speed >= 30 && speed <70) {
                grade = "2";
            }else {
                grade = "1";
            }
        }else if(roadRank ==="102"){
            if(speed >= 0 && speed <30){
                grade = "3";
            }else if(speed >= 30 && speed <50) {
                grade = "2";
            }else {
                grade = "1";
            }
        }else {
            if(speed >= 0 && speed <20){
                grade = "3";
            }else if(speed >= 20 && speed <40) {
                grade = "2";
            }else {
                grade = "1";
            }
        }
        return grade;
    }

    this.getXMLfromAPI = async (url) => {
        const reqURL = url;
        const response = await fetch(reqURL, { mode: 'no-cors'});
        const xmlString = await response.text();
        let XmlNode = new DOMParser().parseFromString(xmlString, "text/xml");
        return JSON.parse(this.xmlToJson(XmlNode));
    };
    this.getStringfromAPI = async (url) => {
        const reqURL = url;
        const response = await fetch(reqURL, { mode: 'no-cors'});
        const xmlString = await response.text();
        return xmlString;
    };
    this.getJsonFormApi = async (url, data) => {
        const reqURL = url;
        let opt = { mode: 'no-cors'};
        if(data){
            opt.body = JSON.stringify(data);
        }
        const response = await fetch(reqURL, data);
        const json = await response.json();
        return json;
    };
    this.xmlStrToJson = function(str){
        let XmlNode = new DOMParser().parseFromString(str, "text/xml");
        return JSON.parse(this.xmlToJson(XmlNode));
    }
    this.xmlToJson = function(xml, tab, ignoreAttrib) {
        var X = {
            toObj: function(xml) {
                var o = {};
                if (xml.nodeType==1) {   // element node ..
                    if (ignoreAttrib && xml.attributes.length)   // element with attributes  ..
                        for (var i=0; i<xml.attributes.length; i++)
                            o["@"+xml.attributes[i].nodeName] = (xml.attributes[i].nodeValue||"").toString();
                    if (xml.firstChild) { // element has child nodes ..
                        var textChild=0, cdataChild=0, hasElementChild=false;
                        for (var n=xml.firstChild; n; n=n.nextSibling) {
                            if (n.nodeType==1) hasElementChild = true;
                            else if (n.nodeType==3 && n.nodeValue.match(/[^ \f\n\r\t\v]/)) textChild++; // non-whitespace text
                            else if (n.nodeType==4) cdataChild++; // cdata section node
                        }
                        if (hasElementChild) {
                            if (textChild < 2 && cdataChild < 2) { // structured element with evtl. a single text or/and cdata node ..
                                X.removeWhite(xml);
                                for (var n=xml.firstChild; n; n=n.nextSibling) {
                                    if (n.nodeType == 3)  // text node
                                        o["#text"] = X.escape(n.nodeValue);
                                    else if (n.nodeType == 4)  // cdata node
                                        o["#cdata"] = X.escape(n.nodeValue);
                                    else if (o[n.nodeName]) {  // multiple occurence of element ..
                                        if (o[n.nodeName] instanceof Array)
                                            o[n.nodeName][o[n.nodeName].length] = X.toObj(n);
                                        else
                                            o[n.nodeName] = [o[n.nodeName], X.toObj(n)];
                                    }
                                    else  // first occurence of element..
                                        o[n.nodeName] = X.toObj(n);
                                }
                            }
                            else { // mixed content
                                if (!xml.attributes.length)
                                    o = X.escape(X.innerXml(xml));
                                else
                                    o["#text"] = X.escape(X.innerXml(xml));
                            }
                        }
                        else if (textChild) { // pure text
                            if (!xml.attributes.length)
                                o = X.escape(X.innerXml(xml));
                            else
                                o["#text"] = X.escape(X.innerXml(xml));
                        }
                        else if (cdataChild) { // cdata
                            if (cdataChild > 1)
                                o = X.escape(X.innerXml(xml));
                            else
                                for (var n=xml.firstChild; n; n=n.nextSibling)
                                    o["#cdata"] = X.escape(n.nodeValue);
                        }
                    }
                    if (!xml.attributes.length && !xml.firstChild) o = null;
                }
                else if (xml.nodeType==9) { // document.node
                    o = X.toObj(xml.documentElement);
                }
                else
                    alert("unhandled node type: " + xml.nodeType);
                return o;
            },
            toJson: function(o, name, ind) {
                let json = name ? ("\"" + name + "\"") : "";
                if (o instanceof Array) {
                    for (var i=0,n=o.length; i<n; i++)
                        o[i] = X.toJson(o[i], "", ind+"\t");
                    json += (name?":[":"[") + (o.length > 1 ? ("\n"+ind+"\t"+o.join(",\n"+ind+"\t")+"\n"+ind) : o.join("")) + "]";
                }
                else if (o == null)
                    json += (name&&":") + "null";
                else if (typeof(o) == "object") {
                    var arr = [];
                    for (var m in o)
                        arr[arr.length] = X.toJson(o[m], m, ind+"\t");
                    json += (name?":{":"{") + (arr.length > 1 ? ("\n"+ind+"\t"+arr.join(",\n"+ind+"\t")+"\n"+ind) : arr.join("")) + "}";
                }
                else if (typeof(o) == "string")
                    json += (name&&":") + "\"" + o.toString() + "\"";
                else
                    json += (name&&":") + o.toString();
                return json;
            },
            innerXml: function(node) {
                let s = "";
                if ("innerHTML" in node)
                    s = node.innerHTML;
                else {
                    const asXml = function (n) {
                        let s = "";
                        if (n.nodeType == 1) {
                            s += "<" + n.nodeName;
                            for (var i = 0; i < n.attributes.length; i++)
                                s += " " + n.attributes[i].nodeName + "=\"" + (n.attributes[i].nodeValue || "").toString() + "\"";
                            if (n.firstChild) {
                                s += ">";
                                for (var c = n.firstChild; c; c = c.nextSibling)
                                    s += asXml(c);
                                s += "</" + n.nodeName + ">";
                            } else
                                s += "/>";
                        } else if (n.nodeType == 3)
                            s += n.nodeValue;
                        else if (n.nodeType == 4)
                            s += "<![CDATA[" + n.nodeValue + "]]>";
                        return s;
                    };
                    for (let c=node.firstChild; c; c=c.nextSibling)
                        s += asXml(c);
                }
                return s;
            },
            escape: function(txt) {
                return txt.replace(/[\\]/g, "\\\\")
                    .replace(/[\"]/g, '\\"')
                    .replace(/[\n]/g, '\\n')
                    .replace(/[\r]/g, '\\r');
            },
            removeWhite: function(e) {
                e.normalize();
                for (let n = e.firstChild; n; ) {
                    if (n.nodeType == 3) {  // text node
                        if (!n.nodeValue.match(/[^ \f\n\r\t\v]/)) { // pure whitespace text node
                            var nxt = n.nextSibling;
                            e.removeChild(n);
                            n = nxt;
                        }
                        else
                            n = n.nextSibling;
                    }
                    else if (n.nodeType == 1) {  // element node
                        X.removeWhite(n);
                        n = n.nextSibling;
                    }
                    else                      // any other node
                        n = n.nextSibling;
                }
                return e;
            }
        };
        if (xml.nodeType == 9) // document node
            xml = xml.documentElement;
        const json = X.toJson(X.toObj(X.removeWhite(xml)), xml.nodeName, "\t");
        return "{\n" + (tab ? json.replace(/\t/g, tab) : json.replace(/\t|\n/g, "")) + "\n}";
    }

    this.wrapFeatureCollection = function(features){
        return {
            featureCollection: {
                type: "FeatureCollection",
                features: features
            }
        }
    }

    this.removeByAttr = function(arr, attr, value){
        var i = arr.length;
        while(i--){
            if( arr[i]
                && arr[i].hasOwnProperty(attr)
                && (arguments.length > 2 && arr[i][attr] === value ) ){

                arr.splice(i,1);

            }
        }
        return arr;
    }

    this.getSGGFeatures = function(env){
        let features = [];
        for(const sggInfo in env.SGG_INFO) {
            const sggInfoObj = env.SGG_INFO[sggInfo];
            features.push({
                'type': 'Feature',
                'properties' : {
                    "sggNm" : sggInfo
                },
                'sggCode' : sggInfoObj.CODE,
                'geometry': {
                    'type': 'Point',
                    'coordinates': sggInfoObj.COORDINATES
                }
            })
        }
        return features;
    }
    this.getSGGInfoByCode = function(code, env){
        for(const sggInfo in env.SGG_INFO) {
            if(env.SGG_INFO[sggInfo].CODE === code) {
                return env.SGG_INFO[sggInfo];
            }
        }
    }

    this.wktToGeoJson = function(string) {
        return wellknown.parse(string);
    }

    this.getWeatherIcon = function(value){
        let icon = "weather_sun";
        switch(value) {
            case "1" :
                icon = "weather_rain";
                break;
            case "2" :
                icon = "weather_rain";
                break;
            case "3" :
                icon = "weather_snow";
                break;
            case "4" :
                icon = "weather_rain";
                break;
            case "5" :
                icon = "weather_rain";
                break;
            case "6" :
                icon = "weather_rain";
                break;
            case "7" :
                icon = "weather_snow";
                break;
        }
        return icon;
    }

    this.dfs_xy_conv = function(code, v1, v2) {
        // LCC DFS 좌표변환을 위한 기초 자료
        //
        const RE = 6371.00877; // 지구 반경(km)
        const GRID = 5.0; // 격자 간격(km)
        const SLAT1 = 30.0; // 투영 위도1(degree)
        const SLAT2 = 60.0; // 투영 위도2(degree)
        const OLON = 126.0; // 기준점 경도(degree)
        const OLAT = 38.0; // 기준점 위도(degree)
        const XO = 43; // 기준점 X좌표(GRID)
        const YO = 136; // 기1준점 Y좌표(GRID)
        //
        // LCC DFS 좌표변환 ( code : "toXY"(위경도->좌표, v1:위도, v2:경도), "toLL"(좌표->위경도,v1:x, v2:y) )
        const DEGRAD = Math.PI / 180.0;
        const RADDEG = 180.0 / Math.PI;

        const re = RE / GRID;
        const slat1 = SLAT1 * DEGRAD;
        const slat2 = SLAT2 * DEGRAD;
        const olon = OLON * DEGRAD;
        const olat = OLAT * DEGRAD;

        let sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
        let sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
        let ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
        ro = re * sf / Math.pow(ro, sn);
        let rs = {};
        if (code === "toXY") {
            rs['lat'] = v1;
            rs['lng'] = v2;
            var ra = Math.tan(Math.PI * 0.25 + (v1) * DEGRAD * 0.5);
            ra = re * sf / Math.pow(ra, sn);
            var theta = v2 * DEGRAD - olon;
            if (theta > Math.PI) theta -= 2.0 * Math.PI;
            if (theta < -Math.PI) theta += 2.0 * Math.PI;
            theta *= sn;
            rs['x'] = Math.floor(ra * Math.sin(theta) + XO + 0.5);
            rs['y'] = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);
        }
        else {
            rs['x'] = v1;
            rs['y'] = v2;
            const xn = v1 - XO;
            const yn = ro - v2 + YO;
            ra = Math.sqrt(xn * xn + yn * yn);
            if (sn < 0.0){
                ra = -ra
            };
            let alat = Math.pow((re * sf / ra), (1.0 / sn));
            alat = 2.0 * Math.atan(alat) - Math.PI * 0.5;

            if (Math.abs(xn) <= 0.0) {
                theta = 0.0;
            }
            else {
                if (Math.abs(yn) <= 0.0) {
                    theta = Math.PI * 0.5;
                    if (xn < 0.0){
                        theta = -theta;
                    }
                }
                else theta = Math.atan2(xn, yn);
            }
            const alon = theta / sn + olon;
            rs['lat'] = alat * RADDEG;
            rs['lng'] = alon * RADDEG;
        }
        return rs;
    }

    this.createDonutChart = function(props,title, counts, colors) {
        const offsets = [];
        let total = 0;
        for (const count of counts) {
            offsets.push(total);
            total += count;
        }
        const fontSize =
            total >= 1000 ? 22 : total >= 100 ? 20 : total >= 10 ? 18 : 16;
        const r = Math.max(total, 50);
            //title >= 1000 ? 50 : total >= 100 ? 32 : total >= 10 ? 24 : 18;
        const r0 = Math.round(r * 0.6);
        const w = r * 2;

        let html = `<div>
        <svg width="${w}" height="${w}" viewbox="0 0 ${w} ${w}" text-anchor="middle" style="font: ${fontSize}px sans-serif; display: block">`;

                for (let i = 0; i < counts.length; i++) {
                    html += _donutSegment(
                        offsets[i] / total,
                        (offsets[i] + counts[i]) / total,
                        r,
                        r0,
                        colors[i]
                    );
                }
                html += `<circle cx="${r}" cy="${r}" r="${r0}" fill="white" />
        <text dominant-baseline="central" transform="translate(${r}, ${r})">
        ${title ? title : total.toLocaleString()}
        </text>
        </svg>
        </div>`;

        const el = document.createElement('div');
        el.innerHTML = html;
        return el.firstChild;
    }

    function _donutSegment(start, end, r, r0, color) {
        if (end - start === 1) end -= 0.00001;
        const a0 = 2 * Math.PI * (start - 0.25);
        const a1 = 2 * Math.PI * (end - 0.25);
        const x0 = Math.cos(a0),
            y0 = Math.sin(a0);
        const x1 = Math.cos(a1),
            y1 = Math.sin(a1);
        const largeArc = end - start > 0.5 ? 1 : 0;

// draw an SVG path
        return `<path d="M ${r + r0 * x0} ${r + r0 * y0} L ${r + r * x0} ${
            r + r * y0
        } A ${r} ${r} 0 ${largeArc} 1 ${r + r * x1} ${r + r * y1} L ${
            r + r0 * x1
        } ${r + r0 * y1} A ${r0} ${r0} 0 ${largeArc} 0 ${r + r0 * x0} ${
            r + r0 * y0
        }" fill="${color}" />`;
    }

}