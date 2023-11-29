package Design.Idea.controller;

import Design.Idea.model.Vertex;
import Design.Idea.model.VertexInfo;
import Design.Idea.service.VertexService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
/*SW는 SideWalk(도보), R은 Road(도로)에 대한 것.*/
public class VertexController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final VertexService vertexService;

    @PostMapping("/saveVertex")
    /*여러 개의 정점 정보들을 문자열 형태로 전달받아 DB에 저장한다.*/
    public String saveVertex(VertexInfo vertexInfo) throws Exception {
        //logger.info("trace log={}", vertexInfo.getVertex_info());
        String[] vertex = vertexInfo.getVertex_info().split(",");
        String RoadOrSideWalk = vertex[0];
        if(RoadOrSideWalk.equals("0")) {
            for (int i = 1; i < vertex.length; i += 4) {
                vertexService.SWSaveVertex(new Vertex(vertex[i], vertex[i + 1], vertex[i + 2], vertex[i+3]));
            }
        }
        else {
            for (int i = 1; i < vertex.length; i += 4) {
                vertexService.RSaveVertex(new Vertex(vertex[i], vertex[i + 1], vertex[i + 2], vertex[i+3]));
            }
        }
        return "redirect:/map";
    }

    @RequestMapping(value = "/selectVertex", method = RequestMethod.POST)
    @ResponseBody
    /*DB에 저장되어 있는 정점 정보들을 가져와 웹페이지로 반환한다.*/
    public List<Map<String, Object>> selectVertex(@RequestParam("ROS") String Road_OR_SideWalk) throws Exception {
        if(Road_OR_SideWalk.equals("0")) {
            //logger.info("selectSWVertex log {}", vertexInfo);
            return vertexService.SelectSWVertex();
        }
        else {
            //logger.info("selectRVertex log {}", vertexInfo);
            return vertexService.SelectRVertex();
        }
    }

    @PostMapping("deleteVertex")
    /*웹페이지의 요청으로 DB에 저장되어 있는 정보를 삭제한다.*/
    public String deleteVertex(@RequestParam("vertexInfo") String vertexInfo) throws Exception {
        String[] vertex = vertexInfo.split(",");
        String RoadOrSideWalk = vertex[0];
        if(RoadOrSideWalk.equals("0")) {
            vertexService.DeleteSWVertex(new Vertex(vertex[1], vertex[2], vertex[3], vertex[4]));
        }
        else {
            vertexService.DeleteRVertex(new Vertex(vertex[1], vertex[2], vertex[3], vertex[4]));
        }
        return "redirect:/map";
    }
}
