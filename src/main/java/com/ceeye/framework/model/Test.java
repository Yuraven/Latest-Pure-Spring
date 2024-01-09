package com.ceeye.framework.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 测试表
 *
 * @author raven
 * @since 2020-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Test extends Model<Test> {

    private static final long serialVersionUID=1L;

    private Integer id;

    private String name;

    private Long testId;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
