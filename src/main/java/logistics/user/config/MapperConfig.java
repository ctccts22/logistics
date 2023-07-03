package logistics.user.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MapperConfig 클래스는 ModelMapper의 설정을 정의하는 클래스입니다.
 * ModelMapper는 객체 간의 데이터 매핑을 처리하는 데 사용되며,
 * 이 설정 클래스를 통해 매핑 전략 및 Null 값을 건너뛰는 옵션을 설정합니다.
 */
@Configuration
public class MapperConfig {
    /**
     * ModelMapper Bean을 생성하고 설정합니다.
     * STRICT 매칭 전략은 소스 객체와 대상 객체의 속성 이름이 정확히 일치하는 경우에만 ModelMapper에 매핑하도록 지시합니다.
     * setSkipNullEnabled(true)는 ModelMapper에 Null 값을 건너뛰도록 지시합니다.
     * 그룹을 그룹ID로 변환하는 컨버터를 정의하고, 이를 코드와 코드DTO 간의 매핑에 사용합니다.
     *
     * @return 설정이 완료된 ModelMapper Bean.
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        modelMapper.getConfiguration().setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        return modelMapper;
    }
}
