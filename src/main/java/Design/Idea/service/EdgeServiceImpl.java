package Design.Idea.service;

import Design.Idea.mapper.EdgeMapper;
import Design.Idea.model.Edge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
/*SW는 SideWalk(도보), R은 Road(도로)에 대한 것.*/
public class EdgeServiceImpl implements EdgeService {
    private final EdgeMapper edgeMapper;

    @Override
    public void SWSaveEdge(Edge edge) throws Exception {
        edgeMapper.SWSaveEdge(edge);
    }

    @Override
    public void RSaveEdge(Edge edge) throws Exception {
        edgeMapper.RSaveEdge(edge);
    }

    @Override
    public List<Map<String, Object>> SelectSWEdge() throws Exception {
        return edgeMapper.SelectSWEdge();
    }

    @Override
    public List<Map<String, Object>> SelectREdge() throws Exception {
        return edgeMapper.SelectREdge();
    }
}
