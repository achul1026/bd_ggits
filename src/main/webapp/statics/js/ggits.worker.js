self.importScripts(
    "./xlsx.full.min.js"
)

let loginTimeInterval = null;
let loginTimeout = null;
let loginMaxTime = 25 * 60 * 1000;
let loginRemainTime = 5*60; // 5분 동안 알라미
self.onmessage = function (e){
    switch(e.data.event) {
        case "LOGIN.TIMEOUT_START" :
            startLoginTimeout();
            break;
        case "EXCEL.GENERATE" :
            generateExcelFile(e.data);
            break;
    }
}

/*Excel 파일 생성*/
function generateExcelFile({exportType, header, metadata, rows, filename}){

    switch (exportType){
        case "featureCollection" :
            rows = rows.map(function(feature){
                let obj = {};
                for(const key of metadata){
                    obj[key] = feature.properties[key];
                }
                return obj;
            });
            break;
        default :
            if(metadata) {
                rows = rows.map(function (oldObj) {
                    let obj = {};
                    for (const key of metadata) {
                        obj[key] = oldObj[key];
                    }
                    return obj;
                });
            }
    }
    console.log("Excel download process --- 01");
    const workbook = XLSX.utils.book_new();
    const ws = XLSX.utils.json_to_sheet([]);
    XLSX.utils.sheet_add_aoa(ws,[header]);
    XLSX.utils.sheet_add_json(ws, rows, {origin : "A2", skipHeader : true});
    XLSX.utils.book_append_sheet(workbook, ws, "Sheet1");
    console.log("Excel download process --- 02");
    /* fix headers */
    /*XLSX.utils.sheet_add_aoa(worksheet, data.header, { origin: "A1" });*/

    /* calculate column width */
    /*const max_width = rows.reduce((w, r) => Math.max(w, r.name.length), 10);
    worksheet["!cols"] = [ { wch: max_width } ];*/
    const uint8 = XLSX.write(workbook, { type: "array", bookType: "xlsx" });
    filename = filename ? filename+(filename.endsWith(".xlsx") ? "" : ".xlsx") : "데이터추출.xlsx";
    console.log("Excel download process --- 03");
    self.postMessage({
        event : "EXCEL.GENERATE",
        uint8 : uint8,
        filename : filename
    });
}


/*자동 로그아웃 설정 함수*/
function startLoginTimeout(){
    if(loginTimeout !== null) clearTimeout(loginTimeout);
    if(loginTimeInterval !== null) clearInterval(loginTimeInterval);
    loginRemainTime = 5*60;
    loginTimeout = setTimeout(function(){
        loginTimeInterval = setInterval(function(){
            const min = parseInt(loginRemainTime/60);
            const sec = loginRemainTime%60;
            loginRemainTime--;
            if(loginRemainTime < 0) {
                self.postMessage({
                    event : "LOGIN.DO_LOGOUT",
                    min : min,
                    sec : sec
                });
            }
            self.postMessage({
                event : "LOGIN.INTERVAL_1000",
                min : min,
                sec : sec
            });
        },1000);
    }, loginMaxTime);
}
