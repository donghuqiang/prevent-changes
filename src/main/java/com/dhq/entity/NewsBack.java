package com.dhq.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author dhq
 * @since 2020-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_news_back")
public class NewsBack extends Model<NewsBack> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("TABLENAME")
    private String tablename;

    @TableField("FIELDNAME")
    private String fieldname;

    @TableField("ENCRYPTVALUE")
    private String encryptvalue;

    @TableField("CREATETIME")
    private LocalDateTime createtime;

    @TableField("BUSINESSID")
    private String businessid;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
