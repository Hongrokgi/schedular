package com.schedular.schedualr.controller;

import com.schedular.schedualr.domain.dto.CameraResponseDto;
import com.schedular.schedualr.domain.entity.Camera;
import com.schedular.schedualr.domain.vo.ProductVo;
import com.schedular.schedualr.domain.vo.UserVo;
import com.schedular.schedualr.service.CameraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JavaApiController {
    private final CameraService cameraService;

    /*
    *  특정 camera 찾기
    * */
    @GetMapping("/api/v1/camera")
    public CameraResponseDto findCamera(@RequestBody Map<String, Long> request) {
        Long id=request.get("cameraId");
        return cameraService.findSpecificCamera(id);
    }

    /*
    *   camera list 반환
    * */
    @GetMapping("/api/v2/cameras")
    public List<CameraResponseDto> cameraList() {
        return cameraService.findAll();

    }
    // 수정하는 경우 Entity 를 노출시키면 안된다고 생각해서 dto 반환 후 dto -> Entity 로 builder 를 했더니
    // 오히려 새롭게 추가가 됐는데 JPA 입장에서 한 트랜잭션 안에서  동일성이 보장되는데 EventListener 를 변경한 상황에서
    // JPA 는 이를 새로운 엔티티 추가로 인식하여 변경감지가 이루어지지 않음
    // 우선 빌더에는 해당 엔티티의 아이디는 포함되어있지 않음 .. 아마 그래도 똑같았을거임
    // 오히려 수정하는 부분은 사용자에게 Entity 를 노출시킬 필요는 없으니 JpaRepository 에서 Entity 를 다이렉트로 반환받아서
    // 그 로직안에서 수정이 이루어지게 하고 id 값만 반환하게 해주면 될거같다.
    // 어떤 컬럼을 수정하든 틀은 변하지 않음
    @PutMapping("/api/v1/update")
    public Long update(@RequestBody Map<String, Long> request) {
        Long id = request.get("cameraId");
        return cameraService.changeEventListener(id);
    }

    /* json list 값 받기 */
    @PostMapping("/api/test")
    public UserVo testApiTest(@RequestBody UserVo userVo) {
        System.out.println("userVo = " + userVo.toString());

        for (ProductVo product : userVo.getProducts()) {
            System.out.println("product = " + product.toString());
        }
        return userVo;
    }
}
