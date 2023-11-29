package Design.Idea.service;

import Design.Idea.mapper.VertexMapper;
import Design.Idea.model.Vertex;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
/*SW는 SideWalk(도보), R은 Road(도로)에 대한 것.*/
public class VertexServiceImpl implements VertexService {
    private final VertexMapper vertexMapper;

    @Override
    public void SWSaveVertex(Vertex vertex) throws Exception {
        vertexMapper.SWSaveVertex(vertex);
    }

    @Override
    public void RSaveVertex(Vertex vertex) throws Exception {
        vertexMapper.RSaveVertex(vertex);
    }

    @Override
    public List<Map<String, Object>> SelectSWVertex() throws Exception {
        return vertexMapper.SelectSWVertex();
    }

    @Override
    public List<Map<String, Object>> SelectRVertex() throws Exception {
        return vertexMapper.SelectRVertex();
    }

    @Override
    public void DeleteSWVertex(Vertex vertex) throws Exception {
        vertexMapper.DeleteSWVertex(vertex);
    }

    @Override
    public void DeleteRVertex(Vertex vertex) throws Exception {
        vertexMapper.DeleteRVertex(vertex);
    }
}
