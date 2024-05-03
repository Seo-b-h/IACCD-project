/*
 * 클래스 기능 : 도보 정점, 간선에 대한 정보를 JSON 형식으로 반환하거나 수정할 수 있는 API 클래스
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.api;

import Design.Idea.dto.*;
import Design.Idea.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sidewalk/*")
public class SidewalkMapApiController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SidewalkVertexService sidewalkVertexService;

    private final SidewalkEdgeService sidewalkEdgeService;

    @GetMapping("/vertices")
    public VertexVCResponse<VertexDto> getAllSidewalkVertex() {
        logger.info("Get all sidewalk vertex");
        List<VertexDto> vertices = sidewalkVertexService.findAll();
        VertexVCResponse<VertexDto> response = new VertexVCResponse<>();
        response.setVertex(vertices);
        return response;
    }

    @PostMapping("/vertex")
    public void saveSidewalkVertex(@RequestBody @Valid VertexVCRequest form) {
        logger.info("Save side walk vertex, latitude: {}, longitude: {}", form.getLatitude(), form.getLongitude());
        sidewalkVertexService.save(form.getLatitude(), form.getLongitude());
    }

    @DeleteMapping("/vertex/{id}")
    public void deleteSidewalkVertex(@PathVariable("id") Long id) {
        logger.info("Delete side walk vertex, id: {}", id);
        sidewalkVertexService.deleteById(id);
    }

    @GetMapping("/edges")
    public EdgeVCResponse<EdgeDto> getAllSidewalkEdge() {
        logger.info("Get all sidewalk edge");
        List<EdgeDto> edges = sidewalkEdgeService.findAll();
        EdgeVCResponse<EdgeDto> response = new EdgeVCResponse<>();
        response.setEdge(edges);
        return response;
    }

    @PostMapping("/edge")
    public void saveSidewalkEdge(@RequestBody @Valid EdgeVCRequest form) {
        logger.info("Save side walk edge, vertex1: {}, vertex2: {}, length: {}", form.getVertex1(), form.getVertex2(), form.getLength());
        sidewalkEdgeService.save(form.getVertex1(), form.getVertex2(), form.getLength());
    }

    @DeleteMapping("/edge")
    public void deleteSidewalkEdge(@RequestBody @Valid EdgeVCRequest form) {
        logger.info("Delete side walk edge, vertex1: {}, vertex2: {}", form.getVertex1(), form.getVertex2());
        sidewalkEdgeService.deleteByVertices(form.getVertex1(), form.getVertex2());
    }
}
