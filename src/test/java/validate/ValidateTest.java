package validate;


import com.neighbor21.ggits.common.component.validate.ValidateBuilder;
import com.neighbor21.ggits.common.component.validate.ValidateChecker;
import com.neighbor21.ggits.common.component.validate.ValidateResult;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;
import java.util.Map;

/**
 * 설명
 *
 * @author : Charles Kim
 * @fileName :  ValidateTest
 * @since : 2023-08-31
 */

public class ValidateTest {

    @DisplayName("Validation Map타입 테스트")
    @Test
    void mapValidateTest(){
        Map<String, String> map = new HashMap<>();

        map.put("username","김철민");
        map.put("email","charles.ku@edus.co.kr");
        map.put("hp","01073442318");

        ValidateBuilder validator = new ValidateBuilder(map);

        validator
                .addRule("username", new ValidateChecker().setRequired())
                .addRule("email", new ValidateChecker().setEmail().setRequired());

        ValidateResult result = validator.isValid();
        System.out.println("====== success " + result.isSuccess());
        System.out.println("====== message " + result.getMessage());



        ValidateTestEntity entity = new ValidateTestEntity("111", "222", "333","444");
        ValidateBuilder dtoValidator = new ValidateBuilder(entity);
        dtoValidator
                .addRule("test1", new ValidateChecker().setRequired())
                .addRule("test2", new ValidateChecker().setEmail().setRequired());
        ValidateResult dtoValidatorResult = dtoValidator.isValid();

        System.out.println("====== success " + dtoValidatorResult.isSuccess());
        System.out.println("====== message " + dtoValidatorResult.getMessage());

    }
}
