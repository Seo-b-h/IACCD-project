/*
 * 클래스 기능 : 건물 주소 정보 편집을 위한 service interface
 * 최근 수정 일자 : 2024.05.01(수)
 */
package Design.Idea.service;

import Design.Idea.model.ObjectAddress;

import java.util.List;

public interface ObjectAddressService {

    public ObjectAddress save(String address);

    public ObjectAddress findById(Long id);

    public List<ObjectAddress> findAll();

    public void deleteById(Long id);
}
