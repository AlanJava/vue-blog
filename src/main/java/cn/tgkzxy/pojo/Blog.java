package cn.tgkzxy.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Blog {
    private int cid;
    private String title;
    private String content;
    private long created;
    private String category;
    private int views;
    private String introduction;
}
