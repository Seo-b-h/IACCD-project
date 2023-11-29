package Design.Idea.mapper;

import Design.Idea.model.Test;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    Test test() throws Exception;
}