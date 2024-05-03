/*
 * 클래스 기능 : 보도 지도에 대한 페이지를 반환하는 클래스
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SidewalkMapController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("sidewalkMap")
    public String sidewalkMap() {
        logger.info("Get sidewalk map");
        return "sidewalkMap";
    }
}
