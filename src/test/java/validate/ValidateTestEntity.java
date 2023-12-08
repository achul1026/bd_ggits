package validate;

/**
 * 설명
 *
 * @author : Charles Kim
 * @fileName :  ValidateTestEntity
 * @since : 2023-09-04
 */
public class ValidateTestEntity {

    public String test1;
    public String test2;
    public String test3;
    public String test4;

    public ValidateTestEntity(String test1, String test2, String test3, String test4) {
        this.test1 = test1;
        this.test2 = test2;
        this.test3 = test3;
        this.test4 = test4;
    }

    public String getTest1() {
        return test1;
    }

    public String getTest2() {
        return test2;
    }

    public String getTest3() {
        return test3;
    }

    public String getTest4() {
        return test4;
    }
}
