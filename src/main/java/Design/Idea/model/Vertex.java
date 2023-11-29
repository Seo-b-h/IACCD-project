package Design.Idea.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
/*정점의 정보를 저장하는 클래스이다.*/
public class Vertex {
    final String ID;
    final String Lat;
    final String Lng;
    final String isDestination;
}
