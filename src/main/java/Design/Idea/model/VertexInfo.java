package Design.Idea.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
/*여러 개의 정점 정보를 문자열 형태로 저장해놓는 클래스이다.*/
public class VertexInfo {
    private final String vertex_info;
}
