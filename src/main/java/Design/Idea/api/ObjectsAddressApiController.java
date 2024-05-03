/*
 * 클래스 기능 : 건물 주소에 대한 정보를 JSON 형식으로 반환하는 API 클래스
 * 최근 수정 일자 : 2024.05.03(금)
 */
package Design.Idea.api;

import Design.Idea.dto.ObjectAddressVCResponse;
import Design.Idea.model.ObjectAddress;
import Design.Idea.service.ObjectAddressService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/*")
public class ObjectsAddressApiController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ObjectAddressService objectAddressService;

    @GetMapping("/address")
    public ObjectAddressVCResponse<String> getAllObjectAddress() {
        logger.info("Get all objects address");
        List<ObjectAddress> address = objectAddressService.findAll();
        ObjectAddressVCResponse<String> response = new ObjectAddressVCResponse<>();
        response.setAddress(address.stream().map(ObjectAddress::getAddress).toList());
        return response;
    }
}
