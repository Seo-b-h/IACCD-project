package Design.Idea.mapper;

import Design.Idea.model.Vertex;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
/*SW는 SideWalk(도보), R은 Road(도로)에 대한 것.*/
public interface VertexMapper {
    public void SWSaveVertex(Vertex vertex) throws Exception;

    public void RSaveVertex(Vertex vertex) throws Exception;

    public List<Map<String, Object>> SelectSWVertex() throws Exception;

    public List<Map<String, Object>> SelectRVertex() throws Exception;

    public void DeleteSWVertex(Vertex vertex) throws Exception;

    public void DeleteRVertex(Vertex vertex) throws Exception;
}
