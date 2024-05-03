//package Design.Idea.controller;
//
//import Design.Idea.model.Edge;
//import Design.Idea.model.EdgeInfo;
//import Design.Idea.service.EdgeService;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequiredArgsConstructor
///*SW는 SideWalk(도보), R은 Road(도로)에 대한 것.*/
//public class EdgeController {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    private final EdgeService edgeService;
//
//    @PostMapping("/saveEdge")
//    /*여러 개의 간선 정보들을 문자열 형태로 전달받아 DB에 저장한다.*/
//    public String saveEdge(EdgeInfo EdgeInfo) throws Exception {
//        String[] edge = EdgeInfo.getEdge_info().split(",");
//        String RoadOrSideWalk = edge[0];
//        if (RoadOrSideWalk.equals("0")) {
//            for (int i = 1; i < edge.length; i += 3) {
//                edgeService.SWSaveEdge(new Edge(Integer.parseInt(edge[i]) + 1, Integer.parseInt(edge[i + 1]) + 1, edge[i + 2]));
//                edgeService.SWSaveEdge(new Edge(Integer.parseInt(edge[i + 1]) + 1, Integer.parseInt(edge[i]) + 1, edge[i + 2]));
//            }
//        } else {
//            for (int i = 1; i < edge.length; i += 4) {
//                //logger.info("trace log={}", EdgeInfo.getEdge_info());
//                edgeService.RSaveEdge(new Edge(Integer.parseInt(edge[i]) + 1, Integer.parseInt(edge[i + 1]) + 1, edge[i + 2], edge[i + 3]));
//                if(edge[i+3].equals("NO"))edgeService.RSaveEdge(new Edge(Integer.parseInt(edge[i + 1]) + 1, Integer.parseInt(edge[i]) + 1, edge[i + 2], edge[i + 3]));
//            }
//        }
//        /*return "edgemap";*/
//        return "redirect:/map";
//    }
//
//    @RequestMapping(value = "/selectEdge", method = RequestMethod.POST)
//    @ResponseBody
//    /*DB에 저장되어 있는 간선 정보들을 가져와 웹페이지로 반환한다.*/
//    public List<Map<String, Object>> selectEdge(@RequestParam("ROS") String Road_OR_SideWalk) throws Exception {
//        if (Road_OR_SideWalk.equals("0")) {
//            //logger.info("selectSWEdge log {}", edgeService.SelectSWEdge());
//            return edgeService.SelectSWEdge();
//        } else {
//            //logger.info("selectREdge log {}", edgeService.SelectREdge());
//            return edgeService.SelectREdge();
//        }
//    }
//}
