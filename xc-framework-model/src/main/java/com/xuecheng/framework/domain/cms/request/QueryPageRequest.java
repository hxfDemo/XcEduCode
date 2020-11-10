package com.xuecheng.framework.domain.cms.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.http.protocol.RequestDate;
@Data
@ToString
public class QueryPageRequest extends RequestDate {
@ApiModelProperty("站点id")
private String siteId;
    @ApiModelProperty("当前页Id")
private String pageId;
    @ApiModelProperty("页名")
private String pageName;
    @ApiModelProperty("别名")
private String pageAliase;
    @ApiModelProperty("模板id")
private String templateId;
}
