package com.neighbor21.ggits.api.module.monitoring;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.api.module.monitoring.dto.MBusInfo;
import com.neighbor21.ggits.common.entity.GgbisBusEventinfo;
import com.neighbor21.ggits.common.entity.GgbisBusrouteLink;
import com.neighbor21.ggits.common.mapper.GgbisBusEventinfoMapper;
import com.neighbor21.ggits.common.mapper.GgbisBusrouteLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 시내버스 이동현황 데이터 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  MBusComponent
 * @since : 2023-09-07
 */
@Component
public class MBusComponent extends BaseMapDataComponent {

    @Autowired
    GgbisBusEventinfoMapper ggbisBusEventinfoMapper;


    /**
     * 실시간 버스이동현황 조회
     * @return
     */
    public List<GgbisBusEventinfo> getRealtimeBusMoveInfo(){
        //ggbisBusEventinfoMapper.findAllRealTimeBusMoveInfo();
        return null;
    }

    int testcount = 0;
    public List<MBusInfo> getBusMoveInfoList(){
        List<String[]> testdata = new ArrayList<>();
        testdata.add(new String []{"127.11517150805295","37.42221479924626"});
        testdata.add(new String []{"127.11471439939037","37.42227293674498"});
        testdata.add(new String []{"127.11442579987174","37.4222938515426"});
        testdata.add(new String []{"127.11378177457519","37.42233107699198"});

        testdata.add(new String []{"127.11361375548655","37.42233522468827"});
        testdata.add(new String []{"127.1132136593506","37.4223501815826"});
        testdata.add(new String []{"127.11297077452694","37.422363493106914"});
        testdata.add(new String []{"127.11266704000867","37.42237136593351"});
        testdata.add(new String []{"127.11236189094673","37.42232702986305"});
        testdata.add(new String []{"127.1121169674642","37.422313449801344"});
        testdata.add(new String []{"127.11188367702516","37.422286314127156"});
        testdata.add(new String []{"127.11165775650602","37.42224585579417"});
        testdata.add(new String []{"127.11143602909794","37.42219351045682"});
        testdata.add(new String []{"127.1112191865565","37.422129459493085"});
        testdata.add(new String []{"127.11100819982506","37.4220539894423"});
        testdata.add(new String []{"127.11080401166626","37.42196743733328"});
        testdata.add(new String []{"127.11060753553176","37.4218701915857"});
        testdata.add(new String []{"127.11041965103281","37.42176268570639"});
        testdata.add(new String []{"127.11024119942667","37.4216454027977"});
        testdata.add(new String []{"127.11007297908392","37.421518866550706"});
        testdata.add(new String []{"127.10991574210254","37.42138364395036"});
        testdata.add(new String []{"127.10977019203928","37.42124033896949"});
        testdata.add(new String []{"127.10963698165301","37.421089594372035"});
        testdata.add(new String []{"127.10951670385302","37.420932081809134"});
        testdata.add(new String []{"127.10940990188173","37.42076851172006"});
        testdata.add(new String []{"127.10931704894578","37.420599609022624"});
        testdata.add(new String []{"127.10923856180403","37.4204261338233"});
        testdata.add(new String []{"127.10917484728014","37.42024901472406"});
        testdata.add(new String []{"127.10911813874718","37.42003328770913"});
        testdata.add(new String []{"127.10907370189858","37.41979933435842"});
        testdata.add(new String []{"127.10903801466334","37.41956610814258"});
        testdata.add(new String []{"127.10899657436701","37.41929871249813"});
        testdata.add(new String []{"127.10896228787992","37.41909396793091"});
        testdata.add(new String []{"127.10891262787354","37.418827624090866"});
        testdata.add(new String []{"127.10884687431223","37.41854835551454"});
        testdata.add(new String []{"127.10875698773344","37.41822189483706"});
        testdata.add(new String []{"127.10864983078112","37.41789245406016"});
        testdata.add(new String []{"127.10854068313687","37.41760054961495"});
        testdata.add(new String []{"127.10842665690825","37.41732869178028"});
        testdata.add(new String []{"127.10830976423074","37.417076284930346"});
        testdata.add(new String []{"127.10820188567043","37.41686385690099"});
        testdata.add(new String []{"127.10810485910132","37.41668840024332"});
        testdata.add(new String []{"127.10809682681484","37.41667388136858"});
        testdata.add(new String []{"127.10801145313994","37.416526079744976"});
        testdata.add(new String []{"127.1078854326523","37.41631568265663"});
        testdata.add(new String []{"127.10773622493494","37.41608481018886"});
        testdata.add(new String []{"127.10759111255298","37.415871759547564"});
        testdata.add(new String []{"127.10739649569418","37.415594238065346"});
        testdata.add(new String []{"127.10531197062826","37.41263868289009"});
        testdata.add(new String []{"127.10495566665226","37.41213575532482"});

        if(testdata.size()-1 < testcount) {
            testcount = 0;
        }
        List<MBusInfo> emergencyVehicleList = new ArrayList<>();

        // testdata
        MBusInfo info = new MBusInfo();
        info.setVehicleNumber("18두09000");
        info.setEndLng(testdata.get(testdata.size()-1)[0]);
        info.setEndLat(testdata.get(testdata.size()-1)[1]);
        info.setStartLng(testdata.get(0)[0]);
        info.setStartLat(testdata.get(0)[1]);

        info.setLng(testdata.get(testcount)[0]);
        info.setLat(testdata.get(testcount)[1]);
        info.setIcon("bus");
        emergencyVehicleList.add(info);
        testcount++;
        return emergencyVehicleList;
    }
}
