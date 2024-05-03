/*
 * 클래스 기능 : 도로 정점, 간선에 대한 정보를 JSON 형식으로 반환하거나 수정할 수 있는 API 클래스
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.api;

import Design.Idea.dto.*;
import Design.Idea.service.RoadEdgeService;
import Design.Idea.service.RoadVertexService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/road/*")
public class RoadMapApiController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RoadVertexService roadVertexService;

    private final RoadEdgeService roadEdgeService;

    @GetMapping("/vertices")
    public VertexVCResponse<VertexDto> getAllRoadVertex() {
        logger.info("Get all road vertex");
        List<VertexDto> vertices = roadVertexService.findAll();
        VertexVCResponse<VertexDto> response = new VertexVCResponse<>();
        response.setVertex(vertices);
        return response;
    }

    @PostMapping("/vertex")
    public void saveRoadVertex(@RequestBody @Valid VertexVCRequest form) {
        logger.info("Save side walk vertex, latitude: {}, longitude: {}", form.getLatitude(), form.getLongitude());
        roadVertexService.save(form.getLatitude(), form.getLongitude());
    }

    @DeleteMapping("/vertex/{id}")
    public void deleteRoadVertex(@PathVariable("id") Long id) {
        logger.info("Delete side walk vertex, id: {}", id);
        roadVertexService.deleteById(id);
    }

    @GetMapping("/edges")
    public EdgeVCResponse<EdgeDto> getAllRoadEdge() {
        logger.info("Get all road edge");
        List<EdgeDto> edges = roadEdgeService.findAll();
        EdgeVCResponse<EdgeDto> response = new EdgeVCResponse<>();
        response.setEdge(edges);
        return response;
    }

    @PostMapping("/edge")
    public void saveRoadEdge(@RequestBody @Valid EdgeVCRequest form) {
        logger.info("Save side walk edge, vertex1: {}, vertex2: {}, length: {}", form.getVertex1(), form.getVertex2(), form.getLength());
        roadEdgeService.save(form.getVertex1(), form.getVertex2(), form.getLength());
    }

    @DeleteMapping("/edge")
    public void deleteRoadEdge(@RequestBody @Valid EdgeVCRequest form) {
        logger.info("Delete side walk edge, vertex1: {}, vertex2: {}", form.getVertex1(), form.getVertex2());
        roadEdgeService.deleteByVertices(form.getVertex1(), form.getVertex2());
    }
}
