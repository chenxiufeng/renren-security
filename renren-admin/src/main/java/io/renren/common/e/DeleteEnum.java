package io.renren.common.e;

/**
 * @author 
 * @Title: DeleteEnum
 * @Description: 删除状态枚举类
 * @date 2018-08-068:43
 */
public enum  DeleteEnum implements BaseEnum{

    /**
     * 1.删除
     */
    DELETE(1,"删除"),

    /**
     * 0.未删除
     */
    NOT_DELETE(0,"未删除"),
    ;

    private int id;
    private String describe;

    DeleteEnum(int id, String describe){
        this.id = id;
        this.describe = describe;
    }

    @Override
    public int toInt() {
        return id;
    }

    @Override
    public String toCode() {
        return null;
    }

    @Override
    public String toDescribe() {
        return describe;
    }
}
